package p000;

import java.util.Map;
import java.util.concurrent.Callable;

/* renamed from: hin */
/* compiled from: PG */
final /* synthetic */ class hin implements Callable {

    /* renamed from: a */
    private final Map f12803a;

    public hin(Map map) {
        this.f12803a = map;
    }

    public final Object call() {
        return this.f12803a.keySet();
    }
}
