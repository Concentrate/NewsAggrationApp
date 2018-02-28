package com.interestcontent.liudeyu.contents.zhihu.fragments;

import android.support.design.widget.TabLayout;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;
import com.interestcontent.liudeyu.base.tabs.ItemTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/2/8.
 */

public class OpinionMainFragment extends AbsTopTabFragment {


    @Override
    protected List<ItemTab> provideItemTabs() {
        List<ItemTab> list = new ArrayList<>();
        int[] keys = getActivity().getResources().getIntArray(R.array.opinion_item_key);
        String[] tabNames = getActivity().getResources().getStringArray(R.array.opinion_item_tab_value);
        for (int i = 0; i < keys.length; i++) {
            list.add(new ItemTab(keys[i], tabNames[i]));
        }
        return list;
    }

    @Override
    protected int provideTabMode() {
        return TabLayout.MODE_FIXED;
    }

    @Override
    protected int viewpagerLimitNum() {
        return 2;
    }
}
