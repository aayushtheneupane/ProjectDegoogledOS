package com.android.settings.display;

import android.content.Context;
import androidx.preference.Preference;

public class AdaptiveSleepDetailPreferenceController extends AdaptiveSleepPreferenceController {
    public boolean isSliceable() {
        return true;
    }

    public AdaptiveSleepDetailPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(17891338) ? 0 : 3;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setEnabled(AdaptiveSleepPreferenceController.hasSufficientPermission(this.mContext.getPackageManager()));
    }
}
