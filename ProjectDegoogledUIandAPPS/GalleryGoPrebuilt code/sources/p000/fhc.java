package p000;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.LinearInterpolator;
import java.util.ArrayList;

/* renamed from: fhc */
/* compiled from: PG */
public final class fhc extends Drawable implements Animatable, fgv {

    /* renamed from: j */
    public static /* synthetic */ int f9610j;

    /* renamed from: m */
    private static final LinearInterpolator f9611m = new LinearInterpolator();

    /* renamed from: A */
    private final float f9612A;

    /* renamed from: B */
    private long f9613B;

    /* renamed from: C */
    private long f9614C;

    /* renamed from: a */
    public final AnimatorSet f9615a;

    /* renamed from: b */
    public final ValueAnimator f9616b;

    /* renamed from: c */
    public float f9617c;

    /* renamed from: d */
    public float f9618d;

    /* renamed from: e */
    public int f9619e;

    /* renamed from: f */
    public int f9620f;

    /* renamed from: g */
    public final int[] f9621g;

    /* renamed from: h */
    public boolean f9622h;

    /* renamed from: i */
    public Runnable f9623i;

    /* renamed from: k */
    private final RectF f9624k = new RectF();

    /* renamed from: l */
    private final Rect f9625l = new Rect();

    /* renamed from: n */
    private final ValueAnimator f9626n;

    /* renamed from: o */
    private final ValueAnimator f9627o;

    /* renamed from: p */
    private final ValueAnimator f9628p;

    /* renamed from: q */
    private final ValueAnimator f9629q;

    /* renamed from: r */
    private final ValueAnimator f9630r;

    /* renamed from: s */
    private final ArrayList f9631s;

    /* renamed from: t */
    private float f9632t;

    /* renamed from: u */
    private float f9633u;

    /* renamed from: v */
    private float f9634v;

    /* renamed from: w */
    private final Paint f9635w;

    /* renamed from: x */
    private int f9636x;

    /* renamed from: y */
    private final int f9637y;

    /* renamed from: z */
    private final int f9638z;

    public float getAlphaFraction() {
        return this.f9634v;
    }

    public int getCurrentColor() {
        return this.f9620f;
    }

    public float getDetentFraction() {
        return this.f9632t;
    }

    public float getHeadFraction() {
        return this.f9633u;
    }

    public final int getOpacity() {
        return -3;
    }

    public float getTailFraction() {
        return this.f9618d;
    }

