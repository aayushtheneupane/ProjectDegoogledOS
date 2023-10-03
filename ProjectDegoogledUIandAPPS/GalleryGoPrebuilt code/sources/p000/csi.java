package p000;

/* renamed from: csi */
/* compiled from: PG */
public final class csi {

    /* renamed from: a */
    public static final cup f5560a = cup.IMAGE_LABELING_JOB;

    /* renamed from: b */
    public final cso f5561b;

    /* renamed from: c */
    public final crz f5562c;

    /* renamed from: d */
    public final cnr f5563d;

    /* renamed from: e */
    public final cny f5564e;

    /* renamed from: f */
    public final iel f5565f;

    /* renamed from: g */
    public final cvm f5566g;

    /* renamed from: h */
    public final cwq f5567h;

    /* renamed from: i */
    public final ble f5568i;

    /* renamed from: j */
    public final cui f5569j;

    /* renamed from: k */
    public final cus f5570k;

    /* renamed from: l */
    public final cjr f5571l;

    /* renamed from: m */
    private final csp f5572m;

    public csi(cso cso, crz crz, cnr cnr, csp csp, cny cny, iel iel, cvm cvm, cwq cwq, ble ble, cui cui, cus cus, cjr cjr) {
        this.f5561b = cso;
        this.f5562c = crz;
        this.f5563d = cnr;
        this.f5572m = csp;
        this.f5564e = cny;
        this.f5565f = iel;
        this.f5566g = cvm;
        this.f5567h = cwq;
        this.f5568i = ble;
        this.f5569j = cui;
        this.f5570k = cus;
        this.f5571l = cjr;
    }

    /* renamed from: a */
    private static boolean m5347a(boolean[] zArr, boolean[] zArr2, csb csb) {
        return !zArr[csb.ordinal()] && zArr2[csb.ordinal()];
    }

    /* renamed from: a */
    public final ieh mo3800a(csl csl, cyg cyg) {
        String str;
        int i;
        boolean[] zArr = new boolean[csb.values().length];
        boolean[] zArr2 = new boolean[csb.values().length];
        ije ije = csl.f5581a;
        int size = ije.size();
        int i2 = 0;
        while (i2 < size) {
            csk csk = (csk) ije.get(i2);
            try {
                str = igd.m12963a(csk.f5578c);
            } catch (IllegalArgumentException e) {
                str = "";
            }
            csb a = this.f5572m.mo3803a(str, csk.f5577b);
            if (a != null) {
                zArr[a.ordinal()] = true;
            }
            hvs i3 = this.f5572m.mo3804b(str, csk.f5577b).listIterator();
            while (true) {
                i = i2 + 1;
                if (!i3.hasNext()) {
                    break;
                }
                zArr2[((csb) i3.next()).ordinal()] = true;
            }
            i2 = i;
        }
        cyf a2 = this.f5570k.mo3838a(cyg.mo3906M(), f5560a, true);
        a2.mo3970c(m5347a(zArr2, zArr, csb.ANIMAL));
        a2.mo3974d(m5347a(zArr2, zArr, csb.DOCUMENT));
        a2.mo3985g(m5347a(zArr2, zArr, csb.NATURE));
        a2.mo3982f(m5347a(zArr2, zArr, csb.FOOD));
        a2.mo3987h(m5347a(zArr2, zArr, csb.SCREENSHOT));
        a2.mo3988i(m5347a(zArr2, zArr, csb.SELFIE));
        a2.mo3964b(csl.mo8512ag());
        a2.mo3983g(1);
        return this.f5561b.mo3802a(a2.mo3966c());
    }
}
