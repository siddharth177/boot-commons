package com.github.bootcommons.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Util {
    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static boolean nullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
    
    public static <T> boolean nullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    public static <K, V> boolean nullOrEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }

    public static <T> boolean nullOrEmpty(Set<T> set) {
        return set == null || set.isEmpty();
    }
}
