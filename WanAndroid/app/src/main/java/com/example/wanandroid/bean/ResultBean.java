package com.example.wanandroid.bean;

import com.google.gson.annotations.SerializedName;

public class ResultBean {

    @SerializedName("errorCode")
    public int code;
    @SerializedName("errorMsg")
    public String msg;
    @SerializedName("data")
    public String data;

    public ResultBean() {
    }

    public ResultBean( String msg,int code) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
