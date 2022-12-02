package com.sharpszhang.tog.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sharpszhang.tog.Bean.ActivityVo;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.adapet.ActivityAdapter;
import com.sharpszhang.tog.base.BaseActivity;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class ActivityManageActivity extends BaseActivity implements RecyclerViewHolder.OnItemClickListener {

    private TitleBar titleBar;

    private SmartRefreshLayout refreshLayout;
    private BaseRecyclerAdapter<ActivityVo> adapter;


    private RecyclerView recyclerView;

    private View emptyView;
    private String userId;
    private String clubId;
    private String token;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_settings);
        emptyView = findViewById(R.layout.empty_activity);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        clubId = intent.getStringExtra("clubId");
        token = intent.getStringExtra("token");
        initView();
    }

    public void initView() {

        titleBar = findViewById(R.id.title_bar);
        titleBar.setLeftClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slid_left_in, R.anim.slid_right_out);
        });

        refreshLayout = findViewById(R.id.refreshLayout);
        recyclerView = findViewById(R.id.recyclerView);
        // 初始化视图
        WidgetUtils.initRecyclerView(recyclerView);
        // RecyclerAdapter
        adapter = new ActivityAdapter();
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                getDataList();
            }
        });
        refreshLayout.autoRefresh();
        // 设置下拉刷新监听
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            // 刷新数据
            getDataList();
        });
        // 初始化默认刷新数据
        refreshLayout.autoRefresh();
    }

    private void getDataList () {
        XHttp.get("/prod-api/system/mobile/activity/findActivityByClubId")
                .syncRequest(false)
                .onMainThread(true)
                .params("clubId", clubId)
                .headers("Authorization", "Bearer " + token)
                .timeOut(1000)
                .timeStamp(true)
                .execute(new SimpleCallBack<List<ActivityVo>>() {
                    @Override
                    public void onSuccess(List<ActivityVo> response) throws Throwable {
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
        startActivity(new Intent(this, NoticesContentActivity.class).putExtra("activityId", ((ActivityVo) item).getId()).putExtra("userId", userId).putExtra("token", token));
    }
}