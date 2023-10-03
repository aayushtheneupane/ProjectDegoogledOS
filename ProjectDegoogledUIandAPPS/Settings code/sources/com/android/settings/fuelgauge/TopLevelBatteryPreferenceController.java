package com.android.settings.fuelgauge;

import android.content.Context;
import android.text.BidiFormatter;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.fuelgauge.BatteryBroadcastReceiver;
import com.android.settings.fuelgauge.BatteryInfo;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.havoc.config.center.C1715R;

public class TopLevelBatteryPreferenceController extends BasePreferenceController implements LifecycleObserver, OnStart, OnStop {
    private final BatteryBroadcastReceiver mBatteryBroadcastReceiver = new BatteryBroadcastReceiver(this.mContext);
    private BatteryInfo mBatteryInfo;
    private Preference mPreference;

    public TopLevelBatteryPreferenceController(Context context, String str) {
        super(context, str);
        this.mBatteryBroadcastReceiver.setBatteryChangedListener(new BatteryBroadcastReceiver.OnBatteryChangedListener() {
            public final void onBatteryChanged(int i) {
                TopLevelBatteryPreferenceController.this.lambda$new$1$TopLevelBatteryPreferenceController(i);
            }
        });
    }

    public /* synthetic */ void lambda$new$1$TopLevelBatteryPreferenceController(int i) {
        BatteryInfo.getBatteryInfo(this.mContext, (BatteryInfo.Callback) new BatteryInfo.Callback() {
            public final void onBatteryInfoLoaded(BatteryInfo batteryInfo) {
                TopLevelBatteryPreferenceController.this.lambda$new$0$TopLevelBatteryPreferenceController(batteryInfo);
            }
        }, true);
    }

    public /* synthetic */ void lambda$new$0$TopLevelBatteryPreferenceController(BatteryInfo batteryInfo) {
        this.mBatteryInfo = batteryInfo;
        updateState(this.mPreference);
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(C1715R.bool.config_show_top_level_battery) ? 1 : 3;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
    }

    public void onStart() {
        this.mBatteryBroadcastReceiver.register();
    }

    public void onStop() {
        this.mBatteryBroadcastReceiver.unRegister();
    }

    public CharSequence getSummary() {
        return getDashboardLabel(this.mContext, this.mBatteryInfo);
    }

    static CharSequence getDashboardLabel(Context context, BatteryInfo batteryInfo) {
        CharSequence charSequence;
        if (batteryInfo == null || context == null) {
            return null;
        }
        BidiFormatter instance = BidiFormatter.getInstance();
        if (!batteryInfo.discharging && (charSequence = batteryInfo.chargeLabel) != null) {
            return charSequence;
        }
        if (batteryInfo.remainingLabel == null) {
            return batteryInfo.batteryPercentString;
        }
        return context.getString(C1715R.string.power_remaining_settings_home_page, new Object[]{instance.unicodeWrap(batteryInfo.batteryPercentString), instance.unicodeWrap(batteryInfo.remainingLabel)});
    }
}
