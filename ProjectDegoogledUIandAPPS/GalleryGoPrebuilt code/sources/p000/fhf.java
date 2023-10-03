package p000;

import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* renamed from: fhf */
/* compiled from: PG */
public final class fhf extends Drawable implements fgv {

    /* renamed from: a */
    public boolean f9641a;

    /* renamed from: b */
    public double f9642b;

    /* renamed from: c */
    public Runnable f9643c;

    /* renamed from: d */
    private final int f9644d;

    /* renamed from: e */
    private final int f9645e;

    /* renamed from: f */
    private final int f9646f;

    /* renamed from: g */
    private final int f9647g;

    /* renamed from: h */
    private final int f9648h;

    /* renamed from: i */
    private final Paint f9649i;

    /* renamed from: j */
    private final fgq f9650j;

    /* renamed from: k */
    private final fgt f9651k;

    /* renamed from: l */
    private final ObjectAnimator f9652l;

    /* renamed from: m */
    private final ObjectAnimator f9653m;

    /* renamed from: n */
    private final float f9654n;

    /* renamed from: o */
    private final float f9655o;

    /* renamed from: p */
    private float f9656p;

    /* renamed from: q */
    private float f9657q;

    /* renamed from: r */
    private final fgp f9658r = new fhe(this);

    public fhf(int i, int i2, int i3, float f, int i4) {
        this.f9645e = i;
        this.f9646f = i2;
        this.f9647g = i3;
        this.f9644d = Math.round(f * 255.0f);
        this.f9648h = i4;
        Paint paint = new Paint();
        this.f9649i = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f9649i.setAntiAlias(true);
        float f2 = 1.0f;
        this.f9656p = 1.0f;
        this.f9641a = isVisible();
        this.f9657q = 0.0f;
        double level = (double) getLevel();
        Double.isNaN(level);
        this.f9642b = level / 10000.0d;
        this.f9654n = 1.0f;
        this.f9655o = i4 != 2 ? 0.0f : f2;
        fgq fgq = new fgq();
        this.f9650j = fgq;
        double level2 = (double) getLevel();
        Double.isNaN(level2);
        fgq.mo5601b(level2 / 10000.0d);
        fgq.mo5597a(this.f9642b);
        fgq.mo5600b();
        fgq.mo5598a(this.f9658r);
        this.f9651k = new fgt(this.f9650j);
        this.f9652l = fhg.m8898a((Object) this, "growScale");
        ObjectAnimator b = fhg.m8903b(this, "growScale");
        b.addListener(new fhd(this));
        this.f9653m = b;
    }

    public float getGrowScale() {
        return this.f9657q;
    }

    public final int getIntrinsicHeight() {
        return this.f9645e;
    }

    public final int getIntrinsicWidth() {
        return -1;
    }

    public final int getOpacity() {
        return -3;
    }

    public final void draw(Canvas canvas) {
        if (!getBounds().isEmpty() && isVisible()) {
            canvas.save();
            Rect bounds = getBounds();
            float height = (float) bounds.height();
            float f = (float) this.f9645e;
            if (height > f) {
                canvas.translate(0.0f, (height - f) / 2.0f);
            }
            canvas.scale(((float) bounds.width()) / 10000.0f, ((float) this.f9645e) / 4.0f);
            canvas.translate(5000.0f, 2.0f);
            if (this.f9657q < 1.0f) {
                if (this.f9648h == 0) {
                    canvas.scale(1.0f, -1.0f);
                }
                canvas.translate(0.0f, (this.f9657q - 4.0f) * 4.0f * 0.5f);
                canvas.scale(1.0f, this.f9657q);
            }
            int i = this.f9647g;
            if (i == -1) {
                this.f9649i.setColor(this.f9646f);
            } else {
                this.f9649i.setColor(i);
            }
            this.f9649i.setAlpha((int) (((float) this.f9644d) * this.f9656p));
            canvas.drawRect(-5000.0f, -2.0f, 5000.0f, 2.0f, this.f9649i);
            this.f9649i.setColor(this.f9646f);
            this.f9649i.setAlpha((int) (this.f9656p * 255.0f));
            canvas.drawRect(-5000.0f, -2.0f, ((float) (this.f9642b * 10000.0d)) - 8.687973E-4f, 2.0f, this.f9649i);
            canvas.restore();
        }
    }

    /* renamed from: a */
    public final void mo5606a(Runnable runnable) {
        this.f9643c = runnable;
        setVisible(false, false);
    }

    /* renamed from: a */
    public final void mo5605a() {
        this.f9641a = false;
        if (super.setVisible(false, false)) {
            this.f9652l.cancel();
            this.f9653m.cancel();
            mo5692b();
            Runnable runnable = this.f9643c;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f9643c = null;
    }

    /* renamed from: b */
    public final void mo5692b() {
        fgq fgq = this.f9650j;
        double level = (double) getLevel();
        Double.isNaN(level);
        fgq.mo5601b(level / 10000.0d);
        this.f9651k.mo5603b();
    }

    /* access modifiers changed from: protected */
    public final boolean onLevelChange(int i) {
        fgq fgq = this.f9650j;
        double d = (double) i;
        Double.isNaN(d);
        fgq.mo5597a(d / 10000.0d);
        this.f9651k.mo5602a();
        return true;
    }

    public final void setAlpha(int i) {
        this.f9656p = ((float) i) / 255.0f;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f9649i.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setGrowScale(float f) {
        this.f9657q = f;
        invalidateSelf();
    }

    public final boolean setVisible(boolean z, boolean z2) {
        boolean z3 = z != this.f9641a;
        if (!z3 && !z2) {
            return false;
        }
        this.f9641a = z;
        if (z) {
            super.setVisible(true, z2);
            if (z2) {
                mo5692b();
                this.f9652l.cancel();
                this.f9653m.cancel();
                this.f9657q = this.f9655o;
            }
            this.f9653m.cancel();
            this.f9652l.setFloatValues(new float[]{this.f9654n});
            this.f9652l.start();
            this.f9643c = null;
        } else if (z3) {
            this.f9652l.cancel();
            this.f9653m.setFloatValues(new float[]{this.f9655o});
            this.f9653m.start();
        }
        return z3;
    }
}
