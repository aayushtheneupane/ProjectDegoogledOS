package com.android.dialer.commandline;

import com.android.dialer.commandline.CommandSupplier;
import com.android.dialer.commandline.impl.ActiveCallsCommand;
import com.android.dialer.commandline.impl.BlockingCommand;
import com.android.dialer.commandline.impl.CallCommand;
import com.android.dialer.commandline.impl.Echo;
import com.android.dialer.commandline.impl.Help;
import com.android.dialer.commandline.impl.Version;

public class CommandLineModule$AospCommandInjector {
    private final ActiveCallsCommand activeCallsCommand;
    private final BlockingCommand blockingCommand;
    private final CallCommand callCommand;
    private final Echo echo;
    private final Help help;
    private final Version version;

    CommandLineModule$AospCommandInjector(Help help2, Version version2, Echo echo2, BlockingCommand blockingCommand2, CallCommand callCommand2, ActiveCallsCommand activeCallsCommand2) {
        this.help = help2;
        this.version = version2;
        this.echo = echo2;
        this.blockingCommand = blockingCommand2;
        this.callCommand = callCommand2;
        this.activeCallsCommand = activeCallsCommand2;
    }

    public CommandSupplier.Builder inject(CommandSupplier.Builder builder) {
        builder.commandsBuilder().put("help", this.help);
        builder.commandsBuilder().put("version", this.version);
        builder.commandsBuilder().put("echo", this.echo);
        builder.commandsBuilder().put("blocking", this.blockingCommand);
        builder.commandsBuilder().put("call", this.callCommand);
        builder.commandsBuilder().put("activecalls", this.activeCallsCommand);
        return builder;
    }
}
