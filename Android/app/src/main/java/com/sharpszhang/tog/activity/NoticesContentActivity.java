package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sharpszhang.tog.Bean.NoticeVo;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

/**
 * ”公告详情“页面
 */
public class NoticesContentActivity extends BaseActivity {

    private NoticeVo notice;

    private TextView applicationUser;

    private TextView applicationTime;

    private TextView noticeTitle;
    private TextView noticeContent;
    private TextView clubName;
    private TextView clubDetails;

    private TitleBar titleBar;

    private String userId;
    private String token;
    private String noticeId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notices_content);
        // 头部导航栏
        titleBar = findViewById(R.id.title_bar);
        // 返回
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

        applicationUser = findViewById(R.id.application_username);
        applicationTime = findViewById(R.id.application_time);
        noticeTitle = findViewById(R.id.notice_title);
        noticeContent = findViewById(R.id.notice_content);
        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
        noticeId = intent.getStringExtra("noticeId");
        getNoticeData();
        getPremission();

    }

    private void getNoticeData () {
        XHttp.get("/prod-api/system/mobile/notice/" + noticeId)
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .headers("Authorization", "Bearer " + token)
                .timeStamp(true)
                .execute(new SimpleCallBack<NoticeVo>() {
                    @Override
                    public void onSuccess(NoticeVo response) throws Throwable {
                        notice = response;
                        noticeTitle.setText(notice.getTitle());
                        noticeContent.setText(notice.getContent());
                        applicationUser.setText(notice.getUserName());
                        applicationTime.setText(notice.getGmtCreate());
                        clubName.setText(notice.getClubName());
                        clubDetails.setText("");
                    }
                    @Override
                    public void onError(ApiException e) {
                        setContentView(R.layout.empty_activity);
                    }
                });
    }
    private void deleteNoticeById() {
        XHttp.delete("/prod-api/system/mobile/notice/" + noticeId)
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean response) throws Throwable {
                        if(response) {
                            XToastUtils.success("删除成功");
                            finish();
                        } else {
                            XToastUtils.error("删除失败");
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        XToastUtils.error("删除失败");
                    }
                });
    }
    public void getPremission() {
        XHttp.get("/prod-api/system/mobile/notice/getPermissionByUserId")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .params("userId", userId)
                .params("noticeId", noticeId)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean response) throws Throwable {
                        if (response) {
                            // 删除按钮
                            titleBar.addAction(new TitleBar.TextAction("删除") {
                                @Override
                                public void performAction(View view) {
                                    // 对话框
                                    new MaterialDialog.Builder(view.getContext())
                                            .content("确认删除此公告？")
                                            .negativeText("取消")
                                            .positiveText("确定")
                                            .onPositive((dialog1, which1) -> {
                                                deleteNoticeById();
                                            })
                                            .show();
                                }
                            });
                        } else {

                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                    }
                });
    }


}