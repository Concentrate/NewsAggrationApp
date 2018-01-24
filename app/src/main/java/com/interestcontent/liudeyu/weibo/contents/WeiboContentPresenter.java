package com.interestcontent.liudeyu.weibo.contents;

import com.google.gson.Gson;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.mvp.IMvpView;
import com.interestcontent.liudeyu.base.mvp.MvpPresenter;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBean;
import com.zhy.http.okhttp.OkHttpUtils;

/**
 * Created by liudeyu on 2018/1/23.
 */

public class WeiboContentPresenter extends MvpPresenter<WeiboBean,IMvpView<WeiboBean>> {
    @Override
    public WeiboBean doWork(Object... params) throws Exception {
        String weiboId= (String) params[0];
        String res= OkHttpUtils.get().url(Constants.WEIBO_SINGLE_CONTENT).addParams(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN, SharePreferenceUtil.getStringPreference(MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN))
                .addParams(Constants.WB_REQUEST_PARAMETER.ID,weiboId).build().execute().body().string();
        return new Gson().fromJson(res, WeiboBean.class);
    }
}
