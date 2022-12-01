package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.Bean.ContentPage;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.fragment.ClubActivityFragment;
import com.sharpszhang.tog.fragment.ClubDetailsFragment;
import com.sharpszhang.tog.fragment.ClubOrgFragment;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.tabbar.EasyIndicator;

import butterknife.ButterKnife;

public class ClubActivity extends BaseActivity implements EasyIndicator.OnTabClickListener {

    private ClubActivityFragment clubActivityFragment;
    private ClubOrgFragment clubOrgFragment;
    private ClubDetailsFragment clubDetailsFragment;

    private FragmentManager fragmentManager;

    private TextView clubName;
    private TextView clubDetails;

    // 登陆用户
    private String clubId;
    private String userId;
    private String token;

    private Club club;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        Intent intent = getIntent();
        clubId = intent.getStringExtra("clubId");
        userId = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
        ButterKnife.bind(this);
        getDataList();
        initFragment();
        initView();
    }

    private void initView() {
        EasyIndicator easyIndicator = findViewById(R.id.tab);
        easyIndicator.setTabTitles(ContentPage.getPageNames());
        easyIndicator.setOnTabClickListener(this);

        clubName = findViewById(R.id.clubName);
        clubDetails = findViewById(R.id.clubDetails);


    }

    private void initFragment() {
        clubActivityFragment = new ClubActivityFragment();
        clubOrgFragment = new ClubOrgFragment();
        clubDetailsFragment = new ClubDetailsFragment();
        fragmentManager = getSupportFragmentManager();
        TitleBar titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });
        Bundle bundle = new Bundle();
        bundle.putString("clubId", clubId);
        bundle.putString("token", token);
        bundle.putString("userId", userId);
        switchFragment(clubActivityFragment, bundle);


    }

    private void getDataList () {
        XHttp.get("/prod-api/system/club/" + clubId)
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<Club>() {
                    @Override
                    public void onSuccess(Club response) throws Throwable {
                        if (response != null) {
                            club = response;
                            clubName.setText(club.getClubName());
                            clubDetails.setText(club.getClubDescription());
                        }
                    }

                    @Override
                    public void onError(ApiException e) {
                        setContentView(R.layout.empty_activity);
                    }
                });
    }


    @Override
    public void onTabClick(String title, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("clubId", clubId);
        bundle.putString("token", token);
        bundle.putString("userId", userId);
        switch (position) {
            case 0:
                switchFragment(clubActivityFragment, bundle);
                clubOrgFragment.onResume();
                clubDetailsFragment.onResume();
                break;
            case 1:
                switchFragment(clubOrgFragment, bundle);
                clubActivityFragment.onResume();
                clubDetailsFragment.onResume();
                break;
            case 2:
                bundle.putString("createDate", club.getGmtCreate());
                bundle.putString("details", club.getClubDetails());
                switchFragment(clubDetailsFragment, bundle);
                clubOrgFragment.onResume();
                clubActivityFragment.onResume();
                break;
            default:
                break;
        }
    }

    private void switchFragment(Fragment targetFragment, Bundle bundle) {
        targetFragment.setArguments(bundle);
        /*开启事务*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        /*页面跳转，参数类型：主FragmentID，需要跳转的FragmentID*/
        fragmentTransaction.replace(R.id.home_page_container, targetFragment).commit();
    }
}