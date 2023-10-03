package com.android.dialer.oem;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import com.android.dialer.R;
import com.android.dialer.common.Assert;
import com.android.dialer.common.LogUtil;
import com.android.dialer.configprovider.ConfigProviderComponent;
import com.android.dialer.configprovider.SharedPrefConfigProvider;
import java.lang.reflect.InvocationTargetException;

public class MotorolaUtils {
    static final String CONFIG_DISABLE_PHONE_NUMBER_FORMATTING = "disable_phone_number_formatting";
    static final String HIDDEN_MENU_FEATURE = "com.motorola.software.sprint.hidden_menu";
    public static final String WIFI_CALL_PACKAGE_NAME = "com.motorola.sprintwfc";
    private static Boolean disablePhoneNumberFormattingForTest;
    private static boolean hasCheckedSprintWifiCall;
    private static boolean supportSprintWifiCall;

    public static boolean handleSpecialCharSequence(Context context, String str) {
        return MotorolaHiddenMenuKeySequence.handleCharSequence(context, str);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isSupportingSprintHdCodec(android.content.Context r7) {
        /*
            r0 = 0
            r1 = 1
            android.content.res.Resources r2 = r7.getResources()     // Catch:{ NotFoundException -> 0x002b }
            r3 = 2130903056(0x7f030010, float:1.741292E38)
            java.lang.String[] r2 = r2.getStringArray(r3)     // Catch:{ NotFoundException -> 0x002b }
            int r3 = r2.length     // Catch:{ NotFoundException -> 0x002b }
            r4 = r0
        L_0x000f:
            if (r4 >= r3) goto L_0x0029
            r5 = r2[r4]     // Catch:{ NotFoundException -> 0x002b }
            java.lang.Class<android.telephony.TelephonyManager> r6 = android.telephony.TelephonyManager.class
            java.lang.Object r6 = r7.getSystemService(r6)     // Catch:{ NotFoundException -> 0x002b }
            android.telephony.TelephonyManager r6 = (android.telephony.TelephonyManager) r6     // Catch:{ NotFoundException -> 0x002b }
            java.lang.String r6 = r6.getSimOperatorName()     // Catch:{ NotFoundException -> 0x002b }
            boolean r5 = r5.equalsIgnoreCase(r6)     // Catch:{ NotFoundException -> 0x002b }
            if (r5 == 0) goto L_0x0026
            goto L_0x002b
        L_0x0026:
            int r4 = r4 + 1
            goto L_0x000f
        L_0x0029:
            r2 = r0
            goto L_0x002c
        L_0x002b:
            r2 = r1
        L_0x002c:
            if (r2 == 0) goto L_0x0048
            android.content.res.Resources r2 = r7.getResources()
            r3 = 2131034128(0x7f050010, float:1.7678765E38)
            boolean r2 = r2.getBoolean(r3)
            if (r2 == 0) goto L_0x0048
            android.content.pm.PackageManager r7 = r7.getPackageManager()
            java.lang.String r2 = "com.motorola.software.sprint.hd_call"
            boolean r7 = r7.hasSystemFeature(r2)
            if (r7 == 0) goto L_0x0048
            r0 = r1
        L_0x0048:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.dialer.oem.MotorolaUtils.isSupportingSprintHdCodec(android.content.Context):boolean");
    }

    private static boolean isSupportingSprintWifiCall(Context context) {
        boolean z;
        if (!hasCheckedSprintWifiCall) {
            Assert.isNotNull(WIFI_CALL_PACKAGE_NAME);
            Assert.isNotNull(context);
            Assert.isNotNull(WIFI_CALL_PACKAGE_NAME);
            Assert.isNotNull(context);
            boolean z2 = false;
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(WIFI_CALL_PACKAGE_NAME, 0);
                if (!(packageInfo == null || packageInfo.packageName == null)) {
                    WIFI_CALL_PACKAGE_NAME + " is found";
                    z = true;
                    if (z && context.getPackageManager().getApplicationEnabledSetting(WIFI_CALL_PACKAGE_NAME) != 2) {
                        z2 = true;
                    }
                    supportSprintWifiCall = z2;
                    hasCheckedSprintWifiCall = true;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                WIFI_CALL_PACKAGE_NAME + " is NOT found";
            }
            z = false;
            z2 = true;
            supportSprintWifiCall = z2;
            hasCheckedSprintWifiCall = true;
        }
        return supportSprintWifiCall;
    }

    public static boolean isWifiCallingAvailable(Context context) {
        if (!isSupportingSprintWifiCall(context)) {
            return false;
        }
        try {
            boolean booleanValue = ((Boolean) TelephonyManager.class.getMethod("isWifiCallingAvailable", new Class[0]).invoke((TelephonyManager) context.getSystemService(TelephonyManager.class), new Object[0])).booleanValue();
            new Object[1][0] = Boolean.valueOf(booleanValue);
            return booleanValue;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.m7e("MotorolaUtils.isWifiCallingAvailable", "", e);
            return false;
        }
    }

    public static void resetForTest() {
        hasCheckedSprintWifiCall = false;
        supportSprintWifiCall = false;
    }

    public static void setDisablePhoneNumberFormattingForTest(boolean z) {
        disablePhoneNumberFormattingForTest = Boolean.valueOf(z);
    }

    public static boolean shouldBlinkHdIconWhenConnectingCall(Context context) {
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("hd_codec_blinking_icon_when_connecting_enabled", true) || !isSupportingSprintHdCodec(context)) {
            return false;
        }
        return true;
    }

    public static boolean shouldDisablePhoneNumberFormatting(Context context) {
        Boolean bool = disablePhoneNumberFormattingForTest;
        if (bool != null) {
            return bool.booleanValue();
        }
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean(CONFIG_DISABLE_PHONE_NUMBER_FORMATTING, true) || !context.getResources().getBoolean(R.bool.motorola_disable_phone_number_formatting)) {
            return false;
        }
        return true;
    }

    public static boolean shouldShowHdIconInNotification(Context context) {
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("hd_codec_show_icon_in_notification_enabled", true) || !isSupportingSprintHdCodec(context)) {
            return false;
        }
        return true;
    }

    public static boolean shouldShowWifiIconInCallLog(Context context, int i) {
        if (!((SharedPrefConfigProvider) ConfigProviderComponent.get(context).getConfigProvider()).getBoolean("wifi_call_show_icon_in_call_log_enabled", true) || (i & 8) != 8 || !isSupportingSprintWifiCall(context)) {
            return false;
        }
        return true;
    }
}
