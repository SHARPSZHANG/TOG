package com.sharpszhang.tog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sharpszhang.tog.Bean.Activity;
import com.sharpszhang.tog.Bean.OrgElement;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.adapet.ClubOrgAdapter;
import com.sharpszhang.tog.base.OrgItem;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

public class ClubOrgFragment extends Fragment {
    private ExpandableListView expandableListView;
    private ClubOrgAdapter orgAdapter;

    private String clubId;
    private String userId;
    private String token;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        clubId = bundle.getString("clubId");
        token= bundle.getString("token");
        userId = bundle.getString("userId");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.club_org, null);
        expandableListView = view.findViewById(R.id.elv_org);
        getDataList();
        List<OrgItem> orgItems = new ArrayList<>(16);
        for(String title: OrgElement.getOrgNames()) {
            List<String> members = new ArrayList<>(16);
            for (int i= 0; i < 7; i++) {
                members.add("member" + 1);
            }
            orgItems.add(new OrgItem(title, members));
        }

        orgAdapter = new ClubOrgAdapter(this.getContext(), orgItems);
        expandableListView.setAdapter(orgAdapter);

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    private void getDataList () {
        XHttp.get("/prod-api/system/member/list")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .params("clubId", clubId)
                .headers("Authorization", "Bearer " + token)
                .timeStamp(true)
                .execute(new SimpleCallBack<List<Activity>>() {
                    @Override
                    public void onSuccess(List<Activity> response) throws Throwable {
                        if (response != null && response.size() > 0) {

                        } else {

                        }
                    }
                    @Override
                    public void onError(ApiException e) {

                    }
                });
    }
}
