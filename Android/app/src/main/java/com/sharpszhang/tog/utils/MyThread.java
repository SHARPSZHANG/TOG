package com.sharpszhang.tog.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyThread extends Thread
{
    private JSON json;
    private String path;
    private String data;
    private String requestMethod;

    public MyThread(String path, String data, String requestMethod) {
        this.path = path;
        this.data = data;
        this.requestMethod = requestMethod;
    }

    @Override
    public void run()
    {
        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(800000);//设置连接超时时间
            httpURLConnection.setReadTimeout(800000);//设置读取超时时间
            httpURLConnection.setRequestMethod("GET");//设置请求方法,post
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//设置响应类型
            httpURLConnection.setRequestProperty("Content-Length", data.length() + "");//设置内容长度
            // setDoOutput()为true时
            httpURLConnection.setDoOutput(false);//允许输出
            //OutputStream outputStream = httpURLConnection.getOutputStream();
            //outputStream.write(data.getBytes("utf-8"));//写入数据
            if(httpURLConnection.getResponseCode() == 200){
                //用io流与web后台进行数据交互
                InputStream is=httpURLConnection.getInputStream();
                //字节流转字符流
                BufferedReader br=new BufferedReader(new InputStreamReader(is));
                //读出每一行的数据
                String str=br.readLine();
                Object o = JSON.parse(str);
                if(o != null){
                    if(o instanceof JSONObject){
                        json = (JSONObject) o;
                    } else if(o instanceof JSONArray){
                        json = (JSONArray) o;
                    }
                }

            }
            System.out.println("aa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public JSON getResult()
    {
        return json;
    }
}
