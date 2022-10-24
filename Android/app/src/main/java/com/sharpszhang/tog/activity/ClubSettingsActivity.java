package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sharpszhang.tog.Bean.ActivityBean;
import com.sharpszhang.tog.Bean.RestCode;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.adapet.ClubAdapter;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.service.Service;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.switchbutton.SwitchButton;

import java.util.List;


public class ClubSettingsActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    private List<ActivityBean> clubs;

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
        ListView listView =findViewById(R.id.list_view);
        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);
        action = findViewById(R.id.sb_action);
        notice = findViewById(R.id.sb_notice);
        activity = findViewById(R.id.sb_activity);
        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });
        final ClubAdapter adapter = new ClubAdapter(this, clubs);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    public void initData() {
        JSON json = Service.selectPosts("");
        if(json != null) {
            RestCode result = JSONArray.parseObject(json.toJSONString(), RestCode.class);
            clubs =JSONArray.parseArray(result.getData().toString(), ActivityBean.class);;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(this, ClubActivity.class));
    }
}