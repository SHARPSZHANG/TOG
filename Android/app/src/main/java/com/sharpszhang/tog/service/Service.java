package com.sharpszhang.tog.service;

import com.alibaba.fastjson.JSON;
import com.sharpszhang.tog.utils.MyThread;

import java.io.UnsupportedEncodingException;

public class Service {
    public static JSON signIn(String data) throws UnsupportedEncodingException {
        MyThread thread = new MyThread("http://10.0.2.2:8080/appserver/signIn",data,"POST");
        return getJsonObject(thread);
    }
    public static JSON signUp(String data) throws UnsupportedEncodingException {
        MyThread thread = new MyThread("http://10.0.2.2:8080/appserver/signUp",data, "POST");
        return getJsonObject(thread);
    }
    public static JSON releasePost(String data) throws UnsupportedEncodingException {
        MyThread thread = new MyThread("http://10.0.2.2:8080/appserver/add",data, "POST");
        return getJsonObject(thread);
    }
    public static JSON selectPosts(String data){
        //MyThread thread = new MyThread("https://console-mock.apipost.cn/mock/16ccd0dc-21c3-4728-faf1-db3b266e6f8d/?apipost_id=1e5880",data, "get");
        MyThread thread = new MyThread("http://119.91.193.79:8080/demo/list",data, "GET");
        return getJsonObject(thread);
    }
    public static JSON selectAllPosts(String data){
        MyThread thread = new MyThread("http://10.0.2.2:8080/appserver/selectAll",data, "POST");
        return getJsonObject(thread);
    }
    public static JSON deletePost(String data){
        MyThread thread = new MyThread("http://10.0.2.2:8080/appserver/delete",data, "POST");
        return getJsonObject(thread);
    }
    private static JSON getJsonObject(MyThread thread) {
        try {
            thread.start();
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return thread.getResult();
    }
}
