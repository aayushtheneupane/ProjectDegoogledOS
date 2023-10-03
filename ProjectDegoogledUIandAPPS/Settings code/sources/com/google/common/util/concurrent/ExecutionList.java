package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ExecutionList {
    static final Logger log = Logger.getLogger(ExecutionList.class.getName());
    private boolean executed;
    private RunnableExecutorPair runnables;

    public void add(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        synchronized (this) {
            if (!this.executed) {
                this.runnables = new RunnableExecutorPair(runnable, executor, this.runnables);
            } else {
                executeListener(runnable, executor);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
        r0 = r1.next;
        r1.next = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0019, code lost:
        if (r2 == null) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001b, code lost:
        executeListener(r2.runnable, r2.executor);
        r2 = r2.next;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
        r2 = r1;
        r1 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0012, code lost:
        if (r1 == null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.executed     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r2)     // Catch:{ all -> 0x0026 }
            return
        L_0x0007:
            r0 = 1
            r2.executed = r0     // Catch:{ all -> 0x0026 }
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = r2.runnables     // Catch:{ all -> 0x0026 }
            r1 = 0
            r2.runnables = r1     // Catch:{ all -> 0x0026 }
            monitor-exit(r2)     // Catch:{ all -> 0x0026 }
        L_0x0010:
            r2 = r1
            r1 = r0
            if (r1 == 0) goto L_0x0019
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r0 = r1.next
            r1.next = r2
            goto L_0x0010
        L_0x0019:
            if (r2 == 0) goto L_0x0025
            java.lang.Runnable r0 = r2.runnable
            java.util.concurrent.Executor r1 = r2.executor
            executeListener(r0, r1)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r2 = r2.next
            goto L_0x0019
        L_0x0025:
            return
        L_0x0026:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0026 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ExecutionList.execute():void");
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            logger.log(level, "RuntimeException while executing runnable " + runnable + " with executor " + executor, e);
        }
    }

    private static final class RunnableExecutorPair {
        final Executor executor;
        RunnableExecutorPair next;
        final Runnable runnable;

        RunnableExecutorPair(Runnable runnable2, Executor executor2, RunnableExecutorPair runnableExecutorPair) {
            this.runnable = runnable2;
            this.executor = executor2;
            this.next = runnableExecutorPair;
        }
    }
}
