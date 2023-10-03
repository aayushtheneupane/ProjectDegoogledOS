package p000;

import java.util.concurrent.Callable;

/* renamed from: cux */
/* compiled from: PG */
final /* synthetic */ class cux implements Callable {

    /* renamed from: a */
    private final cva f5700a;

    public cux(cva cva) {
        this.f5700a = cva;
    }

    public final Object call() {
        cva cva = this.f5700a;
        return Boolean.valueOf(cva.mo3845a(!(cva.f5710e.f3080b > 0), 21, "PluggedInWorker ended early because app was foregrounded."));
    }
}
