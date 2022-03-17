package com.okenit.productpricemanager.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatUtil {
    private final SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    private final SimpleDateFormat dateTime = new SimpleDateFormat("");

    public  Date parseFromDate(String string) throws ParseException {
        return date.parse(string);
    }
}
