package com.knily.awesomequote.helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by P KUMAR on 27-12-2016.
 */

public class DateTimeFormat {

    public static String DateTimeFormat(String inputDate){
        String oldDate,str1,str2,newDate = null;
        Date date;

        DateFormat inputDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        try {
            oldDate=inputDate.replace('T',' ');
            date=inputDateFormat.parse(oldDate);
            str2=DateFormat.getDateInstance().format(date);
            newDate=str2;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
