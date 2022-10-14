package com.sharpszhang.tog.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sharpszhang.tog.Bean.BaseActivity;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.fragment.DiscoverFragment;

import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements View.OnClickListener {
    // 底部导航
    private BottomNavigationView bottomNavigationView;
    // 发现页
    private DiscoverFragment discoverFragment;
    // 关注页
    //private AttentionFragment attentionFragment;
    // 消息页
    //private InformationFragment informationFragment;
    // 个人页
    //private MyFragment myFragment;

    private FragmentManager fragmentManager;

    private Intent intent;

    // 登陆用户
    private String user;

    // 发布扩展页

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        intent = getIntent();
        // 获取登陆用户名
        user = intent.getStringExtra("username");
        // 绑定视图
        ButterKnife.bind(this);
        // 初始化fragment
        initFragment();
        // 初始化主页
        initView();

    }

    /**
     * 底部导航选择
     */
    @SuppressLint("NonConstantResourceId")
    private void initView(){
        // 传入登陆用户名
        intent.putExtra("username", user);
        // 导航栏绑定fragment
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.discover:
//                         加载发现页
                    switchFragment(discoverFragment);
                    // 刷新页面？？？？？？
                    //informationFragment.onResume();
                    //myFragment.onResume();
                    //attentionFragment.onResume();
                    break;
                case R.id.attention:
//                         加载关注页
//                    switchFragment(attentionFragment);
                    // 发现页面挂起
                    discoverFragment.onResume();
                    //informationFragment.onResume();
                    //myFragment.onResume();
                    break;
                //case R.id.release:
                //    discoverFragment.onResume();
                //    informationFragment.onResume();
                //    myFragment.onResume();
                //    attentionFragment.onResume();
                //    intent.setClass(HomeActivity.this, ReleaseActivity.class);
                //    startActivity(intent);
                //    break;
                //case R.id.information:
                //    switchFragment(informationFragment);
                //    discoverFragment.onResume();
                //    attentionFragment.onResume();
                //    myFragment.onResume();
                //    break;
                //case R.id.my:
                //    switchFragment(myFragment);
                //    discoverFragment.onResume();
                //    informationFragment.onResume();
                //    attentionFragment.onResume();
                //    break;
                default:
                    break;
            }
            return true;
        });
    }

    /**
     * 初始化页面
     */
    @SuppressLint("RestrictedApi")
    private void initFragment(){
        // 获取发现页  关注页  消息页  个人页
        discoverFragment = new DiscoverFragment();
        //attentionFragment = new AttentionFragment();
        //informationFragment = new InformationFragment();
        //myFragment = new MyFragment();
        fragmentManager = getSupportFragmentManager();
        // activity跳转fragment传值
        Bundle bundle = new Bundle();
        bundle.putString("username", user);
        //myFragment.setArguments(bundle);
        // 获取底部导航
        bottomNavigationView = findViewById(R.id.home_button);
        // 为底部导航页中的消息按钮天际角标
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bottomNavigationView.getChildAt(0);
        View view = menuView.getChildAt(3);
        BottomNavigationItemView itemView = (BottomNavigationItemView) view;
        View badge = LayoutInflater.from(this).inflate(R.layout.menu, menuView, false);
        itemView.addView(badge);
        TextView count = (TextView) badge.findViewById(R.id.tv_msg_count);
        // 设置消息数
        count.setText(String.valueOf(33));

        View view1 = menuView.getChildAt(2);
        BottomNavigationItemView itemView1 = (BottomNavigationItemView) view1;
        itemView1.setIconSize(100);
        itemView1.setPadding( 0, 7, 0, 7);
        // 设置启动默认视图为发现页
        switchFragment(discoverFragment);
    }

    /**
     * @param targetFragment 将要跳转的页面
     * 进行页面跳转
     */
    private void switchFragment(Fragment targetFragment) {
        /*开启事务*/
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        /*页面跳转，参数类型：主FragmentID，需要跳转的FragmentID*/
        fragmentTransaction.replace(R.id.home_page_container, targetFragment).commit();
    }

    @Override
    public void onClick(View v) {

    }

    // 返回键返回主页不销毁activity
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
