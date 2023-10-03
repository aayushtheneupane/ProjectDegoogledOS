package com.android.contacts.compat;

import android.telephony.TelephonyManager;

public class TelephonyManagerCompat {
    public static boolean isVoiceCapable(TelephonyManager telephonyManager) {
        if (telephonyManager == null) {
            return false;
        }
        if (CompatUtils.isLollipopMr1Compatible() || CompatUtils.isMethodAvailable("android.telephony.TelephonyManager", "isVoiceCapable", new Class[0])) {
            return telephonyManager.isVoiceCapable();
        }
        int phoneType = telephonyManager.getPhoneType();
        if (phoneType == 2 || phoneType == 1) {
            return true;
        }
        return false;
    }
}
