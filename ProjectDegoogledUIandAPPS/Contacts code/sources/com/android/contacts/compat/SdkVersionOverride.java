package com.android.contacts.compat;

import android.os.Build;

public class SdkVersionOverride {
    public static int getSdkVersion(int i) {
        return Build.VERSION.SDK_INT;
    }
}
