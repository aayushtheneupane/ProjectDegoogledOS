package p000;

import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: hil */
/* compiled from: PG */
final /* synthetic */ class hil implements Callable {

    /* renamed from: a */
    private final hiq f12799a;

    /* renamed from: b */
    private final ieh f12800b;

    /* renamed from: c */
    private final Map f12801c;

    public hil(hiq hiq, ieh ieh, Map map) {
        this.f12799a = hiq;
        this.f12800b = ieh;
        this.f12801c = map;
    }

    public final Object call() {
        return this.f12799a.mo7468a(this.f12800b, this.f12801c);
    }
}
