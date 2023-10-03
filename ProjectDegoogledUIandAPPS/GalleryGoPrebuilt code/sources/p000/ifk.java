package p000;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

/* renamed from: ifk */
/* compiled from: PG */
final class ifk extends ifh {

    /* renamed from: b */
    private final ifj f14000b = new ifj();

    /* renamed from: a */
    public final void mo8486a(Throwable th, Throwable th2) {
        if (th2 != th) {
            this.f14000b.mo8491a(th, true).add(th2);
            return;
        }
        throw new IllegalArgumentException("Self suppression is not allowed.", th2);
    }

    /* renamed from: a */
    public final Throwable[] mo8487a(Throwable th) {
        List a = this.f14000b.mo8491a(th, false);
        return (a == null || a.isEmpty()) ? f13996a : (Throwable[]) a.toArray(f13996a);
    }

    /* renamed from: b */
    public final void mo8488b(Throwable th) {
        th.printStackTrace();
        List<Throwable> a = this.f14000b.mo8491a(th, false);
        if (a != null) {
            synchronized (a) {
                for (Throwable printStackTrace : a) {
                    System.err.print("Suppressed: ");
                    printStackTrace.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo8484a(Throwable th, PrintStream printStream) {
        th.printStackTrace(printStream);
        List<Throwable> a = this.f14000b.mo8491a(th, false);
        if (a != null) {
            synchronized (a) {
                for (Throwable printStackTrace : a) {
                    printStream.print("Suppressed: ");
                    printStackTrace.printStackTrace(printStream);
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo8485a(Throwable th, PrintWriter printWriter) {
        th.printStackTrace(printWriter);
        List<Throwable> a = this.f14000b.mo8491a(th, false);
        if (a != null) {
            synchronized (a) {
                for (Throwable printStackTrace : a) {
                    printWriter.print("Suppressed: ");
                    printStackTrace.printStackTrace(printWriter);
                }
            }
        }
    }
}
