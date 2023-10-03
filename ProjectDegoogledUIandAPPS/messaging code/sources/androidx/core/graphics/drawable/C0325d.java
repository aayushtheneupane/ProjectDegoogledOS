package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* renamed from: androidx.core.graphics.drawable.d */
class C0325d extends Drawable implements Drawable.Callback, C0324c, C0323b {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;

    /* renamed from: cd */
    private int f320cd;

    /* renamed from: dd */
    private PorterDuff.Mode f321dd;

    /* renamed from: ed */
    private boolean f322ed;
    Drawable mDrawable;
    private boolean mMutated;
    C0327f mState;

    C0325d(C0327f fVar, Resources resources) {
        Drawable.ConstantState constantState;
        this.mState = fVar;
        C0327f fVar2 = this.mState;
        if (fVar2 != null && (constantState = fVar2.mDrawableState) != null) {
            setWrappedDrawable(constantState.newDrawable(resources));
        }
    }

    /* renamed from: e */
    private boolean m265e(int[] iArr) {
        if (!mo3469rb()) {
            return false;
        }
        C0327f fVar = this.mState;
        ColorStateList colorStateList = fVar.mTint;
        PorterDuff.Mode mode = fVar.mTintMode;
        if (colorStateList == null || mode == null) {
            this.f322ed = false;
            clearColorFilter();
        } else {
            int colorForState = colorStateList.getColorForState(iArr, colorStateList.getDefaultColor());
            if (!(this.f322ed && colorForState == this.f320cd && mode == this.f321dd)) {
                setColorFilter(colorForState, mode);
                this.f320cd = colorForState;
                this.f321dd = mode;
                this.f322ed = true;
                return true;
            }
        }
        return false;
    }

    public void draw(Canvas canvas) {
        this.mDrawable.draw(canvas);
    }

    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        C0327f fVar = this.mState;
        int i = 0;
        if (fVar != null) {
            int i2 = fVar.mChangingConfigurations;
            Drawable.ConstantState constantState = fVar.mDrawableState;
            if (constantState != null) {
                i = constantState.getChangingConfigurations();
            }
            i |= i2;
        }
        return this.mDrawable.getChangingConfigurations() | changingConfigurations | i;
    }

    public Drawable.ConstantState getConstantState() {
        C0327f fVar = this.mState;
        if (fVar == null) {
            return null;
        }
        int i = 0;
        if (!(fVar.mDrawableState != null)) {
            return null;
        }
        C0327f fVar2 = this.mState;
        int changingConfigurations = super.getChangingConfigurations();
        C0327f fVar3 = this.mState;
        if (fVar3 != null) {
            int i2 = fVar3.mChangingConfigurations;
            Drawable.ConstantState constantState = fVar3.mDrawableState;
            if (constantState != null) {
                i = constantState.getChangingConfigurations();
            }
            i |= i2;
        }
        fVar2.mChangingConfigurations = i | changingConfigurations | this.mDrawable.getChangingConfigurations();
        return this.mState;
    }

    public Drawable getCurrent() {
        return this.mDrawable.getCurrent();
    }

    public int getIntrinsicHeight() {
        return this.mDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth() {
        return this.mDrawable.getIntrinsicWidth();
    }

    public int getMinimumHeight() {
        return this.mDrawable.getMinimumHeight();
    }

    public int getMinimumWidth() {
        return this.mDrawable.getMinimumWidth();
    }

    public int getOpacity() {
        return this.mDrawable.getOpacity();
    }

    public boolean getPadding(Rect rect) {
        return this.mDrawable.getPadding(rect);
    }

    public int[] getState() {
        return this.mDrawable.getState();
    }

    public Region getTransparentRegion() {
        return this.mDrawable.getTransparentRegion();
    }

    public final Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public boolean isAutoMirrored() {
        return this.mDrawable.isAutoMirrored();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0006, code lost:
        r0 = r1.mState;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStateful() {
        /*
            r1 = this;
            boolean r0 = r1.mo3469rb()
            if (r0 == 0) goto L_0x000d
            androidx.core.graphics.drawable.f r0 = r1.mState
            if (r0 == 0) goto L_0x000d
            android.content.res.ColorStateList r0 = r0.mTint
            goto L_0x000e
        L_0x000d:
            r0 = 0
        L_0x000e:
            if (r0 == 0) goto L_0x0016
            boolean r0 = r0.isStateful()
            if (r0 != 0) goto L_0x001e
        L_0x0016:
            android.graphics.drawable.Drawable r1 = r1.mDrawable
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L_0x0020
        L_0x001e:
            r1 = 1
            goto L_0x0021
        L_0x0020:
            r1 = 0
        L_0x0021:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.C0325d.isStateful():boolean");
    }

    public void jumpToCurrentState() {
        this.mDrawable.jumpToCurrentState();
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mState = new C0327f(this.mState);
            Drawable drawable = this.mDrawable;
            if (drawable != null) {
                drawable.mutate();
            }
            C0327f fVar = this.mState;
            if (fVar != null) {
                Drawable drawable2 = this.mDrawable;
                fVar.mDrawableState = drawable2 != null ? drawable2.getConstantState() : null;
            }
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        return this.mDrawable.setLevel(i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: rb */
    public boolean mo3469rb() {
        return true;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public void setAlpha(int i) {
        this.mDrawable.setAlpha(i);
    }

    public void setAutoMirrored(boolean z) {
        this.mDrawable.setAutoMirrored(z);
    }

    public void setChangingConfigurations(int i) {
        this.mDrawable.setChangingConfigurations(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawable.setColorFilter(colorFilter);
    }

    public void setDither(boolean z) {
        this.mDrawable.setDither(z);
    }

    public void setFilterBitmap(boolean z) {
        this.mDrawable.setFilterBitmap(z);
    }

    public boolean setState(int[] iArr) {
        return m265e(iArr) || this.mDrawable.setState(iArr);
    }

    public void setTint(int i) {
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        this.mState.mTint = colorStateList;
        m265e(this.mDrawable.getState());
    }

    public void setTintMode(PorterDuff.Mode mode) {
        this.mState.mTintMode = mode;
        m265e(this.mDrawable.getState());
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.mDrawable.setVisible(z, z2);
    }

    public final void setWrappedDrawable(Drawable drawable) {
        Drawable drawable2 = this.mDrawable;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.mDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
            boolean isVisible = drawable.isVisible();
            if (!super.setVisible(isVisible, true)) {
                boolean visible = this.mDrawable.setVisible(isVisible, true);
            }
            setState(drawable.getState());
            setLevel(drawable.getLevel());
            setBounds(drawable.getBounds());
            C0327f fVar = this.mState;
            if (fVar != null) {
                fVar.mDrawableState = drawable.getConstantState();
            }
        }
        invalidateSelf();
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
