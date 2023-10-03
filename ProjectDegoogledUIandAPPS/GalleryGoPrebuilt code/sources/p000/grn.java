package p000;

import java.util.concurrent.Callable;

/* renamed from: grn */
/* compiled from: PG */
final /* synthetic */ class grn implements Runnable {

    /* renamed from: a */
    private final iev f11912a;

    /* renamed from: b */
    private final Callable f11913b;

    public grn(iev iev, Callable callable) {
        this.f11912a = iev;
        this.f11913b = callable;
    }

    public final void run() {
        iev iev = this.f11912a;
        try {
            iev.mo8346b(this.f11913b.call());
        } catch (Exception e) {
            iev.mo6952a((Throwable) e);
            throw new RuntimeException(e);
        }
    }
}
