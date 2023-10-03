package p000;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: fmg */
/* compiled from: PG */
final class fmg implements ViewTreeObserver.OnPreDrawListener {

    /* renamed from: a */
    public final /* synthetic */ fmh f10024a;

    /* renamed from: b */
    private View f10025b;

    public /* synthetic */ fmg(fmh fmh, View view) {
        this.f10024a = fmh;
        this.f10025b = view;
    }

    public final boolean onPreDraw() {
        try {
            if (this.f10025b != null) {
                fmh fmh = this.f10024a;
                fmh.f10026a.unregisterActivityLifecycleCallbacks(fmh);
                this.f10025b.getViewTreeObserver().removeOnPreDrawListener(this);
                fxk.m9824a((Runnable) new fmf(this));
            }
        } catch (RuntimeException e) {
            flw.m9192a("PrimesStartupMeasure", "Error handling PrimesStartupMeasure's onPreDraw", (Throwable) e, new Object[0]);
        } catch (Throwable th) {
            this.f10025b = null;
            throw th;
        }
        this.f10025b = null;
        return true;
    }
}
