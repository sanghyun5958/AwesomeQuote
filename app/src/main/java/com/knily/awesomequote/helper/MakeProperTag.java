package com.knily.awesomequote.helper;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by P KUMAR on 23-01-2017.
 */

public class MakeProperTag {
    public static String makeTag(String getSelectedLanguage) throws IndexOutOfBoundsException{
        String substring=getSelectedLanguage.substring(0,getSelectedLanguage.length()-1);
        Log.d("String",substring);
        return substring;
    }
    public static String makeTagEnglish(String getSelectedLanguage) throws IndexOutOfBoundsException{
        String result="";
        ArrayList<String> first=new ArrayList<String>();
        ArrayList<String> second=new ArrayList<String>();
        first.add("35459");
        first.add("2579");
        first.add("17313");
        first.add("31188");

        String[] str= getSelectedLanguage.split(",");

        for(int i=0;i<str.length; i++){
            second.add(str[i]);
        }
        first.removeAll(second);
        Object[] obj = first.toArray();

        String[] str2 = Arrays.copyOf(obj, obj.length, String[].class);

        for(int j=0; j < str2.length; j++){
            result+=str2[j]+",";
        }
        return makeTag(result);
    }
}
