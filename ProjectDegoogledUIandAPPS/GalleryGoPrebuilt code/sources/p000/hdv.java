package p000;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Debug;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: hdv */
/* compiled from: PG */
public final class hdv implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    /* renamed from: a */
    public final Map f12556a = new HashMap();

    /* renamed from: b */
    private final Context f12557b;

    /* renamed from: c */
    private final Deque f12558c = new ArrayDeque();

    /* renamed from: d */
    private final Set f12559d = new HashSet();

    public hdv(Context context) {
        this.f12557b = context;
        ((Application) context).registerActivityLifecycleCallbacks(this);
        context.registerComponentCallbacks(this);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onActivityDestroyed(Activity activity) {
        this.f12558c.remove(activity);
        this.f12556a.remove(activity);
    }

    public final void onActivityStarted(Activity activity) {
        this.f12558c.remove(activity);
        this.f12558c.add(activity);
        this.f12559d.add(activity);
    }

    public final void onActivityStopped(Activity activity) {
        this.f12559d.remove(activity);
        if (!activity.isChangingConfigurations() && !activity.isFinishing()) {
            long maxMemory = Runtime.getRuntime().maxMemory();
            int i = Build.VERSION.SDK_INT;
            double nativeHeapAllocatedSize = (double) Debug.getNativeHeapAllocatedSize();
            double d = (double) maxMemory;
            Double.isNaN(nativeHeapAllocatedSize);
            Double.isNaN(d);
            double d2 = nativeHeapAllocatedSize / d;
            if (d2 >= 0.8d) {
                m11324a(1);
            } else if (d2 >= 0.7d) {
                m11324a(2);
            } else if (d2 >= 0.6d) {
                m11324a(3);
            }
        }
    }

    public final void onTrimMemory(int i) {
        if (i < 20) {
            m11324a(i < 15 ? i < 10 ? 3 : 2 : 1);
        }
    }

    /* renamed from: a */
    private final void m11324a(int i) {
        ife.m12890c(true);
        this.f12558c.size();
        int size = this.f12558c.size() - i;
        for (int i2 = 0; i2 < size; i2++) {
            if (this.f12559d.contains((Activity) this.f12558c.peekFirst())) {
                break;
            }
            Set<hdt> set = (Set) this.f12556a.get((Activity) this.f12558c.removeFirst());
            if (set != null) {
                for (hdt hdt : set) {
                    ife.m12898e((Object) gtf.f12011a);
                    hdt.f12550a.mo1438a();
                }
            }
        }
        if (size > 0) {
            Runtime runtime = Runtime.getRuntime();
            int i3 = Build.VERSION.SDK_INT;
            double d = (double) runtime.totalMemory();
            Double.isNaN(d);
            if (((double) Debug.getNativeHeapAllocatedSize()) > d * 0.8d) {
                aow.m1346a(this.f12557b).mo1396a(15);
            }
        }
    }
}
