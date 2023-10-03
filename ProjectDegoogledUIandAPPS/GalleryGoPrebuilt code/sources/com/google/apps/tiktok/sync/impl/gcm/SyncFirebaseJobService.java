package com.google.apps.tiktok.sync.impl.gcm;

import java.util.HashMap;
import java.util.concurrent.Executor;

/* compiled from: PG */
public final class SyncFirebaseJobService extends bib {

    /* renamed from: d */
    public static final hvy f5310d = hvy.m12261a("com/google/apps/tiktok/sync/impl/gcm/SyncFirebaseJobService");

    /* renamed from: e */
    public iqk f5311e;

    /* renamed from: f */
    private hlz f5312f;

    /* renamed from: g */
    private iek f5313g;

    /* renamed from: h */
    private final HashMap f5314h = new HashMap();

    public final void onCreate() {
        super.onCreate();
        hjs hjs = (hjs) hgh.m11442a(getApplicationContext(), hjs.class);
        this.f5312f = hjs.mo2294cJ();
        this.f5311e = hjs.mo2295cK();
        this.f5313g = hjs.mo2296cL();
    }

    /* renamed from: a */
    public final void mo2067a(bhx bhx) {
        hlp a = this.f5312f.mo7577a("SyncFirebaseRootTrace");
        try {
            hlj a2 = hnb.m11765a("SyncFirebaseJob");
            try {
                ieh a3 = a2.mo7548a(ife.m12816a(hmq.m11743a((ice) new hjq(this)), (Executor) this.f5313g));
                synchronized (this.f5314h) {
                    this.f5314h.put(((bhw) bhx).f2412a, a3);
                }
                ife.m12841a(a3, (idw) new hjr(this, bhx), (Executor) this.f5313g);
                if (a2 != null) {
                    a2.close();
                }
                if (a != null) {
                    a.close();
                }
            } catch (Throwable th) {
                if (a2 != null) {
                    a2.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (a != null) {
                try {
                    a.close();
                } catch (Throwable th3) {
                    ifn.m12935a(th2, th3);
                }
            }
            throw th2;
        }
    }

    /* renamed from: b */
    public final void mo2069b(bhx bhx) {
        ieh ieh;
        synchronized (this.f5314h) {
            ieh = (ieh) this.f5314h.get(((bhw) bhx).f2412a);
        }
        if (ieh != null) {
            ieh.cancel(true);
        }
    }

    /* renamed from: a */
    public final void mo3721a(String str) {
        synchronized (this.f5314h) {
            this.f5314h.remove(str);
        }
    }
}
