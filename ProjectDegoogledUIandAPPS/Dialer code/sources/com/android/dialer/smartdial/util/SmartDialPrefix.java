package com.android.dialer.smartdial.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.android.dialer.smartdial.map.CompositeSmartDialMap;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SmartDialPrefix {
    private static final String PREF_USER_SIM_COUNTRY_CODE_DEFAULT = null;
    private static Set<String> countryCodes = null;
    private static Set<String> nanpCountries = null;
    private static boolean userInNanpRegion = false;
    private static String userSimCountryCode = PREF_USER_SIM_COUNTRY_CODE_DEFAULT;

    public static class PhoneNumberTokens {
        final int countryCodeOffset;
        final int nanpCodeOffset;

        public PhoneNumberTokens(String str, int i, int i2) {
            this.countryCodeOffset = i;
            this.nanpCodeOffset = i2;
        }
    }

    public static ArrayList<String> generateNamePrefixes(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        int length = str.length();
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            char normalizeCharacter = CompositeSmartDialMap.normalizeCharacter(context, str.charAt(i));
            if (CompositeSmartDialMap.isValidDialpadCharacter(context, normalizeCharacter)) {
                sb.append(CompositeSmartDialMap.getDialpadIndex(context, normalizeCharacter));
            } else {
                if (sb.length() != 0) {
                    arrayList2.add(sb.toString());
                }
                sb.delete(0, sb.length());
            }
        }
        if (sb.length() != 0) {
            arrayList2.add(sb.toString());
        }
        if (arrayList2.size() > 0) {
            StringBuilder sb2 = new StringBuilder();
            for (int size = arrayList2.size() - 1; size >= 0; size--) {
                sb2.insert(0, (String) arrayList2.get(size));
                arrayList.add(sb2.toString());
            }
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add((String) arrayList2.get(arrayList2.size() - 1));
            int size2 = arrayList.size();
            int size3 = arrayList.size();
            for (int size4 = arrayList2.size() - 2; size4 >= 0; size4--) {
                if (size4 >= arrayList2.size() - 2 || size4 < 2) {
                    String substring = ((String) arrayList2.get(size4)).substring(0, 1);
                    for (int i2 = 0; i2 < arrayList3.size(); i2++) {
                        StringBuilder outline13 = GeneratedOutlineSupport.outline13(substring);
                        outline13.append((String) arrayList3.get(i2));
                        arrayList.add(outline13.toString());
                    }
                    for (int i3 = size2; i3 < size3; i3++) {
                        StringBuilder outline132 = GeneratedOutlineSupport.outline13(substring);
                        outline132.append(arrayList.get(i3));
                        arrayList.add(outline132.toString());
                    }
                    size3 = arrayList.size();
                    arrayList3.add(((String) arrayList2.get(size4)) + ((String) arrayList3.get(arrayList3.size() - 1)));
                }
            }
        }
        return arrayList;
    }

    public static void initializeNanpSettings(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager != null) {
            userSimCountryCode = telephonyManager.getSimCountryIso();
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        if (userSimCountryCode != null) {
            defaultSharedPreferences.edit().putString("DialtactsActivity_user_sim_country_code", userSimCountryCode).apply();
        } else {
            userSimCountryCode = defaultSharedPreferences.getString("DialtactsActivity_user_sim_country_code", PREF_USER_SIM_COUNTRY_CODE_DEFAULT);
        }
        userInNanpRegion = isCountryNanp(userSimCountryCode);
    }

    public static boolean isCountryNanp(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (nanpCountries == null) {
            HashSet hashSet = new HashSet();
            hashSet.add("US");
            hashSet.add("CA");
            hashSet.add("AS");
            hashSet.add("AI");
            hashSet.add("AG");
            GeneratedOutlineSupport.outline18(hashSet, "BS", "BB", "BM", "VG");
            GeneratedOutlineSupport.outline18(hashSet, "KY", "DM", "DO", "GD");
            GeneratedOutlineSupport.outline18(hashSet, "GU", "JM", "PR", "MS");
            GeneratedOutlineSupport.outline18(hashSet, "MP", "KN", "LC", "VC");
            hashSet.add("TT");
            hashSet.add("TC");
            hashSet.add("VI");
            nanpCountries = hashSet;
        }
        return nanpCountries.contains(str.toUpperCase());
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x02c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.android.dialer.smartdial.util.SmartDialPrefix.PhoneNumberTokens parsePhoneNumber(android.content.Context r14, java.lang.String r15) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r15)
            java.lang.String r1 = ""
            r2 = 0
            if (r0 != 0) goto L_0x02ff
            java.lang.String r14 = com.android.dialer.smartdial.util.SmartDialNameMatcher.normalizeNumber(r14, r15)
            char r0 = r15.charAt(r2)
            r3 = 43
            r4 = -1
            r5 = 4
            r6 = 11
            java.lang.String r7 = "1"
            r8 = 1
            if (r0 != r3) goto L_0x02a1
            r3 = r1
            r0 = r8
        L_0x001e:
            if (r0 > r5) goto L_0x02c1
            int r9 = r15.length()
            if (r9 > r0) goto L_0x0028
            goto L_0x02c1
        L_0x0028:
            java.lang.String r3 = r15.substring(r8, r0)
            java.util.Set<java.lang.String> r9 = countryCodes
            if (r9 != 0) goto L_0x0294
            java.util.HashSet r9 = new java.util.HashSet
            r9.<init>()
            r9.add(r7)
            java.lang.String r10 = "7"
            r9.add(r10)
            java.lang.String r10 = "20"
            r9.add(r10)
            java.lang.String r10 = "27"
            r9.add(r10)
            java.lang.String r10 = "30"
            r9.add(r10)
            java.lang.String r10 = "31"
            java.lang.String r11 = "32"
            java.lang.String r12 = "33"
            java.lang.String r13 = "34"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "36"
            java.lang.String r11 = "39"
            java.lang.String r12 = "40"
            java.lang.String r13 = "41"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "43"
            java.lang.String r11 = "44"
            java.lang.String r12 = "45"
            java.lang.String r13 = "46"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "47"
            java.lang.String r11 = "48"
            java.lang.String r12 = "49"
            java.lang.String r13 = "51"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "52"
            java.lang.String r11 = "53"
            java.lang.String r12 = "54"
            java.lang.String r13 = "55"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "56"
            java.lang.String r11 = "57"
            java.lang.String r12 = "58"
            java.lang.String r13 = "60"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "61"
            java.lang.String r11 = "62"
            java.lang.String r12 = "63"
            java.lang.String r13 = "64"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "65"
            java.lang.String r11 = "66"
            java.lang.String r12 = "81"
            java.lang.String r13 = "82"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "84"
            java.lang.String r11 = "86"
            java.lang.String r12 = "90"
            java.lang.String r13 = "91"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "92"
            java.lang.String r11 = "93"
            java.lang.String r12 = "94"
            java.lang.String r13 = "95"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "98"
            java.lang.String r11 = "211"
            java.lang.String r12 = "212"
            java.lang.String r13 = "213"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "216"
            java.lang.String r11 = "218"
            java.lang.String r12 = "220"
            java.lang.String r13 = "221"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "222"
            java.lang.String r11 = "223"
            java.lang.String r12 = "224"
            java.lang.String r13 = "225"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "226"
            java.lang.String r11 = "227"
            java.lang.String r12 = "228"
            java.lang.String r13 = "229"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "230"
            java.lang.String r11 = "231"
            java.lang.String r12 = "232"
            java.lang.String r13 = "233"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "234"
            java.lang.String r11 = "235"
            java.lang.String r12 = "236"
            java.lang.String r13 = "237"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "238"
            java.lang.String r11 = "239"
            java.lang.String r12 = "240"
            java.lang.String r13 = "241"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "242"
            java.lang.String r11 = "243"
            java.lang.String r12 = "244"
            java.lang.String r13 = "245"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "246"
            java.lang.String r11 = "247"
            java.lang.String r12 = "248"
            java.lang.String r13 = "249"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "250"
            java.lang.String r11 = "251"
            java.lang.String r12 = "252"
            java.lang.String r13 = "253"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "254"
            java.lang.String r11 = "255"
            java.lang.String r12 = "256"
            java.lang.String r13 = "257"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "258"
            java.lang.String r11 = "260"
            java.lang.String r12 = "261"
            java.lang.String r13 = "262"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "263"
            java.lang.String r11 = "264"
            java.lang.String r12 = "265"
            java.lang.String r13 = "266"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "267"
            java.lang.String r11 = "268"
            java.lang.String r12 = "269"
            java.lang.String r13 = "290"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "291"
            java.lang.String r11 = "297"
            java.lang.String r12 = "298"
            java.lang.String r13 = "299"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "350"
            java.lang.String r11 = "351"
            java.lang.String r12 = "352"
            java.lang.String r13 = "353"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "354"
            java.lang.String r11 = "355"
            java.lang.String r12 = "356"
            java.lang.String r13 = "357"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "358"
            java.lang.String r11 = "359"
            java.lang.String r12 = "370"
            java.lang.String r13 = "371"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "372"
            java.lang.String r11 = "373"
            java.lang.String r12 = "374"
            java.lang.String r13 = "375"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "376"
            java.lang.String r11 = "377"
            java.lang.String r12 = "378"
            java.lang.String r13 = "379"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "380"
            java.lang.String r11 = "381"
            java.lang.String r12 = "382"
            java.lang.String r13 = "385"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "386"
            java.lang.String r11 = "387"
            java.lang.String r12 = "389"
            java.lang.String r13 = "420"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "421"
            java.lang.String r11 = "423"
            java.lang.String r12 = "500"
            java.lang.String r13 = "501"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "502"
            java.lang.String r11 = "503"
            java.lang.String r12 = "504"
            java.lang.String r13 = "505"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "506"
            java.lang.String r11 = "507"
            java.lang.String r12 = "508"
            java.lang.String r13 = "509"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "590"
            java.lang.String r11 = "591"
            java.lang.String r12 = "592"
            java.lang.String r13 = "593"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "594"
            java.lang.String r11 = "595"
            java.lang.String r12 = "596"
            java.lang.String r13 = "597"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "598"
            java.lang.String r11 = "599"
            java.lang.String r12 = "670"
            java.lang.String r13 = "672"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "673"
            java.lang.String r11 = "674"
            java.lang.String r12 = "675"
            java.lang.String r13 = "676"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "677"
            java.lang.String r11 = "678"
            java.lang.String r12 = "679"
            java.lang.String r13 = "680"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "681"
            java.lang.String r11 = "682"
            java.lang.String r12 = "683"
            java.lang.String r13 = "685"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "686"
            java.lang.String r11 = "687"
            java.lang.String r12 = "688"
            java.lang.String r13 = "689"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "690"
            java.lang.String r11 = "691"
            java.lang.String r12 = "692"
            java.lang.String r13 = "800"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "808"
            java.lang.String r11 = "850"
            java.lang.String r12 = "852"
            java.lang.String r13 = "853"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "855"
            java.lang.String r11 = "856"
            java.lang.String r12 = "870"
            java.lang.String r13 = "878"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "880"
            java.lang.String r11 = "881"
            java.lang.String r12 = "882"
            java.lang.String r13 = "883"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "886"
            java.lang.String r11 = "888"
            java.lang.String r12 = "960"
            java.lang.String r13 = "961"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "962"
            java.lang.String r11 = "963"
            java.lang.String r12 = "964"
            java.lang.String r13 = "965"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "966"
            java.lang.String r11 = "967"
            java.lang.String r12 = "968"
            java.lang.String r13 = "970"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "971"
            java.lang.String r11 = "972"
            java.lang.String r12 = "973"
            java.lang.String r13 = "974"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "975"
            java.lang.String r11 = "976"
            java.lang.String r12 = "977"
            java.lang.String r13 = "979"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "992"
            java.lang.String r11 = "993"
            java.lang.String r12 = "994"
            java.lang.String r13 = "995"
            com.android.tools.p006r8.GeneratedOutlineSupport.outline18(r9, r10, r11, r12, r13)
            java.lang.String r10 = "996"
            r9.add(r10)
            java.lang.String r10 = "998"
            r9.add(r10)
            countryCodes = r9
        L_0x0294:
            java.util.Set<java.lang.String> r9 = countryCodes
            boolean r9 = r9.contains(r3)
            if (r9 == 0) goto L_0x029d
            goto L_0x02c2
        L_0x029d:
            int r0 = r0 + 1
            goto L_0x001e
        L_0x02a1:
            int r0 = r14.length()
            if (r0 != r6) goto L_0x02c0
            char r0 = r14.charAt(r2)
            r3 = 49
            if (r0 != r3) goto L_0x02c0
            boolean r0 = userInNanpRegion
            if (r0 == 0) goto L_0x02c0
            char r0 = r14.charAt(r8)
            int r0 = r15.indexOf(r0)
            if (r0 != r4) goto L_0x02be
            r0 = r2
        L_0x02be:
            r3 = r7
            goto L_0x02c2
        L_0x02c0:
            r3 = r1
        L_0x02c1:
            r0 = r2
        L_0x02c2:
            boolean r9 = userInNanpRegion
            if (r9 == 0) goto L_0x0301
            boolean r9 = r3.equals(r1)
            r10 = 3
            if (r9 == 0) goto L_0x02da
            int r9 = r14.length()
            r11 = 10
            if (r9 != r11) goto L_0x02da
            java.lang.String r14 = r14.substring(r2, r10)
            goto L_0x02ec
        L_0x02da:
            boolean r7 = r3.equals(r7)
            if (r7 == 0) goto L_0x02eb
            int r7 = r14.length()
            if (r7 != r6) goto L_0x02eb
            java.lang.String r14 = r14.substring(r8, r5)
            goto L_0x02ec
        L_0x02eb:
            r14 = r1
        L_0x02ec:
            boolean r1 = r14.equals(r1)
            if (r1 != 0) goto L_0x0301
            int r1 = r15.indexOf(r14)
            if (r1 == r4) goto L_0x0301
            int r14 = r15.indexOf(r14)
            int r2 = r14 + 3
            goto L_0x0301
        L_0x02ff:
            r3 = r1
            r0 = r2
        L_0x0301:
            com.android.dialer.smartdial.util.SmartDialPrefix$PhoneNumberTokens r14 = new com.android.dialer.smartdial.util.SmartDialPrefix$PhoneNumberTokens
            r14.<init>(r3, r0, r2)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.smartdial.util.SmartDialPrefix.parsePhoneNumber(android.content.Context, java.lang.String):com.android.dialer.smartdial.util.SmartDialPrefix$PhoneNumberTokens");
    }

    public static ArrayList<String> parseToNumberTokens(Context context, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        if (!TextUtils.isEmpty(str)) {
            arrayList.add(SmartDialNameMatcher.normalizeNumber(context, str));
            PhoneNumberTokens parsePhoneNumber = parsePhoneNumber(context, str);
            if (parsePhoneNumber == null) {
                return arrayList;
            }
            int i = parsePhoneNumber.countryCodeOffset;
            if (i != 0) {
                arrayList.add(SmartDialNameMatcher.normalizeNumber(context, str, i));
            }
            int i2 = parsePhoneNumber.nanpCodeOffset;
            if (i2 != 0) {
                arrayList.add(SmartDialNameMatcher.normalizeNumber(context, str, i2));
            }
        }
        return arrayList;
    }

    public static void setUserInNanpRegion(boolean z) {
        userInNanpRegion = z;
    }
}
