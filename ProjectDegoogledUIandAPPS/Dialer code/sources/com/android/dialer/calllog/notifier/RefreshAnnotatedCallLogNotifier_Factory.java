package com.android.dialer.calllog.notifier;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RefreshAnnotatedCallLogNotifier_Factory implements Factory<RefreshAnnotatedCallLogNotifier> {
    private final Provider<Context> appContextProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;

    public RefreshAnnotatedCallLogNotifier_Factory(Provider<Context> provider, Provider<SharedPreferences> provider2) {
        this.appContextProvider = provider;
        this.sharedPreferencesProvider = provider2;
    }

    public Object get() {
        return new RefreshAnnotatedCallLogNotifier(this.appContextProvider.get(), this.sharedPreferencesProvider.get());
    }
}
