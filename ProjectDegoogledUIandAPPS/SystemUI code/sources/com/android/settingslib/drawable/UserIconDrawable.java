package com.android.settingslib.drawable;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class UserIconDrawable extends Drawable implements Drawable.Callback {
    private Drawable mBadge;
    private float mBadgeMargin;
    private float mBadgeRadius;
    private Bitmap mBitmap;
    private Paint mClearPaint;
    private float mDisplayRadius;
    private ColorStateList mFrameColor;
    private float mFramePadding;
    private Paint mFramePaint;
    private float mFrameWidth;
    private final Matrix mIconMatrix;
    private final Paint mIconPaint;
    private float mIntrinsicRadius;
    private boolean mInvalidated;
    private float mPadding;
    private final Paint mPaint;
    private int mSize;
    private ColorStateList mTintColor;
    private PorterDuff.Mode mTintMode;
    private Drawable mUserDrawable;
    private Bitmap mUserIcon;

    public int getOpacity() {
        return -3;
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }

    private static Drawable getDrawableForDisplayDensity(Context context, int i) {
        return context.getResources().getDrawableForDensity(i, context.getResources().getDisplayMetrics().densityDpi, context.getTheme());
    }

    public UserIconDrawable() {
        this(0);
    }

    public UserIconDrawable(int i) {
        this.mIconPaint = new Paint();
        this.mPaint = new Paint();
        this.mIconMatrix = new Matrix();
        this.mPadding = 0.0f;
        this.mSize = 0;
        this.mInvalidated = true;
        this.mTintColor = null;
        this.mTintMode = PorterDuff.Mode.SRC_ATOP;
        this.mFrameColor = null;
        this.mIconPaint.setAntiAlias(true);
        this.mIconPaint.setFilterBitmap(true);
        this.mPaint.setFilterBitmap(true);
        this.mPaint.setAntiAlias(true);
        if (i > 0) {
            setBounds(0, 0, i, i);
            setIntrinsicSize(i);
        }
        setIcon((Bitmap) null);
    }

    public UserIconDrawable setIcon(Bitmap bitmap) {
        Drawable drawable = this.mUserDrawable;
        if (drawable != null) {
            drawable.setCallback((Drawable.Callback) null);
            this.mUserDrawable = null;
        }
        this.mUserIcon = bitmap;
        if (this.mUserIcon == null) {
            this.mIconPaint.setShader((Shader) null);
            this.mBitmap = null;
        } else {
            Paint paint = this.mIconPaint;
            Shader.TileMode tileMode = Shader.TileMode.CLAMP;
            paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
        }
        onBoundsChange(getBounds());
        return this;
    }

    public UserIconDrawable setIconDrawable(Drawable drawable) {
        Drawable drawable2 = this.mUserDrawable;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.mUserIcon = null;
        this.mUserDrawable = drawable;
        Drawable drawable3 = this.mUserDrawable;
        if (drawable3 == null) {
            this.mBitmap = null;
        } else {
            drawable3.setCallback(this);
        }
        onBoundsChange(getBounds());
        return this;
    }

    public UserIconDrawable setBadge(Drawable drawable) {
        this.mBadge = drawable;
        if (this.mBadge != null) {
            if (this.mClearPaint == null) {
                this.mClearPaint = new Paint();
                this.mClearPaint.setAntiAlias(true);
                this.mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                this.mClearPaint.setStyle(Paint.Style.FILL);
            }
            onBoundsChange(getBounds());
        } else {
            invalidateSelf();
        }
        return this;
    }

    public UserIconDrawable setBadgeIfManagedUser(Context context, int i) {
        Drawable drawable;
        if (i != -10000) {
            if (((DevicePolicyManager) context.getSystemService(DevicePolicyManager.class)).getProfileOwnerAsUser(i) != null) {
                drawable = getDrawableForDisplayDensity(context, 17302364);
                setBadge(drawable);
                return this;
            }
        }
        drawable = null;
        setBadge(drawable);
        return this;
    }

    public void setBadgeRadius(float f) {
        this.mBadgeRadius = f;
        onBoundsChange(getBounds());
    }

    public void setBadgeMargin(float f) {
        this.mBadgeMargin = f;
        onBoundsChange(getBounds());
    }

    public void setPadding(float f) {
        this.mPadding = f;
        onBoundsChange(getBounds());
    }

    private void initFramePaint() {
        if (this.mFramePaint == null) {
            this.mFramePaint = new Paint();
            this.mFramePaint.setStyle(Paint.Style.STROKE);
            this.mFramePaint.setAntiAlias(true);
        }
    }

    public void setFrameWidth(float f) {
        initFramePaint();
        this.mFrameWidth = f;
        this.mFramePaint.setStrokeWidth(f);
        onBoundsChange(getBounds());
    }

    public void setFramePadding(float f) {
        initFramePaint();
        this.mFramePadding = f;
        onBoundsChange(getBounds());
    }

    public void setFrameColor(ColorStateList colorStateList) {
        initFramePaint();
        this.mFrameColor = colorStateList;
        invalidateSelf();
    }

    public void setIntrinsicSize(int i) {
        this.mSize = i;
    }

    public void draw(Canvas canvas) {
        if (this.mInvalidated) {
            rebake();
        }
        if (this.mBitmap != null) {
            ColorStateList colorStateList = this.mTintColor;
            if (colorStateList == null) {
                this.mPaint.setColorFilter((ColorFilter) null);
            } else {
                int colorForState = colorStateList.getColorForState(getState(), this.mTintColor.getDefaultColor());
                if (shouldUpdateColorFilter(colorForState, this.mTintMode)) {
                    this.mPaint.setColorFilter(new PorterDuffColorFilter(colorForState, this.mTintMode));
                }
            }
            canvas.drawBitmap(this.mBitmap, 0.0f, 0.0f, this.mPaint);
        }
    }

    private boolean shouldUpdateColorFilter(int i, PorterDuff.Mode mode) {
        ColorFilter colorFilter = this.mPaint.getColorFilter();
        if (!(colorFilter instanceof PorterDuffColorFilter)) {
            return true;
        }
        PorterDuffColorFilter porterDuffColorFilter = (PorterDuffColorFilter) colorFilter;
        int color = porterDuffColorFilter.getColor();
        PorterDuff.Mode mode2 = porterDuffColorFilter.getMode();
        if (color == i && mode2 == mode) {
            return false;
        }
        return true;
    }

    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
        super.invalidateSelf();
    }

    public void setTintList(ColorStateList colorStateList) {
        this.mTintColor = colorStateList;
        super.invalidateSelf();
    }

    public void setTintMode(PorterDuff.Mode mode) {
        this.mTintMode = mode;
        super.invalidateSelf();
    }

    public Drawable.ConstantState getConstantState() {
        return new BitmapDrawable(this.mBitmap).getConstantState();
    }

    public UserIconDrawable bake() {
        int i = this.mSize;
        if (i > 0) {
            onBoundsChange(new Rect(0, 0, i, i));
            rebake();
            this.mFrameColor = null;
            this.mFramePaint = null;
            this.mClearPaint = null;
            Drawable drawable = this.mUserDrawable;
            if (drawable != null) {
                drawable.setCallback((Drawable.Callback) null);
                this.mUserDrawable = null;
            } else {
                Bitmap bitmap = this.mUserIcon;
                if (bitmap != null) {
                    bitmap.recycle();
                    this.mUserIcon = null;
                }
            }
            return this;
        }
        throw new IllegalStateException("Baking requires an explicit intrinsic size");
    }

    private void rebake() {
        this.mInvalidated = false;
        if (this.mBitmap == null) {
            return;
        }
        if (this.mUserDrawable != null || this.mUserIcon != null) {
            Canvas canvas = new Canvas(this.mBitmap);
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
            Drawable drawable = this.mUserDrawable;
            if (drawable != null) {
                drawable.draw(canvas);
            } else if (this.mUserIcon != null) {
                int save = canvas.save();
                canvas.concat(this.mIconMatrix);
                canvas.drawCircle(((float) this.mUserIcon.getWidth()) * 0.5f, ((float) this.mUserIcon.getHeight()) * 0.5f, this.mIntrinsicRadius, this.mIconPaint);
                canvas.restoreToCount(save);
            }
            ColorStateList colorStateList = this.mFrameColor;
            if (colorStateList != null) {
                this.mFramePaint.setColor(colorStateList.getColorForState(getState(), 0));
            }
            float f = this.mFrameWidth;
            if (this.mFramePadding + f > 0.001f) {
                canvas.drawCircle(getBounds().exactCenterX(), getBounds().exactCenterY(), (this.mDisplayRadius - this.mPadding) - (f * 0.5f), this.mFramePaint);
            }
            if (this.mBadge != null) {
                float f2 = this.mBadgeRadius;
                if (f2 > 0.001f) {
                    float f3 = f2 * 2.0f;
                    float height = ((float) this.mBitmap.getHeight()) - f3;
                    float width = ((float) this.mBitmap.getWidth()) - f3;
                    this.mBadge.setBounds((int) width, (int) height, (int) (width + f3), (int) (f3 + height));
                    float width2 = (((float) this.mBadge.getBounds().width()) * 0.5f) + this.mBadgeMargin;
                    float f4 = this.mBadgeRadius;
                    canvas.drawCircle(width + f4, height + f4, width2, this.mClearPaint);
                    this.mBadge.draw(canvas);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        if (rect.isEmpty()) {
            return;
        }
        if (this.mUserIcon != null || this.mUserDrawable != null) {
            float min = ((float) Math.min(rect.width(), rect.height())) * 0.5f;
            int i = (int) (min * 2.0f);
            if (this.mBitmap == null || i != ((int) (this.mDisplayRadius * 2.0f))) {
                this.mDisplayRadius = min;
                Bitmap bitmap = this.mBitmap;
                if (bitmap != null) {
                    bitmap.recycle();
                }
                this.mBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            }
            this.mDisplayRadius = ((float) Math.min(rect.width(), rect.height())) * 0.5f;
            float f = ((this.mDisplayRadius - this.mFrameWidth) - this.mFramePadding) - this.mPadding;
            RectF rectF = new RectF(rect.exactCenterX() - f, rect.exactCenterY() - f, rect.exactCenterX() + f, rect.exactCenterY() + f);
            if (this.mUserDrawable != null) {
                Rect rect2 = new Rect();
                rectF.round(rect2);
                this.mIntrinsicRadius = ((float) Math.min(this.mUserDrawable.getIntrinsicWidth(), this.mUserDrawable.getIntrinsicHeight())) * 0.5f;
                this.mUserDrawable.setBounds(rect2);
            } else {
                Bitmap bitmap2 = this.mUserIcon;
                if (bitmap2 != null) {
                    float width = ((float) bitmap2.getWidth()) * 0.5f;
                    float height = ((float) this.mUserIcon.getHeight()) * 0.5f;
                    this.mIntrinsicRadius = Math.min(width, height);
                    float f2 = this.mIntrinsicRadius;
                    this.mIconMatrix.setRectToRect(new RectF(width - f2, height - f2, width + f2, height + f2), rectF, Matrix.ScaleToFit.FILL);
                }
            }
            invalidateSelf();
        }
    }

    public void invalidateSelf() {
        super.invalidateSelf();
        this.mInvalidated = true;
    }

    public boolean isStateful() {
        ColorStateList colorStateList = this.mFrameColor;
        return colorStateList != null && colorStateList.isStateful();
    }

    public int getIntrinsicWidth() {
        int i = this.mSize;
        return i <= 0 ? ((int) this.mIntrinsicRadius) * 2 : i;
    }

    public int getIntrinsicHeight() {
        return getIntrinsicWidth();
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
