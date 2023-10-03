package com.android.contacts.compat;

import android.app.Activity;

public class MultiWindowCompat {
    public static boolean isInMultiWindowMode(Activity activity) {
        if (CompatUtils.isNCompatible()) {
            return activity.isInMultiWindowMode();
        }
        return false;
    }
}
