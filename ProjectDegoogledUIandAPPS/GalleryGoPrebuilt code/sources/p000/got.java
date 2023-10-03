package p000;

import android.app.Application;
import java.util.HashSet;
import java.util.Set;

/* renamed from: got */
/* compiled from: PG */
public final class got {

    /* renamed from: a */
    public final Object f11767a = new Object();

    /* renamed from: b */
    public final Set f11768b = new HashSet();

    /* renamed from: c */
    public int f11769c;

    /* renamed from: d */
    public final Application.ActivityLifecycleCallbacks f11770d = new gos(this);

    /* renamed from: e */
    private boolean f11771e;

    /* renamed from: a */
    public final boolean mo6891a() {
        boolean z;
        synchronized (this.f11767a) {
            z = this.f11771e;
        }
        return z;
    }

    /* renamed from: a */
    public final void mo6890a(boolean z) {
        fxk.m9830b();
        synchronized (this.f11767a) {
            this.f11771e = z;
            for (Runnable run : this.f11768b) {
                run.run();
            }
        }
    }
}
