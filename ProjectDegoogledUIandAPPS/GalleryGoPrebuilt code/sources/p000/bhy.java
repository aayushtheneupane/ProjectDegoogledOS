package p000;

import java.util.concurrent.ThreadFactory;

/* renamed from: bhy */
/* compiled from: PG */
final class bhy implements ThreadFactory {

    /* renamed from: a */
    private final /* synthetic */ bib f2422a;

    public bhy(bib bib) {
        this.f2422a = bib;
    }

    public final Thread newThread(Runnable runnable) {
        String valueOf = String.valueOf(this.f2422a.getClass().getName());
        return new Thread(runnable, valueOf.length() == 0 ? new String("FJD.JobService ") : "FJD.JobService ".concat(valueOf));
    }
}
