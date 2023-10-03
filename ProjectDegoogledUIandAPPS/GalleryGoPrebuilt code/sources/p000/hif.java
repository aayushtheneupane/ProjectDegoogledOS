package p000;

import java.util.Map;

/* renamed from: hif */
/* compiled from: PG */
final /* synthetic */ class hif implements icf {

    /* renamed from: a */
    private final hiq f12789a;

    /* renamed from: b */
    private final Map f12790b;

    public hif(hiq hiq, Map map) {
        this.f12789a = hiq;
        this.f12790b = map;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        hiq hiq = this.f12789a;
        Map map = this.f12790b;
        Void voidR = (Void) obj;
        hiz hiz = hiq.f12813e;
        return hiz.f12841c.mo5931a((Runnable) new hiw(hiz, map.keySet()));
    }
}
