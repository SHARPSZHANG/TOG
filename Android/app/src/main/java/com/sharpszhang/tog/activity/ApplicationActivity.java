package com.sharpszhang.tog.activity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;

import java.text.SimpleDateFormat;
import java.util.List;


public class ApplicationActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private Spinner clubLst;
    private TextView activityTitle;
    private TextView activityTimeText;
    private XUIAlphaButton activityTime;
    private RoundButton release;
    private MultiLineEditText activityContent;
    private TimePickerView mDatePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        initData();
        initView();
    }

    public void initView() {
        clubLst = findViewById(R.id.club_list);
        release = findViewById(R.id.release);
        activityTitle = findViewById(R.id.activity_title);
        activityTimeText = findViewById(R.id.activity_time_text);
        activityTime = findViewById(R.id.activity_time);
        activityContent = findViewById(R.id.activity_content);
        release.setOnClickListener(this);
        activityTime.setOnClickListener(this);

        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

        WidgetUtils.initSpinnerStyle(clubLst, new String[] {"aaa", "bbb", "ccc", "ddd"} );

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
            case R.id.activity_time:
                showDatePicker();
            default:
                break;
        }
    }

    private void showDatePicker() {
        if (mDatePicker == null) {
            mDatePicker = new TimePickerBuilder(this, (date, v) -> activityTimeText.setText(new SimpleDateFormat("yyyy-MM-dd").format(date)))
                    .setTitleText("日期选择")
                    .build();
        }
        mDatePicker.show();
    }
}