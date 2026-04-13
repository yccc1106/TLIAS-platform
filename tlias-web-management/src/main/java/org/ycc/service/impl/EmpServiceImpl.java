package org.ycc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.ycc.mapper.EmpExprMapper;
import org.ycc.mapper.EmpMapper;
import org.ycc.pojo.Emp;
import org.ycc.pojo.EmpExpr;
import org.ycc.pojo.EmpQueryParam;
import org.ycc.pojo.PageResult;
import org.ycc.service.EmpService;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

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
     * @param
     * @param
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

    @Override
    public void save(Emp emp) {

        //补全基础信息
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //保存员工基础信息
        empMapper.insert(emp);



        // 添加员工工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
            empExprMapper.insertBatch(exprList);
        }

    }
}
