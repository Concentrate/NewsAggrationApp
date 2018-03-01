package com.interestcontent.liudeyu.contents.videos.components;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.google.gson.Gson;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.thread.TaskManager;
import com.interestcontent.liudeyu.contents.videos.beans.VideoBean;
import com.interestcontent.liudeyu.contents.videos.beans.VideoRequest;
import com.interestcontent.liudeyu.contents.videos.cells.VideoCell;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by liudeyu on 2018/2/12.
 */

public class VideoBaseFeedFragment extends AbsFeedFragment {
    private static final int WHAT_INT = 18;
    private static final String RE_URL = "RE_URL".toLowerCase();
    private String mNextReUrl = Constants.VIDEO_REQUEST_LIST_API;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.obj instanceof Exception) {
                onDataError();
                return;
            }
            switch (msg.what) {
                case WHAT_INT:
                    VideoRequest request = (VideoRequest) msg.obj;
                    onDataSuccess(request);
                    break;
            }
        }
    };


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mNextReUrl = bundle.getString(RE_URL);
        }

    }

    private void onDataError() {
        mBaseAdapter.hideLoadMore();
        setRefreshing(false);
    }

    private void onDataSuccess(VideoRequest request) {
        mNextReUrl = request.getNextPageUrl();
        if (request.getItemList() == null || request.getItemList().isEmpty()) {
            return;
        }
        mBaseAdapter.addAll(getCells(request.getItemList()));
        mBaseAdapter.hideLoadMore();
        setRefreshing(false);
    }

    @Override
    public void onRecyclerViewInitialized() {
        startRequestData(mNextReUrl);
        HorizontalDividerItemDecoration itemDecoration = new HorizontalDividerItemDecoration.Builder(getContext())
                .margin(SizeUtils.dp2px(10))
                .size(SizeUtils.dp2px(8)).colorResId(R.color.md_grey_100).build();
        mRecyclerView.addItemDecoration(itemDecoration);
    }

    private void startRequestData(String url) {
        TaskManager.inst().commit(mHandler, new Callable<VideoRequest>() {
            @Override
            public VideoRequest call() throws Exception {
                String tmp = OkHttpUtils.get().url(mNextReUrl).build().execute().body().string();
                Gson gson = new Gson();
                return gson.fromJson(tmp, VideoRequest.class);

            }
        }, WHAT_INT);
        mBaseAdapter.showLoading();
    }

    @Override
    public void onPullRefresh() {
        startRequestData(mNextReUrl = Constants.VIDEO_REQUEST_LIST_API);
    }

    @Override
    public void onLoadMore() {
        startRequestData(mNextReUrl);
    }

    @Override
    protected List<Cell> getCells(List list) {
        List<Cell> cellList = new ArrayList<>();
        List<VideoBean> beans = list;
        for (VideoBean videoBean : beans) {
            if ("video".equals(videoBean.getType())) {
                cellList.add(new VideoCell(videoBean, this));
            }
        }
        return cellList;
    }
}
