package org.ycc.service;

import org.ycc.pojo.PageResult;
import org.ycc.pojo.Student;
import org.ycc.pojo.StudentQueryParam;

public interface StudentService {
    /**
     * 学员分页条件查询
     *
     * @param studentQueryParam
     * @return
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void delete(Integer[] ids);

    void add(Student student);
}