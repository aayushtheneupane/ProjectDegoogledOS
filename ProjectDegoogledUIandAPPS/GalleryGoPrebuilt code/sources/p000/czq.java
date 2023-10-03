package p000;

import java.util.concurrent.Callable;

/* renamed from: czq */
/* compiled from: PG */
final /* synthetic */ class czq implements icf {

    /* renamed from: a */
    private final czt f6111a;

    /* renamed from: b */
    private final iek f6112b;

    public czq(czt czt, iek iek) {
        this.f6111a = czt;
        this.f6112b = iek;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        czt czt = this.f6111a;
        iek iek = this.f6112b;
        int i = czu.f6115a;
        if (((Boolean) obj).booleanValue()) {
            return ife.m12820a((Object) bip.f2457a);
        }
        return iek.mo5933a(hmq.m11749a((Callable) new czs(czt.mo4003b())));
    }
}
