package org.ycc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private Integer page = 1;       // 页码，默认1
    private Integer pageSize = 10;  // 每页记录数，默认10
    private String name;            // 姓名
    private Integer degree;         // 最高学历
    private Integer clazzId;        // 班级ID
}