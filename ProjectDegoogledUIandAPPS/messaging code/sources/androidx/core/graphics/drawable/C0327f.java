package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;

/* renamed from: androidx.core.graphics.drawable.f */
final class C0327f extends Drawable.ConstantState {
    int mChangingConfigurations;
    Drawable.ConstantState mDrawableState;
    ColorStateList mTint = null;
    PorterDuff.Mode mTintMode = C0325d.DEFAULT_TINT_MODE;

    C0327f(C0327f fVar) {
        if (fVar != null) {
            this.mChangingConfigurations = fVar.mChangingConfigurations;
            this.mDrawableState = fVar.mDrawableState;
            this.mTint = fVar.mTint;
            this.mTintMode = fVar.mTintMode;
        }
    }

    public int getChangingConfigurations() {
        int i = this.mChangingConfigurations;
        Drawable.ConstantState constantState = this.mDrawableState;
        return (constantState != null ? constantState.getChangingConfigurations() : 0) | i;
    }

    public Drawable newDrawable() {
        int i = Build.VERSION.SDK_INT;
        return new C0326e(this, (Resources) null);
    }

    public Drawable newDrawable(Resources resources) {
        int i = Build.VERSION.SDK_INT;
        return new C0326e(this, resources);
    }
}
