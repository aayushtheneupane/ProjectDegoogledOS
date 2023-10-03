package com.android.dialer.commandline.impl;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CallCommand_Factory implements Factory<CallCommand> {
    private final Provider<Context> appContextProvider;

    public CallCommand_Factory(Provider<Context> provider) {
        this.appContextProvider = provider;
    }

    public Object get() {
        return new CallCommand(this.appContextProvider.get());
    }
}
