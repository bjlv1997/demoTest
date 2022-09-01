package com.example.demo.shallowcopy.main;

import com.example.demo.Controller1;
import com.example.demo.shallowcopy.pojo.Student;
import com.example.demo.shallowcopy.pojo.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/11/10 11:09
 */
public class ShallowCopy {
    private static final Logger logger = LoggerFactory.getLogger(Controller1.class);

    public static void main(String[] args) {

//        Subject subject = new Subject("yuwen");
//        Student studentA = new Student();
//        studentA.setSubject(subject);
//        studentA.setName("Lynn");
//        studentA.setAge(20);
//        Student studentB = (Student) studentA.clone();
//        studentB.setName("Lily");
//        studentB.setAge(18);
//        Subject subjectB = studentB.getSubject();
//        subjectB.setName("lishi");
//        System.out.println("studentA:" + studentA.toString());
//        System.out.println("studentB:" + studentB.toString());

        Subject subject = new Subject("yuwen");
        Student studentA = new Student();
        studentA.setSubject(subject);
        studentA.setName("Lynn");
        studentA.setAge(20);
        Student studentB = new Student();

        BeanUtils.copyProperties(studentA,studentB);
        studentB.setName("Lily");
        studentB.setAge(18);
        Subject subjectB = studentB.getSubject();
        subjectB.setName("lishi");
        System.out.println("studentA:" + studentA.toString());
        System.out.println("studentB:" + studentB.toString());
    }
}
