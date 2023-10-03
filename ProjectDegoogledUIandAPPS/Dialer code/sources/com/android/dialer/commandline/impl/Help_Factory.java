package com.android.dialer.commandline.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class Help_Factory implements Factory<Help> {
    private final Provider<Context> contextProvider;

    public Help_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public Object get() {
        return new Help(this.contextProvider.get());
    }
}
