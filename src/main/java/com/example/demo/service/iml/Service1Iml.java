package com.example.demo.service.iml;

import com.example.demo.service.Service1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author beijing.lv
 * @version 1.0
 * @date 2021/11/29 14:06
 */
@Service
public class Service1Iml implements Service1 {

    private static final Logger logger = LoggerFactory.getLogger(Service1Iml.class);


    @Override
    @Async
    public String async2() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        logger.info("测试2");
        return "测试2";
    }
}
