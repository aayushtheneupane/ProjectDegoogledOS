package com.android.settings.applications.specialaccess.pictureinpicture;

import android.app.ActivityManager;
import android.content.Context;
import com.android.settings.core.BasePreferenceController;

public class PictureInPictureScreenPreferenceController extends BasePreferenceController {
    public PictureInPictureScreenPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return !ActivityManager.isLowRamDeviceStatic() ? 0 : 3;
    }
}
