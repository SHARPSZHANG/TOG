package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;

import java.util.Date;

/**
 * ”发布公告“页面
 */
public class AddClubActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private TextView clubName;
    private TextView clubDesc;
    private TextView clubDetails;
    private RoundButton release;

    private Club club;

    private String userId;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_club);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
        initView();
    }

    public void initView() {
        clubName = findViewById(R.id.club_name);
        clubDesc = findViewById(R.id.club_desc);
        release = findViewById(R.id.release);
        clubDetails = findViewById(R.id.club_detail);
        release.setOnClickListener(this);

        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.release:
                club = new Club();
                club.setClubName(clubName.getText().toString());
                club.setClubDescription(clubDesc.getText().toString());
                club.setClubDetails(clubDetails.getText().toString());
                club.setGmtCreate(new Date().toString());
                club.setIsDelete(0);
                XHttp.post("/prod-api/system/club")
                        .syncRequest(false)
                        .onMainThread(true)
                        .timeOut(1000)
                        .upJson(JSONObject.toJSONString(club))
                        .params("userId", userId)
                        .headers("Authorization", "Bearer " + token)
                        .timeStamp(true)
                        .execute(new SimpleCallBack<Boolean>() {
                            @Override
                            public void onSuccess(Boolean response) throws Throwable {
                                if (response) {
                                    XToastUtils.success("创建成功！");
                                    finish();
                                } else {
                                    XToastUtils.error("创建失败！");
                                }
                            }
                            @Override
                            public void onError(ApiException e) {
                                XToastUtils.error("创建失败！");
                            }
                        });
                break;
            default:
                break;
        }
    }
}