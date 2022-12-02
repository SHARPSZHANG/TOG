package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sharpszhang.tog.Bean.ActivityVo;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.textview.autofit.AutoFitTextView;

/**
 * ”活动详情“页面
 */
public class ActivityContentActivity extends BaseActivity {

    private ActivityVo activity;

    private TextView title;
    private TextView activityTime;
    private TextView username;
    private TextView createTime;
    private TextView clubName;
    private TextView clubDetails;
    private AutoFitTextView content;
    private String activityId;
    private String userId;
    private String token;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_content);
        title = findViewById(R.id.activity_title);
        content = findViewById(R.id.activity_content);
        username = findViewById(R.id.application_username);
        createTime = findViewById(R.id.date_time);
        activityTime = findViewById(R.id.activity_time);
        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);

        // 头部导航栏
        titleBar = findViewById(R.id.title_bar);
        // 返回
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

        Intent intent = getIntent();
        activityId = intent.getStringExtra("activityId");
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");

        getActivityData();
        getPremission();

    }

    private void getActivityData () {
        XHttp.get("/prod-api/system/mobile/activity/" + activityId + "/vo")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<ActivityVo>() {
                    @Override
                    public void onSuccess(ActivityVo response) throws Throwable {
                        activity = response;
                        title.setText(activity.getTitle());
                        content.setText(activity.getContent());
                        username.setText(activity.getUserName());
                        createTime.setText(activity.getGmtCreate());
                        activityTime.setText(activity.getStartTime());
                        clubName.setText(activity.getClubName());
                        clubDetails.setText("");
                    }
                    @Override
                    public void onError(ApiException e) {
                        setContentView(R.layout.empty_activity);
                    }
                });
    }

    private void deleteActivityById() {
        XHttp.delete("/prod-api/system/mobile/activity/" + activityId)
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
        XHttp.get("/prod-api/system/mobile/activity/getPermissionByUserId")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .headers("Authorization", "Bearer " + token)
                .timeStamp(true)
                .params("userId", userId)
                .params("activityId", activityId)
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
                                                deleteActivityById();
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