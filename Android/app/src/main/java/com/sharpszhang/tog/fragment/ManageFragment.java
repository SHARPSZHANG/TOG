package com.sharpszhang.tog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.sharpszhang.tog.Bean.UserContent;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.activity.ApplicationActivity;
import com.sharpszhang.tog.activity.ClubListActivity;
import com.sharpszhang.tog.activity.NoticeActivity;
import com.sharpszhang.tog.activity.RecruitmentActivity;
import com.sharpszhang.tog.service.Service;

import java.io.UnsupportedEncodingException;

public class ManageFragment extends Fragment implements View.OnClickListener {
    private UserContent userContent;

    private View notice;
    private View activity;
    private View recruitment;
    private View clubSettings;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_manage, null);
        System.out.println(view.getWidth());

        System.out.println(this);
        TextView userName = (TextView) view.findViewById(R.id.username);
        TextView signature = (TextView) view.findViewById(R.id.signature);
        Button userInfoBtn = (Button) view.findViewById(R.id.userInfo_btn);
        notice = view.findViewById(R.id.manage_notice);
        activity = view.findViewById(R.id.manage_activity);
        clubSettings = view.findViewById(R.id.manage_settings);
        recruitment = view.findViewById(R.id.manage_recruitment);
        notice.setOnClickListener(this);
        activity.setOnClickListener(this);
        recruitment.setOnClickListener(this);
        clubSettings.setOnClickListener(this);

        userInfoBtn.setOnClickListener(this);
        try {
            initDataList();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 获取数据

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    private void initDataList() throws UnsupportedEncodingException {
        //String request = "apipost_id=" + URLEncoder.encode("1e5880", "utf-8");
        //String request = "apipost_id=" + "1e5880";
        JSON json = Service.selectPosts("");
        if(json != null) {
            //RestCode result = JSONArray.parseObject(json.toJSONString(), RestCode.class);
            //userContent =JSONArray.parseObject(result.getData().toString(), UserContent.class);;
            //System.out.println(userContent);
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userInfo_btn:
                break;
            case R.id.manage_notice:
                startActivity(new Intent(this.getContext(), NoticeActivity.class));
                break;
            case R.id.manage_activity:
                startActivity(new Intent(this.getContext(), ApplicationActivity.class));
                break;
            case R.id.manage_recruitment:
                startActivity(new Intent(this.getContext(), RecruitmentActivity.class));
                break;
            case R.id.manage_settings:
                startActivity(new Intent(this.getContext(), ClubListActivity.class));
        }
    }
}
