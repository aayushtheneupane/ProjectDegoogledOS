package p000;

import java.util.HashMap;
import java.util.Map;

/* renamed from: ftn */
/* compiled from: PG */
public final class ftn implements ioe, fti {

    /* renamed from: a */
    private volatile Object f10575a;

    /* renamed from: b */
    private final Object f10576b = new Object();

    /* renamed from: c */
    private final fto f10577c;

    /* renamed from: d */
    private final Map f10578d = new HashMap();

    public ftn(fto fto) {
        this.f10577c = fto;
    }

    /* renamed from: b */
    public final Object mo2453b() {
        if (this.f10575a == null) {
            synchronized (this.f10576b) {
                if (this.f10575a == null) {
                    fto fto = this.f10577c;
                    biu biu = new biu((byte[]) null);
                    biu.f2478a = (fty) iol.m14228a((Object) new fty(((bkn) fto).f3058a));
                    iol.m14233a((Object) biu.f2478a, fty.class);
                    if (biu.f2479b == null) {
                        biu.f2479b = new eyu();
                    }
                    if (biu.f2480c == null) {
                        biu.f2480c = new ezk();
                    }
                    if (biu.f2481d == null) {
                        biu.f2481d = new fbi();
                    }
                    this.f10575a = new bjw(biu.f2478a);
                }
            }
        }
        return this.f10575a;
    }

    /* renamed from: a */
    public final Object mo2452a(gkn gkn) {
        Object obj;
        synchronized (this.f10578d) {
            if (!this.f10578d.containsKey(gkn)) {
                this.f10578d.put(gkn, ((ftm) mo2453b()).mo2323cw().mo2135a(new ftw(gkn)).mo2134a());
            }
            obj = this.f10578d.get(gkn);
        }
        return obj;
    }
}
