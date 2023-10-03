package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: am */
/* compiled from: PG */
final class C0013am implements Application.ActivityLifecycleCallbacks {
    public final void onActivityCreated(Activity activity, Bundle bundle) {
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

    public final void onActivityPostCreated(Activity activity, Bundle bundle) {
        C0014an.m798a(activity, C0546u.ON_CREATE);
    }

    public final void onActivityPostResumed(Activity activity) {
        C0014an.m798a(activity, C0546u.ON_RESUME);
    }

    public final void onActivityPostStarted(Activity activity) {
        C0014an.m798a(activity, C0546u.ON_START);
    }

    public final void onActivityPreDestroyed(Activity activity) {
        C0014an.m798a(activity, C0546u.ON_DESTROY);
    }

    public final void onActivityPrePaused(Activity activity) {
        C0014an.m798a(activity, C0546u.ON_PAUSE);
    }

    public final void onActivityPreStopped(Activity activity) {
        C0014an.m798a(activity, C0546u.ON_STOP);
    }
}
