package org.ycc.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ycc.pojo.EmpExpr;

import java.util.List;

/**
 * 员工工作经历mapper
 */
@Mapper
public interface EmpExprMapper {

    /**
     * 批量插入员工工作经历
     * @param exprList
     */
    void insertBatch(List<EmpExpr> exprList);
}
