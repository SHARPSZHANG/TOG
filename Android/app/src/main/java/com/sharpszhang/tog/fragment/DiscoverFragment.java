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

public class DiscoverFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_page, container, false);
        TabLayout tabLayout = view.findViewById(R.id.discover_tab);
        ViewPager viewPager = view.findViewById(R.id.discover_page);
        tabLayout.setupWithViewPager(viewPager);
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        fragmentList.add(new MyDiscover());
        fragmentList.add(new AllDiscover());
        strings.add("我的活动");
        strings.add("全部活动");
        viewPager.setAdapter(new FragmentAdapet(getChildFragmentManager(), fragmentList, strings));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
