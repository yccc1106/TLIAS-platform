package org.ycc.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.ycc.pojo.Result;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result ex(Exception e) {
        log.error("系统异常：", e);
        return Result.error("对不起,操作失败,请联系管理员");
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        log.warn("业务异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.warn("数据库约束异常：{}", e.getMessage());
        String message = e.getMessage();
        if (message != null && message.contains("Duplicate entry")) {
            if (message.contains("dept.name")) {
                return Result.error("部门名称已存在，请更换后重试");
            }
            return Result.error("数据已存在，请勿重复添加");
        }
        return Result.error("数据不符合要求，请检查后重试");
    }

}
