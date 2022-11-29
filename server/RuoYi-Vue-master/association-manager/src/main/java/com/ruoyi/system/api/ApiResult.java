package com.ruoyi.system.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResult<T> {

    @JsonProperty(value = "Code")
    private int Code = 0;
    @JsonProperty(value = "Msg")
    private String Msg = "";
    @JsonProperty(value = "Data")
    private T Data;

    @JsonIgnore
    public int getCode() {
        return Code;
    }

    @JsonIgnore
    public ApiResult setCode(int code) {
        Code = code;
        return this;
    }

    @JsonIgnore
    public String getMsg() {
        return Msg;
    }

    @JsonIgnore
    public ApiResult setmsg(String msg) {
        Msg = msg;
        return this;
    }

    @JsonIgnore
    public T getData() {
        return Data;
    }

    @JsonIgnore
    public ApiResult setData(T data) {
        Data = data;
        return this;
    }

    @JsonIgnore
    public ApiResult setError(int code, String msg) {
        Code = code;
        Msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "Code='" + Code + '\'' +
                ", Msg='" + Msg + '\'' +
                ", Data=" + Data +
                '}';
    }

    /**
     * 获取出错返回
     *
     * @param code
     * @param msg
     * @return
     */
    public static ApiResult error(int code, String msg) {
        ApiResult apiResult = new ApiResult();
        apiResult.setError(code, msg);
        return apiResult;
    }
}
