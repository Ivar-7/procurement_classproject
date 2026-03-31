package com.example.procurement.shared.util;

import java.math.BigDecimal;

public final class ParamUtils {

    private ParamUtils() {
    }

    public static String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    public static String requireText(String value, String fieldName) {
        String text = trimToNull(value);
        if (text == null) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        return text;
    }

    public static Integer toInteger(String value) {
        String text = trimToNull(value);
        if (text == null) {
            return null;
        }
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid number: " + text, ex);
        }
    }

    public static Integer requireInteger(String value, String fieldName) {
        Integer number = toInteger(value);
        if (number == null) {
            throw new IllegalArgumentException(fieldName + " is required.");
        }
        return number;
    }

    public static BigDecimal toBigDecimal(String value) {
        String text = trimToNull(value);
        if (text == null) {
            return null;
        }
        try {
            return new BigDecimal(text);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid decimal: " + text, ex);
        }
    }
}
