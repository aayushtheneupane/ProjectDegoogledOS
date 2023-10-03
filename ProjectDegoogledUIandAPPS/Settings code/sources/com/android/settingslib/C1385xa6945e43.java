package com.android.settingslib;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import com.android.settingslib.RestrictedLockUtilsInternal;

/* renamed from: com.android.settingslib.-$$Lambda$RestrictedLockUtilsInternal$GXYFzBzGab6v5GcOkljXViw5O7I */
/* compiled from: lambda */
public final /* synthetic */ class C1385xa6945e43 implements RestrictedLockUtilsInternal.LockSettingCheck {
    public static final /* synthetic */ C1385xa6945e43 INSTANCE = new C1385xa6945e43();

    private /* synthetic */ C1385xa6945e43() {
    }

    public final boolean isEnforcing(DevicePolicyManager devicePolicyManager, ComponentName componentName, int i) {
        return RestrictedLockUtilsInternal.lambda$checkIfMaximumTimeToLockIsSet$2(devicePolicyManager, componentName, i);
    }
}
