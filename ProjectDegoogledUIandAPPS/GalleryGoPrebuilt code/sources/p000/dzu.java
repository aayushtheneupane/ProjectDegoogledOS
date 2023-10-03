package p000;

/* renamed from: dzu */
/* compiled from: PG */
final class dzu implements hol {

    /* renamed from: a */
    private final /* synthetic */ dzt f7750a;

    public dzu(dzt dzt) {
        this.f7750a = dzt;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        brx brx = (brx) hoi;
        dzt dzt = this.f7750a;
        iir g = cxd.f5884h.mo8793g();
        String str = brx.f3465a.f5896b;
        if (g.f14319c) {
            g.mo8751b();
            g.f14319c = false;
        }
        cxd cxd = (cxd) g.f14318b;
        str.getClass();
        cxd.f5887b = 1;
        cxd.f5888c = str;
        dzr dzr = dzt.f7744a;
        int i = 2;
        if ((dzr.f7738a & 2) != 0) {
            String str2 = dzr.f7740c;
            str2.getClass();
            cxd.f5886a |= 32;
            cxd.f5890e = str2;
        }
        if (!dzr.f7739b) {
            i = 3;
        }
        dzt.f7746c.mo3272a(new dzs(brx, g, i), "picker_single_device_folder", cnf.SLIDE_UP);
        return hom.f13155a;
    }
}
