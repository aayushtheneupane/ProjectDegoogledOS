package com.android.settings.gestures;

import android.content.Context;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.TogglePreferenceController;
import com.android.settings.widget.VideoPreference;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnCreate;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;
import com.android.settingslib.core.lifecycle.events.OnSaveInstanceState;
import com.havoc.config.center.C1715R;

public abstract class GesturePreferenceController extends TogglePreferenceController implements Preference.OnPreferenceChangeListener, LifecycleObserver, OnResume, OnPause, OnCreate, OnSaveInstanceState {
    static final String KEY_VIDEO_PAUSED = "key_video_paused";
    boolean mVideoPaused;
    private VideoPreference mVideoPreference;

    /* access modifiers changed from: protected */
    public boolean canHandleClicks() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract String getVideoPrefKey();

    public GesturePreferenceController(Context context, String str) {
        super(context, str);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        if (isAvailable()) {
            this.mVideoPreference = (VideoPreference) preferenceScreen.findPreference(getVideoPrefKey());
        }
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (preference != null) {
            preference.setEnabled(canHandleClicks());
        }
    }

    public CharSequence getSummary() {
        return this.mContext.getText(isChecked() ? C1715R.string.gesture_setting_on : C1715R.string.gesture_setting_off);
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
}
