package p000;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.animation.LinearInterpolator;

/* renamed from: fhj */
/* compiled from: PG */
public final class fhj extends Drawable implements Animatable, fgv {

    /* renamed from: a */
    public final AnimatorSet f9660a;

    /* renamed from: b */
    public boolean f9661b = isVisible();

    /* renamed from: c */
    public Runnable f9662c;

    /* renamed from: d */
    private final int f9663d;

    /* renamed from: e */
    private final boolean f9664e;

    /* renamed from: f */
    private final int f9665f;

    /* renamed from: g */
    private final int f9666g;

    /* renamed from: h */
    private final int f9667h;

    /* renamed from: i */
    private final int f9668i;

    /* renamed from: j */
    private final Paint f9669j;

    /* renamed from: k */
    private final ObjectAnimator f9670k;

    /* renamed from: l */
    private final ObjectAnimator f9671l;

    /* renamed from: m */
    private final float f9672m;

    /* renamed from: n */
    private final float f9673n;

    /* renamed from: o */
    private float f9674o = 1.0f;

    /* renamed from: p */
    private float f9675p;

    /* renamed from: q */
    private float f9676q;

    /* renamed from: r */
    private float f9677r;

    /* renamed from: s */
    private float f9678s;

    /* renamed from: t */
    private float f9679t;

    /* renamed from: u */
    private final Rect f9680u;

