package org.ycc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ycc.pojo.Emp;
import org.ycc.pojo.EmpQueryParam;
import org.ycc.pojo.PageResult;
import org.ycc.pojo.Result;
import org.ycc.service.EmpService;

import java.time.LocalDate;

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
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询：{},{},{},{},{},{}",empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

}
