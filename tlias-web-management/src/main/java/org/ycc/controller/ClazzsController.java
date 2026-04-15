package org.ycc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ycc.pojo.Clazz;
import org.ycc.pojo.ClazzQueryParam;
import org.ycc.pojo.PageResult;
import org.ycc.pojo.Result;
import org.ycc.service.ClazzService;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzsController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("条件分页查询班级数据：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable("id") Integer id) {
        if (id == null) {
            return Result.error("班级ID不能为空");
        }
        log.info("删除班级：{}", id);
        clazzService.delete(id);
        return Result.success();
    }
}
