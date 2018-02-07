package com.interestcontent.liudeyu.base.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.interestcontent.liudeyu.contents.news.NewsMainFragment;
import com.interestcontent.liudeyu.contents.news.NewsTechnologyFragment;
import com.interestcontent.liudeyu.settings.SettingFragment;
import com.interestcontent.liudeyu.contents.weibo.WeiboMainFragment;
import com.interestcontent.liudeyu.contents.weibo.contents.MyWeiboMessageFragment;
import com.interestcontent.liudeyu.contents.weibo.contents.MyWeiboProfileFragment;
import com.interestcontent.liudeyu.contents.weibo.contents.MyWeiboSettingFragment;
import com.interestcontent.liudeyu.contents.weibo.feeds.WeiboFollowFragment;
import com.interestcontent.liudeyu.contents.weibo.feeds.WeiboHotFragment;

/**
 * Created by liudeyu on 2018/1/12.
 */

public class FragmentFactory {
    public static Fragment getFragmentByItemTab(ItemTab itemTab) {
        Bundle bundle = new Bundle();
        Fragment fragment = null;
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
                return new WeiboHotFragment();
            case ItemTab.WEIBO_SUB_MESSAGE:
                fragment = new MyWeiboMessageFragment();
                return fragment;
            case ItemTab.WEIBO_SUB_MY_WEIBO_PAGE:
                fragment = new MyWeiboProfileFragment();
                return fragment;
            case ItemTab.WEIBO_SUB_PERSON_SETTING:
                fragment = new MyWeiboSettingFragment();
                return fragment;
            case ItemTab.NEWS_TECHNOLEGE:
            case ItemTab.NEWS_GOOGLE:
            case ItemTab.NEWS_PHONE:
            case ItemTab.NEWS_APPLE:
            case ItemTab.NEWS_LIFE:
            case ItemTab.NEWS_PROGRAM:
            case ItemTab.NEWS_TENCENT:
                bundle = NewsTechnologyFragment.getTopicBundle(itemTab);
                fragment = new NewsTechnologyFragment();
                fragment.setArguments(bundle);
                return fragment;
        }
        return null;
    }
}
