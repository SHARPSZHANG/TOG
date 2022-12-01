/*
 * Copyright (C) 2018 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sharpszhang.tog.adapet;

import androidx.annotation.NonNull;

import com.sharpszhang.tog.Bean.ActivityVo;
import com.sharpszhang.tog.Bean.Book;
import com.sharpszhang.tog.R;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import org.jetbrains.annotations.NotNull;

/**
 * @author xuexiang
 * @since 2018/7/17 下午5:25
 */
public class ActivityAdapter extends BaseRecyclerAdapter<ActivityVo> {


    @Override
    protected void bindData(@NonNull @NotNull RecyclerViewHolder holder, int position, ActivityVo item) {
        holder.text(R.id.activity_title, item.getTitle());
        holder.text(R.id.activity_content, item.getContent());
        holder.text(R.id.activity_time, item.getStartTime() + "-" +item.getEndTime());
        holder.text(R.id.org_name, ""+item.getClubName());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.template_activity;
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
