package com.example.demo.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/8/31 14:30
 */
public class Apple implements Serializable {

    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public Apple() {
    }

    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return Objects.equals(id, apple.id) &&
                Objects.equals(name, apple.name) &&
                Objects.equals(money, apple.money) &&
                Objects.equals(num, apple.num);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, money, num);
    }
    public class a {

    }
}
