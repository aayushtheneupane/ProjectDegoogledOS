package p000;

import android.app.Application;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: hfb */
/* compiled from: PG */
public final /* synthetic */ class hfb implements hbc {

    /* renamed from: a */
    private final Application f12634a;

    /* renamed from: b */
    private final Application.ActivityLifecycleCallbacks f12635b;

    /* renamed from: c */
    private final AtomicBoolean f12636c;

    /* renamed from: d */
    private final long f12637d;

    public hfb(Application application, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks, AtomicBoolean atomicBoolean, long j) {
        this.f12634a = application;
        this.f12635b = activityLifecycleCallbacks;
        this.f12636c = atomicBoolean;
        this.f12637d = j;
    }

    /* renamed from: a */
    public final void mo7253a() {
        Application application = this.f12634a;
        Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = this.f12635b;
        AtomicBoolean atomicBoolean = this.f12636c;
        long j = this.f12637d;
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
        fxk.m9824a((Runnable) new hfc(application, activityLifecycleCallbacks, atomicBoolean, j));
    }
}
