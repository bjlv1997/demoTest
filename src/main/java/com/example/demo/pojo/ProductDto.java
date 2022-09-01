package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/9/16 14:59
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {

    //id为表的主键
    private Long id;

    private String brand;

    private String industry;

    private String category;

    private String idFlag;

    private String brandFlag;

    private String industryFlag;

    private String categoryFlag;

    //对象是否发生改变
    private String objectFlag;

    public ProductDto() {
    }

    public ProductDto(Long id, String brand, String industry, String category) {
        this.id = id;
        this.brand = brand;
        this.industry = industry;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdFlag() {
        return idFlag;
    }

    public void setIdFlag(String idFlag) {
        this.idFlag = idFlag;
    }

    public String getBrandFlag() {
        return brandFlag;
    }

    public void setBrandFlag(String brandFlag) {
        this.brandFlag = brandFlag;
    }

    public String getIndustryFlag() {
        return industryFlag;
    }

    public void setIndustryFlag(String industryFlag) {
        this.industryFlag = industryFlag;
    }

    public String getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(String categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    public String getObjectFlag() {
        return objectFlag;
    }

    public void setObjectFlag(String objectFlag) {
        this.objectFlag = objectFlag;
    }
}
