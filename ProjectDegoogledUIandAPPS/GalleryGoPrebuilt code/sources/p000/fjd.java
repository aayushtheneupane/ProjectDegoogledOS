package p000;

import android.app.Activity;
import android.app.Application;
import java.util.HashMap;
import java.util.Map;

/* renamed from: fjd */
/* compiled from: PG */
final class fjd extends fhn implements fhz, fmc {

    /* renamed from: d */
    public final fjb f9789d;

    /* renamed from: e */
    public final Map f9790e = new HashMap();

    /* renamed from: f */
    public final boolean f9791f;

    /* renamed from: g */
    public final int f9792g;

    /* renamed from: h */
    public final fog f9793h;

    /* renamed from: i */
    public final iqk f9794i;

    /* renamed from: j */
    private final fid f9795j;

    public fjd(iqk iqk, Application application, hqk hqk, hqk hqk2, boolean z, int i, fog fog, iqk iqk2) {
        super(iqk, application, hqk, hqk2, 2, i);
        this.f9795j = fid.m8938a(application);
        this.f9791f = z;
        this.f9793h = (fog) ife.m12898e((Object) fog);
        this.f9794i = iqk2;
        if (foj.f10148a == 0) {
            synchronized (foj.class) {
                if (foj.f10148a == 0) {
                    int a = foj.m9310a(application);
                    double d = (double) ((a < 10 || a > 60) ? 60 : a);
                    Double.isNaN(d);
                    foj.f10148a = (int) Math.ceil(1000.0d / d);
                }
            }
        }
        this.f9792g = foj.f10148a;
        fjb fjb = new fjb(new fja(this), z);
        this.f9789d = fjb;
        this.f9795j.mo5747a((fic) fjb);
    }

    /* renamed from: e */
    public final void mo5833e() {
    }

    /* renamed from: f */
    public final void mo5834f() {
    }

    /* renamed from: b */
    public final void mo5742b(Activity activity) {
        synchronized (this.f9790e) {
            this.f9790e.clear();
        }
    }

    /* renamed from: d */
    public final void mo5733d() {
        this.f9795j.mo5748b(this.f9789d);
        fjb fjb = this.f9789d;
        synchronized (fjb) {
            fjb.mo5869a();
            if (fjb.f9784b != null) {
                fjb.f9783a.quitSafely();
                fjb.f9783a = null;
                fjb.f9784b = null;
            }
        }
        synchronized (this.f9790e) {
            this.f9790e.clear();
        }
    }
}
