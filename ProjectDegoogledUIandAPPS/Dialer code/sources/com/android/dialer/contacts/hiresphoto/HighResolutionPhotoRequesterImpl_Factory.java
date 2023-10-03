package com.android.dialer.contacts.hiresphoto;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class HighResolutionPhotoRequesterImpl_Factory implements Factory<HighResolutionPhotoRequesterImpl> {
    private final Provider<Context> appContextProvider;
    private final Provider<ListeningExecutorService> backgroundExecutorProvider;

    public HighResolutionPhotoRequesterImpl_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2) {
        this.appContextProvider = provider;
        this.backgroundExecutorProvider = provider2;
    }

    public static Factory<HighResolutionPhotoRequesterImpl> create(Provider<Context> provider, Provider<ListeningExecutorService> provider2) {
        return new HighResolutionPhotoRequesterImpl_Factory(provider, provider2);
    }

    public Object get() {
        return new HighResolutionPhotoRequesterImpl(this.appContextProvider.get(), this.backgroundExecutorProvider.get());
    }
}
