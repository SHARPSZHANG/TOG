package com.sharpszhang.tog.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sharpszhang.tog.Bean.LoginBody;
import com.sharpszhang.tog.Bean.SysUser;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;

import org.json.JSONException;

import java.io.UnsupportedEncodingException;

import static com.sharpszhang.tog.utils.KeyboardUtils.isShouldHideInput;


/*
 * 登陆页面
 * 页面提供注册账号、找回密码
 * 进行账户名与密码验证
 */
public class Login extends BaseActivity implements View.OnClickListener, TextWatcher {
    private Gson gson;
    // 定义结果值
    public final int REQUEST_CODE = 0X101;
    // 用户名栏信息 全局变量
    private String accountText = null;
    // 用户名栏  输入框  清空图标
    private LinearLayout usernameLayout;
    private EditText usernameEdit;
    private ImageView usernameDel;
    // 密码栏  输入框  清空图标
    private LinearLayout passwordLayout;
    private EditText passwordEdit;
    private ImageView passwordDel;
    // 登陆 注册按钮
    private Button submitButton;
    private Button registerButton;


    /**
     * @param savedInstanceState
     * 设置关联视图
     * 加载初始化
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    /**
     * 初始化视图
     * 获取控件
     * 设置监听事件
     */
    private void initView(){
        // 获取控件
        // 用户名栏 输入框  清空图标
        usernameLayout = findViewById(R.id.login_username);
        usernameEdit = findViewById(R.id.login_username_edit);
        usernameDel = findViewById(R.id.login_username_del);
        // 密码栏  输入框 清空图标
        passwordLayout = findViewById(R.id.login_password);
        passwordEdit = findViewById(R.id.login_password_edit);
        passwordDel = findViewById(R.id.login_password_del);
        // 登陆  注册按钮
        submitButton = findViewById(R.id.login_submit_bt);
        registerButton = findViewById(R.id.login_register_bt);
        // 设置点击事件监听
        usernameEdit.setOnClickListener(this);
        usernameDel.setOnClickListener(this);
        passwordEdit.setOnClickListener(this);
        passwordDel.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        // 设置文本监听
        usernameEdit.addTextChangedListener(this);
        passwordEdit.addTextChangedListener(this);
        // 设置登陆按钮默认不可用
        submitButton.setEnabled(false);


    }

    // 事件处理
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.login_username_del:
                usernameEdit.setText(null);
                break;
            case R.id.login_password_del:
                passwordEdit.setText(null);
                break;
            case R.id.login_submit_bt:
                try {
                    submit();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.login_register_bt:
                registered();
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

    // 用户名发生改变清除密码栏
    @Override
    public void afterTextChanged(Editable s) {
        System.out.println(s.toString().trim());
        String username = usernameEdit.getText().toString().trim();
        String pwd = passwordEdit.getText().toString().trim();
//        if(!(accountText.equals(username))){
//            passwordEdit.setText(null);
//            accountText = username;
//        }
        // 是否显示清除按钮
        if (username.length() > 0) {
            usernameDel.setVisibility(View.VISIBLE);
        } else {
            usernameDel.setVisibility(View.INVISIBLE);
        }
        if (pwd.length() > 0) {
            passwordDel.setVisibility(View.VISIBLE);
        } else {
            passwordDel.setVisibility(View.INVISIBLE);
        }

        // 登录按钮是否可用
        // 用户名、密码均不为空的时候按钮可用
        // getColor() 方法在API 23中过时 采用ContextCompat.getColor(context,R.color.name))替代
        if (!TextUtils.isEmpty(pwd) && !TextUtils.isEmpty(username)) {
            submitButton.setEnabled(true);
            submitButton.setBackgroundResource(R.drawable.submit_unlock);
            submitButton.setTextColor(ContextCompat.getColor(this, R.color.white));
        } else {
            submitButton.setEnabled(false);
            submitButton.setBackgroundResource(R.drawable.submit_lock);
            submitButton.setTextColor(ContextCompat.getColor(this, R.color.white));
        }
    }

    // 登陆
    private void submit() throws JSONException, UnsupportedEncodingException {
        LoginBody login = new LoginBody();
        login.setUsername(usernameEdit.getText().toString());
        login.setPassword(passwordEdit.getText().toString());
        XHttp.post("/prod-api/toLogin")
                .upJson(JSONObject.toJSONString(login))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onSuccess(String token) {
                        if (token != null) {
                            getUserInfo(token);
                        } else {
                            XToastUtils.toast("登陆失败！");
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        XToastUtils.toast("登陆失败！");
                    }
                });

    }

    private void getUserInfo(String token) {
        LoginBody login = new LoginBody();
        login.setUsername(usernameEdit.getText().toString());
        login.setPassword(passwordEdit.getText().toString());
        XHttp.get("/prod-api/getUserInfo")
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<SysUser>() {
                    @Override
                    public void onSuccess(SysUser response) {
                        if (response != null) {
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class).putExtra("userId", response.getUserId().toString()).putExtra("token", token));
                            finish();
                        } else {
                            XToastUtils.toast("登陆失败！");
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        XToastUtils.toast("登陆失败！");
                    }
                });

    }



    // 注册
    // 调用注册页面
    private void registered(){
        Intent intent = new Intent(getApplication(), Registered.class);
        startActivity(intent);
        // 界面切换方式
        overridePendingTransition(R.anim.slid_right_in, R.anim.slid_left_out);
    }

    // 获取刚注册的账号
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null && requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            // 将注册账号写入账号栏
            usernameEdit.setText(data.getStringExtra("username"));
            passwordEdit.setText(data.getStringExtra("password"));
        }
    }

    // 账号或密码错误提示
    private void showDialog(){
        Context context;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("账号或密码错误").setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }

    /**
     * @param ev
     * 点击空白处收回键盘
     * @return
     *
     */
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

    // 返回键返回主页不销毁activity
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
