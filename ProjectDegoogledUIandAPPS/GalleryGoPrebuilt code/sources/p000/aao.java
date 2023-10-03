package p000;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* renamed from: aao */
/* compiled from: PG */
public final class aao extends Drawable {

    /* renamed from: a */
    public final float f29a;

    /* renamed from: b */
    public float f30b;

    /* renamed from: c */
    public boolean f31c = false;

    /* renamed from: d */
    public boolean f32d = true;

    /* renamed from: e */
    public final ColorStateList f33e;

    /* renamed from: f */
    private final Paint f34f;

    /* renamed from: g */
    private final RectF f35g;

    /* renamed from: h */
    private final Rect f36h;

    /* renamed from: i */
    private PorterDuffColorFilter f37i;

    /* renamed from: j */
    private ColorStateList f38j;

    /* renamed from: k */
    private PorterDuff.Mode f39k = PorterDuff.Mode.SRC_IN;

    public aao(ColorStateList colorStateList, float f) {
        this.f29a = f;
        this.f34f = new Paint(5);
        colorStateList = colorStateList == null ? ColorStateList.valueOf(0) : colorStateList;
        this.f33e = colorStateList;
        this.f34f.setColor(colorStateList.getColorForState(getState(), this.f33e.getDefaultColor()));
        this.f35g = new RectF();
        this.f36h = new Rect();
    }

    public final int getOpacity() {
        return -3;
    }

    /* renamed from: a */
    private final PorterDuffColorFilter m27a(ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public final void draw(Canvas canvas) {
        Paint paint = this.f34f;
        boolean z = false;
        if (this.f37i != null && paint.getColorFilter() == null) {
            paint.setColorFilter(this.f37i);
            z = true;
        }
        RectF rectF = this.f35g;
        float f = this.f29a;
        canvas.drawRoundRect(rectF, f, f, paint);
        if (z) {
            paint.setColorFilter((ColorFilter) null);
        }
    }

    public final void getOutline(Outline outline) {
        outline.setRoundRect(this.f36h, this.f29a);
    }

    public final boolean isStateful() {
        ColorStateList colorStateList = this.f38j;
        if (colorStateList != null && colorStateList.isStateful()) {
            return true;
        }
        ColorStateList colorStateList2 = this.f33e;
        return (colorStateList2 != null && colorStateList2.isStateful()) || super.isStateful();
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        mo25a(rect);
    }

    /* access modifiers changed from: protected */
    public final boolean onStateChange(int[] iArr) {
        boolean z;
        PorterDuff.Mode mode;
        ColorStateList colorStateList = this.f33e;
        int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
        if (colorForState != this.f34f.getColor()) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            this.f34f.setColor(colorForState);
        }
        ColorStateList colorStateList2 = this.f38j;
        if (colorStateList2 == null || (mode = this.f39k) == null) {
            return z;
        }
        this.f37i = m27a(colorStateList2, mode);
        return true;
    }

    public final void setAlpha(int i) {
        this.f34f.setAlpha(i);
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f34f.setColorFilter(colorFilter);
    }

    public final void setTintList(ColorStateList colorStateList) {
        this.f38j = colorStateList;
        this.f37i = m27a(colorStateList, this.f39k);
        invalidateSelf();
    }

    public final void setTintMode(PorterDuff.Mode mode) {
        this.f39k = mode;
        this.f37i = m27a(this.f38j, mode);
        invalidateSelf();
    }

    /* renamed from: a */
    public final void mo25a(Rect rect) {
        if (rect == null) {
            rect = getBounds();
        }
        this.f35g.set((float) rect.left, (float) rect.top, (float) rect.right, (float) rect.bottom);
        this.f36h.set(rect);
        if (this.f31c) {
            float a = aap.m29a(this.f30b, this.f29a, this.f32d);
            this.f36h.inset((int) Math.ceil((double) aap.m30b(this.f30b, this.f29a, this.f32d)), (int) Math.ceil((double) a));
            this.f35g.set(this.f36h);
        }
    }
}
