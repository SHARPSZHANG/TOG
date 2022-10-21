package com.sharpszhang.tog.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

public class NoticeActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private TextView noticeTitle;
    private TextView clubName;
    private TextView clubDetails;
    private RoundButton release;
    private RadiusImageView clubImg;
    private View clubContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        initData();
        initView();
    }

    public void initView() {
        noticeTitle = findViewById(R.id.notice_title);
        release = findViewById(R.id.release);
        clubImg = findViewById(R.id.club_img);
        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);
        clubContent = findViewById(R.id.club_content);
        clubContent.setOnClickListener(this);
        release.setOnClickListener(this);

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
            case R.id.club_content:

                break;
            case R.id.release:

                break;
            default:
                break;
        }
    }
}