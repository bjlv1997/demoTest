package com.example.demo.pojo;

import java.util.List;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/7/20 16:27
 */
public class CourseDto {
    private Integer courseId;

    private String courseName;

    private String remarks;

    private String remarks1;

    List<String> list;

    List<ContractProcessingResult> contractProcessingResultList;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public List<ContractProcessingResult> getContractProcessingResultList() {
        return contractProcessingResultList;
    }

    public void setContractProcessingResultList(List<ContractProcessingResult> contractProcessingResultList) {
        this.contractProcessingResultList = contractProcessingResultList;
    }

    public String getRemarks1() {
        return remarks1;
    }

    public void setRemarks1(String remarks1) {
        this.remarks1 = remarks1;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
