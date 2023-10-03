package com.android.systemui.appops;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.util.ArrayMap;
import com.android.internal.annotations.VisibleForTesting;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionFlagsCache.kt */
public class PermissionFlagsCache {
    private final PackageManager packageManager;
    private final ArrayMap<PermissionFlagKey, PermissionFlag> permissionFlagsCache = new ArrayMap<>();

    public PermissionFlagsCache(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.packageManager = context.getPackageManager();
    }

    public final int getPermissionFlags(String str, String str2, UserHandle userHandle) {
        Intrinsics.checkParameterIsNotNull(str, "permission");
        Intrinsics.checkParameterIsNotNull(str2, "packageName");
        Intrinsics.checkParameterIsNotNull(userHandle, "user");
        PermissionFlagKey permissionFlagKey = new PermissionFlagKey(str, str2, userHandle);
        long currentTime = getCurrentTime();
        ArrayMap<PermissionFlagKey, PermissionFlag> arrayMap = this.permissionFlagsCache;
        PermissionFlag permissionFlag = arrayMap.get(permissionFlagKey);
        if (permissionFlag == null) {
            permissionFlag = new PermissionFlag(getFlags(permissionFlagKey), currentTime);
            arrayMap.put(permissionFlagKey, permissionFlag);
        }
        PermissionFlag permissionFlag2 = permissionFlag;
        if (currentTime - permissionFlag2.getTimestamp() <= 10000) {
            return permissionFlag2.getFlag();
        }
        PermissionFlag permissionFlag3 = new PermissionFlag(getFlags(permissionFlagKey), currentTime);
        this.permissionFlagsCache.put(permissionFlagKey, permissionFlag3);
        return permissionFlag3.getFlag();
    }

    private final int getFlags(PermissionFlagKey permissionFlagKey) {
        return this.packageManager.getPermissionFlags(permissionFlagKey.getPermission(), permissionFlagKey.getPackageName(), permissionFlagKey.getUser());
    }

    /* access modifiers changed from: protected */
    @VisibleForTesting
    public long getCurrentTime() {
        return System.currentTimeMillis();
    }
}
