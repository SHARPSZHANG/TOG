package com.sharpszhang.tog.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.sharpszhang.tog.Bean.NoticeVo;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.adapet.NoticesAdapter;
import com.sharpszhang.tog.base.BaseActivity;
import com.xuexiang.xhttp2.XHttp;
import com.xuexiang.xhttp2.callback.SimpleCallBack;
import com.xuexiang.xhttp2.exception.ApiException;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;

import java.util.List;


public class NoticesManageActivity extends BaseActivity implements RecyclerViewHolder.OnItemClickListener {
    /**
     * 布局
     */
    private SmartRefreshLayout refreshLayout;
    private BaseRecyclerAdapter<NoticeVo> adapter;


    private RecyclerView recyclerView;

    private View emptyView;

    private String id;
    private String clubId;
    private String token;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_settings);
        Intent intent = getIntent();
        id = intent.getStringExtra("userId");
        token = intent.getStringExtra("token");
        clubId = intent.getStringExtra("clubId");
        refreshLayout = findViewById(R.id.refreshLayout);
        // 数据展示视图
        recyclerView = findViewById(R.id.recyclerView);
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
        XHttp.get("/prod-api/system/mobile/notice/findNoticeByClubId")
                .syncRequest(false)
                .onMainThread(true)
                .timeOut(1000)
                .timeStamp(true)
                .execute(new SimpleCallBack<List<NoticeVo>>() {
                    @Override
                    public void onSuccess(List<NoticeVo> response) throws Throwable {
                        refreshLayout.finishRefresh(true);
                        if (response != null && response.size() > 0) {
                            adapter.refresh(response);
                            // 展示数据视图
                            refreshLayout.setRefreshContent(recyclerView);
                        } else {
                            setContentView(R.layout.empty_activity);
                        }
                    }
                    @Override
                    public void onError(ApiException e) {
                        refreshLayout.finishRefresh(false);
                    }
                });
    }


    @Override
    public void onItemClick(View itemView, Object item, int position) {
        // 判断当前是否下拉、上滑状态
        if (!RefreshState.None.equals(refreshLayout.getState())) {
            return;
        }
        Intent intent = new Intent(this, NoticesContentActivity.class);
        intent.putExtra("noticeId", "" + ((NoticeVo) item).getId());
        intent.putExtra("userId", "" + id);
        intent.putExtra("token", "" + token);
        startActivity(intent);
    }
}