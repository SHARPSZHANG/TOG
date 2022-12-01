package com.sharpszhang.tog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sharpszhang.tog.R;

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
        Bundle bundle = this.getArguments();
        String createDate = bundle.getString("createDate");
        String content = bundle.getString("details");
        createTime.setText(createDate);
        details.setText(content);
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
