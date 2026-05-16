package org.ycc.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ycc.mapper.OperateLogMapper;
import org.ycc.pojo.OperateLog;
import org.ycc.pojo.PageResult;
import org.ycc.pojo.Result;

import java.util.List;

/**
 * 操作日志查询接口。
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {

    private final OperateLogMapper operateLogMapper;

    public OperateLogController(OperateLogMapper operateLogMapper) {
        this.operateLogMapper = operateLogMapper;
    }

    /**
     * 分页查询操作日志，对应前端请求：GET /log/page?page=1&pageSize=10。
     */
    @GetMapping("/page")
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询操作日志：page={}, pageSize={}", page, pageSize);

        PageHelper.startPage(page, pageSize);
        List<OperateLog> operateLogList = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) operateLogList;

        return Result.success(new PageResult<>(p.getTotal(), p.getResult()));
    }
}
