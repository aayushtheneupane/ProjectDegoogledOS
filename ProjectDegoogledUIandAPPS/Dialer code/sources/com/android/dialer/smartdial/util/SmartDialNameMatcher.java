package com.android.dialer.smartdial.util;

import android.content.Context;
import android.text.TextUtils;
import com.android.dialer.smartdial.map.CompositeSmartDialMap;
import com.android.dialer.smartdial.util.SmartDialPrefix;
import java.util.ArrayList;
import java.util.Iterator;

public class SmartDialNameMatcher {
    private final ArrayList<SmartDialMatchPosition> matchPositions = new ArrayList<>();
    private String query;
    private boolean shouldMatchEmptyQuery = false;

    public SmartDialNameMatcher(String str) {
        this.query = str;
    }

    private boolean matchesCombination(Context context, String str, String str2, ArrayList<SmartDialMatchPosition> arrayList) {
        Context context2 = context;
        String str3 = str;
        String str4 = str2;
        ArrayList<SmartDialMatchPosition> arrayList2 = arrayList;
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            sb.append("0");
        }
        int length2 = str.length();
        int length3 = str2.length();
        if (length2 < length3 || length3 == 0) {
            return false;
        }
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        ArrayList arrayList3 = new ArrayList();
        int i5 = 0;
        while (i5 < length2 && i2 < length3) {
            char normalizeCharacter = CompositeSmartDialMap.normalizeCharacter(context2, str3.charAt(i5));
            if (CompositeSmartDialMap.isValidDialpadCharacter(context2, normalizeCharacter)) {
                if (CompositeSmartDialMap.isValidDialpadAlphabeticChar(context2, normalizeCharacter)) {
                    normalizeCharacter = CompositeSmartDialMap.getDialpadNumericCharacter(context2, normalizeCharacter);
                }
                if (normalizeCharacter != str4.charAt(i2)) {
                    if (i2 == 0 || CompositeSmartDialMap.isValidDialpadCharacter(context2, CompositeSmartDialMap.normalizeCharacter(context2, str3.charAt(i5 - 1)))) {
                        while (i5 < length2 && CompositeSmartDialMap.isValidDialpadCharacter(context2, CompositeSmartDialMap.normalizeCharacter(context2, str3.charAt(i5)))) {
                            i5++;
                        }
                        i5++;
                    }
                    i4 = i5;
                    i2 = 0;
                    i3 = 0;
                } else if (i2 == length3 - 1) {
                    arrayList2.add(new SmartDialMatchPosition(i4, length3 + i4 + i3));
                    Iterator<SmartDialMatchPosition> it = arrayList.iterator();
                    while (it.hasNext()) {
                        replaceBitInMask(sb, it.next());
                    }
                    return true;
                } else {
                    if (i2 < 1) {
                        int i6 = i5;
                        while (i6 < length2 && CompositeSmartDialMap.isValidDialpadCharacter(context2, CompositeSmartDialMap.normalizeCharacter(context2, str3.charAt(i6)))) {
                            i6++;
                        }
                        if (i6 < length2 - 1) {
                            int i7 = i6 + 1;
                            String substring = str3.substring(i7);
                            ArrayList arrayList4 = new ArrayList();
                            if (matchesCombination(context2, substring, str4.substring(i2 + 1), arrayList4)) {
                                for (int i8 = 0; i8 < arrayList4.size(); i8++) {
                                    SmartDialMatchPosition smartDialMatchPosition = (SmartDialMatchPosition) arrayList4.get(i8);
                                    smartDialMatchPosition.start += i7;
                                    smartDialMatchPosition.end += i7;
                                }
                                arrayList4.add(0, new SmartDialMatchPosition(i5, i5 + 1));
                                arrayList3 = arrayList4;
                            }
                        }
                    }
                    i5++;
                    i2++;
                }
            } else {
                i5++;
                if (i2 == 0) {
                    i4 = i5;
                } else {
                    i3++;
                }
            }
            str3 = str;
        }
        if (arrayList3.isEmpty()) {
            return false;
        }
        arrayList2.addAll(arrayList3);
        Iterator<SmartDialMatchPosition> it2 = arrayList.iterator();
        while (it2.hasNext()) {
            replaceBitInMask(sb, it2.next());
        }
        return true;
    }

    private SmartDialMatchPosition matchesNumberWithOffset(Context context, String str, String str2, int i) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            int i2 = 0;
            int i3 = i;
            int i4 = i3;
            while (i < str.length() && i2 != str2.length()) {
                char charAt = str.charAt(i);
                if (CompositeSmartDialMap.isValidDialpadNumericChar(context, charAt)) {
                    if (charAt != str2.charAt(i2)) {
                        return null;
                    }
                    i2++;
                } else if (i2 == 0 && i3 != 0) {
                    i3++;
                }
                i4++;
                i++;
            }
            return new SmartDialMatchPosition(i3 + 0, i4);
        } else if (this.shouldMatchEmptyQuery) {
            return new SmartDialMatchPosition(i, i);
        } else {
            return null;
        }
    }

    public static String normalizeNumber(Context context, String str) {
        return normalizeNumber(context, str, 0);
    }

    private void replaceBitInMask(StringBuilder sb, SmartDialMatchPosition smartDialMatchPosition) {
        int i = smartDialMatchPosition.start;
        while (i < smartDialMatchPosition.end) {
            int i2 = i + 1;
            sb.replace(i, i2, "1");
            i = i2;
        }
    }

    public boolean matches(Context context, String str) {
        this.matchPositions.clear();
        return matchesCombination(context, str, this.query, this.matchPositions);
    }

    public SmartDialMatchPosition matchesNumber(Context context, String str, String str2) {
        int i;
        if (!TextUtils.isEmpty(str)) {
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                sb.append("0");
            }
            SmartDialMatchPosition matchesNumberWithOffset = matchesNumberWithOffset(context, str, str2, 0);
            if (matchesNumberWithOffset == null) {
                SmartDialPrefix.PhoneNumberTokens parsePhoneNumber = SmartDialPrefix.parsePhoneNumber(context, str);
                int i3 = parsePhoneNumber.countryCodeOffset;
                if (i3 != 0) {
                    matchesNumberWithOffset = matchesNumberWithOffset(context, str, str2, i3);
                }
                if (matchesNumberWithOffset == null && (i = parsePhoneNumber.nanpCodeOffset) != 0) {
                    matchesNumberWithOffset = matchesNumberWithOffset(context, str, str2, i);
                }
            }
            if (matchesNumberWithOffset != null) {
                replaceBitInMask(sb, matchesNumberWithOffset);
            }
            return matchesNumberWithOffset;
        } else if (this.shouldMatchEmptyQuery) {
            return new SmartDialMatchPosition(0, 0);
        } else {
            return null;
        }
    }

    public void setShouldMatchEmptyQuery(boolean z) {
        this.shouldMatchEmptyQuery = z;
    }

    public static String normalizeNumber(Context context, String str, int i) {
        StringBuilder sb = new StringBuilder();
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (CompositeSmartDialMap.isValidDialpadNumericChar(context, charAt)) {
                sb.append(charAt);
            }
            i++;
        }
        return sb.toString();
    }
}
