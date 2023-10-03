package com.android.settings.applications.specialaccess.notificationaccess;

import android.app.ActivityManager;
import android.content.Context;
import com.android.settings.core.BasePreferenceController;

public class NotificationAccessScreenPreferenceController extends BasePreferenceController {
    public NotificationAccessScreenPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return !ActivityManager.isLowRamDeviceStatic() ? 0 : 3;
    }
}
