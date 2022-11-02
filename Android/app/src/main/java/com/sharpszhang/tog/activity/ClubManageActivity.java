package com.sharpszhang.tog.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sharpszhang.tog.Bean.ActivityBean;
import com.sharpszhang.tog.Bean.RestCode;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.adapet.ClubAdapter;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.service.Service;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.switchbutton.SwitchButton;
import com.xuexiang.xui.widget.dialog.materialdialog.DialogAction;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class ClubManageActivity extends BaseActivity implements View.OnClickListener {
    private List<ActivityBean> clubs;

    private TitleBar titleBar;
    private TextView members;
    private TextView notices;
    private TextView activity;
    private TextView clubDetail;
    private TextView exit;
    private TextView dissolve;

    private MaterialDialog.Builder builder;
    List<String> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initView();
    }

    public void initView() {
        members = findViewById(R.id.members);
        notices = findViewById(R.id.notices);
        activity = findViewById(R.id.activitys);
        clubDetail = findViewById(R.id.club_detail);
        exit = findViewById(R.id.exit);
        dissolve = findViewById(R.id.dissolve);

        members.setOnClickListener(this);
        notices.setOnClickListener(this);
        activity.setOnClickListener(this);
        clubDetail.setOnClickListener(this);
        exit.setOnClickListener(this);
        dissolve.setOnClickListener(this);

        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

        list = new ArrayList<>(16);
        for (int i = 0; i < 20; i++) {
            list.add("user" + i);
        }
        Context context = this;


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.members: {
                List<String> userIds = new ArrayList<>(16);
                MaterialDialog builder = new MaterialDialog.Builder(this)
                        .title("移除社员")
                        .items(list)
                        .itemsCallbackMultiChoice(
                                new Integer[]{0, 1},
                                (dialog, which, text) -> {
                                    for (int i = 0; i < which.length; i ++){
                                        userIds.add(text[i].toString());
                                    }
                                    return true;
                                })
                        .positiveText("确定")
                        .negativeText("取消")
                        .positiveColor(getResources().getColor(R.color.red, this.getTheme()))
                        .onPositive((dialog, which) -> {
                            final Boolean[] delete = {false};
                            new MaterialDialog.Builder(dialog.getContext())
                                    .iconRes(R.drawable.icon_warning)
                                    .title("警告")
                                    .content("确认移除社员？")
                                    .negativeText("取消")
                                    .positiveText("确定")
                                    .onPositive((dialog1, which1) -> {
                                        delete[0] = true;
                                        XToastUtils.toast(userIds.toString());
                                    })
                                    .show();
                        })
                        .show();
            }
            break;
            case R.id.notices:
                startActivity(new Intent(this, NoticesManageActivity.class));
                break;
            case R.id.activitys:
                startActivity(new Intent(this, ActivityManageActivity.class));
                break;
            case R.id.club_detail:
                startActivity(new Intent(this, ClubContentActivity.class));
                break;
            case R.id.exit: {
                new MaterialDialog.Builder(view.getContext())
                        .iconRes(R.drawable.icon_warning)
                        .title("提示")
                        .content("确认退出社团？")
                        .negativeText("取消")
                        .positiveText("确定")
                        .onPositive((dialog, which) -> {
                            XToastUtils.success("已提交申请");
                            finish();
                        })
                        .show();
            }
            break;
            case R.id.dissolve: {
                new MaterialDialog.Builder(view.getContext())
                        .iconRes(R.drawable.icon_warning)
                        .title("提示")
                        .content("确认解散社团？")
                        .negativeText("取消")
                        .positiveText("确定")
                        .onPositive((dialog, which) -> {
                            XToastUtils.success("已提交申请");
                            finish();
                        })
                        .show();
            }
            break;
        }
    }
}