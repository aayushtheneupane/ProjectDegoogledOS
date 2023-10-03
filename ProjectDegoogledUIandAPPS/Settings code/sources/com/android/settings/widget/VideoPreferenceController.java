package com.android.settings.widget;

import android.content.Context;
import androidx.preference.PreferenceScreen;
import com.android.settings.core.BasePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnPause;
import com.android.settingslib.core.lifecycle.events.OnResume;

public class VideoPreferenceController extends BasePreferenceController implements LifecycleObserver, OnResume, OnPause {
    private boolean mVideoPaused;
    private VideoPreference mVideoPreference;

    public VideoPreferenceController(Context context, String str) {
        super(context, str);
    }

    public int getAvailabilityStatus() {
        VideoPreference videoPreference = this.mVideoPreference;
        return (videoPreference == null || !videoPreference.isAnimationAvailable()) ? 3 : 1;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mVideoPreference = (VideoPreference) preferenceScreen.findPreference(getPreferenceKey());
        super.displayPreference(preferenceScreen);
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
