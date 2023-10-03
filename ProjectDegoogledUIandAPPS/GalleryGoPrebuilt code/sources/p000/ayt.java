package p000;

/* renamed from: ayt */
/* compiled from: PG */
public final class ayt implements axo {

    /* renamed from: a */
    private static final aqy f1872a = aqy.m1471a("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Timeout", 2500);

    /* renamed from: b */
    private final axm f1873b;

    public ayt() {
        this((axm) null);
    }

    public ayt(axm axm) {
        this.f1873b = axm;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ axn mo1697a(Object obj, int i, int i2, aqz aqz) {
        axa axa = (axa) obj;
        axm axm = this.f1873b;
        if (axm != null) {
            axl a = axl.m1853a(axa);
            Object b = axm.f1828a.mo1957b(a);
            a.mo1714a();
            axa axa2 = (axa) b;
            if (axa2 == null) {
                axm axm2 = this.f1873b;
                axm2.f1828a.mo1958b(axl.m1853a(axa), axa);
            } else {
                axa = axa2;
            }
        }
        return new axn(axa, new arr(axa, ((Integer) aqz.mo1502a(f1872a)).intValue()));
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo1698a(Object obj) {
        axa axa = (axa) obj;
        return true;
    }
}
