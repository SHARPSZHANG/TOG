package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.switchbutton.SwitchButton;


public class RecruitmentActivity extends BaseActivity {

    private TitleBar titleBar;
    private TextView clubName;
    private TextView clubDetails;
    private SwitchButton action;
    private SwitchButton notice;
    private SwitchButton activity;

    private String userId;
    private String token;
    private Club club;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
        initData();
        initView();
    }

    public void initView() {
        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);
        action = findViewById(R.id.sb_action);
        notice = findViewById(R.id.sb_notice);
        activity = findViewById(R.id.sb_activity);
        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        })
        .addAction(new TitleBar.TextAction("保存") {
            @Override
            public void performAction(View view) {
                if(action.isChecked()) {
                    XHttp.post("/system/club/mobile/enRecruit")
                            .params("clubId", club.getId())
                            .params("notice", notice.isChecked())
                            .params("activity", activity.isChecked())
                            .execute(new SimpleCallBack<Boolean>() {
                                @Override
                                public void onSuccess(Boolean aBoolean) {
                                    if (aBoolean) {
                                        XToastUtils.toast("发布成功！");
                                        finish();
                                    } else {
                                        XToastUtils.toast("发布失败！");
                                    }
                                }

                                @Override
                                public void onError(ApiException e) {
                                    XToastUtils.toast("发布失败！");
                                }
                            });
                }
            }
        });

    }

    public void initData() {
        XHttp.get("/system/club/mobile/findClubByUserId")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .params("userId", userId)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Club>() {
                    @Override
                    public void onSuccess(Club response) throws Throwable {
                        if (response != null) {
                            club = response;
                            clubName.setText(response.getClubName());
                            clubDetails.setText("");
                        } else {
                            setContentView(R.layout.empty_activity);
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        setContentView(R.layout.empty_activity);
                    }
                });
    }

}