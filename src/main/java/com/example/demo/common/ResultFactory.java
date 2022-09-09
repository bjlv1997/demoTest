package com.example.demo.common;

import java.util.Date;

public class ResultFactory {

    /**
     * 返回成功
     *
     * @param data 返回数据
     * @return data为null的ResultObject
     */
    public static <T> ResultObject<T> success(T data) {
        return new ResultObject<>(Long.valueOf(200), true, "", new Date().getTime(), data);
    }

    /**
     * 返回成功的消息
     *
     * @param msg  消息提示
     * @param data 数据
     * @param <T>  data泛型
     * @return 成功的data返回
     */
    public static <T> ResultObject<T> success(String msg, T data) {
        return new ResultObject<>(Long.valueOf(200), true, msg, new Date().getTime(), data);
    }

    /**
     * 返回异常
     *
     * @param code 错误码
     * @param msg  错误消息
     * @param data 返回数据
     * @return data为null的ResultObject
     */
    public static <T> ResultObject<T> error(long code, String msg, T data) {
        return new ResultObject<>(code, false, msg, new Date().getTime(), data);
    }

    /**
     * 返回异常2
     *
     * @param code 错误码
     * @param msg  错误消息
     * @return data为null的ResultObject
     */
    public static <T> ResultObject<T> error(long code, String msg) {
        return new ResultObject<>(code, false, msg, new Date().getTime(), null);
    }
    public static <T> ResultObject<T> error(String msg  ) {
        return new ResultObject<T>(Long.valueOf(400), false, msg, new Date().getTime(), null);
    }
    public static <T> ResultObject<T> errorBat(String msg,T data  ) {
        return new ResultObject<T>(Long.valueOf(400), false, msg, new Date().getTime(), data);
    }

}
