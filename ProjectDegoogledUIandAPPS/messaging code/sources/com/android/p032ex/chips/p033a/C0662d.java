package com.android.p032ex.chips.p033a;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.style.ReplacementSpan;

/* renamed from: com.android.ex.chips.a.d */
public class C0662d extends ReplacementSpan {

    /* renamed from: Ne */
    protected static final Paint f770Ne = new Paint();

    /* renamed from: Me */
    private float f771Me;
    protected Drawable mDrawable;

    public C0662d(Drawable drawable) {
        this.mDrawable = drawable;
    }

    /* renamed from: a */
    public void mo5477a(float f) {
        this.f771Me = f;
    }

    public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        canvas.save();
        canvas.translate(f, (float) (((i5 - this.mDrawable.getBounds().bottom) + i3) / 2));
        this.mDrawable.draw(canvas);
        canvas.restore();
    }

    /* access modifiers changed from: protected */
    public Rect getBounds() {
        return this.mDrawable.getBounds();
    }

    public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        f770Ne.set(paint);
        if (fontMetricsInt != null) {
            f770Ne.getFontMetricsInt(fontMetricsInt);
            Rect bounds = getBounds();
            int i3 = fontMetricsInt.descent - fontMetricsInt.ascent;
            int i4 = ((int) this.f771Me) / 2;
            int i5 = fontMetricsInt.top;
            fontMetricsInt.ascent = Math.min(i5, ((i3 - bounds.bottom) / 2) + i5) - i4;
            int i6 = fontMetricsInt.bottom;
            fontMetricsInt.descent = Math.max(i6, ((bounds.bottom - i3) / 2) + i6) + i4;
            fontMetricsInt.top = fontMetricsInt.ascent;
            fontMetricsInt.bottom = fontMetricsInt.descent;
        }
        return getBounds().right;
    }
}
