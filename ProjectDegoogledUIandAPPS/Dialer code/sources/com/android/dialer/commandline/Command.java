package com.android.dialer.commandline;

import com.google.common.util.concurrent.ListenableFuture;

public interface Command {

    public static class IllegalCommandLineArgumentException extends Exception {
        public IllegalCommandLineArgumentException(String str) {
            super(str);
        }
    }

    String getShortDescription();

    String getUsage();

    ListenableFuture<String> run(Arguments arguments) throws IllegalCommandLineArgumentException;
}
