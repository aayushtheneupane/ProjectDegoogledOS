package p000;

import java.util.List;
import java.util.concurrent.Executor;
import p003j$.util.Optional;

/* renamed from: dcv */
/* compiled from: PG */
public final class dcv extends iox {

    /* renamed from: b */
    private final ioq f6292b;

    /* renamed from: c */
    private final ioq f6293c;

    /* renamed from: d */
    private final ioq f6294d;

    /* renamed from: e */
    private final ioq f6295e;

    public dcv(iqk iqk, iqk iqk2, ioq ioq, ioq ioq2, ioq ioq3, ioq ioq4) {
        super(iqk2, iph.m14288a(dcv.class), iqk);
        this.f6292b = ipd.m14274a(ioq);
        this.f6293c = ipd.m14274a(ioq2);
        this.f6294d = ipd.m14274a(ioq3);
        this.f6295e = ipd.m14274a(ioq4);
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ ieh mo3132b(Object obj) {
        List list = (List) obj;
        Optional optional = (Optional) list.get(0);
        Optional optional2 = (Optional) list.get(1);
        cip cip = (cip) list.get(2);
        iek iek = (iek) list.get(3);
        if (!optional2.isPresent() || !optional.isPresent() || !((cyg) optional2.get()).mo3907a().isPresent() || !((cyg) optional.get()).mo3907a().isPresent()) {
            return ife.m12820a((Object) false);
        }
        long longValue = ((Long) ((cyg) optional2.get()).mo3907a().get()).longValue();
        long longValue2 = ((Long) ((cyg) optional.get()).mo3907a().get()).longValue();
        bpt bpt = cip.f4469a;
        hgn a = cip.m4364a();
        a.mo7409a(" WHERE c = ?");
        a.mo7411b(String.valueOf(longValue2));
        return gte.m10771a(bpt.mo2655a(a.mo7407a(), cim.f4466a).mo6899b(), (icf) new dbw(longValue, cip, iek), (Executor) iek);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final ieh mo3131a() {
        return ife.m12823a(this.f6292b.mo9044b(), this.f6293c.mo9044b(), this.f6294d.mo9044b(), this.f6295e.mo9044b());
    }
}
