package com.sharpszhang.tog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sharpszhang.tog.Bean.ActivityBean;
import com.sharpszhang.tog.Bean.RestCode;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.activity.ClubActivity;
import com.sharpszhang.tog.adapet.ActivityAdapter;
import com.sharpszhang.tog.adapet.ClubAdapter;
import com.sharpszhang.tog.service.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MyClubFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<ActivityBean> posts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_activity, null);
        ListView listView = view.findViewById(R.id.list_view);
        try {
            initDataList();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 获取数据

        final ClubAdapter adapter = new ClubAdapter(this.getContext(), posts);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
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
            RestCode result = JSONArray.parseObject(json.toJSONString(), RestCode.class);
            posts =JSONArray.parseArray(result.getData().toString(), ActivityBean.class);;
            System.out.println(posts);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(this.getActivity(), ClubActivity.class));
    }
}
