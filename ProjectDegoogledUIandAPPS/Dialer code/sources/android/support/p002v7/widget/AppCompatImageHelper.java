package android.support.p002v7.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.support.p002v7.appcompat.R$styleable;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: android.support.v7.widget.AppCompatImageHelper */
public class AppCompatImageHelper {
    private TintInfo mImageTint;
    private TintInfo mInternalImageTint;
    private TintInfo mTmpInfo;
    private final ImageView mView;

    public AppCompatImageHelper(ImageView imageView) {
        this.mView = imageView;
    }

    /* access modifiers changed from: package-private */
    public void applySupportImageTint() {
        Drawable drawable = this.mView.getDrawable();
        if (drawable != null) {
            DrawableUtils.fixDrawable(drawable);
        }
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            boolean z = true;
            if (this.mInternalImageTint != null) {
                if (this.mTmpInfo == null) {
                    this.mTmpInfo = new TintInfo();
                }
                TintInfo tintInfo = this.mTmpInfo;
                tintInfo.clear();
                ImageView imageView = this.mView;
                int i2 = Build.VERSION.SDK_INT;
                ColorStateList imageTintList = imageView.getImageTintList();
                if (imageTintList != null) {
                    tintInfo.mHasTintList = true;
                    tintInfo.mTintList = imageTintList;
                }
                ImageView imageView2 = this.mView;
                int i3 = Build.VERSION.SDK_INT;
                PorterDuff.Mode imageTintMode = imageView2.getImageTintMode();
                if (imageTintMode != null) {
                    tintInfo.mHasTintMode = true;
                    tintInfo.mTintMode = imageTintMode;
                }
                if (tintInfo.mHasTintList || tintInfo.mHasTintMode) {
                    AppCompatDrawableManager.tintDrawable(drawable, tintInfo, this.mView.getDrawableState());
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            TintInfo tintInfo2 = this.mImageTint;
            if (tintInfo2 != null) {
                AppCompatDrawableManager.tintDrawable(drawable, tintInfo2, this.mView.getDrawableState());
                return;
            }
            TintInfo tintInfo3 = this.mInternalImageTint;
            if (tintInfo3 != null) {
                AppCompatDrawableManager.tintDrawable(drawable, tintInfo3, this.mView.getDrawableState());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean hasOverlappingRendering() {
        Drawable background = this.mView.getBackground();
        int i = Build.VERSION.SDK_INT;
        return !(background instanceof RippleDrawable);
    }

    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        int resourceId;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(this.mView.getContext(), attributeSet, R$styleable.AppCompatImageView, i, 0);
        try {
            Drawable drawable = this.mView.getDrawable();
            if (!(drawable != null || (resourceId = obtainStyledAttributes.getResourceId(1, -1)) == -1 || (drawable = AppCompatResources.getDrawable(this.mView.getContext(), resourceId)) == null)) {
                this.mView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                DrawableUtils.fixDrawable(drawable);
            }
            if (obtainStyledAttributes.hasValue(2)) {
                ImageView imageView = this.mView;
                ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(2);
                int i2 = Build.VERSION.SDK_INT;
                imageView.setImageTintList(colorStateList);
                int i3 = Build.VERSION.SDK_INT;
            }
            if (obtainStyledAttributes.hasValue(3)) {
                ImageView imageView2 = this.mView;
                PorterDuff.Mode parseTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(3, -1), (PorterDuff.Mode) null);
                int i4 = Build.VERSION.SDK_INT;
                imageView2.setImageTintMode(parseTintMode);
                int i5 = Build.VERSION.SDK_INT;
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public void setImageResource(int i) {
        if (i != 0) {
            Drawable drawable = AppCompatResources.getDrawable(this.mView.getContext(), i);
            if (drawable != null) {
                DrawableUtils.fixDrawable(drawable);
            }
            this.mView.setImageDrawable(drawable);
        } else {
            this.mView.setImageDrawable((Drawable) null);
        }
        applySupportImageTint();
    }
}
