package com.android.launcher3.icons;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.Drawable;
import java.nio.ByteBuffer;

public class IconNormalizer {
    private final RectF mAdaptiveIconBounds;
    private float mAdaptiveIconScale;
    private final Bitmap mBitmap;
    private final Rect mBounds;
    private final Canvas mCanvas = new Canvas(this.mBitmap);
    private boolean mEnableShapeDetection;
    private final float[] mLeftBorder;
    private final Matrix mMatrix;
    private final int mMaxSize;
    private final Paint mPaintMaskShape;
    private final Paint mPaintMaskShapeOutline;
    private final byte[] mPixels;
    private final float[] mRightBorder;
    private final Path mShapePath;

    IconNormalizer(Context context, int i, boolean z) {
        this.mMaxSize = i * 2;
        int i2 = this.mMaxSize;
        this.mBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ALPHA_8);
        int i3 = this.mMaxSize;
        this.mPixels = new byte[(i3 * i3)];
        this.mLeftBorder = new float[i3];
        this.mRightBorder = new float[i3];
        this.mBounds = new Rect();
        this.mAdaptiveIconBounds = new RectF();
        this.mPaintMaskShape = new Paint();
        this.mPaintMaskShape.setColor(-65536);
        this.mPaintMaskShape.setStyle(Paint.Style.FILL);
        this.mPaintMaskShape.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.XOR));
        this.mPaintMaskShapeOutline = new Paint();
        this.mPaintMaskShapeOutline.setStrokeWidth(context.getResources().getDisplayMetrics().density * 2.0f);
        this.mPaintMaskShapeOutline.setStyle(Paint.Style.STROKE);
        this.mPaintMaskShapeOutline.setColor(-16777216);
        this.mPaintMaskShapeOutline.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        this.mShapePath = new Path();
        this.mMatrix = new Matrix();
        this.mAdaptiveIconScale = 0.0f;
        this.mEnableShapeDetection = z;
    }

    private static float getScale(float f, float f2, float f3) {
        float f4 = f / f2;
        float f5 = f4 < 0.7853982f ? 0.6597222f : ((1.0f - f4) * 0.040449437f) + 0.6510417f;
        float f6 = f / f3;
        if (f6 > f5) {
            return (float) Math.sqrt((double) (f5 / f6));
        }
        return 1.0f;
    }

    @TargetApi(26)
    public static float normalizeAdaptiveIcon(Drawable drawable, int i, RectF rectF) {
        Rect rect = new Rect(drawable.getBounds());
        drawable.setBounds(0, 0, i, i);
        Path iconMask = ((AdaptiveIconDrawable) drawable).getIconMask();
        Region region = new Region();
        region.setPath(iconMask, new Region(0, 0, i, i));
        Rect bounds = region.getBounds();
        int area = GraphicsUtils.getArea(region);
        if (rectF != null) {
            float f = (float) i;
            rectF.set(((float) bounds.left) / f, ((float) bounds.top) / f, 1.0f - (((float) bounds.right) / f), 1.0f - (((float) bounds.bottom) / f));
        }
        drawable.setBounds(rect);
        float f2 = (float) area;
        return getScale(f2, f2, (float) (i * i));
    }

    private boolean isShape(Path path) {
        if (Math.abs((((float) this.mBounds.width()) / ((float) this.mBounds.height())) - 1.0f) > 0.05f) {
            return false;
        }
        this.mMatrix.reset();
        this.mMatrix.setScale((float) this.mBounds.width(), (float) this.mBounds.height());
        Matrix matrix = this.mMatrix;
        Rect rect = this.mBounds;
        matrix.postTranslate((float) rect.left, (float) rect.top);
        path.transform(this.mMatrix, this.mShapePath);
        this.mCanvas.drawPath(this.mShapePath, this.mPaintMaskShape);
        this.mCanvas.drawPath(this.mShapePath, this.mPaintMaskShapeOutline);
        return isTransparentBitmap();
    }

    private boolean isTransparentBitmap() {
        Rect rect;
        ByteBuffer wrap = ByteBuffer.wrap(this.mPixels);
        wrap.rewind();
        this.mBitmap.copyPixelsToBuffer(wrap);
        Rect rect2 = this.mBounds;
        int i = rect2.top;
        int i2 = this.mMaxSize;
        int i3 = i * i2;
        int i4 = i2 - rect2.right;
        int i5 = i3;
        int i6 = 0;
        while (true) {
            rect = this.mBounds;
            if (i >= rect.bottom) {
                break;
            }
            int i7 = rect.left;
            int i8 = i5 + i7;
            while (i7 < this.mBounds.right) {
                if ((this.mPixels[i8] & 255) > 40) {
                    i6++;
                }
                i8++;
                i7++;
            }
            i5 = i8 + i4;
            i++;
        }
        if (((float) i6) / ((float) (rect.width() * this.mBounds.height())) < 0.005f) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:74:0x0154, code lost:
        return 1.0f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized float getScale(android.graphics.drawable.Drawable r18, android.graphics.RectF r19, android.graphics.Path r20, boolean[] r21) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r19
            r3 = r21
            monitor-enter(r17)
            boolean r4 = com.android.launcher3.icons.BaseIconFactory.ATLEAST_OREO     // Catch:{ all -> 0x0155 }
            r5 = 0
            if (r4 == 0) goto L_0x002d
            boolean r4 = r0 instanceof android.graphics.drawable.AdaptiveIconDrawable     // Catch:{ all -> 0x0155 }
            if (r4 == 0) goto L_0x002d
            float r3 = r1.mAdaptiveIconScale     // Catch:{ all -> 0x0155 }
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0022
            int r3 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            android.graphics.RectF r4 = r1.mAdaptiveIconBounds     // Catch:{ all -> 0x0155 }
            float r0 = normalizeAdaptiveIcon(r0, r3, r4)     // Catch:{ all -> 0x0155 }
            r1.mAdaptiveIconScale = r0     // Catch:{ all -> 0x0155 }
        L_0x0022:
            if (r2 == 0) goto L_0x0029
            android.graphics.RectF r0 = r1.mAdaptiveIconBounds     // Catch:{ all -> 0x0155 }
            r2.set(r0)     // Catch:{ all -> 0x0155 }
        L_0x0029:
            float r0 = r1.mAdaptiveIconScale     // Catch:{ all -> 0x0155 }
            monitor-exit(r17)
            return r0
        L_0x002d:
            int r4 = r18.getIntrinsicWidth()     // Catch:{ all -> 0x0155 }
            int r6 = r18.getIntrinsicHeight()     // Catch:{ all -> 0x0155 }
            if (r4 <= 0) goto L_0x0051
            if (r6 > 0) goto L_0x003a
            goto L_0x0051
        L_0x003a:
            int r7 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            if (r4 > r7) goto L_0x0042
            int r7 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            if (r6 <= r7) goto L_0x0061
        L_0x0042:
            int r7 = java.lang.Math.max(r4, r6)     // Catch:{ all -> 0x0155 }
            int r8 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            int r8 = r8 * r4
            int r4 = r8 / r7
            int r8 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            int r8 = r8 * r6
            int r6 = r8 / r7
            goto L_0x0061
        L_0x0051:
            if (r4 <= 0) goto L_0x0057
            int r7 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            if (r4 <= r7) goto L_0x0059
        L_0x0057:
            int r4 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
        L_0x0059:
            if (r6 <= 0) goto L_0x005f
            int r7 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            if (r6 <= r7) goto L_0x0061
        L_0x005f:
            int r6 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
        L_0x0061:
            android.graphics.Bitmap r7 = r1.mBitmap     // Catch:{ all -> 0x0155 }
            r8 = 0
            r7.eraseColor(r8)     // Catch:{ all -> 0x0155 }
            r0.setBounds(r8, r8, r4, r6)     // Catch:{ all -> 0x0155 }
            android.graphics.Canvas r7 = r1.mCanvas     // Catch:{ all -> 0x0155 }
            r0.draw(r7)     // Catch:{ all -> 0x0155 }
            byte[] r0 = r1.mPixels     // Catch:{ all -> 0x0155 }
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)     // Catch:{ all -> 0x0155 }
            r0.rewind()     // Catch:{ all -> 0x0155 }
            android.graphics.Bitmap r7 = r1.mBitmap     // Catch:{ all -> 0x0155 }
            r7.copyPixelsToBuffer(r0)     // Catch:{ all -> 0x0155 }
            int r0 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            r7 = 1
            int r0 = r0 + r7
            int r9 = r1.mMaxSize     // Catch:{ all -> 0x0155 }
            int r9 = r9 - r4
            r10 = -1
            r15 = r0
            r0 = r8
            r14 = r0
            r11 = r10
            r12 = r11
            r13 = r12
        L_0x008b:
            if (r0 >= r6) goto L_0x00ce
            r5 = r10
            r16 = r14
            r14 = r8
            r8 = r5
        L_0x0092:
            if (r14 >= r4) goto L_0x00aa
            byte[] r7 = r1.mPixels     // Catch:{ all -> 0x0155 }
            byte r7 = r7[r16]     // Catch:{ all -> 0x0155 }
            r7 = r7 & 255(0xff, float:3.57E-43)
            r3 = 40
            if (r7 <= r3) goto L_0x00a2
            if (r5 != r10) goto L_0x00a1
            r5 = r14
        L_0x00a1:
            r8 = r14
        L_0x00a2:
            int r16 = r16 + 1
            int r14 = r14 + 1
            r3 = r21
            r7 = 1
            goto L_0x0092
        L_0x00aa:
            int r14 = r16 + r9
            float[] r3 = r1.mLeftBorder     // Catch:{ all -> 0x0155 }
            float r7 = (float) r5     // Catch:{ all -> 0x0155 }
            r3[r0] = r7     // Catch:{ all -> 0x0155 }
            float[] r3 = r1.mRightBorder     // Catch:{ all -> 0x0155 }
            float r7 = (float) r8     // Catch:{ all -> 0x0155 }
            r3[r0] = r7     // Catch:{ all -> 0x0155 }
            if (r5 == r10) goto L_0x00c6
            if (r11 != r10) goto L_0x00bb
            r11 = r0
        L_0x00bb:
            int r3 = java.lang.Math.min(r15, r5)     // Catch:{ all -> 0x0155 }
            int r5 = java.lang.Math.max(r12, r8)     // Catch:{ all -> 0x0155 }
            r13 = r0
            r15 = r3
            r12 = r5
        L_0x00c6:
            int r0 = r0 + 1
            r3 = r21
            r5 = 0
            r7 = 1
            r8 = 0
            goto L_0x008b
        L_0x00ce:
            r0 = 1065353216(0x3f800000, float:1.0)
            if (r11 == r10) goto L_0x0153
            if (r12 != r10) goto L_0x00d6
            goto L_0x0153
        L_0x00d6:
            float[] r3 = r1.mLeftBorder     // Catch:{ all -> 0x0155 }
            r5 = 1
            convertToConvexArray(r3, r5, r11, r13)     // Catch:{ all -> 0x0155 }
            float[] r3 = r1.mRightBorder     // Catch:{ all -> 0x0155 }
            convertToConvexArray(r3, r10, r11, r13)     // Catch:{ all -> 0x0155 }
            r3 = 0
            r5 = 0
        L_0x00e3:
            if (r3 >= r6) goto L_0x00fe
            float[] r7 = r1.mLeftBorder     // Catch:{ all -> 0x0155 }
            r7 = r7[r3]     // Catch:{ all -> 0x0155 }
            r8 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 > 0) goto L_0x00f0
            goto L_0x00fb
        L_0x00f0:
            float[] r7 = r1.mRightBorder     // Catch:{ all -> 0x0155 }
            r7 = r7[r3]     // Catch:{ all -> 0x0155 }
            float[] r8 = r1.mLeftBorder     // Catch:{ all -> 0x0155 }
            r8 = r8[r3]     // Catch:{ all -> 0x0155 }
            float r7 = r7 - r8
            float r7 = r7 + r0
            float r5 = r5 + r7
        L_0x00fb:
            int r3 = r3 + 1
            goto L_0x00e3
        L_0x00fe:
            android.graphics.Rect r3 = r1.mBounds     // Catch:{ all -> 0x0155 }
            r3.left = r15     // Catch:{ all -> 0x0155 }
            android.graphics.Rect r3 = r1.mBounds     // Catch:{ all -> 0x0155 }
            r3.right = r12     // Catch:{ all -> 0x0155 }
            android.graphics.Rect r3 = r1.mBounds     // Catch:{ all -> 0x0155 }
            r3.top = r11     // Catch:{ all -> 0x0155 }
            android.graphics.Rect r3 = r1.mBounds     // Catch:{ all -> 0x0155 }
            r3.bottom = r13     // Catch:{ all -> 0x0155 }
            if (r2 == 0) goto L_0x0130
            android.graphics.Rect r3 = r1.mBounds     // Catch:{ all -> 0x0155 }
            int r3 = r3.left     // Catch:{ all -> 0x0155 }
            float r3 = (float) r3     // Catch:{ all -> 0x0155 }
            float r7 = (float) r4     // Catch:{ all -> 0x0155 }
            float r3 = r3 / r7
            android.graphics.Rect r8 = r1.mBounds     // Catch:{ all -> 0x0155 }
            int r8 = r8.top     // Catch:{ all -> 0x0155 }
            float r8 = (float) r8     // Catch:{ all -> 0x0155 }
            float r9 = (float) r6     // Catch:{ all -> 0x0155 }
            float r8 = r8 / r9
            android.graphics.Rect r10 = r1.mBounds     // Catch:{ all -> 0x0155 }
            int r10 = r10.right     // Catch:{ all -> 0x0155 }
            float r10 = (float) r10     // Catch:{ all -> 0x0155 }
            float r10 = r10 / r7
            float r7 = r0 - r10
            android.graphics.Rect r10 = r1.mBounds     // Catch:{ all -> 0x0155 }
            int r10 = r10.bottom     // Catch:{ all -> 0x0155 }
            float r10 = (float) r10     // Catch:{ all -> 0x0155 }
            float r10 = r10 / r9
            float r0 = r0 - r10
            r2.set(r3, r8, r7, r0)     // Catch:{ all -> 0x0155 }
        L_0x0130:
            r0 = r21
            if (r0 == 0) goto L_0x0144
            boolean r2 = r1.mEnableShapeDetection     // Catch:{ all -> 0x0155 }
            if (r2 == 0) goto L_0x0144
            int r2 = r0.length     // Catch:{ all -> 0x0155 }
            if (r2 <= 0) goto L_0x0144
            r2 = r20
            boolean r2 = r1.isShape(r2)     // Catch:{ all -> 0x0155 }
            r3 = 0
            r0[r3] = r2     // Catch:{ all -> 0x0155 }
        L_0x0144:
            r0 = 1
            int r13 = r13 + r0
            int r13 = r13 - r11
            int r12 = r12 + r0
            int r12 = r12 - r15
            int r13 = r13 * r12
            float r0 = (float) r13     // Catch:{ all -> 0x0155 }
            int r4 = r4 * r6
            float r2 = (float) r4     // Catch:{ all -> 0x0155 }
            float r0 = getScale(r5, r0, r2)     // Catch:{ all -> 0x0155 }
            monitor-exit(r17)
            return r0
        L_0x0153:
            monitor-exit(r17)
            return r0
        L_0x0155:
            r0 = move-exception
            monitor-exit(r17)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.icons.IconNormalizer.getScale(android.graphics.drawable.Drawable, android.graphics.RectF, android.graphics.Path, boolean[]):float");
    }

    private static void convertToConvexArray(float[] fArr, int i, int i2, int i3) {
        float[] fArr2 = new float[(fArr.length - 1)];
        int i4 = -1;
        float f = Float.MAX_VALUE;
        for (int i5 = i2 + 1; i5 <= i3; i5++) {
            if (fArr[i5] > -1.0f) {
                if (f == Float.MAX_VALUE) {
                    i4 = i2;
                } else {
                    float f2 = ((fArr[i5] - fArr[i4]) / ((float) (i5 - i4))) - f;
                    float f3 = (float) i;
                    if (f2 * f3 < 0.0f) {
                        while (i4 > i2) {
                            i4--;
                            if ((((fArr[i5] - fArr[i4]) / ((float) (i5 - i4))) - fArr2[i4]) * f3 >= 0.0f) {
                                break;
                            }
                        }
                    }
                }
                f = (fArr[i5] - fArr[i4]) / ((float) (i5 - i4));
                for (int i6 = i4; i6 < i5; i6++) {
                    fArr2[i6] = f;
                    fArr[i6] = fArr[i4] + (((float) (i6 - i4)) * f);
                }
                i4 = i5;
            }
        }
    }
}
