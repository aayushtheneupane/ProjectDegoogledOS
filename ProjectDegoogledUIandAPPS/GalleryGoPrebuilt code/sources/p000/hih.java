package p000;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: hih */
/* compiled from: PG */
final /* synthetic */ class hih implements icf {

    /* renamed from: a */
    private final hiq f12793a;

    public hih(hiq hiq) {
        this.f12793a = hiq;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        hiq hiq = this.f12793a;
        Map map = (Map) obj;
        if (map.isEmpty()) {
            return ife.m12820a((Object) Collections.emptySet());
        }
        hiz hiz = hiq.f12813e;
        ieh a = hiz.f12841c.mo5933a((Callable) new hiu(hiz, map.keySet()));
        ieh b = hiq.mo7475b(a);
        Callable a2 = hmq.m11749a((Callable) new hil(hiq, a, map));
        a2.getClass();
        ieh a3 = gqb.m10617a(b, (ice) new him(a2), (Executor) hiq.f12810b);
        goo goo = hiq.f12812d;
        map.getClass();
        return goo.mo6881a(gqb.m10618a(a3, hmq.m11749a((Callable) new hin(map)), (Executor) hiq.f12810b));
    }
}
