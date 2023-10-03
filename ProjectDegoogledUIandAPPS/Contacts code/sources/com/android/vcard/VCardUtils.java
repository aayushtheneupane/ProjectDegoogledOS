package com.android.vcard;

import android.telephony.PhoneNumberUtils;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayOutputStream;
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
    private static final int[] sEscapeIndicatorsV30 = {58, 59, 44, 32};
    private static final int[] sEscapeIndicatorsV40 = {59, 58};
    private static final Map<Integer, String> sKnownImPropNameMap_ItoS = new HashMap();
    private static final Map<String, Integer> sKnownPhoneTypeMap_StoI = new HashMap();
    private static final Map<Integer, String> sKnownPhoneTypesMap_ItoS = new HashMap();
    private static final Set<String> sMobilePhoneLabelSet = new HashSet(Arrays.asList(new String[]{"MOBILE", "携帯電話", "携帯", "ケイタイ", "ｹｲﾀｲ"}));
    private static final Set<String> sPhoneTypesUnknownToContactsSet = new HashSet();
    private static final Set<Character> sUnAcceptableAsciiInV21WordSet = new HashSet(Arrays.asList(new Character[]{'[', ']', '=', ':', '.', ',', ' '}));

    private static class DecoderException extends Exception {
        public DecoderException(String str) {
            super(str);
        }
    }

    private static class QuotedPrintableCodecPort {
        private static byte ESCAPE_CHAR = 61;

        public static final byte[] decodeQuotedPrintable(byte[] bArr) throws DecoderException {
            if (bArr == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < bArr.length) {
                byte b = bArr[i];
                if (b == ESCAPE_CHAR) {
                    int i2 = i + 1;
                    try {
                        int digit = Character.digit((char) bArr[i2], 16);
                        i = i2 + 1;
                        int digit2 = Character.digit((char) bArr[i], 16);
                        if (digit == -1 || digit2 == -1) {
                            throw new DecoderException("Invalid quoted-printable encoding");
                        }
                        byteArrayOutputStream.write((char) ((digit << 4) + digit2));
                    } catch (ArrayIndexOutOfBoundsException unused) {
                        throw new DecoderException("Invalid quoted-printable encoding");
                    }
                } else {
                    byteArrayOutputStream.write(b);
                }
                i++;
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    public static class PhoneNumberUtilsPort {
        public static String formatNumber(String str, int i) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
            PhoneNumberUtils.formatNumber(spannableStringBuilder, i);
            return spannableStringBuilder.toString();
        }
    }

    public static class TextUtilsPort {
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
        sKnownPhoneTypesMap_ItoS.put(9, "CAR");
        sKnownPhoneTypeMap_StoI.put("CAR", 9);
        sKnownPhoneTypesMap_ItoS.put(6, "PAGER");
        sKnownPhoneTypeMap_StoI.put("PAGER", 6);
        sKnownPhoneTypesMap_ItoS.put(11, "ISDN");
        sKnownPhoneTypeMap_StoI.put("ISDN", 11);
        sKnownPhoneTypeMap_StoI.put("HOME", 1);
        sKnownPhoneTypeMap_StoI.put("WORK", 3);
        sKnownPhoneTypeMap_StoI.put("CELL", 2);
        sKnownPhoneTypeMap_StoI.put("OTHER", 7);
        sKnownPhoneTypeMap_StoI.put("CALLBACK", 8);
        sKnownPhoneTypeMap_StoI.put("COMPANY-MAIN", 10);
        sKnownPhoneTypeMap_StoI.put("RADIO", 14);
        sKnownPhoneTypeMap_StoI.put("TTY-TDD", 16);
        sKnownPhoneTypeMap_StoI.put("ASSISTANT", 19);
        sKnownPhoneTypeMap_StoI.put("VOICE", 7);
        sPhoneTypesUnknownToContactsSet.add("MODEM");
        sPhoneTypesUnknownToContactsSet.add("MSG");
        sPhoneTypesUnknownToContactsSet.add("BBS");
        sPhoneTypesUnknownToContactsSet.add("VIDEO");
        sKnownImPropNameMap_ItoS.put(0, "X-AIM");
        sKnownImPropNameMap_ItoS.put(1, "X-MSN");
        sKnownImPropNameMap_ItoS.put(2, "X-YAHOO");
        sKnownImPropNameMap_ItoS.put(3, "X-SKYPE-USERNAME");
        sKnownImPropNameMap_ItoS.put(5, "X-GOOGLE-TALK");
        sKnownImPropNameMap_ItoS.put(6, "X-ICQ");
        sKnownImPropNameMap_ItoS.put(7, "X-JABBER");
        sKnownImPropNameMap_ItoS.put(4, "X-QQ");
        sKnownImPropNameMap_ItoS.put(8, "X-NETMEETING");
    }

    public static String getPhoneTypeString(Integer num) {
        return sKnownPhoneTypesMap_ItoS.get(num);
    }

    public static Object getPhoneTypeFromStrings(Collection<String> collection, String str) {
        String str2;
        boolean z;
        if (str == null) {
            str = "";
        }
        int i = -1;
        boolean z2 = false;
        if (collection != null) {
            Iterator<String> it = collection.iterator();
            str2 = null;
            boolean z3 = false;
            z = false;
            int i2 = -1;
            while (it.hasNext()) {
                String next = it.next();
                if (next != null) {
                    String upperCase = next.toUpperCase();
                    if (upperCase.equals("PREF")) {
                        z = true;
                    } else if (upperCase.equals("FAX")) {
                        z3 = true;
                    } else {
                        if (upperCase.startsWith("X-") && i2 < 0) {
                            next = next.substring(2);
                        }
                        if (next.length() != 0) {
                            Integer num = sKnownPhoneTypeMap_StoI.get(next.toUpperCase());
                            if (num != null) {
                                int intValue = num.intValue();
                                int indexOf = str.indexOf("@");
                                if ((intValue == 6 && indexOf > 0 && indexOf < str.length() - 1) || i2 < 0 || i2 == 0 || i2 == 7) {
                                    i2 = num.intValue();
                                }
                            } else if (i2 < 0) {
                                str2 = next;
                                i2 = 0;
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
        return i3 == 0 ? str2 : Integer.valueOf(i3);
    }

    public static boolean isMobilePhoneLabel(String str) {
        return "_AUTO_CELL".equals(str) || sMobilePhoneLabelSet.contains(str);
    }

    public static boolean isValidInV21ButUnknownToContactsPhoteType(String str) {
        return sPhoneTypesUnknownToContactsSet.contains(str);
    }

    public static String getPropertyNameForIm(int i) {
        return sKnownImPropNameMap_ItoS.get(Integer.valueOf(i));
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

    public static int getPhoneNumberFormat(int i) {
        return VCardConfig.isJapaneseDevice(i) ? 2 : 1;
    }

    public static String constructNameFromElements(int i, String str, String str2, String str3) {
        return constructNameFromElements(i, str, str2, str3, (String) null, (String) null);
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

    public static List<String> constructListFromValue(String str, int i) {
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
                if (VCardConfig.isVersion40(i)) {
                    str2 = VCardParserImpl_V40.unescapeCharacter(charAt2);
                } else if (VCardConfig.isVersion30(i)) {
                    str2 = VCardParserImpl_V30.unescapeCharacter(charAt2);
                } else {
                    if (!VCardConfig.isVersion21(i)) {
                        Log.w("vCard", "Unknown vCard type");
                    }
                    str2 = VCardParserImpl_V21.unescapeCharacter(charAt2);
                }
                if (str2 != null) {
                    sb.append(str2);
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

    public static boolean containsOnlyPrintableAscii(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return containsOnlyPrintableAscii((Collection<String>) Arrays.asList(strArr));
    }

    public static boolean containsOnlyPrintableAscii(Collection<String> collection) {
        if (collection == null) {
            return true;
        }
        for (String next : collection) {
            if (!TextUtils.isEmpty(next) && !TextUtilsPort.isPrintableAsciiOnly(next)) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsOnlyNonCrLfPrintableAscii(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return containsOnlyNonCrLfPrintableAscii((Collection<String>) Arrays.asList(strArr));
    }

    public static boolean containsOnlyNonCrLfPrintableAscii(Collection<String> collection) {
        if (collection == null) {
            return true;
        }
        for (String next : collection) {
            if (!TextUtils.isEmpty(next)) {
                int length = next.length();
                for (int i = 0; i < length; i = next.offsetByCodePoints(i, 1)) {
                    int codePointAt = next.codePointAt(i);
                    if (32 > codePointAt || codePointAt > 126) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    public static boolean containsOnlyAlphaDigitHyphen(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return containsOnlyAlphaDigitHyphen((Collection<String>) Arrays.asList(strArr));
    }

    public static boolean containsOnlyAlphaDigitHyphen(Collection<String> collection) {
        if (collection == null) {
            return true;
        }
        for (String next : collection) {
            if (!TextUtils.isEmpty(next)) {
                int length = next.length();
                for (int i = 0; i < length; i = next.offsetByCodePoints(i, 1)) {
                    int codePointAt = next.codePointAt(i);
                    if ((97 > codePointAt || codePointAt >= 123) && ((65 > codePointAt || codePointAt >= 91) && ((48 > codePointAt || codePointAt >= 58) && codePointAt != 45))) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    public static boolean containsOnlyWhiteSpaces(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return containsOnlyWhiteSpaces((Collection<String>) Arrays.asList(strArr));
    }

    public static boolean containsOnlyWhiteSpaces(Collection<String> collection) {
        if (collection == null) {
            return true;
        }
        for (String next : collection) {
            if (!TextUtils.isEmpty(next)) {
                int length = next.length();
                for (int i = 0; i < length; i = next.offsetByCodePoints(i, 1)) {
                    if (!Character.isWhitespace(next.codePointAt(i))) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
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

    public static String toStringAsV30ParamValue(String str) {
        return toStringAsParamValue(str, sEscapeIndicatorsV30);
    }

    public static String toStringAsV40ParamValue(String str) {
        return toStringAsParamValue(str, sEscapeIndicatorsV40);
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

    public static String toHalfWidthString(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            String tryGetHalfWidthText = JapaneseUtils.tryGetHalfWidthText(charAt);
            if (tryGetHalfWidthText != null) {
                sb.append(tryGetHalfWidthText);
            } else {
                sb.append(charAt);
            }
            i = str.offsetByCodePoints(i, 1);
        }
        return sb.toString();
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

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0019, code lost:
        r5 = r3 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String parseQuotedPrintable(java.lang.String r8, boolean r9, java.lang.String r10, java.lang.String r11) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            int r1 = r8.length()
            r2 = 0
            r3 = 0
        L_0x000b:
            if (r3 >= r1) goto L_0x0032
            char r4 = r8.charAt(r3)
            r5 = 61
            if (r4 != r5) goto L_0x002c
            int r5 = r1 + -1
            if (r3 >= r5) goto L_0x002c
            int r5 = r3 + 1
            char r6 = r8.charAt(r5)
            r7 = 32
            if (r6 == r7) goto L_0x0027
            r7 = 9
            if (r6 != r7) goto L_0x002c
        L_0x0027:
            r0.append(r6)
            r3 = r5
            goto L_0x002f
        L_0x002c:
            r0.append(r4)
        L_0x002f:
            int r3 = r3 + 1
            goto L_0x000b
        L_0x0032:
            java.lang.String r8 = r0.toString()
            if (r9 == 0) goto L_0x003f
            java.lang.String r9 = "\r\n"
            java.lang.String[] r8 = r8.split(r9)
            goto L_0x009f
        L_0x003f:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            int r0 = r8.length()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r3 = r9
            r9 = 0
        L_0x004f:
            if (r9 >= r0) goto L_0x008a
            char r4 = r8.charAt(r9)
            r5 = 10
            if (r4 != r5) goto L_0x0066
            java.lang.String r3 = r3.toString()
            r1.add(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            goto L_0x0087
        L_0x0066:
            r6 = 13
            if (r4 != r6) goto L_0x0084
            java.lang.String r3 = r3.toString()
            r1.add(r3)
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            int r4 = r0 + -1
            if (r9 >= r4) goto L_0x0087
            int r4 = r9 + 1
            char r6 = r8.charAt(r4)
            if (r6 != r5) goto L_0x0087
            r9 = r4
            goto L_0x0087
        L_0x0084:
            r3.append(r4)
        L_0x0087:
            int r9 = r9 + 1
            goto L_0x004f
        L_0x008a:
            java.lang.String r8 = r3.toString()
            int r9 = r8.length()
            if (r9 <= 0) goto L_0x0097
            r1.add(r8)
        L_0x0097:
            java.lang.String[] r8 = new java.lang.String[r2]
            java.lang.Object[] r8 = r1.toArray(r8)
            java.lang.String[] r8 = (java.lang.String[]) r8
        L_0x009f:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            int r0 = r8.length
            r1 = 0
        L_0x00a6:
            if (r1 >= r0) goto L_0x00c2
            r3 = r8[r1]
            java.lang.String r4 = "="
            boolean r4 = r3.endsWith(r4)
            if (r4 == 0) goto L_0x00bc
            int r4 = r3.length()
            int r4 = r4 + -1
            java.lang.String r3 = r3.substring(r2, r4)
        L_0x00bc:
            r9.append(r3)
            int r1 = r1 + 1
            goto L_0x00a6
        L_0x00c2:
            java.lang.String r8 = r9.toString()
            boolean r9 = android.text.TextUtils.isEmpty(r8)
            java.lang.String r0 = "vCard"
            if (r9 == 0) goto L_0x00d3
            java.lang.String r9 = "Given raw string is empty."
            android.util.Log.w(r0, r9)
        L_0x00d3:
            byte[] r8 = r8.getBytes(r10)     // Catch:{ UnsupportedEncodingException -> 0x00d8 }
            goto L_0x00f0
        L_0x00d8:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r1 = "Failed to decode: "
            r9.append(r1)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            android.util.Log.w(r0, r9)
            byte[] r8 = r8.getBytes()
        L_0x00f0:
            byte[] r8 = com.android.vcard.VCardUtils.QuotedPrintableCodecPort.decodeQuotedPrintable(r8)     // Catch:{ DecoderException -> 0x00f5 }
            goto L_0x00fa
        L_0x00f5:
            java.lang.String r9 = "DecoderException is thrown."
            android.util.Log.e(r0, r9)
        L_0x00fa:
            java.lang.String r9 = new java.lang.String     // Catch:{ UnsupportedEncodingException -> 0x0100 }
            r9.<init>(r8, r11)     // Catch:{ UnsupportedEncodingException -> 0x0100 }
            return r9
        L_0x0100:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r10 = "Failed to encode: charset="
            r9.append(r10)
            r9.append(r11)
            java.lang.String r9 = r9.toString()
            android.util.Log.e(r0, r9)
            java.lang.String r9 = new java.lang.String
            r9.<init>(r8)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.vcard.VCardUtils.parseQuotedPrintable(java.lang.String, boolean, java.lang.String, java.lang.String):java.lang.String");
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
            Log.e("vCard", "Failed to encode: charset=" + str3);
            return null;
        }
    }
}
