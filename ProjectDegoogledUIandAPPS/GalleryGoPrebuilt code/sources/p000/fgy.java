package p000;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

/* renamed from: fgy */
/* compiled from: PG */
public final class fgy extends Drawable implements fgv {

    /* renamed from: d */
    public static /* synthetic */ int f9542d;

    /* renamed from: e */
    private static final LinearInterpolator f9543e = new LinearInterpolator();

    /* renamed from: a */
    public boolean f9544a;

    /* renamed from: b */
    public float f9545b;

    /* renamed from: c */
    public Runnable f9546c;

    /* renamed from: f */
    private final ValueAnimator f9547f;

    /* renamed from: g */
    private final ValueAnimator f9548g;

    /* renamed from: h */
    private float f9549h;

    /* renamed from: i */
    private final RectF f9550i = new RectF();

    /* renamed from: j */
    private final Rect f9551j = new Rect();

    /* renamed from: k */
    private final int f9552k;

    /* renamed from: l */
    private final Paint f9553l;

    /* renamed from: m */
    private final int f9554m;

    /* renamed from: n */
    private final int f9555n;

    /* renamed from: o */
    private final float f9556o;

    /* renamed from: p */
    private final fgq f9557p;

    /* renamed from: q */
    private final fgt f9558q;

    /* renamed from: r */
    private int f9559r = 255;

    public float getAlphaFraction() {
        return this.f9549h;
    }

    public final int getOpacity() {
        return -3;
    }

    public fgy(int i, int i2, int i3) {
        this.f9554m = i;
        this.f9556o = -1.0f;
        this.f9555n = i2;
        this.f9552k = i3;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "alphaFraction", new float[]{0.0f, 1.0f});
        ofFloat.setDuration(750);
        ofFloat.setInterpolator(f9543e);
        this.f9547f = ofFloat;
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "alphaFraction", new float[]{1.0f, 0.0f});
        ofFloat2.addListener(new fgx(this));
        ofFloat2.setDuration(750);
        ofFloat2.setInterpolator(f9543e);
        this.f9548g = ofFloat2;
        Paint paint = new Paint();
        this.f9553l = paint;
        paint.setStyle(Paint.Style.STROKE);
        this.f9553l.setStrokeCap(Paint.Cap.SQUARE);
        this.f9553l.setAntiAlias(true);
        this.f9544a = false;
        this.f9545b = (float) (getLevel() / 10000);
        fgq fgq = new fgq();
        this.f9557p = fgq;
        double level = (double) getLevel();
        Double.isNaN(level);
        fgq.mo5601b(level / 10000.0d);
        fgq.mo5597a((double) this.f9545b);
        fgq.mo5600b();
        fgq.mo5598a((fgp) new fgw(this));
        this.f9558q = new fgt(this.f9557p);
        setVisible(false, false);
    }

    public final void draw(Canvas canvas) {
        Rect rect;
        if (!getBounds().isEmpty() && isVisible()) {
            if (this.f9556o == -1.0f) {
                rect = getBounds();
            } else {
                Rect bounds = getBounds();
                int centerX = bounds.centerX();
                int centerY = bounds.centerY();
                this.f9551j.left = centerX - (getIntrinsicWidth() / 2);
                this.f9551j.right = centerX + (getIntrinsicWidth() / 2);
                this.f9551j.top = centerY - (getIntrinsicHeight() / 2);
                this.f9551j.bottom = centerY + (getIntrinsicHeight() / 2);
                rect = this.f9551j;
            }
            int i = this.f9554m;
            float f = this.f9549h;
            float f2 = ((float) i) * f;
            int i2 = (int) (((float) this.f9559r) * f);
            float f3 = ((float) (this.f9555n + i)) - (f2 / 2.0f);
            this.f9553l.setStrokeWidth(f2);
            this.f9550i.set(rect);
            this.f9550i.inset(f3, f3);
            float width = this.f9550i.width();
            float f4 = this.f9545b;
            this.f9553l.setColor(this.f9552k);
            this.f9553l.setAlpha((int) (((float) i2) * 0.2f));
            canvas.drawOval(this.f9550i, this.f9553l);
            this.f9553l.setAlpha(i2);
            double d = (double) ((width / 2.0f) - f2);
            Double.isNaN(d);
            double d2 = (double) (f2 * 180.0f);
            Double.isNaN(d2);
            float max = Math.max(f4 * 360.0f, (float) ((d * 3.141592653589793d) / d2));
            if (max >= 5.0f) {
                canvas.drawArc(this.f9550i, -90.0f, max, false, this.f9553l);
            }
        }
    }

    public final int getIntrinsicHeight() {
        float f = this.f9556o;
        return f != -1.0f ? (int) (f + f) : super.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth() {
        float f = this.f9556o;
        return f != -1.0f ? (int) (f + f) : super.getIntrinsicWidth();
    }

    /* renamed from: a */
    public final void mo5606a(Runnable runnable) {
        this.f9546c = runnable;
        setVisible(false, false);
    }

    /* renamed from: a */
    public final void mo5605a() {
        this.f9544a = false;
        if (super.setVisible(false, false)) {
            Runnable runnable = this.f9546c;
            if (runnable != null) {
                runnable.run();
            }
            m8812c();
        }
        this.f9546c = null;
    }

    /* renamed from: c */
    private final void m8812c() {
        fgq fgq = this.f9557p;
        double level = (double) getLevel();
        Double.isNaN(level);
        fgq.mo5601b(level / 10000.0d);
        this.f9558q.mo5603b();
    }

    /* access modifiers changed from: protected */
    public final boolean onLevelChange(int i) {
        fgq fgq = this.f9557p;
        double d = (double) i;
        Double.isNaN(d);
        fgq.mo5597a(d / 10000.0d);
        this.f9558q.mo5602a();
        return true;
    }

    /* renamed from: b */
    public final void mo5608b() {
        m8812c();
        if (this.f9547f.isStarted()) {
            this.f9547f.cancel();
        }
        if (this.f9548g.isStarted()) {
            this.f9548g.cancel();
        }
    }

    public final void setAlpha(int i) {
        if (i != this.f9559r) {
            this.f9559r = i;
            invalidateSelf();
        }
    }

    public void setAlphaFraction(float f) {
        this.f9549h = f;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f9553l.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public final boolean setVisible(boolean z, boolean z2) {
        boolean z3 = z != this.f9544a;
        if (!z3 && !z2) {
            return false;
        }
        this.f9544a = z;
        if (z) {
            super.setVisible(true, z2);
            if (this.f9548g.isRunning()) {
                this.f9547f.setCurrentPlayTime(750 - this.f9548g.getCurrentPlayTime());
                this.f9548g.cancel();
            }
            if (z2) {
                mo5608b();
                this.f9547f.start();
            } else {
                this.f9547f.start();
            }
            this.f9546c = null;
        } else if (z3) {
            if (this.f9547f.isRunning()) {
                this.f9548g.setCurrentPlayTime(750 - this.f9547f.getCurrentPlayTime());
                this.f9547f.cancel();
            }
            this.f9548g.start();
        } else {
            mo5608b();
        }
        return z3;
    }
}
