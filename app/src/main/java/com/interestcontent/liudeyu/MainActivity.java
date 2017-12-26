package com.interestcontent.liudeyu;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.interestcontent.liudeyu.base.baseUiKit.AdvanceViewPager;
import com.interestcontent.liudeyu.news.NewsMainFragment;
import com.interestcontent.liudeyu.settings.SettingFragment;
import com.interestcontent.liudeyu.weibo.WeiboMainFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2017/12/23.
 */

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    AdvanceViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.setCurrentItem(0);
        mViewPager.setCanScroll(false);
    }



    private void initData() {
        List<Fragment> fragments = mFragments;
        fragments.add(new WeiboMainFragment());
        fragments.add(new NewsMainFragment());
        fragments.add(new SettingFragment());
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabTextColors(getResources().getColor(R.color.md_blue_grey_200), getResources().getColor(R.color
                .md_black_1000));
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.md_orange_A700));

        mTabLayout.getTabAt(0).setIcon(R.drawable.tab_weibo_selector)
                .setText(R.string.tab_weibo);
        mTabLayout.getTabAt(1).setIcon(R.drawable.tab_news_selector)
                .setText(R.string.tab_news);
        mTabLayout.getTabAt(2).setIcon(R.drawable.tab_setting_selector)
                .setText(R.string.tab_settings);

    }
}
