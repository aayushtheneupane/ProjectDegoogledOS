package com.android.settings.fuelgauge;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.fuelgauge.BatterySaverReceiver;
import com.android.settingslib.Utils;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.android.settingslib.fuelgauge.BatterySaverUtils;
import com.havoc.config.center.C1715R;

public class BatterySaverController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop, BatterySaverReceiver.BatterySaverListener {
    private static final String KEY_BATTERY_SAVER = "battery_saver_summary";
    private Preference mBatterySaverPref;
    private final BatterySaverReceiver mBatteryStateChangeReceiver;
    private final ContentObserver mObserver = new ContentObserver(new Handler(Looper.getMainLooper())) {
        public void onChange(boolean z) {
            BatterySaverController.this.updateSummary();
        }
    };
    private final PowerManager mPowerManager = ((PowerManager) this.mContext.getSystemService("power"));

    public int getAvailabilityStatus() {
        return 1;
    }

    public String getPreferenceKey() {
        return KEY_BATTERY_SAVER;
    }

    public void onBatteryChanged(boolean z) {
    }

    public BatterySaverController(Context context) {
        super(context, KEY_BATTERY_SAVER);
        this.mBatteryStateChangeReceiver = new BatterySaverReceiver(context);
        this.mBatteryStateChangeReceiver.setBatterySaverListener(this);
        BatterySaverUtils.revertScheduleToNoneIfNeeded(context);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mBatterySaverPref = preferenceScreen.findPreference(KEY_BATTERY_SAVER);
    }

    public void onStart() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("low_power_trigger_level"), true, this.mObserver);
        this.mBatteryStateChangeReceiver.setListening(true);
        updateSummary();
    }

    public void onStop() {
        this.mContext.getContentResolver().unregisterContentObserver(this.mObserver);
        this.mBatteryStateChangeReceiver.setListening(false);
    }

    public CharSequence getSummary() {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        boolean isPowerSaveMode = this.mPowerManager.isPowerSaveMode();
        int i = Settings.Global.getInt(contentResolver, "low_power_trigger_level", 0);
        int i2 = Settings.Global.getInt(contentResolver, "automatic_power_save_mode", 0);
        if (isPowerSaveMode) {
            return this.mContext.getString(C1715R.string.battery_saver_on_summary);
        }
        if (i2 != 0) {
            return this.mContext.getString(C1715R.string.battery_saver_auto_routine);
        }
        if (i == 0) {
            return this.mContext.getString(C1715R.string.battery_saver_off_summary);
        }
        return this.mContext.getString(C1715R.string.battery_saver_off_scheduled_summary, new Object[]{Utils.formatPercentage(i)});
    }

    /* access modifiers changed from: private */
    public void updateSummary() {
        this.mBatterySaverPref.setSummary(getSummary());
    }

    public void onPowerSaveModeChanged() {
        updateSummary();
    }
}
