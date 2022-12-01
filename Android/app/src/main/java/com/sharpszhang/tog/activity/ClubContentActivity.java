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
import com.xuexiang.xui.widget.edittext.MultiLineEditText;

/**
 * ”活动详情“页面
 */
public class ClubContentActivity extends BaseActivity {

    private MultiLineEditText content;

    private TextView clubName;
    private TextView clubDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_details);
        Intent intent = getIntent();
        String clubId = intent.getStringExtra("clubId");

        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);
        content = findViewById(R.id.tv_org_details);
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
                    Club club = new Club();
                    club.setId(Long.valueOf(clubId));
                    club.setClubDetails(content.getContentText());
                    XHttp.post("/api/club/saveClub")
                            .params("club", club)
                            .execute(new SimpleCallBack<Boolean>() {
                                @Override
                                public void onSuccess(Boolean aBoolean) {
                                    if (aBoolean) {
                                        XToastUtils.toast("保存成功！");
                                        finish();
                                    } else {
                                        XToastUtils.toast("保存失败！");
                                    }
                                }

                                @Override
                                public void onError(ApiException e) {
                                    XToastUtils.toast("保存失败！");
                                }
                            });
                }
         });
        getClubData(clubId);
    }

    public void getClubData(String clubId) {
        XHttp.get("/api/club/findClubById?id=" + clubId)
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .execute(new SimpleCallBack<Club>() {
                    @Override
                    public void onSuccess(Club response) throws Throwable {
                        if (response != null) {
                            clubName.setText(response.getClubName());
                            clubDetails.setText(response.getClubDescription());
                            content.setContentText(response.getClubDetails());
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
