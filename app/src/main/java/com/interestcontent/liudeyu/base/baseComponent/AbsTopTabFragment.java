package com.interestcontent.liudeyu.base.baseComponent;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.commonlib.components.AbsActivity;
import com.example.commonlib.components.AbsFragment;
import com.example.commonlib.components.LifeCycleMonitor;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseUiKit.AdvanceViewPager;
import com.interestcontent.liudeyu.base.tabs.BasePageAdapter;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.settings.ThemeDataManager;

import java.util.List;

/**
 * Created by liudeyu on 2017/12/25.
 */

/**
 * 第一层具有tablayout和内嵌ViewPager的Fragment
 */
public abstract class AbsTopTabFragment extends AbsFragment implements LifeCycleMonitor {

    protected TabLayout mTabLayout;
    protected AdvanceViewPager mViewPager;
    protected ViewGroup mEmptyContainer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = LayoutInflater.from(getActivity()).inflate(getFragmentResourseLayout(), null);
        return view1;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
        Activity activity = getActivity();
        //注册上层Activity监听
        if (activity instanceof AbsActivity) {
            ((AbsActivity) activity).registerLifeCycleMonitor(this);
        }
    }

    protected void initView(View view1) {
        mTabLayout = view1.findViewById(R.id.tab_layout);
        mViewPager = view1.findViewById(R.id.viewpager);
        mEmptyContainer = view1.findViewById(R.id.empty_view);
    }

    protected void hideContentAndShowEmptyView() {
        mTabLayout.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
        mEmptyContainer.setVisibility(View.VISIBLE);
    }

    protected void showContentHideEmptyView() {
        mEmptyContainer.setVisibility(View.GONE);
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }

    protected int provideInitShowItem() {
        return 0;
    }

    protected abstract List<ItemTab> provideItemTabs();

    protected void initData() {
        mViewPager.setAdapter(new BasePageAdapter(getChildFragmentManager(), provideItemTabs()));
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(viewpagerLimitNum());
        mTabLayout.setSelectedTabIndicatorColor(setTabSelectedColor());
        mTabLayout.setSelectedTabIndicatorHeight(getActivity().getResources().getDimensionPixelSize(R.dimen.selected_tab_color_height));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabLayout.setTabMode(provideTabMode());
        mTabLayout.setTabTextColors(getActivity().getResources().getColor(R.color.md_white_1000), getActivity()
                .getResources().getColor(R.color.md_yellow_100));
        mViewPager.setCurrentItem(provideInitShowItem());
    }

    @Override
    public void onResume() {
        super.onResume();
        mTabLayout.setBackgroundColor(ThemeDataManager.getInstance().getThemeColorInt());

    }

    protected int provideTabMode() {
        return TabLayout.MODE_SCROLLABLE;
    }

    protected int getFragmentResourseLayout() {
        return R.layout.fragment_top_tab_main_layout;
    }


    protected int viewpagerLimitNum() {
        return 1;
    }

    protected int setTabSelectedColor() {
        return getActivity().getResources().getColor(R.color.md_deep_orange_200);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Activity activity = getActivity();
        if (activity instanceof AbsActivity) {
            ((AbsActivity) activity).unregisterLifeCycleMonitor(this);
        }
    }


    public void setTabLayoutVisible(int visiblety) {
        mTabLayout.setVisibility(visiblety);
    }

    @Override
    public void onTopFragmentUserVisibleHint(boolean visible) {

    }

    @Override
    public boolean onTopBackPressed() {
        return false;
    }
}
