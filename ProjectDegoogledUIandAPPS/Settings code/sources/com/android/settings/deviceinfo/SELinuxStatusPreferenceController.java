package com.android.settings.deviceinfo;

import android.content.Context;
import android.os.SELinux;
import android.os.SystemProperties;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settingslib.core.AbstractPreferenceController;
import com.havoc.config.center.C1715R;

public class SELinuxStatusPreferenceController extends AbstractPreferenceController implements PreferenceControllerMixin {
    public String getPreferenceKey() {
        return "selinux_status";
    }

    public SELinuxStatusPreferenceController(Context context) {
        super(context);
    }

    public boolean isAvailable() {
        return !TextUtils.isEmpty(SystemProperties.get("ro.build.selinux"));
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference("selinux_status");
        if (findPreference != null) {
            if (!SELinux.isSELinuxEnabled()) {
                findPreference.setSummary((CharSequence) this.mContext.getResources().getString(C1715R.string.selinux_status_disabled));
            } else if (!SELinux.isSELinuxEnforced()) {
                findPreference.setSummary((CharSequence) this.mContext.getResources().getString(C1715R.string.selinux_status_permissive));
            }
        }
    }
}
