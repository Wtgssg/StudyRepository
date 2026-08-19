package com.success.oracle.util;

import java.util.List;

public class StringUtil {
    public static boolean in(String str, List<String> stringList){
        if (stringList == null) return false;
        for (String s : stringList) {
            if (str.equals(s)) {
                return true;
            }
        }
        return false;
    }


}
