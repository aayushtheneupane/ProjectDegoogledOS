package p000;

import java.util.ArrayList;
import java.util.List;
import p003j$.util.Optional;

/* renamed from: dcs */
/* compiled from: PG */
public final class dcs extends iox {

    /* renamed from: b */
    private final ioq f6278b;

    /* renamed from: c */
    private final ioq f6279c;

    /* renamed from: d */
    private final ioq f6280d;

    /* renamed from: e */
    private final ioq f6281e;

    public dcs(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4) {
        super(iqk2, iph.m14288a(dcs.class), iqk);
        this.f6278b = ipd.m14274a(ioq);
        this.f6279c = ipd.m14274a(ioq2);
        this.f6280d = ipd.m14274a(ioq3);
        this.f6281e = ipd.m14274a(ioq4);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        bip bip = (bip) list.get(0);
        bip bip2 = (bip) list.get(2);
        bip bip3 = (bip) list.get(3);
        ArrayList arrayList = new ArrayList();
        arrayList.getClass();
        ((Optional) list.get(1)).ifPresent(new dbv(arrayList));
        return ife.m12820a((Object) arrayList);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6278b.mo9044b(), this.f6279c.mo9044b(), this.f6280d.mo9044b(), this.f6281e.mo9044b());
    }
}
