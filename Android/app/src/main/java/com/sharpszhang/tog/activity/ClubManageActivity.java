package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sharpszhang.tog.Bean.Activity;
import com.sharpszhang.tog.Bean.ClubMemberVo;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.util.ArrayList;
import java.util.List;


public class ClubManageActivity extends BaseActivity implements View.OnClickListener {
    private List<Activity> clubs;

    private TitleBar titleBar;
    private TextView members;
    private TextView notices;
    private TextView activity;
    private TextView clubDetail;
    private TextView exit;
    private TextView dissolve;

    private String userId;
    private String clubId;
    private String token;
    private List<ClubMemberVo> memberList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        clubId = intent.getStringExtra("clubId");
        token = intent.getStringExtra("token");
        getPermission();
        initView();
    }

    public void initView() {
        members = findViewById(R.id.members);
        notices = findViewById(R.id.notices);
        activity = findViewById(R.id.activitys);
        clubDetail = findViewById(R.id.club_detail);
        exit = findViewById(R.id.exit);
        dissolve = findViewById(R.id.dissolve);

        members.setOnClickListener(this);
        notices.setOnClickListener(this);
        activity.setOnClickListener(this);
        clubDetail.setOnClickListener(this);
        exit.setOnClickListener(this);
        dissolve.setOnClickListener(this);

        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });


    }

    public void getPermission() {
        XHttp.get("/prod-api/system/mobile/member/getPermissionByUserId")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .headers("Authorization", "Bearer " + token)
                .params("userId", userId)
                .params("clubId", clubId)
                .timeStamp(true)
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean response) throws Throwable {
                        if (response) {
                            exit.setVisibility(View.GONE);
                            getMemberList();
                        } else {
                            members.setVisibility(View.GONE);
                            notices.setVisibility(View.GONE);
                            activity.setVisibility(View.GONE);
                            clubDetail.setVisibility(View.GONE);
                            dissolve.setVisibility(View.GONE);
                            exit.setVisibility(View.VISIBLE);
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        setContentView(R.layout.empty_activity);
                    }
                });
    }

    public void getMemberList() {
        XHttp.get("/prod-api/system/mobile/member/list")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .headers("Authorization", "Bearer " + token)
                .params("clubId", clubId)
                .execute(new SimpleCallBack<List<ClubMemberVo>>() {
                    @Override
                    public void onSuccess(List<ClubMemberVo> response) throws Throwable {
                        if (response != null && response.size() > 0) {
                            memberList = response;
                        } else {
                            memberList = new ArrayList<>();
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                    }
                });
    }

    public void deleteMembers(Long[] ids) {
        XHttp.delete("/prod-api/system/mobile/member/")
                .params("ids", ids)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        if (aBoolean) {
                            XToastUtils.toast("移除成功！");
                        } else {
                            XToastUtils.toast("移除失败！");
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        XToastUtils.toast("移除失败！");
                    }
                });
    }

    public void exitClub() {
        XHttp.post("/prod-api/system/mobile/member/del/club/user")
                .params("userId", userId)
                .params("clubId", clubId)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean aBoolean) {
                        if (aBoolean) {
                            XToastUtils.toast("提交成功！");
                            finish();
                        } else {
                            XToastUtils.toast("提交失败！");
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        XToastUtils.toast("提交失败！");
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.members: {
                List<Long> ids = new ArrayList<>();
                List<String> mem = new ArrayList<>();
                for (ClubMemberVo ac: memberList) {
                    mem.add(ac.getUserName());
                }
                MaterialDialog builder = new MaterialDialog.Builder(this)
                        .title("移除社员")
                        .items(mem)
                        .itemsCallbackMultiChoice(
                                new Integer[]{0, 1},
                                (dialog, which, text) -> {
                                    for (int i = 0; i < which.length; i ++){
                                        ids.add(memberList.get(i).getUserId());
                                    }
                                    return true;
                                })
                        .positiveText("确定")
                        .negativeText("取消")
                        .positiveColor(getResources().getColor(R.color.red, this.getTheme()))
                        .onPositive((dialog, which) -> {
                            final Boolean[] delete = {false};
                            new MaterialDialog.Builder(dialog.getContext())
                                    .iconRes(R.drawable.icon_warning)
                                    .title("警告")
                                    .content("确认移除社员？")
                                    .negativeText("取消")
                                    .positiveText("确定")
                                    .onPositive((dialog1, which1) -> {
                                        deleteMembers(ids.toArray(new Long[ids.size()]));
                                    })
                                    .show();
                        })
                        .show();
            }
            break;
            case R.id.notices:
                startActivity(new Intent(this, NoticesManageActivity.class)
                        .putExtra("clubId", clubId)
                        .putExtra("token", token)
                        .putExtra("userId", userId));
                break;
            case R.id.activitys:
                startActivity(new Intent(this, ActivityManageActivity.class)
                        .putExtra("clubId", clubId)
                        .putExtra("token", token)
                        .putExtra("userId", userId));
                break;
            case R.id.club_detail:
                startActivity(new Intent(this, ClubContentActivity.class)
                        .putExtra("clubId", clubId)
                        .putExtra("token", token));
                break;
            case R.id.exit: {
                new MaterialDialog.Builder(view.getContext())
                        .iconRes(R.drawable.icon_warning)
                        .title("提示")
                        .content("确认退出社团？")
                        .negativeText("取消")
                        .positiveText("确定")
                        .onPositive((dialog, which) -> {
                            exitClub();
                        })
                        .show();
            }
            break;
            case R.id.dissolve: {
                new MaterialDialog.Builder(view.getContext())
                        .iconRes(R.drawable.icon_warning)
                        .title("提示")
                        .content("确认解散社团？")
                        .negativeText("取消")
                        .positiveText("确定")
                        .onPositive((dialog, which) -> {
                            XToastUtils.success("已提交申请");
                            finish();
                        })
                        .show();
            }
            break;
        }
    }
}