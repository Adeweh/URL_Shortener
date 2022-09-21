package com.newURL.utils;

import java.util.ArrayList;
import java.util.List;

public class IdConverter {
    private static final String allowedString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static char [] allowedCharacters = allowedString.toCharArray();
    private static final List<Long> remainderList = new ArrayList<>();

    public static String convertKey(long key) {
        if (key < 62) {
            long request = key;
            return String.valueOf(allowedCharacters[(int) request]);
        }

        long newDivide = key;
        while (newDivide != 0) {
            remainderList.add(0, newDivide % 62);
            newDivide /= 62;
        }

        StringBuilder convertedKey = new StringBuilder();
        for (long remainder : remainderList) {
            convertedKey.append(allowedCharacters[(int) remainder]);
        }
        remainderList.clear();
        return convertedKey.toString();
    }

    public static int getKey(String convertedKey) {
        int requestKey = 0;
        for (int i = 0; i < convertedKey.length(); i++) {
            for (int j = 0; j < 62; j++) {
                if (convertedKey.charAt(i) == allowedCharacters[j]) {
                    requestKey += j * Math.pow(62, convertedKey.length() - (i + 1));
                    break;
                }

            }

        }
        return requestKey;
    }

}
