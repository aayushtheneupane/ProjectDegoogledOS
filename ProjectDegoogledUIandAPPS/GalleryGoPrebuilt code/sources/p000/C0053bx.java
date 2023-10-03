package p000;

import android.database.Cursor;
import android.os.Looper;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import p003j$.util.concurrent.ConcurrentHashMap;

/* renamed from: bx */
/* compiled from: PG */
public abstract class C0053bx {
    @Deprecated

    /* renamed from: a */
    public volatile C0028az f3800a;

    /* renamed from: b */
    public Executor f3801b;

    /* renamed from: c */
    public C0034be f3802c;

    /* renamed from: d */
    public final C0050bu f3803d;

    /* renamed from: e */
    public boolean f3804e;

    /* renamed from: f */
    public boolean f3805f;
    @Deprecated

    /* renamed from: g */
    public List f3806g;

    /* renamed from: h */
    private final ReentrantReadWriteLock f3807h = new ReentrantReadWriteLock();

    /* renamed from: i */
    private final ThreadLocal f3808i = new ThreadLocal();

    public C0053bx() {
        new ConcurrentHashMap();
        this.f3803d = mo1234b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C0034be mo1233a(C0046bq bqVar);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract C0050bu mo1234b();

    /* renamed from: c */
    public final void mo2843c() {
        if (!this.f3804e && Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
    }

    /* renamed from: d */
    public final void mo2844d() {
        if (!mo2848h() && this.f3808i.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
    }

    @Deprecated
    /* renamed from: e */
    public final void mo2845e() {
        mo2843c();
        C0028az a = this.f3802c.mo1892a();
        this.f3803d.mo2761a(a);
        a.mo1731a();
    }

    /* renamed from: a */
    public final C0037bh mo2841a(String str) {
        mo2843c();
        mo2844d();
        return this.f3802c.mo1892a().mo1730a(str);
    }

    @Deprecated
    /* renamed from: f */
    public final void mo2846f() {
        this.f3802c.mo1892a().mo1734b();
        if (!mo2848h()) {
            C0050bu buVar = this.f3803d;
            if (buVar.f3600b.compareAndSet(false, true)) {
                buVar.f3599a.f3801b.execute(buVar.f3604f);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final Lock mo2842a() {
        return this.f3807h.readLock();
    }

    /* renamed from: h */
    public final boolean mo2848h() {
        return this.f3802c.mo1892a().mo1737d();
    }

    /* renamed from: a */
    public final Cursor mo2840a(C0036bg bgVar) {
        mo2843c();
        mo2844d();
        return this.f3802c.mo1892a().mo1729a(bgVar);
    }

    @Deprecated
    /* renamed from: g */
    public final void mo2847g() {
        this.f3802c.mo1892a().mo1735c();
    }
}
