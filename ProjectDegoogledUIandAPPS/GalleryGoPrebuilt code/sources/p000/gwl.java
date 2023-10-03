package p000;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/* renamed from: gwl */
/* compiled from: PG */
public final /* synthetic */ class gwl implements hpr {

    /* renamed from: a */
    public static final hpr f12199a = new gwl();

    private gwl() {
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        hsq g = hsu.m12070g();
        for (AbstractMap.SimpleEntry simpleEntry : (List) obj) {
            if (simpleEntry.getValue() != gwq.f12205a) {
                g.mo7933a((Map.Entry) simpleEntry);
            }
        }
        return g.mo7930a();
    }
}
