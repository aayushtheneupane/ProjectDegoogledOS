package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.Set;

/* renamed from: hcn */
/* compiled from: PG */
final class hcn implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private boolean f12476a = false;

    /* renamed from: b */
    private final /* synthetic */ Application f12477b;

    /* renamed from: c */
    private final /* synthetic */ iqk f12478c;

    /* renamed from: d */
    private final /* synthetic */ iqk f12479d;

    public hcn(Application application, iqk iqk, iqk iqk2) {
        this.f12477b = application;
        this.f12478c = iqk;
        this.f12479d = iqk2;
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
        this.f12477b.unregisterActivityLifecycleCallbacks(this);
        if (!this.f12476a) {
            this.f12476a = true;
            for (Application.ActivityLifecycleCallbacks hlx : (Set) this.f12478c.mo2097a()) {
                hlx hlx2 = new hlx((hlz) this.f12479d.mo2097a(), hlx);
                this.f12477b.registerActivityLifecycleCallbacks(hlx2);
                hlx2.onActivityCreated(activity, bundle);
            }
        }
    }
}
