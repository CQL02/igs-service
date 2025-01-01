package com.wif3006.igsservice.shared.utils;

public class StringUtils {

    /**
     * Check if the string is empty or null.
     *
     * @param str The string to check
     * @return true if the string is null or empty, false otherwise
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Check if the string is not empty.
     *
     * @param str The string to check
     * @return true if the string is not null or empty, false otherwise
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}

