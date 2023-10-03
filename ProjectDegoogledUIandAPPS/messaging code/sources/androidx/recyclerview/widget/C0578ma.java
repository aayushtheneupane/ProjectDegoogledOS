package androidx.recyclerview.widget;

import android.util.Log;
import android.view.animation.Interpolator;

/* renamed from: androidx.recyclerview.widget.ma */
public class C0578ma {

    /* renamed from: Ts */
    private int f650Ts = -1;

    /* renamed from: Us */
    private boolean f651Us = false;

    /* renamed from: Vs */
    private int f652Vs = 0;
    private int mDuration;
    private int mDx;
    private int mDy;
    private Interpolator mInterpolator;

    public C0578ma(int i, int i2) {
        this.mDx = i;
        this.mDy = i2;
        this.mDuration = RtlSpacingHelper.UNDEFINED;
        this.mInterpolator = null;
    }

    /* access modifiers changed from: package-private */
    public boolean hasJumpTarget() {
        return this.f650Ts >= 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: j */
    public void mo5164j(RecyclerView recyclerView) {
        int i = this.f650Ts;
        if (i >= 0) {
            this.f650Ts = -1;
            recyclerView.jumpToPositionForSmoothScroller(i);
            this.f651Us = false;
        } else if (!this.f651Us) {
            this.f652Vs = 0;
        } else if (this.mInterpolator == null || this.mDuration >= 1) {
            int i2 = this.mDuration;
            if (i2 >= 1) {
                recyclerView.mViewFlinger.smoothScrollBy(this.mDx, this.mDy, i2, this.mInterpolator);
                this.f652Vs++;
                if (this.f652Vs > 10) {
                    Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
                }
                this.f651Us = false;
                return;
            }
            throw new IllegalStateException("Scroll duration must be a positive number");
        } else {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
    }

    public void jumpTo(int i) {
        this.f650Ts = i;
    }

    public void update(int i, int i2, int i3, Interpolator interpolator) {
        this.mDx = i;
        this.mDy = i2;
        this.mDuration = i3;
        this.mInterpolator = interpolator;
        this.f651Us = true;
    }
}
