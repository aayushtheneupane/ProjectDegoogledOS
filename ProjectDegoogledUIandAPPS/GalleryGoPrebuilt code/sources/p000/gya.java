package p000;

/* renamed from: gya */
/* compiled from: PG */
final /* synthetic */ class gya implements ice {

    /* renamed from: a */
    private final String f12285a;

    /* renamed from: b */
    private final gww f12286b;

    /* renamed from: c */
    private final icf f12287c;

    public gya(String str, gww gww, icf icf) {
        this.f12285a = str;
        this.f12286b = gww;
        this.f12287c = icf;
    }

    /* renamed from: a */
    public final ieh mo2539a() {
        String str = this.f12285a;
        gww gww = this.f12286b;
        icf icf = this.f12287c;
        if (gww == gww.USER || gww == gww.UI_USER) {
            str = fqb.m9397b(str);
        }
        return icf.mo2538a(str);
    }
}
