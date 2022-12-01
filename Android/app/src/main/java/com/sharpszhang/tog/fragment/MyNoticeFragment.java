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
import com.sharpszhang.tog.Bean.NoticeVo;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.activity.NoticesContentActivity;
import com.sharpszhang.tog.adapet.NoticesAdapter;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;

import java.util.List;

/**
 * “全部活动”页面
 */
public class MyNoticeFragment extends Fragment implements RecyclerViewHolder.OnItemClickListener {


    /**
     * 布局
     */
    private SmartRefreshLayout refreshLayout;
    private BaseRecyclerAdapter<NoticeVo> adapter;


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
        adapter = new NoticesAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refreshLayout.setOnRefreshListener(refreshLayout -> getDataList());
        refreshLayout.autoRefresh();
    }

    private void getDataList () {
        XHttp.get("/prod-api/system/activity/findNoticeByUserId")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .params("userId", userId)
                .headers("Authorization", "Bearer " + token)
                .execute(new SimpleCallBack<List<NoticeVo>>() {
                    @Override
                    public void onSuccess(List<NoticeVo> response) throws Throwable {
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
        Intent intent = new Intent(this.getContext(), NoticesContentActivity.class);
        intent.putExtra("noticeId", "" + ((NoticeVo) item).getId());
        intent.putExtra("userId", userId);
        intent.putExtra("tokenId", token);
        startActivity(intent);
    }
}