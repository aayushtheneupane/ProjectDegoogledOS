package p000;

import android.os.Looper;
import java.lang.Thread;
import java.util.ArrayList;

/* renamed from: gtl */
/* compiled from: PG */
final /* synthetic */ class gtl implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private final gtm f12020a;

    /* renamed from: b */
    private final Thread.UncaughtExceptionHandler f12021b;

    public gtl(gtm gtm, Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.f12020a = gtm;
        this.f12021b = uncaughtExceptionHandler;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        ArrayList<Throwable> arrayList;
        gtm gtm = this.f12020a;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.f12021b;
        try {
            arrayList = new ArrayList<>();
            ((hvv) ((hvv) ((hvv) gtm.f12022a.mo8178a()).mo8202a(th)).mo8201a("com/google/apps/tiktok/core/UncaughtExceptionHandlerProcessInitializer", "lambda$init$0", 46, "UncaughtExceptionHandlerProcessInitializer.java")).mo8203a("Encountered uncaught exception.");
        } catch (Throwable th2) {
            if (uncaughtExceptionHandler != null) {
                uncaughtExceptionHandler.uncaughtException(thread, th);
            }
            throw th2;
        }
        if (thread == Looper.getMainLooper().getThread()) {
            for (gth a : gtm.f12023b) {
                try {
                    a.mo7034a();
                } catch (Throwable th3) {
                    arrayList.add(th3);
                }
            }
        }
        for (Throwable a2 : arrayList) {
            ifn.m12932a(a2);
        }
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }
}
