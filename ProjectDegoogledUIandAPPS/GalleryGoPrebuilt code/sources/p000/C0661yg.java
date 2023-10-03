package p000;

import android.support.p002v7.widget.RecyclerView;
import android.util.Log;
import android.view.animation.Interpolator;

/* renamed from: yg */
/* compiled from: PG */
public final class C0661yg {

    /* renamed from: a */
    public int f16335a = -1;

    /* renamed from: b */
    private int f16336b = 0;

    /* renamed from: c */
    private int f16337c = 0;

    /* renamed from: d */
    private int f16338d = RecyclerView.UNDEFINED_DURATION;

    /* renamed from: e */
    private Interpolator f16339e = null;

    /* renamed from: f */
    private boolean f16340f = false;

    /* renamed from: g */
    private int f16341g = 0;

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10621a(RecyclerView recyclerView) {
        int i = this.f16335a;
        if (i >= 0) {
            this.f16335a = -1;
            recyclerView.jumpToPositionForSmoothScroller(i);
            this.f16340f = false;
        } else if (this.f16340f) {
            Interpolator interpolator = this.f16339e;
            if (interpolator != null && this.f16338d <= 0) {
                throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
            }
            int i2 = this.f16338d;
            if (i2 > 0) {
                recyclerView.mViewFlinger.mo10631a(this.f16336b, this.f16337c, i2, interpolator);
                int i3 = this.f16341g + 1;
                this.f16341g = i3;
                if (i3 > 10) {
                    Log.e(RecyclerView.TAG, "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                }
                this.f16340f = false;
                return;
            }
            throw new IllegalStateException("Scroll duration must be a positive number");
        } else {
            this.f16341g = 0;
        }
    }

    /* renamed from: a */
    public final void mo10620a(int i, int i2, int i3, Interpolator interpolator) {
        this.f16336b = i;
        this.f16337c = i2;
        this.f16338d = i3;
        this.f16339e = interpolator;
        this.f16340f = true;
    }
}
