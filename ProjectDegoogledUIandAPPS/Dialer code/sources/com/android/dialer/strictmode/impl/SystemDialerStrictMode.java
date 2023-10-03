package com.android.dialer.strictmode.impl;

import android.app.Application;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.design.R$dimen;
import com.android.dialer.common.Assert;
import com.android.dialer.storage.StorageComponent;
import com.android.dialer.strictmode.DialerStrictMode;
import com.android.dialer.strictmode.StrictModeUtils;
import com.android.dialer.strictmode.impl.AutoValue_SystemDialerStrictMode_StrictModeVmConfig;
import com.google.auto.value.AutoValue;

final class SystemDialerStrictMode implements DialerStrictMode {
    private static final StrictMode.ThreadPolicy THREAD_DEATH_PENALTY = new StrictMode.ThreadPolicy.Builder().penaltyLog().penaltyDeath().build();
    private static final StrictMode.VmPolicy VM_DEATH_PENALTY = new StrictMode.VmPolicy.Builder().penaltyLog().penaltyDeath().build();

    @AutoValue
    static abstract class StrictModeVmConfig {

        public static abstract class Builder {
            Builder() {
            }

            public abstract StrictModeVmConfig build();
        }

        StrictModeVmConfig() {
        }
    }

    public void onApplicationCreate(Application application) {
        if (StrictModeUtils.isStrictModeAllowed()) {
            if (R$dimen.isUserUnlocked(application)) {
                PreferenceManager.getDefaultSharedPreferences(application);
                application.getSharedPreferences(application.getPackageName(), 0);
            }
            StorageComponent.get(application).unencryptedSharedPrefs();
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder(THREAD_DEATH_PENALTY).detectAll().build());
            StrictMode.VmPolicy vmPolicy = VM_DEATH_PENALTY;
            Assert.isNotNull(new AutoValue_SystemDialerStrictMode_StrictModeVmConfig.Builder().build());
            StrictMode.VmPolicy.Builder detectLeakedSqlLiteObjects = new StrictMode.VmPolicy.Builder(vmPolicy).detectLeakedClosableObjects().detectLeakedSqlLiteObjects();
            int i = Build.VERSION.SDK_INT;
            detectLeakedSqlLiteObjects.detectContentUriWithoutPermission();
            StrictMode.setVmPolicy(detectLeakedSqlLiteObjects.build());
            new Handler(Looper.myLooper()).postAtFrontOfQueue($$Lambda$SystemDialerStrictMode$_qvKNOJ5TTFJIagjswmPr9E2rfE.INSTANCE);
        }
    }
}
