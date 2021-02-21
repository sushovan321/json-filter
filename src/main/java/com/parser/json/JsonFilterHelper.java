package com.parser.json;

public class JsonFilterHelper {

    public static boolean validateTextValueIfEmptyOrNull(String text) {
        return null != text && !text.isEmpty();
    }
}
