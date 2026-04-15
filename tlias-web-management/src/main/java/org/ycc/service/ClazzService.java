package org.ycc.service;

import org.ycc.pojo.Clazz;
import org.ycc.pojo.ClazzQueryParam;
import org.ycc.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    List<Clazz> findAll();

    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void delete(Integer id);
}
