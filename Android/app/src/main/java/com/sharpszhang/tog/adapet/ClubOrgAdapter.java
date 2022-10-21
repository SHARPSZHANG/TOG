package com.sharpszhang.tog.adapet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.sharpszhang.tog.R;
import com.sharpszhang.tog.base.OrgItem;

import java.util.List;

public class ClubOrgAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<OrgItem> data;

    public ClubOrgAdapter(Context context, List<OrgItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getGroupCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return data.get(i).getMembers().size();
    }

    @Override
    public Object getGroup(int i) {
        return data.get(i).getTitle();
    }

    @Override
    public Object getChild(int i, int child) {
        return data.get(i).getMembers().get(child);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        OrgHoder orgHoder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.template_org, null);
            orgHoder = new OrgHoder();
            orgHoder.tv_title = view.findViewById(R.id.org_name);
            view.setTag(orgHoder);
        } else {
            orgHoder = (OrgHoder) view.getTag();
        }
        orgHoder.tv_title.setText(data.get(i).getTitle());
        return view;
    }

    @Override
    public View getChildView(int i, int child, boolean b, View view, ViewGroup viewGroup) {
        final MemberHoder memberHoder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.template_member, null);
            memberHoder = new MemberHoder();
            memberHoder.tv_username = view.findViewById(R.id.user_name);
            view.setTag(memberHoder);
        } else {
            memberHoder = (MemberHoder) view.getTag();
        }
        memberHoder.tv_username.setText(data.get(i).getMembers().get(child));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    class OrgHoder {
        TextView tv_title;
    }

    class MemberHoder {
        TextView tv_username;
    }

    void reFreshData(List<OrgItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
