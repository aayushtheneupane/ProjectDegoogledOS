package p000;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: fie */
/* compiled from: PG */
final class fie implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    /* renamed from: a */
    public final List f9699a = new CopyOnWriteArrayList();

    /* renamed from: b */
    private final AtomicInteger f9700b = new AtomicInteger();

    /* renamed from: c */
    private final AtomicInteger f9701c = new AtomicInteger();

    /* renamed from: d */
    private final AtomicInteger f9702d = new AtomicInteger();

    /* renamed from: e */
    private final AtomicInteger f9703e = new AtomicInteger();

    /* renamed from: f */
    private final AtomicInteger f9704f = new AtomicInteger();

    /* renamed from: g */
    private final AtomicInteger f9705g = new AtomicInteger();

    /* renamed from: h */
    private Boolean f9706h;

    /* renamed from: i */
    private volatile String f9707i;

    /* renamed from: j */
    private volatile Activity f9708j;

    private fie() {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public /* synthetic */ fie(byte[] bArr) {
    }

    /* renamed from: a */
    private final void m8941a(Activity activity) {
        m8942a(Boolean.valueOf(fom.m9323b(activity.getApplicationContext())), activity);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.f9700b.incrementAndGet();
        this.f9708j = null;
        for (fic fic : this.f9699a) {
            if (fic instanceof fhs) {
                ((fhs) fic).mo5735b();
            }
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.f9705g.incrementAndGet();
        this.f9708j = null;
        for (fic fic : this.f9699a) {
            if (fic instanceof fht) {
                ((fht) fic).mo5736a();
            }
        }
    }

    public final void onActivityPaused(Activity activity) {
        this.f9703e.incrementAndGet();
        this.f9707i = null;
        for (fic fic : this.f9699a) {
            if (fic instanceof fhu) {
                ((fhu) fic).mo5737a(activity);
            }
        }
    }

    public final void onActivityResumed(Activity activity) {
        this.f9702d.incrementAndGet();
        this.f9708j = null;
        this.f9707i = activity.getClass().getSimpleName();
        for (fic fic : this.f9699a) {
            if (fic instanceof fhv) {
                ((fhv) fic).mo5738b(activity);
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        for (fic fic : this.f9699a) {
            if (fic instanceof fhw) {
                ((fhw) fic).mo5739a();
            }
        }
    }

    public final void onActivityStarted(Activity activity) {
        this.f9701c.incrementAndGet();
        this.f9708j = null;
        m8941a(activity);
        for (fic fic : this.f9699a) {
            if (fic instanceof fhx) {
                ((fhx) fic).mo5740a(activity);
            }
        }
    }

    public final void onActivityStopped(Activity activity) {
        this.f9704f.incrementAndGet();
        this.f9708j = activity;
        for (fic fic : this.f9699a) {
            if (fic instanceof fhy) {
                ((fhy) fic).mo5741a();
            }
        }
        m8941a(activity);
    }

    public final void onTrimMemory(int i) {
        for (fic fic : this.f9699a) {
            if (fic instanceof fib) {
                ((fib) fic).mo5746a();
            }
        }
        if (i >= 20 && this.f9708j != null) {
            m8942a(false, this.f9708j);
        }
        this.f9708j = null;
    }

    /* renamed from: a */
    private final void m8942a(Boolean bool, Activity activity) {
        if (bool.equals(this.f9706h)) {
            flw.m9201c("AppLifecycleTracker", "App foreground state unchanged: inForeground ? %b", bool);
            return;
        }
        this.f9706h = bool;
        if (bool.booleanValue()) {
            flw.m9201c("AppLifecycleTracker", "App transition to foreground", new Object[0]);
            for (fic fic : this.f9699a) {
                if (fic instanceof fia) {
                    ((fia) fic).mo5745a(activity);
                }
            }
            return;
        }
        flw.m9201c("AppLifecycleTracker", "App transition to background", new Object[0]);
        for (fic fic2 : this.f9699a) {
            if (fic2 instanceof fhz) {
                ((fhz) fic2).mo5742b(activity);
            }
        }
    }
}
