package com.android.settings.gestures;

import android.content.Context;
import android.provider.Settings;
import com.android.settings.core.BasePreferenceController;
import com.havoc.config.center.C1715R;

public class PreventRingingParentPreferenceController extends BasePreferenceController {
    final String SECURE_KEY = "volume_hush_gesture";

    public PreventRingingParentPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(17891608) ? 1 : 3;
    }

    public CharSequence getSummary() {
        int i = Settings.Secure.getInt(this.mContext.getContentResolver(), "volume_hush_gesture", 1);
        return this.mContext.getText(i != 1 ? i != 2 ? i != 3 ? i != 4 ? C1715R.string.prevent_ringing_option_none_summary : C1715R.string.prevent_ringing_option_cycle_summary : C1715R.string.prevent_ringing_option_mute_no_media_summary : C1715R.string.prevent_ringing_option_mute_summary : C1715R.string.prevent_ringing_option_vibrate_summary);
    }
}
