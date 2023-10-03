package p000;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;

/* renamed from: pz */
/* compiled from: PG */
public class C0437pz extends Drawable implements Drawable.Callback {

    /* renamed from: a */
    public final Drawable f15599a;

    public C0437pz(Drawable drawable) {
        Drawable drawable2 = this.f15599a;
        if (drawable2 != null) {
            drawable2.setCallback((Drawable.Callback) null);
        }
        this.f15599a = drawable;
        drawable.setCallback(this);
    }

    public void draw(Canvas canvas) {
        this.f15599a.draw(canvas);
    }

    public final int getChangingConfigurations() {
        return this.f15599a.getChangingConfigurations();
    }

    public final Drawable getCurrent() {
        return this.f15599a.getCurrent();
    }

    public final int getIntrinsicHeight() {
        return this.f15599a.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth() {
        return this.f15599a.getIntrinsicWidth();
    }

    public final int getMinimumHeight() {
        return this.f15599a.getMinimumHeight();
    }

    public final int getMinimumWidth() {
        return this.f15599a.getMinimumWidth();
    }

    public final int getOpacity() {
        return this.f15599a.getOpacity();
    }

    public final boolean getPadding(Rect rect) {
        return this.f15599a.getPadding(rect);
    }

    public final int[] getState() {
        return this.f15599a.getState();
    }

    public final Region getTransparentRegion() {
        return this.f15599a.getTransparentRegion();
    }

    public final void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    public final boolean isAutoMirrored() {
        return C0257jh.m14480a(this.f15599a);
    }

    public final boolean isStateful() {
        return this.f15599a.isStateful();
    }

    public final void jumpToCurrentState() {
        this.f15599a.jumpToCurrentState();
    }

    /* access modifiers changed from: protected */
    public final void onBoundsChange(Rect rect) {
        this.f15599a.setBounds(rect);
    }

    /* access modifiers changed from: protected */
    public final boolean onLevelChange(int i) {
        return this.f15599a.setLevel(i);
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    public final void setAlpha(int i) {
        this.f15599a.setAlpha(i);
    }

    public final void setAutoMirrored(boolean z) {
        C0257jh.m14477a(this.f15599a, z);
    }

    public final void setChangingConfigurations(int i) {
        this.f15599a.setChangingConfigurations(i);
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        this.f15599a.setColorFilter(colorFilter);
    }

    public final void setDither(boolean z) {
        this.f15599a.setDither(z);
    }

    public final void setFilterBitmap(boolean z) {
        this.f15599a.setFilterBitmap(z);
    }

    public void setHotspot(float f, float f2) {
        C0257jh.m14472a(this.f15599a, f, f2);
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        C0257jh.m14474a(this.f15599a, i, i2, i3, i4);
    }

    public boolean setState(int[] iArr) {
        return this.f15599a.setState(iArr);
    }

    public final void setTint(int i) {
        C0257jh.m14473a(this.f15599a, i);
    }

    public final void setTintList(ColorStateList colorStateList) {
        C0257jh.m14475a(this.f15599a, colorStateList);
    }

    public final void setTintMode(PorterDuff.Mode mode) {
        C0257jh.m14476a(this.f15599a, mode);
    }

    public boolean setVisible(boolean z, boolean z2) {
        return super.setVisible(z, z2) || this.f15599a.setVisible(z, z2);
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }
}
