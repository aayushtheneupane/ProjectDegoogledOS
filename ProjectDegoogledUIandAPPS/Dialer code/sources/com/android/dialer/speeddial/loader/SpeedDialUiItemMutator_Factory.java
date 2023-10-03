package com.android.dialer.speeddial.loader;

import android.content.Context;
import com.android.dialer.contacts.displaypreference.ContactDisplayPreferences;
import com.android.dialer.contacts.hiresphoto.HighResolutionPhotoRequester;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SpeedDialUiItemMutator_Factory implements Factory<SpeedDialUiItemMutator> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;
    private final Provider<ContactDisplayPreferences> contactDisplayPreferencesProvider;
    private final Provider<HighResolutionPhotoRequester> highResolutionPhotoRequesterProvider;

    public SpeedDialUiItemMutator_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2, Provider<ContactDisplayPreferences> provider3, Provider<HighResolutionPhotoRequester> provider4) {
        this.appContextProvider = provider;
        this.backgroundExecutorProvider = provider2;
        this.contactDisplayPreferencesProvider = provider3;
        this.highResolutionPhotoRequesterProvider = provider4;
    }

    public Object get() {
        return new SpeedDialUiItemMutator(this.appContextProvider.get(), this.backgroundExecutorProvider.get(), this.contactDisplayPreferencesProvider.get(), this.highResolutionPhotoRequesterProvider.get());
    }
}
