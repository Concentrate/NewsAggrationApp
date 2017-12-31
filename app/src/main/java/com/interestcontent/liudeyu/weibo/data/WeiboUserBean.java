package com.interestcontent.liudeyu.weibo.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by liudeyu on 2017/12/31.
 */

public class WeiboUserBean implements Parcelable {

    /**
     * id : 1404376560
     * screen_name : zaku
     * name : zaku
     * province : 11
     * city : 5
     * location : 北京 朝阳区
     * description : 人生五十年，乃如梦如幻；有生斯有死，壮士复何憾。
     * url : http://blog.sina.com.cn/zaku
     * profile_image_url : http://tp1.sinaimg.cn/1404376560/50/0/1
     * domain : zaku
     * gender : m
     * followers_count : 1204
     * friends_count : 447
     * statuses_count : 2908
     * favourites_count : 0
     * created_at : Fri Aug 28 00:00:00 +0800 2009
     * following : false
     * allow_all_act_msg : false
     * remark :
     * geo_enabled : true
     * verified : false
     * allow_all_comment : true
     * avatar_large : http://tp1.sinaimg.cn/1404376560/180/0/1
     * verified_reason :
     * follow_me : false
     * online_status : 0
     * bi_followers_count : 215
     */

    @JSONField(name = "id")
    private int id;
    @JSONField(name = "screen_name")
    private String screen_name;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "province")
    private String province;
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "location")
    private String location;
    @JSONField(name = "description")
    private String description;
    @JSONField(name = "url")
    private String url;
    @JSONField(name = "profile_image_url")
    private String profile_image_url;
    @JSONField(name = "domain")
    private String domain;
    @JSONField(name = "gender")
    private String gender;
    @JSONField(name = "followers_count")
    private int followers_count;
    @JSONField(name = "friends_count")
    private int friends_count;
    @JSONField(name = "statuses_count")
    private int statuses_count;
    @JSONField(name = "favourites_count")
    private int favourites_count;
    @JSONField(name = "created_at")
    private String created_at;
    @JSONField(name = "following")
    private boolean following;
    @JSONField(name = "allow_all_act_msg")
    private boolean allow_all_act_msg;
    @JSONField(name = "remark")
    private String remark;
    @JSONField(name = "geo_enabled")
    private boolean geo_enabled;
    @JSONField(name = "verified")
    private boolean verified;
    @JSONField(name = "allow_all_comment")
    private boolean allow_all_comment;
    @JSONField(name = "avatar_large")
    private String avatar_large;
    @JSONField(name = "verified_reason")
    private String verified_reason;
    @JSONField(name = "follow_me")
    private boolean follow_me;
    @JSONField(name = "online_status")
    private int online_status;
    @JSONField(name = "bi_followers_count")
    private int bi_followers_count;

    protected WeiboUserBean(Parcel in) {
        id = in.readInt();
        screen_name = in.readString();
        name = in.readString();
        province = in.readString();
        city = in.readString();
        location = in.readString();
        description = in.readString();
        url = in.readString();
        profile_image_url = in.readString();
        domain = in.readString();
        gender = in.readString();
        followers_count = in.readInt();
        friends_count = in.readInt();
        statuses_count = in.readInt();
        favourites_count = in.readInt();
        created_at = in.readString();
        following = in.readByte() != 0;
        allow_all_act_msg = in.readByte() != 0;
        remark = in.readString();
        geo_enabled = in.readByte() != 0;
        verified = in.readByte() != 0;
        allow_all_comment = in.readByte() != 0;
        avatar_large = in.readString();
        verified_reason = in.readString();
        follow_me = in.readByte() != 0;
        online_status = in.readInt();
        bi_followers_count = in.readInt();
    }

    public static final Creator<WeiboUserBean> CREATOR = new Creator<WeiboUserBean>() {
        @Override
        public WeiboUserBean createFromParcel(Parcel in) {
            return new WeiboUserBean(in);
        }

        @Override
        public WeiboUserBean[] newArray(int size) {
            return new WeiboUserBean[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfile_image_url() {
        return profile_image_url;
    }

    public void setProfile_image_url(String profile_image_url) {
        this.profile_image_url = profile_image_url;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(int followers_count) {
        this.followers_count = followers_count;
    }

    public int getFriends_count() {
        return friends_count;
    }

    public void setFriends_count(int friends_count) {
        this.friends_count = friends_count;
    }

    public int getStatuses_count() {
        return statuses_count;
    }

    public void setStatuses_count(int statuses_count) {
        this.statuses_count = statuses_count;
    }

    public int getFavourites_count() {
        return favourites_count;
    }

    public void setFavourites_count(int favourites_count) {
        this.favourites_count = favourites_count;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public boolean isAllow_all_act_msg() {
        return allow_all_act_msg;
    }

    public void setAllow_all_act_msg(boolean allow_all_act_msg) {
        this.allow_all_act_msg = allow_all_act_msg;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isGeo_enabled() {
        return geo_enabled;
    }

    public void setGeo_enabled(boolean geo_enabled) {
        this.geo_enabled = geo_enabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isAllow_all_comment() {
        return allow_all_comment;
    }

    public void setAllow_all_comment(boolean allow_all_comment) {
        this.allow_all_comment = allow_all_comment;
    }

    public String getAvatar_large() {
        return avatar_large;
    }

    public void setAvatar_large(String avatar_large) {
        this.avatar_large = avatar_large;
    }

    public String getVerified_reason() {
        return verified_reason;
    }

    public void setVerified_reason(String verified_reason) {
        this.verified_reason = verified_reason;
    }

    public boolean isFollow_me() {
        return follow_me;
    }

    public void setFollow_me(boolean follow_me) {
        this.follow_me = follow_me;
    }

    public int getOnline_status() {
        return online_status;
    }

    public void setOnline_status(int online_status) {
        this.online_status = online_status;
    }

    public int getBi_followers_count() {
        return bi_followers_count;
    }

    public void setBi_followers_count(int bi_followers_count) {
        this.bi_followers_count = bi_followers_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(screen_name);
        parcel.writeString(name);
        parcel.writeString(province);
        parcel.writeString(city);
        parcel.writeString(location);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(profile_image_url);
        parcel.writeString(domain);
        parcel.writeString(gender);
        parcel.writeInt(followers_count);
        parcel.writeInt(friends_count);
        parcel.writeInt(statuses_count);
        parcel.writeInt(favourites_count);
        parcel.writeString(created_at);
        parcel.writeByte((byte) (following ? 1 : 0));
        parcel.writeByte((byte) (allow_all_act_msg ? 1 : 0));
        parcel.writeString(remark);
        parcel.writeByte((byte) (geo_enabled ? 1 : 0));
        parcel.writeByte((byte) (verified ? 1 : 0));
        parcel.writeByte((byte) (allow_all_comment ? 1 : 0));
        parcel.writeString(avatar_large);
        parcel.writeString(verified_reason);
        parcel.writeByte((byte) (follow_me ? 1 : 0));
        parcel.writeInt(online_status);
        parcel.writeInt(bi_followers_count);
    }

}
