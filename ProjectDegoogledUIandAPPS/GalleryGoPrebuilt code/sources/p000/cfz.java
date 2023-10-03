package p000;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import p003j$.util.Iterator$$Dispatch;

/* renamed from: cfz */
/* compiled from: PG */
public final class cfz extends iox {

    /* renamed from: b */
    private final ioq f4301b;

    /* renamed from: c */
    private final ioq f4302c;

    /* renamed from: d */
    private final ioq f4303d;

    public cfz(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3) {
        super(iqk2, iph.m14288a(cfz.class), iqk);
        this.f4301b = ipd.m14274a(ioq);
        this.f4302c = ipd.m14274a(ioq2);
        this.f4303d = ipd.m14274a(ioq3);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        Void voidR = (Void) list.get(0);
        cjh cjh = (cjh) list.get(1);
        Iterator it = ((Set) list.get(2)).iterator();
        if (!it.hasNext()) {
            return ife.m12820a((Object) hvb.f13454a);
        }
        hgn c = cjh.m4390c();
        c.mo7409a(" WHERE d IN(? ");
        c.mo7411b((String) it.next());
        Iterator$$Dispatch.forEachRemaining(it, new cja(c));
        c.mo7409a(" ) ");
        return cjh.f4490a.mo2655a(c.mo7407a(), cjb.f4484a).mo6899b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f4301b.mo9044b(), this.f4302c.mo9044b(), this.f4303d.mo9044b());
    }
}
