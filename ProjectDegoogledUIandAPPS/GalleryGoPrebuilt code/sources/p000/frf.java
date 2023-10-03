package p000;

import java.io.IOException;
import java.util.concurrent.Callable;

/* renamed from: frf */
/* compiled from: PG */
final class frf {

    /* renamed from: a */
    public final long f10309a;

    /* renamed from: b */
    public final long f10310b;

    /* renamed from: c */
    public final boolean f10311c;

    public frf(long j, long j2, boolean z) {
        this.f10309a = j;
        this.f10310b = j2;
        this.f10311c = z;
    }

    /* renamed from: a */
    public static Object m9451a(Callable callable) {
        try {
            return callable.call();
        } catch (Throwable th) {
            throw new IOException(th);
        }
    }
}
