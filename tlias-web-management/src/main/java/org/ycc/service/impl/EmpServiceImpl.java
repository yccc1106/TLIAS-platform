package org.ycc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ycc.mapper.EmpMapper;
import org.ycc.pojo.Emp;
import org.ycc.pojo.EmpQueryParam;
import org.ycc.pojo.PageResult;
import org.ycc.service.EmpService;

import java.time.LocalDate;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    //-------------------原始分页查询---------------------
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        long total = empMapper.count();
//
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        return new PageResult<Emp>(total,rows);
//    }

    /**
     * 基于pagehelper来实现
     *
     * @param page
     * @param pageSize
     * @return
     */

    // 注意事项：1.定义的SQL语句结尾不能加分号。
    //         2.PageHelper仅仅能对紧跟在其后面的第一个查询语句进行分页处理
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
//        //1.设置分页参数(pagehelper)
//
//        PageHelper.startPage(page, pageSize);
//
//        //2.执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//
//        //3.解析查询结果，并封装
//        Page<Emp> p = (Page<Emp>) empList;
//
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数(pagehelper)

        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        //3.解析查询结果，并封装
        Page<Emp> p = (Page<Emp>) empList;

        return new PageResult<Emp>(p.getTotal(), p.getResult());

    }
}
