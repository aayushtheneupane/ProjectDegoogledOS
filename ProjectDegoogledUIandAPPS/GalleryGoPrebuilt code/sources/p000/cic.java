package p000;

/* renamed from: cic */
/* compiled from: PG */
public final /* synthetic */ class cic implements hpr {

    /* renamed from: a */
    public static final hpr f4451a = new cic();

    private cic() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        cig cig = (cig) obj;
        iir g = igl.f14119d.mo8793g();
        int i = cig.f4457a;
        if (!((i & 1) == 0 || (i & 2) == 0)) {
            igj igj = cig.f4458b;
            if (igj == null) {
                igj = igj.f14107d;
            }
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            igl igl = (igl) g.f14318b;
            igj.getClass();
            igl.f14122b = igj;
            igl.f14121a |= 1;
            igo igo = cig.f4459c;
            if (igo == null) {
                igo = igo.f14138b;
            }
            if (g.f14319c) {
                g.mo8751b();
                g.f14319c = false;
            }
            igl igl2 = (igl) g.f14318b;
            igo.getClass();
            igl2.f14123c = igo;
            igl2.f14121a |= 4;
        }
        return (igl) g.mo8770g();
    }
}
