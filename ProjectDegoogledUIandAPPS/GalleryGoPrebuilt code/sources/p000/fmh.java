package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;

/* renamed from: fmh */
/* compiled from: PG */
public final class fmh implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    public final Application f10026a;

    /* renamed from: b */
    public final /* synthetic */ fmj f10027b;

    public fmh(fmj fmj, Application application) {
        this.f10027b = fmj;
        this.f10026a = application;
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        fme fme;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f10027b.f10043i.f10020b != null) {
            fme = this.f10027b.f10044j;
        } else {
            fme = this.f10027b.f10043i;
        }
        fme.f10019a = activity.getClass().getSimpleName();
        fme.f10020b = Long.valueOf(elapsedRealtime);
    }

    public final void onActivityResumed(Activity activity) {
        fme fme;
        if (this.f10027b.f10044j.f10020b == null) {
            fme = this.f10027b.f10043i;
        } else {
            fme = this.f10027b.f10044j;
        }
        if (fme.f10022d == null) {
            fme.f10022d = Long.valueOf(SystemClock.elapsedRealtime());
        }
        try {
            View findViewById = activity.findViewById(16908290);
            findViewById.getViewTreeObserver().addOnPreDrawListener(new fmg(this, findViewById));
        } catch (RuntimeException e) {
            flw.m9192a("PrimesStartupMeasure", "Error handling PrimesStartupMeasure's onActivityResume", (Throwable) e, new Object[0]);
        }
    }

    public final void onActivityStarted(Activity activity) {
        fme fme;
        if (this.f10027b.f10044j.f10020b == null) {
            fme = this.f10027b.f10043i;
        } else {
            fme = this.f10027b.f10044j;
        }
        if (fme.f10021c == null) {
            fme.f10021c = Long.valueOf(SystemClock.elapsedRealtime());
        }
    }
}
