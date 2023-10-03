package androidx.appcompat.p020b.p021a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.graphics.drawable.C0322a;

/* renamed from: androidx.appcompat.b.a.l */
class C0169l extends Drawable implements Drawable.Callback {

    /* renamed from: Hc */
    private C0168k f149Hc;

    /* renamed from: Ic */
    private Rect f150Ic;

    /* renamed from: Jc */
    private Drawable f151Jc;

    /* renamed from: Kc */
    private Drawable f152Kc;

    /* renamed from: Lc */
    private boolean f153Lc;

    /* renamed from: Mc */
    private int f154Mc = -1;

    /* renamed from: Nc */
    private Runnable f155Nc;

    /* renamed from: Oc */
    private long f156Oc;

    /* renamed from: Pc */
    private long f157Pc;

    /* renamed from: Qc */
    private C0167j f158Qc;
    private int mAlpha = 255;
    private boolean mMutated;

    C0169l() {
    }

    /* renamed from: e */
    private void m147e(Drawable drawable) {
        if (this.f158Qc == null) {
            this.f158Qc = new C0167j();
        }
        C0167j jVar = this.f158Qc;
        jVar.wrap(drawable.getCallback());
        drawable.setCallback(jVar);
        try {
            if (this.f149Hc.mEnterFadeDuration <= 0 && this.f153Lc) {
                drawable.setAlpha(this.mAlpha);
            }
            if (this.f149Hc.mHasColorFilter) {
                drawable.setColorFilter(this.f149Hc.mColorFilter);
            } else {
                if (this.f149Hc.mHasTintList) {
                    ColorStateList colorStateList = this.f149Hc.mTintList;
                    int i = Build.VERSION.SDK_INT;
                    drawable.setTintList(colorStateList);
                }
                if (this.f149Hc.mHasTintMode) {
                    PorterDuff.Mode mode = this.f149Hc.mTintMode;
                    int i2 = Build.VERSION.SDK_INT;
                    drawable.setTintMode(mode);
                }
            }
            drawable.setVisible(isVisible(), true);
            drawable.setDither(this.f149Hc.mDither);
            drawable.setState(getState());
            drawable.setLevel(getLevel());
            drawable.setBounds(getBounds());
            int i3 = Build.VERSION.SDK_INT;
            drawable.setLayoutDirection(getLayoutDirection());
            int i4 = Build.VERSION.SDK_INT;
            drawable.setAutoMirrored(this.f149Hc.mAutoMirrored);
            Rect rect = this.f150Ic;
            int i5 = Build.VERSION.SDK_INT;
            if (rect != null) {
                drawable.setHotspotBounds(rect.left, rect.top, rect.right, rect.bottom);
            }
        } finally {
            drawable.setCallback(this.f158Qc.unwrap());
        }
    }

