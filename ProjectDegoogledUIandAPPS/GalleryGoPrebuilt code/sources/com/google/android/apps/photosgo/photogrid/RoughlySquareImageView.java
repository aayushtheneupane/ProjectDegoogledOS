package com.google.android.apps.photosgo.photogrid;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

/* compiled from: PG */
public final class RoughlySquareImageView extends C0533tn {

    /* renamed from: a */
    private int f4905a;

    public RoughlySquareImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public RoughlySquareImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoughlySquareImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4905a = -1;
    }

    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        m4860a(i, i2);
    }

    public final void setImageDrawable(Drawable drawable) {
        if (drawable != null) {
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            if (intrinsicWidth == intrinsicHeight) {
                this.f4905a = intrinsicWidth;
                m4860a(getWidth(), getHeight());
                super.setImageDrawable(drawable);
                return;
            }
            throw new IllegalArgumentException(String.format("RoughlySquareImageView only takes square images. Received: %dx%d", new Object[]{Integer.valueOf(intrinsicWidth), Integer.valueOf(intrinsicHeight)}));
        }
        this.f4905a = -1;
        super.setImageDrawable((Drawable) null);
    }

    /* renamed from: a */
    private final void m4860a(int i, int i2) {
        if (i != 0 && i2 != 0 && this.f4905a != -1) {
            int max = Math.max(i, i2);
            Matrix matrix = new Matrix();
            int i3 = this.f4905a;
            if (i3 < max) {
                float f = ((float) max) / ((float) i3);
                matrix.setScale(f, f);
            } else {
                float f2 = (float) ((int) ((((float) (max - i3)) * 0.5f) + 0.5f));
                matrix.postTranslate(f2, f2);
            }
            setImageMatrix(matrix);
        }
    }
}
