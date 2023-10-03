package com.android.settings.biometrics.face;

import android.content.ContentResolver;
import android.content.Context;
import android.hardware.face.FaceManager;
import android.provider.Settings;
import androidx.preference.PreferenceScreen;
import com.android.settings.Utils;
import com.havoc.support.preferences.SwitchPreference;

public class FaceSettingsAttentionPreferenceController extends FaceSettingsPreferenceController {
    public static final String KEY = "security_settings_face_require_attention";
    /* access modifiers changed from: private */
    public FaceManager mFaceManager;
    private final FaceManager.GetFeatureCallback mGetFeatureCallback;
    /* access modifiers changed from: private */
    public SwitchPreference mPreference;
    private final FaceManager.SetFeatureCallback mSetFeatureCallback;
    private byte[] mToken;

    public int getAvailabilityStatus() {
        return 0;
    }

    public FaceSettingsAttentionPreferenceController(Context context, String str) {
        super(context, str);
        this.mSetFeatureCallback = new FaceManager.SetFeatureCallback() {
            public void onCompleted(boolean z, int i) {
                if (i == 1) {
                    FaceSettingsAttentionPreferenceController.this.mPreference.setEnabled(true);
                    boolean z2 = false;
                    if (!z) {
                        SwitchPreference access$000 = FaceSettingsAttentionPreferenceController.this.mPreference;
                        if (!FaceSettingsAttentionPreferenceController.this.mPreference.isChecked()) {
                            z2 = true;
                        }
                        access$000.setChecked(z2);
                        return;
                    }
                    ContentResolver contentResolver = FaceSettingsAttentionPreferenceController.this.mContext.getContentResolver();
                    if (FaceSettingsAttentionPreferenceController.this.mPreference.isChecked()) {
                        z2 = true;
                    }
                    Settings.Secure.putIntForUser(contentResolver, "face_unlock_attention_required", z2 ? 1 : 0, FaceSettingsAttentionPreferenceController.this.getUserId());
                }
            }
        };
        this.mGetFeatureCallback = new FaceManager.GetFeatureCallback() {
            public void onCompleted(boolean z, int i, boolean z2) {
                if (i == 1 && z) {
                    if (!FaceSettingsAttentionPreferenceController.this.mFaceManager.hasEnrolledTemplates(FaceSettingsAttentionPreferenceController.this.getUserId())) {
                        FaceSettingsAttentionPreferenceController.this.mPreference.setEnabled(false);
                        return;
                    }
                    FaceSettingsAttentionPreferenceController.this.mPreference.setEnabled(true);
                    FaceSettingsAttentionPreferenceController.this.mPreference.setChecked(z2);
                }
            }
        };
        this.mFaceManager = Utils.getFaceManagerOrNull(context);
    }

    public FaceSettingsAttentionPreferenceController(Context context) {
        this(context, KEY);
    }

    public void setToken(byte[] bArr) {
        this.mToken = bArr;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SwitchPreference) preferenceScreen.findPreference(KEY);
    }

    public boolean isChecked() {
        if (!FaceSettings.isAvailable(this.mContext)) {
            return true;
        }
        this.mPreference.setEnabled(false);
        this.mFaceManager.getFeature(getUserId(), 1, this.mGetFeatureCallback);
        return true;
    }

    public boolean setChecked(boolean z) {
        this.mPreference.setEnabled(false);
        this.mPreference.setChecked(z);
        this.mFaceManager.setFeature(getUserId(), 1, z, this.mToken, this.mSetFeatureCallback);
        return true;
    }
}
