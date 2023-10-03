package com.android.settings.biometrics.fingerprint;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import com.android.settings.Utils;
import com.android.settings.biometrics.BiometricStatusPreferenceController;
import com.havoc.config.center.C1715R;

public class FingerprintStatusPreferenceController extends BiometricStatusPreferenceController {
    private static final String KEY_FINGERPRINT_SETTINGS = "fingerprint_settings";
    protected final FingerprintManager mFingerprintManager;

    public FingerprintStatusPreferenceController(Context context) {
        this(context, KEY_FINGERPRINT_SETTINGS);
    }

    public FingerprintStatusPreferenceController(Context context, String str) {
        super(context, str);
        this.mFingerprintManager = Utils.getFingerprintManagerOrNull(context);
    }

    /* access modifiers changed from: protected */
    public boolean isDeviceSupported() {
        FingerprintManager fingerprintManager = this.mFingerprintManager;
        return fingerprintManager != null && fingerprintManager.isHardwareDetected();
    }

    /* access modifiers changed from: protected */
    public boolean hasEnrolledBiometrics() {
        return this.mFingerprintManager.hasEnrolledFingerprints(getUserId());
    }

    /* access modifiers changed from: protected */
    public String getSummaryTextEnrolled() {
        int size = this.mFingerprintManager.getEnrolledFingerprints(getUserId()).size();
        return this.mContext.getResources().getQuantityString(C1715R.plurals.security_settings_fingerprint_preference_summary, size, new Object[]{Integer.valueOf(size)});
    }

    /* access modifiers changed from: protected */
    public String getSummaryTextNoneEnrolled() {
        return this.mContext.getString(C1715R.string.security_settings_fingerprint_preference_summary_none);
    }

    /* access modifiers changed from: protected */
    public String getSettingsClassName() {
        return FingerprintSettings.class.getName();
    }

    /* access modifiers changed from: protected */
    public String getEnrollClassName() {
        return FingerprintEnrollIntroduction.class.getName();
    }
}
