package p000;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ListView;

/* renamed from: nl */
/* compiled from: PG */
public final class C0369nl implements View.OnTouchListener {

    /* renamed from: q */
    private static final int f15276q = ViewConfiguration.getTapTimeout();

    /* renamed from: a */
    public final C0367nj f15277a = new C0367nj();

    /* renamed from: b */
    public final View f15278b;

    /* renamed from: c */
    public boolean f15279c;

    /* renamed from: d */
    public boolean f15280d;

    /* renamed from: e */
    public boolean f15281e;

    /* renamed from: f */
    public final ListView f15282f;

    /* renamed from: g */
    private final Interpolator f15283g = new AccelerateInterpolator();

    /* renamed from: h */
    private Runnable f15284h;

    /* renamed from: i */
    private final float[] f15285i = {0.0f, 0.0f};

    /* renamed from: j */
    private final float[] f15286j = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: k */
    private final int f15287k;

    /* renamed from: l */
    private final float[] f15288l = {0.0f, 0.0f};

    /* renamed from: m */
    private final float[] f15289m = {0.0f, 0.0f};

    /* renamed from: n */
    private final float[] f15290n = {Float.MAX_VALUE, Float.MAX_VALUE};

    /* renamed from: o */
    private boolean f15291o;

    /* renamed from: p */
    private boolean f15292p;

    /* renamed from: a */
    private final float m14809a(float f, float f2) {
        if (f2 != 0.0f && f < f2) {
            if (f >= 0.0f) {
                return 1.0f - (f / f2);
            }
            if (this.f15281e) {
                return 1.0f;
            }
        }
        return 0.0f;
    }

