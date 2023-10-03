package com.android.dialer.compat.telephony;

import android.content.Context;
import android.os.Build;
import android.telecom.PhoneAccountHandle;
import android.telephony.TelephonyManager;
import com.android.dialer.common.LogUtil;
import com.android.dialer.telecom.TelecomUtil;
import java.lang.reflect.InvocationTargetException;

public class TelephonyManagerCompat {
    public static final String EXTRA_IS_REFRESH = "android.telephony.extra.IS_REFRESH";
    public static final Integer FEATURES_ASSISTED_DIALING = 16;

    static {
        int i = Build.VERSION.SDK_INT;
    }

    public static int getPhoneCount(TelephonyManager telephonyManager) {
        if (telephonyManager == null) {
            return 1;
        }
        return telephonyManager.getPhoneCount();
    }

    public static TelephonyManager getTelephonyManagerForPhoneAccountHandle(Context context, PhoneAccountHandle phoneAccountHandle) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(TelephonyManager.class);
        if (phoneAccountHandle == null) {
            return telephonyManager;
        }
        int i = Build.VERSION.SDK_INT;
        TelephonyManager createForPhoneAccountHandle = telephonyManager.createForPhoneAccountHandle(phoneAccountHandle);
        return createForPhoneAccountHandle != null ? createForPhoneAccountHandle : telephonyManager;
    }

    public static void handleSecretCode(Context context, String str) {
        int i = Build.VERSION.SDK_INT;
        if (!TelecomUtil.isDefaultDialer(context)) {
            LogUtil.m8e("TelephonyManagerCompat.handleSecretCode", "not default dialer, cannot send special code", new Object[0]);
        } else {
            ((TelephonyManager) context.getSystemService(TelephonyManager.class)).sendDialerSpecialCode(str);
        }
    }

    public static boolean isTtyModeSupported(TelephonyManager telephonyManager) {
        return telephonyManager != null && telephonyManager.isTtyModeSupported();
    }

    public static void setVisualVoicemailEnabled(TelephonyManager telephonyManager, PhoneAccountHandle phoneAccountHandle, boolean z) {
        Class<TelephonyManager> cls = TelephonyManager.class;
        try {
            cls.getMethod("setVisualVoicemailEnabled", new Class[]{PhoneAccountHandle.class, Boolean.TYPE}).invoke(telephonyManager, new Object[]{phoneAccountHandle, Boolean.valueOf(z)});
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            LogUtil.m7e("TelephonyManagerCompat.setVisualVoicemailEnabled", "failed", e);
        }
    }
}
