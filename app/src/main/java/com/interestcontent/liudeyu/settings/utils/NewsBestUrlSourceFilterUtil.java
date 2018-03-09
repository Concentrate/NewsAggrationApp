package com.interestcontent.liudeyu.settings.utils;

/**
 * Created by liudeyu on 2018/3/8.
 */

import android.text.TextUtils;

import com.google.gson.Gson;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 根据新闻话题，筛选出最适应的新闻源,并提供新闻tab标题
 */
public class NewsBestUrlSourceFilterUtil {
    public static Set<String> cctvTopicSet;
    public static Set<String> sinaSportSet;
    public static Set<String> qihuSet;
    public static Set<String> techNewsSet;

    public static String getBestUrlForTopic(String topic) {
        initAllNeedSets();
        if (cctvTopicSet.contains(topic)) {
        } else if (sinaSportSet.contains(topic)) {
        } else if (qihuSet.contains(topic)) {
            return Constants.NEWS_360_DOMAIN;
        } else if (techNewsSet.contains(topic)) {
            return Constants.NEWS_LEIFENG_NET_BASE;
        }

        return Constants.NEWS_360_DOMAIN;
    }

    private static void initAllNeedSets() {
        if (cctvTopicSet == null) {
            cctvTopicSet = new HashSet<>();
            String[] cctvArr = MyApplication.sApplication.getResources().getStringArray(R.array.news_360_news);
            cctvTopicSet.addAll(Arrays.asList(cctvArr));
        }
        if (sinaSportSet == null) {
            sinaSportSet = new HashSet<>();
            String[] cctvArr = MyApplication.sApplication.getResources().getStringArray(R.array.news_360search_news);
            sinaSportSet.addAll(Arrays.asList(cctvArr));
        }
        if (qihuSet == null) {
            qihuSet = new HashSet<>();
            String[] cctvArr = MyApplication.sApplication.getResources().getStringArray(R.array.news_360news2);
            qihuSet.addAll(Arrays.asList(cctvArr));
        }

        if (techNewsSet == null) {
            techNewsSet = new HashSet<>();
            String[] cctvArr = MyApplication.sApplication.getResources().getStringArray(R.array.news_leifeng_techno);
            techNewsSet.addAll(Arrays.asList(cctvArr));
        }
    }


    private static Set<String> getCustomCreateTagSet() {
        String tmp = SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.NEWS_CUSTOM_ADD_TAG_SP, "");
        Set<String> set = new HashSet<>();
        Gson gson = new Gson();
        if (!TextUtils.isEmpty(tmp)) {
            set = gson.fromJson(tmp, set.getClass());
        }
        return set;
    }

    /**
     * 自定义产生的topic
     */
    public static void addCustomCreateTopic(List<String> topic) {
        Set<String> set = getCustomCreateTagSet();
        set.addAll(topic);
        saveTopicSetToSp(set);
    }

    private static void saveTopicSetToSp(Set<String> set) {
        Gson gson = new Gson();
        SharePreferenceUtil.setStringPreference(MyApplication.sApplication, SpConstants.NEWS_CUSTOM_ADD_TAG_SP,
                gson.toJson(set));
    }

    public static void deleteCustomTopic(String tag) {
        Set<String> set = getCustomCreateTagSet();
        set.remove(tag);
        saveTopicSetToSp(set);
    }

    public static boolean isCustomCreateTopic(String topic) {
        Set<String> set = getCustomCreateTagSet();
        return set.contains(topic);
    }

    /**
     * 得到用户自定义产生的新闻标签
     */
    public static Set<String> getCustomCreateTopics() {
        return getCustomCreateTagSet();
    }

    public static List<String> getAllTopic() {
        initAllNeedSets();
//        为了去重
        List<String> allNewsList = new ArrayList<>();
        Set<String> allTopicSet = new HashSet<>();
        allTopicSet.addAll(techNewsSet);
        allTopicSet.addAll(cctvTopicSet);
        allTopicSet.addAll(sinaSportSet);
        allTopicSet.addAll(qihuSet);
        allTopicSet.addAll(getCustomCreateTagSet());
        allNewsList.addAll(allTopicSet);
        return allNewsList;
    }

    public static List<String> getDefaultNewsTopic() {
        String[] array = MyApplication.sApplication.getResources().getStringArray(R.array.news_topic_default);
        return new ArrayList<>(Arrays.asList(array));
    }

}
