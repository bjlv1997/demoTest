package com.example.demo.common;

import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;


/**
 * @Description: 发起http请求工具类
 * @Author: beijing.lv@hand-china.com
 * @Date: 2020/9/11
 */
public class HttpRequestUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestUtils.class);

    /**
     * @Description: 发送Get请求无参或者"/"拼接的参数
     * @Param: @param reqUrl 请求URL
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String sendGet(String reqUrl) {
        StringBuffer buffer = new StringBuffer();
        URL url = null;
        HttpURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            url = new URL(reqUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            // 将返回的输入流转换成字符串
            if (httpUrlConn.getResponseCode() != 200) {
                inputStream = httpUrlConn.getErrorStream();
                logger.error("-" + httpUrlConn.getHeaderField("errorMsg"));
            } else {
                inputStream = httpUrlConn.getInputStream();
            }

            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.getStackTrace();
            return e.getMessage();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    bufferedReader = null;
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                    inputStreamReader = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
                if (httpUrlConn != null) {
                    httpUrlConn.disconnect();
                }
            } catch (IOException e) {
                logger.error("IOException:{}", e);
            }

        }
        return buffer.toString();
    }

    /**
     * @param params 参数
     * @Description: 发起http GEt请求,并返回响应字符串,含参数
     * @Param: @param reqUrl 请求url
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String sendGetParameter(String reqUrl, Map<String, String> params) {
        StringBuffer buffer = new StringBuffer();
        URL url = null;
        HttpURLConnection httpUrlConn = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            url = new URL(reqUrl);
            httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(false);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);

            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();
            if (!Objects.isNull(params)) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    httpUrlConn.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // 将返回的输入流转换成字符串
            inputStream = httpUrlConn.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            e.getStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                    bufferedReader = null;
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                    inputStreamReader = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
                if (httpUrlConn != null) {
                    httpUrlConn.disconnect();
                }
            } catch (IOException e) {
                logger.error("IOException:{}", e);
            }

        }
        return buffer.toString();
    }

    /**
     * @param map
     * @Description: 发起http GEt请求,并返回响应字符串,含参数
     * @Param: @param urlStr 请求url
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String sendGetParameter1(String urlStr, Map<String, String> map) {
        CloseableHttpClient client = null;
        HttpGet get = null;
        CloseableHttpResponse response = null;
        String result = null;
        String url = null;
        try {
            RequestConfig config = RequestConfig.custom().setSocketTimeout(30000).setConnectTimeout(30000).build();
            client = HttpClients.custom().setDefaultRequestConfig(config).build();
            if (!ObjectUtils.isEmpty(map)) {
                String linkStringByGet = createLinkStringByGet(map);
                url = urlStr + "?" + linkStringByGet;
            } else {
                url = urlStr;
            }

            get = new HttpGet(url);
            get.setHeader("Content-Type", "multipart/form-data;boundary=----footfoodapplicationrequestnetwork");
            get.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            get.setHeader("Accept", "*/*");
            get.setHeader("Range", "bytes=" + "");
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("调用外部系统失败！");
            }
            result = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            logger.error("SupplierUtil.httpClientGet has exception ：{}", e);
        } finally {
            try {
                client.close();
                response.close();
                try {
                    get.clone();
                } catch (CloneNotSupportedException e) {
                    logger.error("SupplierUtil.httpClientGet has exception ：{}", e);
                }
            } catch (IOException e) {
                logger.error("SupplierUtil.httpClientGet has exception ：{}", e);
            }
        }
        return result;
    }


    /**
     * @Description: 创建字符串为Get请求
     * @Param: @param params
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String createLinkStringByGet(Map<String, String> params) throws UnsupportedEncodingException {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            value = URLEncoder.encode(value, "UTF-8");
            if (i == keys.size() - 1) {
                //拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }


    /**
     * @param paramsMap  请求参数
     * @param headersMap 请求头
     * @Description: 发起http GEt请求,带入参和请求头
     * @Param: @param url 请求url
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String sendGetParameterAndHeader(String url, Map<String, String> paramsMap, Map<String, String> headersMap) throws HttpException, IOException {
        HttpClient httpClient = new HttpClient();// 客户端实例化
        if (paramsMap != null && !paramsMap.isEmpty()) {
            url = url.contains("?") ? url : (url + "?");
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                url += entry.getKey() + "=" + String.valueOf(entry.getValue()) + "&";
            }
            url = url.substring(0, (url.length() - 1));
        }

        logger.info("url ======== " + url);
        GetMethod getMethod = new GetMethod();
        getMethod.setURI(new URI(url, true, "UTF-8"));
        if (headersMap != null && !headersMap.isEmpty()) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                logger.info("请求头====" + entry.getKey() + ": " + (String.valueOf(entry.getValue())));
                getMethod.setRequestHeader(entry.getKey(), (String.valueOf(entry.getValue())));
            }
        }
        httpClient.executeMethod(getMethod);
        InputStream soapResponseStream = getMethod.getResponseBodyAsStream();// 获取返回的流
        byte[] datas = null;
        try {
            datas = readInputStream(soapResponseStream);// 从输入流中读取数据
        } catch (Exception e) {
            logger.info("Exception e:{}", e);
        }
        String result = new String(datas, "UTF-8");// 将二进制流转为String
        logger.info("返回结果===== " + result);
        return result;
    }

    /**
     * @param params 请求参数
     * @Description: 发送post请求
     * @Param: @param url 请求url
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String sendPost(String url, Map<String, String> params) throws Exception {
        org.apache.http.client.HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(JSON.toJSONString(params), "utf-8"));
            post.setHeader("Content-type", "application/json;charset=utf-8");
            HttpResponse response = httpClient.execute(post);
            Integer status = response.getStatusLine().getStatusCode();
            String result = "";
            if (status.equals(200)) {
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                logger.info("调用结果为： " + result);
            }
            return result;
        } finally {
            ((CloseableHttpClient) httpClient).close();
        }
    }

    /**
     * @param jsonString 请求参数
     * @Description: 发送post请求
     * @Param: @param url 请求url
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String sendPost1(String url, String jsonString) {
        String content = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(url);
            post.addHeader("Content-Type", "application/json;charset=utf-8");
            StringEntity postingString = new StringEntity(jsonString,
                    "utf-8");
            post.setEntity(postingString);
            HttpResponse response = httpClient.execute(post);
            logger.error("状态码：" + response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                logger.error("调用外部系统失败！");
                return content;
            }
            content = EntityUtils.toString(response.getEntity(), "UTF-8");
            logger.info("外部系统返回数据" + content);
            return content;
        } catch (SocketTimeoutException e) {
            logger.error("调用接口超时,超时时间:" + 300
                    + "秒,url:" + url + ",参数：" + jsonString, e);
            return content;
        } catch (Exception e) {
            logger.error("调用接口失败,url:" + url + ",参数：" + jsonString, e);
            return content;
        }
    }

    /**
     * @param params        参数
     * @param authorization 请求头参数
     * @Description: 发送post请求带参数和请求头
     * @Param: @param requestUrl 请求地址
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String sendPostParameterAndHeader(String requestUrl, String params, Map<String, String> authorization) throws IOException {

        byte[] requestBytes = params.getBytes("utf-8"); // 将参数转为二进制流
        HttpClient httpClient = new HttpClient();// 客户端实例化

        PostMethod postMethod = new PostMethod(requestUrl);
        // 设置请求头Authorization
        if (authorization != null && !authorization.isEmpty()) {
            for (Map.Entry<String, String> entry : authorization.entrySet()) {
                postMethod.setRequestHeader(entry.getKey(), (String.valueOf(entry.getValue())));
            }
        }

        // 设置请求头 Content-Type
        postMethod.setRequestHeader("Content-Type", "application/json");
        InputStream inputStream = new ByteArrayInputStream(requestBytes, 0, requestBytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, requestBytes.length,
                "application/json; charset=utf-8"); // 请求体
        postMethod.setRequestEntity(requestEntity);

        httpClient.executeMethod(postMethod);// 执行请求
        InputStream soapResponseStream = postMethod.getResponseBodyAsStream();// 获取返回的流
        byte[] datas = null;
        try {
            datas = readInputStream(soapResponseStream);// 从输入流中读取数据
        } catch (Exception e) {
            logger.info("Exception e:{}", e);
        }
        String result = new String(datas, "UTF-8");// 将二进制流转为String
        return result;

    }

    /**
     * @param bodyParams    参数
     * @param authorization 请求头参数 格式为 x-www-form-urlencoded
     * @Description: 发送post请求带参数和请求头
     * @Param: @param requestUrl 请求地址
     * @return: java.lang.String
     * @Author: beijing.lv@hand-china.com
     * @Date: 2020/9/11
     */
    public static String sendPostParameterAndHeader1(String requestUrl, String bodyParams, Map<String, String> authorization)
            throws IOException {

        HttpClient httpClient = new HttpClient();// 客户端实例化
        PostMethod postMethod = new PostMethod(requestUrl);
        // 设置请求头Authorization
        if (authorization != null && !authorization.isEmpty()) {
            for (Map.Entry<String, String> entry : authorization.entrySet()) {
                postMethod.setRequestHeader(entry.getKey(), (String.valueOf(entry.getValue())));
            }
        }

        // 设置请求头 Content-Type
        postMethod.setRequestHeader("Content-Type", "application/json");
        logger.info("HTTP_bodyParams :" + bodyParams);
        if (bodyParams != null) {
            byte[] requestBytes = bodyParams.getBytes("utf-8"); // 将参数转为二进制流
            InputStream inputStream = new ByteArrayInputStream(requestBytes, 0, requestBytes.length);
            RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, requestBytes.length,
                    "application/json; charset=utf-8"); // 请求体
            postMethod.setRequestEntity(requestEntity);

        }

        logger.info("HTTP_bodyParams != null:" + (bodyParams != null));
        if (bodyParams != null && !"".equals(bodyParams)) {
            postMethod.setRequestBody(bodyParams);
            /*
             * NameValuePair message = new NameValuePair("json", bodyParams);
             * postMethod.setRequestBody(new NameValuePair[] { message });
             */
        }
        httpClient.executeMethod(postMethod);// 执行请求
        InputStream soapResponseStream = postMethod.getResponseBodyAsStream();// 获取返回的流
        byte[] datas = null;
        try {
            datas = readInputStream(soapResponseStream);// 从输入流中读取数据
        } catch (Exception e) {
            logger.info("Exception e:{}", e);
        }
        String result = new String(datas, "UTF-8");// 将二进制流转为String
        logger.info("HTTP_result:" + result);
        return result;

    }

    /**
    * @Description:  从输入流中读取数据
    * @Param: @param inStream
    * @return: byte[]
    * @Author: beijing.lv@hand-china.com
    * @Date: 2020/9/11
    */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

}
