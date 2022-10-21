package com.sharpszhang.tog.activity;

import android.os.Bundle;

import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;

public class ActivityContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_content);
        TitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });
    }


}