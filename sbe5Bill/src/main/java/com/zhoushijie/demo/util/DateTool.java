package com.zhoushijie.demo.util;
import org.springframework.stereotype.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class DateTool {
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");

    public static Date StringToolDate(String time){
        try {
            Date parse = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String DateToolString(Date date){
        String format = simpleDateFormat.format(date);
        return null;
    }
}