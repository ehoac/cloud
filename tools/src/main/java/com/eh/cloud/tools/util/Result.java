package com.eh.cloud.tools.util;

import com.alibaba.fastjson.JSONObject;
import com.eh.cloud.tools.constants.ResponseConstant;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

/**
 * @author caopeihe
 * @Type Result.java
 * @Desc
 * @date 2020/2/28 13:43
 */
@Data
@JsonSerialize
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 4893280118017319089L;
    // 编码
    private int code;
    // 信息
    private String message;
    // 数据
    private T result;

    public Result(int code, String message) {
        this(code, message, null);
    }

    Result(int code, String message, T result) {
        super();
        this.code(code).message(message).result(result);
    }

    /**
     * 返回成功消息
     * @return
     */
    public static <E> Result<E> success(E o) {
        return new Result<>(ResponseConstant.SUCCESS_CODE, ResponseConstant.SUCCESS_MESSAGE, o);
    }

    /**
     * 返回系统异常消息
     * @return
     */
    public static <E> Result<E> error() {
        return new Result<>(ResponseConstant.ERROR_CODE, ResponseConstant.ERROR_MESSAGE);
    }

    public static <E> Result<E> info(int code, String message) {
        return new Result<>(code, message);
    }

    public static <E> Result<E> info(int code, String message, E result) {
        return new Result<>(code, message, result);
    }

    private Result<T> code(int code) {
        this.setCode(code);
        return this;
    }

    private Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    private Result<T> result(T result) {
        this.setResult(result);
        return this;
    }

    public String toJsonString() {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("message", message);
        json.put("result", result);
        return json.toString();
    }
}
