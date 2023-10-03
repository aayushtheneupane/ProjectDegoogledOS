package com.android.dialer.commandline.impl;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BlockingCommand_Factory implements Factory<BlockingCommand> {
    private final Provider<Context> contextProvider;
    private final Provider<ListeningExecutorService> executorServiceProvider;

    public BlockingCommand_Factory(Provider<Context> provider, Provider<ListeningExecutorService> provider2) {
        this.contextProvider = provider;
        this.executorServiceProvider = provider2;
    }

    public Object get() {
        return new BlockingCommand(this.contextProvider.get(), this.executorServiceProvider.get());
    }
}
