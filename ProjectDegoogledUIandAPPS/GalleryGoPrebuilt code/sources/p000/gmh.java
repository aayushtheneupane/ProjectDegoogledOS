package p000;

import java.util.Collection;

/* renamed from: gmh */
/* compiled from: PG */
public final /* synthetic */ class gmh implements hpr {

    /* renamed from: a */
    private final Collection f11622a;

    public gmh(Collection collection) {
        this.f11622a = collection;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        Collection<gkn> collection = this.f11622a;
        gng gng = (gng) obj;
        iir iir = (iir) gng.mo8790b(5);
        iir.mo8503a((iix) gng);
        for (gkn a : collection) {
            int a2 = a.mo6807a();
            if (iir.f14319c) {
                iir.mo8751b();
                iir.f14319c = false;
            }
            gng gng2 = gng.f11674d;
            ((gng) iir.f14318b).mo6859a().remove(Integer.valueOf(a2));
        }
        return (gng) iir.mo8770g();
    }
}
