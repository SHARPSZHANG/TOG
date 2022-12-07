package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sharpszhang.tog.Bean.ActivityVo;
import com.sharpszhang.tog.Bean.SysUser;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;
import com.xuexiang.xui.widget.edittext.PasswordEditText;
import com.xuexiang.xui.widget.textview.autofit.AutoFitTextView;

/**
 * ”活动详情“页面
 */
public class EditUserActivity extends BaseActivity {

    private SysUser userInfo;

    private PasswordEditText passwordEditText;
    private TextView username;
    private TextView nickname;
    private String userId;
    private String token;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_user);
        username = findViewById(R.id.username);
        nickname = findViewById(R.id.nickname);
        passwordEditText = findViewById(R.id.password);

        // 头部导航栏
        titleBar = findViewById(R.id.title_bar);
        // 返回
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });


        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");

        getData();

    }

    private void getData () {
        XHttp.get("/prod-api/getUserInfo")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<SysUser>() {
                    @Override
                    public void onSuccess(SysUser response) throws Throwable {
                        userInfo = response;
                        username.setText(userInfo.getUserName());
                        nickname.setText(userInfo.getNickName());
                    }
                    @Override
                    public void onError(ApiException e) {
                        setContentView(R.layout.empty_activity);
                    }
                });
    }

    private void editUser() {
        SysUser user = new SysUser();
        user.setUserId(Long.valueOf(userId));
        user.setPassword(passwordEditText.getText().toString());
        XHttp.post("/prod-api/system/mobile/edit")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .upJson(JSONObject.toJSONString(user))
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Boolean>() {
                    @Override
                    public void onSuccess(Boolean response) throws Throwable {
                        if(response) {
                            XToastUtils.success("修改成功");
                            finish();
                        } else {
                            XToastUtils.error("修改失败");
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        XToastUtils.error("修改失败");
                    }
                });
    }


}