    public fhc(int i, int i2, int[] iArr) {
        this.f9637y = i;
        this.f9612A = -1.0f;
        this.f9638z = i2;
        this.f9621g = iArr;
        this.f9622h = false;
        ArrayList arrayList = new ArrayList();
        this.f9631s = arrayList;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "detentFraction", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(1332);
        ofFloat.setInterpolator(f9611m);
        this.f9626n = ofFloat;
        arrayList.add(ofFloat);
        ArrayList arrayList2 = this.f9631s;
        int[] iArr2 = this.f9621g;
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "currentColor", new int[]{iArr2[this.f9619e], iArr2[mo5668b()]});
        ofInt.setEvaluator(fgf.f9506a);
        ofInt.setStartDelay(999);
        ofInt.setDuration(333);
        ofInt.setInterpolator(f9611m);
        this.f9620f = this.f9621g[this.f9619e];
        this.f9616b = ofInt;
        arrayList2.add(ofInt);
        ArrayList arrayList3 = this.f9631s;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "headFraction", new float[]{0.0f, 1.0f});
        ofFloat2.setDuration(666);
        ofFloat2.setInterpolator(fgo.f9518a);
        this.f9627o = ofFloat2;
        arrayList3.add(ofFloat2);
        ArrayList arrayList4 = this.f9631s;
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, "tailFraction", new float[]{0.0f, 1.0f});
        ofFloat3.setStartDelay(666);
        ofFloat3.setDuration(666);
        ofFloat3.setInterpolator(fgo.f9518a);
        this.f9628p = ofFloat3;
        arrayList4.add(ofFloat3);
        ArrayList arrayList5 = this.f9631s;
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this, "alphaFraction", new float[]{0.0f, 1.0f});
        ofFloat4.addListener(new fha(this));
        ofFloat4.setDuration(750);
        ofFloat4.setInterpolator(f9611m);
        this.f9629q = ofFloat4;
        arrayList5.add(ofFloat4);
        ArrayList arrayList6 = this.f9631s;
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this, "alphaFraction", new float[]{1.0f, 0.0f});
        ofFloat5.addListener(new fhb(this));
        ofFloat5.setDuration(750);
        ofFloat5.setInterpolator(f9611m);
        this.f9630r = ofFloat5;
        arrayList6.add(ofFloat5);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{this.f9626n, this.f9627o, this.f9628p, this.f9616b});
        fhm.m8910a();
        fgi.m8780a(animatorSet, new fgz(this));
        this.f9615a = animatorSet;
        Paint paint = new Paint();
        this.f9635w = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.f9635w.setStrokeCap(Paint.Cap.SQUARE);
        this.f9635w.setAntiAlias(true);
        this.f9636x = 255;
        setVisible(false, false);
        mo5669c();
    }

    public final void draw(Canvas canvas) {
        Rect rect;
        if (getBounds().isEmpty()) {
            return;
        }
        if (isVisible() || this.f9630r.isRunning()) {
            if (this.f9612A == -1.0f) {
                rect = getBounds();
            } else {
                Rect bounds = getBounds();
                int centerX = bounds.centerX();
                int centerY = bounds.centerY();
                this.f9625l.left = centerX - (getIntrinsicWidth() / 2);
                this.f9625l.right = centerX + (getIntrinsicWidth() / 2);
                this.f9625l.top = centerY - (getIntrinsicHeight() / 2);
                this.f9625l.bottom = centerY + (getIntrinsicHeight() / 2);
                rect = this.f9625l;
            }
            int i = this.f9637y;
            float f = this.f9634v;
            float f2 = ((float) i) * f;
            int i2 = this.f9636x;
            float f3 = ((float) (this.f9638z + i)) - (f2 / 2.0f);
            this.f9635w.setColor(this.f9620f);
            this.f9635w.setAlpha((int) (((float) i2) * f));
            this.f9635w.setStrokeWidth(f2);
            this.f9624k.set(rect);
            this.f9624k.inset(f3, f3);
            float width = this.f9624k.width();
            float f4 = this.f9618d * 290.0f;
            float abs = Math.abs((this.f9633u * 290.0f) - f4);
            double d = (double) ((width / 2.0f) - f2);
            Double.isNaN(d);
            double d2 = (double) (f2 * 180.0f);
            Double.isNaN(d2);
            float max = Math.max(abs, (float) ((d * 3.141592653589793d) / d2));
            float f5 = this.f9632t;
            Canvas canvas2 = canvas;
            canvas2.drawArc(this.f9624k, (f4 + (this.f9617c + (f5 * 286.0f))) - 0.049804688f, max, false, this.f9635w);
        }
    }

    public final int getIntrinsicHeight() {
        float f = this.f9612A;
        return f != -1.0f ? (int) (f + f) : super.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth() {
        float f = this.f9612A;
        return f != -1.0f ? (int) (f + f) : super.getIntrinsicWidth();
    }

    /* renamed from: b */
    public final int mo5668b() {
        return (this.f9619e + 1) % this.f9621g.length;
    }

    /* renamed from: a */
    public final void mo5606a(Runnable runnable) {
        this.f9623i = runnable;
        stop();
    }

    /* renamed from: a */
    public final void mo5605a() {
        stop();
        mo5669c();
    }

    public final boolean isRunning() {
        return this.f9615a.isRunning() || this.f9629q.isRunning();
    }

    /* renamed from: c */
    public final void mo5669c() {
        super.setVisible(this.f9622h, false);
        if (this.f9615a.isStarted()) {
            this.f9615a.cancel();
        }
        ArrayList arrayList = this.f9631s;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Animator animator = (Animator) arrayList.get(i);
            if (animator.isStarted()) {
                animator.cancel();
            }
        }
        mo5670d();
        this.f9633u = 0.0f;
        this.f9618d = 0.0f;
        this.f9632t = 0.0f;
        this.f9617c = 0.0f;
        this.f9619e = 0;
        int[] iArr = this.f9621g;
        int i2 = iArr[0];
        this.f9620f = i2;
        this.f9616b.setIntValues(new int[]{i2, iArr[mo5668b()]});
        this.f9634v = 0.0f;
        invalidateSelf();
    }

    /* renamed from: d */
    public final void mo5670d() {
        this.f9616b.setStartDelay(999);
        this.f9628p.setStartDelay(666);
    }

    public final void setAlpha(int i) {
        if (i != this.f9636x) {
            this.f9636x = i;
            invalidateSelf();
        }
    }

    public void setAlphaFraction(float f) {
        this.f9634v = f;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f9635w.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setCurrentColor(int i) {
        this.f9620f = i;
        invalidateSelf();
    }

    public void setDetentFraction(float f) {
        this.f9632t = f;
        invalidateSelf();
    }

    public void setHeadFraction(float f) {
        this.f9633u = f;
        invalidateSelf();
    }

    public void setTailFraction(float f) {
        this.f9618d = f;
        invalidateSelf();
    }

    /* renamed from: a */
    private final void m8886a(boolean z) {
        setVisible(z, true);
    }

    public final boolean setVisible(boolean z, boolean z2) {
        boolean z3 = z != this.f9622h;
        if (!z3 && !z2) {
            return false;
        }
        this.f9622h = z;
        if (z) {
            super.setVisible(true, z2);
            if (this.f9630r.isRunning()) {
                this.f9629q.setCurrentPlayTime(750 - this.f9630r.getCurrentPlayTime());
                this.f9630r.cancel();
            }
            if (!z2) {
                long max = Math.max(0, this.f9613B - (SystemClock.elapsedRealtime() - this.f9614C));
                this.f9613B = max;
                this.f9629q.setStartDelay(max);
                this.f9629q.start();
            } else {
                mo5669c();
                this.f9629q.setStartDelay(0);
                this.f9629q.start();
                this.f9613B = 0;
            }
            this.f9614C = SystemClock.elapsedRealtime();
            this.f9623i = null;
        } else if (z3) {
            if (this.f9629q.isRunning()) {
                this.f9630r.setCurrentPlayTime(750 - this.f9629q.getCurrentPlayTime());
                this.f9629q.cancel();
            }
            this.f9630r.start();
        } else {
            mo5669c();
        }
        return z3;
    }

    public final void start() {
        m8886a(true);
    }

    public final void stop() {
        m8886a(false);
    }
}
