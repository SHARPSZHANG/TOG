package com.sharpszhang.tog.Bean;

import android.os.Bundle;
import android.os.PersistableBundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.xuexiang.xui.XUI;
import com.xuexiang.xui.utils.StatusBarUtils;

import butterknife.ButterKnife;


public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        XUI.initTheme(this);
        super.onCreate(savedInstanceState, persistentState);
        //设置沉浸式状态栏
        StatusBarUtils.translucent(this);


    }


}
