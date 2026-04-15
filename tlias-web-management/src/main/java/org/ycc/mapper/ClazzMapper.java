package org.ycc.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.ycc.pojo.Clazz;
import org.ycc.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {

    @Select("select * from clazz order by create_time desc")
    List<Clazz> findAll();

    List<Clazz> list(ClazzQueryParam clazzQueryParam);


    @Delete("delete  from clazz where id = #{id}")
    void delete(Integer id);
}
