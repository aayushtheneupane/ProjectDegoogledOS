package com.android.settings.network;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.SystemProperties;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.AirplaneModeEnabler;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.overlay.FeatureFactory;
import com.android.settingslib.core.instrumentation.MetricsFeatureProvider;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.havoc.config.center.C1715R;
import com.havoc.support.preferences.SwitchPreference;

public class AirplaneModePreferenceController extends TogglePreferenceController implements LifecycleObserver, OnResume, OnPause, AirplaneModeEnabler.OnAirplaneModeChangedListener {
    private static final String EXIT_ECM_RESULT = "exit_ecm_result";
    public static final int REQUEST_CODE_EXIT_ECM = 1;
    private AirplaneModeEnabler mAirplaneModeEnabler = new AirplaneModeEnabler(this.mContext, this.mMetricsFeatureProvider, this);
    private SwitchPreference mAirplaneModePreference;
    private Fragment mFragment;
    private final MetricsFeatureProvider mMetricsFeatureProvider;

    public boolean isSliceable() {
        return true;
    }

    public AirplaneModePreferenceController(Context context, String str) {
        super(context, str);
        this.mMetricsFeatureProvider = FeatureFactory.getFactory(context).getMetricsFeatureProvider();
    }

    public void setFragment(Fragment fragment) {
        this.mFragment = fragment;
    }

    public boolean handlePreferenceTreeClick(Preference preference) {
        if (!"airplane_mode".equals(preference.getKey()) || !Boolean.parseBoolean(SystemProperties.get("ril.cdma.inecmmode"))) {
            return false;
        }
        Fragment fragment = this.mFragment;
        if (fragment != null) {
            fragment.startActivityForResult(new Intent("com.android.internal.intent.action.ACTION_SHOW_NOTICE_ECM_BLOCK_OTHERS", (Uri) null), 1);
        }
        return true;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mAirplaneModePreference = (SwitchPreference) preferenceScreen.findPreference(getPreferenceKey());
        }
    }

    public static boolean isAvailable(Context context) {
        return context.getResources().getBoolean(C1715R.bool.config_show_toggle_airplane) && !context.getPackageManager().hasSystemFeature("android.software.leanback");
    }

    public int getAvailabilityStatus() {
        return isAvailable(this.mContext) ? 0 : 3;
    }

    public void onResume() {
        if (isAvailable()) {
            this.mAirplaneModeEnabler.resume();
        }
    }

    public void onPause() {
        if (isAvailable()) {
            this.mAirplaneModeEnabler.pause();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            this.mAirplaneModeEnabler.setAirplaneModeInECM(Boolean.valueOf(intent.getBooleanExtra(EXIT_ECM_RESULT, false)).booleanValue(), this.mAirplaneModePreference.isChecked());
        }
    }

    public boolean isChecked() {
        return this.mAirplaneModeEnabler.isAirplaneModeOn();
    }

    public boolean setChecked(boolean z) {
        if (isChecked() == z) {
            return false;
        }
        this.mAirplaneModeEnabler.setAirplaneMode(z);
        return true;
    }

    public void onAirplaneModeChanged(boolean z) {
        SwitchPreference switchPreference = this.mAirplaneModePreference;
        if (switchPreference != null) {
            switchPreference.setChecked(z);
        }
    }
}
