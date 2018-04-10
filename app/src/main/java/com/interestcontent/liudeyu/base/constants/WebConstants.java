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


    public static final String WEB_TOUTIAO_DOMAIN = "m.toutiao.com";

    public static final String REMOVE_TOUTIAO_WEB_AD_TAG = "var arraySele=[\"#main > div > div.news-banner-container-new-article.banner-top.new-style-test\",\"#main > div > div:nth-child(2)\",\n" +
            "\"#main > div > article > div.unfold-field.unfold-field-download > a\",'#main > div > div.share-card-container-test2.share-card-container-true']\n" +
            "for(var a1 of arraySele){\n" +
            "\tconst b1=document.querySelector(a1)\n" +
            "\tif(b1){\n" +
            "\t\tb1.parentElement.removeChild(b1)\n" +
            "\t}\n" +
            "}\n" +
            "var a1=document.getElementsByClassName(\"new-style-test-favor-btn\")\n" +
            "if(a1&&a1[0]){\n" +
            "    a1[0].parentElement.removeChild(a1[0])\n" +
            "}";
    public static final String WEB_163_DOMAIN = "3g.163.com";
    public static final String REMOVE_163_WEB_AD = "var bArray=[\"body > div.doc-footer-wrapper\", \"body > header\",\"#article-DCMN1GSG000380D0 > div.footer > div.wakeup_client.js-wakeupclient\",\"body > footer > div.foot_nav\",\"body > footer\"]\n" +
            "for(c1 of bArray){\n" +
            "\tvar headdiv=document.querySelector(c1);\n" +
            "\tif(headdiv){\n" +
            "\t\theaddiv.parentElement.removeChild(headdiv)\n" +
            "\t}\n" +
            "}\n" +
            "\n" +
            "var array=[]\n" +
            "array[0]=\"article_comment\"\n" +
            "array[1]=\"relative_doc\"\n" +
            "array[2]=\"hot_news\"\n" +
            "array[4]=\"footer\"\n" +
            "\n" +
            "for(a1 of array){\n" +
            "    var b1=document.getElementsByClassName(a1)\n" +
            "    if(b1){\n" +
            "    \tfor(c1 of b1){\n" +
            "     if(c1){\n" +
            "        c1.parentElement.removeChild(c1);\n" +
            "    } }\n" +
            "   }\n" +
            "    \n" +
            "}\n" +
            "\n";


    public static final String WEB_LEIFENG_DOMAIN = "m.leiphone.com";
    public static final String LEIFENG_REMOVE_AD_TAG = "\n" +
            "var seleArr=[\"body > footer\",\"body > div.article-list\"]\n" +
            "for(const a1 of seleArr){\n" +
            "\ta2=document.querySelector(a1)\n" +
            "\tif(a2){\n" +
            "\t\ta2.parentElement.removeChild(a2)\n" +
            "\t}\n" +
            "}";

    public static final String WEB_TOUTIAO_VIDEO = "m.365yg.com";
    public static final String TOUTIAO_VIDEO_REMOVE_AD = "var classNameArray=[\"news-banner-container-xigua-video banner-top\",\"video-download-new\",\"recommendation-container\",\n" +
            "\"bottom-banner-container\",\"download\",\"share-card-container\",]\n" +
            "for(let a1 of classNameArray){\n" +
            "\tconst b1=document.getElementsByClassName(a1)\n" +
            "\tif(b1&&b1[0]){\n" +
            "\t\tb1[0].parentElement.removeChild(b1[0])\n" +
            "\t}\n" +
            "}\n";
    public static final String WEI_TOUTIAO_BASE = "weitoutiao";
    public static final String WEI_TOUTIAO_REMOVE_AD = "a=document.querySelector(\"body > div.wrapper.dynamic-list > div.comment_list > div.tips\")\n" +
            "if(a){\n" +
            "a.parentElement.removeChild(a)\t\n" +
            "}\n" +
            "var classNameArray=[\"focus_btn\",\"download\"]\n" +
            "for(let a1 of classNameArray){\n" +
            "\tconst b1=document.getElementsByClassName(a1)\n" +
            "\tif(b1&&b1[0]){\n" +
            "\t\tb1[0].parentElement.removeChild(b1[0])\n" +
            "\t}\n" +
            "}\n";

    public static final String WUKONGWENDA_BASE = "wukong";
    public static final String WUKONGWENDA_REMOVEAD = "var arraySele=[\"#body > div.m-feed-share-recommend.recommend-feed.recommend\",\"#body > div.answer-in-app.answer-in-wap\"]\n" +
            "for(var a1 of arraySele){\n" +
            "\tconst b1=document.querySelector(a1)\n" +
            "\tif(b1){\n" +
            "\t\tb1.parentElement.removeChild(b1)\n" +
            "\t}\n" +
            "}\n" +
            "a=document.getElementsByClassName(\"top-bar\")\n" +
            "if(a&&a[0]){\n" +
            "    a[0].parentElement.removeChild(a[0]);\n" +
            "}";

}
