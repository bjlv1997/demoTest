package com.example.demo.deepcope.pojo;

import java.io.Serializable;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/11/10 15:15
 */
public class Subject implements Serializable {

    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[Subject: " + this.hashCode() + ",name:" + name + "]";
    }
}
