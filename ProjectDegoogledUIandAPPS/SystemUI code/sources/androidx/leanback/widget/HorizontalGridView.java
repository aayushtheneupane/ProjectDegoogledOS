package androidx.leanback.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import androidx.leanback.R$styleable;

public class HorizontalGridView extends BaseGridView {
    private boolean mFadingHighEdge;
    private boolean mFadingLowEdge;
    private LinearGradient mHighFadeShader;
    private int mHighFadeShaderLength;
    private int mHighFadeShaderOffset;
    private LinearGradient mLowFadeShader;
    private int mLowFadeShaderLength;
    private int mLowFadeShaderOffset;
    private Bitmap mTempBitmapHigh;
    private Bitmap mTempBitmapLow;
    private Paint mTempPaint;
    private Rect mTempRect;

    public HorizontalGridView(Context context) {
        this(context, (AttributeSet) null);
    }

    public HorizontalGridView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTempPaint = new Paint();
        this.mTempRect = new Rect();
        this.mLayoutManager.setOrientation(0);
        initAttributes(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void initAttributes(Context context, AttributeSet attributeSet) {
        initBaseGridViewAttributes(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.lbHorizontalGridView);
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, R$styleable.lbHorizontalGridView, attributeSet, obtainStyledAttributes, 0, 0);
        }
        setRowHeight(obtainStyledAttributes);
        setNumRows(obtainStyledAttributes.getInt(R$styleable.lbHorizontalGridView_numberOfRows, 1));
        obtainStyledAttributes.recycle();
        updateLayerType();
        this.mTempPaint = new Paint();
        this.mTempPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    }

    /* access modifiers changed from: package-private */
    public void setRowHeight(TypedArray typedArray) {
        if (typedArray.peekValue(R$styleable.lbHorizontalGridView_rowHeight) != null) {
            setRowHeight(typedArray.getLayoutDimension(R$styleable.lbHorizontalGridView_rowHeight, 0));
        }
    }

    public void setNumRows(int i) {
        this.mLayoutManager.setNumRows(i);
        requestLayout();
    }

    public void setRowHeight(int i) {
        this.mLayoutManager.setRowHeight(i);
        requestLayout();
    }

    private boolean needsFadingLowEdge() {
        if (!this.mFadingLowEdge) {
            return false;
        }
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (this.mLayoutManager.getOpticalLeft(getChildAt(i)) < getPaddingLeft() - this.mLowFadeShaderOffset) {
                return true;
            }
        }
        return false;
    }

    private boolean needsFadingHighEdge() {
        if (!this.mFadingHighEdge) {
            return false;
        }
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            if (this.mLayoutManager.getOpticalRight(getChildAt(childCount)) > (getWidth() - getPaddingRight()) + this.mHighFadeShaderOffset) {
                return true;
            }
        }
        return false;
    }

    private Bitmap getTempBitmapLow() {
        Bitmap bitmap = this.mTempBitmapLow;
        if (!(bitmap != null && bitmap.getWidth() == this.mLowFadeShaderLength && this.mTempBitmapLow.getHeight() == getHeight())) {
            this.mTempBitmapLow = Bitmap.createBitmap(this.mLowFadeShaderLength, getHeight(), Bitmap.Config.ARGB_8888);
        }
        return this.mTempBitmapLow;
    }

    private Bitmap getTempBitmapHigh() {
        Bitmap bitmap = this.mTempBitmapHigh;
        if (!(bitmap != null && bitmap.getWidth() == this.mHighFadeShaderLength && this.mTempBitmapHigh.getHeight() == getHeight())) {
            this.mTempBitmapHigh = Bitmap.createBitmap(this.mHighFadeShaderLength, getHeight(), Bitmap.Config.ARGB_8888);
        }
        return this.mTempBitmapHigh;
    }

    public void draw(Canvas canvas) {
        int i;
        Canvas canvas2 = canvas;
        boolean needsFadingLowEdge = needsFadingLowEdge();
        boolean needsFadingHighEdge = needsFadingHighEdge();
        if (!needsFadingLowEdge) {
            this.mTempBitmapLow = null;
        }
        if (!needsFadingHighEdge) {
            this.mTempBitmapHigh = null;
        }
        if (needsFadingLowEdge || needsFadingHighEdge) {
            int paddingLeft = this.mFadingLowEdge ? (getPaddingLeft() - this.mLowFadeShaderOffset) - this.mLowFadeShaderLength : 0;
            if (this.mFadingHighEdge) {
                i = (getWidth() - getPaddingRight()) + this.mHighFadeShaderOffset + this.mHighFadeShaderLength;
            } else {
                i = getWidth();
            }
            int save = canvas.save();
            canvas2.clipRect((this.mFadingLowEdge ? this.mLowFadeShaderLength : 0) + paddingLeft, 0, i - (this.mFadingHighEdge ? this.mHighFadeShaderLength : 0), getHeight());
            super.draw(canvas);
            canvas2.restoreToCount(save);
            Canvas canvas3 = new Canvas();
            Rect rect = this.mTempRect;
            rect.top = 0;
            rect.bottom = getHeight();
            if (needsFadingLowEdge && this.mLowFadeShaderLength > 0) {
                Bitmap tempBitmapLow = getTempBitmapLow();
                tempBitmapLow.eraseColor(0);
                canvas3.setBitmap(tempBitmapLow);
                int save2 = canvas3.save();
                canvas3.clipRect(0, 0, this.mLowFadeShaderLength, getHeight());
                float f = (float) (-paddingLeft);
                canvas3.translate(f, 0.0f);
                super.draw(canvas3);
                canvas3.restoreToCount(save2);
                this.mTempPaint.setShader(this.mLowFadeShader);
                canvas3.drawRect(0.0f, 0.0f, (float) this.mLowFadeShaderLength, (float) getHeight(), this.mTempPaint);
                Rect rect2 = this.mTempRect;
                rect2.left = 0;
                rect2.right = this.mLowFadeShaderLength;
                canvas2.translate((float) paddingLeft, 0.0f);
                Rect rect3 = this.mTempRect;
                canvas2.drawBitmap(tempBitmapLow, rect3, rect3, (Paint) null);
                canvas2.translate(f, 0.0f);
            }
            if (needsFadingHighEdge && this.mHighFadeShaderLength > 0) {
                Bitmap tempBitmapHigh = getTempBitmapHigh();
                tempBitmapHigh.eraseColor(0);
                canvas3.setBitmap(tempBitmapHigh);
                int save3 = canvas3.save();
                canvas3.clipRect(0, 0, this.mHighFadeShaderLength, getHeight());
                canvas3.translate((float) (-(i - this.mHighFadeShaderLength)), 0.0f);
                super.draw(canvas3);
                canvas3.restoreToCount(save3);
                this.mTempPaint.setShader(this.mHighFadeShader);
                canvas3.drawRect(0.0f, 0.0f, (float) this.mHighFadeShaderLength, (float) getHeight(), this.mTempPaint);
                Rect rect4 = this.mTempRect;
                rect4.left = 0;
                int i2 = this.mHighFadeShaderLength;
                rect4.right = i2;
                canvas2.translate((float) (i - i2), 0.0f);
                Rect rect5 = this.mTempRect;
                canvas2.drawBitmap(tempBitmapHigh, rect5, rect5, (Paint) null);
                canvas2.translate((float) (-(i - this.mHighFadeShaderLength)), 0.0f);
                return;
            }
            return;
        }
        super.draw(canvas);
    }

    private void updateLayerType() {
        if (this.mFadingLowEdge || this.mFadingHighEdge) {
            setLayerType(2, (Paint) null);
            setWillNotDraw(false);
            return;
        }
        setLayerType(0, (Paint) null);
        setWillNotDraw(true);
    }
}
