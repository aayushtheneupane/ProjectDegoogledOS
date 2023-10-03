package p000;

import java.io.Closeable;
import java.io.IOException;
import java.util.logging.Level;

/* renamed from: icn */
/* compiled from: PG */
final class icn implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Closeable f13886a;

    public icn(Closeable closeable) {
        this.f13886a = closeable;
    }

    public final void run() {
        try {
            this.f13886a.close();
        } catch (IOException | RuntimeException e) {
            idb.f13904a.logp(Level.WARNING, "com.google.common.util.concurrent.ClosingFuture$9", "run", "thrown by Closeable.close()", e);
        }
    }
}
