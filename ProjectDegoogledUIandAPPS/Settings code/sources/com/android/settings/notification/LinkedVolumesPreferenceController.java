package com.android.settings.notification;

import android.content.Context;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;

public class LinkedVolumesPreferenceController extends BasePreferenceController {
    public LinkedVolumesPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return Utils.isVoiceCapable(this.mContext) ? 0 : 3;
    }
}
