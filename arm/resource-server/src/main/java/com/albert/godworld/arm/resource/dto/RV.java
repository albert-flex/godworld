package com.albert.godworld.arm.resource.dto;

import lombok.Data;

@Data
public class RV <T>{

    private Boolean success;
    private Integer code;
    private String error;
    private T obj;

    private RV() {}

    public static <T> RV<T> error(Integer errorCode, String error){
        RV<T> res=new RV<>();
        res.code=errorCode;
        res.error=error;
        res.success=false;
        return res;
    }

    public static <T> RV<T> error(RVError error){
        return error(error.code,error.msg);
    }

    public static <T> RV<T> success(T obj){
        RV<T> res=new RV<>();
        res.success=true;
        res.code=0;
        res.error="";
        res.obj=obj;
        return res;
    }
}
