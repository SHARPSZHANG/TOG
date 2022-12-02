/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ruoyi.system.api;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 提供的默认的标注返回api
 * @author xuexiang
 * @since 2018/5/22 下午4:22
 */
public class ApiResult<T> {

    @JsonProperty(value = "code")
    private int code = 0;
    @JsonProperty(value = "msg")
    private String msg = "";
    @JsonProperty(value = "data")
    private T data;

    @JsonIgnore
    public int getCode() {
        return code;
    }

    @JsonIgnore
    public ApiResult setCode(int code) {
        this.code = code;
        return this;
    }

    @JsonIgnore
    public String getMsg() {
        return msg;
    }

    @JsonIgnore
    public ApiResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    @JsonIgnore
    public T getData() {
        return data;
    }

    @JsonIgnore
    public ApiResult setData(T data) {
        this.data = data;
        return this;
    }

    @JsonIgnore
    public ApiResult setError(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
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

    public static ApiResult success() {
        ApiResult apiResult = new ApiResult();
        return apiResult;
    }


}
