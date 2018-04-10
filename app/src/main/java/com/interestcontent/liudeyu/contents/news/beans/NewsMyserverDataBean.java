package com.interestcontent.liudeyu.contents.news.beans;

import com.interestcontent.liudeyu.base.baseBeans.FeedBaseBean;

/**
 * Created by liudeyu on 2018/4/9.
 */
public class NewsMyserverDataBean extends FeedBaseBean {
    /**
     * item_id : 6541977833206449000
     * title : 要上线自己小程序的朋友，千万要留意这些骗局
     * tag : news_tech
     * tag_url : news_tech
     * chinese_tag : 科技
     * source : 不插电的苹果
     * source_url : https://www.toutiao.com/group/6541977833206448647/
     * image_url : //p1.pstatp.com/list/190x124/pgc-image/1523172655697dd007a1945
     * media_url : /c/user/51473115773/
     * media_avatar_url : //p3.pstatp.com/large/658e001d3610dfac44f6
     * behot_time : 1523250102
     * group_id : 6541977833206448647
     * middle_image : http://p1.pstatp.com/list/pgc-image/1523172655697dd007a1945
     * comments_count : 0
     * video_duration_str : None
     * video_play_count : 0
     * video_id : 0
     */

    private long item_id;
    private String title;
    private String tag;
    private String tag_url;
    private String chinese_tag;
    private String source;
    private String source_url;
    private String image_url;
    private String media_url;
    private String media_avatar_url;
    private String behot_time;
    private String group_id;
    private String middle_image;
    private int comments_count;
    private String video_duration_str;
    private int video_play_count;
    private String video_id;

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag_url() {
        return tag_url;
    }

    public void setTag_url(String tag_url) {
        this.tag_url = tag_url;
    }

    public String getChinese_tag() {
        return chinese_tag;
    }

    public void setChinese_tag(String chinese_tag) {
        this.chinese_tag = chinese_tag;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getMedia_avatar_url() {
        return media_avatar_url;
    }

    public void setMedia_avatar_url(String media_avatar_url) {
        this.media_avatar_url = media_avatar_url;
    }

    public String getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(String behot_time) {
        this.behot_time = behot_time;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMiddle_image() {
        return middle_image;
    }

    public void setMiddle_image(String middle_image) {
        this.middle_image = middle_image;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public String getVideo_duration_str() {
        return video_duration_str;
    }

    public void setVideo_duration_str(String video_duration_str) {
        this.video_duration_str = video_duration_str;
    }

    public int getVideo_play_count() {
        return video_play_count;
    }

    public void setVideo_play_count(int video_play_count) {
        this.video_play_count = video_play_count;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }
}
