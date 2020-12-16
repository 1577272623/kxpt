package com.rongyungov.kxpt.api.vo;

import lombok.Data;

@Data
public class ClassTaskVo {

    private Long taskId;
    private Long classNo;
    private String taskType;
    private String taskName;
    private String taskDec;
    private Integer overTaskNum;
    private Integer studentNum;
    private Integer taskNum;

    public ClassTaskVo() {
    }

    public ClassTaskVo(Long taskId, Long classNo, String taskType, String taskName, String taskDec, Integer overTaskNum, Integer studentNum, Integer taskNum) {
        this.taskId = taskId;
        this.classNo = classNo;
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskDec = taskDec;
        this.overTaskNum = overTaskNum;
        this.studentNum = studentNum;
        this.taskNum = taskNum;
    }
}
