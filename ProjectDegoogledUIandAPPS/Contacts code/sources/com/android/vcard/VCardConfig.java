package com.android.vcard;

import android.util.Log;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VCardConfig {
    public static int VCARD_TYPE_DEFAULT = -1073741824;
    static String VCARD_TYPE_V21_GENERIC_STR = "v21_generic";
    private static final Set<Integer> sJapaneseMobileTypeSet = new HashSet();
    private static final Map<String, Integer> sVCardTypeMap = new HashMap();

    public static int getNameOrderType(int i) {
        return i & 12;
    }

    public static boolean isDoCoMo(int i) {
        return (i & 536870912) != 0;
    }

    public static boolean isVersion21(int i) {
        return (i & 3) == 0;
    }

    public static boolean isVersion30(int i) {
        return (i & 3) == 1;
    }

    public static boolean isVersion40(int i) {
        return (i & 3) == 2;
    }

    public static boolean needsToConvertPhoneticString(int i) {
        return (i & 134217728) != 0;
    }

    public static boolean onlyOneNoteFieldIsAvailable(int i) {
        return i == 939524104;
    }

    static boolean refrainPhoneNumberFormatting(int i) {
        return (i & 33554432) != 0;
    }

    public static boolean showPerformanceLog() {
        return false;
    }

    public static boolean usesAndroidSpecificProperty(int i) {
        return (i & Integer.MIN_VALUE) != 0;
    }

    public static boolean usesDefactProperty(int i) {
        return (i & 1073741824) != 0;
    }

    static {
        sVCardTypeMap.put(VCARD_TYPE_V21_GENERIC_STR, -1073741824);
        sVCardTypeMap.put("v30_generic", -1073741823);
        sVCardTypeMap.put("v21_europe", -1073741820);
        sVCardTypeMap.put("v30_europe", -1073741819);
        sVCardTypeMap.put("v21_japanese_utf8", -1073741816);
        sVCardTypeMap.put("v30_japanese_utf8", -1073741815);
        sVCardTypeMap.put("v21_japanese_mobile", 402653192);
        sVCardTypeMap.put("docomo", 939524104);
        sJapaneseMobileTypeSet.add(-1073741816);
        sJapaneseMobileTypeSet.add(-1073741815);
        sJapaneseMobileTypeSet.add(402653192);
        sJapaneseMobileTypeSet.add(939524104);
    }

    public static int getVCardTypeFromString(String str) {
        String lowerCase = str.toLowerCase();
        if (sVCardTypeMap.containsKey(lowerCase)) {
            return sVCardTypeMap.get(lowerCase).intValue();
        }
        if ("default".equalsIgnoreCase(str)) {
            return VCARD_TYPE_DEFAULT;
        }
        Log.e("vCard", "Unknown vCard type String: \"" + str + "\"");
        return VCARD_TYPE_DEFAULT;
    }

    public static boolean shouldUseQuotedPrintable(int i) {
        return !isVersion30(i);
    }

    public static boolean shouldRefrainQPToNameProperties(int i) {
        return !shouldUseQuotedPrintable(i) || (i & 268435456) != 0;
    }

    public static boolean appendTypeParamName(int i) {
        return isVersion30(i) || (i & 67108864) != 0;
    }

    public static boolean isJapaneseDevice(int i) {
        return sJapaneseMobileTypeSet.contains(Integer.valueOf(i));
    }
}
