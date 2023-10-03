package com.android.settingslib.graph;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.DrawableWrapper;
import android.os.Handler;
import android.util.PathParser;
import com.android.settingslib.R$color;
import com.android.settingslib.R$dimen;
import com.android.settingslib.Utils;

public class SignalDrawable extends DrawableWrapper {
    private boolean mAnimating;
    /* access modifiers changed from: private */
    public final Runnable mChangeDot = new Runnable() {
        public void run() {
            if (SignalDrawable.access$004(SignalDrawable.this) == 3) {
                int unused = SignalDrawable.this.mCurrentDot = 0;
            }
            SignalDrawable.this.invalidateSelf();
            SignalDrawable.this.mHandler.postDelayed(SignalDrawable.this.mChangeDot, 1000);
        }
    };
    /* access modifiers changed from: private */
    public int mCurrentDot;
    private final float mCutoutHeightFraction;
    private final Path mCutoutPath = new Path();
    private final float mCutoutWidthFraction;
    private float mDarkIntensity = -1.0f;
    private final int mDarkModeFillColor;
    private final Paint mForegroundPaint = new Paint(1);
    private final Path mForegroundPath = new Path();
    /* access modifiers changed from: private */
    public final Handler mHandler;
    private final int mIntrinsicSize;
    private final int mLightModeFillColor;
    private final Path mScaledXPath = new Path();
    private final Paint mTransparentPaint = new Paint(1);
    private final Path mXPath = new Path();
    private final Matrix mXScaleMatrix = new Matrix();

    public static int getState(int i) {
        return (i & 16711680) >> 16;
    }

    public static int getState(int i, int i2, boolean z) {
        return i | (i2 << 8) | ((z ? 2 : 0) << 16);
    }

    private int unpackLevel(int i) {
        return (i & 255) + (((65280 & i) >> 8) == 6 ? 10 : 0);
    }

    static /* synthetic */ int access$004(SignalDrawable signalDrawable) {
        int i = signalDrawable.mCurrentDot + 1;
        signalDrawable.mCurrentDot = i;
        return i;
    }

    public SignalDrawable(Context context) {
        super(context.getDrawable(17302812));
        this.mXPath.set(PathParser.createPathFromPathData(context.getString(17039816)));
        updateScaledXPath();
        this.mCutoutWidthFraction = context.getResources().getFloat(17105078);
        this.mCutoutHeightFraction = context.getResources().getFloat(17105077);
        this.mDarkModeFillColor = Utils.getColorStateListDefaultColor(context, R$color.dark_mode_icon_color_single_tone);
        this.mLightModeFillColor = Utils.getColorStateListDefaultColor(context, R$color.light_mode_icon_color_single_tone);
        this.mIntrinsicSize = context.getResources().getDimensionPixelSize(R$dimen.signal_icon_size);
        this.mTransparentPaint.setColor(context.getColor(17170445));
        this.mTransparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        this.mHandler = new Handler();
        setDarkIntensity(0.0f);
    }

    private void updateScaledXPath() {
        if (getBounds().isEmpty()) {
            this.mXScaleMatrix.setScale(1.0f, 1.0f);
        } else {
            this.mXScaleMatrix.setScale(((float) getBounds().width()) / 24.0f, ((float) getBounds().height()) / 24.0f);
        }
        this.mXPath.transform(this.mXScaleMatrix, this.mScaledXPath);
    }

    public int getIntrinsicWidth() {
        return this.mIntrinsicSize;
    }

    public int getIntrinsicHeight() {
        return this.mIntrinsicSize;
    }

    private void updateAnimation() {
        boolean z = isInState(3) && isVisible();
        if (z != this.mAnimating) {
            this.mAnimating = z;
            if (z) {
                this.mChangeDot.run();
            } else {
                this.mHandler.removeCallbacks(this.mChangeDot);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        super.onLevelChange(unpackLevel(i));
        updateAnimation();
        setTintList(ColorStateList.valueOf(this.mForegroundPaint.getColor()));
        invalidateSelf();
        return true;
    }

    public void setDarkIntensity(float f) {
        if (f != this.mDarkIntensity) {
            setTintList(ColorStateList.valueOf(getFillColor(f)));
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
        int color = this.mForegroundPaint.getColor();
        this.mForegroundPaint.setColor(colorStateList.getDefaultColor());
        if (color != this.mForegroundPaint.getColor()) {
            invalidateSelf();
        }
    }

    private int getFillColor(float f) {
        return getColorForDarkIntensity(f, this.mLightModeFillColor, this.mDarkModeFillColor);
    }

    private int getColorForDarkIntensity(float f, int i, int i2) {
        return ((Integer) ArgbEvaluator.getInstance().evaluate(f, Integer.valueOf(i), Integer.valueOf(i2))).intValue();
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        updateScaledXPath();
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        canvas.saveLayer((RectF) null, (Paint) null);
        float width = (float) getBounds().width();
        float height = (float) getBounds().height();
        boolean z = true;
        if (getLayoutDirection() != 1) {
            z = false;
        }
        if (z) {
            canvas.save();
            canvas.translate(width, 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        super.draw(canvas);
        this.mCutoutPath.reset();
        this.mCutoutPath.setFillType(Path.FillType.WINDING);
        float round = (float) Math.round(0.083333336f * width);
        if (isInState(3)) {
            float f = 0.125f * height;
            float f2 = height * 0.0625f;
            float f3 = f2 + f;
            float f4 = (width - round) - f;
            this.mForegroundPath.reset();
            float f5 = (height - round) - f;
            float f6 = f2;
            float f7 = f;
            drawDotAndPadding(f4, f5, f6, f7, 2);
            drawDotAndPadding(f4 - f3, f5, f6, f7, 1);
            drawDotAndPadding(f4 - (f3 * 2.0f), f5, f6, f7, 0);
            canvas.drawPath(this.mCutoutPath, this.mTransparentPaint);
            canvas.drawPath(this.mForegroundPath, this.mForegroundPaint);
        } else if (isInState(2)) {
            float f8 = (this.mCutoutWidthFraction * width) / 24.0f;
            float f9 = (this.mCutoutHeightFraction * height) / 24.0f;
            this.mCutoutPath.moveTo(width, height);
            this.mCutoutPath.rLineTo(-f8, 0.0f);
            this.mCutoutPath.rLineTo(0.0f, -f9);
            this.mCutoutPath.rLineTo(f8, 0.0f);
            this.mCutoutPath.rLineTo(0.0f, f9);
            canvas.drawPath(this.mCutoutPath, this.mTransparentPaint);
            canvas.drawPath(this.mScaledXPath, this.mForegroundPaint);
        }
        if (z) {
            canvas.restore();
        }
        canvas.restore();
    }

    private void drawDotAndPadding(float f, float f2, float f3, float f4, int i) {
        if (i == this.mCurrentDot) {
            float f5 = f + f4;
            float f6 = f2 + f4;
            this.mForegroundPath.addRect(f, f2, f5, f6, Path.Direction.CW);
            this.mCutoutPath.addRect(f - f3, f2 - f3, f5 + f3, f6 + f3, Path.Direction.CW);
        }
    }

    public void setAlpha(int i) {
        super.setAlpha(i);
        this.mForegroundPaint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        this.mForegroundPaint.setColorFilter(colorFilter);
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        updateAnimation();
        return visible;
    }

    private boolean isInState(int i) {
        return getState(getLevel()) == i;
    }
}
