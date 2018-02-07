package com.interestcontent.liudeyu.base.baseComponent;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.example.commonlib.utils.Logger;
import com.interestcontent.liudeyu.base.constants.Constants;
import com.interestcontent.liudeyu.base.constants.WebConstants;

import java.util.HashMap;

/**
 * Created by liudeyu on 2018/2/7.
 */

public class RemoveWebAdTagControler {
    private static final String TAG = "RemoveWebAdTagControler";
    private HashMap<String, String> matchUrlAndJsMethod = new HashMap<>();


    public RemoveWebAdTagControler() {
        baseRegistAllMethod();
    }

    private void baseRegistAllMethod() {
        matchUrlAndJsMethod.put(Constants.WEIBO_BASE_WEB_DOMAIN, WebConstants.DEPRECATED_WEIBO_AD_CLASS);
        matchUrlAndJsMethod.put(Constants.NEWS_36KR_BASE_WEB_DOMAIN, WebConstants.REMOVE_36KR_AD_TAG);

    }

    public void registJsMethod(String url, String method) {
        matchUrlAndJsMethod.put(url, method);
    }


    public void removeFrontEndAd(WebView view, final String url) {
        for (String tmp : matchUrlAndJsMethod.keySet()) {
            if (url.contains(tmp)) {
                Logger.d(TAG, "execute on url: " + url + " and js execute is " + "javascript:" + matchUrlAndJsMethod.get(tmp));
                if (Build.VERSION.SDK_INT < 19) {
                    view.loadUrl("javaScript:" + matchUrlAndJsMethod.get(tmp));
                } else {
                    view.evaluateJavascript("javascript:" + matchUrlAndJsMethod.get(tmp), new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            //此处为 js 返回的结果
                            Logger.d(TAG, "url " + url + " execute result is : " + value);
                        }
                    });
                }
            }
        }
    }
}
