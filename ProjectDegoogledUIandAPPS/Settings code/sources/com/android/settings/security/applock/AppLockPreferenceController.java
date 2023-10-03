package com.android.settings.security.applock;

import android.app.AppLockManager;
import android.content.Context;
import android.content.res.Resources;
import com.android.settings.biometrics.BiometricStatusPreferenceController;
import com.havoc.config.center.C1715R;

public class AppLockPreferenceController extends BiometricStatusPreferenceController {
    private static final String KEY_APP_LOCK = "app_lock_settings";
    private static final String TAG = "AppLockPrefController";
    private AppLockManager mAppLockManager;
    private Resources mResources;

    public AppLockPreferenceController(Context context) {
        super(context, KEY_APP_LOCK);
        this.mAppLockManager = (AppLockManager) context.getSystemService("applock");
        this.mResources = context.getResources();
    }

    /* access modifiers changed from: protected */
    public boolean isDeviceSupported() {
        return this.mAppLockManager != null;
    }

    /* access modifiers changed from: protected */
    public boolean hasEnrolledBiometrics() {
        return this.mAppLockManager.getLockedAppsCount() > 0;
    }

    /* access modifiers changed from: protected */
    public String getSummaryTextEnrolled() {
        int lockedAppsCount = this.mAppLockManager.getLockedAppsCount();
        return this.mResources.getQuantityString(C1715R.plurals.applock_summary, lockedAppsCount, new Object[]{Integer.valueOf(lockedAppsCount)});
    }

    /* access modifiers changed from: protected */
    public String getSummaryTextNoneEnrolled() {
        return this.mResources.getString(C1715R.string.applock_setup);
    }

    /* access modifiers changed from: protected */
    public String getSettingsClassName() {
        return AppLockSettings.class.getName();
    }

    /* access modifiers changed from: protected */
    public String getEnrollClassName() {
        return AppLockSettings.class.getName();
    }
}
