package com.jianjian.crud.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author：简简
 * @createTime：[2022/6/5 16:43]
 **/
//通用的用来返回JSON数据的类

public class Msg {

    private int code;//状态码 100成功 200失败
    private String msg;//用户返回给浏览器的数据
    private Map<String, Object> data = new HashMap<>();//用户返回给浏览器的数据

    public static Msg success() {
        Msg result = new Msg();
        result.setCode(100);
        result.setMsg("处理成功");
        return result;
    }

    public static Msg fail() {
        Msg result = new Msg();
        result.setCode(200);
        result.setMsg("处理失败");
        return result;
    }

    //链式编程
    public Msg add(String key, Object value) {
        this.getData().put(key, value);
        return this;
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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}