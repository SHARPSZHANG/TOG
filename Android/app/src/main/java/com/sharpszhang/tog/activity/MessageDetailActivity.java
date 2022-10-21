package com.sharpszhang.tog.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

public class MessageDetailActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private TextView grade;
    private TextView faculty;
    private TextView major;
    private TextView userClass;
    private TextView username;
    private RoundButton refuse;
    private RoundButton agree;
    private RadiusImageView userImg;
    private View userContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);
        initData();
        initView();
    }

    public void initView() {
        grade = findViewById(R.id.grade);
        faculty = findViewById(R.id.faculty);
        major = findViewById(R.id.major);
        userClass = findViewById(R.id.user_class);
        username = findViewById(R.id.username);
        userImg = findViewById(R.id.user_img);
        refuse = findViewById(R.id.refuse);
        agree = findViewById(R.id.agree);
        userContent = findViewById(R.id.user_content);
        userContent.setOnClickListener(this);
        refuse.setOnClickListener(this);
        agree.setOnClickListener(this);



        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

    }

    public void initData() {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_content:

                break;
            case R.id.refuse:

                break;
            case R.id.agree:

                break;
            default:
                break;
        }
    }
}