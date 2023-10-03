package com.android.dialer.commandline;

import com.android.dialer.commandline.impl.ActiveCallsCommand;
import com.android.dialer.commandline.impl.BlockingCommand;
import com.android.dialer.commandline.impl.CallCommand;
import com.android.dialer.commandline.impl.Echo;
import com.android.dialer.commandline.impl.Help;
import com.android.dialer.commandline.impl.Version;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class CommandLineModule_AospCommandInjector_Factory implements Factory<CommandLineModule$AospCommandInjector> {
    private final Provider<ActiveCallsCommand> activeCallsCommandProvider;
    private final Provider<BlockingCommand> blockingCommandProvider;
    private final Provider<CallCommand> callCommandProvider;
    private final Provider<Echo> echoProvider;
    private final Provider<Help> helpProvider;
    private final Provider<Version> versionProvider;

    public CommandLineModule_AospCommandInjector_Factory(Provider<Help> provider, Provider<Version> provider2, Provider<Echo> provider3, Provider<BlockingCommand> provider4, Provider<CallCommand> provider5, Provider<ActiveCallsCommand> provider6) {
        this.helpProvider = provider;
        this.versionProvider = provider2;
        this.echoProvider = provider3;
        this.blockingCommandProvider = provider4;
        this.callCommandProvider = provider5;
        this.activeCallsCommandProvider = provider6;
    }

    public Object get() {
        return new CommandLineModule$AospCommandInjector(this.helpProvider.get(), this.versionProvider.get(), this.echoProvider.get(), this.blockingCommandProvider.get(), this.callCommandProvider.get(), this.activeCallsCommandProvider.get());
    }
}
