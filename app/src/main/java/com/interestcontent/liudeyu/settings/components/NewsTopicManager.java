package com.interestcontent.liudeyu.settings.components;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/3/7.
 */

public class NewsTopicManager {
    public static NewsTopicManager sNewsTopicManager;
    private List<String> mList;


    private NewsTopicManager() {

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
            return mList;
        }
        topics.remove(position);
        setNewsCategories(topics);
        return getNewsCatetory();
    }

    public List<String> getNewsCatetory() {
        if (mList != null) {
            return mList;
        }
        mList = new ArrayList<>();
        String tmp = SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.NEWS_CATEGORY_SP);
        if (TextUtils.isEmpty(tmp)) {
            mList = getDefaultCategories();
        } else {
            Gson gson = new Gson();
            mList = gson.fromJson(tmp, mList.getClass());
        }
        return mList;
    }


    /**
     * 返回默认的新闻主题
     */
    public List<String> getDefaultCategories() {
        List<String> aList = new ArrayList<>();
        String[] topic = MyApplication.sApplication.getResources().getStringArray(R.array.news_top_tab_name);
        for (String t : topic) {
            aList.add(t);
        }
        return aList;
    }

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
            mList = new ArrayList<>();
        } else {
            mList = newsCategories;
        }
        saveNewsCatetory(mList);
    }

    private boolean saveNewsCatetory(List<String> allCategories) {
        Gson gson = new Gson();
        return SharePreferenceUtil.setStringPreference(MyApplication.sApplication,
                SpConstants.NEWS_CATEGORY_SP, gson.toJson(allCategories));

    }


}
