package com.example.demo.common;

import java.io.Serializable;

public class ResultObject<T> implements Serializable {

    private long code;//状态码
    private Boolean success;//是否成功
    private String msg;//描述
    private Long timeStamp;//时间戳
    private T data;//返回数据体
    private T otherMsg;//获取当前登录人的id


    public T getOtherMsg() {
        return otherMsg;
    }

    public void setOtherMsg(T otherMsg) {
        this.otherMsg = otherMsg;
    }

    public ResultObject(){}

    public ResultObject(Long code, Boolean success, String msg, Long timeStamp, T data) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.timeStamp = timeStamp;
        this.data = data;
    }

    public ResultObject(long code, Boolean success, String msg, Long timeStamp, T data, T otherMsg) {
        this.code = code;
        this.success = success;
        this.msg = msg;
        this.timeStamp = timeStamp;
        this.data = data;
        this.otherMsg = otherMsg;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
