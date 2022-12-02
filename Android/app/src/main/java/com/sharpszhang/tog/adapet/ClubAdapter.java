package com.sharpszhang.tog.adapet;

import androidx.annotation.NonNull;

import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.R;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

import org.jetbrains.annotations.NotNull;

public class ClubAdapter extends BaseRecyclerAdapter<Club> {

    @Override
    protected void bindData(@NonNull @NotNull RecyclerViewHolder holder, int position, Club item) {
        holder.text(R.id.clubName, item.getClubName());
        holder.text(R.id.clubDetails, item.getClubDesc());
    }

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.template_club;
    }


}