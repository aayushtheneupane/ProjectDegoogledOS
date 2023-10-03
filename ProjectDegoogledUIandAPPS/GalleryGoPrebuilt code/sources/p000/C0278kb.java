package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: kb */
/* compiled from: PG */
final class C0278kb implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ AtomicReference f15115a;

    /* renamed from: b */
    private final /* synthetic */ Callable f15116b;

    /* renamed from: c */
    private final /* synthetic */ ReentrantLock f15117c;

    /* renamed from: d */
    private final /* synthetic */ AtomicBoolean f15118d;

    /* renamed from: e */
    private final /* synthetic */ Condition f15119e;

    public C0278kb(AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
        this.f15115a = atomicReference;
        this.f15116b = callable;
        this.f15117c = reentrantLock;
        this.f15118d = atomicBoolean;
        this.f15119e = condition;
    }

    public final void run() {
        try {
            this.f15115a.set(((C0266jq) this.f15116b).call());
        } catch (Exception e) {
        }
        this.f15117c.lock();
        try {
            this.f15118d.set(false);
            this.f15119e.signal();
        } finally {
            this.f15117c.unlock();
        }
    }
}
