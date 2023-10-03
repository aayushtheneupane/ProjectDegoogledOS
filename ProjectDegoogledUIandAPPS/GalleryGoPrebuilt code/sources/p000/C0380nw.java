package p000;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.OverScroller;
import java.util.Arrays;

/* renamed from: nw */
/* compiled from: PG */
public final class C0380nw {

    /* renamed from: u */
    private static final Interpolator f15314u = new C0377nt();

    /* renamed from: a */
    public int f15315a;

    /* renamed from: b */
    public final int f15316b;

    /* renamed from: c */
    public int f15317c = -1;

    /* renamed from: d */
    public View f15318d;

    /* renamed from: e */
    private float[] f15319e;

    /* renamed from: f */
    private float[] f15320f;

    /* renamed from: g */
    private float[] f15321g;

    /* renamed from: h */
    private float[] f15322h;

    /* renamed from: i */
    private int[] f15323i;

    /* renamed from: j */
    private int[] f15324j;

    /* renamed from: k */
    private int[] f15325k;

    /* renamed from: l */
    private int f15326l;

    /* renamed from: m */
    private VelocityTracker f15327m;

    /* renamed from: n */
    private final float f15328n;

    /* renamed from: o */
    private final float f15329o;

    /* renamed from: p */
    private final int f15330p;

    /* renamed from: q */
    private final OverScroller f15331q;

    /* renamed from: r */
    private final C0379nv f15332r;

    /* renamed from: s */
    private boolean f15333s;

    /* renamed from: t */
    private final ViewGroup f15334t;

    /* renamed from: v */
    private final Runnable f15335v = new C0378nu(this);

    /* renamed from: c */
    private final boolean m14857c(int i) {
        return ((1 << i) & this.f15326l) != 0;
    }

    private C0380nw(Context context, ViewGroup viewGroup, C0379nv nvVar) {
        if (nvVar != null) {
            this.f15334t = viewGroup;
            this.f15332r = nvVar;
            ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
            this.f15330p = (int) ((context.getResources().getDisplayMetrics().density * 20.0f) + 0.5f);
            this.f15316b = viewConfiguration.getScaledTouchSlop();
            this.f15328n = (float) viewConfiguration.getScaledMaximumFlingVelocity();
            this.f15329o = (float) viewConfiguration.getScaledMinimumFlingVelocity();
            this.f15331q = new OverScroller(context, f15314u);
            return;
        }
        throw new IllegalArgumentException("Callback may not be null");
    }

    /* renamed from: b */
    private final void m14850b() {
        this.f15317c = -1;
        float[] fArr = this.f15319e;
        if (fArr != null) {
            Arrays.fill(fArr, 0.0f);
            Arrays.fill(this.f15320f, 0.0f);
            Arrays.fill(this.f15321g, 0.0f);
            Arrays.fill(this.f15322h, 0.0f);
            Arrays.fill(this.f15323i, 0);
            Arrays.fill(this.f15324j, 0);
            Arrays.fill(this.f15325k, 0);
            this.f15326l = 0;
        }
        VelocityTracker velocityTracker = this.f15327m;
        if (velocityTracker != null) {
            velocityTracker.recycle();
            this.f15327m = null;
        }
    }

    /* renamed from: a */
    public final void mo9478a(View view, int i) {
        if (view.getParent() == this.f15334t) {
            this.f15318d = view;
            this.f15317c = i;
            this.f15332r.mo6411a(view, i);
            mo9477a(1);
            return;
        }
        throw new IllegalArgumentException("captureChildView: parameter must be a descendant of the ViewDragHelper's tracked parent view (" + this.f15334t + ")");
    }

    /* renamed from: b */
    private static final void m14851b(float f, float f2) {
        Math.abs(f);
        Math.abs(f2);
    }