    /* renamed from: a */
    static float m14810a(float f, float f2, float f3) {
        return f <= f3 ? f < f2 ? f2 : f : f3;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0061 A[RETURN] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final float m14811a(int r4, float r5, float r6, float r7) {
        /*
            r3 = this;
            float[] r0 = r3.f15285i
            r0 = r0[r4]
            float r0 = r0 * r6
            float[] r1 = r3.f15286j
            r1 = r1[r4]
            r2 = 0
            float r0 = m14810a(r0, r2, r1)
            float r1 = r3.m14809a(r5, r0)
            float r6 = r6 - r5
            float r5 = r3.m14809a(r6, r0)
            float r5 = r5 - r1
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 >= 0) goto L_0x0026
            android.view.animation.Interpolator r6 = r3.f15283g
            float r5 = -r5
            float r5 = r6.getInterpolation(r5)
            float r5 = -r5
            goto L_0x0030
        L_0x0026:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 <= 0) goto L_0x0039
            android.view.animation.Interpolator r6 = r3.f15283g
            float r5 = r6.getInterpolation(r5)
        L_0x0030:
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            r0 = 1065353216(0x3f800000, float:1.0)
            float r5 = m14810a(r5, r6, r0)
            goto L_0x003b
        L_0x0039:
            r5 = 0
        L_0x003b:
            int r6 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0061
            float[] r6 = r3.f15288l
            r6 = r6[r4]
            float[] r0 = r3.f15289m
            r0 = r0[r4]
            float[] r1 = r3.f15290n
            r4 = r1[r4]
            float r6 = r6 * r7
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 <= 0) goto L_0x0058
            float r5 = r5 * r6
            float r4 = m14810a(r5, r0, r4)
            return r4
        L_0x0058:
            float r5 = -r5
            float r5 = r5 * r6
            float r4 = m14810a(r5, r0, r4)
            float r4 = -r4
            return r4
        L_0x0061:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0369nl.m14811a(int, float, float, float):float");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        if (r0 != 3) goto L_0x0080;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean onTouch(android.view.View r6, android.view.MotionEvent r7) {
        /*
            r5 = this;
            boolean r0 = r5.f15292p
            r1 = 0
            if (r0 == 0) goto L_0x0081
            int r0 = r7.getActionMasked()
            r2 = 1
            if (r0 == 0) goto L_0x0019
            if (r0 == r2) goto L_0x0015
            r3 = 2
            if (r0 == r3) goto L_0x001e
            r6 = 3
            if (r0 == r6) goto L_0x0015
            goto L_0x007f
        L_0x0015:
            r5.m14812b()
            goto L_0x007f
        L_0x0019:
            r5.f15280d = r2
            r5.f15291o = r1
        L_0x001e:
            float r0 = r7.getX()
            int r3 = r6.getWidth()
            android.view.View r4 = r5.f15278b
            int r4 = r4.getWidth()
            float r3 = (float) r3
            float r4 = (float) r4
            float r0 = r5.m14811a(r1, r0, r3, r4)
            float r7 = r7.getY()
            int r6 = r6.getHeight()
            android.view.View r3 = r5.f15278b
            int r3 = r3.getHeight()
            float r6 = (float) r6
            float r3 = (float) r3
            float r6 = r5.m14811a(r2, r7, r6, r3)
            nj r7 = r5.f15277a
            r7.f15266c = r0
            r7.f15267d = r6
            boolean r6 = r5.f15281e
            if (r6 != 0) goto L_0x007f
            boolean r6 = r5.mo9459a()
            if (r6 == 0) goto L_0x007f
            java.lang.Runnable r6 = r5.f15284h
            if (r6 != 0) goto L_0x0061
            nk r6 = new nk
            r6.<init>(r5)
            r5.f15284h = r6
        L_0x0061:
            r5.f15281e = r2
            r5.f15279c = r2
            boolean r6 = r5.f15291o
            if (r6 != 0) goto L_0x0077
            int r6 = r5.f15287k
            if (r6 <= 0) goto L_0x0077
            android.view.View r7 = r5.f15278b
            java.lang.Runnable r0 = r5.f15284h
            long r3 = (long) r6
            p000.C0340mj.m14696a((android.view.View) r7, (java.lang.Runnable) r0, (long) r3)
            goto L_0x007c
        L_0x0077:
            java.lang.Runnable r6 = r5.f15284h
            r6.run()
        L_0x007c:
            r5.f15291o = r2
        L_0x007f:
            return r1
        L_0x0081:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0369nl.onTouch(android.view.View, android.view.MotionEvent):boolean");
    }

    /* renamed from: b */
    private final void m14812b() {
        int i = 0;
        if (!this.f15279c) {
            C0367nj njVar = this.f15277a;
            long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
            int i2 = (int) (currentAnimationTimeMillis - njVar.f15268e);
            int i3 = njVar.f15265b;
            if (i2 > i3) {
                i = i3;
            } else if (i2 >= 0) {
                i = i2;
            }
            njVar.f15274k = i;
            njVar.f15273j = njVar.mo9456a(currentAnimationTimeMillis);
            njVar.f15272i = currentAnimationTimeMillis;
            return;
        }
        this.f15281e = false;
    }

    /* renamed from: a */
    public final void mo9458a(boolean z) {
        if (this.f15292p && !z) {
            m14812b();
        }
        this.f15292p = z;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo9459a() {
        ListView listView;
        int count;
        C0367nj njVar = this.f15277a;
        float f = njVar.f15267d;
        int abs = (int) (f / Math.abs(f));
        float f2 = njVar.f15266c;
        int abs2 = (int) (f2 / Math.abs(f2));
        if (!(abs == 0 || (count = listView.getCount()) == 0)) {
            int childCount = (listView = this.f15282f).getChildCount();
            int firstVisiblePosition = listView.getFirstVisiblePosition();
            int i = firstVisiblePosition + childCount;
            if (abs > 0 ? i < count || listView.getChildAt(childCount - 1).getBottom() > listView.getHeight() : abs < 0 && (firstVisiblePosition > 0 || listView.getChildAt(0).getTop() < 0)) {
                return true;
            }
        }
        return abs2 == 0 ? false : false;
    }

    public C0369nl(ListView listView) {
        this.f15278b = listView;
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        float f = displayMetrics.density;
        float f2 = displayMetrics.density;
        float[] fArr = this.f15290n;
        float f3 = ((float) ((int) ((f * 1575.0f) + 0.5f))) / 1000.0f;
        fArr[0] = f3;
        fArr[1] = f3;
        float[] fArr2 = this.f15289m;
        float f4 = ((float) ((int) ((f2 * 315.0f) + 0.5f))) / 1000.0f;
        fArr2[0] = f4;
        fArr2[1] = f4;
        float[] fArr3 = this.f15286j;
        fArr3[0] = Float.MAX_VALUE;
        fArr3[1] = Float.MAX_VALUE;
        float[] fArr4 = this.f15285i;
        fArr4[0] = 0.2f;
        fArr4[1] = 0.2f;
        float[] fArr5 = this.f15288l;
        fArr5[0] = 0.001f;
        fArr5[1] = 0.001f;
        this.f15287k = f15276q;
        C0367nj njVar = this.f15277a;
        njVar.f15264a = 500;
        njVar.f15265b = 500;
        this.f15282f = listView;
    }
}
