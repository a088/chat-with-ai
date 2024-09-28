package com.coffee.common;

import lombok.Data;

@Data
public class AjaxResult<T> {

    private  int code;

    private String msg;

    private T data;

    public static <T>  AjaxResult success(String msg,T data){
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T>  AjaxResult success(String msg){
        AjaxResult result = new AjaxResult();
        result.setCode(200);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
    public static <T> AjaxResult faile(String msg){
        AjaxResult result = new AjaxResult();
        result.setCode(500);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }
}
