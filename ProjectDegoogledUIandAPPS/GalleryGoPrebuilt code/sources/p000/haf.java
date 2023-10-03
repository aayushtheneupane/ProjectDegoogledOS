package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;

/* renamed from: haf */
/* compiled from: PG */
public final class haf implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private final /* synthetic */ ham f12402a;

    public haf(ham ham) {
        this.f12402a = ham;
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.f12402a.f12413a.add(activity);
    }

    public final void onActivityDestroyed(Activity activity) {
        this.f12402a.f12413a.remove(activity);
    }

    public final void onActivityStarted(Activity activity) {
        this.f12402a.f12414b.add(activity);
    }

    public final void onActivityStopped(Activity activity) {
        this.f12402a.f12414b.remove(activity);
        ham ham = this.f12402a;
        fxk.m9830b();
        if (!((Boolean) ham.f12416d.mo7645a(false)).booleanValue() && !ham.f12417e && ham.f12414b.isEmpty() && ham.mo7250a()) {
            Looper.myQueue().addIdleHandler(hmq.m11740a((MessageQueue.IdleHandler) new hac(ham)));
        }
    }
}
