package com.android.dialer.commandline.impl;

import android.content.Context;
import com.android.dialer.activecalls.ActiveCallsComponent;
import com.android.dialer.activecalls.impl.ActiveCallsImpl;
import com.android.dialer.commandline.Arguments;
import com.android.dialer.commandline.Command;
import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

public class ActiveCallsCommand implements Command {
    private final Context appContext;

    ActiveCallsCommand(Context context) {
        this.appContext = context;
    }

    public String getShortDescription() {
        return "manipulate active calls";
    }

    public String getUsage() {
        return "activecalls list";
    }

    public ListenableFuture<String> run(Arguments arguments) throws Command.IllegalCommandLineArgumentException {
        if (arguments.getPositionals().isEmpty()) {
            return Futures.immediateFuture("activecalls list");
        }
        boolean z = false;
        String str = arguments.getPositionals().get(0);
        if (str.hashCode() != 3322014 || !str.equals("list")) {
            z = true;
        }
        if (!z) {
            return Futures.immediateFuture(((ActiveCallsImpl) ActiveCallsComponent.get(this.appContext).activeCalls()).getActiveCalls().toString());
        }
        throw new Command.IllegalCommandLineArgumentException(GeneratedOutlineSupport.outline8("unknown command ", str));
    }
}
