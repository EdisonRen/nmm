package com.edisonren.nmm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by edison on 9/16/17.
 */
public class Utils {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public static String generateUUID(String prefix) {
        return String.format("%s-%s", prefix, UUID.randomUUID().toString());
    }

    public static String printDate(Date date) {
        return dateFormat.format(date);
    }
}
