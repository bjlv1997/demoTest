package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.pojo.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author beijing.lv
 * @version 1.0
 * @date 2022/2/14 14:10
 */
@RestController
@RequestMapping("/testController")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/getFileName")
    public String getFileName(MultipartFile file){

        logger.info("获取文件名称");
        logger.info("文件大小：{}", file.getSize());
        return file.getOriginalFilename();
    }


    @PostMapping("/getDocumerName")
    public String getDocumerName(MultipartFile file){

        logger.info("获取文件名称----------");
        return file.getOriginalFilename();
    }

    @GetMapping("/getName")
    public String getName(@RequestBody ProductDto productDto){

        logger.info("获取对象数据：{}", JSON.toJSONString(productDto));
        return "个体请求";
    }


    @PostMapping("/postName")
    public String postName(@RequestBody ProductDto productDto){

        logger.info("post获取对象数据：{}", JSON.toJSONString(productDto));
        return "个体请求";
    }
}
