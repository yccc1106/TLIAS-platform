package org.ycc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ycc.pojo.Student;
import org.ycc.pojo.StudentQueryParam;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 学员条件分页查询
     *
     * @param studentQueryParam 查询条件
     * @return 学员列表
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    void delete(Integer[] ids);

    void add(Student student);

    Student getById(Integer id);

    void update(Student student);

    void updateScore(Integer id, Integer score);
}