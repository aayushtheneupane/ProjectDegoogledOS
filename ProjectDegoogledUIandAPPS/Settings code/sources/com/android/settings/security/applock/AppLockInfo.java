package com.android.settings.security.applock;

import android.app.AppLockManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

public class AppLockInfo {
    private boolean mAppLocked;
    private Drawable mIcon;
    private String mLabel;
    private String mPackageName;

    AppLockInfo(ResolveInfo resolveInfo, PackageManager packageManager, AppLockManager appLockManager) {
        this.mLabel = resolveInfo.loadLabel(packageManager).toString();
        this.mIcon = resolveInfo.loadIcon(packageManager);
        this.mPackageName = resolveInfo.activityInfo.packageName;
        this.mAppLocked = appLockManager.isAppLocked(this.mPackageName);
    }

    /* access modifiers changed from: package-private */
    public String getLabel() {
        return this.mLabel;
    }

    /* access modifiers changed from: package-private */
    public void setLabel(String str) {
        this.mLabel = str;
    }

    /* access modifiers changed from: package-private */
    public String getPackageName() {
        return this.mPackageName;
    }

    /* access modifiers changed from: package-private */
    public boolean isAppLocked() {
        return this.mAppLocked;
    }

    /* access modifiers changed from: package-private */
    public void setAppLocked(boolean z) {
        this.mAppLocked = z;
    }

    /* access modifiers changed from: package-private */
    public Drawable getIcon() {
        return this.mIcon;
    }

    public String toString() {
        return this.mLabel;
    }
}
