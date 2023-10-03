package p000;

import java.util.List;

/* renamed from: drz */
/* compiled from: PG */
public final class drz implements hol {

    /* renamed from: a */
    private final /* synthetic */ dry f7261a;

    public drz(dry dry) {
        this.f7261a = dry;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        drr drr = (drr) hoi;
        dry dry = this.f7261a;
        int b = drr.f7246a.mo4373b();
        int i = dry.f7260f;
        if (b == i) {
            return hom.f13155a;
        }
        List list = dry.f7258d;
        drp e = ((drq) list.get(i)).mo4376e();
        e.mo4381a(false);
        list.set(i, e.mo4380a());
        dry.f7257c.mo10538c(dry.f7260f);
        int b2 = drr.f7246a.mo4373b();
        dry.f7260f = b2;
        dry.f7255a.mo10470d(b2);
        List list2 = dry.f7258d;
        int i2 = dry.f7260f;
        drp e2 = ((drq) list2.get(i2)).mo4376e();
        e2.mo4381a(true);
        list2.set(i2, e2.mo4380a());
        dry.f7257c.mo10538c(dry.f7260f);
        return hom.f13156b;
    }
}
