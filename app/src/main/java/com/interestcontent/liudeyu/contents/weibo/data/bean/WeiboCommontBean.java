package com.interestcontent.liudeyu.contents.weibo.data.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by liudeyu on 2017/12/31.
 */

public class WeiboCommontBean extends FeedBaseBean implements Serializable {

    /*
    * 返回值字段	字段类型	字段说明
created_at	string	评论创建时间
id	int64	评论的ID
text	string	评论的内容
source	string	评论的来源
user	object	评论作者的用户信息字段 详细
mid	string	评论的MID
idstr	string	字符串型的评论ID
status	object	评论的微博信息字段 详细
reply_comment	object	评论来源评论，当本评论属于对另一评论的回复时返回此字段
*/

    /**
     * created_at : Tue Jan 02 16:03:30 +0800 2018
     * id : 4191773775176206
     * rootid : 4191773775176206
     * floor_number : 1
     * text : oooo
     * disable_reply : 0
     * source_allowclick : 0
     * source_type : 1
     * source : <a href="http://app.weibo.com/t/feed/3R2ZKL" rel="nofollow">微博 HTML5 版</a>
     * user : {}
     * mid : 4191773775176206
     * idstr : 4191773775176206
     * status : {"created_at":"Tue Jan 02 15:15:03 +0800 2018","id":4191761582252424,"mid":"4191761582252424","idstr":"4191761582252424","can_edit":false,"text":"#笔经面经# 史上最短小的面经\u2014\u2014十分钟视频面面经分享！戳http://t.cn/RHl0jG4直接与LZ交流讨论！","textLength":92,"source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/6vtZb0\" rel=\"nofollow\">微博 weibo.com<\/a>","appid":780,"favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_ids":["8116e3ccly1fn217qae2jj20dc0awdgs"],"thumbnail_pic":"http://wx1.sinaimg.cn/thumbnail/8116e3ccly1fn217qae2jj20dc0awdgs.jpg","bmiddle_pic":"http://wx1.sinaimg.cn/bmiddle/8116e3ccly1fn217qae2jj20dc0awdgs.jpg","original_pic":"http://wx1.sinaimg.cn/large/8116e3ccly1fn217qae2jj20dc0awdgs.jpg","geo":null,"is_paid":false,"mblog_vip_type":0,"user":{},"reposts_count":0,"comments_count":0,"attitudes_count":0,"pending_approval_count":0,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"page_type":32,"hasActionTypeCard":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0,"more_info_type":0,"cardid":"star_047","positive_recom_flag":0,"gif_ids":"","is_show_bulletin":2,"comment_manage_info":{"comment_permission_type":-1,"approval_comment_type":0}}
     */

    private String created_at;
    private long id;
    private long rootid;
    private int floor_number;
    private String text;
    private int disable_reply;
    private int source_allowclick;
    private int source_type;
    private String source;
    private WeiboUserBean user;
    private String mid;
    private String idstr;
    @SerializedName(value = "status")
    private WeiboBean status;

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRootid() {
        return rootid;
    }

    public void setRootid(long rootid) {
        this.rootid = rootid;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getDisable_reply() {
        return disable_reply;
    }

    public void setDisable_reply(int disable_reply) {
        this.disable_reply = disable_reply;
    }

    public int getSource_allowclick() {
        return source_allowclick;
    }

    public void setSource_allowclick(int source_allowclick) {
        this.source_allowclick = source_allowclick;
    }

    public int getSource_type() {
        return source_type;
    }

    public void setSource_type(int source_type) {
        this.source_type = source_type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public WeiboUserBean getUser() {
        return user;
    }

    public void setUser(WeiboUserBean user) {
        this.user = user;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public WeiboBean getStatus() {
        return status;
    }

    public void setStatus(WeiboBean status) {
        this.status = status;
    }




}
