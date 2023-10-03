package com.android.vcard;

import android.telephony.PhoneNumberUtils;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import com.android.vcard.exception.VCardException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VCardUtils {
    private static final String LOG_TAG = "vCard";
    private static final int[] sEscapeIndicatorsV30 = {58, 59, 44, 32};
    private static final int[] sEscapeIndicatorsV40 = {59, 58};
    private static final Map sKnownImPropNameMap_ItoS = new HashMap();
    private static final Map sKnownPhoneTypeMap_StoI = new HashMap();
    private static final Map sKnownPhoneTypesMap_ItoS = new HashMap();
    private static final Set sMobilePhoneLabelSet = new HashSet(Arrays.asList(new String[]{"MOBILE", "携帯電話", "携帯", "ケイタイ", "ｹｲﾀｲ"}));
    private static final Set sPhoneTypesUnknownToContactsSet = new HashSet();
    private static final Set sUnAcceptableAsciiInV21WordSet = new HashSet(Arrays.asList(new Character[]{'[', ']', '=', ':', '.', ',', ' '}));

    class DecoderException extends Exception {
        public DecoderException(String str) {
            super(str);
        }
    }

    public class PhoneNumberUtilsPort {
        public static String formatNumber(String str, int i) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            PhoneNumberUtils.formatNumber(spannableStringBuilder, i);
            return spannableStringBuilder.toString();
        }
    }

    public class TextUtilsPort {
        public static boolean isPrintableAscii(char c) {
            return (' ' <= c && c <= '~') || c == 13 || c == 10;
        }

        public static boolean isPrintableAsciiOnly(CharSequence charSequence) {
            int length = charSequence.length();
            for (int i = 0; i < length; i++) {
                if (!isPrintableAscii(charSequence.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    static {
        sKnownPhoneTypesMap_ItoS.put(9, VCardConstants.PARAM_TYPE_CAR);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_TYPE_CAR, 9);
        sKnownPhoneTypesMap_ItoS.put(6, VCardConstants.PARAM_TYPE_PAGER);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_TYPE_PAGER, 6);
        sKnownPhoneTypesMap_ItoS.put(11, VCardConstants.PARAM_TYPE_ISDN);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_TYPE_ISDN, 11);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_TYPE_HOME, 1);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_TYPE_WORK, 3);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_TYPE_CELL, 2);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_PHONE_EXTRA_TYPE_OTHER, 7);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_PHONE_EXTRA_TYPE_CALLBACK, 8);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_PHONE_EXTRA_TYPE_COMPANY_MAIN, 10);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_PHONE_EXTRA_TYPE_RADIO, 14);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_PHONE_EXTRA_TYPE_TTY_TDD, 16);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_PHONE_EXTRA_TYPE_ASSISTANT, 19);
        sKnownPhoneTypeMap_StoI.put(VCardConstants.PARAM_TYPE_VOICE, 7);
        sPhoneTypesUnknownToContactsSet.add(VCardConstants.PARAM_TYPE_MODEM);
        sPhoneTypesUnknownToContactsSet.add(VCardConstants.PARAM_TYPE_MSG);
        sPhoneTypesUnknownToContactsSet.add(VCardConstants.PARAM_TYPE_BBS);
        sPhoneTypesUnknownToContactsSet.add(VCardConstants.PARAM_TYPE_VIDEO);
        sKnownImPropNameMap_ItoS.put(0, VCardConstants.PROPERTY_X_AIM);
        sKnownImPropNameMap_ItoS.put(1, VCardConstants.PROPERTY_X_MSN);
        sKnownImPropNameMap_ItoS.put(2, VCardConstants.PROPERTY_X_YAHOO);
        sKnownImPropNameMap_ItoS.put(3, VCardConstants.PROPERTY_X_SKYPE_USERNAME);
        sKnownImPropNameMap_ItoS.put(5, VCardConstants.PROPERTY_X_GOOGLE_TALK);
        sKnownImPropNameMap_ItoS.put(6, VCardConstants.PROPERTY_X_ICQ);
        sKnownImPropNameMap_ItoS.put(7, VCardConstants.PROPERTY_X_JABBER);
        sKnownImPropNameMap_ItoS.put(4, VCardConstants.PROPERTY_X_QQ);
        sKnownImPropNameMap_ItoS.put(8, VCardConstants.PROPERTY_X_NETMEETING);
    }

    private VCardUtils() {
    }

    public static boolean appearsLikeAndroidVCardQuotedPrintable(String str) {
        int length = str.length() % 3;
        if (str.length() < 2 || (length != 1 && length != 0)) {
            return false;
        }
        for (int i = 0; i < str.length(); i += 3) {
            if (str.charAt(i) != '=') {
                return false;
            }
        }
        return true;
    }

    public static boolean areAllEmpty(String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String isEmpty : strArr) {
            if (!TextUtils.isEmpty(isEmpty)) {
                return false;
            }
        }
        return true;
    }

    public static List constructListFromValue(String str, int i) {
        String str2;
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt == '\\' && i2 < length - 1) {
                int i3 = i2 + 1;
                char charAt2 = str.charAt(i3);
                String str3 = "\n";
                if (VCardConfig.isVersion40(i)) {
                    if (!(charAt2 == 'n' || charAt2 == 'N')) {
                        str3 = String.valueOf(charAt2);
                    }
                } else if (!VCardConfig.isVersion30(i)) {
                    if (!VCardConfig.isVersion21(i)) {
                        Log.w(LOG_TAG, "Unknown vCard type");
                    }
                    if (charAt2 == '\\' || charAt2 == ';' || charAt2 == ':' || charAt2 == ',') {
                        str2 = String.valueOf(charAt2);
                    } else {
                        str2 = null;
                    }
                    str3 = str2;
                } else if (!(charAt2 == 'n' || charAt2 == 'N')) {
                    str3 = String.valueOf(charAt2);
                }
                if (str3 != null) {
                    sb.append(str3);
                    i2 = i3;
                } else {
                    sb.append(charAt);
                }
            } else if (charAt == ';') {
                arrayList.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(charAt);
            }
            i2++;
        }
        arrayList.add(sb.toString());
        return arrayList;
    }

    public static String constructNameFromElements(int i, String str, String str2, String str3) {
        return constructNameFromElements(i, str, str2, str3, (String) null, (String) null);
    }

    public static boolean containsOnlyAlphaDigitHyphen(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return containsOnlyAlphaDigitHyphen((Collection) Arrays.asList(strArr));
    }

    public static boolean containsOnlyNonCrLfPrintableAscii(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return containsOnlyNonCrLfPrintableAscii((Collection) Arrays.asList(strArr));
    }

    public static boolean containsOnlyPrintableAscii(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return containsOnlyPrintableAscii((Collection) Arrays.asList(strArr));
    }

    public static boolean containsOnlyWhiteSpaces(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return containsOnlyWhiteSpaces((Collection) Arrays.asList(strArr));
    }

    public static final String convertStringCharset(String str, String str2, String str3) {
        if (str2.equalsIgnoreCase(str3)) {
            return str;
        }
        ByteBuffer encode = Charset.forName(str2).encode(str);
        byte[] bArr = new byte[encode.remaining()];
        encode.get(bArr);
        try {
            return new String(bArr, str3);
        } catch (UnsupportedEncodingException unused) {
            Log.e(LOG_TAG, "Failed to encode: charset=" + str3);
            return null;
        }
    }

    public static final VCardParser getAppropriateParser(int i) {
        if (VCardConfig.isVersion21(i)) {
            return new VCardParser_V21();
        }
        if (VCardConfig.isVersion30(i)) {
            return new VCardParser_V30();
        }
        if (VCardConfig.isVersion40(i)) {
            return new VCardParser_V40();
        }
        throw new VCardException("Version is not specified");
    }

    public static int getPhoneNumberFormat(int i) {
        return VCardConfig.isJapaneseDevice(i) ? 2 : 1;
    }

    public static Object getPhoneTypeFromStrings(Collection collection, String str) {
        String str2;
        boolean z;
        if (str == null) {
            str = "";
        }
        int i = -1;
        boolean z2 = false;
        if (collection != null) {
            Iterator it = collection.iterator();
            int i2 = -1;
            str2 = null;
            boolean z3 = false;
            z = false;
            while (it.hasNext()) {
                String str3 = (String) it.next();
                if (str3 != null) {
                    String upperCase = str3.toUpperCase();
                    if (upperCase.equals(VCardConstants.PARAM_TYPE_PREF)) {
                        z = true;
                    } else if (upperCase.equals(VCardConstants.PARAM_TYPE_FAX)) {
                        z3 = true;
                    } else {
                        if (upperCase.startsWith("X-") && i2 < 0) {
                            str3 = str3.substring(2);
                        }
                        if (str3.length() != 0) {
                            Integer num = (Integer) sKnownPhoneTypeMap_StoI.get(str3.toUpperCase());
                            if (num != null) {
                                int intValue = num.intValue();
                                int indexOf = str.indexOf("@");
                                if ((intValue == 6 && indexOf > 0 && indexOf < str.length() - 1) || i2 < 0 || i2 == 0 || i2 == 7) {
                                    i2 = num.intValue();
                                }
                            } else if (i2 < 0) {
                                i2 = 0;
                                str2 = str3;
                            }
                        }
                    }
                }
            }
            z2 = z3;
            i = i2;
        } else {
            str2 = null;
            z = false;
        }
        int i3 = i < 0 ? z ? 12 : 1 : i;
        if (z2) {
            if (i3 == 1) {
                i3 = 5;
            } else if (i3 == 3) {
                i3 = 4;
            } else if (i3 == 7) {
                i3 = 13;
            }
        }
        if (i3 == 0) {
            return str2;
        }
        return Integer.valueOf(i3);
    }

    public static String getPhoneTypeString(Integer num) {
        return (String) sKnownPhoneTypesMap_ItoS.get(num);
    }

    public static String getPropertyNameForIm(int i) {
        return (String) sKnownImPropNameMap_ItoS.get(Integer.valueOf(i));
    }

    public static String guessImageType(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        if (bArr.length >= 3 && bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70) {
            return "GIF";
        }
        if (bArr.length >= 4 && bArr[0] == -119 && bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71) {
            return "PNG";
        }
        if (bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -40) {
            return "JPEG";
        }
        return null;
    }

    public static boolean isMobilePhoneLabel(String str) {
        return "_AUTO_CELL".equals(str) || sMobilePhoneLabelSet.contains(str);
    }

    public static boolean isV21Word(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (32 > codePointAt || codePointAt > 126 || sUnAcceptableAsciiInV21WordSet.contains(Character.valueOf((char) codePointAt))) {
                return false;
            }
            i = str.offsetByCodePoints(i, 1);
        }
        return true;
    }

    public static boolean isValidInV21ButUnknownToContactsPhoteType(String str) {
        return sPhoneTypesUnknownToContactsSet.contains(str);
    }

    public static String parseQuotedPrintable(String str, boolean z, String str2, String str3) {
        String[] strArr;
        byte[] bArr;
        int i;
        char charAt;
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i2 = 0;
        while (i2 < length) {
            char charAt2 = str.charAt(i2);
            if (charAt2 == '=' && i2 < length - 1 && ((charAt = str.charAt(i)) == ' ' || charAt == 9)) {
                sb.append(charAt);
                i2++;
            } else {
                sb.append(charAt2);
            }
            i2++;
        }
        String sb2 = sb.toString();
        if (z) {
            strArr = sb2.split(VCardBuilder.VCARD_END_OF_LINE);
        } else {
            StringBuilder sb3 = new StringBuilder();
            int length2 = sb2.length();
            ArrayList arrayList = new ArrayList();
            StringBuilder sb4 = sb3;
            int i3 = 0;
            while (i3 < length2) {
                char charAt3 = sb2.charAt(i3);
                if (charAt3 == 10) {
                    arrayList.add(sb4.toString());
                    sb4 = new StringBuilder();
                } else if (charAt3 == 13) {
                    arrayList.add(sb4.toString());
                    sb4 = new StringBuilder();
                    if (i3 < length2 - 1) {
                        int i4 = i3 + 1;
                        if (sb2.charAt(i4) == 10) {
                            i3 = i4;
                        }
                    }
                } else {
                    sb4.append(charAt3);
                }
                i3++;
            }
            String sb5 = sb4.toString();
            if (sb5.length() > 0) {
                arrayList.add(sb5);
            }
            strArr = (String[]) arrayList.toArray(new String[0]);
        }
        StringBuilder sb6 = new StringBuilder();
        for (String str4 : strArr) {
            if (str4.endsWith("=")) {
                str4 = str4.substring(0, str4.length() - 1);
            }
            sb6.append(str4);
        }
        String sb7 = sb6.toString();
        if (TextUtils.isEmpty(sb7)) {
            Log.w(LOG_TAG, "Given raw string is empty.");
        }
        try {
            bArr = sb7.getBytes(str2);
        } catch (UnsupportedEncodingException unused) {
            Log.w(LOG_TAG, "Failed to decode: " + str2);
            bArr = sb7.getBytes();
        }
        try {
            bArr = C1503j.m3942r(bArr);
        } catch (DecoderException unused2) {
            Log.e(LOG_TAG, "DecoderException is thrown.");
        }
        try {
            return new String(bArr, str3);
        } catch (UnsupportedEncodingException unused3) {
            Log.e(LOG_TAG, "Failed to encode: charset=" + str3);
            return new String(bArr);
        }
    }

    public static String[] sortNameElements(int i, String str, String str2, String str3) {
        String[] strArr = new String[3];
        int nameOrderType = VCardConfig.getNameOrderType(i);
        if (nameOrderType == 4) {
            strArr[0] = str2;
            strArr[1] = str3;
            strArr[2] = str;
        } else if (nameOrderType != 8) {
            strArr[0] = str3;
            strArr[1] = str2;
            strArr[2] = str;
        } else {
            if (containsOnlyPrintableAscii(str)) {
                if (containsOnlyPrintableAscii(str3)) {
                    strArr[0] = str3;
                    strArr[1] = str2;
                    strArr[2] = str;
                }
            }
            strArr[0] = str;
            strArr[1] = str2;
            strArr[2] = str3;
        }
        return strArr;
    }

    public static String toHalfWidthString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            String a = C1494a.m3907a(charAt);
            if (a != null) {
                sb.append(a);
            } else {
                sb.append(charAt);
            }
            i = str.offsetByCodePoints(i, 1);
        }
        return sb.toString();
    }

    private static String toStringAsParamValue(String str, int[] iArr) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i = str.offsetByCodePoints(i, 1)) {
            int codePointAt = str.codePointAt(i);
            if (codePointAt >= 32 && codePointAt != 34) {
                sb.appendCodePoint(codePointAt);
                int length2 = iArr.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length2) {
                        break;
                    } else if (codePointAt == iArr[i2]) {
                        z = true;
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        String sb2 = sb.toString();
        if (sb2.isEmpty()) {
            return "";
        }
        if (containsOnlyWhiteSpaces(sb2)) {
            return "";
        }
        if (!z) {
            return sb2;
        }
        return '\"' + sb2 + '\"';
    }

    public static String toStringAsV30ParamValue(String str) {
        return toStringAsParamValue(str, sEscapeIndicatorsV30);
    }

    public static String toStringAsV40ParamValue(String str) {
        return toStringAsParamValue(str, sEscapeIndicatorsV40);
    }

    public static String constructNameFromElements(int i, String str, String str2, String str3, String str4, String str5) {
        boolean z;
        StringBuilder sb = new StringBuilder();
        String[] sortNameElements = sortNameElements(i, str, str2, str3);
        if (!TextUtils.isEmpty(str4)) {
            sb.append(str4);
            z = false;
        } else {
            z = true;
        }
        boolean z2 = z;
        for (String str6 : sortNameElements) {
            if (!TextUtils.isEmpty(str6)) {
                if (z2) {
                    z2 = false;
                } else {
                    sb.append(' ');
                }
                sb.append(str6);
            }
        }
        if (!TextUtils.isEmpty(str5)) {
            if (!z2) {
                sb.append(' ');
            }
            sb.append(str5);
        }
        return sb.toString();
    }

    public static boolean containsOnlyAlphaDigitHyphen(Collection collection) {
        if (collection == null) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!TextUtils.isEmpty(str)) {
                int length = str.length();
                for (int i = 0; i < length; i = str.offsetByCodePoints(i, 1)) {
                    int codePointAt = str.codePointAt(i);
                    if ((97 > codePointAt || codePointAt >= 123) && ((65 > codePointAt || codePointAt >= 91) && ((48 > codePointAt || codePointAt >= 58) && codePointAt != 45))) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    public static boolean containsOnlyNonCrLfPrintableAscii(Collection collection) {
        if (collection == null) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!TextUtils.isEmpty(str)) {
                int length = str.length();
                for (int i = 0; i < length; i = str.offsetByCodePoints(i, 1)) {
                    int codePointAt = str.codePointAt(i);
                    if (32 > codePointAt || codePointAt > 126) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    public static boolean containsOnlyPrintableAscii(Collection collection) {
        if (collection == null) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!TextUtils.isEmpty(str) && !TextUtilsPort.isPrintableAsciiOnly(str)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsOnlyWhiteSpaces(Collection collection) {
        if (collection == null) {
            return true;
        }
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (!TextUtils.isEmpty(str)) {
                int length = str.length();
                for (int i = 0; i < length; i = str.offsetByCodePoints(i, 1)) {
                    if (!Character.isWhitespace(str.codePointAt(i))) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }
}
