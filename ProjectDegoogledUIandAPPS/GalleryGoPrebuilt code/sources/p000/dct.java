package p000;

import android.content.Context;
import java.util.List;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dct */
/* compiled from: PG */
public final class dct extends iox {

    /* renamed from: b */
    private final ioq f6282b;

    /* renamed from: c */
    private final ioq f6283c;

    /* renamed from: d */
    private final ioq f6284d;

    /* renamed from: e */
    private final ioq f6285e;

    /* renamed from: f */
    private final ioq f6286f;

    public dct(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4, ioq ioq5) {
        super(iqk2, iph.m14288a(dct.class), iqk);
        this.f6282b = ipd.m14274a(ioq);
        this.f6283c = ipd.m14274a(ioq2);
        this.f6284d = ipd.m14274a(ioq3);
        this.f6285e = ipd.m14274a(ioq4);
        this.f6286f = ipd.m14274a(ioq5);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        return ((gpc) list.get(3)).mo6896a((icf) new dcd((Optional) list.get(0), (Context) list.get(2), (dbo) list.get(1)), (Executor) (iek) list.get(4));
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6282b.mo9044b(), this.f6283c.mo9044b(), this.f6284d.mo9044b(), this.f6285e.mo9044b(), this.f6286f.mo9044b());
    }
}
