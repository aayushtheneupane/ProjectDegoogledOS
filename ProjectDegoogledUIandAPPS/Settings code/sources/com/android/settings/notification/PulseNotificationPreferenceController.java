package com.android.settings.notification;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;

public class PulseNotificationPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnResume, OnPause {
    private static final int OFF = 0;

    /* renamed from: ON */
    private static final int f54ON = 1;
    private SettingObserver mSettingObserver;

    public PulseNotificationPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(getPreferenceKey());
        if (findPreference != null) {
            this.mSettingObserver = new SettingObserver(findPreference);
        }
    }

    public void onResume() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver(), true);
        }
    }

    public void onPause() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(this.mContext.getContentResolver(), false);
        }
    }

    public int getAvailabilityStatus() {
        return this.mContext.getResources().getBoolean(17891494) ? 0 : 3;
    }

    public boolean isChecked() {
        return Settings.System.getInt(this.mContext.getContentResolver(), "notification_light_pulse", 0) == 1;
    }

    public boolean setChecked(boolean z) {
        return Settings.System.putInt(this.mContext.getContentResolver(), "notification_light_pulse", z ? 1 : 0);
    }

    class SettingObserver extends ContentObserver {
        private final Uri NOTIFICATION_LIGHT_PULSE_URI = Settings.System.getUriFor("notification_light_pulse");
        private final Preference mPreference;

        public SettingObserver(Preference preference) {
            super(new Handler());
            this.mPreference = preference;
        }

        public void register(ContentResolver contentResolver, boolean z) {
            if (z) {
                contentResolver.registerContentObserver(this.NOTIFICATION_LIGHT_PULSE_URI, false, this);
            } else {
                contentResolver.unregisterContentObserver(this);
            }
        }

        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (this.NOTIFICATION_LIGHT_PULSE_URI.equals(uri)) {
                PulseNotificationPreferenceController.this.updateState(this.mPreference);
            }
        }
    }
}
