package com.android.settings.gestures;

import android.content.Context;
import android.provider.Settings;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.android.settings.overlay.FeatureFactory;
import com.havoc.config.center.C1715R;

public class AssistGestureSettingsPreferenceController extends GesturePreferenceController {
    private static final int OFF = 0;

    /* renamed from: ON */
    private static final int f32ON = 1;
    private static final String PREF_KEY_VIDEO = "gesture_assist_video";
    private static final String SECURE_KEY_ASSIST = "assist_gesture_enabled";
    private static final String SECURE_KEY_SILENCE = "assist_gesture_silence_alerts_enabled";
    boolean mAssistOnly;
    private final AssistGestureFeatureProvider mFeatureProvider;
    private Preference mPreference;
    private PreferenceScreen mScreen;
    private boolean mWasAvailable = isAvailable();

    /* access modifiers changed from: protected */
    public String getVideoPrefKey() {
        return PREF_KEY_VIDEO;
    }

    public AssistGestureSettingsPreferenceController(Context context, String str) {
        super(context, str);
        this.mFeatureProvider = FeatureFactory.getFactory(context).getAssistGestureFeatureProvider();
    }

    public int getAvailabilityStatus() {
        boolean z;
        if (this.mAssistOnly) {
            z = this.mFeatureProvider.isSupported(this.mContext);
        } else {
            z = this.mFeatureProvider.isSensorAvailable(this.mContext);
        }
        return z ? 0 : 3;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        this.mScreen = preferenceScreen;
        this.mPreference = preferenceScreen.findPreference(getPreferenceKey());
        super.displayPreference(preferenceScreen);
    }

    public void onResume() {
        if (this.mWasAvailable != isAvailable()) {
            updatePreference();
            this.mWasAvailable = isAvailable();
        }
    }

    public AssistGestureSettingsPreferenceController setAssistOnly(boolean z) {
        this.mAssistOnly = z;
        return this;
    }

    private void updatePreference() {
        if (this.mPreference != null) {
            if (!isAvailable()) {
                this.mScreen.removePreference(this.mPreference);
            } else if (this.mScreen.findPreference(getPreferenceKey()) == null) {
                this.mScreen.addPreference(this.mPreference);
            }
        }
    }

    private boolean isAssistGestureEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), SECURE_KEY_ASSIST, 1) != 0;
    }

    private boolean isSilenceGestureEnabled() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), SECURE_KEY_SILENCE, 1) != 0;
    }

    public boolean setChecked(boolean z) {
        return Settings.Secure.putInt(this.mContext.getContentResolver(), SECURE_KEY_ASSIST, z ? 1 : 0);
    }

    public CharSequence getSummary() {
        boolean z = isAssistGestureEnabled() && this.mFeatureProvider.isSupported(this.mContext);
        if (!this.mAssistOnly) {
            z = z || isSilenceGestureEnabled();
        }
        return this.mContext.getText(z ? C1715R.string.gesture_setting_on : C1715R.string.gesture_setting_off);
    }

    public boolean isChecked() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), SECURE_KEY_ASSIST, 0) == 1;
    }
}
