package com.google.common.base;

public final class Strings {
    public static boolean isNullOrEmpty(String str) {
        return Platform.stringIsNullOrEmpty(str);
    }

    static boolean validSurrogatePairAt(CharSequence charSequence, int i) {
        if (i < 0 || i > charSequence.length() - 2 || !Character.isHighSurrogate(charSequence.charAt(i)) || !Character.isLowSurrogate(charSequence.charAt(i + 1))) {
            return false;
        }
        return true;
    }
}
