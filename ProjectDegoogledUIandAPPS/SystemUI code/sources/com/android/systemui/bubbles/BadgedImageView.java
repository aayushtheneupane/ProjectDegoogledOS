package com.android.systemui.bubbles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.android.internal.graphics.ColorUtils;
import com.android.launcher3.icons.DotRenderer;
import com.android.systemui.C1775R$dimen;

public class BadgedImageView extends ImageView {
    private int mDotColor;
    private DotRenderer mDotRenderer;
    private float mDotScale;
    private DotRenderer.DrawParams mDrawParams;
    private int mIconBitmapSize;
    private boolean mOnLeft;
    private boolean mShowDot;
    private Rect mTempBounds;

    public BadgedImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public BadgedImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BadgedImageView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public BadgedImageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTempBounds = new Rect();
        this.mDotScale = 0.0f;
        this.mIconBitmapSize = getResources().getDimensionPixelSize(C1775R$dimen.bubble_icon_bitmap_size);
        this.mDrawParams = new DotRenderer.DrawParams();
        context.obtainStyledAttributes(new int[]{16844002}).recycle();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.mShowDot) {
            getDrawingRect(this.mTempBounds);
            DotRenderer.DrawParams drawParams = this.mDrawParams;
            drawParams.color = this.mDotColor;
            drawParams.iconBounds = this.mTempBounds;
            drawParams.leftAlign = this.mOnLeft;
            drawParams.scale = this.mDotScale;
            if (this.mDotRenderer == null) {
                Path path = new Path();
                path.addCircle(50.0f, 50.0f, 50.0f, Path.Direction.CW);
                this.mDotRenderer = new DotRenderer(this.mIconBitmapSize, path, 100);
            }
            this.mDotRenderer.draw(canvas, this.mDrawParams);
        }
    }

    /* access modifiers changed from: package-private */
    public void setDotOnLeft(boolean z) {
        this.mOnLeft = z;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public boolean getDotOnLeft() {
        return this.mOnLeft;
    }

    /* access modifiers changed from: package-private */
    public void setShowDot(boolean z) {
        this.mShowDot = z;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public boolean isShowingDot() {
        return this.mShowDot;
    }

    public void setDotColor(int i) {
        this.mDotColor = ColorUtils.setAlphaComponent(i, 255);
        invalidate();
    }

    public void drawDot(Path path) {
        this.mDotRenderer = new DotRenderer(this.mIconBitmapSize, path, 100);
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public void setDotScale(float f) {
        this.mDotScale = f;
        invalidate();
    }

    /* access modifiers changed from: package-private */
    public float[] getDotCenter() {
        float[] fArr;
        if (this.mOnLeft) {
            fArr = this.mDotRenderer.getLeftDotPosition();
        } else {
            fArr = this.mDotRenderer.getRightDotPosition();
        }
        getDrawingRect(this.mTempBounds);
        return new float[]{((float) this.mTempBounds.width()) * fArr[0], ((float) this.mTempBounds.height()) * fArr[1]};
    }
}
