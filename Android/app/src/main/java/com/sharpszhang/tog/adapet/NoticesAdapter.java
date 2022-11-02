package com.sharpszhang.tog.adapet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.sharpszhang.tog.Bean.ActivityBean;
import com.sharpszhang.tog.R;

import java.util.List;

public class NoticesAdapter extends BaseAdapter {
    private final List<ActivityBean> mList;//数据源
    private final LayoutInflater inflater;

    public NoticesAdapter(Context context, List<ActivityBean> mList) {
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
        View view = inflater.inflate(R.layout.template_notice,null);
        /**
         * 找到item布局文件中对应的控件
         */

        return view;
    }

}