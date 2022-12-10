package com.sharpszhang.tog.adapet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.OrgItem;

import java.util.List;

public class ClubOrgAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<String> data;

    public ClubOrgAdapter(Context context, List<String> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public int getCount() {
        return Math.max(data.size(), 0);
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        view = inflater.inflate(R.layout.template_org, null);
        TextView name = view.findViewById(R.id.name);
        name.setText(data.get(i));
        return view;
    }
}
