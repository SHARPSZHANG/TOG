package com.sharpszhang.tog.adapet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sharpszhang.tog.Bean.Activity;
import com.sharpszhang.tog.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.layout.XUILinearLayout;

import java.util.List;

public class ManageAdapter extends BaseAdapter {
    private final List<Activity> mList;//数据源
    private final LayoutInflater inflater;

    public ManageAdapter(Context context, List<Activity> mList) {
        super();
        // 获取上下文
        this.mList = mList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList == null? 0 :mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //将布局文件转化为View对象
        View view = inflater.inflate(R.layout.template_message,null);
        XUILinearLayout messageLayout = (XUILinearLayout) view.findViewById(R.id.message_layout);
        messageLayout.setRadius(17);
        /**
         * 找到item布局文件中对应的控件
         */
        RadiusImageView activityImg= view.findViewById(R.id.user_img);
        TextView username = view.findViewById(R.id.message_title);
        TextView message = view.findViewById(R.id.message);
        TextView messageState = view.findViewById(R.id.message_status);
        //获取相应索引的ItemBean对象
        Activity bean = mList.get(position);
        ///**
        // * 设置控件的对应属性值
        // */
        //activityImg.setImageResource(bean.getOrgIcon());
        return view;
    }

}