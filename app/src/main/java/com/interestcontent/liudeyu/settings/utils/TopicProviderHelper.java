package com.interestcontent.liudeyu.settings.utils;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.interestcontent.liudeyu.R;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.pchmn.materialchips.model.ChipInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/3/7.
 */

public class TopicProviderHelper {

    public List<ChipInterface> getNewsTopicFilter() {
        List<ChipInterface> list = new ArrayList<>();
        String[] topic = MyApplication.sApplication.getResources().getStringArray(R.array.news_top_tab_name);
        for (String t : topic) {
            list.add(new NewsTopic(t));
        }
        return list;

    }


    private static class NewsTopic implements ChipInterface {

        private String title;

        private NewsTopic(String title) {
            this.title = title;
        }

        @Override
        public Object getId() {
            return null;
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
