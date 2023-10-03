package com.android.p032ex.chips;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: com.android.ex.chips.CircularImageView */
public class CircularImageView extends ImageView {

    /* renamed from: Jj */
    private static float f750Jj = 1.0f;

    /* renamed from: Gj */
    private final RectF f751Gj;

    /* renamed from: Hj */
    private final Paint f752Hj;

    /* renamed from: Ij */
    private final Paint f753Ij;
    private final Matrix matrix;
    private final RectF source;

    public CircularImageView(Context context) {
        this(context, (AttributeSet) null, 0);
    }

    /* renamed from: a */
    public void mo5441a(Bitmap bitmap, Canvas canvas, RectF rectF, RectF rectF2) {
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        this.matrix.reset();
        this.matrix.setRectToRect(rectF, rectF2, Matrix.ScaleToFit.FILL);
        bitmapShader.setLocalMatrix(this.matrix);
        this.f752Hj.setShader(bitmapShader);
        canvas.drawCircle(rectF2.centerX(), rectF2.centerY(), rectF2.width() / 2.0f, this.f752Hj);
        canvas.drawCircle(rectF2.centerX(), rectF2.centerY(), (rectF2.width() / 2.0f) - (f750Jj / 2.0f), this.f753Ij);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        BitmapDrawable bitmapDrawable;
        Bitmap bitmap;
        Drawable drawable = getDrawable();
        if (drawable instanceof StateListDrawable) {
            bitmapDrawable = ((StateListDrawable) drawable).getCurrent() != null ? (BitmapDrawable) drawable.getCurrent() : null;
        } else {
            bitmapDrawable = (BitmapDrawable) drawable;
        }
        if (bitmapDrawable != null && (bitmap = bitmapDrawable.getBitmap()) != null) {
            this.source.set(0.0f, 0.0f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
            this.f751Gj.set((float) getPaddingLeft(), (float) getPaddingTop(), (float) (getWidth() - getPaddingRight()), (float) (getHeight() - getPaddingBottom()));
            mo5441a(bitmap, canvas, this.source, this.f751Gj);
        }
    }

    public CircularImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CircularImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.matrix = new Matrix();
        this.source = new RectF();
        this.f751Gj = new RectF();
        this.f752Hj = new Paint();
        this.f752Hj.setAntiAlias(true);
        this.f752Hj.setFilterBitmap(true);
        this.f752Hj.setDither(true);
        this.f753Ij = new Paint();
        this.f753Ij.setColor(0);
        this.f753Ij.setStyle(Paint.Style.STROKE);
        this.f753Ij.setStrokeWidth(f750Jj);
        this.f753Ij.setAntiAlias(true);
    }
}
