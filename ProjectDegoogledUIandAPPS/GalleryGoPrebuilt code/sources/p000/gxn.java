package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import java.lang.reflect.Field;

/* renamed from: gxn */
/* compiled from: PG */
final class gxn implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    public Object f12256a;

    /* renamed from: b */
    private Activity f12257b;

    /* renamed from: c */
    private boolean f12258c = false;

    /* renamed from: d */
    private boolean f12259d = false;

    /* renamed from: e */
    private boolean f12260e = false;

    public gxn(Activity activity) {
        this.f12257b = activity;
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
        if (this.f12257b == activity) {
            this.f12257b = null;
            this.f12259d = true;
        }
    }

    public final void onActivityPaused(Activity activity) {
        if (this.f12259d && !this.f12260e && !this.f12258c) {
            Object obj = this.f12256a;
            Field field = gxo.f12261a;
            try {
                Object obj2 = gxo.f12262b.get(activity);
                if (obj2 == obj) {
                    new Handler(Looper.getMainLooper()).postAtFrontOfQueue(new gxm(gxo.f12261a.get(activity), obj2));
                    this.f12260e = true;
                    this.f12256a = null;
                }
            } catch (Throwable th) {
                ifn.m12932a(th);
            }
        }
    }

    public final void onActivityStarted(Activity activity) {
        if (this.f12257b == activity) {
            this.f12258c = true;
        }
    }
}
