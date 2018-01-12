package com.interestcontent.liudeyu.base.tabs;

import android.support.v4.app.Fragment;

import com.interestcontent.liudeyu.news.NewsMainFragment;
import com.interestcontent.liudeyu.settings.SettingFragment;
import com.interestcontent.liudeyu.weibo.WeiboFollowFragment;
import com.interestcontent.liudeyu.weibo.WeiboMainFragment;

/**
 * Created by liudeyu on 2018/1/12.
 */

public class FragmentFactory {
    public static Fragment getFragmentByItemTab(ItemTab itemTab) {
        switch (itemTab.getItemKey()) {
            case ItemTab.TAB_WEIBO:
                return new WeiboMainFragment();
            case ItemTab.TAB_NEWS:
                return new NewsMainFragment();
            case ItemTab.TAB_SETTINGS:
                return new SettingFragment();
            case ItemTab.WEIBO_SUB_FOLLOW:
                return new WeiboFollowFragment();
            case ItemTab.WEIBO_SUB_HOT:
                // TODO: 2018/1/12 模拟 ,下面三个数据源not done,模拟
                return new WeiboFollowFragment();
            case ItemTab.WEIBO_SUB_MESSAGE:
                return new WeiboFollowFragment();
            case ItemTab.WEIBO_SUB_PROFILE:
                return new WeiboFollowFragment();
        }
        return null;
    }
}
