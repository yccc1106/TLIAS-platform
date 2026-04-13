package org.ycc.service;

import org.ycc.pojo.Emp;
import org.ycc.pojo.EmpQueryParam;
import org.ycc.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {
   // PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParam empQueryParam);
}
