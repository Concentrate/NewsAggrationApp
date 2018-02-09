package com.interestcontent.liudeyu.contents.zhihu.fragments;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AlphaAnimation;

import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.dataManager.FeedDataManager;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.base.thread.TaskManager;
import com.interestcontent.liudeyu.contents.zhihu.bean.ZhihuThemeRequest;
import com.interestcontent.liudeyu.contents.zhihu.cells.ZhihuThemeCell;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by liudeyu on 2018/2/9.
 */

public class ZhihuThemeFragment extends AbsFeedFragment<ZhihuThemeRequest.ThemeBean> {

    private static final String TAG = "ZhihuThemeFragment";
    private int what = 45;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == what) {
                if (msg.obj instanceof Exception) {
                    Logger.d(TAG, "query data error");
                } else {
                    onQuerySuccess((ZhihuThemeRequest) msg.obj);
                }
            }
            hideLoadMore();
            setRefreshing(false);

        }
    };

    private void onQuerySuccess(ZhihuThemeRequest obj) {
        if (obj != null && !obj.getThemeBeanList().isEmpty()) {
            mBaseAdapter.setData(getCells(obj.getThemeBeanList()));
        }
    }

    @Override
    public void onRecyclerViewInitialized() {
        initRecycleViews();
        startRequetData(false);

    }

    private void initRecycleViews() {
        mRecyclerView.setAnimation(new AlphaAnimation(0, 1.0f));
    }

    private void startRequetData(final boolean reflash) {
        TaskManager.inst().commit(mHandler, new Callable() {
            @Override
            public Object call() throws Exception {
                return FeedDataManager.getInstance().getRequest(reflash, ItemTab.TAB_ZHIHU_THEME,
                        Constants.ZHIHU_THEME_LIST, ZhihuThemeRequest.class);
            }
        }, what);
    }

    @Override
    public void onPullRefresh() {
        startRequetData(true);
    }

    @Override
    public void onLoadMore() {
        mBaseAdapter.hideLoadMore();
    }

    @Override
    protected List<Cell> getCells(List<ZhihuThemeRequest.ThemeBean> list) {
        List<Cell> list1 = new ArrayList<>();
        for (ZhihuThemeRequest.ThemeBean themeBean : list) {
            list1.add(new ZhihuThemeCell(themeBean, this));
        }
        return list1;
    }

    @Override
    protected RecyclerView.LayoutManager initLayoutManger() {
        return new GridLayoutManager(getContext(), 2);
    }
}
