package com.sharpszhang.tog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.adapet.FragmentAdapet;

import java.util.ArrayList;
import java.util.List;

public class ClubDetailsFragment extends Fragment {
    private TextView createTime;
    private TextView details;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.club_details, container, false);
        createTime = view.findViewById(R.id.tv_org_create_time);
        details = view.findViewById(R.id.tv_org_details);

        createTime.setText("2011-10-15");

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
