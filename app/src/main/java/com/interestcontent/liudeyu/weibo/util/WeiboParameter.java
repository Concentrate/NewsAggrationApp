package com.interestcontent.liudeyu.weibo.util;

import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liudeyu on 2018/1/23.
 */

public class WeiboParameter {

    private Map para = null;

    private WeiboParameter(Map map) {
        para = map;
    }

    public Map getParaMap() {
        return para;
    }

    public static class ParameterBuilder {

        Map<String, String> map = new HashMap<>();

        public ParameterBuilder() {
            map.put(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN, SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN));
        }

        /**
         * 默认带 token
         */
        public ParameterBuilder removeAccessToken() {
            map.remove(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN);
            return this;
        }

        /**
         * 返回uid,默认返回完整user 字段
         */
        public ParameterBuilder trimUser() {
            map.put(Constants.WB_REQUEST_PARAMETER.TRIM_USER, "1");
            return this;
        }

        public ParameterBuilder setEveryPageCount(int count) {
            map.put(Constants.WB_REQUEST_PARAMETER.SINGLE_PAGE_COUNT, count + "");
            return this;
        }

        public ParameterBuilder setWeiboId(String id) {
            map.put(Constants.WB_REQUEST_PARAMETER.ID, id);
            return this;
        }

        public ParameterBuilder setUserId(String uid) {
            map.put(Constants.WB_REQUEST_PARAMETER.UID, uid);
            return this;
        }

       public   WeiboParameter build() {
            return new WeiboParameter(map);
        }
    }
}
