package p000;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;

/* renamed from: gfx */
/* compiled from: PG */
public class gfx extends C0601wa {

    /* renamed from: a */
    private Drawable f11221a;

    /* renamed from: b */
    private final Rect f11222b;

    /* renamed from: c */
    private final Rect f11223c;

    /* renamed from: d */
    private int f11224d;

    /* renamed from: e */
    private boolean f11225e;

    /* renamed from: i */
    private boolean f11226i;

    public gfx(Context context) {
        this(context, (AttributeSet) null);
    }

    public final Drawable getForeground() {
        return this.f11221a;
    }

    public final int getForegroundGravity() {
        return this.f11224d;
    }

    public gfx(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public gfx(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11222b = new Rect();
        this.f11223c = new Rect();
        this.f11224d = 119;
        this.f11225e = true;
        this.f11226i = false;
        TypedArray a = gga.m10238a(context, attributeSet, gfz.f11228a, i, 0, new int[0]);
        this.f11224d = a.getInt(1, this.f11224d);
        Drawable drawable = a.getDrawable(0);
        if (drawable != null) {
            setForeground(drawable);
        }
        this.f11225e = a.getBoolean(2, true);
        a.recycle();
    }

    public final void draw(Canvas canvas) {
        super.draw(canvas);
        Drawable drawable = this.f11221a;
        if (drawable != null) {
            if (this.f11226i) {
                this.f11226i = false;
                Rect rect = this.f11222b;
                Rect rect2 = this.f11223c;
                int right = getRight() - getLeft();
                int bottom = getBottom() - getTop();
                if (this.f11225e) {
                    rect.set(0, 0, right, bottom);
                } else {
                    rect.set(getPaddingLeft(), getPaddingTop(), right - getPaddingRight(), bottom - getPaddingBottom());
                }
                Gravity.apply(this.f11224d, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), rect, rect2);
                drawable.setBounds(rect2);
            }
            drawable.draw(canvas);
        }
    }

    public final void drawableHotspotChanged(float f, float f2) {
        super.drawableHotspotChanged(f, f2);
        Drawable drawable = this.f11221a;
        if (drawable != null) {
            drawable.setHotspot(f, f2);
        }
    }

    /* access modifiers changed from: protected */
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        Drawable drawable = this.f11221a;
        if (drawable != null && drawable.isStateful()) {
            this.f11221a.setState(getDrawableState());
        }
    }

    public final void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.f11221a;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.f11226i = z | this.f11226i;
    }

    /* access modifiers changed from: protected */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.f11226i = true;
    }

    public final void setForeground(Drawable drawable) {
        Drawable drawable2 = this.f11221a;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                drawable2.setCallback((Drawable.Callback) null);
                unscheduleDrawable(this.f11221a);
            }
            this.f11221a = drawable;
            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
                if (this.f11224d == 119) {
                    drawable.getPadding(new Rect());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

    public final void setForegroundGravity(int i) {
        if (this.f11224d != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.f11224d = i;
            if (i == 119 && this.f11221a != null) {
                this.f11221a.getPadding(new Rect());
            }
            requestLayout();
        }
    }

    /* access modifiers changed from: protected */
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.f11221a;
    }
}
