package p000;

import android.content.Context;
import android.util.Log;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: fsx */
/* compiled from: PG */
public final class fsx implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private final Context f10551a;

    /* renamed from: b */
    private final Thread.UncaughtExceptionHandler f10552b;

    /* renamed from: c */
    private final AtomicReference f10553c;

    /* renamed from: d */
    private final fta f10554d;

    public fsx(Context context, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, AtomicReference atomicReference, fta fta) {
        this.f10551a = context;
        this.f10552b = uncaughtExceptionHandler;
        this.f10553c = atomicReference;
        this.f10554d = fta;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        Throwable th2;
        try {
            if (this.f10554d.mo3864c() && ((th2 = (Throwable) this.f10553c.get()) == null || th2 != th)) {
                foj.m9312a(this.f10551a, th, this.f10554d.mo3863b());
            }
        } catch (Throwable th3) {
            Log.e("SilentFeedbackHandler", "An error occured checking if exception should be reported, skipping silent feedback.", th3);
        }
        this.f10552b.uncaughtException(thread, th);
    }
}
