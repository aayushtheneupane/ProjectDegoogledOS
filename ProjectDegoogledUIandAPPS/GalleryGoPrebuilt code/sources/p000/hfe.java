package p000;

import android.app.Application;
import android.content.Context;
import android.os.SystemClock;

/* renamed from: hfe */
/* compiled from: PG */
public final class hfe implements gtj {

    /* renamed from: a */
    private final Context f12644a;

    /* renamed from: b */
    private final long f12645b;

    public hfe(Context context, long j) {
        this.f12644a = context;
        this.f12645b = j;
    }

    /* renamed from: a */
    public final void mo7033a() {
        fmj fmj = fmj.f10033a;
        Application application = (Application) this.f12644a;
        long j = this.f12645b;
        if (j <= SystemClock.elapsedRealtime()) {
            fmj.f10037c = j;
            fmj.f10042h.f10028a = true;
            if (fxk.m9826a() && fmj.f10037c > 0 && fmj.f10038d == 0 && application != null) {
                fmj.f10038d = SystemClock.elapsedRealtime();
                fmj.f10042h.f10029b = true;
                fxk.m9824a((Runnable) new fmd(fmj));
                application.registerActivityLifecycleCallbacks(new fmh(fmj, application));
            }
        }
    }
}
