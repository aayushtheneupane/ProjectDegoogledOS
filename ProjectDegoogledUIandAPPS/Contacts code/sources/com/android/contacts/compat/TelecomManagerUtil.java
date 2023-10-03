package com.android.contacts.compat;

import android.content.Intent;
import android.telecom.TelecomManager;

public class TelecomManagerUtil {
    public static Intent createManageBlockedNumbersIntent(TelecomManager telecomManager) {
        if (CompatUtils.isNCompatible()) {
            return telecomManager.createManageBlockedNumbersIntent();
        }
        return null;
    }
}
