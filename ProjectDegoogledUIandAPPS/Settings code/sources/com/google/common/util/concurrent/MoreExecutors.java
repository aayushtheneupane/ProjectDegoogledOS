package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

public final class MoreExecutors {

    static class Application {
        Application() {
        }

        /* access modifiers changed from: package-private */
        public void addShutdownHook(Thread thread) {
            Runtime.getRuntime().addShutdownHook(thread);
        }
    }

    public static Executor directExecutor() {
        return DirectExecutor.INSTANCE;
    }

    private enum DirectExecutor implements Executor {
        INSTANCE;

        public void execute(Runnable runnable) {
            runnable.run();
        }
    }
}
