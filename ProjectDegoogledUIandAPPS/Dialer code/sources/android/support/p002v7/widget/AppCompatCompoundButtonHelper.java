package android.support.p002v7.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p002v7.appcompat.R$styleable;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/* renamed from: android.support.v7.widget.AppCompatCompoundButtonHelper */
class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    /* access modifiers changed from: package-private */
    public void applyButtonTint() {
        CompoundButton compoundButton = this.mView;
        int i = Build.VERSION.SDK_INT;
        Drawable buttonDrawable = compoundButton.getButtonDrawable();
        if (buttonDrawable == null) {
            return;
        }
        if (this.mHasButtonTint || this.mHasButtonTintMode) {
            int i2 = Build.VERSION.SDK_INT;
            Drawable mutate = buttonDrawable.mutate();
            if (this.mHasButtonTint) {
                ColorStateList colorStateList = this.mButtonTintList;
                int i3 = Build.VERSION.SDK_INT;
                mutate.setTintList(colorStateList);
            }
            if (this.mHasButtonTintMode) {
                PorterDuff.Mode mode = this.mButtonTintMode;
                int i4 = Build.VERSION.SDK_INT;
                mutate.setTintMode(mode);
            }
            if (mutate.isStateful()) {
                mutate.setState(this.mView.getDrawableState());
            }
            this.mView.setButtonDrawable(mutate);
        }
    }

    /* access modifiers changed from: package-private */
    public int getCompoundPaddingLeft(int i) {
        int i2 = Build.VERSION.SDK_INT;
        return i;
    }

    /* access modifiers changed from: package-private */
    public void loadFromAttributes(AttributeSet attributeSet, int i) {
        int resourceId;
        TypedArray obtainStyledAttributes = this.mView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CompoundButton, i, 0);
        try {
            if (obtainStyledAttributes.hasValue(R$styleable.CompoundButton_android_button) && (resourceId = obtainStyledAttributes.getResourceId(R$styleable.CompoundButton_android_button, 0)) != 0) {
                this.mView.setButtonDrawable(AppCompatResources.getDrawable(this.mView.getContext(), resourceId));
            }
            if (obtainStyledAttributes.hasValue(1)) {
                CompoundButton compoundButton = this.mView;
                ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(1);
                int i2 = Build.VERSION.SDK_INT;
                compoundButton.setButtonTintList(colorStateList);
            }
            if (obtainStyledAttributes.hasValue(2)) {
                CompoundButton compoundButton2 = this.mView;
                PorterDuff.Mode parseTintMode = DrawableUtils.parseTintMode(obtainStyledAttributes.getInt(2, -1), (PorterDuff.Mode) null);
                int i3 = Build.VERSION.SDK_INT;
                compoundButton2.setButtonTintMode(parseTintMode);
            }
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    /* access modifiers changed from: package-private */
    public void onSetButtonDrawable() {
        if (this.mSkipNextApply) {
            this.mSkipNextApply = false;
            return;
        }
        this.mSkipNextApply = true;
        applyButtonTint();
    }
}
