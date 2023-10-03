package p000;

import java.util.concurrent.Callable;

/* renamed from: flt */
/* compiled from: PG */
final class flt implements Callable {

    /* renamed from: a */
    private final /* synthetic */ Callable f9999a;

    /* renamed from: b */
    private final /* synthetic */ flv f10000b;

    public flt(flv flv, Callable callable) {
        this.f10000b = flv;
        this.f9999a = callable;
    }

    public final Object call() {
        try {
            return this.f9999a.call();
        } catch (Throwable th) {
            this.f10000b.f10001a.mo3868a(th);
            throw th;
        }
    }
}
