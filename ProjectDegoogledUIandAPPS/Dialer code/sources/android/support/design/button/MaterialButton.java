package android.support.design.button;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.content.res.AppCompatResources;
import android.support.p002v7.widget.AppCompatButton;
import android.util.Log;

public class MaterialButton extends AppCompatButton {
    private Drawable icon;
    private int iconGravity;
    private int iconLeft;
    private int iconPadding;
    private int iconSize;
    private ColorStateList iconTint;
    private PorterDuff.Mode iconTintMode;
    private final MaterialButtonHelper materialButtonHelper;

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x004b, code lost:
        r9 = android.support.p002v7.content.res.AppCompatResources.getDrawable(r9, (r2 = r8.getResourceId(7, 0)));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaterialButton(android.content.Context r8, android.util.AttributeSet r9) {
        /*
            r7 = this;
            r0 = 2130968931(0x7f040163, float:1.754653E38)
            r7.<init>(r8, r9, r0)
            int[] r3 = android.support.design.R$styleable.MaterialButton
            r0 = 0
            int[] r6 = new int[r0]
            r5 = 2131886679(0x7f120257, float:1.9407944E38)
            r4 = 2130968931(0x7f040163, float:1.754653E38)
            r1 = r8
            r2 = r9
            android.content.res.TypedArray r8 = android.support.design.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            r9 = 9
            int r9 = r8.getDimensionPixelSize(r9, r0)
            r7.iconPadding = r9
            r9 = 12
            r1 = -1
            int r9 = r8.getInt(r9, r1)
            android.graphics.PorterDuff$Mode r1 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r9 = android.support.design.R$dimen.parseTintMode(r9, r1)
            r7.iconTintMode = r9
            android.content.Context r9 = r7.getContext()
            r1 = 11
            android.content.res.ColorStateList r9 = android.support.design.R$dimen.getColorStateList(r9, r8, r1)
            r7.iconTint = r9
            android.content.Context r9 = r7.getContext()
            r1 = 7
            boolean r2 = r8.hasValue(r1)
            if (r2 == 0) goto L_0x0052
            int r2 = r8.getResourceId(r1, r0)
            if (r2 == 0) goto L_0x0052
            android.graphics.drawable.Drawable r9 = android.support.p002v7.content.res.AppCompatResources.getDrawable(r9, r2)
            if (r9 == 0) goto L_0x0052
            goto L_0x0056
        L_0x0052:
            android.graphics.drawable.Drawable r9 = r8.getDrawable(r1)
        L_0x0056:
            r7.icon = r9
            r9 = 8
            r1 = 1
            int r9 = r8.getInteger(r9, r1)
            r7.iconGravity = r9
            r9 = 10
            int r9 = r8.getDimensionPixelSize(r9, r0)
            r7.iconSize = r9
            android.support.design.button.MaterialButtonHelper r9 = new android.support.design.button.MaterialButtonHelper
            r9.<init>(r7)
            r7.materialButtonHelper = r9
            android.support.design.button.MaterialButtonHelper r9 = r7.materialButtonHelper
            r9.loadFromAttributes(r8)
            r8.recycle()
            int r8 = r7.iconPadding
            r7.setCompoundDrawablePadding(r8)
            r7.updateIcon()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.design.button.MaterialButton.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    private boolean isUsingOriginalBackground() {
        MaterialButtonHelper materialButtonHelper2 = this.materialButtonHelper;
        return materialButtonHelper2 != null && !materialButtonHelper2.isBackgroundOverwritten();
    }

    private void updateIcon() {
        Drawable drawable = this.icon;
        if (drawable != null) {
            this.icon = drawable.mutate();
            Drawable drawable2 = this.icon;
            ColorStateList colorStateList = this.iconTint;
            int i = Build.VERSION.SDK_INT;
            drawable2.setTintList(colorStateList);
            PorterDuff.Mode mode = this.iconTintMode;
            if (mode != null) {
                Drawable drawable3 = this.icon;
                int i2 = Build.VERSION.SDK_INT;
                drawable3.setTintMode(mode);
            }
            int i3 = this.iconSize;
            if (i3 == 0) {
                i3 = this.icon.getIntrinsicWidth();
            }
            int i4 = this.iconSize;
            if (i4 == 0) {
                i4 = this.icon.getIntrinsicHeight();
            }
            Drawable drawable4 = this.icon;
            int i5 = this.iconLeft;
            drawable4.setBounds(i5, 0, i3 + i5, i4);
        }
        Drawable drawable5 = this.icon;
        int i6 = Build.VERSION.SDK_INT;
        setCompoundDrawablesRelative(drawable5, (Drawable) null, (Drawable) null, (Drawable) null);
    }

    public ColorStateList getBackgroundTintList() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintList();
        }
        return super.getSupportBackgroundTintList();
    }

    public PorterDuff.Mode getBackgroundTintMode() {
        if (isUsingOriginalBackground()) {
            return this.materialButtonHelper.getSupportBackgroundTintMode();
        }
        return super.getSupportBackgroundTintMode();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = Build.VERSION.SDK_INT;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = Build.VERSION.SDK_INT;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.icon != null && this.iconGravity == 2) {
            int measureText = (int) getPaint().measureText(getText().toString());
            int i3 = this.iconSize;
            if (i3 == 0) {
                i3 = this.icon.getIntrinsicWidth();
            }
            int measuredWidth = (((((getMeasuredWidth() - measureText) - ViewCompat.getPaddingEnd(this)) - i3) - this.iconPadding) - ViewCompat.getPaddingStart(this)) / 2;
            boolean z = true;
            if (ViewCompat.getLayoutDirection(this) != 1) {
                z = false;
            }
            if (z) {
                measuredWidth = -measuredWidth;
            }
            if (this.iconLeft != measuredWidth) {
                this.iconLeft = measuredWidth;
                updateIcon();
            }
        }
    }

    public void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public void setBackgroundColor(int i) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setBackgroundColor(i);
        } else {
            super.setBackgroundColor(i);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (!isUsingOriginalBackground()) {
            super.setBackgroundDrawable(drawable);
        } else if (drawable != getBackground()) {
            Log.i("MaterialButton", "Setting a custom background is not supported.");
            this.materialButtonHelper.setBackgroundOverwritten();
            super.setBackgroundDrawable(drawable);
        } else {
            getBackground().setState(drawable.getState());
        }
    }

    public void setBackgroundResource(int i) {
        setBackgroundDrawable(i != 0 ? AppCompatResources.getDrawable(getContext(), i) : null);
    }

    public void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    public void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    /* access modifiers changed from: package-private */
    public void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintList(colorStateList);
        } else if (this.materialButtonHelper != null) {
            super.setSupportBackgroundTintList(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setSupportBackgroundTintMode(mode);
        } else if (this.materialButtonHelper != null) {
            super.setSupportBackgroundTintMode(mode);
        }
    }
}
