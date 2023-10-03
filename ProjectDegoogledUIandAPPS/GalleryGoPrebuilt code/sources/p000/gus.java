package p000;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

/* renamed from: gus */
/* compiled from: PG */
public final class gus {

    /* renamed from: a */
    public final Object f12089a;

    /* renamed from: b */
    public final Map f12090b;

    /* renamed from: c */
    private final huo f12091c;

    gus() {
    }

    public gus(byte[] bArr) {
        this.f12089a = new Object();
        this.f12090b = new C0290kn();
        this.f12091c = new hsc();
    }

    /* renamed from: a */
    private final boolean m10842a(guv guv) {
        return this.f12091c.mo7770a(guv, 1) == 0;
    }

    /* renamed from: a */
    public final void mo7097a(ieh ieh, Object obj, guw guw, hpy hpy, Executor executor) {
        List list = hnb.f13076a;
        ife.m12841a(ieh, hmq.m11746a((idw) new gut(this, obj, guw, hpy)), executor);
    }

    /* renamed from: a */
    public final void mo7096a(ieh ieh, Object obj) {
        mo7097a(ieh, obj, guw.f12099a, hph.f13219a, idh.f13918a);
    }

    /* renamed from: b */
    public final void mo7099b(ieh ieh, Object obj) {
        mo7097a(ieh, obj, guw.f12100b, hph.f13219a, idh.f13918a);
    }

    /* renamed from: c */
    private final void m10844c(Object obj, gux gux) {
        hth k = htk.m12102k();
        htk htk = (htk) this.f12090b.get(obj);
        if (htk != null) {
            k.mo7984b((Iterable) htk);
        }
        k.mo7874b(gux);
        this.f12090b.put(obj, k.mo7981a());
    }

    /* renamed from: b */
    private final boolean m10843b(guv guv) {
        int b = this.f12091c.mo7772b(guv, 1);
        ife.m12896d(b > 0);
        return b == 1;
    }

    /* renamed from: d */
    private final void m10845d(Object obj, gux gux) {
        htk htk = (htk) this.f12090b.get(obj);
        ife.m12876b(htk != null, (Object) "Failed to remove a subscription key. State is corrupted.");
        Map map = this.f12090b;
        hth k = htk.m12102k();
        k.mo7984b((Iterable) htk);
        int a = htk.mo7769a(gux) - 1;
        if (a == 0 && !k.f13379c) {
            k.f13377a = new huu(k.f13377a);
            k.f13379c = true;
        } else if (k.f13378b) {
            k.f13377a = new hut(k.f13377a);
            k.f13379c = false;
        }
        k.f13378b = false;
        ife.m12898e((Object) gux);
        if (a != 0) {
            k.f13377a.mo8108b(ife.m12898e((Object) gux), a);
        } else {
            k.f13377a.mo8103a((Object) gux, ife.m12863b((Object) gux));
        }
        map.put(obj, k.mo7981a());
        if (((htk) this.f12090b.get(obj)).isEmpty()) {
            this.f12090b.remove(obj);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo7098a(Object obj, gux gux) {
        fxk.m9830b();
        ife.m12869b(obj, (Object) "Cannot subscribe with a null key");
        C0292kp kpVar = new C0292kp();
        synchronized (this.f12089a) {
            if (!(obj instanceof guq)) {
                m10844c(obj, gux);
                if (obj instanceof guv) {
                    guv guv = (guv) obj;
                    if (m10842a(guv)) {
                        kpVar.add(guv);
                    }
                }
            } else {
                for (Object next : ((guq) obj).f12082a) {
                    m10844c(next, gux);
                    if (next instanceof guv) {
                        guv guv2 = (guv) next;
                        if (m10842a(guv2)) {
                            kpVar.add(guv2);
                        }
                    }
                }
            }
        }
        Iterator it = kpVar.iterator();
        while (it.hasNext()) {
            ((guv) it.next()).mo7101a();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo7100b(Object obj, gux gux) {
        fxk.m9830b();
        ife.m12869b(obj, (Object) "Cannot unsubscribe from a null key");
        C0292kp kpVar = new C0292kp();
        synchronized (this.f12089a) {
            if (!(obj instanceof guq)) {
                m10845d(obj, gux);
                if (obj instanceof guv) {
                    guv guv = (guv) obj;
                    if (m10843b(guv)) {
                        kpVar.add(guv);
                    }
                }
            } else {
                for (Object next : ((guq) obj).f12082a) {
                    m10845d(next, gux);
                    if (next instanceof guv) {
                        guv guv2 = (guv) next;
                        if (m10843b(guv2)) {
                            kpVar.add(guv2);
                        }
                    }
                }
            }
        }
        Iterator it = kpVar.iterator();
        while (it.hasNext()) {
            ((guv) it.next()).mo7102b();
        }
    }
}
