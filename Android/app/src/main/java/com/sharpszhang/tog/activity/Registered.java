package com.sharpszhang.tog.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.sharpszhang.tog.Bean.LoginBody;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.sharpszhang.tog.utils.KeyboardUtils.isShouldHideInput;


/*
 * 注册页面
 * 注册成功则返回账户名给上一页面
 */
public class Registered extends BaseActivity implements View.OnClickListener, TextWatcher {
    // 定义结果
    private final int OK = 1;
    private final int NO = -1;
    // 账号栏  清除按钮
    private EditText accountEdit;
    private ImageView accountDel;
    // 用户名栏  清除按钮
    private EditText usernameEdit;
    private ImageView usernameDel;
    // 密码栏  清除按钮
    private EditText passwordEdit;
    private ImageView passwordDel;
    // 重复密码栏  清除按钮
    private EditText passwordConfirmEdit;
    private ImageView passwordConfirmDel;
    // 顶部导航栏  返回按钮
    TitleBar titleBar;

    // 注册按钮
    private  Button submit;
    private JSONObject data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register);
        initView();
    }

    /**
     * 初始化视图
     * 获取控件
     * 设置点击事件监听
     */
    private void initView(){
        // 顶部栏  返回按钮
        // 账号栏  清除按钮
        accountEdit = findViewById(R.id.register_account_edit);
        accountDel = findViewById(R.id.register_account_del);

        // 用户名栏  清除按钮
        usernameEdit = findViewById(R.id.register_username_edit);
        usernameDel = findViewById(R.id.register_username_del);
        // 密码栏 清除按钮
        passwordEdit = findViewById(R.id.register_password_edit);
        passwordDel = findViewById(R.id.register_password_del);
        // 重复密码栏  清除按钮
        passwordConfirmEdit = findViewById(R.id.register_password_conf_edit);
        passwordConfirmDel = findViewById(R.id.register_password_conf_del);
        // 注册按钮
        submit = findViewById(R.id.register_submit_bt);

        titleBar = findViewById(R.id.title_bar);

        // 设置点击事件监听
        submit.setOnClickListener(this);
        accountDel.setOnClickListener(this);
        usernameDel.setOnClickListener(this);
        passwordDel.setOnClickListener(this);
        passwordConfirmDel.setOnClickListener(this);
        titleBar.setLeftClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

        // 设置文本监听
        accountEdit.addTextChangedListener(this);
        passwordEdit.addTextChangedListener(this);
        passwordConfirmEdit.addTextChangedListener(this);

        // 设置注册按钮默认不可用
        submit.setEnabled(false);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register_account_del:
                accountEdit.setText(null);
                break;
            case R.id.register_password_del:
                passwordEdit.setText(null);
                break;
            case R.id.register_password_conf_del:
                passwordConfirmEdit.setText(null);
                break;
            case R.id.register_username_del:
                usernameEdit.setText(null);
                break;
            case R.id.register_submit_bt:
                try {
                    submit();
                } catch (JSONException | InterruptedException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;

        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String account = accountEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String username = usernameEdit.getText().toString().trim();
        String passwordConfirm = passwordConfirmEdit.getText().toString().trim();
        // 是否显示清除按钮
        if (account.length() > 0) {
            accountDel.setVisibility(View.VISIBLE);
        } else {
            accountDel.setVisibility(View.INVISIBLE);
        }
        if (username.length() > 0) {
            usernameDel.setVisibility(View.VISIBLE);
        } else {
            accountDel.setVisibility(View.INVISIBLE);
        }
        if (password.length() > 0) {
            passwordDel.setVisibility(View.VISIBLE);
        } else {
            passwordDel.setVisibility(View.INVISIBLE);
        }
        if (passwordConfirm.length() > 0) {
            passwordConfirmDel.setVisibility(View.VISIBLE);
        } else {
            passwordConfirmDel.setVisibility(View.INVISIBLE);
        }

        // 登录按钮是否可用
        // 用户名、密码均不为空的时候按钮可用
        // getColor() 方法在API 23中过时 采用ContextCompat.getColor(context,R.color.name))替代
        if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(passwordConfirm)) {
            submit.setEnabled(true);
            submit.setBackgroundResource(R.drawable.submit_unlock);
        } else {
            submit.setEnabled(false);
            submit.setBackgroundResource(R.drawable.submit_lock);
        }
        submit.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    private void submit() throws JSONException, InterruptedException, UnsupportedEncodingException {
        String username = usernameEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String passwordConfirm = passwordConfirmEdit.getText().toString().trim();
        if(password.equals(passwordConfirm)){
            LoginBody login = new LoginBody();
            login.setUsername(accountEdit.getText().toString());
            login.setNiceName(usernameEdit.getText().toString());
            login.setPassword(passwordEdit.getText().toString());
            XHttp.post("/prod-api/register")
                    .headers("Content-Type", "application/json")
                    .upJson(JSONObject.toJSONString(login))
                    .execute(new SimpleCallBack<Boolean>() {
                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            setResult(RESULT_OK, new Intent().putExtra("username", username).putExtra("password", password));
                            XToastUtils.toast("注册成功！");
                            finish();
                        }
                        @Override
                        public void onError(ApiException e) {
                            XToastUtils.toast("注册失败！");
                            e.printStackTrace();

                        }
                    });
        } else {
            data.put("code", false);
            data.put("message", "两次密码不一致");
            showSign(data);
        }
    }
    private void showSign(JSONObject msg) throws JSONException {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String code = msg.getString("code");
        builder.setMessage(msg.getString("message")).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if ("true".equals(code)){
                    finish();
                }
            }
        }).show();
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            //当isShouldHideInput(v, ev)为true时，表示的是点击输入框区域，则需要显示键盘，同时显示光标，反之，需要隐藏键盘、光标
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    //处理Editext的光标隐藏、显示逻辑
                    //  t1.clearFocus();
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }
}
