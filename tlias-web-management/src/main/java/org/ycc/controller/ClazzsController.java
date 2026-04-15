package org.ycc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping
    public Result saveClazz(@RequestBody Clazz clazz) {
        log.info("添加班级数据：{}", clazz);
        clazzService.insertClazz(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getClazz(@PathVariable("id") Integer id) {
        log.info("查询班级：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz) {
        log.info("更新班级：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }
}
