package com.sharpszhang.tog.adapet;

import androidx.annotation.NonNull;

import com.sharpszhang.tog.Bean.Book;
import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.R;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import org.jetbrains.annotations.NotNull;

public class ClubAdapter extends BaseRecyclerAdapter<Club> {

    @Override
    protected void bindData(@NonNull @NotNull RecyclerViewHolder holder, int position, Club item) {
        holder.text(R.id.clubName, item.getClubName());
        holder.text(R.id.clubDetails, item.getClubDescription());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.template_club;
    }



    public static String getBaseImgUrl() {
        String baseUrl = XHttp.getBaseUrl().trim();
        if (baseUrl.endsWith("/")) {
            return baseUrl + "file/downloadFile/";
        } else {
            return baseUrl + "/file/downloadFile/";
        }

    }

    public static String getBookImgUrl(Book book) {
        return getBaseImgUrl() + book.getPicture();
    }

    public static String getBookImgUrlWithoutBaseUrl(Book book) {
        return "/file/downloadFile/" + book.getPicture();
    }

}