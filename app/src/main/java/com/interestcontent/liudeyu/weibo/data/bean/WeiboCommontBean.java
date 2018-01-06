package com.interestcontent.liudeyu.weibo.data.bean;

import java.util.List;

/**
 * Created by liudeyu on 2017/12/31.
 */

public class WeiboCommontBean {

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
    private StatusBean status;

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

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }



    public static class StatusBean {
        /**
         * created_at : Tue Jan 02 15:15:03 +0800 2018
         * id : 4191761582252424
         * mid : 4191761582252424
         * idstr : 4191761582252424
         * can_edit : false
         * text : #笔经面经# 史上最短小的面经——十分钟视频面面经分享！戳http://t.cn/RHl0jG4直接与LZ交流讨论！
         * textLength : 92
         * source_allowclick : 0
         * source_type : 1
         * source : <a href="http://app.weibo.com/t/feed/6vtZb0" rel="nofollow">微博 weibo.com</a>
         * appid : 780
         * favorited : false
         * truncated : false
         * in_reply_to_status_id :
         * in_reply_to_user_id :
         * in_reply_to_screen_name :
         * pic_ids : ["8116e3ccly1fn217qae2jj20dc0awdgs"]
         * thumbnail_pic : http://wx1.sinaimg.cn/thumbnail/8116e3ccly1fn217qae2jj20dc0awdgs.jpg
         * bmiddle_pic : http://wx1.sinaimg.cn/bmiddle/8116e3ccly1fn217qae2jj20dc0awdgs.jpg
         * original_pic : http://wx1.sinaimg.cn/large/8116e3ccly1fn217qae2jj20dc0awdgs.jpg
         * geo : null
         * is_paid : false
         * mblog_vip_type : 0
         * user : {}
         * reposts_count : 0
         * comments_count : 0
         * attitudes_count : 0
         * pending_approval_count : 0
         * isLongText : false
         * mlevel : 0
         * visible : {"type":0,"list_id":0}
         * biz_feature : 0
         * page_type : 32
         * hasActionTypeCard : 0
         * darwin_tags : []
         * hot_weibo_tags : []
         * text_tag_tips : []
         * userType : 0
         * more_info_type : 0
         * cardid : star_047
         * positive_recom_flag : 0
         * gif_ids :
         * is_show_bulletin : 2
         * comment_manage_info : {"comment_permission_type":-1,"approval_comment_type":0}
         */

        private String created_at;
        private long id;
        private String mid;
        private String idstr;
        private boolean can_edit;
        private String text;
        private int textLength;
        private int source_allowclick;
        private int source_type;
        private String source;
        private int appid;
        private boolean favorited;
        private boolean truncated;
        private String in_reply_to_status_id;
        private String in_reply_to_user_id;
        private String in_reply_to_screen_name;
        private String thumbnail_pic;
        private String bmiddle_pic;
        private String original_pic;
        private Object geo;
        private boolean is_paid;
        private int mblog_vip_type;
        private WeiboUserBean user;
        private int reposts_count;
        private int comments_count;
        private int attitudes_count;
        private int pending_approval_count;
        private boolean isLongText;
        private int mlevel;
        private VisibleBean visible;
        private int biz_feature;
        private int page_type;
        private int hasActionTypeCard;
        private int userType;
        private int more_info_type;
        private String cardid;
        private int positive_recom_flag;
        private String gif_ids;
        private int is_show_bulletin;
        private CommentManageInfoBean comment_manage_info;
        private List<String> pic_ids;
        private List<?> darwin_tags;
        private List<?> hot_weibo_tags;
        private List<?> text_tag_tips;

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

        public boolean isCan_edit() {
            return can_edit;
        }

        public void setCan_edit(boolean can_edit) {
            this.can_edit = can_edit;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getTextLength() {
            return textLength;
        }

        public void setTextLength(int textLength) {
            this.textLength = textLength;
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

        public int getAppid() {
            return appid;
        }

        public void setAppid(int appid) {
            this.appid = appid;
        }

        public boolean isFavorited() {
            return favorited;
        }

        public void setFavorited(boolean favorited) {
            this.favorited = favorited;
        }

        public boolean isTruncated() {
            return truncated;
        }

        public void setTruncated(boolean truncated) {
            this.truncated = truncated;
        }

        public String getIn_reply_to_status_id() {
            return in_reply_to_status_id;
        }

        public void setIn_reply_to_status_id(String in_reply_to_status_id) {
            this.in_reply_to_status_id = in_reply_to_status_id;
        }

        public String getIn_reply_to_user_id() {
            return in_reply_to_user_id;
        }

        public void setIn_reply_to_user_id(String in_reply_to_user_id) {
            this.in_reply_to_user_id = in_reply_to_user_id;
        }

        public String getIn_reply_to_screen_name() {
            return in_reply_to_screen_name;
        }

        public void setIn_reply_to_screen_name(String in_reply_to_screen_name) {
            this.in_reply_to_screen_name = in_reply_to_screen_name;
        }

        public String getThumbnail_pic() {
            return thumbnail_pic;
        }

        public void setThumbnail_pic(String thumbnail_pic) {
            this.thumbnail_pic = thumbnail_pic;
        }

        public String getBmiddle_pic() {
            return bmiddle_pic;
        }

        public void setBmiddle_pic(String bmiddle_pic) {
            this.bmiddle_pic = bmiddle_pic;
        }

        public String getOriginal_pic() {
            return original_pic;
        }

        public void setOriginal_pic(String original_pic) {
            this.original_pic = original_pic;
        }

        public Object getGeo() {
            return geo;
        }

        public void setGeo(Object geo) {
            this.geo = geo;
        }

        public boolean isIs_paid() {
            return is_paid;
        }

        public void setIs_paid(boolean is_paid) {
            this.is_paid = is_paid;
        }

        public int getMblog_vip_type() {
            return mblog_vip_type;
        }

        public void setMblog_vip_type(int mblog_vip_type) {
            this.mblog_vip_type = mblog_vip_type;
        }

        public WeiboUserBean getUser() {
            return user;
        }

        public void setUser(WeiboUserBean user) {
            this.user = user;
        }

        public int getReposts_count() {
            return reposts_count;
        }

        public void setReposts_count(int reposts_count) {
            this.reposts_count = reposts_count;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public int getAttitudes_count() {
            return attitudes_count;
        }

        public void setAttitudes_count(int attitudes_count) {
            this.attitudes_count = attitudes_count;
        }

        public int getPending_approval_count() {
            return pending_approval_count;
        }

        public void setPending_approval_count(int pending_approval_count) {
            this.pending_approval_count = pending_approval_count;
        }

        public boolean isIsLongText() {
            return isLongText;
        }

        public void setIsLongText(boolean isLongText) {
            this.isLongText = isLongText;
        }

        public int getMlevel() {
            return mlevel;
        }

        public void setMlevel(int mlevel) {
            this.mlevel = mlevel;
        }

        public VisibleBean getVisible() {
            return visible;
        }

        public void setVisible(VisibleBean visible) {
            this.visible = visible;
        }

        public int getBiz_feature() {
            return biz_feature;
        }

        public void setBiz_feature(int biz_feature) {
            this.biz_feature = biz_feature;
        }

        public int getPage_type() {
            return page_type;
        }

        public void setPage_type(int page_type) {
            this.page_type = page_type;
        }

        public int getHasActionTypeCard() {
            return hasActionTypeCard;
        }

        public void setHasActionTypeCard(int hasActionTypeCard) {
            this.hasActionTypeCard = hasActionTypeCard;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getMore_info_type() {
            return more_info_type;
        }

        public void setMore_info_type(int more_info_type) {
            this.more_info_type = more_info_type;
        }

        public String getCardid() {
            return cardid;
        }

        public void setCardid(String cardid) {
            this.cardid = cardid;
        }

        public int getPositive_recom_flag() {
            return positive_recom_flag;
        }

        public void setPositive_recom_flag(int positive_recom_flag) {
            this.positive_recom_flag = positive_recom_flag;
        }

        public String getGif_ids() {
            return gif_ids;
        }

        public void setGif_ids(String gif_ids) {
            this.gif_ids = gif_ids;
        }

        public int getIs_show_bulletin() {
            return is_show_bulletin;
        }

        public void setIs_show_bulletin(int is_show_bulletin) {
            this.is_show_bulletin = is_show_bulletin;
        }

        public CommentManageInfoBean getComment_manage_info() {
            return comment_manage_info;
        }

        public void setComment_manage_info(CommentManageInfoBean comment_manage_info) {
            this.comment_manage_info = comment_manage_info;
        }

        public List<String> getPic_ids() {
            return pic_ids;
        }

        public void setPic_ids(List<String> pic_ids) {
            this.pic_ids = pic_ids;
        }

        public List<?> getDarwin_tags() {
            return darwin_tags;
        }

        public void setDarwin_tags(List<?> darwin_tags) {
            this.darwin_tags = darwin_tags;
        }

        public List<?> getHot_weibo_tags() {
            return hot_weibo_tags;
        }

        public void setHot_weibo_tags(List<?> hot_weibo_tags) {
            this.hot_weibo_tags = hot_weibo_tags;
        }

        public List<?> getText_tag_tips() {
            return text_tag_tips;
        }

        public void setText_tag_tips(List<?> text_tag_tips) {
            this.text_tag_tips = text_tag_tips;
        }



        public static class VisibleBean {
            /**
             * type : 0
             * list_id : 0
             */

            private int type;
            private int list_id;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getList_id() {
                return list_id;
            }

            public void setList_id(int list_id) {
                this.list_id = list_id;
            }
        }

        public static class CommentManageInfoBean {
            /**
             * comment_permission_type : -1
             * approval_comment_type : 0
             */

            private int comment_permission_type;
            private int approval_comment_type;

            public int getComment_permission_type() {
                return comment_permission_type;
            }

            public void setComment_permission_type(int comment_permission_type) {
                this.comment_permission_type = comment_permission_type;
            }

            public int getApproval_comment_type() {
                return approval_comment_type;
            }

            public void setApproval_comment_type(int approval_comment_type) {
                this.approval_comment_type = approval_comment_type;
            }
        }
    }
}
