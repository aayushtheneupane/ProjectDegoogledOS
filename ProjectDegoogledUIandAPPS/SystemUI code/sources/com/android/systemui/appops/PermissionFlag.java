package com.android.systemui.appops;

/* compiled from: PermissionFlagsCache.kt */
final class PermissionFlag {
    private final int flag;
    private final long timestamp;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof PermissionFlag) {
                PermissionFlag permissionFlag = (PermissionFlag) obj;
                if (this.flag == permissionFlag.flag) {
                    if (this.timestamp == permissionFlag.timestamp) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Integer.hashCode(this.flag) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "PermissionFlag(flag=" + this.flag + ", timestamp=" + this.timestamp + ")";
    }

    public PermissionFlag(int i, long j) {
        this.flag = i;
        this.timestamp = j;
    }

    public final int getFlag() {
        return this.flag;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
