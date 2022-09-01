package com.example.demo.pojo;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {
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

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    public Course(Integer courseId, String courseName, String remarks) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.remarks = remarks;
    }

    public Course(){}
}
