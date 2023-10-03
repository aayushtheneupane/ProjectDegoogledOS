package com.android.dialer.phonenumberutil;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Trace;
import android.support.p002v7.appcompat.R$style;
import android.telecom.PhoneAccountHandle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.text.BidiFormatter;
import android.text.TextDirectionHeuristics;
import android.text.TextUtils;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.compat.telephony.TelephonyManagerCompat;
import com.android.dialer.inject.HasRootComponent;
import com.android.dialer.oem.MotorolaUtils;
import com.android.dialer.phonenumbergeoutil.impl.PhoneNumberGeoUtilImpl;
import com.android.dialer.telecom.TelecomUtil;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Optional;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PhoneNumberHelper {
    private static final Set<String> LEGACY_UNKNOWN_NUMBERS = new HashSet(Arrays.asList(new String[]{"-1", "-2", "-3"}));

    public static boolean canPlaceCallsTo(CharSequence charSequence, int i) {
        if (i != 1 || TextUtils.isEmpty(charSequence) || isLegacyUnknownNumbers(charSequence)) {
            return false;
        }
        return true;
    }

    public static String formatNumber(Context context, String str, String str2, String str3) {
        if (str == null) {
            return null;
        }
        if (MotorolaUtils.shouldDisablePhoneNumberFormatting(context)) {
            return str;
        }
        String formatNumber = PhoneNumberUtils.formatNumber(str, str2, str3);
        return formatNumber != null ? formatNumber : str;
    }

    public static CharSequence formatNumberForDisplay(Context context, String str, String str2) {
        if (str == null) {
            return null;
        }
        return PhoneNumberUtils.createTtsSpannable(BidiFormatter.getInstance().unicodeWrap(formatNumber(context, str, str2), TextDirectionHeuristics.LTR));
    }

    public static String getCurrentCountryIso(Context context, PhoneAccountHandle phoneAccountHandle) {
        Trace.beginSection("PhoneNumberHelper.getCurrentCountryIso");
        String networkCountryIso = TelephonyManagerCompat.getTelephonyManagerForPhoneAccountHandle(context, phoneAccountHandle).getNetworkCountryIso();
        if (TextUtils.isEmpty(networkCountryIso)) {
            networkCountryIso = R$style.getLocale(context).getCountry();
            LogUtil.m9i("PhoneNumberHelper.getCurrentCountryIso", GeneratedOutlineSupport.outline8("No CountryDetector; falling back to countryIso based on locale: ", networkCountryIso), new Object[0]);
        }
        String upperCase = networkCountryIso.toUpperCase();
        Trace.endSection();
        return upperCase;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getDisplayNameForRestrictedNumber(android.content.Context r4) {
        /*
            java.lang.Class<android.telephony.TelephonyManager> r0 = android.telephony.TelephonyManager.class
            java.lang.Object r0 = r4.getSystemService(r0)
            android.telephony.TelephonyManager r0 = (android.telephony.TelephonyManager) r0
            java.lang.String r0 = r0.getSimOperator()
            int r1 = r0.hashCode()
            r2 = 1
            r3 = 0
            switch(r1) {
                case 1506816866: goto L_0x0066;
                case 1506816893: goto L_0x005b;
                case 1506821946: goto L_0x0050;
                case 1506824829: goto L_0x0045;
                case 1506825542: goto L_0x003a;
                case 1506847645: goto L_0x002f;
                case 1506849815: goto L_0x0023;
                default: goto L_0x0015;
            }
        L_0x0015:
            switch(r1) {
                case 1506816895: goto L_0x007c;
                case 1506816896: goto L_0x0071;
                default: goto L_0x0018;
            }
        L_0x0018:
            switch(r1) {
                case 1506848792: goto L_0x00f3;
                case 1506848793: goto L_0x00e7;
                case 1506848794: goto L_0x00db;
                case 1506848795: goto L_0x00cf;
                case 1506848796: goto L_0x00c3;
                case 1506848797: goto L_0x00b7;
                case 1506848798: goto L_0x00ab;
                case 1506848799: goto L_0x009f;
                case 1506848800: goto L_0x0093;
                case 1506848801: goto L_0x0087;
                default: goto L_0x001b;
            }
        L_0x001b:
            switch(r1) {
                case 1506848823: goto L_0x016b;
                case 1506848824: goto L_0x015f;
                case 1506848825: goto L_0x0153;
                case 1506848826: goto L_0x0147;
                case 1506848827: goto L_0x013b;
                case 1506848828: goto L_0x012f;
                case 1506848829: goto L_0x0123;
                case 1506848830: goto L_0x0117;
                case 1506848831: goto L_0x010b;
                case 1506848832: goto L_0x00ff;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r1) {
                case 1506850745: goto L_0x01db;
                case 1506850746: goto L_0x01d0;
                case 1506850747: goto L_0x01c5;
                case 1506850748: goto L_0x01ba;
                case 1506850749: goto L_0x01af;
                case 1506850750: goto L_0x01a4;
                case 1506850751: goto L_0x0199;
                case 1506850752: goto L_0x018e;
                case 1506850753: goto L_0x0183;
                case 1506850754: goto L_0x0177;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x01e6
        L_0x0023:
            java.lang.String r1 = "311390"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 28
            goto L_0x01e7
        L_0x002f:
            java.lang.String r1 = "311110"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 7
            goto L_0x01e7
        L_0x003a:
            java.lang.String r1 = "310910"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 6
            goto L_0x01e7
        L_0x0045:
            java.lang.String r1 = "310890"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 5
            goto L_0x01e7
        L_0x0050:
            java.lang.String r1 = "310590"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 4
            goto L_0x01e7
        L_0x005b:
            java.lang.String r1 = "310010"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = r2
            goto L_0x01e7
        L_0x0066:
            java.lang.String r1 = "310004"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = r3
            goto L_0x01e7
        L_0x0071:
            java.lang.String r1 = "310013"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 3
            goto L_0x01e7
        L_0x007c:
            java.lang.String r1 = "310012"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 2
            goto L_0x01e7
        L_0x0087:
            java.lang.String r1 = "311279"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 17
            goto L_0x01e7
        L_0x0093:
            java.lang.String r1 = "311278"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 16
            goto L_0x01e7
        L_0x009f:
            java.lang.String r1 = "311277"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 15
            goto L_0x01e7
        L_0x00ab:
            java.lang.String r1 = "311276"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 14
            goto L_0x01e7
        L_0x00b7:
            java.lang.String r1 = "311275"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 13
            goto L_0x01e7
        L_0x00c3:
            java.lang.String r1 = "311274"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 12
            goto L_0x01e7
        L_0x00cf:
            java.lang.String r1 = "311273"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 11
            goto L_0x01e7
        L_0x00db:
            java.lang.String r1 = "311272"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 10
            goto L_0x01e7
        L_0x00e7:
            java.lang.String r1 = "311271"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 9
            goto L_0x01e7
        L_0x00f3:
            java.lang.String r1 = "311270"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 8
            goto L_0x01e7
        L_0x00ff:
            java.lang.String r1 = "311289"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 27
            goto L_0x01e7
        L_0x010b:
            java.lang.String r1 = "311288"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 26
            goto L_0x01e7
        L_0x0117:
            java.lang.String r1 = "311287"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 25
            goto L_0x01e7
        L_0x0123:
            java.lang.String r1 = "311286"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 24
            goto L_0x01e7
        L_0x012f:
            java.lang.String r1 = "311285"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 23
            goto L_0x01e7
        L_0x013b:
            java.lang.String r1 = "311284"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 22
            goto L_0x01e7
        L_0x0147:
            java.lang.String r1 = "311283"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 21
            goto L_0x01e7
        L_0x0153:
            java.lang.String r1 = "311282"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 20
            goto L_0x01e7
        L_0x015f:
            java.lang.String r1 = "311281"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 19
            goto L_0x01e7
        L_0x016b:
            java.lang.String r1 = "311280"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 18
            goto L_0x01e7
        L_0x0177:
            java.lang.String r1 = "311489"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 38
            goto L_0x01e7
        L_0x0183:
            java.lang.String r1 = "311488"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 37
            goto L_0x01e7
        L_0x018e:
            java.lang.String r1 = "311487"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 36
            goto L_0x01e7
        L_0x0199:
            java.lang.String r1 = "311486"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 35
            goto L_0x01e7
        L_0x01a4:
            java.lang.String r1 = "311485"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 34
            goto L_0x01e7
        L_0x01af:
            java.lang.String r1 = "311484"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 33
            goto L_0x01e7
        L_0x01ba:
            java.lang.String r1 = "311483"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 32
            goto L_0x01e7
        L_0x01c5:
            java.lang.String r1 = "311482"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 31
            goto L_0x01e7
        L_0x01d0:
            java.lang.String r1 = "311481"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 30
            goto L_0x01e7
        L_0x01db:
            java.lang.String r1 = "311480"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x01e6
            r0 = 29
            goto L_0x01e7
        L_0x01e6:
            r0 = -1
        L_0x01e7:
            switch(r0) {
                case 0: goto L_0x01eb;
                case 1: goto L_0x01eb;
                case 2: goto L_0x01eb;
                case 3: goto L_0x01eb;
                case 4: goto L_0x01eb;
                case 5: goto L_0x01eb;
                case 6: goto L_0x01eb;
                case 7: goto L_0x01eb;
                case 8: goto L_0x01eb;
                case 9: goto L_0x01eb;
                case 10: goto L_0x01eb;
                case 11: goto L_0x01eb;
                case 12: goto L_0x01eb;
                case 13: goto L_0x01eb;
                case 14: goto L_0x01eb;
                case 15: goto L_0x01eb;
                case 16: goto L_0x01eb;
                case 17: goto L_0x01eb;
                case 18: goto L_0x01eb;
                case 19: goto L_0x01eb;
                case 20: goto L_0x01eb;
                case 21: goto L_0x01eb;
                case 22: goto L_0x01eb;
                case 23: goto L_0x01eb;
                case 24: goto L_0x01eb;
                case 25: goto L_0x01eb;
                case 26: goto L_0x01eb;
                case 27: goto L_0x01eb;
                case 28: goto L_0x01eb;
                case 29: goto L_0x01eb;
                case 30: goto L_0x01eb;
                case 31: goto L_0x01eb;
                case 32: goto L_0x01eb;
                case 33: goto L_0x01eb;
                case 34: goto L_0x01eb;
                case 35: goto L_0x01eb;
                case 36: goto L_0x01eb;
                case 37: goto L_0x01eb;
                case 38: goto L_0x01eb;
                default: goto L_0x01ea;
            }
        L_0x01ea:
            r2 = r3
        L_0x01eb:
            if (r2 == 0) goto L_0x01f5
            r0 = 2131821245(0x7f1102bd, float:1.9275228E38)
            java.lang.String r4 = r4.getString(r0)
            return r4
        L_0x01f5:
            r0 = 2131821244(0x7f1102bc, float:1.9275226E38)
            java.lang.String r4 = r4.getString(r0)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.phonenumberutil.PhoneNumberHelper.getDisplayNameForRestrictedNumber(android.content.Context):java.lang.String");
    }

    public static String getGeoDescription(Context context, String str, String str2) {
        return ((PhoneNumberGeoUtilImpl) ((DaggerAospDialerRootComponent) ((HasRootComponent) context.getApplicationContext()).component()).phoneNumberGeoUtilComponent().getPhoneNumberGeoUtil()).getGeoDescription(context, str, str2);
    }

    public static String getUsernameFromUriNumber(String str) {
        int indexOf = str.indexOf(64);
        if (indexOf < 0) {
            indexOf = str.indexOf("%40");
        }
        if (indexOf >= 0) {
            return str.substring(0, indexOf);
        }
        StringBuilder outline13 = GeneratedOutlineSupport.outline13("getUsernameFromUriNumber: no delimiter found in SIP address: ");
        outline13.append(LogUtil.sanitizePii(str));
        LogUtil.m9i("PhoneNumberHelper.getUsernameFromUriNumber", outline13.toString(), new Object[0]);
        return str;
    }

    public static boolean isLegacyUnknownNumbers(CharSequence charSequence) {
        return charSequence != null && LEGACY_UNKNOWN_NUMBERS.contains(charSequence.toString());
    }

    public static boolean isLocalEmergencyNumber(Context context, String str) {
        List<PhoneAccountHandle> subscriptionPhoneAccounts = TelecomUtil.getSubscriptionPhoneAccounts(context);
        if (subscriptionPhoneAccounts.size() <= 1) {
            return PhoneNumberUtils.isLocalEmergencyNumber(context, str);
        }
        for (PhoneAccountHandle subscriptionInfo : subscriptionPhoneAccounts) {
            Optional<SubscriptionInfo> subscriptionInfo2 = TelecomUtil.getSubscriptionInfo(context, subscriptionInfo);
            if (subscriptionInfo2.isPresent()) {
                int subscriptionId = subscriptionInfo2.get().getSubscriptionId();
                Class<PhoneNumberUtils> cls = PhoneNumberUtils.class;
                try {
                    if (((Boolean) cls.getMethod("isLocalEmergencyNumber", new Class[]{Context.class, Integer.TYPE, String.class}).invoke((Object) null, new Object[]{context, Integer.valueOf(subscriptionId), str})).booleanValue()) {
                        return true;
                    }
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return false;
    }

    public static boolean isUriNumber(String str) {
        return str != null && (str.contains("@") || str.contains("%40"));
    }

    public static boolean numberHasSpecialChars(String str) {
        return !TextUtils.isEmpty(str) && str.contains("#");
    }

    public static boolean sameRawNumbers(String str, String str2) {
        return PhoneNumberUtils.stripSeparators(PhoneNumberUtils.convertKeypadLettersToDigits(str)).equals(PhoneNumberUtils.stripSeparators(PhoneNumberUtils.convertKeypadLettersToDigits(str2)));
    }

    public static boolean updateCursorToMatchContactLookupUri(Cursor cursor, int i, Uri uri) {
        if (cursor == null || uri == null || !cursor.moveToFirst()) {
            return false;
        }
        Assert.checkArgument(i >= 0 && i < cursor.getColumnCount());
        String lastPathSegment = uri.getLastPathSegment();
        if (TextUtils.isEmpty(lastPathSegment)) {
            return false;
        }
        boolean numberHasSpecialChars = numberHasSpecialChars(lastPathSegment);
        do {
            String string = cursor.getString(i);
            boolean numberHasSpecialChars2 = numberHasSpecialChars(string);
            if ((!numberHasSpecialChars && !numberHasSpecialChars2) || sameRawNumbers(string, lastPathSegment)) {
                return true;
            }
        } while (cursor.moveToNext());
        return false;
    }

    public static String formatNumber(Context context, String str, String str2) {
        return formatNumber(context, str, (String) null, str2);
    }
}
