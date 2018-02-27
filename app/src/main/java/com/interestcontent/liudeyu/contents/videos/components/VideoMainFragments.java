package com.interestcontent.liudeyu.contents.videos.components;

import com.interestcontent.liudeyu.base.baseComponent.AbsTopTabFragment;
import com.interestcontent.liudeyu.base.tabs.ItemTab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liudeyu on 2018/2/12.
 */

public class VideoMainFragments extends AbsTopTabFragment {
    @Override
    protected List<ItemTab> provideItemTabs() {
        int[] itemTabKeys = new int[]{
                ItemTab.VIDEO_RECOMEND_TAB
        };
        String[] names = new String[]{
                "推荐"
        };
        List<ItemTab> itemTabs = new ArrayList<>();
        for (int i = 0; i < itemTabKeys.length; i++) {
            itemTabs.add(new ItemTab(itemTabKeys[i], names[i]));
        }
        return itemTabs;
    }
}
