package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: gos */
/* compiled from: PG */
final class gos implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private final /* synthetic */ got f11766a;

    public gos(got got) {
        this.f11766a = got;
    }

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
        got got = this.f11766a;
        int i = got.f11769c + 1;
        got.f11769c = i;
        if (i == 1) {
            got.mo6890a(true);
        }
    }

    public final void onActivityStopped(Activity activity) {
        got got = this.f11766a;
        int i = got.f11769c - 1;
        got.f11769c = i;
        if (i == 0) {
            got.mo6890a(false);
        }
    }
}
