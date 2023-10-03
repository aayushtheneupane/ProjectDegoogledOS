package p000;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* renamed from: iea */
/* compiled from: PG */
public final class iea {

    /* renamed from: a */
    private final boolean f13948a;

    /* renamed from: b */
    private final hso f13949b;

    public /* synthetic */ iea(boolean z, hso hso) {
        this.f13948a = z;
        this.f13949b = hso;
    }

    /* renamed from: a */
    public final ieh mo8443a(Callable callable, Executor executor) {
        return new idg((hsf) this.f13949b, this.f13948a, executor, callable);
    }

    /* renamed from: a */
    public final ieh mo8442a(ice ice, Executor executor) {
        return new idg((hsf) this.f13949b, this.f13948a, executor, ice);
    }
}
