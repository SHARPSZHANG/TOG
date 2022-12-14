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
    private TextView speciality;
    private TextView hobby;
    private TextView number;
    private TextView content;
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
        change();
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



        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

    }

    public void initData() {
        XHttp.get("/prod-api/system/mobile/member")
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
                            content.setText(member.getApply());
                            hobby.setText(member.getHobby());
                            speciality.setText(member.getSpeciality());
                            number.setText(member.getQqNumber());
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
                            XToastUtils.error("?????????");
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        XToastUtils.error("?????????");
                    }
                });
    }

    public void change() {
        XHttp.get("/prod-api/system/mobile/message/" + messageId)
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean response) throws Throwable {
                    }
                    @Override
                    public void onError(ApiException e) {
                    }
                });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.refuse:
                finish();
                break;
            case R.id.agree:
                doAgree();
                break;
            default:
                break;
        }
    }
}