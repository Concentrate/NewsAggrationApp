package com.interestcontent.liudeyu;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.interestcontent.liudeyu.base.baseComponent.BaseActivity;
import com.interestcontent.liudeyu.base.baseUiKit.AdvanceViewPager;
import com.interestcontent.liudeyu.base.tabs.BasePageAdapter;
import com.interestcontent.liudeyu.base.tabs.ItemTab;
import com.interestcontent.liudeyu.base.utils.BrightnessUtil;
import com.interestcontent.liudeyu.base.utils.RamUtil;
import com.interestcontent.liudeyu.contents.search.SearchActivity;
import com.interestcontent.liudeyu.contents.weibo.data.WeiboLoginManager;
import com.interestcontent.liudeyu.settings.ThemeDataManager;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liudeyu on 2017/12/23.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    AdvanceViewPager mViewPager;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    private int mNewsTab = 0;

    @Override
    protected boolean isUseFullAScreen() {
        return false;
    }

    @Override
    protected boolean isUseToolBar() {
        return true;
    }

    @Override
    protected boolean useImmerseMode() {
        return true;
    }

    @Override
    protected View getResourceLayout() {
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        return view;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initUiData();
        initOtherData();
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
        mViewPager.setCanScroll(false);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSearchBarState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
        setSearchBarState(mViewPager.getCurrentItem());

    }

    @Override
    protected int provideToolbarColor() {
        return ThemeDataManager.getInstance().getThemeColorInt();
    }

    private void setSearchBarState(int position) {
        if (position == mNewsTab) {
            mToolbar.setVisibility(View.VISIBLE);
            addSearchBar();

        } else {
            mToolbar.setVisibility(View.GONE);
        }
    }

    private void addSearchBar() {
        View view = LayoutInflater.from(this).inflate(R.layout.search_bar_layout, null);
        mBackButton.setVisibility(View.GONE);
        mToolbarCustomContainer.removeAllViews();
        mToolbarCustomContainer.addView(view);
        TextView search = view.findViewById(R.id.sear_tv);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchActivity.start(MainActivity.this);
            }
        });

    }

    private void initOtherData() {
        AuthInfo authInfo = WeiboLoginManager.getInstance().getAuthInfo();
        WbSdk.install(this, authInfo);
    }


    private void initUiData() {
        List<ItemTab> itemTabs = new ArrayList<>();
        String[] nameArrays = getResources().getStringArray(R.array.main_tabs_title);
        int[] itemKey = getResources().getIntArray(R.array.main_tabs_key);
        int[] iconArray = new int[]{
                R.drawable.tab_news_selector, R.drawable.tab_videos_selector, R.drawable.tab_opinion_selector, R.drawable.tab_weibo_selector, R.drawable.tab_setting_selector
        };
        for (int i = 0; i < nameArrays.length; i++) {
            itemTabs.add(new ItemTab(itemKey[i], iconArray[i], nameArrays[i]));
        }
        mViewPager.setAdapter(new BasePageAdapter(getSupportFragmentManager(), itemTabs));
        if (RamUtil.getMaxMemoryCanGet() > 192) {
            mViewPager.setOffscreenPageLimit(3);
        } else {
            mViewPager.setOffscreenPageLimit(2);
        }
        mTabLayout.setupWithViewPager(mViewPager);
        for (int i = 0; i < itemTabs.size(); i++) {
            mTabLayout.getTabAt(i).setIcon(itemTabs.get(i).getResourceId());
        }
    }


    @Override
    protected int getStatusBarColor() {
        return ThemeDataManager.getInstance().getThemeColorInt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTabLayout.setTabTextColors(getResources().getColor(R.color.md_blue_grey_200), ThemeDataManager.getInstance().getThemeColorInt());
        BrightnessUtil.saveBrightnessState(this);

    }

    /**
     * 这里跟验证登录接入第三方sdk密切相关
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (WeiboLoginManager.getInstance().getSsoHandler() != null) {
            WeiboLoginManager.getInstance().getSsoHandler().authorizeCallBack(requestCode, resultCode, data);
        }
    }

    public void setTabLayoutVisible(int visiblety) {
        mTabLayout.setVisibility(visiblety);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
