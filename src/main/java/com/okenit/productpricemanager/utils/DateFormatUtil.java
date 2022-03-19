package com.okenit.productpricemanager.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateTime = new SimpleDateFormat("");

    public static Date parseFromDate(String string) throws ParseException {
        return dateFormat.parse(string);
    }

    public static String getNormanDate(Date date){
        return dateFormat.format(date);
    }
}
