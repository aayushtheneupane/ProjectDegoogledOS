package com.android.settings.privacy;

import android.content.Context;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.utils.ContentCaptureUtils;

public final class EnableContentCapturePreferenceController extends TogglePreferenceController {
    public EnableContentCapturePreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean isChecked() {
        return ContentCaptureUtils.isEnabledForUser(this.mContext);
    }

    public boolean setChecked(boolean z) {
        ContentCaptureUtils.setEnabledForUser(this.mContext, z);
        return true;
    }

    public int getAvailabilityStatus() {
        if (ContentCaptureUtils.isFeatureAvailable() && ContentCaptureUtils.getServiceSettingsComponentName() == null) {
            return 0;
        }
        return 3;
    }
}
