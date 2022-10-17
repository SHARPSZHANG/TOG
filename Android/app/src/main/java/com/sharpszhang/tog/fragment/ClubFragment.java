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

public class ClubFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_club, container, false);
        TabLayout tabLayout = view.findViewById(R.id.tab_club);
        ViewPager viewPager = view.findViewById(R.id.view_club);
        tabLayout.setupWithViewPager(viewPager);
        List<Fragment> fragmentList = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        fragmentList.add(new MyClubFragment());
        fragmentList.add(new AllClubFragment());
        strings.add("我加入的");
        strings.add("全部社团");
        viewPager.setAdapter(new FragmentAdapet(getChildFragmentManager(), fragmentList, strings));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
