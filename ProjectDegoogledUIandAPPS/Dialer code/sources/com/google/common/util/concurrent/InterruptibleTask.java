package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;

abstract class InterruptibleTask extends AtomicReference<Thread> implements Runnable {
    private volatile boolean doneInterrupting;

    InterruptibleTask() {
    }

    /* access modifiers changed from: package-private */
    public final void interruptTask() {
        Thread thread = (Thread) get();
        if (thread != null) {
            thread.interrupt();
        }
        this.doneInterrupting = true;
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void run() {
        /*
            r2 = this;
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r1 = 0
            boolean r0 = r2.compareAndSet(r1, r0)
            if (r0 != 0) goto L_0x000c
            return
        L_0x000c:
            r2.runInterruptibly()     // Catch:{ all -> 0x001e }
            boolean r0 = r2.wasInterrupted()
            if (r0 == 0) goto L_0x001d
        L_0x0015:
            boolean r0 = r2.doneInterrupting
            if (r0 != 0) goto L_0x001d
            java.lang.Thread.yield()
            goto L_0x0015
        L_0x001d:
            return
        L_0x001e:
            r0 = move-exception
            boolean r1 = r2.wasInterrupted()
            if (r1 == 0) goto L_0x002d
        L_0x0025:
            boolean r1 = r2.doneInterrupting
            if (r1 != 0) goto L_0x002d
            java.lang.Thread.yield()
            goto L_0x0025
        L_0x002d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.InterruptibleTask.run():void");
    }

    /* access modifiers changed from: package-private */
    public abstract void runInterruptibly();

    /* access modifiers changed from: package-private */
    public abstract boolean wasInterrupted();
}
