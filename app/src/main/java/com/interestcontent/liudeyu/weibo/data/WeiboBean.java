package com.interestcontent.liudeyu.weibo.data;

import java.util.List;

public class WeiboBean {

    /*返回值字段	字段类型	字段说明
created_at	string	微博创建时间
id	int64	微博ID
mid	int64	微博MID
idstr	string	字符串型的微博ID
text	string	微博信息内容
source	string	微博来源
favorited	boolean	是否已收藏，true：是，false：否
truncated	boolean	是否被截断，true：是，false：否
in_reply_to_status_id	string	（暂未支持）回复ID
in_reply_to_user_id	string	（暂未支持）回复人UID
in_reply_to_screen_name	string	（暂未支持）回复人昵称
thumbnail_pic	string	缩略图片地址，没有时不返回此字段
bmiddle_pic	string	中等尺寸图片地址，没有时不返回此字段
original_pic	string	原始图片地址，没有时不返回此字段
geo	object	地理信息字段 详细
user	object	微博作者的用户信息字段 详细
retweeted_status	object	被转发的原微博信息字段，当该微博为转发微博时返回 详细
reposts_count	int	转发数
comments_count	int	评论数
attitudes_count	int	表态数
mlevel	int	暂未支持
visible	object	微博的可见性及指定可见分组信息。该object中type取值，0：普通微博，1：私密微博，3：指定分组微博，4：密友微博；list_id为分组的组号
pic_ids	object	微博配图ID。多图时返回多图ID，用来拼接图片url。用返回字段thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。
ad	object array	微博流内的推广微博ID*/

    /**
     * created_at : Tue Jan 02 15:15:03 +0800 2018
     * id : 4191761582252424
     * mid : 4191761582252424
     * idstr : 4191761582252424
     * can_edit : false
     * text : #笔经面经# 史上最短小的面经——十分钟视频面面经分享！戳http://t.cn/RHl0jG4直接与LZ交流讨论！ ​
     * textLength : 92
     * source_allowclick : 0
     * source_type : 1
     * source : <a href="http://app.weibo.com/t/feed/6vtZb0" rel="nofollow">微博 weibo.com</a>
     * favorited : false
     * truncated : false
     * in_reply_to_status_id :
     * in_reply_to_user_id :
     * in_reply_to_screen_name :
     * pic_urls : [{"thumbnail_pic":"http://wx1.sinaimg.cn/thumbnail/8116e3ccly1fn217qae2jj20dc0awdgs.jpg"}]
     * thumbnail_pic : http://wx1.sinaimg.cn/thumbnail/8116e3ccly1fn217qae2jj20dc0awdgs.jpg
     * bmiddle_pic : http://wx1.sinaimg.cn/bmiddle/8116e3ccly1fn217qae2jj20dc0awdgs.jpg
     * original_pic : http://wx1.sinaimg.cn/large/8116e3ccly1fn217qae2jj20dc0awdgs.jpg
     * geo : null
     * is_paid : false
     * mblog_vip_type : 0
     * user : {"id":2165760972,"idstr":"2165760972","class":1,"screen_name":"牛客网","name":"牛客网","province":"11","city":"1000","location":"北京","description":"www.nowcoder.com，最全IT名企面试笔试题库，帮程序猿们全面提高求职准备效率，高居榜首还有内推机会噢~ 牛客QQ群：244930442","url":"","profile_image_url":"http://tva2.sinaimg.cn/crop.163.177.629.629.50/8116e3ccjw8er0ic82ljxj20qo0zk0u6.jpg","cover_image":"http://ww2.sinaimg.cn/crop.0.0.920.300/8116e3ccjw1er0kqzy1nbj20pk08cmyu.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"nowcoder","domain":"nowcoder","weihao":"","gender":"f","followers_count":45073,"friends_count":908,"pagefriends_count":11,"statuses_count":7756,"favourites_count":77,"created_at":"Thu Jun 09 17:09:32 +0800 2011","following":true,"allow_all_act_msg":false,"geo_enabled":true,"verified":true,"verified_type":2,"remark":"","insecurity":{"sexual_content":false},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva2.sinaimg.cn/crop.163.177.629.629.180/8116e3ccjw8er0ic82ljxj20qo0zk0u6.jpg","avatar_hd":"http://tva2.sinaimg.cn/crop.163.177.629.629.1024/8116e3ccjw8er0ic82ljxj20qo0zk0u6.jpg","verified_reason":"北京牛客科技有限公司官方微博","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","verified_state":0,"verified_level":3,"verified_type_ext":0,"pay_remind":0,"pay_date":"20170713","has_service_tel":false,"verified_reason_modified":"","verified_contact_name":"","verified_contact_email":"admin@nowcoder.com","verified_contact_mobile":"","follow_me":false,"like":false,"like_me":false,"online_status":0,"bi_followers_count":182,"lang":"zh-cn","star":0,"mbtype":12,"mbrank":6,"block_word":0,"block_app":1,"credit_score":80,"user_ability":4,"cardid":"star_047","urank":38,"story_read_state":-1,"vclub_member":0}
     * reposts_count : 0
     * comments_count : 0
     * attitudes_count : 1
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
     * rid : 0_0_1_2789448621601129714
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
    private String rid;
    private int userType;
    private int more_info_type;
    private String cardid;
    private int positive_recom_flag;
    private String gif_ids;
    private int is_show_bulletin;
    private CommentManageInfoBean comment_manage_info;
    private List<PicUrlsBean> pic_urls;
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

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
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

    public List<PicUrlsBean> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<PicUrlsBean> pic_urls) {
        this.pic_urls = pic_urls;
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

    public static class PicUrlsBean {
        /**
         * thumbnail_pic : http://wx1.sinaimg.cn/thumbnail/8116e3ccly1fn217qae2jj20dc0awdgs.jpg
         */

        private String thumbnail_pic;

        public String getThumbnail_pic() {
            return thumbnail_pic;
        }

        public void setThumbnail_pic(String thumbnail_pic) {
            this.thumbnail_pic = thumbnail_pic;
        }
    }
}