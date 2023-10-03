package com.android.dialer.commandline.impl;

import android.content.Context;
import com.android.dialer.binary.aosp.DaggerAospDialerRootComponent;
import com.android.dialer.commandline.Arguments;
import com.android.dialer.commandline.Command;
import com.android.dialer.inject.HasRootComponent;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Help implements Command {
    private final Context context;

    Help(Context context2) {
        this.context = context2;
    }

    public String getShortDescription() {
        return "Print this message";
    }

    public String getUsage() {
        return "help";
    }

    public ListenableFuture<String> run(Arguments arguments) throws Command.IllegalCommandLineArgumentException {
        boolean containsKey = arguments.getFlags().containsKey("showHidden");
        StringBuilder sb = new StringBuilder();
        ImmutableMap immutableMap = ((DaggerAospDialerRootComponent) ((HasRootComponent) this.context.getApplicationContext()).component()).commandLineComponent().commandSupplier().get();
        try {
            sb.append(((Command) immutableMap.get("version")).run(Arguments.EMPTY).get());
            sb.append("\n");
            sb.append("\n");
            sb.append("usage: <command> [args...]\n");
            sb.append("\n");
            sb.append("<command>\n");
            UnmodifiableIterator it = immutableMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String shortDescription = ((Command) entry.getValue()).getShortDescription();
                if (containsKey || !shortDescription.startsWith("@hide ")) {
                    sb.append(String.format(Locale.US, "  %20s  %s\n", new Object[]{entry.getKey(), shortDescription}));
                }
            }
            return Futures.immediateFuture(sb.toString());
        } catch (InterruptedException e) {
            Thread.interrupted();
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            throw new RuntimeException(e2);
        }
    }
}
