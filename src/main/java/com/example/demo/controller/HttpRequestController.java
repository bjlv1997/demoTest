package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.HttpRequestUtils;
import com.example.demo.common.ResultFactory;
import com.example.demo.common.ResultObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author beijing.lv@hand-china.com
 * @version 1.0
 * @date 2020/9/11 11:31
 */

@RestController
@RequestMapping("/httpRequest")
public class HttpRequestController {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestController.class);

    /**
     * @Description: 发送Get请求无参或者"/"拼接的参数
     * @Param: @param
     * @return: java.lang.String
     * @Date: 2020/9/11
     */
    @PostMapping(value = "/httpGetNoParameter")
    public ResultObject<Object> sendGetNoParameter() {
        try {
            String request = HttpRequestUtils.sendGet("http://10.20.2.133:8081/contract/platform/contract/download/8997");
            logger.info("返回数据request：{}", request);

            return ResultFactory.success("成功", request);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("出现异常：{}", e.getMessage());
            return ResultFactory.error("出现异常：{}" + e.getMessage());
        }

    }

    /**
     * @Description: 发送Get请求带参数
     * @Param: @param
     * @return: java.lang.String
     * @Date: 2020/9/11
     */
    @PostMapping(value = "/sendGetParameter")
    public ResultObject<Object> sendGetParameter() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("companyNC", "阡耘信息科技有限责任公司");
            //方法一
            String request = HttpRequestUtils.sendGetParameter("http://10.30.5.237:8283/rcp/v1/executor/supplier/register/judge", params);
            logger.info("返回数据request：{}", request);

            //方法二
            String request1 = HttpRequestUtils.sendGetParameter1("http://10.30.5.237:8283/rcp/v1/executor/supplier/register/judge", params);
            logger.info("返回数据request1：{}", request1);

            return ResultFactory.success("成功", request);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("出现异常：{}", e.getMessage());
            return ResultFactory.error("出现异常：{}" + e.getMessage());
        }

    }

    /**
     * @Description: 发送Get请求带参数和请求头
     * @Param: @param
     * @return: java.lang.String
     * @Date: 2020/9/11
     */
    @PostMapping(value = "/sendGetParameterAndHeader")
    public ResultObject<Object> sendGetParameterAndHeader() {
        try {
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("usercode", "XNZD");
            paramsMap.put("pwd", "a123456");

            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("token", "123");

            String request = HttpRequestUtils.sendGetParameterAndHeader("http://10.30.5.237:8283/rcp/v1/queryContract", paramsMap, headerMap);
            logger.info("返回数据request：{}", request);
            return ResultFactory.success("成功", request);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("出现异常：{}", e.getMessage());
            return ResultFactory.error("出现异常：{}" + e.getMessage());
        }
    }

    /**
     * @Description: 发送POST请求
     * @Param: @param
     * @return: java.lang.String
     * @Date: 2020/9/11
     */
    @PostMapping(value = "/sendPost")
    public ResultObject<Object> sendPost() {
        try {
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("subcompanycode", "9201");
            //方法一
            String request = HttpRequestUtils.sendPost("http://oa.htd.cn/api/ProjectService/getProname", paramsMap);
            logger.info("返回数据request：{}", request);
            //方法二
            String request1 = HttpRequestUtils.sendPost1("http://oa.htd.cn/api/ProjectService/getProname", JSON.toJSONString(paramsMap));
            logger.info("返回数据request1：{}", request1);
            return ResultFactory.success("成功", request);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("出现异常：{}", e.getMessage());
            return ResultFactory.error("出现异常：{}" + e.getMessage());
        }
    }

    /**
     * @Description: 发送POST请求带参数和请求头
     * @Param: @param
     * @return: java.lang.String
     * @Date: 2020/9/11
     */
    @PostMapping(value = "/sendPostParameterAndHeader")
    public ResultObject<Object> sendPostParameterAndHeader() {
        try {
            //入参
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("usercode", "XNZD");
            paramsMap.put("pwd", "a123456");
            //请求头
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("uap_dataSource", "cs0401");
            headerMap.put("uap_usercode", "XNZD");
            //方法一
            String request = HttpRequestUtils.sendPostParameterAndHeader("http://193.168.1.133:1909/uapws/rest/user/login", JSON.toJSONString(paramsMap), headerMap);
            logger.info("返回数据request：{}", request);
            //方法二
            String request1 = HttpRequestUtils.sendPostParameterAndHeader1("http://193.168.1.133:1909/uapws/rest/user/login", JSON.toJSONString(paramsMap), headerMap);
            logger.info("返回数据request1：{}", request1);
            return ResultFactory.success("成功", request);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("出现异常：{}", e.getMessage());
            return ResultFactory.error("出现异常：{}" + e.getMessage());
        }
    }

    /**
     * @Description: 发送POST请求带参数和请求头 格式为 x-www-form-urlencoded
     * @Param: @param
     * @return: java.lang.String
     * @Date: 2020/9/11
     */
    @PostMapping(value = "/sendPostParameterAndHeader1")
    public ResultObject<Object> sendPostParameterAndHeader1() {
        try {
            //入参
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("appid", "contractclients");
            paramsMap.put("loginid", "08010088");

            String request1 = HttpRequestUtils.sendPostParameterAndHeader1("http://prep-cm.htd.cn/ssologin/getToken", JSON.toJSONString(paramsMap), null);
            logger.info("返回数据request1：{}", request1);
            return ResultFactory.success("成功", request1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("出现异常：{}", e.getMessage());
            return ResultFactory.error("出现异常：{}" + e.getMessage());
        }
    }

}
