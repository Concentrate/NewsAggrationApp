package com.interestcontent.liudeyu.util;

import java.util.regex.Pattern;

/**
 * Created by liudeyu on 2018/4/9.
 */

public class NumberUtils {
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
