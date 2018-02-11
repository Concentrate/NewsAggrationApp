package com.interestcontent.liudeyu.contents.zhihu.bean;

import com.interestcontent.liudeyu.base.baseBeans.FeedBaseBean;

import java.util.List;

/**
 * Created by liudeyu on 2018/2/11.
 */
public class StoriesBean extends FeedBaseBean {
    /**
     * images : ["https://pic1.zhimg.com/v2-4d9e250f4ab5bcc383323728295309a0.jpg"]
     * type : 0
     * id : 9670282
     * ga_prefix : 020715
     * title : 为什么部分中国女性不刮腋毛？
     */

    private int type;
    private int id;
    private String title;
    private List<String> images;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
