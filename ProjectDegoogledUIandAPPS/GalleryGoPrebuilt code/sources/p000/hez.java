package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;

/* renamed from: hez */
/* compiled from: PG */
public final class hez implements Application.ActivityLifecycleCallbacks {
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
        if (activity instanceof hbe) {
            fmj fmj = fmj.f10033a;
            long l = ((hbe) activity).mo3320l();
            if (fxk.m9826a() && fmj.f10037c > 0 && l <= SystemClock.elapsedRealtime() && ((fmj.f10043i.f10020b == null || l <= fmj.f10043i.f10020b.longValue()) && fmj.f10039e == 0)) {
                fmj.f10039e = l;
                fmj.f10042h.f10030c = true;
            }
        }
        activity.getApplication().unregisterActivityLifecycleCallbacks(this);
    }
}
