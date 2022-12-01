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
import com.sharpszhang.tog.Bean.Club;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.activity.ClubActivity;
import com.sharpszhang.tog.adapet.ClubAdapter;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;

import java.util.List;

/**
 * “所有社团”页面
 */
public class AllClubFragment extends Fragment implements RecyclerViewHolder.OnItemClickListener {

    /**
     * 布局
     */
    private SmartRefreshLayout refreshLayout;
    private BaseRecyclerAdapter<Club> adapter;


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
        View view = inflater.inflate(R.layout.layout_fragment, null);
        emptyView = inflater.inflate(R.layout.empty_activity, null);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        // 数据展示视图
        recyclerView = view.findViewById(R.id.recyclerView);
        // 初始化视图
        WidgetUtils.initRecyclerView(recyclerView);
        initView();
        // 设置下拉刷新监听
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            // 刷新数据
            getDataList();
        });
        // 初始化默认刷新数据
        refreshLayout.autoRefresh();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void initView() {
        // RecyclerAdapter
        adapter = new ClubAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refreshLayout.setOnRefreshListener(refreshLayout -> getDataList());
        refreshLayout.autoRefresh();
    }

    private void getDataList () {
        XHttp.get("/prod-api/system/club/list")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .params("userId", userId)
                .headers("Authorization", "Bearer " + token)
                .timeStamp(true)
                .execute(new SimpleCallBack<List<Club>>() {
                    @Override
                    public void onSuccess(List<Club> response) throws Throwable {
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
        Intent intent = new Intent(this.getContext(), ClubActivity.class);
        intent.putExtra("clubId", "" + ((Club) item).getId());
        intent.putExtra("userId", userId);
        intent.putExtra("tokenId", token);
        startActivity(intent);
    }
}