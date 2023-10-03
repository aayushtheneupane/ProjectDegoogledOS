package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public final class Monitor {
    private Guard activeGuards;
    private final boolean fair;
    /* access modifiers changed from: private */
    public final ReentrantLock lock;

    public static abstract class Guard {
        final Condition condition;
        final Monitor monitor;
        Guard next;
        int waiterCount = 0;

        public abstract boolean isSatisfied();

        protected Guard(Monitor monitor2) {
            Preconditions.checkNotNull(monitor2, "monitor");
            this.monitor = monitor2;
            this.condition = monitor2.lock.newCondition();
        }
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean z) {
        this.activeGuards = null;
        this.fair = z;
        this.lock = new ReentrantLock(z);
    }

    public void enter() {
        this.lock.lock();
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x002d */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enter(long r6, java.util.concurrent.TimeUnit r8) {
        /*
            r5 = this;
            long r6 = r8.toNanos(r6)
            java.util.concurrent.locks.ReentrantLock r8 = r5.lock
            boolean r0 = r5.fair
            r1 = 1
            if (r0 != 0) goto L_0x0012
            boolean r0 = r8.tryLock()
            if (r0 == 0) goto L_0x0012
            return r1
        L_0x0012:
            long r2 = java.lang.System.nanoTime()
            long r2 = r2 + r6
            boolean r0 = java.lang.Thread.interrupted()
        L_0x001b:
            java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x002d, all -> 0x002b }
            boolean r6 = r8.tryLock(r6, r4)     // Catch:{ InterruptedException -> 0x002d, all -> 0x002b }
            if (r0 == 0) goto L_0x002a
            java.lang.Thread r7 = java.lang.Thread.currentThread()
            r7.interrupt()
        L_0x002a:
            return r6
        L_0x002b:
            r6 = move-exception
            goto L_0x0037
        L_0x002d:
            long r6 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0035 }
            long r6 = r2 - r6
            r0 = 1
            goto L_0x001b
        L_0x0035:
            r6 = move-exception
            r0 = 1
        L_0x0037:
            if (r0 == 0) goto L_0x0040
            java.lang.Thread r7 = java.lang.Thread.currentThread()
            r7.interrupt()
        L_0x0040:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enter(long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterInterruptibly(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.lock.tryLock(j, timeUnit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lockInterruptibly();
            try {
                if (!guard.isSatisfied()) {
                    await(guard, isHeldByCurrentThread);
                }
            } catch (Throwable th) {
                leave();
                throw th;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            reentrantLock.lock();
            try {
                if (!guard.isSatisfied()) {
                    awaitUninterruptibly(guard, isHeldByCurrentThread);
                }
            } catch (Throwable th) {
                leave();
                throw th;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterWhen(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
            boolean z = false;
            if (this.fair || !reentrantLock.tryLock()) {
                long nanoTime = System.nanoTime() + nanos;
                if (!reentrantLock.tryLock(j, timeUnit)) {
                    return false;
                }
                nanos = nanoTime - System.nanoTime();
            }
            try {
                if (guard.isSatisfied() || awaitNanos(guard, nanos, isHeldByCurrentThread)) {
                    z = true;
                }
                if (!z) {
                }
                return z;
            } catch (Throwable th) {
                if (!isHeldByCurrentThread) {
                    signalNextWaiter();
                }
                throw th;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 151 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0060 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r9, long r10, java.util.concurrent.TimeUnit r12) {
        /*
            r8 = this;
            long r10 = r12.toNanos(r10)
            com.google.common.util.concurrent.Monitor r12 = r9.monitor
            if (r12 != r8) goto L_0x007a
            java.util.concurrent.locks.ReentrantLock r12 = r8.lock
            long r0 = java.lang.System.nanoTime()
            long r0 = r0 + r10
            boolean r2 = r12.isHeldByCurrentThread()
            boolean r3 = java.lang.Thread.interrupted()
            boolean r4 = r8.fair     // Catch:{ all -> 0x006f }
            r5 = 1
            r6 = 0
            if (r4 != 0) goto L_0x0023
            boolean r4 = r12.tryLock()     // Catch:{ all -> 0x006f }
            if (r4 != 0) goto L_0x0041
        L_0x0023:
            r4 = 0
        L_0x0024:
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ InterruptedException -> 0x0038 }
            boolean r10 = r12.tryLock(r10, r7)     // Catch:{ InterruptedException -> 0x0038 }
            if (r10 != 0) goto L_0x0036
            if (r3 == 0) goto L_0x0035
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            r9.interrupt()
        L_0x0035:
            return r6
        L_0x0036:
            r4 = r10
            goto L_0x0039
        L_0x0038:
            r3 = 1
        L_0x0039:
            long r10 = java.lang.System.nanoTime()     // Catch:{ all -> 0x006f }
            long r10 = r0 - r10
            if (r4 == 0) goto L_0x0024
        L_0x0041:
            boolean r4 = r9.isSatisfied()     // Catch:{ InterruptedException -> 0x0060, all -> 0x005e }
            if (r4 != 0) goto L_0x004f
            boolean r9 = r8.awaitNanos(r9, r10, r2)     // Catch:{ InterruptedException -> 0x0060, all -> 0x005e }
            if (r9 == 0) goto L_0x004e
            goto L_0x004f
        L_0x004e:
            r5 = 0
        L_0x004f:
            if (r5 != 0) goto L_0x0054
            r12.unlock()     // Catch:{ all -> 0x006f }
        L_0x0054:
            if (r3 == 0) goto L_0x005d
            java.lang.Thread r9 = java.lang.Thread.currentThread()
            r9.interrupt()
        L_0x005d:
            return r5
        L_0x005e:
            r9 = move-exception
            goto L_0x006b
        L_0x0060:
            long r10 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0069 }
            long r10 = r0 - r10
            r2 = 0
            r3 = 1
            goto L_0x0041
        L_0x0069:
            r9 = move-exception
            r3 = 1
        L_0x006b:
            r12.unlock()     // Catch:{ all -> 0x006f }
            throw r9     // Catch:{ all -> 0x006f }
        L_0x006f:
            r9 = move-exception
            if (r3 == 0) goto L_0x0079
            java.lang.Thread r10 = java.lang.Thread.currentThread()
            r10.interrupt()
        L_0x0079:
            throw r9
        L_0x007a:
            java.lang.IllegalMonitorStateException r9 = new java.lang.IllegalMonitorStateException
            r9.<init>()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lock();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            reentrantLock.lockInterruptibly();
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIf(Guard guard, long j, TimeUnit timeUnit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        } else if (!enter(j, timeUnit)) {
            return false;
        } else {
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (!reentrantLock.tryLock(j, timeUnit)) {
                return false;
            }
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock reentrantLock = this.lock;
            if (!reentrantLock.tryLock()) {
                return false;
            }
            try {
                boolean isSatisfied = guard.isSatisfied();
                if (!isSatisfied) {
                }
                return isSatisfied;
            } finally {
                reentrantLock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            await(guard, true);
        }
    }

    public void waitForUninterruptibly(Guard guard) {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            awaitUninterruptibly(guard, true);
        }
    }

    public boolean waitFor(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(j);
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied() || awaitNanos(guard, nanos, true)) {
            return true;
        } else {
            return false;
        }
    }

    /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0039 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean waitForUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r6, long r7, java.util.concurrent.TimeUnit r9) {
        /*
            r5 = this;
            long r7 = r9.toNanos(r7)
            com.google.common.util.concurrent.Monitor r9 = r6.monitor
            r0 = 0
            r1 = 1
            if (r9 != r5) goto L_0x000c
            r9 = 1
            goto L_0x000d
        L_0x000c:
            r9 = 0
        L_0x000d:
            java.util.concurrent.locks.ReentrantLock r2 = r5.lock
            boolean r2 = r2.isHeldByCurrentThread()
            r9 = r9 & r2
            if (r9 == 0) goto L_0x005b
            boolean r9 = r6.isSatisfied()
            if (r9 == 0) goto L_0x001d
            return r1
        L_0x001d:
            long r2 = java.lang.System.nanoTime()
            long r2 = r2 + r7
            boolean r9 = java.lang.Thread.interrupted()
            r4 = r9
            r9 = 1
        L_0x0028:
            boolean r6 = r5.awaitNanos(r6, r7, r9)     // Catch:{ InterruptedException -> 0x0039, all -> 0x0036 }
            if (r4 == 0) goto L_0x0035
            java.lang.Thread r7 = java.lang.Thread.currentThread()
            r7.interrupt()
        L_0x0035:
            return r6
        L_0x0036:
            r6 = move-exception
            r1 = r4
            goto L_0x0051
        L_0x0039:
            boolean r7 = r6.isSatisfied()     // Catch:{ all -> 0x0050 }
            if (r7 == 0) goto L_0x0047
            java.lang.Thread r6 = java.lang.Thread.currentThread()
            r6.interrupt()
            return r1
        L_0x0047:
            long r7 = java.lang.System.nanoTime()     // Catch:{ all -> 0x0050 }
            long r7 = r2 - r7
            r9 = 0
            r4 = 1
            goto L_0x0028
        L_0x0050:
            r6 = move-exception
        L_0x0051:
            if (r1 == 0) goto L_0x005a
            java.lang.Thread r7 = java.lang.Thread.currentThread()
            r7.interrupt()
        L_0x005a:
            throw r6
        L_0x005b:
            java.lang.IllegalMonitorStateException r6 = new java.lang.IllegalMonitorStateException
            r6.<init>()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.waitForUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor == this) {
            this.lock.lock();
            try {
                return guard.waiterCount;
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            signalAllWaiters();
            Throwables.propagate(th);
            throw null;
        }
    }

    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    private void beginWaitingFor(Guard guard) {
        int i = guard.waiterCount;
        guard.waiterCount = i + 1;
        if (i == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    private void endWaitingFor(Guard guard) {
        int i = guard.waiterCount - 1;
        guard.waiterCount = i;
        if (i == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                guard3 = guard2;
                guard2 = guard2.next;
            }
            if (guard3 == null) {
                this.activeGuards = guard2.next;
            } else {
                guard3.next = guard2.next;
            }
            guard2.next = null;
        }
    }

    private void await(Guard guard, boolean z) throws InterruptedException {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private void awaitUninterruptibly(Guard guard, boolean z) {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private boolean awaitNanos(Guard guard, long j, boolean z) throws InterruptedException {
        boolean z2;
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        while (true) {
            if (j < 0) {
                z2 = false;
                break;
            }
            try {
                j = guard.condition.awaitNanos(j);
                if (guard.isSatisfied()) {
                    z2 = true;
                    break;
                }
            } catch (Throwable th) {
                endWaitingFor(guard);
                throw th;
            }
        }
        endWaitingFor(guard);
        return z2;
    }
}
