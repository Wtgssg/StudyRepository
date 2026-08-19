package com.success.oracle.util;


import lombok.Data;

@Data
public class Result<T> {
    private String code;
    private String msg;
    private T data;

    public static <T> Result<T> ok(String msg, T data) {
        Result<T> r = new Result<>();
        r.code = "200";
        r.msg = msg;
        r.data = data;
        return r;
    }

    public static <T> Result<T> ok(T data) {
        return ok("success", data);
    }

    public static <T> Result<T> error(String code, String msg) {
        Result<T> r = new Result<>();
        r.code = code;
        r.msg = msg;
        r.data = null;
        return r;
    }

    public static <T> Result<T> error( String msg) {
        Result<T> r = new Result<>();
        r.msg = msg;
        r.data = null;
        return r;
    }
}