package com.interestcontent.liudeyu.contents.news;

import android.os.Bundle;

import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.zhouwei.rvadapterlib.base.Cell;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;

import java.util.List;

/**
 * Created by liudeyu on 2018/4/9.
 */

public class MyServerNewsFragment extends AbsFeedFragment {
    private static final String ITEM_TAB = "ITEM_TAB";
    private static final String UPLOAD_PARA = "UPLOAD_PARA";


    public static Bundle getBundle(String para, ItemTab itemTab) {
        Bundle bundle = new Bundle();
        bundle.putString(UPLOAD_PARA, para);
        bundle.putSerializable(ITEM_TAB, itemTab);
        return bundle;
    }


    @Override
    public void onRecyclerViewInitialized() {

    }

    @Override
    public void onPullRefresh() {

    }

    @Override
    public void onLoadMore() {

    }

    @Override
    protected List<Cell> getCells(List list) {
        return null;
    }
}
