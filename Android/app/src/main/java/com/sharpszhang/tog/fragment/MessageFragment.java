package com.sharpszhang.tog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.sharpszhang.tog.Bean.TogMessage;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.activity.ActivityContentActivity;
import com.sharpszhang.tog.activity.MessageDetailActivity;
import com.sharpszhang.tog.activity.NoticesContentActivity;
import com.sharpszhang.tog.adapet.MessageAdapter;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;

import java.util.List;

public class MessageFragment extends Fragment implements RecyclerViewHolder.OnItemClickListener {

    /**
     * 布局
     */
    private SmartRefreshLayout refreshLayout;
    private BaseRecyclerAdapter<TogMessage> adapter;


    private RecyclerView recyclerView;

    private View emptyView;
    private String userId;
    private String token;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        userId = bundle.getString("userId");
        token = bundle.getString("token");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载布局
        View view = inflater.inflate(R.layout.page_message, null);
        emptyView = inflater.inflate(R.layout.empty_activity, null);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        // 数据展示视图
        recyclerView = view.findViewById(R.id.recyclerView);
        // 初始化视图
        WidgetUtils.initRecyclerView(recyclerView);
        initView();
        refreshLayout.setEnableLoadMore(false);
        // 设置下拉刷新监听
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            // 刷新数据
            getDataList();
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void initView() {
        // RecyclerAdapter
        adapter = new MessageAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refreshLayout.resetNoMoreData();
        refreshLayout.setOnRefreshListener(refreshLayout -> getDataList());
        refreshLayout.autoRefresh();
    }

    private void getDataList () {
        XHttp.get("/prod-api/system/tog/message")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .params("userId", userId)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<List<TogMessage>>() {
                    @Override
                    public void onSuccess(List<TogMessage> response) throws Throwable {
                        refreshLayout.finishRefresh(true);
                        if (response != null && response.size() > 0) {
                            adapter.refresh(response);
                            // 展示数据视图
                            refreshLayout.setRefreshContent(recyclerView);
                        } else {
                            refreshLayout.setRefreshContent(emptyView);
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        refreshLayout.finishRefresh(false);
                        refreshLayout.setRefreshContent(emptyView);
                    }
                });
    }


    @Override
    public void onItemClick(View itemView, Object item, int position) {
        // 判断当前是否下拉、上滑状态
        if (!RefreshState.None.equals(refreshLayout.getState())) {
            return;
        }

        switch (((TogMessage) item).getType()) {
            case 0:
                Intent intent = new Intent(this.getContext(), MessageDetailActivity.class);
                intent.putExtra("messageId", "" + ((TogMessage) item).getId());
                intent.putExtra("token", token);
                intent.putExtra("sendId", "" + ((TogMessage) item).getSendId());
                startActivity(intent);
                break;
            case 1:
                Intent intent1 = new Intent(this.getContext(), NoticesContentActivity.class);
                intent1.putExtra("userId", userId);
                intent1.putExtra("token", token);
                intent1.putExtra("noticeId", "" + ((TogMessage) item).getSendId());
                startActivity(intent1);
                break;
            case 2:
                Intent intent2 = new Intent(this.getContext(), ActivityContentActivity.class);
                intent2.putExtra("userId", userId);
                intent2.putExtra("token", token);
                intent2.putExtra("activityId", "" + ((TogMessage) item).getSendId());
                startActivity(intent2);
        }
    }
}
