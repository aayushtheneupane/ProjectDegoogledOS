package com.android.settings.notification;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.DeviceConfig;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;

public class VibrateWhenRingPreferenceController extends TogglePreferenceController implements LifecycleObserver, OnResume, OnPause {
    private static final String KEY_VIBRATE_WHEN_RINGING = "vibrate_when_ringing";
    private static final String RAMPING_RINGER_ENABLED = "ramping_ringer_enabled";
    private final int DEFAULT_VALUE = 0;
    private final int NOTIFICATION_VIBRATE_WHEN_RINGING = 1;
    private SettingObserver mSettingObserver;

    public VibrateWhenRingPreferenceController(Context context, String str) {
        super(context, str);
    }

    public boolean isChecked() {
        return Settings.System.getInt(this.mContext.getContentResolver(), KEY_VIBRATE_WHEN_RINGING, 0) != 0;
    }

    public boolean setChecked(boolean z) {
        return Settings.System.putInt(this.mContext.getContentResolver(), KEY_VIBRATE_WHEN_RINGING, z ? 1 : 0);
    }

    public int getAvailabilityStatus() {
        return (!Utils.isVoiceCapable(this.mContext) || isRampingRingerEnabled()) ? 3 : 0;
    }

    public boolean isSliceable() {
        return TextUtils.equals(getPreferenceKey(), KEY_VIBRATE_WHEN_RINGING);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        Preference findPreference = preferenceScreen.findPreference(KEY_VIBRATE_WHEN_RINGING);
        if (findPreference != null) {
            this.mSettingObserver = new SettingObserver(findPreference);
            findPreference.setPersistent(false);
        }
    }

    public void onResume() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(true);
        }
    }

    public void onPause() {
        SettingObserver settingObserver = this.mSettingObserver;
        if (settingObserver != null) {
            settingObserver.register(false);
        }
    }

    private final class SettingObserver extends ContentObserver {
        private final Uri VIBRATE_WHEN_RINGING_URI = Settings.System.getUriFor(VibrateWhenRingPreferenceController.KEY_VIBRATE_WHEN_RINGING);
        private final Preference mPreference;

        public SettingObserver(Preference preference) {
            super(new Handler());
            this.mPreference = preference;
        }

        public void register(boolean z) {
            ContentResolver contentResolver = VibrateWhenRingPreferenceController.this.mContext.getContentResolver();
            if (z) {
                contentResolver.registerContentObserver(this.VIBRATE_WHEN_RINGING_URI, false, this);
            } else {
                contentResolver.unregisterContentObserver(this);
            }
        }

        public void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            if (this.VIBRATE_WHEN_RINGING_URI.equals(uri)) {
                VibrateWhenRingPreferenceController.this.updateState(this.mPreference);
            }
        }
    }

    private boolean isRampingRingerEnabled() {
        return DeviceConfig.getBoolean("telephony", RAMPING_RINGER_ENABLED, false);
    }
}
