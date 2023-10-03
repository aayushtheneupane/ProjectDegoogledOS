package com.android.dialer.commandline.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ActiveCallsCommand_Factory implements Factory<ActiveCallsCommand> {
    private final Provider<Context> appContextProvider;

    public ActiveCallsCommand_Factory(Provider<Context> provider) {
        this.appContextProvider = provider;
    }

    public Object get() {
        return new ActiveCallsCommand(this.appContextProvider.get());
    }
}
