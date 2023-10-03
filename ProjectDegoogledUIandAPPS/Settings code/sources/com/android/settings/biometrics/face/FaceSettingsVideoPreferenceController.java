package com.android.settings.biometrics.face;

import android.content.Context;
import androidx.preference.PreferenceScreen;
import com.android.settings.widget.VideoPreference;
import com.android.settings.widget.VideoPreferenceController;

public class FaceSettingsVideoPreferenceController extends VideoPreferenceController {
    private static final String KEY_VIDEO = "security_settings_face_video";
    private VideoPreference mVideoPreference;

    public FaceSettingsVideoPreferenceController(Context context, String str) {
        super(context, str);
    }

    public FaceSettingsVideoPreferenceController(Context context) {
        this(context, KEY_VIDEO);
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mVideoPreference = (VideoPreference) preferenceScreen.findPreference(KEY_VIDEO);
        this.mVideoPreference.onViewVisible(false);
    }
}
