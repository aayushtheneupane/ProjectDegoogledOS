package p000;

import java.io.Closeable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: gqk */
/* compiled from: PG */
final /* synthetic */ class gqk implements Closeable {

    /* renamed from: a */
    private final iev f11840a;

    public gqk(iev iev) {
        this.f11840a = iev;
    }

    public final void close() {
        iev iev = this.f11840a;
        if (!iev.isCancelled() && iev.isDone()) {
            try {
                gqr.m10644a(ife.m12871b((Future) iev));
            } catch (ExecutionException e) {
            }
        }
    }
}
