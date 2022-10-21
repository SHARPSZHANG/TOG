package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.sharpszhang.tog.Bean.ContentPage;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.BaseActivity;
import com.sharpszhang.tog.fragment.ClubActivityFragment;
import com.sharpszhang.tog.fragment.ClubDetailsFragment;
import com.sharpszhang.tog.fragment.ClubOrgFragment;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.tabbar.EasyIndicator;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class ClubActivity extends BaseActivity implements EasyIndicator.OnTabClickListener {

    private ClubActivityFragment clubActivityFragment;
    private ClubOrgFragment clubOrgFragment;
    private ClubDetailsFragment clubDetailsFragment;

    private FragmentManager fragmentManager;
    private TitleBar titleBar;

    private Intent intent;

    // 登陆用户
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club);
        ButterKnife.bind(this);
        initFragment();
        initView();
    }

    private void initView() {
        EasyIndicator easyIndicator = findViewById(R.id.tab);
        easyIndicator.setTabTitles(ContentPage.getPageNames());
        easyIndicator.setOnTabClickListener(this);


    }

    private void initFragment() {
        clubActivityFragment = new ClubActivityFragment();
        clubOrgFragment = new ClubOrgFragment();
        clubDetailsFragment = new ClubDetailsFragment();
        fragmentManager = getSupportFragmentManager();
        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });
        switchFragment(clubActivityFragment);


    }


    @Override
    public void onTabClick(String title, int position) {
        switch (position) {
            case 0:
                switchFragment(clubActivityFragment);
                clubOrgFragment.onResume();
                clubDetailsFragment.onResume();
                break;
            case 1:
                switchFragment(clubOrgFragment);
                clubActivityFragment.onResume();
                clubDetailsFragment.onResume();
                break;
            case 2:
                switchFragment(clubDetailsFragment);
                clubOrgFragment.onResume();
                clubActivityFragment.onResume();
                break;
            default:
                break;
        }
    }

    private void switchFragment(Fragment targetFragment) {
        /*开启事务*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        /*页面跳转，参数类型：主FragmentID，需要跳转的FragmentID*/
        fragmentTransaction.replace(R.id.home_page_container, targetFragment).commit();
    }
}