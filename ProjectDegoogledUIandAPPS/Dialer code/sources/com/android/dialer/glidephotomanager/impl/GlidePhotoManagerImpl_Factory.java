package com.android.dialer.glidephotomanager.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class GlidePhotoManagerImpl_Factory implements Factory<GlidePhotoManagerImpl> {
    private final Provider<Context> appContextProvider;

    public GlidePhotoManagerImpl_Factory(Provider<Context> provider) {
        this.appContextProvider = provider;
    }

    public Object get() {
        return new GlidePhotoManagerImpl(this.appContextProvider.get());
    }
}
