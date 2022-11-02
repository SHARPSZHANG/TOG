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
public class ActivityContentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_content);
        // 头部导航栏
        TitleBar titleBar = findViewById(R.id.title_bar);
        // 返回
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

        // 当前用户为该社团团长
        if ( true ) {
            // 删除按钮
            titleBar.addAction(new TitleBar.TextAction("删除") {
                @Override
                public void performAction(View view) {
                    // 对话框
                    new MaterialDialog.Builder(view.getContext())
                            .content("确认删除此活动？")
                            .negativeText("取消")
                            .positiveText("确定")
                            .onPositive((dialog1, which1) -> {
                                XToastUtils.success("删除成功");
                            })
                            .show();
                }
            });
        }
    }


}