package p000;

import android.app.Application;
import android.os.Build;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: fks */
/* compiled from: PG */
final class fks implements fkm {

    /* renamed from: e */
    private static final AtomicInteger f9917e = new AtomicInteger();

    /* renamed from: a */
    public final Application f9918a;

    /* renamed from: b */
    public final hqk f9919b;

    /* renamed from: c */
    public final AtomicReference f9920c = new AtomicReference();

    /* renamed from: d */
    public final CountDownLatch f9921d = new CountDownLatch(1);

    /* renamed from: f */
    private final AtomicBoolean f9922f = new AtomicBoolean();

    public fks(Application application, hqk hqk, boolean z) {
        int i = Build.VERSION.SDK_INT;
        ife.m12896d(true);
        this.f9918a = (Application) ife.m12898e((Object) application);
        this.f9919b = (hqk) ife.m12898e((Object) hqk);
        f9917e.incrementAndGet();
        this.f9920c.set(new fkk(z));
    }

    /* renamed from: e */
    private final fkm m9096e() {
        return (fkm) this.f9920c.get();
    }

    /* renamed from: d */
    static void m9095d() {
        int i = Build.VERSION.SDK_INT;
    }

    /* renamed from: a */
    public static Runnable m9094a(Runnable runnable) {
        return new fko(runnable);
    }

    /* renamed from: a */
    public final void mo5837a(fnj fnj, String str, long j, long j2, iqx iqx) {
        m9096e().mo5837a(fnj, str, j, j2, iqx);
    }

    /* renamed from: a */
    public final void mo5838a(String str) {
        m9096e().mo5838a(str);
    }

    /* renamed from: a */
    public final void mo5836a(fnb fnb, iri iri) {
        m9096e().mo5836a(fnb, iri);
    }

    /* renamed from: a */
    public final void mo5835a() {
        ((fkm) this.f9920c.getAndSet(new fka())).mo5835a();
        try {
            Application application = this.f9918a;
            synchronized (fid.class) {
                if (fid.f9697a != null) {
                    fif fif = fid.f9697a.f9698b;
                    application.unregisterActivityLifecycleCallbacks(fif.f9709a);
                    application.unregisterComponentCallbacks(fif.f9709a);
                    fid.f9697a = null;
                }
            }
        } catch (RuntimeException e) {
            flw.m9202d("Primes", "Failed to shutdown app lifecycle monitor", new Object[0]);
        }
    }

    /* renamed from: c */
    public final void mo5841c() {
        if (!this.f9922f.getAndSet(true)) {
            m9096e().mo5841c();
        }
    }

    /* renamed from: b */
    public final void mo5839b() {
        m9096e().mo5839b();
    }
}
