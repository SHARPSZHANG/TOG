package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.Bean.ClubMember;
import com.sharpszhang.tog.Bean.ClubMemberVo;
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


public class ApplyClubActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private MaterialEditText speciality;
    private MaterialEditText hobby;
    private MaterialEditText phone;
    private MultiLineEditText noticeContent;
    private TextView clubName;
    private TextView clubDetails;
    private Button release;

    private Club club;

    private String userId;
    private String token;
    private String clubId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
        clubId = intent.getStringExtra("clubId");
        initData();
        initView();
    }

    public void initView() {
        speciality = findViewById(R.id.speciality);
        hobby = findViewById(R.id.hobby);
        phone = findViewById(R.id.phone);
        release = findViewById(R.id.release);
        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);
        release.setOnClickListener(this);
        noticeContent = findViewById(R.id.notice_content);

        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

    }

    public void initData() {
        XHttp.get("/prod-api/system/mobile/club/" + clubId)
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
                ClubMember member = new ClubMember();
                member.setClubId(Long.valueOf(clubId));
                member.setUserId(Long.valueOf(userId));
                member.setGmtCreate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                member.setPosition("成员");
                member.setQqNumber(phone.getEditValue());
                member.setState(2);
                member.setHobby(hobby.getEditValue());
                member.setSpeciality(speciality.getEditValue());
                member.setApply(noticeContent.getContentText());
                XHttp.post("/prod-api/system/mobile/member/add")
                        .upJson(JSONObject.toJSONString(member))
                        .headers("Authorization", "Bearer " + token)
                        .execute(new SimpleCallBack<Boolean>() {
                            @Override
                            public void onSuccess(Boolean aBoolean) {
                                if (aBoolean) {
                                    XToastUtils.toast("申请成功！");
                                    finish();
                                } else {
                                    XToastUtils.toast("申请失败！");
                                }
                            }

                            @Override
                            public void onError(ApiException e) {
                                XToastUtils.toast("申请失败！");
                            }
                        });
                break;
            default:
                break;
        }
    }
}