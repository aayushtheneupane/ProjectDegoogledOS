package p000;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* renamed from: ieg */
/* compiled from: PG */
abstract class ieg extends AtomicReference implements Runnable {

    /* renamed from: a */
    private static final Runnable f13954a = new ief((byte[]) null);

    /* renamed from: b */
    private static final Runnable f13955b = new ief((byte[]) null);

    /* renamed from: c */
    private static final Runnable f13956c = new ief((byte[]) null);

    /* renamed from: a */
    public abstract String mo8406a();

    /* renamed from: a */
    public abstract void mo8409a(Object obj, Throwable th);

    /* renamed from: b */
    public abstract Object mo8408b();

    /* renamed from: c */
    public abstract boolean mo8410c();

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public final void mo8452e() {
        Runnable runnable = (Runnable) get();
        if ((runnable instanceof Thread) && compareAndSet(runnable, f13955b)) {
            try {
                Thread thread = (Thread) runnable;
                thread.interrupt();
                if (((Runnable) getAndSet(f13954a)) == f13956c) {
                    LockSupport.unpark(thread);
                }
            } catch (Throwable th) {
                if (((Runnable) getAndSet(f13954a)) == f13956c) {
                    LockSupport.unpark((Thread) runnable);
                }
                throw th;
            }
        }
    }

    public final void run() {
        Object obj;
        Thread currentThread = Thread.currentThread();
        if (compareAndSet((Object) null, currentThread)) {
            boolean z = !mo8410c();
            if (z) {
                try {
                    obj = mo8408b();
                } catch (Throwable th) {
                    if (!compareAndSet(currentThread, f13954a)) {
                        Runnable runnable = (Runnable) get();
                        int i = 0;
                        boolean z2 = false;
                        while (true) {
                            if (runnable != f13955b && runnable != f13956c) {
                                break;
                            }
                            i++;
                            if (i <= 1000) {
                                Thread.yield();
                            } else {
                                Runnable runnable2 = f13956c;
                                if (runnable == runnable2 || compareAndSet(f13955b, runnable2)) {
                                    z2 = Thread.interrupted() || z2;
                                    LockSupport.park(this);
                                }
                            }
                            runnable = (Runnable) get();
                        }
                        if (z2) {
                            currentThread.interrupt();
                        }
                    }
                    mo8409a((Object) null, th);
                    return;
                }
            } else {
                obj = null;
            }
            if (!compareAndSet(currentThread, f13954a)) {
                Runnable runnable3 = (Runnable) get();
                int i2 = 0;
                boolean z3 = false;
                while (true) {
                    if (runnable3 != f13955b && runnable3 != f13956c) {
                        break;
                    }
                    i2++;
                    if (i2 <= 1000) {
                        Thread.yield();
                    } else {
                        Runnable runnable4 = f13956c;
                        if (runnable3 == runnable4 || compareAndSet(f13955b, runnable4)) {
                            z3 = Thread.interrupted() || z3;
                            LockSupport.park(this);
                        }
                    }
                    runnable3 = (Runnable) get();
                }
                if (z3) {
                    currentThread.interrupt();
                }
            }
            if (z) {
                mo8409a(obj, (Throwable) null);
            }
        }
    }

    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == f13954a) {
            str = "running=[DONE]";
        } else if (runnable == f13955b) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            String name = ((Thread) runnable).getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 21);
            sb.append("running=[RUNNING ON ");
            sb.append(name);
            sb.append("]");
            str = sb.toString();
        } else {
            str = "running=[NOT STARTED YET]";
        }
        String a = mo8406a();
        StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 2 + String.valueOf(a).length());
        sb2.append(str);
        sb2.append(", ");
        sb2.append(a);
        return sb2.toString();
    }
}
