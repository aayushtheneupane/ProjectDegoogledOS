package com.android.dialer.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.appcompat.R$style;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StorageModule_ProvideUnencryptedSharedPrefsFactory implements Factory<SharedPreferences> {
    private final Provider<Context> appContextProvider;

    public StorageModule_ProvideUnencryptedSharedPrefsFactory(Provider<Context> provider) {
        this.appContextProvider = provider;
    }

    public Object get() {
        Context context = this.appContextProvider.get();
        Context createDeviceProtectedStorageContext = ContextCompat.createDeviceProtectedStorageContext(context);
        if (createDeviceProtectedStorageContext != null) {
            context = createDeviceProtectedStorageContext;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        R$style.checkNotNull1(defaultSharedPreferences, "Cannot return null from a non-@Nullable @Provides method");
        return defaultSharedPreferences;
    }
}
