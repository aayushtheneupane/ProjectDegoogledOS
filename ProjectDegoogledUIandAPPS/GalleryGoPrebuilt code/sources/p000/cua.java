package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: cua */
/* compiled from: PG */
final /* synthetic */ class cua implements cue {

    /* renamed from: a */
    private final Callable f5658a;

    public cua(Callable callable) {
        this.f5658a = callable;
    }

    /* renamed from: a */
    public final ieh mo3142a() {
        try {
            return ife.m12820a((Object) (Boolean) this.f5658a.call());
        } catch (Exception e) {
            return ife.m12822a((Throwable) e);
        }
    }

    /* renamed from: a */
    public final ieh mo3143a(ice ice, Executor executor) {
        return cun.m5445a((cue) this, ice, executor);
    }
}
