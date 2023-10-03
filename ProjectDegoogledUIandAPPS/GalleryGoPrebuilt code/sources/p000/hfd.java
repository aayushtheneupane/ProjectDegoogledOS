package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: hfd */
/* compiled from: PG */
public final class hfd implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private final /* synthetic */ AtomicBoolean f12642a;

    /* renamed from: b */
    private final /* synthetic */ Application f12643b;

    public hfd(AtomicBoolean atomicBoolean, Application application) {
        this.f12642a = atomicBoolean;
        this.f12643b = application;
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.f12642a.set(false);
        this.f12643b.unregisterActivityLifecycleCallbacks(this);
    }
}
