package com.android.dialer.commandline.impl;

import android.text.TextUtils;
import com.android.dialer.commandline.Arguments;
import com.android.dialer.commandline.Command;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class Echo implements Command {
    public String getShortDescription() {
        return "@hide Print all arguments.";
    }

    public String getUsage() {
        return "echo [arguments...]";
    }

    public ListenableFuture<String> run(Arguments arguments) throws Command.IllegalCommandLineArgumentException {
        return Futures.immediateFuture(TextUtils.join(" ", arguments.getPositionals()));
    }
}
