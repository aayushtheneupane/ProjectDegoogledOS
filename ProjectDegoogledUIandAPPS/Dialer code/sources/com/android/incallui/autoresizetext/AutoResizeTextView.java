package com.android.incallui.autoresizetext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.widget.TextView;

public class AutoResizeTextView extends TextView {
    private final RectF availableSpaceRect = new RectF();
    private final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
    private float maxTextSize;
    private int maxWidth;
    private float minTextSize = 16.0f;
    private int resizeStepUnit = 0;
    private final TextPaint textPaint = new TextPaint();
    private final SparseIntArray textSizesCache = new SparseIntArray();

    public AutoResizeTextView(Context context) {
        super(context, (AttributeSet) null, 0);
        initialize(context, (AttributeSet) null, 0, 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00df, code lost:
        if (r8.getLineCount() > r7) goto L_0x00ee;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00eb, code lost:
        if (((float) r8.getHeight()) > r3.bottom) goto L_0x00ee;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00fa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void adjustTextSize() {
        /*
            r20 = this;
            r0 = r20
            int r1 = r20.getMeasuredWidth()
            int r2 = r20.getPaddingLeft()
            int r1 = r1 - r2
            int r2 = r20.getPaddingRight()
            int r1 = r1 - r2
            int r2 = r20.getMeasuredHeight()
            int r3 = r20.getPaddingBottom()
            int r2 = r2 - r3
            int r3 = r20.getPaddingTop()
            int r2 = r2 - r3
            if (r1 <= 0) goto L_0x0110
            if (r2 > 0) goto L_0x0024
            goto L_0x0110
        L_0x0024:
            r0.maxWidth = r1
            android.graphics.RectF r3 = r0.availableSpaceRect
            float r1 = (float) r1
            r3.right = r1
            float r1 = (float) r2
            r3.bottom = r1
            float r1 = r0.minTextSize
            int r2 = r0.resizeStepUnit
            android.util.DisplayMetrics r3 = r0.displayMetrics
            r4 = 1065353216(0x3f800000, float:1.0)
            float r2 = android.util.TypedValue.applyDimension(r2, r4, r3)
            float r2 = r4 / r2
            float r2 = r2 * r1
            double r1 = (double) r2
            double r1 = java.lang.Math.ceil(r1)
            int r1 = (int) r1
            float r2 = r0.maxTextSize
            int r3 = r0.resizeStepUnit
            android.util.DisplayMetrics r5 = r0.displayMetrics
            float r3 = android.util.TypedValue.applyDimension(r3, r4, r5)
            float r4 = r4 / r3
            float r4 = r4 * r2
            double r2 = (double) r4
            double r2 = java.lang.Math.floor(r2)
            int r2 = (int) r2
            android.graphics.RectF r3 = r0.availableSpaceRect
            java.lang.CharSequence r4 = r20.getText()
            if (r4 == 0) goto L_0x0076
            android.util.SparseIntArray r5 = r0.textSizesCache
            int r6 = r4.hashCode()
            int r5 = r5.get(r6)
            if (r5 == 0) goto L_0x0076
            android.util.SparseIntArray r1 = r0.textSizesCache
            int r2 = r4.hashCode()
            int r1 = r1.get(r2)
            float r1 = (float) r1
            goto L_0x010b
        L_0x0076:
            int r5 = r1 + 1
            r19 = r2
            r2 = r1
            r1 = r5
            r5 = r19
        L_0x007e:
            r6 = 0
            if (r1 > r5) goto L_0x00fe
            int r2 = r1 + r5
            int r2 = r2 / 2
            int r7 = r0.resizeStepUnit
            float r8 = (float) r2
            android.util.DisplayMetrics r9 = r0.displayMetrics
            float r7 = android.util.TypedValue.applyDimension(r7, r8, r9)
            android.text.TextPaint r8 = r0.textPaint
            r8.setTextSize(r7)
            java.lang.CharSequence r7 = r20.getText()
            java.lang.String r9 = r7.toString()
            int r7 = r20.getMaxLines()
            r15 = 1
            if (r7 != r15) goto L_0x00bd
            android.text.TextPaint r7 = r0.textPaint
            float r7 = r7.getFontSpacing()
            float r8 = r3.bottom
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 > 0) goto L_0x00ee
            android.text.TextPaint r7 = r0.textPaint
            float r7 = r7.measureText(r9)
            float r8 = r3.right
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 > 0) goto L_0x00ee
            r18 = r15
            goto L_0x00f0
        L_0x00bd:
            android.text.StaticLayout r16 = new android.text.StaticLayout
            android.text.TextPaint r10 = r0.textPaint
            int r11 = r0.maxWidth
            android.text.Layout$Alignment r12 = android.text.Layout.Alignment.ALIGN_NORMAL
            float r13 = r20.getLineSpacingMultiplier()
            float r14 = r20.getLineSpacingExtra()
            r17 = 1
            r8 = r16
            r18 = r15
            r15 = r17
            r8.<init>(r9, r10, r11, r12, r13, r14, r15)
            r8 = -1
            if (r7 == r8) goto L_0x00e2
            int r8 = r16.getLineCount()
            if (r8 <= r7) goto L_0x00e2
            goto L_0x00ee
        L_0x00e2:
            int r7 = r16.getHeight()
            float r7 = (float) r7
            float r8 = r3.bottom
            int r7 = (r7 > r8 ? 1 : (r7 == r8 ? 0 : -1))
            if (r7 > 0) goto L_0x00ee
            goto L_0x00f0
        L_0x00ee:
            r18 = r6
        L_0x00f0:
            if (r18 == 0) goto L_0x00fa
            int r2 = r2 + 1
            r19 = r2
            r2 = r1
            r1 = r19
            goto L_0x007e
        L_0x00fa:
            int r2 = r2 + -1
            r5 = r2
            goto L_0x007e
        L_0x00fe:
            android.util.SparseIntArray r1 = r0.textSizesCache
            if (r4 != 0) goto L_0x0103
            goto L_0x0107
        L_0x0103:
            int r6 = r4.hashCode()
        L_0x0107:
            r1.put(r6, r2)
            float r1 = (float) r2
        L_0x010b:
            int r2 = r0.resizeStepUnit
            super.setTextSize(r2, r1)
        L_0x0110:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.incallui.autoresizetext.AutoResizeTextView.adjustTextSize():void");
    }

    private void initialize(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.AutoResizeTextView, i, i2);
        this.resizeStepUnit = obtainStyledAttributes.getInt(1, 0);
        this.minTextSize = (float) ((int) obtainStyledAttributes.getDimension(0, 16.0f));
        this.maxTextSize = (float) ((int) getTextSize());
        obtainStyledAttributes.recycle();
        this.textPaint.set(getPaint());
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        adjustTextSize();
        super.onMeasure(i, i2);
    }

    /* access modifiers changed from: protected */
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (i != i3 || i2 != i4) {
            this.textSizesCache.clear();
            adjustTextSize();
        }
    }

    /* access modifiers changed from: protected */
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        adjustTextSize();
    }

    public final void setTextSize(int i, float f) {
        float applyDimension = TypedValue.applyDimension(i, f, this.displayMetrics);
        if (this.maxTextSize != applyDimension) {
            this.maxTextSize = applyDimension;
            this.textSizesCache.clear();
            requestLayout();
        }
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        initialize(context, attributeSet, 0, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context, attributeSet, i, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        initialize(context, attributeSet, i, i2);
    }
}
