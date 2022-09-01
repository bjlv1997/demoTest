package com.example.demo.deepcope.main;

import com.alibaba.fastjson.JSON;
import com.example.demo.deepcope.pojo.Student;
import com.example.demo.deepcope.pojo.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/11/10 15:42
 */
public class DeppCopy {

    private static final Logger logger = LoggerFactory.getLogger(DeppCopy.class);
    public static void main(String[] args) {
        Subject subject = new Subject("yuwen");
        Student studentA = new Student();
        studentA.setSubject(subject);
        studentA.setName("Lynn");
        studentA.setAge(20);
        logger.info("studentA:{}", JSON.toJSONString(studentA));
        Student studentB = new Student();
        BeanUtils.copyProperties(studentA,studentB);
        logger.info("studentB:{}", JSON.toJSONString(studentB));
        studentB.setName("Lily");
        studentB.setAge(18);
        Subject subjectB = studentB.getSubject();
        subjectB.setName("lishi");
        System.out.println("studentA:" + studentA.toString());
        System.out.println("studentB:" + studentB.toString());
    }
}
