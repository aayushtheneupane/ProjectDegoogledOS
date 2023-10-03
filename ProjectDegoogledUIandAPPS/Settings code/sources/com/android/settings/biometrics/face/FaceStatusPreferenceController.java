package com.android.settings.biometrics.face;

import android.content.Context;
import android.hardware.face.FaceManager;
import com.android.settings.Settings;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricStatusPreferenceController;
import com.havoc.config.center.C1715R;

public class FaceStatusPreferenceController extends BiometricStatusPreferenceController {
    public static final String KEY_FACE_SETTINGS = "face_settings";
    protected final FaceManager mFaceManager;

    public FaceStatusPreferenceController(Context context) {
        this(context, KEY_FACE_SETTINGS);
    }

    public FaceStatusPreferenceController(Context context, String str) {
        super(context, str);
        this.mFaceManager = Utils.getFaceManagerOrNull(context);
    }

    /* access modifiers changed from: protected */
    public boolean isDeviceSupported() {
        FaceManager faceManager = this.mFaceManager;
        return faceManager != null && faceManager.isHardwareDetected();
    }

    /* access modifiers changed from: protected */
    public boolean hasEnrolledBiometrics() {
        return this.mFaceManager.hasEnrolledTemplates(getUserId());
    }

    /* access modifiers changed from: protected */
    public String getSummaryTextEnrolled() {
        return this.mContext.getResources().getString(C1715R.string.security_settings_face_preference_summary);
    }

    /* access modifiers changed from: protected */
    public String getSummaryTextNoneEnrolled() {
        return this.mContext.getResources().getString(C1715R.string.security_settings_face_preference_summary_none);
    }

    /* access modifiers changed from: protected */
    public String getSettingsClassName() {
        return Settings.FaceSettingsActivity.class.getName();
    }

    /* access modifiers changed from: protected */
    public String getEnrollClassName() {
        return FaceEnrollIntroduction.class.getName();
    }
}
