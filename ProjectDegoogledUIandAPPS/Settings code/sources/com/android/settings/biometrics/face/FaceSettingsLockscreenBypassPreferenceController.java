package com.android.settings.biometrics.face;

import android.content.Context;
import android.hardware.face.FaceManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import androidx.preference.Preference;
import com.android.internal.annotations.VisibleForTesting;

public class FaceSettingsLockscreenBypassPreferenceController extends FaceSettingsPreferenceController {
    @VisibleForTesting
    protected FaceManager mFaceManager;
    private UserManager mUserManager;

    public FaceSettingsLockscreenBypassPreferenceController(Context context, String str) {
        super(context, str);
        if (context.getPackageManager().hasSystemFeature("android.hardware.biometrics.face")) {
            this.mFaceManager = (FaceManager) context.getSystemService(FaceManager.class);
        }
        this.mUserManager = (UserManager) context.getSystemService(UserManager.class);
    }

    public boolean isChecked() {
        return Settings.Secure.getIntForUser(this.mContext.getContentResolver(), "face_unlock_dismisses_keyguard", this.mContext.getResources().getBoolean(17891466) ? 1 : 0, getUserId()) != 0;
    }

    public boolean setChecked(boolean z) {
        Settings.Secure.putInt(this.mContext.getContentResolver(), "face_unlock_dismisses_keyguard", z ? 1 : 0);
        return true;
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

    public int getAvailabilityStatus() {
        FaceManager faceManager;
        if (!this.mUserManager.isManagedProfile(UserHandle.myUserId()) && (faceManager = this.mFaceManager) != null && faceManager.isHardwareDetected()) {
            return this.mFaceManager.hasEnrolledTemplates(getUserId()) ? 0 : 5;
        }
        return 3;
    }
}
