package com.android.settings.notification;

import android.content.Context;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.lifecycle.Lifecycle;
import com.havoc.config.center.C1715R;

public class ZenModeDurationPreferenceController extends AbstractZenModePreferenceController implements PreferenceControllerMixin {
    public String getPreferenceKey() {
        return "zen_mode_duration_settings";
    }

    public boolean isAvailable() {
        return true;
    }

    public ZenModeDurationPreferenceController(Context context, Lifecycle lifecycle) {
        super(context, "zen_mode_duration_settings", lifecycle);
    }

    public CharSequence getSummary() {
        int zenDuration = getZenDuration();
        if (zenDuration < 0) {
            return this.mContext.getString(C1715R.string.zen_mode_duration_summary_always_prompt);
        }
        if (zenDuration == 0) {
            return this.mContext.getString(C1715R.string.zen_mode_duration_summary_forever);
        }
        if (zenDuration >= 60) {
            int i = zenDuration / 60;
            return this.mContext.getResources().getQuantityString(C1715R.plurals.zen_mode_duration_summary_time_hours, i, new Object[]{Integer.valueOf(i)});
        }
        return this.mContext.getResources().getString(C1715R.string.zen_mode_duration_summary_time_minutes, new Object[]{Integer.valueOf(zenDuration)});
    }
}
