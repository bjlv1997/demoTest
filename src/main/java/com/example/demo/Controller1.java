package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.ResultFactory;
import com.example.demo.common.ResultObject;
//import com.example.demo.common.SupplierUtil;
import com.example.demo.pojo.Course;
import com.example.demo.service.Service1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;


@RestController
public class Controller1 {

    private static final Logger logger = LoggerFactory.getLogger(Controller1.class);

    @Value("${my.name}")
    private String name;
    @Value("${my.age}")
    private int age;

    @Autowired
    private Service1 service1;

    @RequestMapping(value = "/miya")
    public String miya() {
        System.out.printf("123");
        return name + ":" + age;
    }

    @RequestMapping(value = "/test")
    public ResultObject<Object> test() throws Exception {

        MyThread myThread = new MyThread();
        myThread.run();
        return ResultFactory.success("调用成功");
    }

    public class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread.run()");
        }
    }


    @RequestMapping(value = "/jsonTest")
    public String jsonTest(@RequestBody Course list) {
        logger.info("入参：" + list);
        if (list == null) {
//            return ResultFactory.error(400, "参数为空");
            return "数据为空";
        }
        String jsonString = JSON.toJSONString(list);
        logger.info("入参：" + jsonString);
//        return ResultFactory.success("调用成功", jsonString);
        return jsonString;
    }

//    @RequestMapping(value = "/result")
//    public String result(@RequestBody String msg) throws IOException {
//        logger.info("入参：" + msg);
//        String result1 = SupplierUtil.sendPost(null, "http://172.16.1.176:8080/oauth/oauth/token", null);
//        logger.info("result{}", result1);
//        Map stringToMap = JSONObject.parseObject(result1);
//        logger.info(JSON.toJSONString(stringToMap));
//        logger.info(stringToMap.get("access_token").toString());
//        Map<String, Object> authorization1 = new HashMap<>();
//        authorization1.put("Authorization", stringToMap.get("access_token"));
//        String result = SupplierUtil.sendPost1(msg, "http://172.16.1.176:8080/spuc/v1/manifestCreate/addManifest", authorization1);
//        // logger.info("合同系统的返回的参数：" + result);
//        return result;
//    }
//
//
//    @RequestMapping(value = "/result1")
//    public String result1(@RequestBody String msg) throws IOException {
//        logger.info("合同系统的返回的参数：" + msg);
//        String authorization = "Basic " + Base64.getUrlEncoder().encodeToString(("authUser4Import" + ":" + "importPass").getBytes());
//        logger.info(authorization);
//        Map<String, Object> authorization1 = new HashMap<>();
//        authorization1.put("Authorization", authorization);
//        String result = SupplierUtil.sendPost1(msg, "http://10.20.2.21/hmdm/auth/import", authorization1);
//        logger.info("合同系统的返回的参数：" + result);
//        return result;
//    }


    @RequestMapping(value = "/StringTest")
    public String StringTest() {
        try {
            String str = "";
            Long startTime = System.currentTimeMillis();
            logger.info("开始执行时间：{}", startTime);
            for (int i = 0; i < 2147483647; i++) {
                str += "S";
            }
//            Integer integer;
            Long endTime = System.currentTimeMillis();
            logger.info("执行结束时间：{}", endTime);
            logger.info("执行时间：{}", endTime - startTime);
            logger.info("======{}", str.length());
            logger.info("Interge-max：{}", Integer.MAX_VALUE);
            logger.info("Interge-min: {}", Integer.MIN_VALUE);
            return "===>" + str.length();
        } catch (Exception e) {
            e.printStackTrace();

            logger.info("======{}", e.getMessage());
            return null;
        }
    }


    @RequestMapping("/async")
    public ResultObject<Object> async() {
        String buffer = getAccountOpeningName("测试数据", 1, 11);
        logger.info("------------:{}", buffer);
        async1();
        service1.async2();
        async3();

        ExecutorService executorService = new ThreadPoolExecutor(4,
                6,
                0L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(512),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());




//        ExecutorsService executorService = new ThreadPoolExecutor(4,6,0L,TimeUnit.Seconds,
//                new LinkedBlockingQueue);

//        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> {
            async2();
        });

        logger.info("测试4");
        return ResultFactory.success("调用成功");
    }


    public String async1() {

        logger.info("测试1");
        return "测试1";
    }

    @Async
    public void async2() {

        try {
            Thread.sleep(5000);
        } catch (Exception e) {

        }
        logger.info("测试2");
    }

    public String async3() {

        logger.info("测试3");
        return "测试3";
    }


    public String getAccountOpeningName(String openAccOrgName, Integer secAccountStatus, Integer accountType){

        //判断账户状态 0 正常;1 挂失;2 冻结;3 休眠;4 注销;5 禁买;6 禁卖
        StringBuffer buffer = new StringBuffer();
        switch (accountType) {
            case 11:
                buffer.append("沪市A股账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 12:
                buffer.append("沪市B股账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 13:
                buffer.append("沪市封闭式基金账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 14:
                buffer.append("沪市A股信用证券账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 15:
                buffer.append("沪市衍生品合约账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 21:
                buffer.append("深市A股账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 22:
                buffer.append("深市B股账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 23:
                buffer.append("深市封闭式基金账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 24:
                buffer.append("深市A股信用证券账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 25:
                buffer.append("深市衍生品合约账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 31:
                buffer.append("全国中小企业股份转让系统账户/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            case 99:
                buffer.append("其他/").append(openAccOrgName);
                if(secAccountStatus == 3){
                    buffer.append("/休眠");
                }
                break;
            default:
                break;
        }

        return buffer.toString();
    }


}
