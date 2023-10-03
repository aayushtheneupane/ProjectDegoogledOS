package com.android.settings.biometrics.face;

import android.content.Context;
import android.hardware.face.FaceManager;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.Utils;

public class FaceSettingsKeyguardPreferenceController extends FaceSettingsPreferenceController {
    private static final int DEFAULT = 1;
    static final String KEY = "security_settings_face_keyguard";
    private static final int OFF = 0;

    /* renamed from: ON */
    private static final int f24ON = 1;
    private FaceManager mFaceManager;

    public int getAvailabilityStatus() {
        return 0;
    }

    public FaceSettingsKeyguardPreferenceController(Context context, String str) {
        super(context, str);
        this.mFaceManager = Utils.getFaceManagerOrNull(context);
    }

    public FaceSettingsKeyguardPreferenceController(Context context) {
        this(context, KEY);
    }

    public boolean isChecked() {
        if (FaceSettings.isAvailable(this.mContext) && !adminDisabled() && Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "face_unlock_keyguard_enabled", 1, getUserId()) == 1) {
            return true;
        }
        return false;
    }

    public boolean setChecked(boolean z) {
        return Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "face_unlock_keyguard_enabled", z ? 1 : 0, getUserId());
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (!FaceSettings.isAvailable(this.mContext)) {
            preference.setEnabled(false);
        } else if (adminDisabled()) {
            preference.setEnabled(false);
        } else if (!this.mFaceManager.hasEnrolledTemplates(getUserId())) {
            preference.setEnabled(false);
        } else {
            preference.setEnabled(true);
        }
    }
}
