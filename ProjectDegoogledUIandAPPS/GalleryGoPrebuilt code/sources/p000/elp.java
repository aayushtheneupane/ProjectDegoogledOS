package p000;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: elp */
/* compiled from: PG */
public final class elp implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    /* renamed from: a */
    public static final elp f8513a = new elp();

    /* renamed from: b */
    public final AtomicBoolean f8514b = new AtomicBoolean();

    /* renamed from: c */
    public final AtomicBoolean f8515c = new AtomicBoolean();

    /* renamed from: d */
    public final ArrayList f8516d = new ArrayList();

    /* renamed from: e */
    public boolean f8517e = false;

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    private elp() {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.f8514b.compareAndSet(true, false);
        this.f8515c.set(true);
        if (compareAndSet) {
            m7739a(false);
        }
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.f8514b.compareAndSet(true, false);
        this.f8515c.set(true);
        if (compareAndSet) {
            m7739a(false);
        }
    }

    /* renamed from: a */
    private final void m7739a(boolean z) {
        synchronized (f8513a) {
            Iterator it = this.f8516d.iterator();
            while (it.hasNext()) {
                ((elo) it.next()).mo4969a(z);
            }
        }
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.f8514b.compareAndSet(false, true)) {
            this.f8515c.set(true);
            m7739a(true);
        }
    }
}
