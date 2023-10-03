package com.google.android.material.shadow;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import androidx.core.graphics.ColorUtils;
import com.android.contacts.ContactPhotoManager;

public class ShadowRenderer {
    private static final int[] cornerColors = new int[4];
    private static final float[] cornerPositions = {ContactPhotoManager.OFFSET_DEFAULT, ContactPhotoManager.OFFSET_DEFAULT, 0.5f, 1.0f};
    private static final int[] edgeColors = new int[3];
    private static final float[] edgePositions = {ContactPhotoManager.OFFSET_DEFAULT, 0.5f, 1.0f};
    private final Paint cornerShadowPaint;
    private final Paint edgeShadowPaint;
    private final Path scratch;
    private int shadowEndColor;
    private int shadowMiddleColor;
    private final Paint shadowPaint;
    private int shadowStartColor;

    public ShadowRenderer() {
        this(-16777216);
    }

    public ShadowRenderer(int i) {
        this.scratch = new Path();
        setShadowColor(i);
        this.cornerShadowPaint = new Paint(4);
        this.cornerShadowPaint.setStyle(Paint.Style.FILL);
        this.shadowPaint = new Paint();
        this.shadowPaint.setColor(this.shadowStartColor);
        this.edgeShadowPaint = new Paint(this.cornerShadowPaint);
    }

    public void setShadowColor(int i) {
        this.shadowStartColor = ColorUtils.setAlphaComponent(i, 68);
        this.shadowMiddleColor = ColorUtils.setAlphaComponent(i, 20);
        this.shadowEndColor = ColorUtils.setAlphaComponent(i, 0);
    }

    public void drawEdgeShadow(Canvas canvas, Matrix matrix, RectF rectF, int i) {
        rectF.bottom += (float) i;
        rectF.offset(ContactPhotoManager.OFFSET_DEFAULT, (float) (-i));
        int[] iArr = edgeColors;
        iArr[0] = this.shadowEndColor;
        iArr[1] = this.shadowMiddleColor;
        iArr[2] = this.shadowStartColor;
        Paint paint = this.edgeShadowPaint;
        float f = rectF.left;
        paint.setShader(new LinearGradient(f, rectF.top, f, rectF.bottom, iArr, edgePositions, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        canvas.drawRect(rectF, this.edgeShadowPaint);
        canvas.restore();
    }

    public void drawCornerShadow(Canvas canvas, Matrix matrix, RectF rectF, int i, float f, float f2) {
        RectF rectF2 = rectF;
        int i2 = i;
        float f3 = f2;
        boolean z = f3 < ContactPhotoManager.OFFSET_DEFAULT;
        Path path = this.scratch;
        if (z) {
            int[] iArr = cornerColors;
            iArr[0] = 0;
            iArr[1] = this.shadowEndColor;
            iArr[2] = this.shadowMiddleColor;
            iArr[3] = this.shadowStartColor;
            float f4 = f;
        } else {
            path.rewind();
            path.moveTo(rectF.centerX(), rectF.centerY());
            path.arcTo(rectF2, f, f3);
            path.close();
            float f5 = (float) (-i2);
            rectF2.inset(f5, f5);
            int[] iArr2 = cornerColors;
            iArr2[0] = 0;
            iArr2[1] = this.shadowStartColor;
            iArr2[2] = this.shadowMiddleColor;
            iArr2[3] = this.shadowEndColor;
        }
        float width = 1.0f - (((float) i2) / (rectF.width() / 2.0f));
        float[] fArr = cornerPositions;
        fArr[1] = width;
        fArr[2] = ((1.0f - width) / 2.0f) + width;
        this.cornerShadowPaint.setShader(new RadialGradient(rectF.centerX(), rectF.centerY(), rectF.width() / 2.0f, cornerColors, cornerPositions, Shader.TileMode.CLAMP));
        canvas.save();
        canvas.concat(matrix);
        if (!z) {
            canvas.clipPath(path, Region.Op.DIFFERENCE);
        } else {
            Canvas canvas2 = canvas;
        }
        canvas.drawArc(rectF, f, f2, true, this.cornerShadowPaint);
        canvas.restore();
    }

    public Paint getShadowPaint() {
        return this.shadowPaint;
    }
}
