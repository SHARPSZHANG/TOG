package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.Bean.Notice;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ”发布公告“页面
 */
public class NoticeActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private MaterialEditText noticeTitle;
    private MultiLineEditText noticeContent;
    private TextView clubName;
    private TextView clubDetails;
    private RoundButton release;

    private Club club;

    private String userId;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
        initData();
        initView();
    }

    public void initView() {
        noticeTitle = findViewById(R.id.notice_title);
        noticeContent = findViewById(R.id.notice_content);
        release = findViewById(R.id.release);
        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);
        release.setOnClickListener(this);

        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

    }

    public void initData() {
        XHttp.get("/prod-api/system/mobile/club/findClubByUserId")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .params("userId", userId)
                .headers("Authorization", "Bearer " + token)
                .timeStamp(true)
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


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.release:
                Notice notice = new Notice();
                notice.setClubId(club.getId());
                notice.setTitle(noticeTitle.getText().toString());
                notice.setContent(noticeContent.getContentText().toString());
                notice.setUserId(Long.valueOf(userId));
                notice.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                notice.setIsDelete(0);
                XHttp.post("/prod-api/system/mobile/notice")
                        .upJson(JSONObject.toJSONString(notice))
                        .headers("Authorization", "Bearer " + token)
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
                break;
            default:
                break;
        }
    }
}