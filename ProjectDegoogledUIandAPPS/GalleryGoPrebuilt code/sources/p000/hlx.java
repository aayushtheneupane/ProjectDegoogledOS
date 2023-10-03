package p000;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* renamed from: hlx */
/* compiled from: PG */
public final class hlx implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private final /* synthetic */ Application.ActivityLifecycleCallbacks f13011a;

    /* renamed from: b */
    private final /* synthetic */ hlz f13012b;

    public hlx(hlz hlz, Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.f13012b = hlz;
        this.f13011a = activityLifecycleCallbacks;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13011a.onActivityCreated(activity, bundle);
            return;
        }
        hlp a = this.f13012b.mo7577a(String.valueOf(activity.getClass().getName()).concat("#onActivityCreated"));
        try {
            this.f13011a.onActivityCreated(activity, bundle);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onActivityDestroyed(Activity activity) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13011a.onActivityDestroyed(activity);
            return;
        }
        hlp a = this.f13012b.mo7577a(String.valueOf(activity.getClass().getName()).concat("#onActivityDestroyed"));
        try {
            this.f13011a.onActivityDestroyed(activity);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onActivityPaused(Activity activity) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13011a.onActivityPaused(activity);
            return;
        }
        hlp a = this.f13012b.mo7577a(String.valueOf(activity.getClass().getName()).concat("#onActivityPaused"));
        try {
            this.f13011a.onActivityPaused(activity);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onActivityResumed(Activity activity) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13011a.onActivityResumed(activity);
            return;
        }
        hlp a = this.f13012b.mo7577a(String.valueOf(activity.getClass().getName()).concat("#onActivityResumed"));
        try {
            this.f13011a.onActivityResumed(activity);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13011a.onActivitySaveInstanceState(activity, bundle);
            return;
        }
        hlp a = this.f13012b.mo7577a(String.valueOf(activity.getClass().getName()).concat("#onActivitySaveInstanceState"));
        try {
            this.f13011a.onActivitySaveInstanceState(activity, bundle);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onActivityStarted(Activity activity) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13011a.onActivityStarted(activity);
            return;
        }
        hlp a = this.f13012b.mo7577a(String.valueOf(activity.getClass().getName()).concat("#onActivityStarted"));
        try {
            this.f13011a.onActivityStarted(activity);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }

    public final void onActivityStopped(Activity activity) {
        if (hnb.m11774a(hnf.f13084a)) {
            this.f13011a.onActivityStopped(activity);
            return;
        }
        hlp a = this.f13012b.mo7577a(String.valueOf(activity.getClass().getName()).concat("#onActivityStopped"));
        try {
            this.f13011a.onActivityStopped(activity);
            if (a != null) {
                a.close();
                return;
            }
            return;
        } catch (Throwable th) {
            ifn.m12935a(th, th);
        }
        throw th;
    }
}
