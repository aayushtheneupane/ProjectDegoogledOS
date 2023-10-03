package com.android.settings.biometrics.face;

import android.content.Context;
import android.hardware.face.FaceManager;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.settings.Utils;

public class FaceSettingsConfirmPreferenceController extends FaceSettingsPreferenceController {
    private static final int DEFAULT = 0;
    static final String KEY = "security_settings_face_require_confirmation";
    private static final int OFF = 0;

    /* renamed from: ON */
    private static final int f23ON = 1;
    private FaceManager mFaceManager;

    public int getAvailabilityStatus() {
        return 0;
    }

    public FaceSettingsConfirmPreferenceController(Context context) {
        this(context, KEY);
    }

    public FaceSettingsConfirmPreferenceController(Context context, String str) {
        super(context, str);
        this.mFaceManager = Utils.getFaceManagerOrNull(context);
    }

    public boolean isChecked() {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "face_unlock_always_require_confirmation", 0, getUserId()) == 1;
    }

    public boolean setChecked(boolean z) {
        return Settings.Secure.putIntForUser(this.mContext.getContentResolver(), "face_unlock_always_require_confirmation", z ? 1 : 0, getUserId());
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        if (!FaceSettings.isAvailable(this.mContext)) {
            preference.setEnabled(false);
        } else if (!this.mFaceManager.hasEnrolledTemplates(getUserId())) {
            preference.setEnabled(false);
        } else {
            preference.setEnabled(true);
        }
    }
}
