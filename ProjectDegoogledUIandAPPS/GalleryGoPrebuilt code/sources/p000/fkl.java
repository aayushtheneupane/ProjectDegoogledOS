package p000;

import android.util.Log;

/* renamed from: fkl */
/* compiled from: PG */
public final class fkl {

    /* renamed from: a */
    public static volatile fna f9892a = null;

    /* renamed from: c */
    private static final fkl f9893c = new fkl(new fka());

    /* renamed from: d */
    private static volatile boolean f9894d = true;

    /* renamed from: e */
    private static volatile fkl f9895e = f9893c;

    /* renamed from: b */
    public final fkm f9896b;

    private fkl(fkm fkm) {
        this.f9896b = (fkm) ife.m12898e((Object) fkm);
    }

    /* renamed from: a */
    public static fkl m9082a() {
        if (f9895e == f9893c && f9894d) {
            f9894d = false;
            Log.w("Primes", flw.m9188a("Primes not initialized, returning default (no-op) Primes instance which will ignore all calls. Please call Primes.initialize(...) before using any Primes API.", new Object[0]));
        }
        return f9895e;
    }

    /* renamed from: a */
    public static synchronized fkl m9083a(iqk iqk) {
        fkl fkl;
        synchronized (fkl.class) {
            if (f9895e == f9893c) {
                f9895e = new fkl((fkm) iqk.mo2097a());
            } else {
                flw.m9199b("Primes", "Primes.initialize() is called more than once. This call will be ignored.", new Object[0]);
            }
            fkl = f9895e;
        }
        return fkl;
    }
}
