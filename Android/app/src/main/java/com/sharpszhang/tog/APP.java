package com.sharpszhang.tog;

import android.app.Application;

import com.xuexiang.xhttp2.XHttpSDK;

public class APP extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XHttpSDK.init(this);
        XHttpSDK.debug();
        XHttpSDK.setBaseUrl("http://139.155.244.28:80");
//        XHttpSDK.setBaseUrl("http://192.168.1.110:8080");
    }
}
