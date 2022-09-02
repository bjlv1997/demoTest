package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.IdGeneratorSnowflake;
import com.example.demo.common.ResultFactory;
import com.example.demo.common.ResultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author beijing.lv
 * @version 1.0
 * @date 2022/9/1 18:20
 */
@RestController
@RequestMapping("/idGeneratorSnowflake")
public class IdGeneratorSnowflakeController {

    private static final Logger logger = LoggerFactory.getLogger(IdGeneratorSnowflakeController.class);

    @Autowired
    private IdGeneratorSnowflake idGeneratorSnowflake;

    @GetMapping("/getUUID")
    public ResultObject<String> queryUUID() {

        long uuid = idGeneratorSnowflake.snowflakeId();
        logger.info("获取UUID：{}", JSON.toJSONString(uuid));

        return ResultFactory.success(String.valueOf(uuid));
    }
}
