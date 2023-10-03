package p000;

import java.util.concurrent.Executor;
import p003j$.util.function.Supplier;

/* renamed from: ctz */
/* compiled from: PG */
public final /* synthetic */ class ctz implements cue {

    /* renamed from: a */
    private final Supplier f5648a;

    /* renamed from: b */
    private final Object f5649b;

    public ctz(Supplier supplier, Object obj) {
        this.f5648a = supplier;
        this.f5649b = obj;
    }

    /* renamed from: a */
    public final ieh mo3142a() {
        return gte.m10770a((ieh) this.f5648a.get(), (hpr) new cub(this.f5649b), (Executor) idh.f13918a);
    }

    /* renamed from: a */
    public final ieh mo3143a(ice ice, Executor executor) {
        return cun.m5445a((cue) this, ice, executor);
    }
}
