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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 根据新闻话题，筛选出最适应的新闻源,并提供新闻tab标题
 */
public class NewsTopicSelectUtils {
    public static Set<String> qihuSet;
    public static Set<String> techNewsSet;
    public static Map<String, String> myServersTitileAndCategoriesMap = new HashMap<>();

    static {
        myServersTitileAndCategoriesMap.put("推荐", "all");
        myServersTitileAndCategoriesMap.put("热点", "news_hot");
        myServersTitileAndCategoriesMap.put("探索", "news_discovery");
        myServersTitileAndCategoriesMap.put("美文", "news_essay");
        myServersTitileAndCategoriesMap.put("科技", "news_tech");
        myServersTitileAndCategoriesMap.put("社会", "news_society");
    }

    public static enum TOPIC_SOURCE {
        NEWS_IDATA_API, NEWS_MYSERVER_API
    }

    public static String getIDataApiBestUrlForTopic(String topic) {
        initAllNeedSets();
        if (techNewsSet.contains(topic)) {
            return Constants.NEWS_LEIFENG_NET_BASE;
        }

        return Constants.NEWS_360_DOMAIN;
    }


    /**
     * 自己服务器，话题对应的上传参数
     */
    public static String getTopicParameter(String topic) {
        return myServersTitileAndCategoriesMap.get(topic);
    }

    private static void initAllNeedSets() {
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


    public static TOPIC_SOURCE getTopicSourceFragmentTag(String topic) {
        initAllNeedSets();
        // TODO: 2018/4/9 这边还未加入 
        if (false &&myServersTitileAndCategoriesMap.keySet().contains(topic)) {
            return TOPIC_SOURCE.NEWS_MYSERVER_API;
        }
        return TOPIC_SOURCE.NEWS_IDATA_API;
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
