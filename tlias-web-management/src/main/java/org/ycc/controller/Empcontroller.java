package org.ycc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.ycc.pojo.Emp;
import org.ycc.pojo.EmpQueryParam;
import org.ycc.pojo.PageResult;
import org.ycc.pojo.Result;
import org.ycc.service.EmpService;

import java.util.Arrays;
import java.util.List;

/**
 * 员工管理的controller
 */
@RestController
@Slf4j
@RequestMapping("/emps")
public class Empcontroller {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     *
     * @return
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize,
//                       String name, Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
//                       ){
//        log.info("分页查询：{},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
//       PageResult<Emp> pageResult = empService.page(page,pageSize,name,gender,begin,end);
//        return Result.success(pageResult);
//    }
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询：{}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }


    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工:{}", emp);
        empService.save(emp);
        return Result.success();

    }


    /**
     * 删除员工/批量删除员工
     *
     * @param ids
     * @return
     */
    // method1:以数组的形式
//    @DeleteMapping
//    public Result delete(Integer[] ids) {
//        log.info("删除员工：{}", Arrays.toString(ids));
//        return Result.success();
//
//    }
    //method2:以集合的形式
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();

    }

}
