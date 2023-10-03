package com.android.settings.applications.specialaccess.pictureinpicture;

import android.app.ActivityManager;
import android.content.Context;
import com.android.settings.core.BasePreferenceController;

public class PictureInPictureController extends BasePreferenceController {
    public PictureInPictureController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return !ActivityManager.isLowRamDeviceStatic() ? 1 : 3;
    }
}
