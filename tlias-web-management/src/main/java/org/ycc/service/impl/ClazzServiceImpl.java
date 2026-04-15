package org.ycc.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ycc.mapper.ClazzMapper;
import org.ycc.pojo.Clazz;
import org.ycc.pojo.ClazzQueryParam;
import org.ycc.pojo.PageResult;
import org.ycc.service.ClazzService;

import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        // 2. 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        // 3. 解析查询结果并封装
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(Integer id) {
        clazzMapper.delete(id);
    }


}