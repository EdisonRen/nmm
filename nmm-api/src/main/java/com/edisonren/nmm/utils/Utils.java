package com.edisonren.nmm.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by edison on 9/16/17.
 */
public class Utils {
    public static final Integer UUID_LEN = 8;

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Base 62
     *
     * @param prefix
     * @return
     */
    public static String generateUUID(String prefix) {
        return String.format("%s-%s", prefix, RandomIdGenerator.getBase62(UUID_LEN));
    }

    public static String printDate(Date date) {
        return dateFormat.format(date);
    }

    private static class RandomIdGenerator {
        private static final Integer BASE_62 = 62;
        private static final Integer BASE_36 = 36;

        private static final char[] BASE_CHARS =
                "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
                        .toCharArray();

        private static final Random RANDOM = new Random();

        // Base 62
        private static String getBase62(int len) {
            return getId(len, BASE_62);
        }

        // Base 36
        private static String getBase36(int len) {
            return getId(len, BASE_36);
        }

        /**
         * Generate human readable randomly generated ID
         *
         * @param len length of the generated result
         * @param base set of the chars
         * @return
         */
        private static String getId(int len, int base) {
            StringBuilder srb = new StringBuilder(len);

            for (int i = 0; i <len; i++) {
                srb.append(BASE_CHARS[RANDOM.nextInt(62)]);
            }

            return srb.toString();
        }
    }
}
