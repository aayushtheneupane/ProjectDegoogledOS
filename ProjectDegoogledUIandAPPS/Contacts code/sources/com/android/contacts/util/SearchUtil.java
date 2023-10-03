package com.android.contacts.util;

public class SearchUtil {

    public static class MatchedLine {
        public String line;
        public int startIndex = -1;

        public String toString() {
            return "MatchedLine{line='" + this.line + '\'' + ", startIndex=" + this.startIndex + '}';
        }
    }

    public static MatchedLine findMatchingLine(String str, String str2) {
        MatchedLine matchedLine = new MatchedLine();
        int contains = contains(str, str2);
        if (contains != -1) {
            int i = contains - 1;
            while (i > -1 && str.charAt(i) != 10) {
                i--;
            }
            int i2 = contains + 1;
            while (i2 < str.length() && str.charAt(i2) != 10) {
                i2++;
            }
            int i3 = i + 1;
            matchedLine.line = str.substring(i3, i2);
            matchedLine.startIndex = contains - i3;
        }
        return matchedLine;
    }

    static int contains(String str, String str2) {
        int lowerCase;
        if (str.length() < str2.length()) {
            return -1;
        }
        int[] iArr = new int[str2.length()];
        int i = 0;
        int i2 = 0;
        while (i < str2.length()) {
            int codePointAt = Character.codePointAt(str2, i);
            iArr[i2] = codePointAt;
            i2++;
            i += Character.charCount(codePointAt);
        }
        int i3 = 0;
        while (i3 < str.length()) {
            int i4 = i3;
            int i5 = 0;
            while (i4 < str.length() && i5 < i2 && (lowerCase = Character.toLowerCase(str.codePointAt(i4))) == iArr[i5]) {
                i4 += Character.charCount(lowerCase);
                i5++;
            }
            if (i5 == i2) {
                return i3;
            }
            i3 = findNextTokenStart(str, i3);
        }
        return -1;
    }

    static int findNextTokenStart(String str, int i) {
        while (i <= str.length()) {
            if (i == str.length()) {
                return i;
            }
            int codePointAt = str.codePointAt(i);
            if (!Character.isLetterOrDigit(codePointAt)) {
                break;
            }
            i += Character.charCount(codePointAt);
        }
        while (i <= str.length() && i != str.length()) {
            int codePointAt2 = str.codePointAt(i);
            if (Character.isLetterOrDigit(codePointAt2)) {
                return i;
            }
            i += Character.charCount(codePointAt2);
        }
        return i;
    }

    public static String cleanStartAndEndOfSearchQuery(String str) {
        int i = 0;
        while (i < str.length()) {
            int codePointAt = str.codePointAt(i);
            if (Character.isLetterOrDigit(codePointAt)) {
                break;
            }
            i += Character.charCount(codePointAt);
        }
        if (i == str.length()) {
            return "";
        }
        int length = str.length();
        while (true) {
            length--;
            if (length <= -1) {
                break;
            }
            if (Character.isLowSurrogate(str.charAt(length))) {
                length--;
            }
            if (Character.isLetterOrDigit(str.codePointAt(length))) {
                break;
            }
        }
        return str.substring(i, length + 1);
    }
}
