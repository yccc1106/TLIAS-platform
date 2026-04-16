package org.ycc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzQueryParam {
    private Integer page = 1;       // 页码，默认1
    private Integer pageSize = 5;   // 每页记录数，按接口需求样例默认5
    private String name;            // 班级名称

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;        // 结课时间-开始

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;          // 结课时间-结束
}