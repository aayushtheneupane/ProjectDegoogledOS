package p000;

import android.view.View;
import android.view.ViewConfiguration;

/* renamed from: vq */
/* compiled from: PG */
public abstract class C0590vq implements View.OnTouchListener, View.OnAttachStateChangeListener {

    /* renamed from: a */
    public final View f16142a;

    /* renamed from: b */
    public boolean f16143b;

    /* renamed from: c */
    private final float f16144c;

    /* renamed from: d */
    private final int f16145d;

    /* renamed from: e */
    private final int f16146e;

    /* renamed from: f */
    private Runnable f16147f;

    /* renamed from: g */
    private Runnable f16148g;

    /* renamed from: h */
    private int f16149h;

    /* renamed from: i */
    private final int[] f16150i = new int[2];

    public C0590vq(View view) {
        this.f16142a = view;
        view.setLongClickable(true);
        view.addOnAttachStateChangeListener(this);
        this.f16144c = (float) ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        int tapTimeout = ViewConfiguration.getTapTimeout();
        this.f16145d = tapTimeout;
        this.f16146e = (tapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    /* renamed from: a */
    public abstract C0490ry mo9782a();

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo9783b() {
        throw null;
    }

    public final void onViewAttachedToWindow(View view) {
    }

    /* renamed from: d */
    public final void mo10399d() {
        Runnable runnable = this.f16148g;
        if (runnable != null) {
            this.f16142a.removeCallbacks(runnable);
        }
        Runnable runnable2 = this.f16147f;
        if (runnable2 != null) {
            this.f16142a.removeCallbacks(runnable2);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public boolean mo10066c() {
        C0490ry a = mo9782a();
        if (a == null || !a.mo9811e()) {
            return true;
        }
        a.mo9810d();
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005c, code lost:
        if (r13 != false) goto L_0x0068;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007e, code lost:
        if (r4 != 3) goto L_0x0106;
     */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0109  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouch(android.view.View r12, android.view.MotionEvent r13) {
        /*
            r11 = this;
            boolean r12 = r11.f16143b
            r0 = 3
            r1 = 1
            r2 = 0
            if (r12 == 0) goto L_0x006b
            android.view.View r3 = r11.f16142a
            ry r4 = r11.mo9782a()
            if (r4 == 0) goto L_0x005e
            boolean r5 = r4.mo9811e()
            if (r5 == 0) goto L_0x005e
            android.widget.ListView r4 = r4.mo9806ac()
            vi r4 = (p000.C0582vi) r4
            if (r4 == 0) goto L_0x005e
            boolean r5 = r4.isShown()
            if (r5 == 0) goto L_0x005e
            android.view.MotionEvent r5 = android.view.MotionEvent.obtainNoHistory(r13)
            int[] r6 = r11.f16150i
            r3.getLocationOnScreen(r6)
            r3 = r6[r2]
            float r3 = (float) r3
            r6 = r6[r1]
            float r6 = (float) r6
            r5.offsetLocation(r3, r6)
            int[] r3 = r11.f16150i
            r4.getLocationOnScreen(r3)
            r6 = r3[r2]
            int r6 = -r6
            float r6 = (float) r6
            r3 = r3[r1]
            int r3 = -r3
            float r3 = (float) r3
            r5.offsetLocation(r6, r3)
            int r3 = r11.f16149h
            boolean r3 = r4.mo10377a((android.view.MotionEvent) r5, (int) r3)
            r5.recycle()
            int r13 = r13.getActionMasked()
            if (r13 != r1) goto L_0x0056
        L_0x0054:
            r13 = 0
            goto L_0x005a
        L_0x0056:
            if (r13 == r0) goto L_0x0054
            r13 = 1
        L_0x005a:
            if (r3 == 0) goto L_0x005e
            if (r13 != 0) goto L_0x0068
        L_0x005e:
            boolean r13 = r11.mo10066c()
            if (r13 == 0) goto L_0x0067
            r13 = 0
            goto L_0x011e
        L_0x0067:
        L_0x0068:
            r13 = 1
            goto L_0x011e
        L_0x006b:
            android.view.View r3 = r11.f16142a
            boolean r4 = r3.isEnabled()
            if (r4 == 0) goto L_0x0105
            int r4 = r13.getActionMasked()
            if (r4 == 0) goto L_0x00d5
            if (r4 == r1) goto L_0x00cf
            r5 = 2
            if (r4 == r5) goto L_0x0082
            if (r4 == r0) goto L_0x00cf
            goto L_0x0106
        L_0x0082:
            int r0 = r11.f16149h
            int r0 = r13.findPointerIndex(r0)
            if (r0 < 0) goto L_0x0106
            float r4 = r13.getX(r0)
            float r13 = r13.getY(r0)
            float r0 = r11.f16144c
            float r5 = -r0
            int r6 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r6 >= 0) goto L_0x009a
            goto L_0x00bd
        L_0x009a:
            int r5 = (r13 > r5 ? 1 : (r13 == r5 ? 0 : -1))
            if (r5 < 0) goto L_0x00bd
            int r5 = r3.getRight()
            int r6 = r3.getLeft()
            int r5 = r5 - r6
            float r5 = (float) r5
            float r5 = r5 + r0
            int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
            if (r4 >= 0) goto L_0x00bd
            int r4 = r3.getBottom()
            int r5 = r3.getTop()
            int r4 = r4 - r5
            float r4 = (float) r4
            float r4 = r4 + r0
            int r13 = (r13 > r4 ? 1 : (r13 == r4 ? 0 : -1))
            if (r13 >= 0) goto L_0x00bd
            goto L_0x0106
        L_0x00bd:
            r11.mo10399d()
            android.view.ViewParent r13 = r3.getParent()
            r13.requestDisallowInterceptTouchEvent(r1)
            boolean r13 = r11.mo9783b()
            if (r13 == 0) goto L_0x0106
            r13 = 1
            goto L_0x0107
        L_0x00cf:
            r11.mo10399d()
            r13 = 0
            goto L_0x0107
        L_0x00d5:
            int r13 = r13.getPointerId(r2)
            r11.f16149h = r13
            java.lang.Runnable r13 = r11.f16147f
            if (r13 != 0) goto L_0x00e7
            vo r13 = new vo
            r13.<init>(r11)
            r11.f16147f = r13
        L_0x00e7:
            java.lang.Runnable r13 = r11.f16147f
            int r0 = r11.f16145d
            long r4 = (long) r0
            r3.postDelayed(r13, r4)
            java.lang.Runnable r13 = r11.f16148g
            if (r13 != 0) goto L_0x00fa
            vp r13 = new vp
            r13.<init>(r11)
            r11.f16148g = r13
        L_0x00fa:
            java.lang.Runnable r13 = r11.f16148g
            int r0 = r11.f16146e
            long r4 = (long) r0
            r3.postDelayed(r13, r4)
            r13 = 0
            goto L_0x0107
        L_0x0105:
        L_0x0106:
            r13 = 0
        L_0x0107:
            if (r13 == 0) goto L_0x011e
            long r5 = android.os.SystemClock.uptimeMillis()
            r7 = 3
            r8 = 0
            r9 = 0
            r10 = 0
            r3 = r5
            android.view.MotionEvent r0 = android.view.MotionEvent.obtain(r3, r5, r7, r8, r9, r10)
            android.view.View r3 = r11.f16142a
            r3.onTouchEvent(r0)
            r0.recycle()
        L_0x011e:
            r11.f16143b = r13
            if (r13 != 0) goto L_0x0125
            if (r12 != 0) goto L_0x0125
            return r2
        L_0x0125:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0590vq.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    public final void onViewDetachedFromWindow(View view) {
        this.f16143b = false;
        this.f16149h = -1;
        Runnable runnable = this.f16147f;
        if (runnable != null) {
            this.f16142a.removeCallbacks(runnable);
        }
    }
}
