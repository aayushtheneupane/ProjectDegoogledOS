package com.havoc.config.center.fragments;

import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;
import com.havoc.support.preferences.SystemSettingSwitchPreference;

public class LockScreen extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SystemSettingSwitchPreference mFODAnimationEnabled;
    private SwitchPreference mScreenOffFOD;
    private SystemSettingSwitchPreference mScreenOffFODIcon;

    public int getMetricsCategory() {
        return 1999;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.config_center_lockscreen);
        PackageManager packageManager = getPackageManager();
        this.mFODAnimationEnabled = (SystemSettingSwitchPreference) findPreference("fod_recognizing_animation");
        boolean z = Settings.System.getInt(getActivity().getContentResolver(), "screen_off_fod", 0) != 0;
        this.mScreenOffFOD = (SwitchPreference) findPreference("screen_off_fod");
        this.mScreenOffFOD.setChecked(z);
        this.mScreenOffFOD.setOnPreferenceChangeListener(this);
        this.mScreenOffFODIcon = (SystemSettingSwitchPreference) findPreference("screen_off_fod_icon");
        if (!packageManager.hasSystemFeature("vendor.lineage.biometrics.fingerprint.inscreen")) {
            this.mFODAnimationEnabled.setVisible(false);
            this.mScreenOffFOD.setVisible(false);
            this.mScreenOffFODIcon.setVisible(false);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        if (preference != this.mScreenOffFOD) {
            return false;
        }
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Settings.System.putInt(contentResolver, "screen_off_fod", booleanValue ? 1 : 0);
        Settings.Secure.putInt(contentResolver, "doze_always_on", booleanValue);
        return true;
    }
}
