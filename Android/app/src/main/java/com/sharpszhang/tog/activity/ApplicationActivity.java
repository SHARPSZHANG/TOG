package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.sharpszhang.tog.Bean.Activity;
import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.alpha.XUIAlphaButton;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.MultiLineEditText;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.picker.widget.TimePickerView;
import com.xuexiang.xui.widget.picker.widget.builder.TimePickerBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ApplicationActivity extends BaseActivity implements View.OnClickListener {

    private TitleBar titleBar;
    private Spinner clubLst;
    private MaterialEditText activityTitle;
    private TextView activityTimeText;
    private XUIAlphaButton activityTime;
    private RoundButton release;
    private MultiLineEditText activityContent;
    private TimePickerView mDatePicker;

    private String userId;
    private String token;
    private int select;
    private List<Club> clubs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
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

        clubLst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                select = (int) clubLst.getItemIdAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void initData() {
        XHttp.get("/prod-api/system/mobile/club/listByUserId")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .params("userId", userId)
                .headers("Authorization", "Bearer " + token)
                .timeStamp(true)
                .execute(new SimpleCallBack<List<Club>>() {
                    @Override
                    public void onSuccess(List<Club> response) throws Throwable {
                        if (response != null) {
                            clubs = response;
                            List<String> array = new ArrayList<>();
                            for (Club club: response) {
                                array.add(club.getClubName());
                            }
                            WidgetUtils.initSpinnerStyle(clubLst, array.stream().toArray(String[]::new));
                        } else {
                            WidgetUtils.initSpinnerStyle(clubLst, new String[]{"查询社团失败"});
                            release.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        WidgetUtils.initSpinnerStyle(clubLst, new String[]{"查询社团失败"});
                        release.setVisibility(View.GONE);
                    }
                });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.release:
                Activity activity = new Activity();
                activity.setClubId(clubs.get(select).getId());
                activity.setUserId(Long.parseLong(userId));
                activity.setTitle(activityTitle.getEditValue());
                activity.setStartTime(activityTimeText.getText().toString());
                activity.setContent(activityContent.getContentText());
                activity.setGmtCreate(new Date().toString());
                activity.setIsDelete(0);
                XHttp.post("/prod-api/system/mobile/activity")
                        .upJson(JSONObject.toJSONString(activity))
                        .headers("Authorization", "Bearer " + token)
                        .execute(new SimpleCallBack<Boolean>() {
                            @Override
                            public void onSuccess(Boolean aBoolean) {
                                if (aBoolean) {
                                    XToastUtils.toast("发起成功，待审批中");
                                    finish();
                                } else {
                                    XToastUtils.toast("发起失败！");
                                }
                            }

                            @Override
                            public void onError(ApiException e) {
                                XToastUtils.toast("发起失败！");
                            }
                        });
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