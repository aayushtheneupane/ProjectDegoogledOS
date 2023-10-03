package androidx.recyclerview.widget;

import android.os.Build;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import java.util.Arrays;

/* renamed from: androidx.recyclerview.widget.pa */
class C0584pa implements Runnable {

    /* renamed from: Ws */
    private int f659Ws;

    /* renamed from: Xs */
    private int f660Xs;

    /* renamed from: Ys */
    OverScroller f661Ys;

    /* renamed from: Zs */
    private boolean f662Zs = false;

    /* renamed from: _s */
    private boolean f663_s = false;
    Interpolator mInterpolator = RecyclerView.sQuinticInterpolator;
    final /* synthetic */ RecyclerView this$0;

    C0584pa(RecyclerView recyclerView) {
        this.this$0 = recyclerView;
        this.f661Ys = new OverScroller(recyclerView.getContext(), RecyclerView.sQuinticInterpolator);
    }

    public void fling(int i, int i2) {
        this.this$0.setScrollState(2);
        this.f660Xs = 0;
        this.f659Ws = 0;
        Interpolator interpolator = this.mInterpolator;
        Interpolator interpolator2 = RecyclerView.sQuinticInterpolator;
        if (interpolator != interpolator2) {
            this.mInterpolator = interpolator2;
            this.f661Ys = new OverScroller(this.this$0.getContext(), RecyclerView.sQuinticInterpolator);
        }
        this.f661Ys.fling(0, 0, i, i2, RtlSpacingHelper.UNDEFINED, Integer.MAX_VALUE, RtlSpacingHelper.UNDEFINED, Integer.MAX_VALUE);
        postOnAnimation();
    }

    /* access modifiers changed from: package-private */
    public void postOnAnimation() {
        if (this.f662Zs) {
            this.f663_s = true;
            return;
        }
        this.this$0.removeCallbacks(this);
        ViewCompat.postOnAnimation(this.this$0, this);
    }

