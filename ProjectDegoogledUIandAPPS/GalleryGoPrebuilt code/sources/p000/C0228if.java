package p000;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: if */
/* compiled from: PG */
abstract class C0228if {

    /* renamed from: e */
    private static Handler f13983e;

    /* renamed from: a */
    public final FutureTask f13984a = new C0226id(this, new C0225ic(this));

    /* renamed from: b */
    public final AtomicBoolean f13985b = new AtomicBoolean();

    /* renamed from: c */
    public final AtomicBoolean f13986c = new AtomicBoolean();

    /* renamed from: d */
    public volatile int f13987d = 1;

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract Object mo8187a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8188a(Object obj) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo8189b() {
    }

    /* renamed from: c */
    public final boolean mo8481c() {
        return this.f13985b.get();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final void mo8480c(Object obj) {
        Handler handler;
        synchronized (C0228if.class) {
            if (f13983e == null) {
                f13983e = new Handler(Looper.getMainLooper());
            }
            handler = f13983e;
        }
        handler.post(new C0227ie(this, obj));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo8479b(Object obj) {
        if (!this.f13986c.get()) {
            mo8480c(obj);
        }
    }
}
