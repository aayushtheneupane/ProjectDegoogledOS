package p000;

import java.util.concurrent.Executor;

/* renamed from: cru */
/* compiled from: PG */
public final class cru implements gud {

    /* renamed from: a */
    public final Object f5513a = new Object();

    /* renamed from: b */
    public final /* synthetic */ crv f5514b;

    /* renamed from: c */
    private ieh f5515c = ife.m12820a((Object) null);

    /* renamed from: d */
    private crp f5516d = null;

    public /* synthetic */ cru(crv crv) {
        this.f5514b = crv;
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        ieh a;
        hlj a2;
        ieh a3;
        hlj a4;
        ieh a5;
        synchronized (this.f5513a) {
            this.f5515c.cancel(true);
            crp crp = new crp((byte[]) null);
            crp.mo3790b(false);
            crp.mo3789a(false);
            this.f5516d = crp;
            hlj a6 = hnb.m11765a("Get media for home fragment");
            try {
                cxn cxn = this.f5514b.f5518b.f5944f;
                iir g = cxd.f5884h.mo8793g();
                if (g.f14319c) {
                    g.mo8751b();
                    g.f14319c = false;
                }
                cxd cxd = (cxd) g.f14318b;
                cxd.f5892g = 1;
                cxd.f5886a |= 128;
                a = a6.mo7548a(gte.m10770a(((grf) cxn.mo9237a((Object) (cxd) g.mo8770g())).mo6948a(), (hpr) new crr(this, crp), (Executor) idh.f13918a));
                if (a6 != null) {
                    a6.close();
                }
                a2 = hnb.m11765a("Get user preferences for home fragment");
                a3 = a2.mo7548a(gte.m10770a(this.f5514b.f5519c.f8300a.mo6359a(), (hpr) new crs(this), (Executor) idh.f13918a));
                if (a2 != null) {
                    a2.close();
                }
                crp crp2 = this.f5516d;
                a4 = hnb.m11765a("Get onboarding data for home fragment");
                dka dka = this.f5514b.f5520d;
                a5 = a4.mo7548a(gte.m10770a(gte.m10770a(dka.f6702a.mo4812a(), (hpr) new djz(dka), (Executor) idh.f13918a), (hpr) new crt(this, crp2), (Executor) idh.f13918a));
                if (a4 != null) {
                    a4.close();
                }
                this.f5515c = ife.m12823a(a, a3, a5);
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        }
        this.f5514b.f5517a.mo7096a(a, (Object) "HOME_FRAGMENT_DATA_SERVICE");
        this.f5514b.f5517a.mo7096a(a3, (Object) "HOME_FRAGMENT_DATA_SERVICE");
        this.f5514b.f5517a.mo7096a(a5, (Object) "HOME_FRAGMENT_DATA_SERVICE");
        cwn.m5509a(a, "HomeFragmentDataService: Failed to fetch media list.", new Object[0]);
        cwn.m5509a(a3, "HomeFragmentDataService: Failed to fetch user preferences.", new Object[0]);
        cwn.m5509a(a5, "HomeFragmentDataService: Failed to fetch onboarding data", new Object[0]);
        return ife.m12820a((Object) null);
        throw th;
        throw th;
        throw th;
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return guj.m10828a("HOME_FRAGMENT_DATA_SERVICE", "MEDIA_DATA_SERVICE_KEY", "ONBOARDING_DATA_SERVICE");
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        synchronized (this.f5513a) {
            if (this.f5516d == null) {
                gpc a = gpc.m10579a((Object) guc.f12067a);
                return a;
            } else if (!this.f5515c.isCancelled()) {
                crp crp = this.f5516d;
                crv crv = this.f5514b;
                int i = crv.f5521e.f5526c;
                if (i != 0) {
                    crp.f5507e = i;
                    int i2 = crv.f5522f.f5528b;
                    if (i2 != 0) {
                        crp.f5506d = i2;
                        String str = "";
                        if (crp.f5504b == null) {
                            str = str.concat(" photoGridHasContent");
                        }
                        if (crp.f5505c == null) {
                            str = String.valueOf(str).concat(" folderGridHasContent");
                        }
                        if (crp.f5507e == 0) {
                            str = String.valueOf(str).concat(" tab");
                        }
                        if (str.isEmpty()) {
                            gpc a2 = gpc.m10579a((Object) guc.m10815a(new cro(crp.f5503a, crp.f5506d, crp.f5504b.booleanValue(), crp.f5505c.booleanValue(), crp.f5507e), this.f5514b.f5523g.mo5370a()));
                            return a2;
                        }
                        String valueOf = String.valueOf(str);
                        throw new IllegalStateException(valueOf.length() == 0 ? new String("Missing required properties:") : "Missing required properties:".concat(valueOf));
                    }
                    throw new NullPointerException("Null permissionState");
                }
                throw new NullPointerException("Null tab");
            } else {
                gpc a3 = gpc.m10579a((Object) guc.f12067a);
                return a3;
            }
        }
    }
}
