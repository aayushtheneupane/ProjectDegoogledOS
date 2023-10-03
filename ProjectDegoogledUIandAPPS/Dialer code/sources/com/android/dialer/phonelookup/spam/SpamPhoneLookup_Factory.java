package com.android.dialer.phonelookup.spam;

import android.content.SharedPreferences;
import com.android.dialer.spam.Spam;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SpamPhoneLookup_Factory implements Factory<SpamPhoneLookup> {
    private final Provider<ListeningExecutorService> backgroundExecutorServiceProvider;
    private final Provider<ListeningExecutorService> lightweightExecutorServiceProvider;
    private final Provider<SharedPreferences> sharedPreferencesProvider;
    private final Provider<Spam> spamProvider;

    public SpamPhoneLookup_Factory(Provider<ListeningExecutorService> provider, Provider<ListeningExecutorService> provider2, Provider<SharedPreferences> provider3, Provider<Spam> provider4) {
        this.backgroundExecutorServiceProvider = provider;
        this.lightweightExecutorServiceProvider = provider2;
        this.sharedPreferencesProvider = provider3;
        this.spamProvider = provider4;
    }

    public Object get() {
        return new SpamPhoneLookup(this.backgroundExecutorServiceProvider.get(), this.lightweightExecutorServiceProvider.get(), this.sharedPreferencesProvider.get(), this.spamProvider.get());
    }
}
