package com.zdh.alphathink.imh.Bean;

/**
 * Created by Panda on 2016/12/12.
 */

public class CodeTransformation {

    public static String Chinese2Unicode(String chinese){
        String unicode = "";
       for (int i=0;i<=chinese.length();i++){
           unicode = unicode + "\\u"+Integer.toHexString(chinese.charAt(i));
       }
        return  unicode;
    }

    public static String Unicode2String(String unicode){
        String chinese = "";
        for (int i = 0; i <= unicode.length();i++){
            chinese = chinese+unicode.charAt(i);
        }
        return chinese;
    }
}
