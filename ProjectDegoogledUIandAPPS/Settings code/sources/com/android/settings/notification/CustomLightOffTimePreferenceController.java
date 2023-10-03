package com.android.settings.notification;

import android.app.NotificationChannel;
import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.core.PreferenceControllerMixin;
import com.havoc.support.preferences.CustomSeekBarPreference;

public class CustomLightOffTimePreferenceController extends NotificationPreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener {
    private int mLedColor = 0;

    public String getPreferenceKey() {
        return "custom_light_off_time";
    }

    public CustomLightOffTimePreferenceController(Context context, NotificationBackend notificationBackend) {
        super(context, notificationBackend);
    }

    public boolean isAvailable() {
        if (this.mContext.getResources().getBoolean(17891481) && super.isAvailable() && this.mChannel != null && checkCanBeVisible(3) && canPulseLight()) {
            return true;
        }
        return false;
    }

    public void updateState(Preference preference) {
        NotificationChannel notificationChannel = this.mChannel;
        if (notificationChannel != null) {
            CustomSeekBarPreference customSeekBarPreference = (CustomSeekBarPreference) preference;
            int lightOffTime = notificationChannel.getLightOffTime();
            int integer = this.mContext.getResources().getInteger(17694781);
            customSeekBarPreference.setDefaultValue(Integer.valueOf(integer));
            if (lightOffTime != 0) {
                integer = lightOffTime;
            }
            customSeekBarPreference.setValue(integer);
        }
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        if (this.mChannel == null) {
            return true;
        }
        this.mChannel.setLightOffTime(((Integer) obj).intValue());
        saveChannel();
        showLedPreview();
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean canPulseLight() {
        if (!this.mContext.getResources().getBoolean(17891494)) {
            return false;
        }
        return Settings.System.getInt(this.mContext.getContentResolver(), "notification_light_pulse", 1) == 1;
    }

    private void showLedPreview() {
        if (this.mChannel.shouldShowLights()) {
            if (this.mLedColor == -1) {
                this.mLedColor = 16777215;
            }
            this.mNm.forcePulseLedLight(this.mLedColor, this.mChannel.getLightOnTime(), this.mChannel.getLightOffTime());
        }
    }
}
