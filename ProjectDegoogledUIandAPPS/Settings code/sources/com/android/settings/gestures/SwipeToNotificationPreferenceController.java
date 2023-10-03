package com.android.settings.gestures;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.android.settings.core.BasePreferenceController;
import com.android.settings.core.PreferenceControllerMixin;
import com.android.settings.widget.VideoPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.havoc.support.preferences.SwitchPreference;

public class SwipeToNotificationPreferenceController extends BasePreferenceController implements PreferenceControllerMixin, Preference.OnPreferenceChangeListener, LifecycleObserver, OnResume, OnPause, OnCreate, OnSaveInstanceState {
    static final String KEY_VIDEO_PAUSED = "key_video_paused";
    private static final int OFF = 0;

    /* renamed from: ON */
    private static final int f37ON = 1;
    private static final String PREF_KEY_VIDEO = "gesture_swipe_down_fingerprint_video";
    private static final String PREF_SWIPE_DISMISS = "gesture_swipe_dismiss_fingerprint";
    private static final String PREF_SWIPE_DOWN = "gesture_swipe_down_fingerprint";
    private PreferenceScreen mPreferenceScreen;
    boolean mVideoPaused;
    private VideoPreference mVideoPreference;

    public SwipeToNotificationPreferenceController(Context context, String str) {
        super(context, str);
    }

    public static boolean isSuggestionComplete(Context context, SharedPreferences sharedPreferences) {
        if (!isGestureAvailable(context) || sharedPreferences.getBoolean("pref_swipe_to_notification_suggestion_complete", false)) {
            return true;
        }
        return false;
    }

    private static boolean isGestureAvailable(Context context) {
        return Utils.hasFingerprintHardware(context) && context.getResources().getBoolean(17891570);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mVideoPreference = (VideoPreference) preferenceScreen.findPreference(PREF_KEY_VIDEO);
        }
        this.mPreferenceScreen = preferenceScreen;
    }

    public int getAvailabilityStatus() {
        return isAvailable(this.mContext) ? 0 : 3;
    }

    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            this.mVideoPaused = bundle.getBoolean(KEY_VIDEO_PAUSED, false);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(KEY_VIDEO_PAUSED, this.mVideoPaused);
    }

    public void onPause() {
        VideoPreference videoPreference = this.mVideoPreference;
        if (videoPreference != null) {
            this.mVideoPaused = videoPreference.isVideoPaused();
            this.mVideoPreference.onViewInvisible();
        }
    }

    public void onResume() {
        VideoPreference videoPreference = this.mVideoPreference;
        if (videoPreference != null) {
            videoPreference.onViewVisible(this.mVideoPaused);
        }
    }

    public static boolean isAvailable(Context context) {
        return isGestureAvailable(context);
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (preference != null && (preference instanceof SwitchPreference)) {
            SwitchPreference switchPreference = (SwitchPreference) preference;
            boolean z = true;
            if (TextUtils.equals(switchPreference.getKey(), PREF_SWIPE_DOWN)) {
                if (Settings.Secure.getInt(this.mContext.getContentResolver(), "system_navigation_keys_enabled", 0) != 1) {
                    z = false;
                }
                switchPreference.setChecked(z);
            } else if (TextUtils.equals(switchPreference.getKey(), PREF_SWIPE_DISMISS)) {
                if (Settings.Secure.getInt(this.mContext.getContentResolver(), "fp_swipe_to_dismiss_notifications", 0) != 1) {
                    z = false;
                }
                switchPreference.setChecked(z);
                switchPreference.setEnabled(swipeDownEnabled());
            }
        }
    }

    private boolean swipeDownEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), "system_navigation_keys_enabled", 0) == 1;
    }

    public boolean onPreferenceChange(Preference preference, Object obj) {
        SwitchPreference switchPreference = (SwitchPreference) preference;
        if (TextUtils.equals(switchPreference.getKey(), PREF_SWIPE_DOWN)) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            Settings.Secure.putInt(this.mContext.getContentResolver(), "system_navigation_keys_enabled", booleanValue ? 1 : 0);
            switchPreference.setChecked(booleanValue);
            SwitchPreference switchPreference2 = (SwitchPreference) this.mPreferenceScreen.findPreference(PREF_SWIPE_DISMISS);
            if (switchPreference2 == null) {
                return true;
            }
            switchPreference2.setEnabled(booleanValue);
            return true;
        } else if (!TextUtils.equals(switchPreference.getKey(), PREF_SWIPE_DISMISS)) {
            return true;
        } else {
            boolean booleanValue2 = ((Boolean) obj).booleanValue();
            Settings.Secure.putInt(this.mContext.getContentResolver(), "fp_swipe_to_dismiss_notifications", booleanValue2 ? 1 : 0);
            switchPreference.setChecked(booleanValue2);
            return true;
        }
    }
}
