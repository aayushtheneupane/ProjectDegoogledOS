package com.android.dialer.commandline.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class Version_Factory implements Factory<Version> {
    private final Provider<Context> contextProvider;

    public Version_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public Object get() {
        return new Version(this.contextProvider.get());
    }
}
