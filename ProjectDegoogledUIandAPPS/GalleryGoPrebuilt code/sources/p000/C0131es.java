package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: es */
/* compiled from: PG */
final class C0131es implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    public Object f8890a;

    /* renamed from: b */
    private Activity f8891b;

    /* renamed from: c */
    private boolean f8892c = false;

    /* renamed from: d */
    private boolean f8893d = false;

    /* renamed from: e */
    private boolean f8894e = false;

    public C0131es(Activity activity) {
        this.f8891b = activity;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onActivityDestroyed(Activity activity) {
        if (this.f8891b == activity) {
            this.f8891b = null;
            this.f8893d = true;
        }
    }

    public final void onActivityPaused(Activity activity) {
        if (this.f8893d && !this.f8894e && !this.f8892c && C0132et.m8137a(this.f8890a, activity)) {
            this.f8894e = true;
            this.f8890a = null;
        }
    }

    public final void onActivityStarted(Activity activity) {
        if (this.f8891b == activity) {
            this.f8892c = true;
        }
    }
}
