package com.sharpszhang.tog.adapet;

import androidx.annotation.NonNull;

import com.sharpszhang.tog.Bean.NoticeVo;
import com.sharpszhang.tog.R;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import org.jetbrains.annotations.NotNull;

public class NoticesAdapter extends BaseRecyclerAdapter<NoticeVo> {

    @Override
    protected void bindData(@NonNull @NotNull RecyclerViewHolder holder, int position, NoticeVo item) {
        holder.text(R.id.notice_title, item.getTitle());
        holder.text(R.id.org_name, item.getClubName());
        holder.text(R.id.notice_content, item.getContent());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.template_notice;
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