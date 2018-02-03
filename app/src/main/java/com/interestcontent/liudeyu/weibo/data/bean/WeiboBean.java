package com.interestcontent.liudeyu.weibo.data.bean;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liudeyu on 2018/1/22.
 */
public class WeiboBean  extends WeiboBaseBean implements Serializable{
    private static final long serialVersionUID = 7382351359868556980L;
    /**
     * created_at : Sat Jan 06 14:05:03 +0800 2018
     * id : 4193193518299263
     * mid : 4193193518299263
     * idstr : 4193193518299263
     * can_edit : false
     * text : #简书分享# 凌晨三点 | 月亮和无人便利店 ​
     * textLength : 38
     * source_allowclick : 0
     * source_type : 1
     * source : <a href="http://app.weibo.com/t/feed/6vtZb0" rel="nofollow">微博 weibo.com</a>
     * favorited : false
     * truncated : false
     * in_reply_to_status_id :
     * in_reply_to_user_id :
     * in_reply_to_screen_name :
     * pic_urls : [{"thumbnail_pic":"http://wx2.sinaimg.cn/thumbnail/aa397b7fgy1fn5p5inohpj20g8cmhkjl.jpg"}]
     * thumbnail_pic : http://wx2.sinaimg.cn/thumbnail/aa397b7fgy1fn5p5inohpj20g8cmhkjl.jpg
     * bmiddle_pic : http://wx2.sinaimg.cn/bmiddle/aa397b7fgy1fn5p5inohpj20g8cmhkjl.jpg
     * original_pic : http://wx2.sinaimg.cn/large/aa397b7fgy1fn5p5inohpj20g8cmhkjl.jpg
     * geo : null
     * is_paid : false
     * mblog_vip_type : 0
     * uid : 2855893887
     * reposts_count : 0
     * comments_count : 2
     * attitudes_count : 11
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
     * rid : 0_0_1_2666887494956978362
     * userType : 0
     * more_info_type : 0
     * cardid : star_086
     * positive_recom_flag : 0
     * content_auth : 0
     * gif_ids :
     * is_show_bulletin : 2
     * comment_manage_info : {"comment_permission_type":-1,"approval_comment_type":0}
     * biz_ids : [0]
     * annotations : [{"client_mblogid":"iPhone-B1750F90-69A5-4DE4-AFA8-91DA84EA843C"},{"mapi_request":true}]
     * picStatus : 0:1
     */

    private String created_at;
    private long id;
    private String mid;
    private String idstr;
    private boolean can_edit;
    private String text;
    private long textLength;
    private long source_allowclick;
    private long source_type;
    private String source;
    private boolean favorited;
    private boolean truncated;
    private String in_reply_to_status_id;
    private String in_reply_to_user_id;

    private String in_reply_to_screen_name;
    private String thumbnail_pic;
    private String bmiddle_pic;
    private String original_pic;
    private boolean is_paid;
    private long mblog_vip_type;
    private long uid;
    private long reposts_count;
    private long comments_count;
    private long attitudes_count;
    private long pending_approval_count;
    private boolean isLongText;
    private long mlevel;
    private VisibleBean visible;
    private long biz_feature;
    private long page_type;
    private long hasActionTypeCard;
    private String rid;
    private long userType;
    private long more_info_type;
    private String cardid;
    private long positive_recom_flag;
    private long content_auth;
    private String gif_ids;
    private long is_show_bulletin;
    private CommentManageInfoBean comment_manage_info;
    private String picStatus;
    private List<PicUrlsBean> pic_urls;
    private List<Long> biz_ids;
    private List<AnnotationsBean> annotations;
    @SerializedName(value = "user")
    private WeiboUserBean user;


    public WeiboUserBean getUser() {
        return user;
    }

    public void setUser(WeiboUserBean user) {
        this.user = user;
    }

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

    public long getTextLength() {
        return textLength;
    }

    public void setTextLength(long textLength) {
        this.textLength = textLength;
    }

    public long getSource_allowclick() {
        return source_allowclick;
    }

    public void setSource_allowclick(long source_allowclick) {
        this.source_allowclick = source_allowclick;
    }

    public long getSource_type() {
        return source_type;
    }