    public void run() {
        int i;
        int i2;
        RecyclerView recyclerView = this.this$0;
        if (recyclerView.mLayout == null) {
            stop();
            return;
        }
        this.f663_s = false;
        this.f662Zs = true;
        recyclerView.consumePendingUpdateOperations();
        OverScroller overScroller = this.f661Ys;
        if (overScroller.computeScrollOffset()) {
            int currX = overScroller.getCurrX();
            int currY = overScroller.getCurrY();
            int i3 = currX - this.f659Ws;
            int i4 = currY - this.f660Xs;
            this.f659Ws = currX;
            this.f660Xs = currY;
            RecyclerView recyclerView2 = this.this$0;
            int[] iArr = recyclerView2.f595yi;
            iArr[0] = 0;
            iArr[1] = 0;
            if (recyclerView2.dispatchNestedPreScroll(i3, i4, iArr, (int[]) null, 1)) {
                int[] iArr2 = this.this$0.f595yi;
                i3 -= iArr2[0];
                i4 -= iArr2[1];
            }
            if (this.this$0.getOverScrollMode() != 2) {
                this.this$0.considerReleasingGlowsOnScroll(i3, i4);
            }
            RecyclerView recyclerView3 = this.this$0;
            if (recyclerView3.mAdapter != null) {
                int[] iArr3 = recyclerView3.f595yi;
                iArr3[0] = 0;
                iArr3[1] = 0;
                recyclerView3.mo4818a(i3, i4, iArr3);
                RecyclerView recyclerView4 = this.this$0;
                int[] iArr4 = recyclerView4.f595yi;
                i = iArr4[0];
                i2 = iArr4[1];
                i3 -= i;
                i4 -= i2;
                C0529E e = recyclerView4.mLayout.mSmoothScroller;
                if (e != null && !e.isPendingInitialRun() && e.isRunning()) {
                    int itemCount = this.this$0.mState.getItemCount();
                    if (itemCount == 0) {
                        e.stop();
                    } else if (e.getTargetPosition() >= itemCount) {
                        e.setTargetPosition(itemCount - 1);
                        e.mo4655j(i, i2);
                    } else {
                        e.mo4655j(i, i2);
                    }
                }
            } else {
                i2 = 0;
                i = 0;
            }
            if (!this.this$0.mItemDecorations.isEmpty()) {
                this.this$0.invalidate();
            }
            RecyclerView recyclerView5 = this.this$0;
            int[] iArr5 = recyclerView5.f595yi;
            iArr5[0] = 0;
            iArr5[1] = 0;
            recyclerView5.dispatchNestedScroll(i, i2, i3, i4, (int[]) null, 1, iArr5);
            int[] iArr6 = this.this$0.f595yi;
            int i5 = i3 - iArr6[0];
            int i6 = i4 - iArr6[1];
            if (!(i == 0 && i2 == 0)) {
                this.this$0.dispatchOnScrolled(i, i2);
            }
            if (!this.this$0.awakenScrollBars()) {
                this.this$0.invalidate();
            }
            boolean z = overScroller.isFinished() || (((overScroller.getCurrX() == overScroller.getFinalX()) || i5 != 0) && ((overScroller.getCurrY() == overScroller.getFinalY()) || i6 != 0));
            C0529E e2 = this.this$0.mLayout.mSmoothScroller;
            if ((e2 != null && e2.isPendingInitialRun()) || !z) {
                postOnAnimation();
                RecyclerView recyclerView6 = this.this$0;
                C0597w wVar = recyclerView6.mGapWorker;
                if (wVar != null) {
                    wVar.mo5257b(recyclerView6, i5, i6);
                }
            } else {
                if (this.this$0.getOverScrollMode() != 2) {
                    int currVelocity = (int) overScroller.getCurrVelocity();
                    int i7 = i5 < 0 ? -currVelocity : i5 > 0 ? currVelocity : 0;
                    if (i6 < 0) {
                        currVelocity = -currVelocity;
                    } else if (i6 <= 0) {
                        currVelocity = 0;
                    }
                    this.this$0.absorbGlows(i7, currVelocity);
                }
                if (RecyclerView.f562Di) {
                    C0593u uVar = this.this$0.mPrefetchRegistry;
                    int[] iArr7 = uVar.mPrefetchArray;
                    if (iArr7 != null) {
                        Arrays.fill(iArr7, -1);
                    }
                    uVar.mCount = 0;
                }
            }
        }
        C0529E e3 = this.this$0.mLayout.mSmoothScroller;
        if (e3 != null && e3.isPendingInitialRun()) {
            e3.mo4655j(0, 0);
        }
        this.f662Zs = false;
        if (this.f663_s) {
            this.this$0.removeCallbacks(this);
            ViewCompat.postOnAnimation(this.this$0, this);
            return;
        }
        this.this$0.setScrollState(0);
        this.this$0.stopNestedScroll(1);
    }

    public void smoothScrollBy(int i, int i2, int i3, Interpolator interpolator) {
        int i4;
        if (i3 == Integer.MIN_VALUE) {
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            boolean z = abs > abs2;
            int sqrt = (int) Math.sqrt((double) 0);
            int sqrt2 = (int) Math.sqrt((double) ((i2 * i2) + (i * i)));
            RecyclerView recyclerView = this.this$0;
            int width = z ? recyclerView.getWidth() : recyclerView.getHeight();
            int i5 = width / 2;
            float f = (float) width;
            float f2 = (float) i5;
            float sin = (((float) Math.sin((double) ((Math.min(1.0f, (((float) sqrt2) * 1.0f) / f) - 0.5f) * 0.47123894f))) * f2) + f2;
            if (sqrt > 0) {
                i4 = Math.round(Math.abs(sin / ((float) sqrt)) * 1000.0f) * 4;
            } else {
                if (!z) {
                    abs = abs2;
                }
                i4 = (int) (((((float) abs) / f) + 1.0f) * 300.0f);
            }
            i3 = Math.min(i4, 2000);
        }
        int i6 = i3;
        if (interpolator == null) {
            interpolator = RecyclerView.sQuinticInterpolator;
        }
        if (this.mInterpolator != interpolator) {
            this.mInterpolator = interpolator;
            this.f661Ys = new OverScroller(this.this$0.getContext(), interpolator);
        }
        this.f660Xs = 0;
        this.f659Ws = 0;
        this.this$0.setScrollState(2);
        this.f661Ys.startScroll(0, 0, i, i2, i6);
        int i7 = Build.VERSION.SDK_INT;
        postOnAnimation();
    }

    public void stop() {
        this.this$0.removeCallbacks(this);
        this.f661Ys.abortAnimation();
    }
}
