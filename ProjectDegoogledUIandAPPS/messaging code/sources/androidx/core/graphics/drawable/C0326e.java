package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import java.lang.reflect.Method;

/* renamed from: androidx.core.graphics.drawable.e */
class C0326e extends C0325d {

    /* renamed from: gd */
    private static Method f323gd;

    C0326e(C0327f fVar, Resources resources) {
        super(fVar, resources);
        m267Im();
    }

    /* renamed from: Im */
    private void m267Im() {
        if (f323gd == null) {
            try {
                f323gd = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", e);
            }
        }
    }

    public Rect getDirtyBounds() {
        return this.mDrawable.getDirtyBounds();
    }

    public void getOutline(Outline outline) {
        this.mDrawable.getOutline(outline);
    }

    public boolean isProjected() {
        Method method;
        Drawable drawable = this.mDrawable;
        if (!(drawable == null || (method = f323gd) == null)) {
            try {
                return ((Boolean) method.invoke(drawable, new Object[0])).booleanValue();
            } catch (Exception e) {
                Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", e);
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    /* renamed from: rb */
    public boolean mo3469rb() {
        int i = Build.VERSION.SDK_INT;
        return false;
    }

    public void setHotspot(float f, float f2) {
        this.mDrawable.setHotspot(f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        this.mDrawable.setHotspotBounds(i, i2, i3, i4);
    }

    public boolean setState(int[] iArr) {
        if (!super.setState(iArr)) {
            return false;
        }
        invalidateSelf();
        return true;
    }

    public void setTint(int i) {
        int i2 = Build.VERSION.SDK_INT;
        this.mDrawable.setTint(i);
    }

    public void setTintList(ColorStateList colorStateList) {
        int i = Build.VERSION.SDK_INT;
        this.mDrawable.setTintList(colorStateList);
    }

    public void setTintMode(PorterDuff.Mode mode) {
        int i = Build.VERSION.SDK_INT;
        this.mDrawable.setTintMode(mode);
    }
}
