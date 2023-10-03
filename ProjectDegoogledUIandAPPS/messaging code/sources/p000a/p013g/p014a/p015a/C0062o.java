package p000a.p013g.p014a.p015a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* renamed from: a.g.a.a.o */
class C0062o extends Drawable.ConstantState {

    /* renamed from: Ac */
    int f68Ac;

    /* renamed from: Bc */
    Paint f69Bc;
    boolean mAutoMirrored;
    boolean mCacheDirty;
    boolean mCachedAutoMirrored;
    ColorStateList mCachedTint;
    int mChangingConfigurations;
    ColorStateList mTint;
    PorterDuff.Mode mTintMode;

    /* renamed from: xc */
    C0061n f70xc;

    /* renamed from: yc */
    Bitmap f71yc;

    /* renamed from: zc */
    PorterDuff.Mode f72zc;

    public C0062o(C0062o oVar) {
        this.mTint = null;
        this.mTintMode = C0064q.DEFAULT_TINT_MODE;
        if (oVar != null) {
            this.mChangingConfigurations = oVar.mChangingConfigurations;
            this.f70xc = new C0061n(oVar.f70xc);
            Paint paint = oVar.f70xc.f64uu;
            if (paint != null) {
                this.f70xc.f64uu = new Paint(paint);
            }
            Paint paint2 = oVar.f70xc.f63tu;
            if (paint2 != null) {
                this.f70xc.f63tu = new Paint(paint2);
            }
            this.mTint = oVar.mTint;
            this.mTintMode = oVar.mTintMode;
            this.mAutoMirrored = oVar.mAutoMirrored;
        }
    }

    /* renamed from: a */
    public void mo365a(Canvas canvas, ColorFilter colorFilter, Rect rect) {
        canvas.drawBitmap(this.f71yc, (Rect) null, rect, mo364a(colorFilter));
    }

    /* renamed from: b */
    public void mo368b(int i, int i2) {
        if (this.f71yc == null || !mo366a(i, i2)) {
            this.f71yc = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            this.mCacheDirty = true;
        }
    }

    /* renamed from: c */
    public void mo369c(int i, int i2) {
        this.f71yc.eraseColor(0);
        this.f70xc.mo357a(new Canvas(this.f71yc), i, i2, (ColorFilter) null);
    }

    public boolean canReuseCache() {
        return !this.mCacheDirty && this.mCachedTint == this.mTint && this.f72zc == this.mTintMode && this.mCachedAutoMirrored == this.mAutoMirrored && this.f68Ac == this.f70xc.getRootAlpha();
    }

    public int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }

    public boolean isStateful() {
        return this.f70xc.isStateful();
    }

    public Drawable newDrawable() {
        return new C0064q(this);
    }

    /* renamed from: qb */
    public boolean mo375qb() {
        return this.f70xc.getRootAlpha() < 255;
    }

    public void updateCacheStates() {
        this.mCachedTint = this.mTint;
        this.f72zc = this.mTintMode;
        this.f68Ac = this.f70xc.getRootAlpha();
        this.mCachedAutoMirrored = this.mAutoMirrored;
        this.mCacheDirty = false;
    }

    public Drawable newDrawable(Resources resources) {
        return new C0064q(this);
    }

    /* renamed from: a */
    public Paint mo364a(ColorFilter colorFilter) {
        if (!mo375qb() && colorFilter == null) {
            return null;
        }
        if (this.f69Bc == null) {
            this.f69Bc = new Paint();
            this.f69Bc.setFilterBitmap(true);
        }
        this.f69Bc.setAlpha(this.f70xc.getRootAlpha());
        this.f69Bc.setColorFilter(colorFilter);
        return this.f69Bc;
    }

    /* renamed from: a */
    public boolean mo366a(int i, int i2) {
        return i == this.f71yc.getWidth() && i2 == this.f71yc.getHeight();
    }

    /* renamed from: a */
    public boolean mo367a(int[] iArr) {
        boolean a = this.f70xc.mo358a(iArr);
        this.mCacheDirty |= a;
        return a;
    }

    public C0062o() {
        this.mTint = null;
        this.mTintMode = C0064q.DEFAULT_TINT_MODE;
        this.f70xc = new C0061n();
    }
}
