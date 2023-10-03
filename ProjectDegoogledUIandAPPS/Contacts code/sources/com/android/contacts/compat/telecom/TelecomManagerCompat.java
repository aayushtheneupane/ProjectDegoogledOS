package com.android.contacts.compat.telecom;

import android.app.Activity;
import android.content.Intent;
import android.telecom.TelecomManager;
import com.android.contacts.compat.CompatUtils;

public class TelecomManagerCompat {
    public static void placeCall(Activity activity, TelecomManager telecomManager, Intent intent) {
        if (activity != null && telecomManager != null && intent != null) {
            if (CompatUtils.isMarshmallowCompatible()) {
                telecomManager.placeCall(intent.getData(), intent.getExtras());
            } else {
                activity.startActivityForResult(intent, 0);
            }
        }
    }
}