    /* renamed from: a */
    private final boolean m14847a(View view, float f, float f2) {
        if (view != null) {
            int a = this.f15332r.mo6408a(view);
            int a2 = this.f15332r.mo6464a();
            if (a > 0 && a2 > 0) {
                int i = this.f15316b;
                if ((f * f) + (f2 * f2) <= ((float) (i * i))) {
                    return false;
                }
                return true;
            } else if (a > 0) {
                if (Math.abs(f) <= ((float) this.f15316b)) {
                    return false;
                }
                return true;
            } else if (a2 <= 0 || Math.abs(f2) <= ((float) this.f15316b)) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static final float m14842a(float f, float f2, float f3) {
        float abs = Math.abs(f);
        if (abs < f2) {
            return 0.0f;
        }
        if (abs > f3) {
            return f <= 0.0f ? -f3 : f3;
        }
        return f;
    }

    /* renamed from: b */
    private static final int m14848b(int i, int i2, int i3) {
        int abs = Math.abs(i);
        if (abs < i2) {
            return 0;
        }
        if (abs > i3) {
            return i <= 0 ? -i3 : i3;
        }
        return i;
    }

    /* renamed from: b */
    private final void m14852b(int i) {
        if (this.f15319e != null && m14857c(i)) {
            this.f15319e[i] = 0.0f;
            this.f15320f[i] = 0.0f;
            this.f15321g[i] = 0.0f;
            this.f15322h[i] = 0.0f;
            this.f15323i[i] = 0;
            this.f15324j[i] = 0;
            this.f15325k[i] = 0;
            this.f15326l = ((1 << i) ^ -1) & this.f15326l;
        }
    }

    /* renamed from: a */
    private final int m14843a(int i, int i2, int i3) {
        int i4;
        if (i == 0) {
            return 0;
        }
        int width = this.f15334t.getWidth();
        float f = (float) (width / 2);
        float sin = f + (((float) Math.sin((double) ((Math.min(1.0f, ((float) Math.abs(i)) / ((float) width)) - 8.0f) * 0.47123894f))) * f);
        int abs = Math.abs(i2);
        if (abs > 0) {
            i4 = Math.round(Math.abs(sin / ((float) abs)) * 1000.0f) << 2;
        } else {
            i4 = (int) (((((float) Math.abs(i)) / ((float) i3)) + 1.0f) * 256.0f);
        }
        return Math.min(i4, 600);
    }

    /* renamed from: a */
    public final boolean mo9479a() {
        if (this.f15315a == 2) {
            boolean computeScrollOffset = this.f15331q.computeScrollOffset();
            int currX = this.f15331q.getCurrX();
            int currY = this.f15331q.getCurrY();
            int left = currX - this.f15318d.getLeft();
            int top = currY - this.f15318d.getTop();
            if (left != 0) {
                C0340mj.m14711d(this.f15318d, left);
            }
            if (top != 0) {
                C0340mj.m14708c(this.f15318d, top);
            }
            if (!(left == 0 && top == 0)) {
                this.f15332r.mo6412a(this.f15318d, currX, currY);
            }
            if (computeScrollOffset) {
                if (currX == this.f15331q.getFinalX() && currY == this.f15331q.getFinalY()) {
                    this.f15331q.abortAnimation();
                }
            }
            this.f15334t.post(this.f15335v);
        }
        return this.f15315a == 2;
    }

    /* renamed from: a */
    public static C0380nw m14844a(ViewGroup viewGroup, C0379nv nvVar) {
        return new C0380nw(viewGroup.getContext(), viewGroup, nvVar);
    }

    /* renamed from: a */
    private final void m14845a(float f, float f2) {
        this.f15333s = true;
        this.f15332r.mo6410a(this.f15318d, f, f2);
        this.f15333s = false;
        if (this.f15315a == 1) {
            mo9477a(0);
        }
    }

    /* renamed from: b */
    private final View m14849b(int i, int i2) {
        for (int childCount = this.f15334t.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = this.f15334t.getChildAt(childCount);
            if (i >= childAt.getLeft() && i < childAt.getRight() && i2 >= childAt.getTop() && i2 < childAt.getBottom()) {
                return childAt;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final boolean mo9481a(int i, int i2, int i3, int i4) {
        int left = this.f15318d.getLeft();
        int top = this.f15318d.getTop();
        int i5 = i - left;
        int i6 = i2 - top;
        if (i5 == 0 && i6 == 0) {
            this.f15331q.abortAnimation();
            mo9477a(0);
            return false;
        }
        View view = this.f15318d;
        int b = m14848b(i3, (int) this.f15329o, (int) this.f15328n);
        int b2 = m14848b(i4, (int) this.f15329o, (int) this.f15328n);
        int abs = Math.abs(i5);
        int abs2 = Math.abs(i6);
        int abs3 = Math.abs(b);
        int abs4 = Math.abs(b2);
        int i7 = abs3 + abs4;
        int i8 = abs + abs2;
        this.f15331q.startScroll(left, top, i5, i6, (int) ((((float) m14843a(i5, b, this.f15332r.mo6408a(view))) * (b == 0 ? ((float) abs) / ((float) i8) : ((float) abs3) / ((float) i7))) + (((float) m14843a(i6, b2, this.f15332r.mo6464a())) * (b2 == 0 ? ((float) abs2) / ((float) i8) : ((float) abs4) / ((float) i7)))));
        mo9477a(2);
        return true;
    }

    /* renamed from: d */
    private final boolean m14858d(int i) {
        if (m14857c(i)) {
            return true;
        }
        Log.e("ViewDragHelper", "Ignoring pointerId=" + i + " because ACTION_DOWN was not received for this pointer before ACTION_MOVE. It likely happened because  ViewDragHelper did not receive all the events in the event stream.");
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0068, code lost:
        if (r9.f15317c == -1) goto L_0x006e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x006e, code lost:
        m14854c();
     */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo9483b(android.view.MotionEvent r10) {
        /*
            r9 = this;
            int r0 = r10.getActionMasked()
            int r1 = r10.getActionIndex()
            if (r0 == 0) goto L_0x000b
            goto L_0x000e
        L_0x000b:
            r9.m14850b()
        L_0x000e:
            android.view.VelocityTracker r2 = r9.f15327m
            if (r2 == 0) goto L_0x0013
            goto L_0x0019
        L_0x0013:
            android.view.VelocityTracker r2 = android.view.VelocityTracker.obtain()
            r9.f15327m = r2
        L_0x0019:
            android.view.VelocityTracker r2 = r9.f15327m
            r2.addMovement(r10)
            r2 = 0
            if (r0 == 0) goto L_0x0188
            r3 = 1
            if (r0 == r3) goto L_0x017c
            r4 = 2
            if (r0 == r4) goto L_0x00c2
            r4 = 3
            if (r0 == r4) goto L_0x00b6
            r4 = 5
            if (r0 == r4) goto L_0x0075
            r4 = 6
            if (r0 == r4) goto L_0x0032
            goto L_0x0135
        L_0x0032:
            int r0 = r10.getPointerId(r1)
            int r1 = r9.f15315a
            if (r1 != r3) goto L_0x0071
            int r1 = r9.f15317c
            if (r0 != r1) goto L_0x0071
            int r1 = r10.getPointerCount()
        L_0x0042:
            if (r2 >= r1) goto L_0x006e
            int r3 = r10.getPointerId(r2)
            int r4 = r9.f15317c
            if (r3 == r4) goto L_0x006b
            float r4 = r10.getX(r2)
            float r5 = r10.getY(r2)
            int r4 = (int) r4
            int r5 = (int) r5
            android.view.View r4 = r9.m14849b((int) r4, (int) r5)
            android.view.View r5 = r9.f15318d
            if (r4 == r5) goto L_0x005f
            goto L_0x006b
        L_0x005f:
            boolean r3 = r9.m14853b((android.view.View) r5, (int) r3)
            if (r3 == 0) goto L_0x006b
            int r10 = r9.f15317c
            r1 = -1
            if (r10 != r1) goto L_0x0071
            goto L_0x006e
        L_0x006b:
            int r2 = r2 + 1
            goto L_0x0042
        L_0x006e:
            r9.m14854c()
        L_0x0071:
            r9.m14852b((int) r0)
            return
        L_0x0075:
            int r0 = r10.getPointerId(r1)
            float r2 = r10.getX(r1)
            float r10 = r10.getY(r1)
            r9.m14846a((float) r2, (float) r10, (int) r0)
            int r1 = r9.f15315a
            if (r1 == 0) goto L_0x00ac
            int r1 = (int) r2
            int r10 = (int) r10
            android.view.View r2 = r9.f15318d
            if (r2 == 0) goto L_0x0135
            int r3 = r2.getLeft()
            if (r1 < r3) goto L_0x0135
            int r3 = r2.getRight()
            if (r1 >= r3) goto L_0x0135
            int r1 = r2.getTop()
            if (r10 < r1) goto L_0x0135
            int r1 = r2.getBottom()
            if (r10 >= r1) goto L_0x0135
            android.view.View r10 = r9.f15318d
            r9.m14853b((android.view.View) r10, (int) r0)
            return
        L_0x00ac:
            int r1 = (int) r2
            int r10 = (int) r10
            android.view.View r10 = r9.m14849b((int) r1, (int) r10)
            r9.m14853b((android.view.View) r10, (int) r0)
            return
        L_0x00b6:
            int r10 = r9.f15315a
            if (r10 != r3) goto L_0x00be
            r10 = 0
            r9.m14845a((float) r10, (float) r10)
        L_0x00be:
            r9.m14850b()
            return
        L_0x00c2:
            int r0 = r9.f15315a
            if (r0 != r3) goto L_0x0136
            int r0 = r9.f15317c
            boolean r0 = r9.m14858d(r0)
            if (r0 == 0) goto L_0x0135
            int r0 = r9.f15317c
            int r0 = r10.findPointerIndex(r0)
            float r1 = r10.getX(r0)
            float r0 = r10.getY(r0)
            float[] r2 = r9.f15321g
            int r3 = r9.f15317c
            r2 = r2[r3]
            float r1 = r1 - r2
            int r1 = (int) r1
            float[] r2 = r9.f15322h
            r2 = r2[r3]
            float r0 = r0 - r2
            int r0 = (int) r0
            android.view.View r2 = r9.f15318d
            int r2 = r2.getLeft()
            int r2 = r2 + r1
            android.view.View r3 = r9.f15318d
            int r3 = r3.getTop()
            int r3 = r3 + r0
            android.view.View r4 = r9.f15318d
            int r4 = r4.getLeft()
            android.view.View r5 = r9.f15318d
            int r5 = r5.getTop()
            if (r1 == 0) goto L_0x0115
            nv r6 = r9.f15332r
            android.view.View r7 = r9.f15318d
            int r2 = r6.mo6414c(r7, r2)
            android.view.View r6 = r9.f15318d
            int r4 = r2 - r4
            p000.C0340mj.m14711d(r6, r4)
        L_0x0115:
            if (r0 == 0) goto L_0x0126
            nv r4 = r9.f15332r
            android.view.View r6 = r9.f15318d
            int r3 = r4.mo6415d(r6, r3)
            android.view.View r4 = r9.f15318d
            int r5 = r3 - r5
            p000.C0340mj.m14708c(r4, r5)
        L_0x0126:
            if (r1 == 0) goto L_0x0129
            goto L_0x012b
        L_0x0129:
            if (r0 == 0) goto L_0x0132
        L_0x012b:
            nv r0 = r9.f15332r
            android.view.View r1 = r9.f15318d
            r0.mo6412a((android.view.View) r1, (int) r2, (int) r3)
        L_0x0132:
            r9.m14856c((android.view.MotionEvent) r10)
        L_0x0135:
            return
        L_0x0136:
            int r0 = r10.getPointerCount()
        L_0x013a:
            if (r2 >= r0) goto L_0x0178
            int r1 = r10.getPointerId(r2)
            boolean r4 = r9.m14858d(r1)
            if (r4 == 0) goto L_0x0175
            float r4 = r10.getX(r2)
            float r5 = r10.getY(r2)
            float[] r6 = r9.f15319e
            r6 = r6[r1]
            float r6 = r4 - r6
            float[] r7 = r9.f15320f
            r7 = r7[r1]
            float r7 = r5 - r7
            m14855c(r6, r7)
            int r8 = r9.f15315a
            if (r8 == r3) goto L_0x0178
            int r4 = (int) r4
            int r5 = (int) r5
            android.view.View r4 = r9.m14849b((int) r4, (int) r5)
            boolean r5 = r9.m14847a((android.view.View) r4, (float) r6, (float) r7)
            if (r5 != 0) goto L_0x016e
            goto L_0x0175
        L_0x016e:
            boolean r1 = r9.m14853b((android.view.View) r4, (int) r1)
            if (r1 == 0) goto L_0x0175
            goto L_0x0178
        L_0x0175:
            int r2 = r2 + 1
            goto L_0x013a
        L_0x0178:
            r9.m14856c((android.view.MotionEvent) r10)
            return
        L_0x017c:
            int r10 = r9.f15315a
            if (r10 == r3) goto L_0x0181
            goto L_0x0184
        L_0x0181:
            r9.m14854c()
        L_0x0184:
            r9.m14850b()
            return
        L_0x0188:
            float r0 = r10.getX()
            float r1 = r10.getY()
            int r10 = r10.getPointerId(r2)
            int r2 = (int) r0
            int r3 = (int) r1
            android.view.View r2 = r9.m14849b((int) r2, (int) r3)
            r9.m14846a((float) r0, (float) r1, (int) r10)
            r9.m14853b((android.view.View) r2, (int) r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0380nw.mo9483b(android.view.MotionEvent):void");
    }

    /* renamed from: c */
    private final void m14854c() {
        this.f15327m.computeCurrentVelocity(1000, this.f15328n);
        m14845a(m14842a(this.f15327m.getXVelocity(this.f15317c), this.f15329o, this.f15328n), m14842a(this.f15327m.getYVelocity(this.f15317c), this.f15329o, this.f15328n));
    }

    /* renamed from: c */
    private static final void m14855c(float f, float f2) {
        m14851b(f, f2);
        m14851b(f2, f);
        m14851b(f, f2);
        m14851b(f2, f);
    }

    /* renamed from: a */
    private final void m14846a(float f, float f2, int i) {
        float[] fArr = this.f15319e;
        int i2 = 0;
        if (fArr == null || fArr.length <= i) {
            int i3 = i + 1;
            float[] fArr2 = new float[i3];
            float[] fArr3 = new float[i3];
            float[] fArr4 = new float[i3];
            float[] fArr5 = new float[i3];
            int[] iArr = new int[i3];
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            if (fArr != null) {
                System.arraycopy(fArr, 0, fArr2, 0, fArr.length);
                float[] fArr6 = this.f15320f;
                System.arraycopy(fArr6, 0, fArr3, 0, fArr6.length);
                float[] fArr7 = this.f15321g;
                System.arraycopy(fArr7, 0, fArr4, 0, fArr7.length);
                float[] fArr8 = this.f15322h;
                System.arraycopy(fArr8, 0, fArr5, 0, fArr8.length);
                int[] iArr4 = this.f15323i;
                System.arraycopy(iArr4, 0, iArr, 0, iArr4.length);
                int[] iArr5 = this.f15324j;
                System.arraycopy(iArr5, 0, iArr2, 0, iArr5.length);
                int[] iArr6 = this.f15325k;
                System.arraycopy(iArr6, 0, iArr3, 0, iArr6.length);
            }
            this.f15319e = fArr2;
            this.f15320f = fArr3;
            this.f15321g = fArr4;
            this.f15322h = fArr5;
            this.f15323i = iArr;
            this.f15324j = iArr2;
            this.f15325k = iArr3;
        }
        float[] fArr9 = this.f15319e;
        this.f15321g[i] = f;
        fArr9[i] = f;
        float[] fArr10 = this.f15320f;
        this.f15322h[i] = f2;
        fArr10[i] = f2;
        int[] iArr7 = this.f15323i;
        int i4 = (int) f;
        int i5 = (int) f2;
        if (i4 < this.f15334t.getLeft() + this.f15330p) {
            i2 = 1;
        }
        if (i5 < this.f15334t.getTop() + this.f15330p) {
            i2 |= 4;
        }
        if (i4 > this.f15334t.getRight() - this.f15330p) {
            i2 |= 2;
        }
        if (i5 > this.f15334t.getBottom() - this.f15330p) {
            i2 |= 8;
        }
        iArr7[i] = i2;
        this.f15326l |= 1 << i;
    }

    /* renamed from: c */
    private final void m14856c(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            int pointerId = motionEvent.getPointerId(i);
            if (m14858d(pointerId)) {
                float x = motionEvent.getX(i);
                float y = motionEvent.getY(i);
                this.f15321g[pointerId] = x;
                this.f15322h[pointerId] = y;
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo9477a(int i) {
        this.f15334t.removeCallbacks(this.f15335v);
        if (this.f15315a != i) {
            this.f15315a = i;
            this.f15332r.mo6409a(i);
            if (this.f15315a == 0) {
                this.f15318d = null;
            }
        }
    }

    /* renamed from: a */
    public final boolean mo9480a(int i, int i2) {
        if (this.f15333s) {
            return mo9481a(i, i2, (int) this.f15327m.getXVelocity(this.f15317c), (int) this.f15327m.getYVelocity(this.f15317c));
        }
        throw new IllegalStateException("Cannot settleCapturedViewAt outside of a call to Callback#onViewReleased");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00cc, code lost:
        if (r12 != r11) goto L_0x00d5;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean mo9482a(android.view.MotionEvent r17) {
        /*
            r16 = this;
            r0 = r16
            r1 = r17
            int r2 = r17.getActionMasked()
            int r3 = r17.getActionIndex()
            if (r2 == 0) goto L_0x000f
            goto L_0x0012
        L_0x000f:
            r16.m14850b()
        L_0x0012:
            android.view.VelocityTracker r4 = r0.f15327m
            if (r4 == 0) goto L_0x0017
            goto L_0x001d
        L_0x0017:
            android.view.VelocityTracker r4 = android.view.VelocityTracker.obtain()
            r0.f15327m = r4
        L_0x001d:
            android.view.VelocityTracker r4 = r0.f15327m
            r4.addMovement(r1)
            r4 = 2
            r6 = 1
            if (r2 == 0) goto L_0x00f2
            if (r2 == r6) goto L_0x00ee
            if (r2 == r4) goto L_0x0062
            r7 = 3
            if (r2 == r7) goto L_0x00ee
            r7 = 5
            if (r2 == r7) goto L_0x003e
            r4 = 6
            if (r2 == r4) goto L_0x0035
            goto L_0x0113
        L_0x0035:
            int r1 = r1.getPointerId(r3)
            r0.m14852b((int) r1)
            goto L_0x0113
        L_0x003e:
            int r2 = r1.getPointerId(r3)
            float r7 = r1.getX(r3)
            float r1 = r1.getY(r3)
            r0.m14846a((float) r7, (float) r1, (int) r2)
            int r3 = r0.f15315a
            if (r3 == 0) goto L_0x0113
            if (r3 != r4) goto L_0x0113
            int r3 = (int) r7
            int r1 = (int) r1
            android.view.View r1 = r0.m14849b((int) r3, (int) r1)
            android.view.View r3 = r0.f15318d
            if (r1 != r3) goto L_0x0113
            r0.m14853b((android.view.View) r1, (int) r2)
            goto L_0x0113
        L_0x0062:
            float[] r2 = r0.f15319e
            if (r2 == 0) goto L_0x0113
            float[] r2 = r0.f15320f
            if (r2 == 0) goto L_0x0113
            int r2 = r17.getPointerCount()
            r3 = 0
        L_0x006f:
            if (r3 >= r2) goto L_0x00ea
            int r4 = r1.getPointerId(r3)
            boolean r7 = r0.m14858d(r4)
            if (r7 == 0) goto L_0x00e7
            float r7 = r1.getX(r3)
            float r8 = r1.getY(r3)
            float[] r9 = r0.f15319e
            r9 = r9[r4]
            float r9 = r7 - r9
            float[] r10 = r0.f15320f
            r10 = r10[r4]
            float r10 = r8 - r10
            int r7 = (int) r7
            int r8 = (int) r8
            android.view.View r7 = r0.m14849b((int) r7, (int) r8)
            if (r7 == 0) goto L_0x00a0
            boolean r8 = r0.m14847a((android.view.View) r7, (float) r9, (float) r10)
            if (r8 == 0) goto L_0x009f
            r8 = 1
            goto L_0x00a1
        L_0x009f:
        L_0x00a0:
            r8 = 0
        L_0x00a1:
            if (r8 == 0) goto L_0x00d5
            int r11 = r7.getLeft()
            nv r12 = r0.f15332r
            int r13 = (int) r9
            int r13 = r13 + r11
            int r12 = r12.mo6414c(r7, r13)
            int r13 = r7.getTop()
            nv r14 = r0.f15332r
            int r15 = (int) r10
            int r15 = r15 + r13
            int r14 = r14.mo6415d(r7, r15)
            nv r15 = r0.f15332r
            int r15 = r15.mo6408a((android.view.View) r7)
            nv r5 = r0.f15332r
            int r5 = r5.mo6464a()
            if (r15 == 0) goto L_0x00ce
            if (r15 > 0) goto L_0x00cc
            goto L_0x00d5
        L_0x00cc:
            if (r12 != r11) goto L_0x00d5
        L_0x00ce:
            if (r5 == 0) goto L_0x00ea
            if (r5 <= 0) goto L_0x00d5
            if (r14 != r13) goto L_0x00d5
            goto L_0x00ea
        L_0x00d5:
            m14855c(r9, r10)
            int r5 = r0.f15315a
            if (r5 != r6) goto L_0x00dd
            goto L_0x00ea
        L_0x00dd:
            if (r8 != 0) goto L_0x00e0
            goto L_0x00e7
        L_0x00e0:
            boolean r4 = r0.m14853b((android.view.View) r7, (int) r4)
            if (r4 == 0) goto L_0x00e7
            goto L_0x00ea
        L_0x00e7:
            int r3 = r3 + 1
            goto L_0x006f
        L_0x00ea:
            r16.m14856c((android.view.MotionEvent) r17)
            goto L_0x0113
        L_0x00ee:
            r16.m14850b()
            goto L_0x0113
        L_0x00f2:
            float r2 = r17.getX()
            float r3 = r17.getY()
            r5 = 0
            int r1 = r1.getPointerId(r5)
            r0.m14846a((float) r2, (float) r3, (int) r1)
            int r2 = (int) r2
            int r3 = (int) r3
            android.view.View r2 = r0.m14849b((int) r2, (int) r3)
            android.view.View r3 = r0.f15318d
            if (r2 != r3) goto L_0x0113
            int r3 = r0.f15315a
            if (r3 != r4) goto L_0x0113
            r0.m14853b((android.view.View) r2, (int) r1)
        L_0x0113:
            int r1 = r0.f15315a
            if (r1 == r6) goto L_0x0119
            r1 = 0
            return r1
        L_0x0119:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: p000.C0380nw.mo9482a(android.view.MotionEvent):boolean");
    }

    /* renamed from: b */
    private final boolean m14853b(View view, int i) {
        if (view == this.f15318d && this.f15317c == i) {
            return true;
        }
        if (view == null || !this.f15332r.mo6413b(view, i)) {
            return false;
        }
        this.f15317c = i;
        mo9478a(view, i);
        return true;
    }
}
