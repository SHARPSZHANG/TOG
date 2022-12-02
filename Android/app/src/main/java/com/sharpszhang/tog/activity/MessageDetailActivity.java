package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sharpszhang.tog.Bean.ClubMember;
import com.sharpszhang.tog.Bean.TogMessage;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;

import java.util.Date;

public class MessageDetailActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private TextView username;
    private MultiLineEditText content;
    private RoundButton refuse;
    private RoundButton agree;

    private String messageId;
    private String userId;
    private String sendId;
    private String token;

    private TogMessage message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        sendId = intent.getStringExtra("sendId");
        messageId = intent.getStringExtra("messageId");
        token = intent.getStringExtra("token");
        initData();
        initView();
    }

    public void initView() {
        username = findViewById(R.id.username);
        content = findViewById(R.id.message_content);
        refuse = findViewById(R.id.refuse);
        agree = findViewById(R.id.agree);
        refuse.setOnClickListener(this);
        agree.setOnClickListener(this);



        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

    }

    public void initData() {
        XHttp.get("/prod-api/system/mobile/message/" + messageId)
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<TogMessage>() {
                    @Override
                    public void onSuccess(TogMessage response) throws Throwable {
                        if (response != null) {
                            message = response;
                            username.setText(response.getTitle());
                            content.setContentText(response.getContent());
                            content.setVisibility(View.GONE);
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

    public void doAgree() {
        ClubMember clubMember = new ClubMember();
        clubMember.setPosition("成员");
        clubMember.setUserId(Long.valueOf(sendId));
        clubMember.setGmtCreate(new Date().toString());
        XHttp.post("/prod-api/system/mobile/")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .upJson(JSONObject.toJSONString(clubMember))
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean response) throws Throwable {
                        if (response) {
                           finish();
                        } else {
                            XToastUtils.error("出错了");
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        XToastUtils.error("出错了");
                    }
                });
    }

    public void doRefuse() {
//        XHttp.get("/system/tog/message/" + messageId)
//                .syncRequest(false)
//                .onMainThread(true)
//                .timeOut(1000)
//                .timeStamp(true)
//                .headers("token", token)
//                .execute(new SimpleCallBack<Activity>() {
//                    @Override
//                    public void onSuccess(Activity response) throws Throwable {
//                        if (response != null) {
//                            username.setText(response.getTitle());
//                            content.setContentText(response.getDescription());
//                        } else {
//                            setContentView(R.layout.empty_activity);
//                        }
//                    }
//                    @Override
//                    public void onError(ApiException e) {
//                        setContentView(R.layout.empty_activity);
//                    }
//                });
        finish();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refuse:
                doRefuse();
                break;
            case R.id.agree:
                doAgree();
                break;
            default:
                break;
        }
    }
}