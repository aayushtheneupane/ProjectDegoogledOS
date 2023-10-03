package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: bku */
/* compiled from: PG */
final class bku implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private final /* synthetic */ bkv f3078a;

    public /* synthetic */ bku(bkv bkv) {
        this.f3078a = bkv;
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
        this.f3078a.f3080b++;
        Object[] objArr = {activity.getClass().getSimpleName(), Integer.valueOf(this.f3078a.f3080b)};
    }

    public final void onActivityStopped(Activity activity) {
        bkv bkv = this.f3078a;
        bkv.f3080b--;
        Object[] objArr = {activity.getClass().getSimpleName(), Integer.valueOf(this.f3078a.f3080b)};
    }
}
