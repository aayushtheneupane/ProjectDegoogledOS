package androidx.appcompat.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.CompoundButton;
import androidx.core.widget.CompoundButtonCompat;

class AppCompatCompoundButtonHelper {
    private ColorStateList mButtonTintList = null;
    private PorterDuff.Mode mButtonTintMode = null;
    private boolean mHasButtonTint = false;
    private boolean mHasButtonTintMode = false;
    private boolean mSkipNextApply;
    private final CompoundButton mView;

    interface DirectSetButtonDrawableInterface {
        void setButtonDrawable(Drawable drawable);
    }

    AppCompatCompoundButtonHelper(CompoundButton compoundButton) {
        this.mView = compoundButton;
    }

    /* access modifiers changed from: package-private */
    public void applyButtonTint() {
        Drawable buttonDrawable = CompoundButtonCompat.getButtonDrawable(this.mView);
        if (buttonDrawable == null) {
            return;
        }
        if (this.mHasButtonTint || this.mHasButtonTintMode) {
            int i = Build.VERSION.SDK_INT;
            Drawable mutate = buttonDrawable.mutate();
            if (this.mHasButtonTint) {
                ColorStateList colorStateList = this.mButtonTintList;
                int i2 = Build.VERSION.SDK_INT;
                mutate.setTintList(colorStateList);
            }
            if (this.mHasButtonTintMode) {
                PorterDuff.Mode mode = this.mButtonTintMode;
                int i3 = Build.VERSION.SDK_INT;
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
    public ColorStateList getSupportButtonTintList() {
        return this.mButtonTintList;
    }

    /* access modifiers changed from: package-private */
    public PorterDuff.Mode getSupportButtonTintMode() {
        return this.mButtonTintMode;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031 A[SYNTHETIC, Splitter:B:12:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058 A[Catch:{ all -> 0x0084 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006d A[Catch:{ all -> 0x0084 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadFromAttributes(android.util.AttributeSet r4, int r5) {
        /*
            r3 = this;
            android.widget.CompoundButton r0 = r3.mView
            android.content.Context r0 = r0.getContext()
            int[] r1 = androidx.appcompat.C0126R.styleable.CompoundButton
            r2 = 0
            android.content.res.TypedArray r4 = r0.obtainStyledAttributes(r4, r1, r5, r2)
            int r5 = androidx.appcompat.C0126R.styleable.CompoundButton_buttonCompat     // Catch:{ all -> 0x0084 }
            boolean r5 = r4.hasValue(r5)     // Catch:{ all -> 0x0084 }
            if (r5 == 0) goto L_0x002e
            int r5 = androidx.appcompat.C0126R.styleable.CompoundButton_buttonCompat     // Catch:{ all -> 0x0084 }
            int r5 = r4.getResourceId(r5, r2)     // Catch:{ all -> 0x0084 }
            if (r5 == 0) goto L_0x002e
            android.widget.CompoundButton r0 = r3.mView     // Catch:{ NotFoundException -> 0x002e }
            android.widget.CompoundButton r1 = r3.mView     // Catch:{ NotFoundException -> 0x002e }
            android.content.Context r1 = r1.getContext()     // Catch:{ NotFoundException -> 0x002e }
            android.graphics.drawable.Drawable r5 = androidx.appcompat.p018a.p019a.C0130a.getDrawable(r1, r5)     // Catch:{ NotFoundException -> 0x002e }
            r0.setButtonDrawable(r5)     // Catch:{ NotFoundException -> 0x002e }
            r5 = 1
            goto L_0x002f
        L_0x002e:
            r5 = r2
        L_0x002f:
            if (r5 != 0) goto L_0x0050
            int r5 = androidx.appcompat.C0126R.styleable.CompoundButton_android_button     // Catch:{ all -> 0x0084 }
            boolean r5 = r4.hasValue(r5)     // Catch:{ all -> 0x0084 }
            if (r5 == 0) goto L_0x0050
            int r5 = androidx.appcompat.C0126R.styleable.CompoundButton_android_button     // Catch:{ all -> 0x0084 }
            int r5 = r4.getResourceId(r5, r2)     // Catch:{ all -> 0x0084 }
            if (r5 == 0) goto L_0x0050
            android.widget.CompoundButton r0 = r3.mView     // Catch:{ all -> 0x0084 }
            android.widget.CompoundButton r1 = r3.mView     // Catch:{ all -> 0x0084 }
            android.content.Context r1 = r1.getContext()     // Catch:{ all -> 0x0084 }
            android.graphics.drawable.Drawable r5 = androidx.appcompat.p018a.p019a.C0130a.getDrawable(r1, r5)     // Catch:{ all -> 0x0084 }
            r0.setButtonDrawable(r5)     // Catch:{ all -> 0x0084 }
        L_0x0050:
            int r5 = androidx.appcompat.C0126R.styleable.CompoundButton_buttonTint     // Catch:{ all -> 0x0084 }
            boolean r5 = r4.hasValue(r5)     // Catch:{ all -> 0x0084 }
            if (r5 == 0) goto L_0x0065
            android.widget.CompoundButton r5 = r3.mView     // Catch:{ all -> 0x0084 }
            int r0 = androidx.appcompat.C0126R.styleable.CompoundButton_buttonTint     // Catch:{ all -> 0x0084 }
            android.content.res.ColorStateList r0 = r4.getColorStateList(r0)     // Catch:{ all -> 0x0084 }
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0084 }
            r5.setButtonTintList(r0)     // Catch:{ all -> 0x0084 }
        L_0x0065:
            int r5 = androidx.appcompat.C0126R.styleable.CompoundButton_buttonTintMode     // Catch:{ all -> 0x0084 }
            boolean r5 = r4.hasValue(r5)     // Catch:{ all -> 0x0084 }
            if (r5 == 0) goto L_0x0080
            android.widget.CompoundButton r3 = r3.mView     // Catch:{ all -> 0x0084 }
            int r5 = androidx.appcompat.C0126R.styleable.CompoundButton_buttonTintMode     // Catch:{ all -> 0x0084 }
            r0 = -1
            int r5 = r4.getInt(r5, r0)     // Catch:{ all -> 0x0084 }
            r0 = 0
            android.graphics.PorterDuff$Mode r5 = androidx.appcompat.widget.DrawableUtils.parseTintMode(r5, r0)     // Catch:{ all -> 0x0084 }
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0084 }
            r3.setButtonTintMode(r5)     // Catch:{ all -> 0x0084 }
        L_0x0080:
            r4.recycle()
            return
        L_0x0084:
            r3 = move-exception
            r4.recycle()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatCompoundButtonHelper.loadFromAttributes(android.util.AttributeSet, int):void");
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

    /* access modifiers changed from: package-private */
    public void setSupportButtonTintList(ColorStateList colorStateList) {
        this.mButtonTintList = colorStateList;
        this.mHasButtonTint = true;
        applyButtonTint();
    }

    /* access modifiers changed from: package-private */
    public void setSupportButtonTintMode(PorterDuff.Mode mode) {
        this.mButtonTintMode = mode;
        this.mHasButtonTintMode = true;
        applyButtonTint();
    }
}