    public fhj(int i, int i2, int i3, float f, boolean z, int i4) {
        this.f9665f = i;
        this.f9667h = i2;
        this.f9663d = Math.round(f * 255.0f);
        this.f9664e = z;
        this.f9668i = i4;
        this.f9666g = i3;
        float f2 = 1.0f;
        Paint paint = new Paint();
        this.f9669j = paint;
        paint.setStyle(Paint.Style.FILL);
        this.f9669j.setAntiAlias(true);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, "rect1ScaleX", new float[]{0.1f, 0.1f});
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setDuration(733);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, "rect1ScaleX", new float[]{0.1f, 0.8268492f});
        ofFloat2.setInterpolator(cya.m5633a(0.33473143f, 0.12481982f, 0.78584397f, 1.0f));
        ofFloat2.setDuration(650);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, "rect1ScaleX", new float[]{0.8268492f, 0.1f});
        ofFloat3.setInterpolator(cya.m5633a(0.225732f, 0.0f, 0.35f, 1.05f));
        ofFloat3.setDuration(617);
        animatorSet.playSequentially(new Animator[]{ofFloat, ofFloat2, ofFloat3});
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this, "rect1TranslationX", new float[]{-522.6f, 199.6f});
        ofFloat4.setInterpolator(cya.m5633a(0.34f, 0.0f, 0.78f, 1.0f));
        ofFloat4.setStartDelay(400);
        ofFloat4.setDuration(1600);
        animatorSet.play(ofFloat4);
        ObjectAnimator ofFloat5 = ObjectAnimator.ofFloat(this, "rect2ScaleX", new float[]{0.1f, 0.55f});
        ofFloat5.setInterpolator(cya.m5633a(0.20502818f, 0.057050835f, 0.5f, 0.5f));
        ofFloat5.setDuration(352);
        ObjectAnimator ofFloat6 = ObjectAnimator.ofFloat(this, "rect2ScaleX", new float[]{0.55f, 0.90995026f});
        ofFloat6.setInterpolator(cya.m5633a(0.15f, 0.2f, 0.6483738f, 1.0043154f));
        ofFloat6.setDuration(532);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(this, "rect2ScaleX", new float[]{0.90995026f, 0.1f});
        ofFloat7.setInterpolator(cya.m5633a(0.25775883f, -0.003163357f, 0.21176192f, 1.3817896f));
        ofFloat7.setDuration(1116);
        animatorSet.playSequentially(new Animator[]{ofFloat5, ofFloat6, ofFloat7});
        ObjectAnimator ofFloat8 = ObjectAnimator.ofFloat(this, "rect2TranslationX", new float[]{-208.0f, 132.0f});
        ofFloat8.setInterpolator(cya.m5633a(0.26f, 0.0f, 0.75f, 0.68f));
        ofFloat8.setDuration(964);
        ObjectAnimator ofFloat9 = ObjectAnimator.ofFloat(this, "rect2TranslationX", new float[]{132.0f, 422.6f});
        ofFloat9.setInterpolator(cya.m5633a(0.4f, 0.6270349f, 0.6f, 0.9020258f));
        ofFloat9.setDuration(1036);
        animatorSet.playSequentially(new Animator[]{ofFloat8, ofFloat9});
        animatorSet.addListener(new fhh());
        fhm.m8910a();
        fgi.m8780a(animatorSet, (Runnable) null);
        this.f9660a = animatorSet;
        this.f9672m = 1.0f;
        this.f9673n = i4 != 2 ? 0.0f : f2;
        this.f9670k = fhg.m8898a((Object) this, "growScale");
        ObjectAnimator b = fhg.m8903b(this, "growScale");
        b.addListener(new fhi(this));
        this.f9671l = b;
        this.f9680u = new Rect();
        mo5705b();
    }

    public float getGrowScale() {
        return this.f9679t;
    }

    public final int getIntrinsicHeight() {
        return this.f9665f;
    }

    public final int getIntrinsicWidth() {
        return -1;
    }

    public final int getOpacity() {
        return -3;
    }

    public float getRect1ScaleX() {
        return this.f9675p;
    }

    public float getRect1TranslationX() {
        return this.f9676q;
    }

    public float getRect2ScaleX() {
        return this.f9677r;
    }

    public float getRect2TranslationX() {
        return this.f9678s;
    }

    public final void draw(Canvas canvas) {
        if (!getBounds().isEmpty() && isVisible() && canvas.getClipBounds(this.f9680u)) {
            canvas.save();
            float height = (float) this.f9680u.height();
            float f = (float) this.f9665f;
            if (height > f) {
                canvas.translate(0.0f, (height - f) / 2.0f);
            }
            canvas.scale(((float) this.f9680u.width()) / 360.0f, ((float) this.f9665f) / 4.0f);
            canvas.translate(180.0f, 2.0f);
            canvas.clipRect(-180.0f, -2.0f, 180.0f, 2.0f);
            if (this.f9679t < 1.0f) {
                if (this.f9668i == 0) {
                    canvas.scale(1.0f, -1.0f);
                }
                canvas.translate(0.0f, (this.f9679t - 4.0f) * 4.0f * 0.5f);
                canvas.scale(1.0f, this.f9679t);
            }
            int i = this.f9666g;
            if (i == -1) {
                this.f9669j.setColor(this.f9667h);
                this.f9669j.setAlpha((int) (this.f9674o * ((float) this.f9663d)));
            } else {
                this.f9669j.setColor(i);
                this.f9669j.setAlpha((int) (this.f9674o * ((float) this.f9663d)));
            }
            canvas.drawRect(-180.0f, -2.0f, 180.0f, 2.0f, this.f9669j);
            this.f9669j.setColor(this.f9667h);
            if (this.f9664e) {
                canvas.scale(-1.0f, 1.0f);
            }
            this.f9669j.setAlpha((int) (this.f9674o * 255.0f));
            canvas.save();
            canvas.translate(this.f9676q, 0.0f);
            canvas.scale(this.f9675p, 1.0f);
            canvas.drawRect(-144.0f, -2.0f, 144.0f, 2.0f, this.f9669j);
            canvas.restore();
            canvas.save();
            canvas.translate(this.f9678s, 0.0f);
            canvas.scale(this.f9677r, 1.0f);
            canvas.drawRect(-144.0f, -2.0f, 144.0f, 2.0f, this.f9669j);
            canvas.restore();
            canvas.restore();
        }
    }

    /* renamed from: a */
    public final void mo5606a(Runnable runnable) {
        this.f9662c = runnable;
        stop();
    }

    /* renamed from: a */
    public final void mo5605a() {
        this.f9661b = false;
        if (super.setVisible(false, false)) {
            this.f9670k.cancel();
            this.f9671l.cancel();
            this.f9660a.cancel();
            Runnable runnable = this.f9662c;
            if (runnable != null) {
                runnable.run();
            }
        }
        this.f9662c = null;
    }

    public final boolean isRunning() {
        return isVisible();
    }

    /* renamed from: b */
    public final void mo5705b() {
        this.f9670k.cancel();
        this.f9671l.cancel();
        this.f9660a.cancel();
        this.f9675p = 0.1f;
        this.f9676q = -522.6f;
        this.f9677r = 0.1f;
        this.f9678s = -197.6f;
        this.f9679t = this.f9673n;
    }

    public final void setAlpha(int i) {
        this.f9674o = (float) i;
        invalidateSelf();
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f9669j.setColorFilter(colorFilter);
        invalidateSelf();
    }

    public void setGrowScale(float f) {
        this.f9679t = f;
        invalidateSelf();
    }

    public void setRect1ScaleX(float f) {
        this.f9675p = f;
        invalidateSelf();
    }

    public void setRect1TranslationX(float f) {
        this.f9676q = f;
        invalidateSelf();
    }

    public void setRect2ScaleX(float f) {
        this.f9677r = f;
        invalidateSelf();
    }

    public void setRect2TranslationX(float f) {
        this.f9678s = f;
        invalidateSelf();
    }

    public final boolean setVisible(boolean z, boolean z2) {
        boolean z3 = z != this.f9661b;
        if (!z3 && !z2) {
            return false;
        }
        this.f9661b = z;
        if (z) {
            super.setVisible(true, z2);
            if (z2) {
                mo5705b();
            }
            this.f9671l.cancel();
            this.f9670k.setFloatValues(new float[]{this.f9672m});
            this.f9670k.start();
            if (!this.f9660a.isStarted()) {
                this.f9660a.start();
            }
            this.f9662c = null;
        } else if (z3) {
            this.f9670k.cancel();
            this.f9671l.setFloatValues(new float[]{this.f9673n});
            this.f9671l.start();
        }
        return z3;
    }

    public final void start() {
        setVisible(true, true);
    }

    public final void stop() {
        setVisible(false, false);
    }
}
