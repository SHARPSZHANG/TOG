package com.sharpszhang.tog.activity;

import android.os.Bundle;
import android.view.View;

import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

/**
 * ”活动详情“页面
 */
public class ClubContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);
        // 头部导航栏
        TitleBar titleBar = findViewById(R.id.title_bar);
        // 返回
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        })
            // 保存编辑
         .addAction(new TitleBar.TextAction("保存") {
                @Override
                public void performAction(View view) {
                    XToastUtils.success("保存成功");
                }
         });
    }
}
