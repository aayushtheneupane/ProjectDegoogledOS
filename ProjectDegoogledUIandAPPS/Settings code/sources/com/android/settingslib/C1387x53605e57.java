package com.android.settingslib;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import com.android.settingslib.RestrictedLockUtilsInternal;

/* renamed from: com.android.settingslib.-$$Lambda$RestrictedLockUtilsInternal$yvS34yJS2kpTNeXUsuaEu-8yH1g */
/* compiled from: lambda */
public final /* synthetic */ class C1387x53605e57 implements RestrictedLockUtilsInternal.LockSettingCheck {
    public static final /* synthetic */ C1387x53605e57 INSTANCE = new C1387x53605e57();

    private /* synthetic */ C1387x53605e57() {
    }

    public final boolean isEnforcing(DevicePolicyManager devicePolicyManager, ComponentName componentName, int i) {
        return RestrictedLockUtilsInternal.lambda$checkIfPasswordQualityIsSet$1(devicePolicyManager, componentName, i);
    }
}
