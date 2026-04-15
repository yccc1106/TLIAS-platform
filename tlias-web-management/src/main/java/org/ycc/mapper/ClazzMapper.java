package org.ycc.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject,create_time,update_time) VALUES " +
            "(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insertClazz(Clazz clazz);

    @Select("select * from clazz where id = #{id}")
    Clazz getClazz(Integer id);
}
