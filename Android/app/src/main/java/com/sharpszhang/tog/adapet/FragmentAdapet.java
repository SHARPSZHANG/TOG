package com.sharpszhang.tog.adapet;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentAdapet extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private  List<String> strings;

    public FragmentAdapet(@NonNull FragmentManager fm, List<Fragment> fragmentList, List<String> strings) {
        super(fm);
        this.fragmentList = fragmentList;
        this.strings = strings;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public CharSequence getPageTitle(int position){
        return strings.get(position);
    }

}
