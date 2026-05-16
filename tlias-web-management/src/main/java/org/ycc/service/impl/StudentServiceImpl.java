package org.ycc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ycc.mapper.StudentMapper;
import org.ycc.pojo.PageResult;
import org.ycc.pojo.Student;
import org.ycc.pojo.StudentQueryParam;
import org.ycc.service.StudentService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 1. 设置分页参数 (PageHelper)
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());

        // 2. 执行查询
        List<Student> studentList = studentMapper.list(studentQueryParam);

        // 3. 解析查询结果，并封装成 PageResult 对象
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(Integer[] ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void add(Student student) {

        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());

        if (student.getViolationCount() == null) {
            student.setViolationCount((short) 0);
        }
        if (student.getViolationScore() == null) {
            student.setViolationScore((short) 0);
        }

        studentMapper.add(student);
    }

    @Override
    public Student getById(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {

        student.setUpdateTime(LocalDateTime.now());

        studentMapper.update(student);
    }

    @Override
    public void updateScore(Integer id, Integer score) {
        studentMapper.updateScore(id, score);
    }
}