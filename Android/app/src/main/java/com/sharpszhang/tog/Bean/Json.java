package com.sharpszhang.tog.Bean;


/**
 * @Package: com.example.bean
 * @ClassName: Json
 * @Author: SHARPSZHANG
 * @CreateTime: 2021/4/12 13:33
 * @Description:
 */

public class Json {
    private String code;
    private String message;

    public Json(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
