package p000;

import android.content.Context;
import android.view.ScaleGestureDetector;

/* renamed from: isr */
/* compiled from: PG */
public final class isr extends isp {

    /* renamed from: j */
    private final ScaleGestureDetector f15050j;

    public isr(Context context) {
        super(context);
        this.f15050j = new ScaleGestureDetector(context, new isq(this));
    }

    /* renamed from: a */
    public final boolean mo9103a() {
        return this.f15050j.isInProgress();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a6, code lost:
        if (r9 != false) goto L_0x00a8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00fd, code lost:
        if (r5 <= -1.0f) goto L_0x00ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01cd, code lost:
        if (r13 != r0) goto L_0x01cf;
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo9105c(android.view.MotionEvent r23) {
        /*
            r22 = this;
            r1 = r22
            r0 = r23
            r2 = 1
            android.view.ScaleGestureDetector r3 = r1.f15050j     // Catch:{ IllegalArgumentException -> 0x0211 }
            r3.onTouchEvent(r0)     // Catch:{ IllegalArgumentException -> 0x0211 }
            int r3 = r23.getAction()     // Catch:{ IllegalArgumentException -> 0x0211 }
            r3 = r3 & 255(0xff, float:3.57E-43)
            r4 = -1
            r5 = 3
            r6 = 0
            if (r3 == 0) goto L_0x004c
            if (r3 == r2) goto L_0x0048
            if (r3 == r5) goto L_0x0048
            r7 = 6
            if (r3 == r7) goto L_0x001d
            goto L_0x0053
        L_0x001d:
            int r3 = r23.getAction()     // Catch:{ IllegalArgumentException -> 0x0211 }
            int r3 = r3 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ IllegalArgumentException -> 0x0211 }
            int r7 = r0.getPointerId(r3)     // Catch:{ IllegalArgumentException -> 0x0211 }
            int r8 = r1.f15047h     // Catch:{ IllegalArgumentException -> 0x0211 }
            if (r7 != r8) goto L_0x0053
            if (r3 != 0) goto L_0x0033
            r3 = 1
            goto L_0x0035
        L_0x0033:
            r3 = 0
        L_0x0035:
            int r7 = r0.getPointerId(r3)     // Catch:{ IllegalArgumentException -> 0x0211 }
            r1.f15047h = r7     // Catch:{ IllegalArgumentException -> 0x0211 }
            float r7 = r0.getX(r3)     // Catch:{ IllegalArgumentException -> 0x0211 }
            r1.f15041b = r7     // Catch:{ IllegalArgumentException -> 0x0211 }
            float r3 = r0.getY(r3)     // Catch:{ IllegalArgumentException -> 0x0211 }
            r1.f15042c = r3     // Catch:{ IllegalArgumentException -> 0x0211 }
            goto L_0x0053
        L_0x0048:
            r1.f15047h = r4     // Catch:{ IllegalArgumentException -> 0x0211 }
            goto L_0x0053
        L_0x004c:
            int r3 = r0.getPointerId(r6)     // Catch:{ IllegalArgumentException -> 0x0211 }
            r1.f15047h = r3     // Catch:{ IllegalArgumentException -> 0x0211 }
        L_0x0053:
            int r3 = r1.f15047h     // Catch:{ IllegalArgumentException -> 0x0211 }
            if (r3 == r4) goto L_0x0058
            goto L_0x005a
        L_0x0058:
            r3 = 0
        L_0x005a:
            int r3 = r0.findPointerIndex(r3)     // Catch:{ IllegalArgumentException -> 0x0211 }
            r1.f15048i = r3     // Catch:{ IllegalArgumentException -> 0x0211 }
            int r3 = r23.getAction()     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r3 == 0) goto L_0x01f1
            r4 = 0
            if (r3 == r2) goto L_0x0117
            r7 = 2
            if (r3 == r7) goto L_0x007b
            if (r3 == r5) goto L_0x0070
            goto L_0x020f
        L_0x0070:
            android.view.VelocityTracker r0 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r0 == 0) goto L_0x020f
            r0.recycle()     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15045f = r4     // Catch:{ IllegalArgumentException -> 0x020e }
            goto L_0x020f
        L_0x007b:
            float r3 = r22.mo9102a(r23)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r4 = r22.mo9104b(r23)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r5 = r1.f15041b     // Catch:{ IllegalArgumentException -> 0x020e }
            float r5 = r3 - r5
            float r8 = r1.f15042c     // Catch:{ IllegalArgumentException -> 0x020e }
            float r8 = r4 - r8
            boolean r9 = r1.f15046g     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r9 != 0) goto L_0x00a8
            float r9 = r5 * r5
            float r10 = r8 * r8
            float r9 = r9 + r10
            double r9 = (double) r9     // Catch:{ IllegalArgumentException -> 0x020e }
            double r9 = java.lang.Math.sqrt(r9)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r11 = r1.f15043d     // Catch:{ IllegalArgumentException -> 0x020e }
            double r11 = (double) r11     // Catch:{ IllegalArgumentException -> 0x020e }
            int r13 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r13 < 0) goto L_0x00a2
            r9 = 1
            goto L_0x00a4
        L_0x00a2:
            r9 = 0
        L_0x00a4:
            r1.f15046g = r9     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r9 == 0) goto L_0x020f
        L_0x00a8:
            ist r9 = r1.f15040a     // Catch:{ IllegalArgumentException -> 0x020e }
            r10 = r9
            isn r10 = (p000.isn) r10     // Catch:{ IllegalArgumentException -> 0x020e }
            iss r10 = r10.f15022h     // Catch:{ IllegalArgumentException -> 0x020e }
            boolean r10 = r10.mo9103a()     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r10 != 0) goto L_0x010a
            r10 = r9
            isn r10 = (p000.isn) r10     // Catch:{ IllegalArgumentException -> 0x020e }
            android.widget.ImageView r10 = r10.mo9095c()     // Catch:{ IllegalArgumentException -> 0x020e }
            r11 = r9
            isn r11 = (p000.isn) r11     // Catch:{ IllegalArgumentException -> 0x020e }
            android.graphics.Matrix r11 = r11.f15023i     // Catch:{ IllegalArgumentException -> 0x020e }
            r11.postTranslate(r5, r8)     // Catch:{ IllegalArgumentException -> 0x020e }
            r8 = r9
            isn r8 = (p000.isn) r8     // Catch:{ IllegalArgumentException -> 0x020e }
            r8.mo9099g()     // Catch:{ IllegalArgumentException -> 0x020e }
            android.view.ViewParent r8 = r10.getParent()     // Catch:{ IllegalArgumentException -> 0x020e }
            r10 = r9
            isn r10 = (p000.isn) r10     // Catch:{ IllegalArgumentException -> 0x020e }
            boolean r10 = r10.f15020f     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r10 != 0) goto L_0x00d6
            goto L_0x0105
        L_0x00d6:
            r10 = r9
            isn r10 = (p000.isn) r10     // Catch:{ IllegalArgumentException -> 0x020e }
            iss r10 = r10.f15022h     // Catch:{ IllegalArgumentException -> 0x020e }
            boolean r10 = r10.mo9103a()     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r10 != 0) goto L_0x0105
            r10 = r9
            isn r10 = (p000.isn) r10     // Catch:{ IllegalArgumentException -> 0x020e }
            boolean r10 = r10.f15021g     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r10 != 0) goto L_0x0105
            isn r9 = (p000.isn) r9     // Catch:{ IllegalArgumentException -> 0x020e }
            int r9 = r9.f15027m     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r9 == r7) goto L_0x00ff
            if (r9 != 0) goto L_0x00f7
            r7 = 1065353216(0x3f800000, float:1.0)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 < 0) goto L_0x010a
            goto L_0x00ff
        L_0x00f7:
            if (r9 != r2) goto L_0x010a
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 > 0) goto L_0x010a
        L_0x00ff:
            if (r8 == 0) goto L_0x010a
            r8.requestDisallowInterceptTouchEvent(r6)     // Catch:{ IllegalArgumentException -> 0x020e }
            goto L_0x010a
        L_0x0105:
            if (r8 == 0) goto L_0x010a
            r8.requestDisallowInterceptTouchEvent(r2)     // Catch:{ IllegalArgumentException -> 0x020e }
        L_0x010a:
            r1.f15041b = r3     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15042c = r4     // Catch:{ IllegalArgumentException -> 0x020e }
            android.view.VelocityTracker r3 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r3 == 0) goto L_0x020f
            r3.addMovement(r0)     // Catch:{ IllegalArgumentException -> 0x020e }
            goto L_0x020f
        L_0x0117:
            boolean r3 = r1.f15046g     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r3 == 0) goto L_0x01e7
            android.view.VelocityTracker r3 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r3 == 0) goto L_0x01e7
            float r3 = r22.mo9102a(r23)     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15041b = r3     // Catch:{ IllegalArgumentException -> 0x020e }
            float r3 = r22.mo9104b(r23)     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15042c = r3     // Catch:{ IllegalArgumentException -> 0x020e }
            android.view.VelocityTracker r3 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            r3.addMovement(r0)     // Catch:{ IllegalArgumentException -> 0x020e }
            android.view.VelocityTracker r0 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            r3 = 1000(0x3e8, float:1.401E-42)
            r0.computeCurrentVelocity(r3)     // Catch:{ IllegalArgumentException -> 0x020e }
            android.view.VelocityTracker r0 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            float r0 = r0.getXVelocity()     // Catch:{ IllegalArgumentException -> 0x020e }
            android.view.VelocityTracker r3 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            float r3 = r3.getYVelocity()     // Catch:{ IllegalArgumentException -> 0x020e }
            float r5 = java.lang.Math.abs(r0)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r7 = java.lang.Math.abs(r3)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r5 = java.lang.Math.max(r5, r7)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r7 = r1.f15044e     // Catch:{ IllegalArgumentException -> 0x020e }
            int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r5 < 0) goto L_0x01e7
            ist r5 = r1.f15040a     // Catch:{ IllegalArgumentException -> 0x020e }
            r7 = r5
            isn r7 = (p000.isn) r7     // Catch:{ IllegalArgumentException -> 0x020e }
            android.widget.ImageView r7 = r7.mo9095c()     // Catch:{ IllegalArgumentException -> 0x020e }
            isl r8 = new isl     // Catch:{ IllegalArgumentException -> 0x020e }
            android.content.Context r9 = r7.getContext()     // Catch:{ IllegalArgumentException -> 0x020e }
            r10 = r5
            isn r10 = (p000.isn) r10     // Catch:{ IllegalArgumentException -> 0x020e }
            r8.<init>(r10, r9)     // Catch:{ IllegalArgumentException -> 0x020e }
            r9 = r5
            isn r9 = (p000.isn) r9     // Catch:{ IllegalArgumentException -> 0x020e }
            r9.f15026l = r8     // Catch:{ IllegalArgumentException -> 0x020e }
            r8 = r5
            isn r8 = (p000.isn) r8     // Catch:{ IllegalArgumentException -> 0x020e }
            isl r8 = r8.f15026l     // Catch:{ IllegalArgumentException -> 0x020e }
            int r9 = p000.isn.m14389b((android.widget.ImageView) r7)     // Catch:{ IllegalArgumentException -> 0x020e }
            int r10 = p000.isn.m14387a((android.widget.ImageView) r7)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r0 = -r0
            int r14 = (int) r0     // Catch:{ IllegalArgumentException -> 0x020e }
            float r0 = -r3
            int r15 = (int) r0     // Catch:{ IllegalArgumentException -> 0x020e }
            isn r0 = r8.f15014d     // Catch:{ IllegalArgumentException -> 0x020e }
            android.graphics.RectF r0 = r0.mo9093b()     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r0 == 0) goto L_0x01e0
            float r3 = r0.left     // Catch:{ IllegalArgumentException -> 0x020e }
            float r3 = -r3
            int r12 = java.lang.Math.round(r3)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r3 = (float) r9     // Catch:{ IllegalArgumentException -> 0x020e }
            float r9 = r0.width()     // Catch:{ IllegalArgumentException -> 0x020e }
            int r9 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
            if (r9 >= 0) goto L_0x01a4
            float r9 = r0.width()     // Catch:{ IllegalArgumentException -> 0x020e }
            float r9 = r9 - r3
            int r3 = java.lang.Math.round(r9)     // Catch:{ IllegalArgumentException -> 0x020e }
            r16 = 0
            goto L_0x01a7
        L_0x01a4:
            r3 = r12
            r16 = r3
        L_0x01a7:
            float r9 = r0.top     // Catch:{ IllegalArgumentException -> 0x020e }
            float r9 = -r9
            int r13 = java.lang.Math.round(r9)     // Catch:{ IllegalArgumentException -> 0x020e }
            float r9 = (float) r10     // Catch:{ IllegalArgumentException -> 0x020e }
            float r10 = r0.height()     // Catch:{ IllegalArgumentException -> 0x020e }
            int r10 = (r9 > r10 ? 1 : (r9 == r10 ? 0 : -1))
            if (r10 >= 0) goto L_0x01c3
            float r0 = r0.height()     // Catch:{ IllegalArgumentException -> 0x020e }
            float r0 = r0 - r9
            int r0 = java.lang.Math.round(r0)     // Catch:{ IllegalArgumentException -> 0x020e }
            r18 = 0
            goto L_0x01c6
        L_0x01c3:
            r0 = r13
            r18 = r0
        L_0x01c6:
            r8.f15012b = r12     // Catch:{ IllegalArgumentException -> 0x020e }
            r8.f15013c = r13     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r12 == r3) goto L_0x01cd
            goto L_0x01cf
        L_0x01cd:
            if (r13 == r0) goto L_0x01e0
        L_0x01cf:
            isw r6 = r8.f15011a     // Catch:{ IllegalArgumentException -> 0x020e }
            isu r6 = (p000.isu) r6     // Catch:{ IllegalArgumentException -> 0x020e }
            android.widget.OverScroller r11 = r6.f15051a     // Catch:{ IllegalArgumentException -> 0x020e }
            r20 = 0
            r21 = 0
            r17 = r3
            r19 = r0
            r11.fling(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21)     // Catch:{ IllegalArgumentException -> 0x020e }
        L_0x01e0:
            isn r5 = (p000.isn) r5     // Catch:{ IllegalArgumentException -> 0x020e }
            isl r0 = r5.f15026l     // Catch:{ IllegalArgumentException -> 0x020e }
            r7.post(r0)     // Catch:{ IllegalArgumentException -> 0x020e }
        L_0x01e7:
            android.view.VelocityTracker r0 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r0 == 0) goto L_0x020f
            r0.recycle()     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15045f = r4     // Catch:{ IllegalArgumentException -> 0x020e }
            goto L_0x020f
        L_0x01f1:
            android.view.VelocityTracker r3 = android.view.VelocityTracker.obtain()     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15045f = r3     // Catch:{ IllegalArgumentException -> 0x020e }
            android.view.VelocityTracker r3 = r1.f15045f     // Catch:{ IllegalArgumentException -> 0x020e }
            if (r3 != 0) goto L_0x01fc
            goto L_0x01ff
        L_0x01fc:
            r3.addMovement(r0)     // Catch:{ IllegalArgumentException -> 0x020e }
        L_0x01ff:
            float r3 = r22.mo9102a(r23)     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15041b = r3     // Catch:{ IllegalArgumentException -> 0x020e }
            float r0 = r22.mo9104b(r23)     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15042c = r0     // Catch:{ IllegalArgumentException -> 0x020e }
            r1.f15046g = r6     // Catch:{ IllegalArgumentException -> 0x020e }
            goto L_0x020f
        L_0x020e:
            r0 = move-exception
        L_0x020f:
            return r2
        L_0x0211:
            r0 = move-exception
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.isr.mo9105c(android.view.MotionEvent):boolean");
    }
}
