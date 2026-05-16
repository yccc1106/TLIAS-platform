package org.ycc.mapper;


import org.apache.ibatis.annotations.*;
import org.ycc.pojo.Dept;

import java.util.List;

@Mapper
public interface DeptMapper {


    /**
     * 查询所有的部门数据
     *
     * @return
     */
//    方法一：手动结果映射
//    @Results({
//            @Result(column = "create_time",property = "createTime"),
//            @Result(column = "update_time",property = "updateTime")
//    })
//    方法二：起别名
//    @Select("select id, name, create_time createTime, update_time updateTime from tlias.dept order by update_time desc ")
//    方法三：开启驼峰命名:在xml文件里面开启
    @Select("select id, name, create_time, update_time  from tlias.dept order by update_time desc ")
    List<Dept> findAll();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from tlias.dept where id = #{id}")
    void deleteById(Integer id);
    @Insert("insert into tlias.dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
