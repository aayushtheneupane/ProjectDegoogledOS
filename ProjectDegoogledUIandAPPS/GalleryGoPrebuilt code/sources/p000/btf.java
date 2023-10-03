package p000;

import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: btf */
/* compiled from: PG */
public final class btf implements gud {

    /* renamed from: a */
    public final Object f3526a = new Object();

    /* renamed from: b */
    private final brn f3527b;

    /* renamed from: c */
    private bta f3528c = null;

    /* renamed from: d */
    private ieh f3529d = ife.m12820a((Object) null);

    /* renamed from: e */
    private final /* synthetic */ btg f3530e;

    public /* synthetic */ btf(btg btg, brn brn) {
        this.f3530e = btg;
        this.f3527b = brn;
    }

    /* renamed from: a */
    public final ieh mo2733a() {
        ieh a;
        ieh ieh;
        hlj a2;
        ieh ieh2;
        synchronized (this.f3526a) {
            this.f3529d.cancel(true);
            bta bta = new bta((byte[]) null);
            this.f3528c = bta;
            hlj a3 = hnb.m11765a("Fetching folder data");
            try {
                bsz bsz = this.f3530e.f3531a;
                cxd cxd = this.f3527b.f3434d;
                if (cxd == null) {
                    cxd = cxd.f5884h;
                }
                a = a3.mo7548a(gte.m10770a(bsz.mo2730a(cxd), (hpr) new btd(this, bta), (Executor) idh.f13918a));
                if (a3 != null) {
                    a3.close();
                }
                bta bta2 = this.f3528c;
                if (!this.f3527b.f3435e) {
                    ieh = ife.m12820a((Object) null);
                } else {
                    a2 = hnb.m11765a("Getting promo");
                    btr btr = this.f3530e.f3532b;
                    if (btr.f3563e.mo3175a()) {
                        ieh2 = gte.m10770a(btr.f3560b.mo6359a(), (hpr) new btq(btr), (Executor) btr.f3559a);
                    } else {
                        ieh2 = ife.m12820a((Object) Optional.empty());
                    }
                    ieh a4 = a2.mo7548a(gte.m10770a(ieh2, (hpr) new bte(this, bta2), (Executor) idh.f13918a));
                    if (a2 != null) {
                        a2.close();
                    }
                    ieh = a4;
                }
                this.f3529d = ife.m12823a(a, ieh);
            } catch (Throwable th) {
                ifn.m12935a(th, th);
            }
        }
        this.f3530e.f3533c.mo7096a(gte.m10769a(a, ieh).mo7612a(btc.f3521a, (Executor) idh.f13918a), (Object) "DEVICE_FOLDERS_FRAGMENT_DATA_SERVICE_KEY");
        cwn.m5509a(a, "DeviceFoldersFragmentDataService: Failed to fetch folders.", new Object[0]);
        cwn.m5509a(ieh, "DeviceFoldersFragmentDataService: Failed to fetch promo.", new Object[0]);
        return a;
        throw th;
        throw th;
    }

    /* renamed from: c */
    public final Object mo2735c() {
        return guj.m10828a("DEVICE_FOLDERS_FRAGMENT_DATA_SERVICE_KEY", "DEVICE_FOLDERS_DATA_SERVICE_KEY", "DEVICE_FOLDERS_PROMO_DATA_SERVICE_KEY");
    }

    /* renamed from: b */
    public final gpc mo2734b() {
        synchronized (this.f3526a) {
            if (this.f3528c == null) {
                gpc a = gpc.m10579a((Object) guc.f12067a);
                return a;
            } else if (!this.f3529d.isCancelled()) {
                bta bta = this.f3528c;
                gpc a2 = gpc.m10579a((Object) guc.m10815a(new bsv(bta.f3519a, bta.f3520b), this.f3530e.f3534d.mo5370a()));
                return a2;
            } else {
                gpc a3 = gpc.m10579a((Object) guc.f12067a);
                return a3;
            }
        }
    }
}
