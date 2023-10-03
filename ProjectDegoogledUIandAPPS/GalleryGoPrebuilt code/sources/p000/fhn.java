package p000;

import android.app.Application;
import android.os.SystemClock;

/* renamed from: fhn */
/* compiled from: PG */
abstract class fhn implements fmx {

    /* renamed from: a */
    public final Application f9685a;

    /* renamed from: b */
    public final hqk f9686b;

    /* renamed from: c */
    public volatile boolean f9687c;

    /* renamed from: d */
    private final fjv f9688d;

    protected fhn(iqk iqk, Application application, hqk hqk, hqk hqk2, int i) {
        this(iqk, application, hqk, hqk2, i, Integer.MAX_VALUE);
    }

    /* renamed from: d */
    public abstract void mo5733d();

    protected fhn(iqk iqk, Application application, hqk hqk, hqk hqk2, int i, int i2) {
        ife.m12898e((Object) iqk);
        ife.m12898e((Object) application);
        this.f9685a = application;
        this.f9686b = hqk2;
        this.f9688d = new fjv(iqk, hqk, hqk2, i, i2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final iel mo5732c() {
        return (iel) this.f9686b.mo2652a();
    }

    /* renamed from: a */
    public final void mo5727a() {
        this.f9687c = true;
        mo5733d();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5729a(String str, boolean z, isc isc, iqx iqx) {
        mo5730a(str, z, isc, iqx, (String) null);
    }

    /* renamed from: a */
    public final void mo5730a(String str, boolean z, isc isc, iqx iqx, String str2) {
        if (!this.f9687c) {
            fjv fjv = this.f9688d;
            if (fjv.f9852c != 1) {
                ((iel) fjv.f9851b.mo2652a()).mo5931a((Runnable) new fju(fjv, str, z, isc, iqx, str2));
            } else {
                fjv.mo5887a(str, z, isc, iqx, str2);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo5728a(isc isc) {
        mo5730a((String) null, true, isc, (iqx) null, (String) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final boolean mo5731b() {
        boolean z;
        fop fop = this.f9688d.f9850a;
        synchronized (fop.f10157a) {
            z = false;
            if (SystemClock.elapsedRealtime() - fop.f10160d <= 1000) {
                if (fop.f10159c >= fop.f10158b) {
                    z = true;
                }
            }
        }
        return !z;
    }
}
