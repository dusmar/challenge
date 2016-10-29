package com.a2a.common;

public class Utils {

    private Utils() {}

    /**
     * Helper method that safely parses integers
     * 
     * @param s
     * @param defaultValue
     * @return
     */
    public static int parseInt(String s, int defaultValue) {
        if (s == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultValue;
        }
    }
    
}
