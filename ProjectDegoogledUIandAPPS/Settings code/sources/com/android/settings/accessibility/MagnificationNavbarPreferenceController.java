package com.android.settings.accessibility;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.android.settings.core.TogglePreferenceController;
import com.havoc.config.center.C1715R;

public class MagnificationNavbarPreferenceController extends TogglePreferenceController {
    private boolean mIsFromSUW = false;

    public MagnificationNavbarPreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean isChecked() {
        return MagnificationPreferenceFragment.isChecked(this.mContext.getContentResolver(), "accessibility_display_magnification_navbar_enabled");
    }

    public boolean setChecked(boolean z) {
        return MagnificationPreferenceFragment.setChecked(this.mContext.getContentResolver(), "accessibility_display_magnification_navbar_enabled", z);
    }

    public void setIsFromSUW(boolean z) {
        this.mIsFromSUW = z;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!getPreferenceKey().equals(preference.getKey())) {
            return false;
        }
        Bundle extras = preference.getExtras();
        extras.putString("preference_key", "accessibility_display_magnification_navbar_enabled");
        extras.putInt("title_res", C1715R.string.accessibility_screen_magnification_navbar_title);
        extras.putInt("summary_res", C1715R.string.accessibility_screen_magnification_navbar_summary);
        extras.putBoolean("checked", isChecked());
        extras.putBoolean("from_suw", this.mIsFromSUW);
        return false;
    }

    public int getAvailabilityStatus() {
        return MagnificationPreferenceFragment.isApplicable(this.mContext.getResources()) ? 0 : 3;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), "screen_magnification_navbar_preference_screen");
    }

    public CharSequence getSummary() {
        int i;
        if (this.mIsFromSUW) {
            i = C1715R.string.accessibility_screen_magnification_navbar_short_summary;
        } else {
            i = isChecked() ? C1715R.string.accessibility_feature_state_on : C1715R.string.accessibility_feature_state_off;
        }
        return this.mContext.getText(i);
    }
}
