package org.ycc.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ycc.mapper.EmpLogMapper;
import org.ycc.pojo.EmpLog;
import org.ycc.service.EmpLogService;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}