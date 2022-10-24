package com.sharpszhang.tog.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.button.switchbutton.SwitchButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;
import com.xuexiang.xui.widget.toast.XToast;

import java.text.SimpleDateFormat;


public class RecruitmentActivity extends BaseActivity {

    private TitleBar titleBar;
    private TextView clubName;
    private TextView clubDetails;
    private SwitchButton action;
    private SwitchButton notice;
    private SwitchButton activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruitment);
        initData();
        initView();
    }

    public void initView() {
        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);
        action = findViewById(R.id.sb_action);
        notice = findViewById(R.id.sb_notice);
        activity = findViewById(R.id.sb_activity);
        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        })
        .addAction(new TitleBar.TextAction("保存") {
            @Override
            public void performAction(View view) {
                XToastUtils.success("成功");
            }
        });

    }

    public void initData() {

    }

}