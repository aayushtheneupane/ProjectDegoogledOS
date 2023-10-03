package p000;

import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: hig */
/* compiled from: PG */
final /* synthetic */ class hig implements Callable {

    /* renamed from: a */
    private final hiq f12791a;

    /* renamed from: b */
    private final Map f12792b;

    public hig(hiq hiq, Map map) {
        this.f12791a = hiq;
        this.f12792b = map;
    }

    public final Object call() {
        hiq hiq = this.f12791a;
        Map map = this.f12792b;
        synchronized (hiq.f12817i) {
            for (hjh remove : map.keySet()) {
                hiq.f12817i.remove(remove);
            }
        }
        return null;
    }
}
