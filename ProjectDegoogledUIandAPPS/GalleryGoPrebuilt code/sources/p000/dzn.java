package p000;

import java.util.Collection;
import java.util.List;

/* renamed from: dzn */
/* compiled from: PG */
final /* synthetic */ class dzn implements hpr {

    /* renamed from: a */
    private final Collection f7728a;

    public dzn(Collection collection) {
        this.f7728a = collection;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Collection collection = this.f7728a;
        dzm dzm = (dzm) obj;
        iir g = dzm.f7725b.mo8793g();
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        dzm dzm2 = (dzm) g.f14318b;
        if (!dzm2.f7727a.mo8521a()) {
            dzm2.f7727a = iix.m13608a(dzm2.f7727a);
        }
        igz.m12986a((Iterable) collection, (List) dzm2.f7727a);
        return (dzm) g.mo8770g();
    }
}
