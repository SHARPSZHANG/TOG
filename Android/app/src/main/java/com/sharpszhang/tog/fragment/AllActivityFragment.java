package com.sharpszhang.tog.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.sharpszhang.tog.Bean.ActivityBean;
import com.sharpszhang.tog.Bean.RestCode;
import com.sharpszhang.tog.R;
import com.sharpszhang.tog.activity.ActivityContentActivity;
import com.sharpszhang.tog.service.Service;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;
import com.xuexiang.xui.utils.WidgetUtils;

import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * “全部活动”页面
 */
public class AllActivityFragment extends Fragment implements View.OnClickListener {

    /**
     * 活动列表数据
     */
    private List<ActivityBean> posts = new ArrayList<>(16);

    /**
     * 布局
     */
    private SmartRefreshLayout refreshLayout;

    /**
     * 下拉刷新组件
     */
    private MaterialHeader header;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载布局
        View view = inflater.inflate(R.layout.layout_fragment, null);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        header = (MaterialHeader) refreshLayout.getRefreshHeader();
        // 数据展示视图
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        // 获取当前操作上下文
        AllActivityFragment context = this;

        // 获取数据
        //try {
        //    //initDataList();
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}
        // 初始化视图
        WidgetUtils.initRecyclerView(recyclerView);
        // RecyclerAdapter
        BaseRecyclerAdapter<ActivityBean> adapter = new BaseRecyclerAdapter<ActivityBean>(posts) {

            /**
             * 绑定数据到模板
             * @param holder viewHolder
             * @param position 当前位置索引
             * @param item 当前对象
             */
            @Override
            protected void bindData(@NonNull @NotNull RecyclerViewHolder holder, int position, ActivityBean item) {
                holder.text(R.id.activity_title, "活动" + position);
                holder.itemView.setOnClickListener(context);
            }

            /**
             * 设置数据填充模板
             * @param viewType
             * @return 模板ID
             */
            @Override
            protected int getItemLayoutId(int viewType) {
                return R.layout.template_activity;
            }
        };
        // 绑定Adapter
        recyclerView.setAdapter(adapter);

        // 设置下拉刷新监听
        refreshLayout.setOnRefreshListener(refreshLayout -> {
            // 刷新数据
            adapter.refresh(posts);
            // 完成刷新并成功
            refreshLayout.finishRefresh(true);
            // 判断当前数据是否为空
            if (posts == null || posts.size() <= 0) {
                // 如果数据为空则填充空白页
                refreshLayout.setRefreshContent(inflater.inflate(R.layout.empty_activity, null));
                return;
            }
            // 展示数据视图
            refreshLayout.setRefreshContent(recyclerView);


        });
        // 设置上滑加载监听
        refreshLayout.setOnLoadMoreListener(refreshLayout ->{
            // 获取新数据并添加到数据列
            for (int i = 0; i < 10; i++) {
                posts.add(new ActivityBean());
            }
            // 刷新数据
            adapter.refresh(posts);
            // 数据加载完成且成功
            refreshLayout.finishLoadMore(true);
        });
        // 初始化默认刷新数据
        refreshLayout.autoRefresh();
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void initDataList() throws UnsupportedEncodingException {
        //String request = "apipost_id=" + URLEncoder.encode("1e5880", "utf-8");
        //String request = "apipost_id=" + "1e5880";
        JSON json = Service.selectPosts("");
        if(json != null) {
            RestCode result = JSONArray.parseObject(json.toJSONString(), RestCode.class);
            posts =JSONArray.parseArray(result.getData().toString(), ActivityBean.class);
        }

    }

    @Override
    public void onClick(View view) {
        // 判断当前是否下拉、上滑状态
        if (!RefreshState.None.equals(refreshLayout.getState())) {
            return;
        }
        // 跳转到活动详情页
        startActivity(new Intent(this.getContext(), ActivityContentActivity.class));
    }
}