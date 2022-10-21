package com.sharpszhang.tog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sharpszhang.tog.Bean.OrgElement;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.adapet.ClubOrgAdapter;
import com.sharpszhang.tog.base.OrgItem;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class ClubOrgFragment extends Fragment {
    private ExpandableListView expandableListView;
    private ClubOrgAdapter orgAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.club_org, null);
        expandableListView = view.findViewById(R.id.elv_org);
        try {
            initDataList();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 获取数据
        //
        //final ActivityAdapter adapter = new ActivityAdapter(this.getContext(), posts);
        //listView.setOnItemClickListener(this);
        //listView.setAdapter(adapter);
        List<OrgItem> orgItems = new ArrayList<>(16);
        for(String title: OrgElement.getOrgNames()) {
            List<String> members = new ArrayList<>(16);
            for (int i= 0; i < 7; i++) {
                members.add("member" + 1);
            }
            orgItems.add(new OrgItem(title, members));
        }

        //if(list == null || list .size() < 1) {
        //    list.add("暂无");
        //}
        orgAdapter = new ClubOrgAdapter(this.getContext(), orgItems);
        expandableListView.setAdapter(orgAdapter);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    private void initDataList() throws UnsupportedEncodingException {
        //String request = "apipost_id=" + URLEncoder.encode("1e5880", "utf-8");
        //String request = "apipost_id=" + "1e5880";
        //JSON json = Service.selectPosts("");
        //if(json != null) {
        //    RestCode result = JSONArray.parseObject(json.toJSONString(), RestCode.class);
        //    posts =JSONArray.parseArray(result.getData().toString(), ActivityBean.class);;
        //    System.out.println(posts);
        //}

    }
}
