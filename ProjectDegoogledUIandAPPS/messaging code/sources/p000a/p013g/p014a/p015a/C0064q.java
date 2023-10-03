package p000a.p013g.p014a.p015a;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import androidx.core.graphics.drawable.C0322a;
import androidx.core.view.ViewCompat;
import org.xmlpull.v1.XmlPullParser;

/* renamed from: a.g.a.a.q */
public class C0064q extends C0054g {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;

    /* renamed from: ld */
    private C0062o f74ld;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private final Matrix mTmpMatrix;

    /* renamed from: md */
    private PorterDuffColorFilter f75md;

    /* renamed from: nd */
    private boolean f76nd;

    /* renamed from: od */
    private final float[] f77od;

    /* renamed from: pd */
    private final Rect f78pd;

    C0064q() {
        this.f76nd = true;
        this.f77od = new float[9];
        this.mTmpMatrix = new Matrix();
        this.f78pd = new Rect();
        this.f74ld = new C0062o();
    }

    /* renamed from: a */
    public static C0064q m81a(Resources resources, int i, Resources.Theme theme) {
        int i2 = Build.VERSION.SDK_INT;
        C0064q qVar = new C0064q();
        int i3 = Build.VERSION.SDK_INT;
        qVar.f40hd = resources.getDrawable(i, theme);
        new C0063p(qVar.f40hd.getConstantState());
        return qVar;
    }

    public static C0064q createFromXmlInner(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) {
        C0064q qVar = new C0064q();
        qVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return qVar;
    }

