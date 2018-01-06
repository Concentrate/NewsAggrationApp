package com.interestcontent.liudeyu.weibo;

import com.google.gson.Gson;
import com.interestcontent.liudeyu.base.baseComponent.MyApplication;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.SpConstants;
import com.interestcontent.liudeyu.base.utils.SharePreferenceUtil;
import com.interestcontent.liudeyu.weibo.data.bean.WeiboBeanTestRequest;
import com.zhouwei.rvadapterlib.fragment.AbsFeedFragment;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by liudeyu on 2017/12/30.
 */

/**
 * 检测是否登录微博，并且给予一定操作
 */
public abstract class WeiboBaseTabFragment extends AbsFeedFragment {


    protected int mCurrentPage = 1;
    protected int mEveryPageDataNum = 10;


    protected abstract String providedRequestDataUrl();


    protected void requestPageData(int page) {
        mCurrentPage = page;
        startRequestWeiboFeed(page > 1);

    }

    protected void startRequestWeiboFeed(boolean showLoadMore) {
        String url = providedRequestDataUrl();
        OkHttpUtils.get().url(url)
                .params(provideRequestParameter())
                .build().execute(new Callback<WeiboBeanTestRequest>() {
            @Override
            public WeiboBeanTestRequest parseNetworkResponse(Response response, int id) throws Exception {
                ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
                return new Gson().fromJson(responseBodyCopy.string(), WeiboBeanTestRequest.class);
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                mBaseAdapter.hideLoading();
                setRefreshing(false);
                mBaseAdapter.showError();
            }

            @Override
            public void onResponse(WeiboBeanTestRequest response, int id) {
                mBaseAdapter.hideLoading();
                setRefreshing(false);
                if (response == null || response.getStatuses() == null) {
                    return;
                }
                getResponseData(response.getStatuses());
            }
        });
        if (showLoadMore) {
            mBaseAdapter.showLoading();
        }
    }

    protected abstract void getResponseData(List<WeiboBeanTestRequest.StatusesBean> statuses);

    protected Map<String, String> provideRequestParameter() {
        Map<String, String> map = new HashMap<>();
        map.put(Constants.WB_REQUEST_PARAMETER.ACCESS_TOKEN, SharePreferenceUtil.getStringPreference(
                MyApplication.sApplication, SpConstants.WEIBO_AUTHEN_TOKEN));
        map.put(Constants.WB_REQUEST_PARAMETER.PAGE, mCurrentPage + "");
        map.put(Constants.WB_REQUEST_PARAMETER.SINGLE_PAGE_COUNT, mEveryPageDataNum + "");
        map.put(Constants.WB_REQUEST_PARAMETER.TRIM_USER, 1 + "");
        return map;
    }

}
