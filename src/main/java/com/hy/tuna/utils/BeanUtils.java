package com.hy.tuna.utils;

public class BeanUtils {

    public static String getCamelString(String str,boolean firstLetterUppercase){
        if(!StringUtils.hasText(str)){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String[] strs = str.split("_");
        if(firstLetterUppercase){
            int len = strs[0].length();
            sb.append(Character.toUpperCase(strs[0].charAt(0)));
            if(len>1) {
                for (int i = 1; i < len; i++) {
                    sb.append(Character.toLowerCase(strs[0].charAt(i)));
                }
            }
        }else {
            sb.append(strs[0].toLowerCase());
            int tempLen;
            String tempItem;
            for(int item=1;item<strs.length;item++){
                tempItem = strs[item];
                sb.append(Character.toUpperCase(tempItem.charAt(0)));
                 tempLen = tempItem.length();
                if(tempLen>1) {
                    for (int j = 1; j < tempLen; j++) {
                        sb.append(Character.toLowerCase(tempItem.charAt(j)));
                    }
                }
            }
        }
        return sb.toString();
    }

    public static String getCamelString(String columnName) {
        return getCamelString(columnName,false);
    }
}
