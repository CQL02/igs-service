package com.wif3006.igsservice.shared.utils;

import java.util.Collection;
import java.util.Map;

public class ObjectUtils {

    /**
     * Check if the object is empty.
     * Handles null, empty strings, collections, maps, and arrays.
     *
     * @param obj The object to check
     * @return true if the object is empty, false otherwise
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if (obj instanceof String) {
            return ((String) obj).trim().isEmpty();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).isEmpty();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).isEmpty();
        }
        if (obj.getClass().isArray()) {
            return ((Object[]) obj).length == 0;
        }
        return false; // For other types, assume not empty
    }

    /**
     * Check if the object is not empty.
     *
     * @param obj The object to check
     * @return true if the object is not empty, false otherwise
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
