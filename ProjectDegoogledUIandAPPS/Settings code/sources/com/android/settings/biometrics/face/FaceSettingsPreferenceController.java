package com.android.settings.biometrics.face;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.UserHandle;
import com.android.settings.core.TogglePreferenceController;

public abstract class FaceSettingsPreferenceController extends TogglePreferenceController {
    private int mUserId;

    public FaceSettingsPreferenceController(Context context, String str) {
        super(context, str);
    }

    public void setUserId(int i) {
        this.mUserId = i;
    }

    /* access modifiers changed from: protected */
    public int getUserId() {
        return this.mUserId;
    }

    /* access modifiers changed from: protected */
    public boolean adminDisabled() {
        DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.mContext.getSystemService("device_policy");
        return (devicePolicyManager == null || (devicePolicyManager.getKeyguardDisabledFeatures((ComponentName) null, UserHandle.myUserId()) & 128) == 0) ? false : true;
    }
}
