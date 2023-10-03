package p000;

import java.io.Closeable;

/* renamed from: cuz */
/* compiled from: PG */
final /* synthetic */ class cuz implements Closeable {

    /* renamed from: a */
    private final cva f5702a;

    public cuz(cva cva) {
        this.f5702a = cva;
    }

    public final void close() {
        this.f5702a.f5708c.mo3835b(cuh.PLUGGED_IN_IDLE_WORKER);
    }
}
