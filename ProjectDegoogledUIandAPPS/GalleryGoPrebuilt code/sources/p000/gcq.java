package p000;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.OverScroller;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* renamed from: gcq */
/* compiled from: PG */
public class gcq extends gct {

    /* renamed from: a */
    private Runnable f10954a;

    /* renamed from: b */
    public OverScroller f10955b;

    /* renamed from: c */
    private boolean f10956c;

    /* renamed from: d */
    private int f10957d = -1;

    /* renamed from: e */
    private int f10958e;

    /* renamed from: f */
    private int f10959f = -1;

    /* renamed from: g */
    private VelocityTracker f10960g;

    public gcq() {
    }

    /* renamed from: a */
    public void mo3600a(CoordinatorLayout coordinatorLayout, View view) {
    }

    /* renamed from: b */
    public int mo3601b() {
        throw null;
    }

    /* renamed from: b */
    public int mo3602b(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        throw null;
    }

    /* renamed from: e */
    public int mo3603e(View view) {
        throw null;
    }

    /* renamed from: f */
    public int mo3604f(View view) {
        throw null;
    }

    /* renamed from: g */
    public boolean mo3605g(View view) {
        throw null;
    }

    public gcq(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    public final boolean mo91a(CoordinatorLayout coordinatorLayout, View view, MotionEvent motionEvent) {
        boolean z;
        int findPointerIndex;
        if (this.f10959f < 0) {
            this.f10959f = ViewConfiguration.get(coordinatorLayout.getContext()).getScaledTouchSlop();
        }
        if (motionEvent.getActionMasked() == 2 && this.f10956c) {
            int i = this.f10957d;
            if (i == -1 || (findPointerIndex = motionEvent.findPointerIndex(i)) == -1) {
                return false;
            }
            int y = (int) motionEvent.getY(findPointerIndex);
            if (Math.abs(y - this.f10958e) > this.f10959f) {
                this.f10958e = y;
                return true;
            }
        }
        if (motionEvent.getActionMasked() == 0) {
            this.f10957d = -1;
            int x = (int) motionEvent.getX();
            int y2 = (int) motionEvent.getY();
            if (!mo3605g(view) || !coordinatorLayout.mo1122a(view, x, y2)) {
                z = false;
            } else {
                z = true;
            }
            this.f10956c = z;
            if (z) {
                this.f10958e = y2;
                this.f10957d = motionEvent.getPointerId(0);
                if (this.f10960g == null) {
                    this.f10960g = VelocityTracker.obtain();
                }
                OverScroller overScroller = this.f10955b;
                if (overScroller != null && !overScroller.isFinished()) {
                    this.f10955b.abortAnimation();
                    return true;
                }
            }
        }
        VelocityTracker velocityTracker = this.f10960g;
        if (velocityTracker != null) {
            velocityTracker.addMovement(motionEvent);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00d7  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e8 A[ADDED_TO_REGION] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo95b(androidx.coordinatorlayout.widget.CoordinatorLayout r20, android.view.View r21, android.view.MotionEvent r22) {
        /*
            r19 = this;
            r6 = r19
            r2 = r21
            r7 = r22
            int r0 = r22.getActionMasked()
            r1 = 0
            r3 = -1
            r8 = 1
            r9 = 0
            if (r0 == r8) goto L_0x0063
            r4 = 2
            if (r0 == r4) goto L_0x003d
            r2 = 3
            if (r0 == r2) goto L_0x003a
            r1 = 6
            if (r0 == r1) goto L_0x001c
            r0 = 0
            goto L_0x00dc
        L_0x001c:
            int r0 = r22.getActionIndex()
            if (r0 != 0) goto L_0x0024
            r0 = 1
            goto L_0x0026
        L_0x0024:
            r0 = 0
        L_0x0026:
            int r1 = r7.getPointerId(r0)
            r6.f10957d = r1
            float r0 = r7.getY(r0)
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 + r1
            int r0 = (int) r0
            r6.f10958e = r0
            r0 = 0
            goto L_0x00dc
        L_0x003a:
            r0 = 0
            goto L_0x00ce
        L_0x003d:
            int r0 = r6.f10957d
            int r0 = r7.findPointerIndex(r0)
            if (r0 == r3) goto L_0x0062
            float r0 = r7.getY(r0)
            int r0 = (int) r0
            int r1 = r6.f10958e
            r6.f10958e = r0
            int r3 = r1 - r0
            int r4 = r6.mo3604f(r2)
            r5 = 0
            r0 = r19
            r1 = r20
            r2 = r21
            r0.mo6400c(r1, r2, r3, r4, r5)
            r0 = 0
            goto L_0x00dc
        L_0x0062:
            return r9
        L_0x0063:
            android.view.VelocityTracker r0 = r6.f10960g
            if (r0 == 0) goto L_0x00cc
            r0.addMovement(r7)
            android.view.VelocityTracker r0 = r6.f10960g
            r4 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r4)
            android.view.VelocityTracker r0 = r6.f10960g
            int r4 = r6.f10957d
            float r0 = r0.getYVelocity(r4)
            int r4 = r6.mo3603e(r2)
            int r4 = -r4
            java.lang.Runnable r5 = r6.f10954a
            if (r5 == 0) goto L_0x0087
            r2.removeCallbacks(r5)
            r6.f10954a = r1
        L_0x0087:
            android.widget.OverScroller r5 = r6.f10955b
            if (r5 == 0) goto L_0x008c
            goto L_0x0097
        L_0x008c:
            android.widget.OverScroller r5 = new android.widget.OverScroller
            android.content.Context r10 = r21.getContext()
            r5.<init>(r10)
            r6.f10955b = r5
        L_0x0097:
            android.widget.OverScroller r10 = r6.f10955b
            r11 = 0
            int r12 = r19.mo6403c()
            r13 = 0
            int r14 = java.lang.Math.round(r0)
            r15 = 0
            r16 = 0
            r18 = 0
            r17 = r4
            r10.fling(r11, r12, r13, r14, r15, r16, r17, r18)
            android.widget.OverScroller r0 = r6.f10955b
            boolean r0 = r0.computeScrollOffset()
            if (r0 == 0) goto L_0x00c4
            gcp r0 = new gcp
            r4 = r20
            r0.<init>(r6, r4, r2)
            r6.f10954a = r0
            p000.C0340mj.m14695a((android.view.View) r2, (java.lang.Runnable) r0)
            r0 = 1
            goto L_0x00ce
        L_0x00c4:
            r4 = r20
            r19.mo3600a(r20, r21)
            r0 = 1
            goto L_0x00ce
        L_0x00cc:
            goto L_0x003a
        L_0x00ce:
            r6.f10956c = r9
            r6.f10957d = r3
            android.view.VelocityTracker r2 = r6.f10960g
            if (r2 == 0) goto L_0x00dc
            r2.recycle()
            r6.f10960g = r1
        L_0x00dc:
            android.view.VelocityTracker r1 = r6.f10960g
            if (r1 != 0) goto L_0x00e1
            goto L_0x00e4
        L_0x00e1:
            r1.addMovement(r7)
        L_0x00e4:
            boolean r1 = r6.f10956c
            if (r1 != 0) goto L_0x00eb
            if (r0 != 0) goto L_0x00eb
            return r9
        L_0x00eb:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.gcq.mo95b(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    /* renamed from: c */
    public final int mo6400c(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        return mo3602b(coordinatorLayout, view, mo3601b() - i, i2, i3);
    }

    /* renamed from: b */
    public final void mo6399b(CoordinatorLayout coordinatorLayout, View view, int i) {
        mo3602b(coordinatorLayout, view, i, RecyclerView.UNDEFINED_DURATION, Integer.MAX_VALUE);
    }
}
