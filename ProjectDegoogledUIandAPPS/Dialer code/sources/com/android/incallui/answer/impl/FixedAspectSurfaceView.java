package com.android.incallui.answer.impl;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import com.android.dialer.common.Assert;

public class FixedAspectSurfaceView extends SurfaceView {
    private float aspectRatio;
    private final boolean scaleHeight;
    private final boolean scaleWidth;

    public FixedAspectSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.FixedAspectSurfaceView, 0, 0);
        boolean z = true;
        this.scaleHeight = obtainStyledAttributes.getBoolean(1, false);
        this.scaleWidth = obtainStyledAttributes.getBoolean(2, false);
        Assert.checkArgument(this.scaleHeight == this.scaleWidth ? false : z, "Must either scale width or height", new Object[0]);
        setAspectRatio(obtainStyledAttributes.getFloat(0, 1.0f));
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (this.scaleWidth) {
            size = (int) (((float) size2) * this.aspectRatio);
        } else if (this.scaleHeight) {
            size2 = (int) (((float) size) / this.aspectRatio);
        }
        setMeasuredDimension(View.resolveSizeAndState(size, i, 0), View.resolveSizeAndState(size2, i2, 0));
    }

    public void setAspectRatio(float f) {
        Assert.checkArgument(f >= 0.0f, "Aspect ratio must be positive", new Object[0]);
        this.aspectRatio = f;
        requestLayout();
    }
}
