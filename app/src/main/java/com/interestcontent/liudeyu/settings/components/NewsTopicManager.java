package com.interestcontent.liudeyu.settings.components;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.settings.utils.BestUrlSourceFilterUtil;
import com.pchmn.materialchips.model.ChipInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by liudeyu on 2018/3/7.
 */

public class NewsTopicManager {
    public static NewsTopicManager sNewsTopicManager;
    private List<String> mCurrentShowNewsTopicList;
    private List<Integer> mNewsItemIds;
    private Set<Integer> mNewsItemKeySets;
    private Set<String> deleteTopicSet = new HashSet<>();


    private NewsTopicManager() {
        getNewsItemTabKeys();
    }


    public boolean isNewsItemKey(int key) {
        return mNewsItemKeySets.contains(key);
    }

    public List<Integer> getNewsItemTabKeys() {
        mCurrentShowNewsTopicList = getNewsCatetory();
        mNewsItemIds = new ArrayList<>();
        mNewsItemKeySets = new HashSet<>();
        for (String t : mCurrentShowNewsTopicList) {
            mNewsItemIds.add(t.hashCode());
            mNewsItemKeySets.add(t.hashCode());
        }
        return mNewsItemIds;

    }


    public static synchronized NewsTopicManager getInstance() {
        if (sNewsTopicManager == null) {
            sNewsTopicManager = new NewsTopicManager();
        }
        return sNewsTopicManager;
    }


    public void setItemAtIndex(int position, String item) {
        List<String> list = getNewsCatetory();
        if (position < 0 || position >= list.size()) {
            return;
        }
        list.set(position, item);
        saveNewsCatetory(list);
    }

    public void moveItemPosition(int fromPosition, int toPosition) {
        List<String> list = getNewsCatetory();
        if (fromPosition < 0 || fromPosition >= list.size()) {
            return;
        }
        if (toPosition < 0 || toPosition >= list.size()) {
            return;
        }
        String a1 = list.remove(fromPosition);
        list.add(toPosition, a1);
//        这里为了性能着想，不去本地化sp,因为后面一定会调用setItemIndex那个最后回调
    }

    public List<String> deleteNewsCateory(int position) {
        List<String> topics = getNewsCatetory();
        if (position < 0 || position >= topics.size()) {
            return mCurrentShowNewsTopicList;
        }
        String reTopic = topics.remove(position);
        setNewsCategories(topics);
        return getNewsCatetory();
    }

    public List<String> getNewsCatetory() {
        if (mCurrentShowNewsTopicList != null) {
            return mCurrentShowNewsTopicList;
        }
        mCurrentShowNewsTopicList = new ArrayList<>();
        String tmp = SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.NEWS_SELECTED_CATEGORY_SP);
        if (TextUtils.isEmpty(tmp)) {
            mCurrentShowNewsTopicList = getDefaultCategories();
        } else {
            Gson gson = new Gson();
            mCurrentShowNewsTopicList = gson.fromJson(tmp, mCurrentShowNewsTopicList.getClass());
        }
        return mCurrentShowNewsTopicList;
    }


    /**
     * 返回默认的新闻主题
     */
    public List<String> getDefaultCategories() {
        List<String> aList = BestUrlSourceFilterUtil.getDefaultNewsTopic();
        return aList;
    }

    /**
     * 用户自定义产生的新闻标签
     */
    public void addCustomCreateTopicTag(@NonNull List<String> custom) {
        addNewsCatetory(custom);
        BestUrlSourceFilterUtil.addCustomCreateTopic(custom);
    }

    /**增加已有的新闻标签*/
    public void addNewsCatetory(@NonNull List<String> newsTopic) {
        List<String> oldCategory = getNewsCatetory();
        for (String tmp : newsTopic) {
            oldCategory.remove(tmp);
            oldCategory.add(0, tmp);
        }
        saveNewsCatetory(oldCategory);
    }

    public void setNewsCategories(List<String> newsCategories) {
        if (newsCategories == null || newsCategories.isEmpty()) {
            mCurrentShowNewsTopicList = new ArrayList<>();
        } else {
            mCurrentShowNewsTopicList = newsCategories;
        }
        saveNewsCatetory(mCurrentShowNewsTopicList);
    }

    private boolean saveNewsCatetory(List<String> allCategories) {
        Gson gson = new Gson();
        return SharePreferenceUtil.setStringPreference(MyApplication.sApplication,
                SpConstants.NEWS_SELECTED_CATEGORY_SP, gson.toJson(allCategories));

    }


    /**
     * 所有的默认主题
     */
    private List<String> getAllNewsTopicFilter() {
        return BestUrlSourceFilterUtil.getAllTopic();
    }

    /**
     * 未被选中的可以再选的主题
     */
    public List<String> getNotBeSelectedTopicLabel() {
        List<String> beSelectedTopic = getNewsCatetory();
        Set<String> allSelectedCatoriesSet = new HashSet<>();
        allSelectedCatoriesSet.addAll(beSelectedTopic);
        List<String> canBeSelectedCatories = getAllNewsTopicFilter();
        List<String> result = new ArrayList<>();
        for (String m : canBeSelectedCatories) {
            if (!allSelectedCatoriesSet.contains(m)) {
                result.add(m);
            }
        }
        return result;
    }

    public List<ChipInterface> getNotBeSelectedChipInterfaces() {
        List<ChipInterface> chipInterfaceList = new ArrayList<>();
        List<String> stringList = getNotBeSelectedTopicLabel();
        for (String t1 : stringList) {
            chipInterfaceList.add(new MyChipInterface(t1));
        }
        return chipInterfaceList;
    }

    public static class MyChipInterface implements ChipInterface {
        private String title;

        private MyChipInterface(String title) {
            this.title = title;
        }

        @Override
        public Object getId() {
            return title;
        }

        @Override
        public Uri getAvatarUri() {
            return null;
        }

        @Override
        public Drawable getAvatarDrawable() {
            return null;
        }

        @Override
        public String getLabel() {
            return title;
        }

        @Override
        public String getInfo() {
            return title;
        }
    }

}
