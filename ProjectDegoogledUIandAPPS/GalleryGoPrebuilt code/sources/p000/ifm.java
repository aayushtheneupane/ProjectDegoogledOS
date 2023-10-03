package p000;

import java.io.PrintStream;
import java.io.PrintWriter;

/* renamed from: ifm */
/* compiled from: PG */
final class ifm extends ifh {
    /* renamed from: a */
    public final void mo8486a(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }

    /* renamed from: a */
    public final Throwable[] mo8487a(Throwable th) {
        return th.getSuppressed();
    }

    /* renamed from: b */
    public final void mo8488b(Throwable th) {
        th.printStackTrace();
    }

    /* renamed from: a */
    public final void mo8484a(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
    }

    /* renamed from: a */
    public final void mo8485a(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
    }
}
