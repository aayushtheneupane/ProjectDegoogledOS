package p000;

import android.os.Build;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.OverScroller;

/* renamed from: yl */
/* compiled from: PG */
public final class C0666yl implements Runnable {

    /* renamed from: a */
    public int f16374a;

    /* renamed from: b */
    public int f16375b;

    /* renamed from: c */
    public OverScroller f16376c;

    /* renamed from: d */
    public Interpolator f16377d = RecyclerView.sQuinticInterpolator;

    /* renamed from: e */
    public final /* synthetic */ RecyclerView f16378e;

    /* renamed from: f */
    private boolean f16379f = false;

    /* renamed from: g */
    private boolean f16380g = false;

    public C0666yl(RecyclerView recyclerView) {
        this.f16378e = recyclerView;
        this.f16376c = new OverScroller(recyclerView.getContext(), RecyclerView.sQuinticInterpolator);
    }

    /* renamed from: c */
    private final void m16113c() {
        this.f16378e.removeCallbacks(this);
        C0340mj.m14695a((View) this.f16378e, (Runnable) this);
    }

    /* renamed from: a */
    public final void mo10630a() {
        if (this.f16379f) {
            this.f16380g = true;
        } else {
            m16113c();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0151  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r22 = this;
            r0 = r22
            android.support.v7.widget.RecyclerView r1 = r0.f16378e
            xt r2 = r1.mLayout
            if (r2 == 0) goto L_0x017b
            r2 = 0
            r0.f16380g = r2
            r3 = 1
            r0.f16379f = r3
            r1.consumePendingUpdateOperations()
            android.widget.OverScroller r1 = r0.f16376c
            boolean r4 = r1.computeScrollOffset()
            if (r4 == 0) goto L_0x0154
            int r4 = r1.getCurrX()
            int r5 = r1.getCurrY()
            int r6 = r0.f16374a
            int r6 = r4 - r6
            int r7 = r0.f16375b
            int r13 = r5 - r7
            r0.f16374a = r4
            r0.f16375b = r5
            android.support.v7.widget.RecyclerView r7 = r0.f16378e
            int[] r10 = r7.mReusableIntPair
            r10[r2] = r2
            r10[r3] = r2
            r11 = 0
            r12 = 1
            r8 = r6
            r9 = r13
            boolean r4 = r7.dispatchNestedPreScroll(r8, r9, r10, r11, r12)
            if (r4 == 0) goto L_0x004a
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            int[] r4 = r4.mReusableIntPair
            r5 = r4[r2]
            int r6 = r6 - r5
            r4 = r4[r3]
            int r13 = r13 - r4
            goto L_0x004b
        L_0x004a:
        L_0x004b:
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            int r4 = r4.getOverScrollMode()
            r5 = 2
            if (r4 != r5) goto L_0x0055
            goto L_0x005a
        L_0x0055:
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            r4.considerReleasingGlowsOnScroll(r6, r13)
        L_0x005a:
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            xg r7 = r4.mAdapter
            if (r7 == 0) goto L_0x00a1
            int[] r7 = r4.mReusableIntPair
            r7[r2] = r2
            r7[r3] = r2
            r4.scrollStep(r6, r13, r7)
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            int[] r7 = r4.mReusableIntPair
            r8 = r7[r2]
            r7 = r7[r3]
            int r6 = r6 - r8
            int r13 = r13 - r7
            xt r9 = r4.mLayout
            yi r9 = r9.f16302m
            if (r9 != 0) goto L_0x007a
        L_0x0079:
            goto L_0x00a3
        L_0x007a:
            boolean r10 = r9.f16345d
            if (r10 != 0) goto L_0x0079
            boolean r10 = r9.f16346e
            if (r10 == 0) goto L_0x0079
            yj r4 = r4.mState
            int r4 = r4.mo10626a()
            if (r4 != 0) goto L_0x008f
            r9.mo10623a()
            goto L_0x00a3
        L_0x008f:
            int r10 = r9.f16342a
            if (r10 < r4) goto L_0x009c
            int r4 = r4 + -1
            r9.f16342a = r4
            r9.mo10624a((int) r8, (int) r7)
            goto L_0x00a3
        L_0x009c:
            r9.mo10624a((int) r8, (int) r7)
            goto L_0x00a3
        L_0x00a1:
            r7 = 0
            r8 = 0
        L_0x00a3:
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            java.util.ArrayList r4 = r4.mItemDecorations
            boolean r4 = r4.isEmpty()
            if (r4 != 0) goto L_0x00b2
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            r4.invalidate()
        L_0x00b2:
            android.support.v7.widget.RecyclerView r14 = r0.f16378e
            int[] r4 = r14.mReusableIntPair
            r4[r2] = r2
            r4[r3] = r2
            r19 = 0
            r20 = 1
            r15 = r8
            r16 = r7
            r17 = r6
            r18 = r13
            r21 = r4
            r14.dispatchNestedScroll(r15, r16, r17, r18, r19, r20, r21)
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            int[] r9 = r4.mReusableIntPair
            r10 = r9[r2]
            int r6 = r6 - r10
            r9 = r9[r3]
            int r13 = r13 - r9
            if (r8 == 0) goto L_0x00d7
            goto L_0x00d9
        L_0x00d7:
            if (r7 == 0) goto L_0x00dc
        L_0x00d9:
            r4.dispatchOnScrolled(r8, r7)
        L_0x00dc:
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            boolean r4 = r4.awakenScrollBars()
            if (r4 != 0) goto L_0x00e9
            android.support.v7.widget.RecyclerView r4 = r0.f16378e
            r4.invalidate()
        L_0x00e9:
            int r4 = r1.getCurrX()
            int r9 = r1.getFinalX()
            int r10 = r1.getCurrY()
            int r11 = r1.getFinalY()
            boolean r12 = r1.isFinished()
            if (r12 == 0) goto L_0x0101
        L_0x00ff:
            r4 = 1
            goto L_0x010e
        L_0x0101:
            if (r4 == r9) goto L_0x0108
            if (r6 == 0) goto L_0x0106
            goto L_0x0108
        L_0x0106:
            r4 = 0
            goto L_0x010e
        L_0x0108:
            if (r10 == r11) goto L_0x00ff
            if (r13 != 0) goto L_0x00ff
            r4 = 0
        L_0x010e:
            android.support.v7.widget.RecyclerView r9 = r0.f16378e
            xt r10 = r9.mLayout
            yi r10 = r10.f16302m
            if (r10 == 0) goto L_0x011a
            boolean r10 = r10.f16345d
            if (r10 != 0) goto L_0x0148
        L_0x011a:
            if (r4 == 0) goto L_0x0148
            int r4 = r9.getOverScrollMode()
            if (r4 == r5) goto L_0x013c
            float r1 = r1.getCurrVelocity()
            int r1 = (int) r1
            if (r6 >= 0) goto L_0x012b
            int r4 = -r1
            goto L_0x0130
        L_0x012b:
            if (r6 <= 0) goto L_0x012f
            r4 = r1
            goto L_0x0130
        L_0x012f:
            r4 = 0
        L_0x0130:
            if (r13 >= 0) goto L_0x0134
            int r1 = -r1
            goto L_0x0137
        L_0x0134:
            if (r13 > 0) goto L_0x0137
            r1 = 0
        L_0x0137:
            android.support.v7.widget.RecyclerView r5 = r0.f16378e
            r5.absorbGlows(r4, r1)
        L_0x013c:
            boolean r1 = android.support.p002v7.widget.RecyclerView.ALLOW_THREAD_GAP_WORK
            if (r1 == 0) goto L_0x0154
            android.support.v7.widget.RecyclerView r1 = r0.f16378e
            vs r1 = r1.mPrefetchRegistry
            r1.mo10409a()
            goto L_0x0154
        L_0x0148:
            r22.mo10630a()
            android.support.v7.widget.RecyclerView r1 = r0.f16378e
            vu r4 = r1.mGapWorker
            if (r4 == 0) goto L_0x0154
            r4.mo10413a((android.support.p002v7.widget.RecyclerView) r1, (int) r8, (int) r7)
        L_0x0154:
            android.support.v7.widget.RecyclerView r1 = r0.f16378e
            xt r1 = r1.mLayout
            yi r1 = r1.f16302m
            if (r1 == 0) goto L_0x0165
            boolean r4 = r1.f16345d
            if (r4 != 0) goto L_0x0161
            goto L_0x0165
        L_0x0161:
            r1.mo10624a((int) r2, (int) r2)
        L_0x0165:
            r0.f16379f = r2
            boolean r1 = r0.f16380g
            if (r1 != 0) goto L_0x0177
            android.support.v7.widget.RecyclerView r1 = r0.f16378e
            r1.setScrollState(r2)
            android.support.v7.widget.RecyclerView r1 = r0.f16378e
            r1.stopNestedScroll(r3)
            return
        L_0x0177:
            r22.m16113c()
            return
        L_0x017b:
            r22.mo10632b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0666yl.run():void");
    }

    /* renamed from: a */
    public final void mo10631a(int i, int i2, int i3, Interpolator interpolator) {
        int i4;
        int i5;
        if (i3 == Integer.MIN_VALUE) {
            int abs = Math.abs(i);
            int abs2 = Math.abs(i2);
            int sqrt = (int) Math.sqrt(0.0d);
            int sqrt2 = (int) Math.sqrt((double) ((i * i) + (i2 * i2)));
            int height = abs <= abs2 ? this.f16378e.getHeight() : this.f16378e.getWidth();
            float f = (float) height;
            float f2 = (float) (height / 2);
            float sin = f2 + (((float) Math.sin((double) ((Math.min(1.0f, ((float) sqrt2) / f) - 8.0f) * 0.47123894f))) * f2);
            if (sqrt > 0) {
                i5 = Math.round(Math.abs(sin / ((float) sqrt)) * 1000.0f) << 2;
            } else {
                if (abs <= abs2) {
                    abs = abs2;
                }
                i5 = (int) (((((float) abs) / f) + 1.0f) * 300.0f);
            }
            i4 = Math.min(i5, RecyclerView.MAX_SCROLL_DURATION);
        } else {
            i4 = i3;
        }
        if (interpolator == null) {
            interpolator = RecyclerView.sQuinticInterpolator;
        }
        if (this.f16377d != interpolator) {
            this.f16377d = interpolator;
            this.f16376c = new OverScroller(this.f16378e.getContext(), interpolator);
        }
        this.f16375b = 0;
        this.f16374a = 0;
        this.f16378e.setScrollState(2);
        this.f16376c.startScroll(0, 0, i, i2, i4);
        int i6 = Build.VERSION.SDK_INT;
        mo10630a();
    }

    /* renamed from: b */
    public final void mo10632b() {
        this.f16378e.removeCallbacks(this);
        this.f16376c.abortAnimation();
    }
}
