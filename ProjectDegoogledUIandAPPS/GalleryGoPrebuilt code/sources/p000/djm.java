package p000;

import java.util.Map;
import p003j$.util.function.Consumer;
import p003j$.util.function.Consumer$$CC;

/* renamed from: djm */
/* compiled from: PG */
final /* synthetic */ class djm implements Consumer {

    /* renamed from: a */
    private final Map f6668a;

    public djm(Map map) {
        this.f6668a = map;
    }

    public final void accept(Object obj) {
        dik dik = (dik) obj;
        this.f6668a.put(dik.f6610b, dik);
    }

    public final Consumer andThen(Consumer consumer) {
        return Consumer$$CC.andThen$$dflt$$(this, consumer);
    }
}
