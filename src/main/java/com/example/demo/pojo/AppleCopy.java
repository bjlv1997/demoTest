package com.example.demo.pojo;

import java.math.BigDecimal;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/12/24 9:36
 */

public class AppleCopy {

    private String id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
