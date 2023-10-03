package p000;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.p002v7.widget.RecyclerView;
import android.view.MotionEvent;

/* renamed from: vn */
/* compiled from: PG */
public final class C0587vn extends C0643xp implements C0651xx {

    /* renamed from: p */
    private static final int[] f16109p = {16842919};

    /* renamed from: q */
    private static final int[] f16110q = new int[0];

    /* renamed from: A */
    private int f16111A = 0;

    /* renamed from: B */
    private final int[] f16112B = new int[2];

    /* renamed from: C */
    private final int[] f16113C = new int[2];

    /* renamed from: D */
    private final Runnable f16114D = new C0583vj(this);

    /* renamed from: E */
    private final C0652xy f16115E = new C0584vk(this);

    /* renamed from: a */
    public final int f16116a;

    /* renamed from: b */
    public final StateListDrawable f16117b;

    /* renamed from: c */
    public final Drawable f16118c;

    /* renamed from: d */
    public int f16119d;

    /* renamed from: e */
    public int f16120e;

    /* renamed from: f */
    public int f16121f;

    /* renamed from: g */
    public int f16122g;

    /* renamed from: h */
    public int f16123h = 0;

    /* renamed from: i */
    public int f16124i = 0;

    /* renamed from: j */
    public RecyclerView f16125j;

    /* renamed from: k */
    public boolean f16126k = false;

    /* renamed from: l */
    public boolean f16127l = false;

    /* renamed from: m */
    public int f16128m = 0;

