package com.sharpszhang.tog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sharpszhang.tog.Bean.SysUser;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.activity.AddClubActivity;
import com.sharpszhang.tog.activity.ApplicationActivity;
import com.sharpszhang.tog.activity.ClubListActivity;
import com.sharpszhang.tog.activity.NoticeActivity;
import com.sharpszhang.tog.activity.RecruitmentActivity;
import com.sharpszhang.tog.utils.XToastUtils;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;

public class ManageFragment extends Fragment implements View.OnClickListener {

    private View view;
    private View notice;
    private View activity;
    private View recruitment;
    private View clubSettings;
    private View addClub;

    private TextView userName;
    private TextView signature;

    private RoundButton button;

    private String userId;
    private String token;
    private LayoutInflater inf;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        userId = bundle.getString("userId");
        token = bundle.getString("token");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.page_manage, null);
        inf = inflater;
        userName = (TextView) view.findViewById(R.id.username);
        signature = (TextView) view.findViewById(R.id.signature);
        button = (RoundButton) view.findViewById(R.id.userInfo_btn);
        notice = view.findViewById(R.id.manage_notice);
        activity = view.findViewById(R.id.manage_activity);
        clubSettings = view.findViewById(R.id.manage_settings);
        recruitment = view.findViewById(R.id.manage_recruitment);
        addClub = view.findViewById(R.id.addClub);
        notice.setOnClickListener(this);
        activity.setOnClickListener(this);
        recruitment.setOnClickListener(this);
        clubSettings.setOnClickListener(this);
        addClub.setOnClickListener(this);


        button.setOnClickListener(this);
        initDataList();

        if (false) {
            notice.isShown();
            recruitment.isShown();
        }
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
    private void initDataList() {
        XHttp.get("/prod-api/getUserInfo")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .headers("Authorization", "Bearer " + token)
                .timeStamp(true)
                .execute(new SimpleCallBack<SysUser>() {
                    @Override
                    public void onSuccess(SysUser response) throws Throwable {
                        if (response != null) {
                            userName.setText(response.getUserName());
                            signature.setText(response.getRemark());
                        } else {
                            view = inf.inflate(R.layout.empty_activity, null);
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        view = inf.inflate(R.layout.empty_activity, null);
                    }
                });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userInfo_btn:
                XToastUtils.toast("请前往后台修改");
                break;
            case R.id.manage_notice:
                Intent intent1 = new Intent(this.getContext(), NoticeActivity.class);
                intent1.putExtra("userId", userId);
                intent1.putExtra("token", token);
                startActivity(intent1);
                break;
            case R.id.manage_activity:
                Intent intent2 = new Intent(this.getContext(), ApplicationActivity.class);
                intent2.putExtra("userId", userId);
                intent2.putExtra("token", token);
                startActivity(intent2);
                break;
            case R.id.manage_recruitment:
                Intent intent3 = new Intent(this.getContext(), RecruitmentActivity.class);
                intent3.putExtra("userId", userId);
                intent3.putExtra("token", token);
                startActivity(intent3);
                break;
            case R.id.manage_settings:
                Intent intent4 = new Intent(this.getContext(), ClubListActivity.class);
                intent4.putExtra("userId", userId);
                intent4.putExtra("token", token);
                startActivity(intent4);
                break;
            case R.id.addClub:
                startActivity(new Intent(this.getContext(), AddClubActivity.class).putExtra("userId", userId).putExtra("token", token));
        }

    }
}
