package p000;

import android.graphics.drawable.Drawable;
import android.support.p002v7.widget.RecyclerView;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: bed */
/* compiled from: PG */
public final class bed implements bdz, bee {

    /* renamed from: a */
    private Object f2139a;

    /* renamed from: b */
    private bea f2140b;

    /* renamed from: c */
    private boolean f2141c;

    /* renamed from: d */
    private boolean f2142d;

    /* renamed from: e */
    private boolean f2143e;

    /* renamed from: f */
    private atu f2144f;

    /* renamed from: a */
    public final synchronized void mo1432a(Drawable drawable) {
    }

    /* renamed from: a */
    public final synchronized void mo1433a(Object obj, bex bex) {
    }

    /* renamed from: ae */
    public final synchronized bea mo1896ae() {
        return this.f2140b;
    }

    /* renamed from: b */
    public final void mo1442b() {
    }

    /* renamed from: b */
    public final void mo1798b(Drawable drawable) {
    }

    /* renamed from: b */
    public final void mo1897b(beq beq) {
    }

    /* renamed from: c */
    public final void mo1443c() {
    }

    /* renamed from: c */
    public final void mo1898c(Drawable drawable) {
    }

    /* renamed from: d */
    public final void mo1444d() {
    }

    public final synchronized boolean isCancelled() {
        return this.f2141c;
    }

    public final synchronized boolean isDone() {
        return this.f2141c || this.f2142d || this.f2143e;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0019, code lost:
        if (r1 == null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r1.mo1880b();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean cancel(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.isDone()     // Catch:{ all -> 0x0022 }
            if (r0 != 0) goto L_0x001f
            r0 = 1
            r2.f2141c = r0     // Catch:{ all -> 0x0022 }
            r2.notifyAll()     // Catch:{ all -> 0x0022 }
            r1 = 0
            if (r3 == 0) goto L_0x0016
            bea r3 = r2.f2140b     // Catch:{ all -> 0x0022 }
            r2.f2140b = r1     // Catch:{ all -> 0x0022 }
            r1 = r3
            goto L_0x0018
        L_0x0016:
        L_0x0018:
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            if (r1 == 0) goto L_0x001e
            r1.mo1880b()
        L_0x001e:
            return r0
        L_0x001f:
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            r3 = 0
            return r3
        L_0x0022:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0022 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.bed.cancel(boolean):boolean");
    }

    /* renamed from: a */
    private final synchronized Object m2286a(Long l) {
        if (!isDone()) {
            bfp.m2435b();
        }
        if (this.f2141c) {
            throw new CancellationException();
        } else if (this.f2143e) {
            throw new ExecutionException(this.f2144f);
        } else if (!this.f2142d) {
            if (l == null) {
                wait(0);
            } else if (l.longValue() > 0) {
                long currentTimeMillis = System.currentTimeMillis();
                long longValue = l.longValue() + currentTimeMillis;
                while (!isDone() && currentTimeMillis < longValue) {
                    wait(longValue - currentTimeMillis);
                    currentTimeMillis = System.currentTimeMillis();
                }
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            } else if (this.f2143e) {
                throw new ExecutionException(this.f2144f);
            } else if (this.f2141c) {
                throw new CancellationException();
            } else if (this.f2142d) {
                return this.f2139a;
            } else {
                throw new TimeoutException();
            }
        } else {
            return this.f2139a;
        }
    }

    public final Object get() {
        try {
            return m2286a((Long) null);
        } catch (TimeoutException e) {
            throw new AssertionError(e);
        }
    }

    public final Object get(long j, TimeUnit timeUnit) {
        return m2286a(Long.valueOf(timeUnit.toMillis(j)));
    }

    /* renamed from: a */
    public final void mo1895a(beq beq) {
        beq.mo1906a(RecyclerView.UNDEFINED_DURATION, RecyclerView.UNDEFINED_DURATION);
    }

    /* renamed from: a */
    public final synchronized boolean mo1486a(atu atu) {
        this.f2143e = true;
        this.f2144f = atu;
        notifyAll();
        return false;
    }

    /* renamed from: a */
    public final synchronized boolean mo1487a(Object obj) {
        this.f2142d = true;
        this.f2139a = obj;
        notifyAll();
        return false;
    }

    /* renamed from: a */
    public final synchronized void mo1894a(bea bea) {
        this.f2140b = bea;
    }
}
