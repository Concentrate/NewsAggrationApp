package com.interestcontent.liudeyu.base.constants;

/**
 * Created by liudeyu on 2018/2/6.
 */

public class WebConstants {
    public static final String DEPRECATED_WEIBO_AD_CLASS="var aTags = document.getElementsByTagName(\"a\");\n" +
            "var searchText = \"微博内打开\";\n" +
            "var found;\n" +
            "var re1=/.*微博内打开.*/;\n" +
            "for (var i = 0; i < aTags.length; i++) {\n" +
            "  if (aTags[i].textContent.match(re1)) {\n" +
            "    found = aTags[i];\n" +
            "    break;\n" +
            "  }\n" +
            "}\n" +
            "if(found){\n" +
            "t1=found;\n" +
            "t1.parentNode.removeChild(t1);\n" +
            "}";

}
