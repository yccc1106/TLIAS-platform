package org.ycc.mapper;

import org.apache.ibatis.annotations.*;
import org.ycc.pojo.Emp;
import org.ycc.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
   //---------------------------------原始分页查询实现---------------------------------
    /**
     * 查询中记录数
     *
     * @return
     */
//    @Select("select count(*) from  emp e left join dept d on e.dept_id = d.id")
//    public long count();

    /**
     * 分页查询方法
     *
     * @return
     */
//    @Select("select e.*,d.name deptName from  emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc limit #{start},#{PageSize}")
//    public List<Emp> list(Integer start, Integer PageSize);



    //pagehelper实现
    //@Select("select e.*,d.name deptName from  emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * 条件查询员工信息
     * @param empQueryParam
     * @return
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     * @param emp
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
            " values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getInfo(Integer id);

    /**
     * 根据id更新员工基本信息
     *
     * @param emp
     */
    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
}