    /* renamed from: n */
    public final ValueAnimator f16129n = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});

    /* renamed from: o */
    public int f16130o = 0;

    /* renamed from: r */
    private final int f16131r;

    /* renamed from: s */
    private final int f16132s;

    /* renamed from: t */
    private final int f16133t;

    /* renamed from: u */
    private final StateListDrawable f16134u;

    /* renamed from: v */
    private final Drawable f16135v;

    /* renamed from: w */
    private final int f16136w;

    /* renamed from: x */
    private final int f16137x;

    /* renamed from: y */
    private float f16138y;

    /* renamed from: z */
    private float f16139z;

    /* renamed from: b */
    private final boolean m15614b(float f, float f2) {
        if (f2 < ((float) (this.f16124i - this.f16136w))) {
            return false;
        }
        int i = this.f16122g;
        int i2 = this.f16121f / 2;
        return f >= ((float) (i - i2)) && f <= ((float) (i + i2));
    }

    /* renamed from: b */
    public final void mo10395b() {
    }

    public C0587vn(RecyclerView recyclerView, StateListDrawable stateListDrawable, Drawable drawable, StateListDrawable stateListDrawable2, Drawable drawable2, int i, int i2, int i3) {
        this.f16117b = stateListDrawable;
        this.f16118c = drawable;
        this.f16134u = stateListDrawable2;
        this.f16135v = drawable2;
        this.f16132s = Math.max(i, stateListDrawable.getIntrinsicWidth());
        this.f16133t = Math.max(i, drawable.getIntrinsicWidth());
        this.f16136w = Math.max(i, stateListDrawable2.getIntrinsicWidth());
        this.f16137x = Math.max(i, drawable2.getIntrinsicWidth());
        this.f16116a = i2;
        this.f16131r = i3;
        this.f16117b.setAlpha(255);
        this.f16118c.setAlpha(255);
        this.f16129n.addListener(new C0585vl(this));
        this.f16129n.addUpdateListener(new C0586vm(this));
        RecyclerView recyclerView2 = this.f16125j;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.removeItemDecoration(this);
                this.f16125j.removeOnItemTouchListener(this);
                this.f16125j.removeOnScrollListener(this.f16115E);
                m15618f();
            }
            this.f16125j = recyclerView;
            if (recyclerView != null) {
                recyclerView.addItemDecoration(this);
                this.f16125j.addOnItemTouchListener(this);
                this.f16125j.addOnScrollListener(this.f16115E);
            }
        }
    }

    /* renamed from: f */
    private final void m15618f() {
        this.f16125j.removeCallbacks(this.f16114D);
    }

    /* renamed from: d */
    private final boolean m15616d() {
        return C0340mj.m14714f(this.f16125j) == 1;
    }

    /* renamed from: a */
    private final boolean m15613a(float f, float f2) {
        if (!m15616d()) {
            if (f < ((float) (this.f16123h - this.f16132s))) {
                return false;
            }
        } else if (f > ((float) this.f16132s)) {
            return false;
        }
        int i = this.f16120e;
        int i2 = this.f16119d / 2;
        return f2 >= ((float) (i - i2)) && f2 <= ((float) (i + i2));
    }

    /* renamed from: a */
    public final void mo198a(Canvas canvas, RecyclerView recyclerView) {
        if (this.f16123h != this.f16125j.getWidth() || this.f16124i != this.f16125j.getHeight()) {
            this.f16123h = this.f16125j.getWidth();
            this.f16124i = this.f16125j.getHeight();
            mo10393a(0);
        } else if (this.f16130o != 0) {
            if (this.f16126k) {
                int i = this.f16123h;
                int i2 = this.f16132s;
                int i3 = i - i2;
                int i4 = this.f16120e;
                int i5 = this.f16119d;
                int i6 = i4 - (i5 / 2);
                this.f16117b.setBounds(0, 0, i2, i5);
                this.f16118c.setBounds(0, 0, this.f16133t, this.f16124i);
                if (m15616d()) {
                    this.f16118c.draw(canvas);
                    canvas.translate((float) this.f16132s, (float) i6);
                    canvas.scale(-1.0f, 1.0f);
                    this.f16117b.draw(canvas);
                    canvas.scale(-1.0f, 1.0f);
                    canvas.translate((float) (-this.f16132s), (float) (-i6));
                } else {
                    canvas.translate((float) i3, 0.0f);
                    this.f16118c.draw(canvas);
                    canvas.translate(0.0f, (float) i6);
                    this.f16117b.draw(canvas);
                    canvas.translate((float) (-i3), (float) (-i6));
                }
            }
            if (this.f16127l) {
                int i7 = this.f16124i;
                int i8 = this.f16136w;
                int i9 = i7 - i8;
                int i10 = this.f16122g;
                int i11 = this.f16121f;
                int i12 = i10 - (i11 / 2);
                this.f16134u.setBounds(0, 0, i11, i8);
                this.f16135v.setBounds(0, 0, this.f16123h, this.f16137x);
                canvas.translate(0.0f, (float) i9);
                this.f16135v.draw(canvas);
                canvas.translate((float) i12, 0.0f);
                this.f16134u.draw(canvas);
                canvas.translate((float) (-i12), (float) (-i9));
            }
        }
    }

    /* renamed from: a */
    public final boolean mo10394a(MotionEvent motionEvent) {
        int i = this.f16128m;
        if (i != 1) {
            return i == 2;
        }
        boolean a = m15613a(motionEvent.getX(), motionEvent.getY());
        boolean b = m15614b(motionEvent.getX(), motionEvent.getY());
        if (motionEvent.getAction() != 0) {
            return false;
        }
        if (!a) {
            if (!b) {
                return false;
            }
        } else if (!b) {
            this.f16111A = 2;
            this.f16138y = (float) ((int) motionEvent.getY());
            mo10393a(2);
            return true;
        }
        this.f16111A = 1;
        this.f16139z = (float) ((int) motionEvent.getX());
        mo10393a(2);
        return true;
    }

    /* renamed from: b */
    public final void mo10396b(MotionEvent motionEvent) {
        if (this.f16128m == 0) {
            return;
        }
        if (motionEvent.getAction() == 0) {
            boolean a = m15613a(motionEvent.getX(), motionEvent.getY());
            boolean b = m15614b(motionEvent.getX(), motionEvent.getY());
            if (!a) {
                if (!b) {
                    return;
                }
            } else if (!b) {
                this.f16111A = 2;
                this.f16138y = (float) ((int) motionEvent.getY());
                mo10393a(2);
            }
            this.f16111A = 1;
            this.f16139z = (float) ((int) motionEvent.getX());
            mo10393a(2);
        } else if (motionEvent.getAction() == 1 && this.f16128m == 2) {
            this.f16138y = 0.0f;
            this.f16139z = 0.0f;
            mo10393a(1);
            this.f16111A = 0;
        } else if (motionEvent.getAction() == 2 && this.f16128m == 2) {
            m15617e();
            if (this.f16111A == 1) {
                float x = motionEvent.getX();
                int[] iArr = this.f16113C;
                int i = this.f16131r;
                iArr[0] = i;
                int i2 = this.f16123h - i;
                iArr[1] = i2;
                float max = Math.max((float) iArr[0], Math.min((float) i2, x));
                if (Math.abs(((float) this.f16122g) - max) >= 2.0f) {
                    int a2 = m15612a(this.f16139z, max, iArr, this.f16125j.computeHorizontalScrollRange(), this.f16125j.computeHorizontalScrollOffset(), this.f16123h);
                    if (a2 != 0) {
                        this.f16125j.scrollBy(a2, 0);
                    }
                    this.f16139z = max;
                }
            }
            if (this.f16111A == 2) {
                float y = motionEvent.getY();
                int[] iArr2 = this.f16112B;
                int i3 = this.f16131r;
                iArr2[0] = i3;
                int i4 = this.f16124i - i3;
                iArr2[1] = i4;
                float max2 = Math.max((float) iArr2[0], Math.min((float) i4, y));
                if (Math.abs(((float) this.f16120e) - max2) >= 2.0f) {
                    int a3 = m15612a(this.f16138y, max2, iArr2, this.f16125j.computeVerticalScrollRange(), this.f16125j.computeVerticalScrollOffset(), this.f16124i);
                    if (a3 != 0) {
                        this.f16125j.scrollBy(0, a3);
                    }
                    this.f16138y = max2;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10392a() {
        this.f16125j.invalidate();
    }

    /* renamed from: c */
    private final void m15615c(int i) {
        m15618f();
        this.f16125j.postDelayed(this.f16114D, (long) i);
    }

    /* renamed from: a */
    private static final int m15612a(float f, float f2, int[] iArr, int i, int i2, int i3) {
        int i4 = iArr[1] - iArr[0];
        if (i4 != 0) {
            int i5 = i - i3;
            int i6 = (int) (((f2 - f) / ((float) i4)) * ((float) i5));
            int i7 = i2 + i6;
            if (i7 >= i5 || i7 < 0) {
                return 0;
            }
            return i6;
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10393a(int i) {
        if (i == 2 && this.f16128m != 2) {
            this.f16117b.setState(f16109p);
            m15618f();
        }
        if (i != 0) {
            m15617e();
        } else {
            mo10392a();
        }
        if (this.f16128m == 2 && i != 2) {
            this.f16117b.setState(f16110q);
            m15615c(1200);
        } else if (i == 1) {
            m15615c(1500);
        }
        this.f16128m = i;
    }

    /* renamed from: e */
    private final void m15617e() {
        int i = this.f16130o;
        if (i != 0) {
            if (i == 3) {
                this.f16129n.cancel();
            } else {
                return;
            }
        }
        this.f16130o = 1;
        ValueAnimator valueAnimator = this.f16129n;
        valueAnimator.setFloatValues(new float[]{((Float) valueAnimator.getAnimatedValue()).floatValue(), 1.0f});
        this.f16129n.setDuration(500);
        this.f16129n.setStartDelay(0);
        this.f16129n.start();
    }
}
