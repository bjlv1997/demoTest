package com.example.demo.shallowcopy.pojo;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/11/10 11:06
 */
public class Subject {

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
