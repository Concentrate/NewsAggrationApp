package com.interestcontent.liudeyu.base.tabs;

import android.support.v4.app.Fragment;

import com.interestcontent.liudeyu.news.NewsMainFragment;
import com.interestcontent.liudeyu.news.NewsTechnologyFragment;
import com.interestcontent.liudeyu.settings.SettingFragment;
import com.interestcontent.liudeyu.weibo.WeiboMainFragment;
import com.interestcontent.liudeyu.weibo.feeds.WeiboFollowFragment;
import com.interestcontent.liudeyu.weibo.feeds.WeiboHotFragment;
import com.interestcontent.liudeyu.weibo.contents.MyWeiboMessageFragment;
import com.interestcontent.liudeyu.weibo.contents.MyWeiboProfileFragment;
import com.interestcontent.liudeyu.weibo.contents.MyWeiboSettingFragment;

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
                return new WeiboHotFragment();
            case ItemTab.WEIBO_SUB_MESSAGE:
                return new MyWeiboMessageFragment();
            case ItemTab.WEIBO_SUB_MY_WEIBO_PAGE:
                return new MyWeiboProfileFragment();
            case ItemTab.WEIBO_SUB_PERSON_SETTING:
                return new MyWeiboSettingFragment();
            case ItemTab.NEWS_TECHNOLEGE:
                return new NewsTechnologyFragment();
        }
        return null;
    }
}
