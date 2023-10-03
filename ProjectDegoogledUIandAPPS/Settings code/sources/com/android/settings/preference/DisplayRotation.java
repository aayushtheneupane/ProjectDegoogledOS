package com.android.settings.preference;

import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.Preference;
import com.android.internal.view.RotationPolicy;
import com.android.settings.SettingsPreferenceFragment;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class DisplayRotation extends SettingsPreferenceFragment implements Preference.OnPreferenceChangeListener {
    private SwitchPreference mAccelerometer;
    private ContentObserver mAccelerometerRotationObserver = new ContentObserver(new Handler()) {
        public void onChange(boolean z) {
            DisplayRotation.this.updateAccelerometerRotationSwitch();
        }
    };
    private SwitchPreference mRotation0Pref;
    private SwitchPreference mRotation180Pref;
    private SwitchPreference mRotation270Pref;
    private SwitchPreference mRotation90Pref;

    public int getMetricsCategory() {
        return 1999;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        addPreferencesFromResource(C1715R.xml.display_rotation);
        this.mAccelerometer = (SwitchPreference) findPreference("accelerometer");
        boolean z = false;
        this.mAccelerometer.setPersistent(false);
        this.mRotation0Pref = (SwitchPreference) findPreference("display_rotation_0");
        this.mRotation90Pref = (SwitchPreference) findPreference("display_rotation_90");
        this.mRotation180Pref = (SwitchPreference) findPreference("display_rotation_180");
        this.mRotation270Pref = (SwitchPreference) findPreference("display_rotation_270");
        int i = Settings.System.getInt(getContentResolver(), "accelerometer_rotation_angles", 11);
        this.mRotation0Pref.setChecked((i & 1) != 0);
        this.mRotation90Pref.setChecked((i & 2) != 0);
        this.mRotation180Pref.setChecked((i & 4) != 0);
        SwitchPreference switchPreference = this.mRotation270Pref;
        if ((i & 8) != 0) {
            z = true;
        }
        switchPreference.setChecked(z);
    }

    public void onResume() {
        super.onResume();
        updateState();
        getContentResolver().registerContentObserver(Settings.System.getUriFor("accelerometer_rotation"), true, this.mAccelerometerRotationObserver);
    }

    public void onPause() {
        super.onPause();
        getContentResolver().unregisterContentObserver(this.mAccelerometerRotationObserver);
    }

    private void updateState() {
        updateAccelerometerRotationSwitch();
    }

    /* access modifiers changed from: private */
    public void updateAccelerometerRotationSwitch() {
        this.mAccelerometer.setChecked(!RotationPolicy.isRotationLocked(getActivity()));
    }

    public boolean onPreferenceTreeClick(Preference preference) {
        boolean z = false;
        if (preference == this.mAccelerometer) {
            FragmentActivity activity = getActivity();
            if (!this.mAccelerometer.isChecked()) {
                z = true;
            }
            RotationPolicy.setRotationLockForAccessibility(activity, z);
        } else if (preference == this.mRotation0Pref || preference == this.mRotation90Pref || preference == this.mRotation180Pref || preference == this.mRotation270Pref) {
            if (this.mRotation0Pref.isChecked()) {
                z = true;
            }
            if (this.mRotation90Pref.isChecked()) {
                z |= true;
            }
            if (this.mRotation180Pref.isChecked()) {
                z |= true;
            }
            if (this.mRotation270Pref.isChecked()) {
                z |= true;
            }
            if (!z) {
                z |= true;
                this.mRotation0Pref.setChecked(true);
            }
            Settings.System.putInt(getActivity().getApplicationContext().getContentResolver(), "accelerometer_rotation_angles", z ? 1 : 0);
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }
}
