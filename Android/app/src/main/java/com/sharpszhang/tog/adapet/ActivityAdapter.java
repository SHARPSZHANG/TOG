package com.sharpszhang.tog.adapet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sharpszhang.tog.Bean.ActivityBean;
import com.sharpszhang.tog.R;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ActivityAdapter extends BaseAdapter {
    private final List<ActivityBean> mList;//数据源
    private final LayoutInflater inflater;

    public ActivityAdapter(Context context, List<ActivityBean> mList) {
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
        View view = inflater.inflate(R.layout.template_activity,null);
        /**
         * 找到item布局文件中对应的控件
         */
        RadiusImageView activityImg= view.findViewById(R.id.activity_img);
        TextView activityTitle = view.findViewById(R.id.activity_title);
        TextView activityContent = view.findViewById(R.id.activity_content);
        TextView activityTime = view.findViewById(R.id.activity_time);
        TextView activityType = view.findViewById(R.id.activity_type);
        TextView orgName = view.findViewById(R.id.org_name);
        //获取相应索引的ItemBean对象
        ActivityBean bean = mList.get(position);
        ///**
        // * 设置控件的对应属性值
        // */
        //activityImg.setImageResource(bean.getOrgIcon());
        activityTitle.setText(bean.getActivityTitle());
        activityContent.setText(bean.getActivityContent());
        activityTime.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(bean.getActivityStartTime()));

        if((bean.getActivityStartTime().getTime() - new Date().getTime()) > 0) {
            activityType.setText("未开始");
        } else if(bean.getActivityEndTime().getTime() > new Date().getTime()) {
            activityType.setText("进行中");
        } else if(bean.getActivityEndTime().getTime() < new Date().getTime()) {
            activityType.setText("已结束");
        }
        orgName.setText(bean.getOrgName());
        return view;
    }

}