package com.android.settings.biometrics.face;

import android.content.Context;
import androidx.preference.Preference;
import com.havoc.config.center.C1715R;

public class FaceProfileStatusPreferenceController extends FaceStatusPreferenceController {
    private static final String KEY_FACE_SETTINGS = "face_settings_profile";

    public FaceProfileStatusPreferenceController(Context context) {
        super(context, KEY_FACE_SETTINGS);
    }

    public int getAvailabilityStatus() {
        int availabilityStatus = super.getAvailabilityStatus();
        if (availabilityStatus != 0) {
            return availabilityStatus;
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public boolean isUserSupported() {
        int i = this.mProfileChallengeUserId;
        return i != -10000 && this.mLockPatternUtils.isSeparateProfileChallengeAllowed(i);
    }

    /* access modifiers changed from: protected */
    public int getUserId() {
        return this.mProfileChallengeUserId;
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        preference.setTitle((CharSequence) this.mContext.getResources().getString(C1715R.string.security_settings_face_profile_preference_title));
    }
}
