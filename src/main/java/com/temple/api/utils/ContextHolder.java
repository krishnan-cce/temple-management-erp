package com.temple.api.utils;

import org.springframework.stereotype.Component;

@Component
public class ContextHolder {

    private static final String DEFAULT_YEAR = "2023";
    private static final ThreadLocal<String> currentYearHolder = new ThreadLocal<>();

    public static String getCurrentYear() {
        return currentYearHolder.get() ;//!= null ? currentYearHolder.get() : DEFAULT_YEAR;
    }

    public static void setCurrentYear(String currentYear) {
        currentYearHolder.set(currentYear);
    }

    public static void clear() {
        currentYearHolder.remove();
    }

    static {
        clear(); // Initializing in the static block as you've done is good.
    }
}
