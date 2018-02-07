package com.interestcontent.liudeyu.base.constants;

/**
 * Created by liudeyu on 2018/2/6.
 */

public class WebConstants {
    public static final String DEPRECATED_WEIBO_AD_CLASS = "var aTags = document.getElementsByTagName(\"a\");\n" +
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

    public static final String REMOVE_36KR_AD_TAG = "a1=document.getElementsByClassName(\"fixed-linkedme\");\n" +
            "if(a1){\n" +
            "a1[0].parentElement.removeChild(a1[0])\n" +
            "};\n" +
            "a2=document.querySelector(\"#app > div > div > div > div > div.content-wrapper > a > div\");\n" +
            "if(a2){\n" +
            "\ta2.parentElement.removeChild(a2);\t\n" +
            "}\n" +
            "a3=document.querySelector(\"#app > div > div > div > div > div.content-wrapper > div.comment-list-wrapper > div > a\");\n" +
            "if(a3){\n" +
            "a3.parentElement.removeChild(a3)\t\n" +
            "}\n" +
            "a5=document.querySelector(\"#app > div > div > div > div > div.content-wrapper > div.article-feeds > div:nth-child(1) > div\");\n" +
            "if(a5){\n" +
            "a5.parentElement.removeChild(a5)\n" +
            "}\n" +
            "var a6=document.querySelector(\"#app > div > div > div > div > div.content-wrapper > div.article-feeds > div.bottom-info-block\")\n" +
            "if(a6){\n" +
            "a6.parentElement.remove(a6)\t\n" +
            "}";

}
