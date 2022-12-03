package com.sharpszhang.tog.adapet;

import androidx.annotation.NonNull;

import com.sharpszhang.tog.Bean.TogMessage;
import com.sharpszhang.tog.R;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import org.jetbrains.annotations.NotNull;

public class MessageAdapter extends BaseRecyclerAdapter<TogMessage> {
    @Override
    protected void bindData(@NonNull @NotNull RecyclerViewHolder holder, int position, TogMessage item) {
        holder.text(R.id.message_title, item.getTitle());
        holder.text(R.id.message, item.getContent());
        holder.text(R.id.message_status, item.getStatus() == 1 ? "已处理" : "待处理");
        holder.backgroundResId(R.id.message_layout, item.getStatus() == 1 ? R.color.transparent : R.color.ivory);
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.template_message;
    }



    public static String getBaseImgUrl() {
        String baseUrl = XHttp.getBaseUrl().trim();
        if (baseUrl.endsWith("/")) {
            return baseUrl + "file/downloadFile/";
        } else {
            return baseUrl + "/file/downloadFile/";
        }

    }



}