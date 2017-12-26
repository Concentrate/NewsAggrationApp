package com.interestcontent.liudeyu.base.baseUiKit;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.interestcontent.liudeyu.R;

import java.util.List;

/**
 * Created by liudeyu on 2017/12/25.
 */

public abstract class AbsTopTabFragment extends AbsFragment {

    TabLayout mTabLayout;
    AdvanceViewPager mViewPager;
    private List<Fragment> mFragments;
    private List<String> mtitles;

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
    }

    protected void initView(View view1) {
        mTabLayout = view1.findViewById(R.id.tab_layout);
        mViewPager = view1.findViewById(R.id.viewpager);
    }



    protected void initData() {
        mFragments = provideContents();
        mtitles = provideTitles();
        if(mFragments==null||mtitles==null){
            return;
        }
        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mtitles.get(position);
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(viewpagerLimitNum());
        mTabLayout.setSelectedTabIndicatorColor(setTabSelectedColor());
        mTabLayout.setSelectedTabIndicatorHeight(getActivity().getResources().getDimensionPixelSize(R.dimen.selected_tab_color_height));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setBackgroundColor(getActivity().getResources().getColor(R.color.md_red_A100));
        mTabLayout.setTabTextColors(getActivity().getResources().getColor(R.color.md_white_1000),getActivity()
        .getResources().getColor(R.color.md_yellow_100));
    }


    protected int getFragmentResourseLayout() {
        return R.layout.fragment_top_tab_main_layout;
    }

    abstract protected List<Fragment> provideContents();

    abstract protected List<String> provideTitles();

    protected int viewpagerLimitNum() {
        return 1;
    }

    protected int setTabSelectedColor() {
        return getActivity().getResources().getColor(R.color.md_deep_orange_200);
    }

}