    public void setSource_type(long source_type) {
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


    public boolean isIs_paid() {
        return is_paid;
    }

    public void setIs_paid(boolean is_paid) {
        this.is_paid = is_paid;
    }

    public long getMblog_vip_type() {
        return mblog_vip_type;
    }

    public void setMblog_vip_type(long mblog_vip_type) {
        this.mblog_vip_type = mblog_vip_type;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getReposts_count() {
        return reposts_count;
    }

    public void setReposts_count(long reposts_count) {
        this.reposts_count = reposts_count;
    }

    public long getComments_count() {
        return comments_count;
    }

    public void setComments_count(long comments_count) {
        this.comments_count = comments_count;
    }

    public long getAttitudes_count() {
        return attitudes_count;
    }

    public void setAttitudes_count(long attitudes_count) {
        this.attitudes_count = attitudes_count;
    }

    public long getPending_approval_count() {
        return pending_approval_count;
    }

    public void setPending_approval_count(long pending_approval_count) {
        this.pending_approval_count = pending_approval_count;
    }

    public boolean isIsLongText() {
        return isLongText;
    }

    public void setIsLongText(boolean isLongText) {
        this.isLongText = isLongText;
    }

    public long getMlevel() {
        return mlevel;
    }

    public void setMlevel(long mlevel) {
        this.mlevel = mlevel;
    }

    public VisibleBean getVisible() {
        return visible;
    }

    public void setVisible(VisibleBean visible) {
        this.visible = visible;
    }

    public long getBiz_feature() {
        return biz_feature;
    }

    public void setBiz_feature(long biz_feature) {
        this.biz_feature = biz_feature;
    }

    public long getPage_type() {
        return page_type;
    }

    public void setPage_type(long page_type) {
        this.page_type = page_type;
    }

    public long getHasActionTypeCard() {
        return hasActionTypeCard;
    }

    public void setHasActionTypeCard(long hasActionTypeCard) {
        this.hasActionTypeCard = hasActionTypeCard;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public long getUserType() {
        return userType;
    }

    public void setUserType(long userType) {
        this.userType = userType;
    }

    public long getMore_info_type() {
        return more_info_type;
    }

    public void setMore_info_type(long more_info_type) {
        this.more_info_type = more_info_type;
    }

    public String getCardid() {
        return cardid;
    }

    public void setCardid(String cardid) {
        this.cardid = cardid;
    }

    public long getPositive_recom_flag() {
        return positive_recom_flag;
    }

    public void setPositive_recom_flag(long positive_recom_flag) {
        this.positive_recom_flag = positive_recom_flag;
    }

    public long getContent_auth() {
        return content_auth;
    }

    public void setContent_auth(long content_auth) {
        this.content_auth = content_auth;
    }

    public String getGif_ids() {
        return gif_ids;
    }

    public void setGif_ids(String gif_ids) {
        this.gif_ids = gif_ids;
    }

    public long getIs_show_bulletin() {
        return is_show_bulletin;
    }

    public void setIs_show_bulletin(long is_show_bulletin) {
        this.is_show_bulletin = is_show_bulletin;
    }

    public CommentManageInfoBean getComment_manage_info() {
        return comment_manage_info;
    }

    public void setComment_manage_info(CommentManageInfoBean comment_manage_info) {
        this.comment_manage_info = comment_manage_info;
    }

    public String getPicStatus() {
        return picStatus;
    }

    public void setPicStatus(String picStatus) {
        this.picStatus = picStatus;
    }

    public List<PicUrlsBean> getPic_urls() {
        return pic_urls;
    }

    public void setPic_urls(List<PicUrlsBean> pic_urls) {
        this.pic_urls = pic_urls;
    }

    public List<Long> getBiz_ids() {
        return biz_ids;
    }

    public void setBiz_ids(List<Long> biz_ids) {
        this.biz_ids = biz_ids;
    }

    public List<AnnotationsBean> getAnnotations() {
        return annotations;
    }

    public void setAnnotations(List<AnnotationsBean> annotations) {
        this.annotations = annotations;
    }


    public static class VisibleBean implements Serializable {
        /**
         * type : 0
         * list_id : 0
         */

        private long type;
        private long list_id;

        public long getType() {
            return type;
        }

        public void setType(long type) {
            this.type = type;
        }

        public long getList_id() {
            return list_id;
        }

        public void setList_id(long list_id) {
            this.list_id = list_id;
        }




        public VisibleBean() {
        }

        protected VisibleBean(Parcel in) {
            this.type = in.readLong();
            this.list_id = in.readLong();
        }

    }

    public static class CommentManageInfoBean implements Serializable {
        /**
         * comment_permission_type : -1
         * approval_comment_type : 0
         */

        private long comment_permission_type;
        private long approval_comment_type;

        public long getComment_permission_type() {
            return comment_permission_type;
        }

        public void setComment_permission_type(long comment_permission_type) {
            this.comment_permission_type = comment_permission_type;
        }

        public long getApproval_comment_type() {
            return approval_comment_type;
        }

        public void setApproval_comment_type(long approval_comment_type) {
            this.approval_comment_type = approval_comment_type;
        }


        public CommentManageInfoBean() {
        }


    }

    public static class PicUrlsBean  implements Serializable{
        /**
         * thumbnail_pic : http://wx2.sinaimg.cn/thumbnail/aa397b7fgy1fn5p5inohpj20g8cmhkjl.jpg
         */

        private String thumbnail_pic;

        public String getThumbnail_pic() {
            return thumbnail_pic;
        }

        public void setThumbnail_pic(String thumbnail_pic) {
            this.thumbnail_pic = thumbnail_pic;
        }
    }


    public static class AnnotationsBean implements Serializable{
        /**
         * client_mblogid : iPhone-B1750F90-69A5-4DE4-AFA8-91DA84EA843C
         * mapi_request : true
         */

        private String client_mblogid;
        private boolean mapi_request;

        public String getClient_mblogid() {
            return client_mblogid;
        }

        public void setClient_mblogid(String client_mblogid) {
            this.client_mblogid = client_mblogid;
        }

        public boolean isMapi_request() {
            return mapi_request;
        }

        public void setMapi_request(boolean mapi_request) {
            this.mapi_request = mapi_request;
        }
    }

    public WeiboBean() {
    }


}
