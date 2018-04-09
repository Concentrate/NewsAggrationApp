package com.interestcontent.liudeyu.base.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.contents.news.NewsMainFragment;
import com.interestcontent.liudeyu.contents.videos.components.VideoBaseFeedFragment;
import com.interestcontent.liudeyu.contents.videos.components.VideoMainFragments;
import com.interestcontent.liudeyu.contents.weibo.WeiboMainFragment;
import com.interestcontent.liudeyu.contents.weibo.contents.MyWeiboMessageFragment;
import com.interestcontent.liudeyu.contents.weibo.contents.MyWeiboProfileFragment;
import com.interestcontent.liudeyu.contents.weibo.contents.MyWeiboSettingFragment;
import com.interestcontent.liudeyu.contents.weibo.feeds.WeiboDiscovoryFragment;
import com.interestcontent.liudeyu.contents.weibo.feeds.WeiboFollowFragment;
import com.interestcontent.liudeyu.contents.zhihu.fragments.OpinionMainFragment;
import com.interestcontent.liudeyu.contents.zhihu.fragments.ZhihuJournalListFragment;
import com.interestcontent.liudeyu.contents.zhihu.fragments.ZhihuThemeFragment;
import com.interestcontent.liudeyu.settings.SettingFragment;
import com.interestcontent.liudeyu.settings.components.NewsTopicManager;

/**
 * Created by liudeyu on 2018/1/12.
 */

public class FragmentFactory {
    public static Fragment getFragmentByItemTab(ItemTab itemTab) {
        Bundle bundle = new Bundle();
        String url = "";
        Fragment fragment = null;
        if (NewsTopicManager.getInstance().isNewsItemKey(itemTab.getItemKey())) {
            fragment=NewsTopicManager.getInstance().getNewsFragment(itemTab);
            return fragment;
        }
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
                return new WeiboDiscovoryFragment();
            case ItemTab.WEIBO_SUB_MESSAGE:
                fragment = new MyWeiboMessageFragment();
                return fragment;
            case ItemTab.WEIBO_SUB_MY_WEIBO_PAGE:
                fragment = new MyWeiboProfileFragment();
                return fragment;
            case ItemTab.WEIBO_SUB_PERSON_SETTING:
                fragment = new MyWeiboSettingFragment();
                return fragment;
            case ItemTab.OPINION_ZHIHU_NEW_LEASTEST:
                return new ZhihuJournalListFragment();
            case ItemTab.TAB_MAIN_OPINIONS:
                return new OpinionMainFragment();
            case ItemTab.TAB_ZHIHU_THEME:
                return new ZhihuThemeFragment();
            case ItemTab.VIDEO_MAIN_TAB:
                return new VideoMainFragments();
            case ItemTab.VIDEO_RECOMEND_TAB:
                url = Constants.VIDEO_RECOMAND_LIST_API;
                bundle.putString(VideoBaseFeedFragment.ORIGIN_URL, url);
                fragment = new VideoBaseFeedFragment();
                fragment.setArguments(bundle);
                return fragment;
            case ItemTab.VIDEO_HOT_TAB:
                url = Constants.VIDEO_HOT_API;
                bundle.putString(VideoBaseFeedFragment.ORIGIN_URL, url);
                fragment = new VideoBaseFeedFragment();
                fragment.setArguments(bundle);
                return fragment;


        }
        return null;
    }
}
