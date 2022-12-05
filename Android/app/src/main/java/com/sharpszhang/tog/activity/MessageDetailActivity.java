package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sharpszhang.tog.Bean.ClubMember;
import com.sharpszhang.tog.Bean.ClubMemberVo;
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
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageDetailActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private TextView username;
    private MaterialEditText speciality;
    private MaterialEditText hobby;
    private MaterialEditText number;
    private MultiLineEditText content;
    private RoundButton refuse;
    private RoundButton agree;

    private String messageId;
    private String userId;
    private String sendId;
    private String token;

    private ClubMemberVo member;


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
        hobby = findViewById(R.id.hobby);
        speciality = findViewById(R.id.speciality);
        number = findViewById(R.id.phone);
        refuse = findViewById(R.id.refuse);
        agree = findViewById(R.id.agree);
        refuse.setOnClickListener(this);
        agree.setOnClickListener(this);
        content.setVisibility(View.GONE);
        hobby.setVisibility(View.GONE);
        speciality.setVisibility(View.GONE);
        number.setVisibility(View.GONE);



        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

    }

    public void initData() {
        XHttp.get("/prod-api/system/mobile/member/")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .params("memberId", sendId)
                .timeStamp(true)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<ClubMemberVo>() {
                    @Override
                    public void onSuccess(ClubMemberVo response) throws Throwable {
                        if (response != null) {
                            member = response;
                            username.setText(member.getUserName());
                            content.setContentText(member.getApply());
                            hobby.setText(member.getHobby());
                            speciality.setText(member.getSpeciality());
                            number.setText(member.getQqNumber());
                            content.setVisibility(View.GONE);
                            if(member.getState() != 0) {
                                agree.setVisibility(View.GONE);
                                refuse.setVisibility(View.GONE);
                            }
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
        XHttp.get("/prod-api/system/mobile/member/" + member.getId() + "/pass")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
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