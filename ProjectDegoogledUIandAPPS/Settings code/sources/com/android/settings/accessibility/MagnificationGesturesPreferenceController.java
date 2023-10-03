package com.android.settings.accessibility;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.core.TogglePreferenceController;
import com.havoc.config.center.C1715R;

public class MagnificationGesturesPreferenceController extends TogglePreferenceController {
    private boolean mIsFromSUW = false;

    public int getAvailabilityStatus() {
        return 0;
    }

    public MagnificationGesturesPreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean isChecked() {
        return MagnificationPreferenceFragment.isChecked(this.mContext.getContentResolver(), "accessibility_display_magnification_enabled");
    }

    public boolean setChecked(boolean z) {
        return MagnificationPreferenceFragment.setChecked(this.mContext.getContentResolver(), "accessibility_display_magnification_enabled", z);
    }

    public void setIsFromSUW(boolean z) {
        this.mIsFromSUW = z;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        Bundle extras = preference.getExtras();
        populateMagnificationGesturesPreferenceExtras(extras, this.mContext);
        extras.putBoolean("checked", isChecked());
        extras.putBoolean("from_suw", this.mIsFromSUW);
        return false;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "screen_magnification_gestures_preference_screen");
    }

    public CharSequence getSummary() {
        int i;
        if (this.mIsFromSUW) {
            i = C1715R.string.accessibility_screen_magnification_short_summary;
        } else {
            i = isChecked() ? C1715R.string.accessibility_feature_state_on : C1715R.string.accessibility_feature_state_off;
        }
        return this.mContext.getString(i);
    }

    static void populateMagnificationGesturesPreferenceExtras(Bundle bundle, Context context) {
        bundle.putString("preference_key", "accessibility_display_magnification_enabled");
        bundle.putInt("title_res", C1715R.string.accessibility_screen_magnification_gestures_title);
        bundle.putInt("summary_res", C1715R.string.accessibility_screen_magnification_summary);
        bundle.putInt("video_resource", C1715R.raw.accessibility_screen_magnification);
    }
}
