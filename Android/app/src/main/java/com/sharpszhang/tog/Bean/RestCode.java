package com.sharpszhang.tog.Bean;

/**
 * @author sharpszhang
 * @Description
 * @CreateTime
 * @Version 0.1.0
 **/
public class RestCode {

    private Integer Code;

    private Object Data;

    private String Msg;

    public RestCode() {
    }

    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        Code = code;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public RestCode(Integer code, Object data, String msg) {
        Code = code;
        Data = data;
        Msg = msg;
    }
}
