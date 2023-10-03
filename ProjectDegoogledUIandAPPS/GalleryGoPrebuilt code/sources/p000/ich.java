package p000;

import java.io.Closeable;
import java.util.concurrent.Executor;

/* renamed from: ich */
/* compiled from: PG */
final class ich implements idw {

    /* renamed from: a */
    private final /* synthetic */ idb f13875a;

    /* renamed from: b */
    private final /* synthetic */ Executor f13876b;

    public ich(idb idb, Executor executor) {
        this.f13875a = idb;
        this.f13876b = executor;
    }

    /* renamed from: a */
    public final void mo3868a(Throwable th) {
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo3867a(Object obj) {
        this.f13875a.f13906c.mo8382b((Closeable) obj, this.f13876b);
    }
}
