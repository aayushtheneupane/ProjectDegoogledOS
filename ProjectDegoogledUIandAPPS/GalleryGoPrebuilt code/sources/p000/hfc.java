package p000;

import android.app.Application;
import android.os.SystemClock;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: hfc */
/* compiled from: PG */
final /* synthetic */ class hfc implements Runnable {

    /* renamed from: a */
    private final Application f12638a;

    /* renamed from: b */
    private final Application.ActivityLifecycleCallbacks f12639b;

    /* renamed from: c */
    private final AtomicBoolean f12640c;

    /* renamed from: d */
    private final long f12641d;

    public hfc(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks, AtomicBoolean atomicBoolean, long j) {
        this.f12638a = application;
        this.f12639b = activityLifecycleCallbacks;
        this.f12640c = atomicBoolean;
        this.f12641d = j;
    }

    public final void run() {
        String str;
        Application application = this.f12638a;
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = this.f12639b;
        AtomicBoolean atomicBoolean = this.f12640c;
        long j = this.f12641d;
        application.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        fkl a = fkl.m9082a();
        fnj fnj = fnj.f10089a;
        if (!atomicBoolean.get()) {
            str = "ColdLaunch";
        } else {
            str = "ColdLaunchBackground";
        }
        a.f9896b.mo5837a(fnj, str, j, elapsedRealtime, (iqx) null);
        if (atomicBoolean.get()) {
            fkl.m9082a().f9896b.mo5838a("ColdLaunchBackgroundMemory");
        }
    }
}
