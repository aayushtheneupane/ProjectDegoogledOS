package com.android.settings.fuelgauge.batterysaver;

import android.content.Context;
import android.os.PowerManager;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.fuelgauge.BatterySaverReceiver;
import com.android.settings.widget.TwoStateButtonPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.fuelgauge.BatterySaverUtils;

public class BatterySaverButtonPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnStart, OnStop, BatterySaverReceiver.BatterySaverListener {
    private final BatterySaverReceiver mBatterySaverReceiver;
    private final PowerManager mPowerManager;
    private TwoStateButtonPreference mPreference;

    public int getAvailabilityStatus() {
        return 0;
    }

    public boolean isSliceable() {
        return true;
    }

    public BatterySaverButtonPreferenceController(Context context, String str) {
        super(context, str);
        this.mPowerManager = (PowerManager) context.getSystemService("power");
        this.mBatterySaverReceiver = new BatterySaverReceiver(context);
        this.mBatterySaverReceiver.setBatterySaverListener(this);
    }

    public void onStart() {
        this.mBatterySaverReceiver.setListening(true);
    }

    public void onStop() {
        this.mBatterySaverReceiver.setListening(false);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (TwoStateButtonPreference) preferenceScreen.findPreference(getPreferenceKey());
    }

    public boolean isChecked() {
        return this.mPowerManager.isPowerSaveMode();
    }

    public boolean setChecked(boolean z) {
        return BatterySaverUtils.setPowerSaveMode(this.mContext, z, false);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        TwoStateButtonPreference twoStateButtonPreference = this.mPreference;
        if (twoStateButtonPreference != null) {
            twoStateButtonPreference.setChecked(isChecked());
        }
    }

    public void onPowerSaveModeChanged() {
        boolean isChecked = isChecked();
        TwoStateButtonPreference twoStateButtonPreference = this.mPreference;
        if (twoStateButtonPreference != null && twoStateButtonPreference.isChecked() != isChecked) {
            this.mPreference.setChecked(isChecked);
        }
    }

    public void onBatteryChanged(boolean z) {
        TwoStateButtonPreference twoStateButtonPreference = this.mPreference;
        if (twoStateButtonPreference != null) {
            twoStateButtonPreference.setButtonEnabled(!z);
        }
    }
}
