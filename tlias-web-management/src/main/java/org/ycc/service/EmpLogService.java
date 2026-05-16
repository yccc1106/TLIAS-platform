package org.ycc.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.ycc.pojo.EmpLog;

public interface EmpLogService {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertLog(EmpLog empLog);

}