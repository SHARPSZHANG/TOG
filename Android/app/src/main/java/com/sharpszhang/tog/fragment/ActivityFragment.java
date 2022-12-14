package com.sharpszhang.tog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.adapet.FragmentAdapet;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_activity, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_activity);
        ViewPager viewPager = view.findViewById(R.id.page_activity);
        Bundle bundle = this.getArguments();
        tabLayout.setupWithViewPager(viewPager);
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        MyActivityFragment myActivityFragment = new MyActivityFragment();
        myActivityFragment.setArguments(bundle);
        MyNoticeFragment myNoticeFragment = new MyNoticeFragment();
        myNoticeFragment.setArguments(bundle);
        fragmentList.add(myActivityFragment);
        fragmentList.add(myNoticeFragment);
        strings.add("我的活动");
        strings.add("我的公告");
        viewPager.setAdapter(new FragmentAdapet(getChildFragmentManager(), fragmentList, strings));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
