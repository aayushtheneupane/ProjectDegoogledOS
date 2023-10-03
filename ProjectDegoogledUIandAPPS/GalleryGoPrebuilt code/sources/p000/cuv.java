package p000;

import java.util.concurrent.Callable;

/* renamed from: cuv */
/* compiled from: PG */
final /* synthetic */ class cuv implements Callable {

    /* renamed from: a */
    private final cva f5697a;

    /* renamed from: b */
    private final hqk f5698b;

    public cuv(cva cva, hqk hqk) {
        this.f5697a = cva;
        this.f5698b = hqk;
    }

    public final Object call() {
        return Boolean.valueOf(this.f5697a.mo3845a(!((Boolean) this.f5698b.mo2652a()).booleanValue(), 26, "PluggedInWorker ended early because execution was cancelled."));
    }
}
