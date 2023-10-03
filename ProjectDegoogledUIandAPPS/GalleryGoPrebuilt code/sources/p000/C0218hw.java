package p000;

import android.os.SystemClock;

/* renamed from: hw */
/* compiled from: PG */
public final class C0218hw extends C0228if implements Runnable {

    /* renamed from: e */
    private final /* synthetic */ C0219hx f13495e;

    public C0218hw(C0219hx hxVar) {
        this.f13495e = hxVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo8187a() {
        try {
            return this.f13495e.mo6171b();
        } catch (C0263jn e) {
            if (mo8481c()) {
                return null;
            }
            throw e;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo8189b() {
        this.f13495e.mo8241a(this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo8188a(Object obj) {
        C0219hx hxVar = this.f13495e;
        if (hxVar.f13568a != this) {
            hxVar.mo8241a(this);
        } else if (!hxVar.f13820g) {
            SystemClock.uptimeMillis();
            hxVar.f13568a = null;
            hxVar.mo6169a(obj);
        }
    }

    public final void run() {
        this.f13495e.mo8240a();
    }
}