    public void applyTheme(Resources.Theme theme) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            C0322a.m261a(drawable, theme);
        }
    }

    public boolean canApplyTheme() {
        Drawable drawable = this.f40hd;
        if (drawable == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        drawable.canApplyTheme();
        return false;
    }

    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.f78pd);
        if (this.f78pd.width() > 0 && this.f78pd.height() > 0) {
            ColorFilter colorFilter = this.mColorFilter;
            if (colorFilter == null) {
                colorFilter = this.f75md;
            }
            canvas.getMatrix(this.mTmpMatrix);
            this.mTmpMatrix.getValues(this.f77od);
            float abs = Math.abs(this.f77od[0]);
            float abs2 = Math.abs(this.f77od[4]);
            boolean z = true;
            float abs3 = Math.abs(this.f77od[1]);
            float abs4 = Math.abs(this.f77od[3]);
            if (!(abs3 == 0.0f && abs4 == 0.0f)) {
                abs = 1.0f;
                abs2 = 1.0f;
            }
            int min = Math.min(2048, (int) (((float) this.f78pd.width()) * abs));
            int min2 = Math.min(2048, (int) (((float) this.f78pd.height()) * abs2));
            if (min > 0 && min2 > 0) {
                int save = canvas.save();
                Rect rect = this.f78pd;
                canvas.translate((float) rect.left, (float) rect.top);
                int i = Build.VERSION.SDK_INT;
                if (!isAutoMirrored() || C0322a.m262b(this) != 1) {
                    z = false;
                }
                if (z) {
                    canvas.translate((float) this.f78pd.width(), 0.0f);
                    canvas.scale(-1.0f, 1.0f);
                }
                this.f78pd.offsetTo(0, 0);
                this.f74ld.mo368b(min, min2);
                if (!this.f76nd) {
                    this.f74ld.mo369c(min, min2);
                } else if (!this.f74ld.canReuseCache()) {
                    this.f74ld.mo369c(min, min2);
                    this.f74ld.updateCacheStates();
                }
                this.f74ld.mo365a(canvas, colorFilter, this.f78pd);
                canvas.restoreToCount(save);
            }
        }
    }

    public int getAlpha() {
        Drawable drawable = this.f40hd;
        if (drawable == null) {
            return this.f74ld.f70xc.getRootAlpha();
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.getAlpha();
    }

    public int getChangingConfigurations() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return this.f74ld.getChangingConfigurations() | super.getChangingConfigurations();
    }

    public ColorFilter getColorFilter() {
        Drawable drawable = this.f40hd;
        if (drawable == null) {
            return this.mColorFilter;
        }
        int i = Build.VERSION.SDK_INT;
        return drawable.getColorFilter();
    }

    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            return new C0063p(drawable.getConstantState());
        }
        this.f74ld.mChangingConfigurations = getChangingConfigurations();
        return this.f74ld;
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public int getIntrinsicHeight() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.getIntrinsicHeight();
        }
        return (int) this.f74ld.f70xc.mBaseHeight;
    }

    public int getIntrinsicWidth() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.getIntrinsicWidth();
        }
        return (int) this.f74ld.f70xc.mBaseWidth;
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public int getOpacity() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    /* access modifiers changed from: package-private */
    public Object getTargetByName(String str) {
        return this.f74ld.f70xc.mVGTargetsMap.get(str);
    }

    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, (Resources.Theme) null);
        }
    }

    public void invalidateSelf() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public boolean isAutoMirrored() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return C0322a.m263c(drawable);
        }
        return this.f74ld.mAutoMirrored;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        r1 = r1.f74ld.mTint;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
        r0 = r1.f74ld;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isStateful() {
        /*
            r1 = this;
            android.graphics.drawable.Drawable r0 = r1.f40hd
            if (r0 == 0) goto L_0x0009
            boolean r1 = r0.isStateful()
            return r1
        L_0x0009:
            boolean r0 = super.isStateful()
            if (r0 != 0) goto L_0x0028
            a.g.a.a.o r0 = r1.f74ld
            if (r0 == 0) goto L_0x0026
            boolean r0 = r0.isStateful()
            if (r0 != 0) goto L_0x0028
            a.g.a.a.o r1 = r1.f74ld
            android.content.res.ColorStateList r1 = r1.mTint
            if (r1 == 0) goto L_0x0026
            boolean r1 = r1.isStateful()
            if (r1 == 0) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            r1 = 0
            goto L_0x0029
        L_0x0028:
            r1 = 1
        L_0x0029:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p013g.p014a.p015a.C0064q.isStateful():boolean");
    }

    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public Drawable mutate() {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.mMutated && super.mutate() == this) {
            this.f74ld = new C0062o(this.f74ld);
            this.mMutated = true;
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void onBoundsChange(Rect rect) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        C0062o oVar = this.f74ld;
        ColorStateList colorStateList = oVar.mTint;
        if (!(colorStateList == null || (mode = oVar.mTintMode) == null)) {
            this.f75md = updateTintFilter(this.f75md, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!oVar.isStateful() || !oVar.mo367a(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    /* access modifiers changed from: package-private */
    public void setAllowCaching(boolean z) {
        this.f76nd = z;
    }

    public void setAlpha(int i) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.f74ld.f70xc.getRootAlpha() != i) {
            this.f74ld.f70xc.setRootAlpha(i);
            invalidateSelf();
        }
    }

    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setAutoMirrored(z);
            return;
        }
        this.f74ld.mAutoMirrored = z;
    }

    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    public void setFilterBitmap(boolean z) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.setFilterBitmap(z);
        }
    }

    public void setHotspot(float f, float f2) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setHotspot(f, f2);
        }
    }

    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i5 = Build.VERSION.SDK_INT;
            drawable.setHotspotBounds(i, i2, i3, i4);
        }
    }

    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    public void setTint(int i) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i2 = Build.VERSION.SDK_INT;
            drawable.setTint(i);
            return;
        }
        setTintList(ColorStateList.valueOf(i));
    }

    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setTintList(colorStateList);
            return;
        }
        C0062o oVar = this.f74ld;
        if (oVar.mTint != colorStateList) {
            oVar.mTint = colorStateList;
            this.f75md = updateTintFilter(this.f75md, colorStateList, oVar.mTintMode);
            invalidateSelf();
        }
    }

    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            drawable.setTintMode(mode);
            return;
        }
        C0062o oVar = this.f74ld;
        if (oVar.mTintMode != mode) {
            oVar.mTintMode = mode;
            this.f75md = updateTintFilter(this.f75md, oVar.mTint, mode);
            invalidateSelf();
        }
    }

    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            return drawable.setVisible(z, z2);
        }
        return super.setVisible(z, z2);
    }

    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    /* access modifiers changed from: package-private */
    public PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(super.getState(), 0), mode);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f40hd;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
            return;
        }
        this.mColorFilter = colorFilter;
        invalidateSelf();
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0263  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void inflate(android.content.res.Resources r18, org.xmlpull.v1.XmlPullParser r19, android.util.AttributeSet r20, android.content.res.Resources.Theme r21) {
        /*
            r17 = this;
            r1 = r17
            r2 = r18
            r3 = r19
            r4 = r20
            r5 = r21
            android.graphics.drawable.Drawable r0 = r1.f40hd
            if (r0 == 0) goto L_0x0014
            int r1 = android.os.Build.VERSION.SDK_INT
            r0.inflate(r2, r3, r4, r5)
            return
        L_0x0014:
            a.g.a.a.o r6 = r1.f74ld
            a.g.a.a.n r0 = new a.g.a.a.n
            r0.<init>()
            r6.f70xc = r0
            int[] r0 = p000a.p013g.p014a.p015a.C0048a.f24Mt
            android.content.res.TypedArray r7 = androidx.core.content.p022a.C0308a.m241a(r2, r5, r4, r0)
            a.g.a.a.o r8 = r1.f74ld
            a.g.a.a.n r9 = r8.f70xc
            r0 = 6
            r10 = -1
            java.lang.String r11 = "tintMode"
            int r0 = androidx.core.content.p022a.C0308a.m245b(r7, r3, r11, r0, r10)
            android.graphics.PorterDuff$Mode r10 = android.graphics.PorterDuff.Mode.SRC_IN
            r11 = 5
            r12 = 3
            if (r0 == r12) goto L_0x004b
            if (r0 == r11) goto L_0x004d
            r13 = 9
            if (r0 == r13) goto L_0x0048
            switch(r0) {
                case 14: goto L_0x0045;
                case 15: goto L_0x0042;
                case 16: goto L_0x003f;
                default: goto L_0x003e;
            }
        L_0x003e:
            goto L_0x004d
        L_0x003f:
            android.graphics.PorterDuff$Mode r10 = android.graphics.PorterDuff.Mode.ADD
            goto L_0x004d
        L_0x0042:
            android.graphics.PorterDuff$Mode r10 = android.graphics.PorterDuff.Mode.SCREEN
            goto L_0x004d
        L_0x0045:
            android.graphics.PorterDuff$Mode r10 = android.graphics.PorterDuff.Mode.MULTIPLY
            goto L_0x004d
        L_0x0048:
            android.graphics.PorterDuff$Mode r10 = android.graphics.PorterDuff.Mode.SRC_ATOP
            goto L_0x004d
        L_0x004b:
            android.graphics.PorterDuff$Mode r10 = android.graphics.PorterDuff.Mode.SRC_OVER
        L_0x004d:
            r8.mTintMode = r10
            java.lang.String r0 = "tint"
            boolean r0 = androidx.core.content.p022a.C0308a.m244a((org.xmlpull.v1.XmlPullParser) r3, (java.lang.String) r0)
            r10 = 1
            r13 = 0
            r14 = 2
            if (r0 == 0) goto L_0x00ae
            android.util.TypedValue r0 = new android.util.TypedValue
            r0.<init>()
            r7.getValue(r10, r0)
            int r15 = r0.type
            if (r15 == r14) goto L_0x008f
            r14 = 28
            if (r15 < r14) goto L_0x0075
            r14 = 31
            if (r15 > r14) goto L_0x0075
            int r0 = r0.data
            android.content.res.ColorStateList r0 = android.content.res.ColorStateList.valueOf(r0)
            goto L_0x00af
        L_0x0075:
            android.content.res.Resources r0 = r7.getResources()
            int r14 = r7.getResourceId(r10, r13)
            android.content.res.XmlResourceParser r14 = r0.getXml(r14)     // Catch:{ Exception -> 0x0086 }
            android.content.res.ColorStateList r0 = androidx.core.content.p022a.C0308a.createFromXml(r0, r14, r5)     // Catch:{ Exception -> 0x0086 }
            goto L_0x00af
        L_0x0086:
            r0 = move-exception
            java.lang.String r14 = "CSLCompat"
            java.lang.String r15 = "Failed to inflate ColorStateList."
            android.util.Log.e(r14, r15, r0)
            goto L_0x00ae
        L_0x008f:
            java.lang.UnsupportedOperationException r1 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to resolve attribute at index "
            r2.append(r3)
            r2.append(r10)
            java.lang.String r3 = ": "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L_0x00ae:
            r0 = 0
        L_0x00af:
            if (r0 == 0) goto L_0x00b3
            r8.mTint = r0
        L_0x00b3:
            boolean r0 = r8.mAutoMirrored
            java.lang.String r14 = "autoMirrored"
            boolean r14 = androidx.core.content.p022a.C0308a.m244a((org.xmlpull.v1.XmlPullParser) r3, (java.lang.String) r14)
            if (r14 != 0) goto L_0x00be
            goto L_0x00c2
        L_0x00be:
            boolean r0 = r7.getBoolean(r11, r0)
        L_0x00c2:
            r8.mAutoMirrored = r0
            r0 = 7
            float r8 = r9.mViewportWidth
            java.lang.String r11 = "viewportWidth"
            float r0 = androidx.core.content.p022a.C0308a.m239a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r3, (java.lang.String) r11, (int) r0, (float) r8)
            r9.mViewportWidth = r0
            r0 = 8
            float r8 = r9.mViewportHeight
            java.lang.String r11 = "viewportHeight"
            float r0 = androidx.core.content.p022a.C0308a.m239a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r3, (java.lang.String) r11, (int) r0, (float) r8)
            r9.mViewportHeight = r0
            float r0 = r9.mViewportWidth
            r8 = 0
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x0263
            float r0 = r9.mViewportHeight
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x0248
            float r0 = r9.mBaseWidth
            float r0 = r7.getDimension(r12, r0)
            r9.mBaseWidth = r0
            float r0 = r9.mBaseHeight
            r11 = 2
            float r0 = r7.getDimension(r11, r0)
            r9.mBaseHeight = r0
            float r0 = r9.mBaseWidth
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x022d
            float r0 = r9.mBaseHeight
            int r0 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1))
            if (r0 <= 0) goto L_0x0212
            r0 = 4
            float r8 = r9.getAlpha()
            java.lang.String r11 = "alpha"
            float r0 = androidx.core.content.p022a.C0308a.m239a((android.content.res.TypedArray) r7, (org.xmlpull.v1.XmlPullParser) r3, (java.lang.String) r11, (int) r0, (float) r8)
            r9.setAlpha(r0)
            java.lang.String r0 = r7.getString(r13)
            if (r0 == 0) goto L_0x0120
            r9.mRootName = r0
            a.b.b r8 = r9.mVGTargetsMap
            r8.put(r0, r9)
        L_0x0120:
            r7.recycle()
            int r0 = r17.getChangingConfigurations()
            r6.mChangingConfigurations = r0
            r6.mCacheDirty = r10
            a.g.a.a.o r0 = r1.f74ld
            a.g.a.a.n r7 = r0.f70xc
            java.util.ArrayDeque r8 = new java.util.ArrayDeque
            r8.<init>()
            a.g.a.a.k r9 = r7.mRootGroup
            r8.push(r9)
            int r9 = r19.getEventType()
            int r11 = r19.getDepth()
            int r11 = r11 + r10
            r14 = r10
        L_0x0143:
            if (r9 == r10) goto L_0x01fb
            int r15 = r19.getDepth()
            if (r15 >= r11) goto L_0x014d
            if (r9 == r12) goto L_0x01fb
        L_0x014d:
            java.lang.String r15 = "group"
            r10 = 2
            if (r9 != r10) goto L_0x01e4
            java.lang.String r9 = r19.getName()
            java.lang.Object r16 = r8.peek()
            r10 = r16
            a.g.a.a.k r10 = (p000a.p013g.p014a.p015a.C0058k) r10
            java.lang.String r13 = "path"
            boolean r13 = r13.equals(r9)
            if (r13 == 0) goto L_0x018b
            a.g.a.a.j r9 = new a.g.a.a.j
            r9.<init>()
            r9.mo317a(r2, r4, r5, r3)
            java.util.ArrayList r10 = r10.mChildren
            r10.add(r9)
            java.lang.String r10 = r9.getPathName()
            if (r10 == 0) goto L_0x0182
            a.b.b r10 = r7.mVGTargetsMap
            java.lang.String r13 = r9.getPathName()
            r10.put(r13, r9)
        L_0x0182:
            int r10 = r0.mChangingConfigurations
            int r9 = r9.mChangingConfigurations
            r9 = r9 | r10
            r0.mChangingConfigurations = r9
            r14 = 0
            goto L_0x01f3
        L_0x018b:
            java.lang.String r13 = "clip-path"
            boolean r13 = r13.equals(r9)
            if (r13 == 0) goto L_0x01b7
            a.g.a.a.i r9 = new a.g.a.a.i
            r9.<init>()
            r9.mo315a(r2, r4, r5, r3)
            java.util.ArrayList r10 = r10.mChildren
            r10.add(r9)
            java.lang.String r10 = r9.getPathName()
            if (r10 == 0) goto L_0x01af
            a.b.b r10 = r7.mVGTargetsMap
            java.lang.String r13 = r9.getPathName()
            r10.put(r13, r9)
        L_0x01af:
            int r10 = r0.mChangingConfigurations
            int r9 = r9.mChangingConfigurations
            r9 = r9 | r10
            r0.mChangingConfigurations = r9
            goto L_0x01f3
        L_0x01b7:
            boolean r9 = r15.equals(r9)
            if (r9 == 0) goto L_0x01f3
            a.g.a.a.k r9 = new a.g.a.a.k
            r9.<init>()
            r9.mo336a(r2, r4, r5, r3)
            java.util.ArrayList r10 = r10.mChildren
            r10.add(r9)
            r8.push(r9)
            java.lang.String r10 = r9.getGroupName()
            if (r10 == 0) goto L_0x01dc
            a.b.b r10 = r7.mVGTargetsMap
            java.lang.String r13 = r9.getGroupName()
            r10.put(r13, r9)
        L_0x01dc:
            int r10 = r0.mChangingConfigurations
            int r9 = r9.mChangingConfigurations
            r9 = r9 | r10
            r0.mChangingConfigurations = r9
            goto L_0x01f3
        L_0x01e4:
            if (r9 != r12) goto L_0x01f3
            java.lang.String r9 = r19.getName()
            boolean r9 = r15.equals(r9)
            if (r9 == 0) goto L_0x01f3
            r8.pop()
        L_0x01f3:
            int r9 = r19.next()
            r10 = 1
            r13 = 0
            goto L_0x0143
        L_0x01fb:
            if (r14 != 0) goto L_0x020a
            android.graphics.PorterDuffColorFilter r0 = r1.f75md
            android.content.res.ColorStateList r2 = r6.mTint
            android.graphics.PorterDuff$Mode r3 = r6.mTintMode
            android.graphics.PorterDuffColorFilter r0 = r1.updateTintFilter(r0, r2, r3)
            r1.f75md = r0
            return
        L_0x020a:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.String r1 = "no path defined"
            r0.<init>(r1)
            throw r0
        L_0x0212:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r7.getPositionDescription()
            r1.append(r2)
            java.lang.String r2 = "<vector> tag requires height > 0"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x022d:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r7.getPositionDescription()
            r1.append(r2)
            java.lang.String r2 = "<vector> tag requires width > 0"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0248:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r7.getPositionDescription()
            r1.append(r2)
            java.lang.String r2 = "<vector> tag requires viewportHeight > 0"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0263:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r7.getPositionDescription()
            r1.append(r2)
            java.lang.String r2 = "<vector> tag requires viewportWidth > 0"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p000a.p013g.p014a.p015a.C0064q.inflate(android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    C0064q(C0062o oVar) {
        this.f76nd = true;
        this.f77od = new float[9];
        this.mTmpMatrix = new Matrix();
        this.f78pd = new Rect();
        this.f74ld = oVar;
        this.f75md = updateTintFilter(this.f75md, oVar.mTint, oVar.mTintMode);
    }

    /* renamed from: a */
    static int m80a(int i, float f) {
        return (i & ViewCompat.MEASURED_SIZE_MASK) | (((int) (((float) Color.alpha(i)) * f)) << 24);
    }
}
