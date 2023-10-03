package p000;

import android.content.Context;
import android.util.Log;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: fsy */
/* compiled from: PG */
public final class fsy implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private final Context f10555a;

    /* renamed from: b */
    private final Thread.UncaughtExceptionHandler f10556b;

    /* renamed from: c */
    private final AtomicReference f10557c;

    /* renamed from: d */
    private final fta f10558d;

    public fsy(Context context, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, AtomicReference atomicReference, fta fta) {
        this.f10555a = context;
        this.f10556b = uncaughtExceptionHandler;
        this.f10557c = atomicReference;
        this.f10558d = fta;
    }

    public final void uncaughtException(Thread thread, Throwable th) {
        try {
            if (this.f10558d.mo3864c()) {
                foj.m9312a(this.f10555a, th, this.f10558d.mo3862a());
                this.f10557c.set(th);
            }
        } catch (Throwable th2) {
            Log.e("SilentFeedbackHandler", "An error occured checking if exception should be reported, skipping silent feedback.", th2);
        }
        this.f10556b.uncaughtException(thread, th);
    }
}