    static int resolveDensity(Resources resources, int i) {
        if (resources != null) {
            i = resources.getDisplayMetrics().densityDpi;
        }
        if (i == 0) {
            return 160;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1085a(C0168k kVar) {
        this.f149Hc = kVar;
        int i = this.f154Mc;
        if (i >= 0) {
            this.f151Jc = kVar.getChild(i);
            Drawable drawable = this.f151Jc;
            if (drawable != null) {
                m147e(drawable);
            }
        }
        this.f152Kc = null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0066 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void animate(boolean r14) {
        /*
            r13 = this;
            r0 = 1
            r13.f153Lc = r0
            long r1 = android.os.SystemClock.uptimeMillis()
            android.graphics.drawable.Drawable r3 = r13.f151Jc
            r4 = 255(0xff, double:1.26E-321)
            r6 = 0
            r7 = 0
            if (r3 == 0) goto L_0x0036
            long r9 = r13.f156Oc
            int r11 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r11 == 0) goto L_0x0038
            int r11 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r11 > 0) goto L_0x0022
            int r9 = r13.mAlpha
            r3.setAlpha(r9)
            r13.f156Oc = r7
            goto L_0x0038
        L_0x0022:
            long r9 = r9 - r1
            long r9 = r9 * r4
            int r9 = (int) r9
            androidx.appcompat.b.a.k r10 = r13.f149Hc
            int r10 = r10.mEnterFadeDuration
            int r9 = r9 / r10
            int r9 = 255 - r9
            int r10 = r13.mAlpha
            int r9 = r9 * r10
            int r9 = r9 / 255
            r3.setAlpha(r9)
            r3 = r0
            goto L_0x0039
        L_0x0036:
            r13.f156Oc = r7
        L_0x0038:
            r3 = r6
        L_0x0039:
            android.graphics.drawable.Drawable r9 = r13.f152Kc
            if (r9 == 0) goto L_0x0061
            long r10 = r13.f157Pc
            int r12 = (r10 > r7 ? 1 : (r10 == r7 ? 0 : -1))
            if (r12 == 0) goto L_0x0063
            int r12 = (r10 > r1 ? 1 : (r10 == r1 ? 0 : -1))
            if (r12 > 0) goto L_0x0050
            r9.setVisible(r6, r6)
            r0 = 0
            r13.f152Kc = r0
            r13.f157Pc = r7
            goto L_0x0063
        L_0x0050:
            long r10 = r10 - r1
            long r10 = r10 * r4
            int r3 = (int) r10
            androidx.appcompat.b.a.k r4 = r13.f149Hc
            int r4 = r4.mExitFadeDuration
            int r3 = r3 / r4
            int r4 = r13.mAlpha
            int r3 = r3 * r4
            int r3 = r3 / 255
            r9.setAlpha(r3)
            goto L_0x0064
        L_0x0061:
            r13.f157Pc = r7
        L_0x0063:
            r0 = r3
        L_0x0064:
            if (r14 == 0) goto L_0x0070
            if (r0 == 0) goto L_0x0070
            java.lang.Runnable r14 = r13.f155Nc
            r3 = 16
            long r1 = r1 + r3
            r13.scheduleSelf(r14, r1)
        L_0x0070:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.p020b.p021a.C0169l.animate(boolean):void");
    }

    public void applyTheme(Resources.Theme theme) {
        this.f149Hc.applyTheme(theme);
    }

    public boolean canApplyTheme() {
        C0168k kVar = this.f149Hc;
        int i = kVar.mNumChildren;
        Drawable[] drawableArr = kVar.mDrawables;
        for (int i2 = 0; i2 < i; i2++) {
            Drawable drawable = drawableArr[i2];
            if (drawable == null) {
                Drawable.ConstantState constantState = (Drawable.ConstantState) kVar.mDrawableFutures.get(i2);
                if (constantState != null && constantState.canApplyTheme()) {
                    return true;
                }
            } else if (drawable.canApplyTheme()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void clearMutated() {
        this.f149Hc.mMutated = false;
        this.mMutated = false;
    }

    /* access modifiers changed from: package-private */
    public C0168k cloneConstantState() {
        throw null;
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.f151Jc;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.f152Kc;
        if (drawable2 != null) {
            drawable2.draw(canvas);
        }
    }

    public int getAlpha() {
        return this.mAlpha;
    }

    public int getChangingConfigurations() {
        int changingConfigurations = super.getChangingConfigurations();
        C0168k kVar = this.f149Hc;
        return kVar.mChildrenChangingConfigurations | kVar.mChangingConfigurations | changingConfigurations;
    }

    public final Drawable.ConstantState getConstantState() {
        if (!this.f149Hc.canConstantState()) {
            return null;
        }
        this.f149Hc.mChangingConfigurations = getChangingConfigurations();
        return this.f149Hc;
    }

    public Drawable getCurrent() {
        return this.f151Jc;
    }

    /* access modifiers changed from: package-private */
    public int getCurrentIndex() {
        return this.f154Mc;
    }

    public void getHotspotBounds(Rect rect) {
        Rect rect2 = this.f150Ic;
        if (rect2 != null) {
            rect.set(rect2);
        } else {
            super.getHotspotBounds(rect);
        }
    }

    public int getIntrinsicHeight() {
        C0168k kVar = this.f149Hc;
        if (kVar.mConstantSize) {
            if (!kVar.mCheckedConstantSize) {
                kVar.computeConstantSize();
            }
            return kVar.mConstantHeight;
        }
        Drawable drawable = this.f151Jc;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return -1;
    }

    public int getIntrinsicWidth() {
        C0168k kVar = this.f149Hc;
        if (kVar.mConstantSize) {
            if (!kVar.mCheckedConstantSize) {
                kVar.computeConstantSize();
            }
            return kVar.mConstantWidth;
        }
        Drawable drawable = this.f151Jc;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return -1;
    }

    public int getMinimumHeight() {
        C0168k kVar = this.f149Hc;
        if (kVar.mConstantSize) {
            if (!kVar.mCheckedConstantSize) {
                kVar.computeConstantSize();
            }
            return kVar.mConstantMinimumHeight;
        }
        Drawable drawable = this.f151Jc;
        if (drawable != null) {
            return drawable.getMinimumHeight();
        }
        return 0;
    }

    public int getMinimumWidth() {
        C0168k kVar = this.f149Hc;
        if (kVar.mConstantSize) {
            if (!kVar.mCheckedConstantSize) {
                kVar.computeConstantSize();
            }
            return kVar.mConstantMinimumWidth;
        }
        Drawable drawable = this.f151Jc;
        if (drawable != null) {
            return drawable.getMinimumWidth();
        }
        return 0;
    }

    public int getOpacity() {
        Drawable drawable = this.f151Jc;
        if (drawable == null || !drawable.isVisible()) {
            return -2;
        }
        return this.f149Hc.getOpacity();
    }

    public void getOutline(Outline outline) {
        Drawable drawable = this.f151Jc;
        if (drawable != null) {
            drawable.getOutline(outline);
        }
    }

    public boolean getPadding(Rect rect) {
        boolean z;
        Rect constantPadding = this.f149Hc.getConstantPadding();
        boolean z2 = true;
        if (constantPadding != null) {
            rect.set(constantPadding);
            z = (constantPadding.right | ((constantPadding.left | constantPadding.top) | constantPadding.bottom)) != 0;
        } else {
            Drawable drawable = this.f151Jc;
            if (drawable != null) {
                z = drawable.getPadding(rect);
            } else {
                z = super.getPadding(rect);
            }
        }
        if (!isAutoMirrored() || C0322a.m262b(this) != 1) {
            z2 = false;
        }
        if (z2) {
            int i = rect.left;
            rect.left = rect.right;
            rect.right = i;
        }
        return z;
    }

    public void invalidateDrawable(Drawable drawable) {
        C0168k kVar = this.f149Hc;
        if (kVar != null) {
            kVar.mCheckedOpacity = false;
            kVar.mCheckedStateful = false;
        }
        if (drawable == this.f151Jc && getCallback() != null) {
            getCallback().invalidateDrawable(this);
        }
    }

    public boolean isAutoMirrored() {
        return this.f149Hc.mAutoMirrored;
    }

    public void jumpToCurrentState() {
        boolean z;
        Drawable drawable = this.f152Kc;
        if (drawable != null) {
            drawable.jumpToCurrentState();
            this.f152Kc = null;
            z = true;
        } else {
            z = false;
        }
        Drawable drawable2 = this.f151Jc;
        if (drawable2 != null) {
            drawable2.jumpToCurrentState();
            if (this.f153Lc) {
                this.f151Jc.setAlpha(this.mAlpha);
            }
        }
        if (this.f157Pc != 0) {
            this.f157Pc = 0;
            z = true;
        }
        if (this.f156Oc != 0) {
            this.f156Oc = 0;
            z = true;
        }
        if (z) {
            invalidateSelf();
        }
    }

    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            C0168k cloneConstantState = cloneConstantState();
            cloneConstantState.mutate();
            mo1085a(cloneConstantState);
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f152Kc;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        Drawable drawable2 = this.f151Jc;
        if (drawable2 != null) {
            drawable2.setBounds(rect);
        }
    }

    public boolean onLayoutDirectionChanged(int i) {
        C0168k kVar = this.f149Hc;
        int i2 = this.f154Mc;
        int i3 = kVar.mNumChildren;
        Drawable[] drawableArr = kVar.mDrawables;
        boolean z = false;
        for (int i4 = 0; i4 < i3; i4++) {
            if (drawableArr[i4] != null) {
                int i5 = Build.VERSION.SDK_INT;
                boolean layoutDirection = drawableArr[i4].setLayoutDirection(i);
                if (i4 == i2) {
                    z = layoutDirection;
                }
            }
        }
        kVar.mLayoutDirection = i;
        return z;
    }

    /* access modifiers changed from: protected */
    public boolean onLevelChange(int i) {
        Drawable drawable = this.f152Kc;
        if (drawable != null) {
            return drawable.setLevel(i);
        }
        Drawable drawable2 = this.f151Jc;
        if (drawable2 != null) {
            return drawable2.setLevel(i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        Drawable drawable = this.f152Kc;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        Drawable drawable2 = this.f151Jc;
        if (drawable2 != null) {
            return drawable2.setState(iArr);
        }
        return false;
    }

    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        if (drawable == this.f151Jc && getCallback() != null) {
            getCallback().scheduleDrawable(this, runnable, j);
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean selectDrawable(int r10) {
        /*
            r9 = this;
            int r0 = r9.f154Mc
            r1 = 0
            if (r10 != r0) goto L_0x0006
            return r1
        L_0x0006:
            long r2 = android.os.SystemClock.uptimeMillis()
            androidx.appcompat.b.a.k r0 = r9.f149Hc
            int r0 = r0.mExitFadeDuration
            r4 = 0
            r5 = 0
            if (r0 <= 0) goto L_0x002e
            android.graphics.drawable.Drawable r0 = r9.f152Kc
            if (r0 == 0) goto L_0x001a
            r0.setVisible(r1, r1)
        L_0x001a:
            android.graphics.drawable.Drawable r0 = r9.f151Jc
            if (r0 == 0) goto L_0x0029
            r9.f152Kc = r0
            androidx.appcompat.b.a.k r0 = r9.f149Hc
            int r0 = r0.mExitFadeDuration
            long r0 = (long) r0
            long r0 = r0 + r2
            r9.f157Pc = r0
            goto L_0x0035
        L_0x0029:
            r9.f152Kc = r4
            r9.f157Pc = r5
            goto L_0x0035
        L_0x002e:
            android.graphics.drawable.Drawable r0 = r9.f151Jc
            if (r0 == 0) goto L_0x0035
            r0.setVisible(r1, r1)
        L_0x0035:
            if (r10 < 0) goto L_0x0055
            androidx.appcompat.b.a.k r0 = r9.f149Hc
            int r1 = r0.mNumChildren
            if (r10 >= r1) goto L_0x0055
            android.graphics.drawable.Drawable r0 = r0.getChild(r10)
            r9.f151Jc = r0
            r9.f154Mc = r10
            if (r0 == 0) goto L_0x005a
            androidx.appcompat.b.a.k r10 = r9.f149Hc
            int r10 = r10.mEnterFadeDuration
            if (r10 <= 0) goto L_0x0051
            long r7 = (long) r10
            long r2 = r2 + r7
            r9.f156Oc = r2
        L_0x0051:
            r9.m147e(r0)
            goto L_0x005a
        L_0x0055:
            r9.f151Jc = r4
            r10 = -1
            r9.f154Mc = r10
        L_0x005a:
            long r0 = r9.f156Oc
            int r10 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            r0 = 1
            if (r10 != 0) goto L_0x0067
            long r1 = r9.f157Pc
            int r10 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r10 == 0) goto L_0x0079
        L_0x0067:
            java.lang.Runnable r10 = r9.f155Nc
            if (r10 != 0) goto L_0x0073
            androidx.appcompat.b.a.i r10 = new androidx.appcompat.b.a.i
            r10.<init>(r9)
            r9.f155Nc = r10
            goto L_0x0076
        L_0x0073:
            r9.unscheduleSelf(r10)
        L_0x0076:
            r9.animate(r0)
        L_0x0079:
            r9.invalidateSelf()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.p020b.p021a.C0169l.selectDrawable(int):boolean");
    }

    public void setAlpha(int i) {
        if (!this.f153Lc || this.mAlpha != i) {
            this.f153Lc = true;
            this.mAlpha = i;
            Drawable drawable = this.f151Jc;
            if (drawable == null) {
                return;
            }
            if (this.f156Oc == 0) {
                drawable.setAlpha(i);
            } else {
                animate(false);
            }
        }
    }

    public void setAutoMirrored(boolean z) {
        C0168k kVar = this.f149Hc;
        if (kVar.mAutoMirrored != z) {
            kVar.mAutoMirrored = z;
            Drawable drawable = this.f151Jc;
            if (drawable != null) {
                boolean z2 = kVar.mAutoMirrored;
                int i = Build.VERSION.SDK_INT;
                drawable.setAutoMirrored(z2);
            }
        }
    }

    public void setColorFilter(ColorFilter colorFilter) {
        C0168k kVar = this.f149Hc;
        kVar.mHasColorFilter = true;
        if (kVar.mColorFilter != colorFilter) {
            kVar.mColorFilter = colorFilter;
            Drawable drawable = this.f151Jc;
            if (drawable != null) {
                drawable.setColorFilter(colorFilter);
            }
        }
    }

    public void setDither(boolean z) {
        C0168k kVar = this.f149Hc;
        if (kVar.mDither != z) {
            kVar.mDither = z;
            Drawable drawable = this.f151Jc;
            if (drawable != null) {
                drawable.setDither(kVar.mDither);
            }
        }
    }

    public void setHotspot(float f, float f2) {
        Drawable drawable = this.f151Jc;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setHotspot(f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Rect rect = this.f150Ic;
        if (rect == null) {
            this.f150Ic = new Rect(i, i2, i3, i4);
        } else {
            rect.set(i, i2, i3, i4);
        }
        Drawable drawable = this.f151Jc;
        if (drawable != null) {
            int i5 = Build.VERSION.SDK_INT;
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    public void setTintList(ColorStateList colorStateList) {
        C0168k kVar = this.f149Hc;
        kVar.mHasTintList = true;
        if (kVar.mTintList != colorStateList) {
            kVar.mTintList = colorStateList;
            Drawable drawable = this.f151Jc;
            int i = Build.VERSION.SDK_INT;
            drawable.setTintList(colorStateList);
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        C0168k kVar = this.f149Hc;
        kVar.mHasTintMode = true;
        if (kVar.mTintMode != mode) {
            kVar.mTintMode = mode;
            Drawable drawable = this.f151Jc;
            int i = Build.VERSION.SDK_INT;
            drawable.setTintMode(mode);
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        Drawable drawable = this.f152Kc;
        if (drawable != null) {
            drawable.setVisible(z, z2);
        }
        Drawable drawable2 = this.f151Jc;
        if (drawable2 != null) {
            drawable2.setVisible(z, z2);
        }
        return visible;
    }

    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        if (drawable == this.f151Jc && getCallback() != null) {
            getCallback().unscheduleDrawable(this, runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public final void updateDensity(Resources resources) {
        this.f149Hc.updateDensity(resources);
    }
}
