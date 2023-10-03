package com.android.contacts.util;

import android.content.Context;
import android.content.Intent;
import android.net.sip.SipManager;
import android.telephony.TelephonyManager;
import com.android.contacts.compat.TelephonyManagerCompat;

public final class PhoneCapabilityTester {
    private static boolean sIsInitialized;
    private static boolean sIsPhone;
    private static boolean sIsSipPhone;

    public static boolean isIntentRegistered(Context context, Intent intent) {
        return context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static boolean isPhone(Context context) {
        if (!sIsInitialized) {
            initialize(context);
        }
        return sIsPhone;
    }

    private static void initialize(Context context) {
        sIsPhone = TelephonyManagerCompat.isVoiceCapable((TelephonyManager) context.getSystemService("phone"));
        sIsSipPhone = sIsPhone && SipManager.isVoipSupported(context);
        sIsInitialized = true;
    }

    public static boolean isSipPhone(Context context) {
        if (!sIsInitialized) {
            initialize(context);
        }
        return sIsSipPhone;
    }

    public static boolean isCameraIntentRegistered(Context context) {
        return isIntentRegistered(context, new Intent("android.media.action.IMAGE_CAPTURE"));
    }
}
