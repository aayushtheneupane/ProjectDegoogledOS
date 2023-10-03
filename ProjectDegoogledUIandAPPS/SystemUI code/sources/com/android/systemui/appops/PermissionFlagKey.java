package com.android.systemui.appops;

import android.os.UserHandle;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionFlagsCache.kt */
final class PermissionFlagKey {
    private final String packageName;
    private final String permission;
    private final UserHandle user;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PermissionFlagKey)) {
            return false;
        }
        PermissionFlagKey permissionFlagKey = (PermissionFlagKey) obj;
        return Intrinsics.areEqual((Object) this.permission, (Object) permissionFlagKey.permission) && Intrinsics.areEqual((Object) this.packageName, (Object) permissionFlagKey.packageName) && Intrinsics.areEqual((Object) this.user, (Object) permissionFlagKey.user);
    }

    public int hashCode() {
        String str = this.permission;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.packageName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        UserHandle userHandle = this.user;
        if (userHandle != null) {
            i = userHandle.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "PermissionFlagKey(permission=" + this.permission + ", packageName=" + this.packageName + ", user=" + this.user + ")";
    }

    public PermissionFlagKey(String str, String str2, UserHandle userHandle) {
        Intrinsics.checkParameterIsNotNull(str, "permission");
        Intrinsics.checkParameterIsNotNull(str2, "packageName");
        Intrinsics.checkParameterIsNotNull(userHandle, "user");
        this.permission = str;
        this.packageName = str2;
        this.user = userHandle;
    }

    public final String getPermission() {
        return this.permission;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final UserHandle getUser() {
        return this.user;
    }
}
