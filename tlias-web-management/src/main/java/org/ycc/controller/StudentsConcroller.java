package org.ycc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ycc.pojo.PageResult;
import org.ycc.pojo.Result;
import org.ycc.pojo.Student;
import org.ycc.pojo.StudentQueryParam;
import org.ycc.service.StudentService;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentsConcroller {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("学员分页查询：{}", studentQueryParam);
        // 调用 service 进行分页查询
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        // 返回统一响应结果
        return Result.success(pageResult);
    }

}