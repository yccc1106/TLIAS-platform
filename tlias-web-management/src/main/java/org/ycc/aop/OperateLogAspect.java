package org.ycc.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.ycc.mapper.OperateLogMapper;
import org.ycc.pojo.OperateLog;
import org.ycc.utils.JwtUtils;

import java.time.LocalDateTime;

/**
 * 操作日志切面。
 * 拦截 controller 包下标注了 @Log 注解的方法，记录增、删、改接口的执行信息。
 */
@Slf4j
@Aspect
@Component
public class OperateLogAspect {

    // operate_log 表中 method_params 和 return_value 字段长度为 2000。
    private static final int MAX_TEXT_LENGTH = 2000;

    private final OperateLogMapper operateLogMapper;
    private final ObjectMapper objectMapper;

    public OperateLogAspect(OperateLogMapper operateLogMapper, ObjectMapper objectMapper) {
        this.operateLogMapper = operateLogMapper;
        this.objectMapper = objectMapper;
    }

    /**
     * 环绕通知：在目标方法执行前后分别记录开始时间和返回值，组装操作日志后入库。
     *
     * @param joinPoint 当前被拦截的方法调用
     * @return 目标方法的原始返回值
     * @throws Throwable 目标方法执行过程中抛出的异常
     */
    @Around("@annotation(org.ycc.anno.Log) && execution(* org.ycc.controller..*(..))")
    public Object recordOperateLog(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long costTime = System.currentTimeMillis() - begin;

        // 获取方法签名，用于记录方法名；目标对象用于记录类全名。
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getOperateEmpId());
        operateLog.setOperateTime(LocalDateTime.now());
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        operateLog.setMethodName(signature.getName());
        operateLog.setMethodParams(toJson(joinPoint.getArgs()));
        operateLog.setReturnValue(toJson(result));
        operateLog.setCostTime(costTime);

        try {
            operateLogMapper.insert(operateLog);
            log.info("记录操作日志成功：{}", operateLog);
        } catch (Exception e) {
            log.error("记录操作日志失败: {}", operateLog, e);
        }

        // 保持接口原有返回结果不变。
        return result;
    }

    /**
     * 从当前请求头 token 中解析登录员工 ID。
     *
     * @return 登录员工 ID；未获取到或解析失败时返回 null
     */
    private Integer getOperateEmpId() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes servletRequestAttributes)) {
            return null;
        }

        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            return null;
        }

        try {
            Claims claims = JwtUtils.parseJwt(token);
            Object id = claims.get("id");
            if (id instanceof Integer empId) {
                return empId;
            }
            if (id instanceof Number number) {
                return number.intValue();
            }
            return id == null ? null : Integer.valueOf(id.toString());
        } catch (Exception e) {
            log.warn("解析操作人ID失败", e);
            return null;
        }
    }

    /**
     * 将参数或返回值转换为 JSON 字符串，便于写入数据库。
     */
    private String toJson(Object value) {
        try {
            return truncate(objectMapper.writeValueAsString(value));
        } catch (JsonProcessingException e) {
            return truncate(String.valueOf(value));
        }
    }

    /**
     * 截断过长内容，避免超过数据库字段长度。
     */
    private String truncate(String value) {
        if (value == null || value.length() <= MAX_TEXT_LENGTH) {
            return value;
        }
        return value.substring(0, MAX_TEXT_LENGTH);
    }
}
