package p000;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/* renamed from: baw */
/* compiled from: PG */
final class baw implements Lock {
    public final void lock() {
    }

    public final void lockInterruptibly() {
    }

    public final boolean tryLock() {
        return true;
    }

    public final boolean tryLock(long j, TimeUnit timeUnit) {
        return true;
    }

    public final void unlock() {
    }

    public final Condition newCondition() {
        throw new UnsupportedOperationException("Should not be called");
    }
}
