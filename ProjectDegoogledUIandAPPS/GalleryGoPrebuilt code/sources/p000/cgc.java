package p000;

import java.util.List;

/* renamed from: cgc */
/* compiled from: PG */
public final class cgc extends iox {

    /* renamed from: b */
    private final ioq f4314b;

    /* renamed from: c */
    private final ioq f4315c;

    /* renamed from: d */
    private final ioq f4316d;

    /* renamed from: e */
    private final ioq f4317e;

    public cgc(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4) {
        super(iqk2, iph.m14288a(cgc.class), iqk);
        this.f4314b = ipd.m14274a(ioq);
        this.f4315c = ipd.m14274a(ioq2);
        this.f4316d = ipd.m14274a(ioq3);
        this.f4317e = ipd.m14274a(ioq4);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        Void voidR = (Void) list.get(0);
        cjh cjh = (cjh) list.get(1);
        hsu hsu = (hsu) list.get(3);
        hsj hsj = new hsj();
        hvr a = ((hsu) list.get(2)).values().iterator();
        while (a.hasNext()) {
            cia cia = (cia) a.next();
            if (cia.mo3108b() != ((Long) ((cff) ife.m12898e((Object) (cff) hsu.get(cia.mo3109c()))).mo3091a().get()).longValue()) {
                chz g = cia.mo3114g();
                g.mo3151b(false);
                hsj.mo7908c(g.mo3146a());
            }
        }
        return cjh.mo3170a(hsj.mo7905a());
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f4314b.mo9044b(), this.f4315c.mo9044b(), this.f4316d.mo9044b(), this.f4317e.mo9044b());
    }
}
