package com.interestcontent.liudeyu.base.tabs;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liudeyu on 2018/1/12.
 */

public class BasePageAdapter extends FragmentPagerAdapter {

    private List<ItemTab> mItemTabs;
    private FragmentManager mFragmentManager;

    public BasePageAdapter(FragmentManager fragmentManager, List<ItemTab> itemTabs) {
        super(fragmentManager);
        mFragmentManager = fragmentManager;
        mItemTabs = itemTabs;
    }


    @Override
    public int getCount() {
        return mItemTabs.size();
    }

    @Override
    public Fragment getItem(int position) {
        if (position >= mItemTabs.size()) {
            throw new IllegalArgumentException("position can't be exceed mItemTabs size");
        }
        return FragmentFactory.getFragmentByItemTab(mItemTabs.get(position));
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mItemTabs.get(position).getTitle();
    }
}
