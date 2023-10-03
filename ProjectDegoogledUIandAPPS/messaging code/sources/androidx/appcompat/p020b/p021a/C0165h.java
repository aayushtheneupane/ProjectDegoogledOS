package androidx.appcompat.p020b.p021a;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.C0323b;

@SuppressLint({"RestrictedAPI"})
/* renamed from: androidx.appcompat.b.a.h */
public class C0165h extends C0173p implements C0323b {

    /* renamed from: Sc */
    private int f147Sc = -1;

    /* renamed from: Tc */
    private int f148Tc = -1;
    private boolean mMutated;
    private C0160c mState;
    private C0164g mTransition;

    C0165h(C0160c cVar, Resources resources) {
        super((C0172o) null);
        C0160c cVar2 = new C0160c(cVar, this, resources);
        super.mo1085a(cVar2);
        this.mState = cVar2;
        onStateChange(getState());
        jumpToCurrentState();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01f7, code lost:
        return r5;
     */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static androidx.appcompat.p020b.p021a.C0165h m144a(android.content.Context r17, android.content.res.Resources r18, org.xmlpull.v1.XmlPullParser r19, android.util.AttributeSet r20, android.content.res.Resources.Theme r21) {
        /*
            r0 = r17
            r1 = r18
            r2 = r19
            r3 = r20
            r4 = r21
            java.lang.String r5 = r19.getName()
            java.lang.String r6 = "animated-selector"
            boolean r6 = r5.equals(r6)
            if (r6 == 0) goto L_0x01f8
            androidx.appcompat.b.a.h r5 = new androidx.appcompat.b.a.h
            r6 = 0
            r5.<init>(r6, r6)
            int[] r7 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableCompat
            android.content.res.TypedArray r7 = androidx.core.content.p022a.C0308a.m241a(r1, r4, r3, r7)
            int r8 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableCompat_android_visible
            r9 = 1
            boolean r8 = r7.getBoolean(r8, r9)
            super.setVisible(r8, r9)
            androidx.appcompat.b.a.g r10 = r5.mTransition
            if (r10 == 0) goto L_0x0039
            if (r8 == 0) goto L_0x0036
            r10.start()
            goto L_0x0039
        L_0x0036:
            r5.jumpToCurrentState()
        L_0x0039:
            androidx.appcompat.b.a.c r8 = r5.mState
            int r10 = android.os.Build.VERSION.SDK_INT
            int r10 = r8.mChangingConfigurations
            int r11 = r7.getChangingConfigurations()
            r10 = r10 | r11
            r8.mChangingConfigurations = r10
            int r10 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableCompat_android_variablePadding
            boolean r11 = r8.mVariablePadding
            boolean r10 = r7.getBoolean(r10, r11)
            r8.mVariablePadding = r10
            int r10 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableCompat_android_constantSize
            boolean r11 = r8.mConstantSize
            boolean r10 = r7.getBoolean(r10, r11)
            r8.mConstantSize = r10
            int r10 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableCompat_android_enterFadeDuration
            int r11 = r8.mEnterFadeDuration
            int r10 = r7.getInt(r10, r11)
            r8.mEnterFadeDuration = r10
            int r10 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableCompat_android_exitFadeDuration
            int r11 = r8.mExitFadeDuration
            int r10 = r7.getInt(r10, r11)
            r8.mExitFadeDuration = r10
            int r10 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableCompat_android_dither
            boolean r8 = r8.mDither
            boolean r8 = r7.getBoolean(r10, r8)
            super.setDither(r8)
            r5.updateDensity(r1)
            r7.recycle()
            int r7 = r19.getDepth()
            int r7 = r7 + r9
        L_0x0084:
            int r8 = r19.next()
            if (r8 == r9) goto L_0x01f0
            int r10 = r19.getDepth()
            if (r10 >= r7) goto L_0x0093
            r11 = 3
            if (r8 == r11) goto L_0x01f0
        L_0x0093:
            r11 = 2
            if (r8 == r11) goto L_0x0097
            goto L_0x0084
        L_0x0097:
            if (r10 <= r7) goto L_0x009a
            goto L_0x0084
        L_0x009a:
            java.lang.String r8 = r19.getName()
            java.lang.String r9 = "item"
            boolean r8 = r8.equals(r9)
            r10 = -1
            r11 = 0
            if (r8 == 0) goto L_0x0151
            int[] r8 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableItem
            android.content.res.TypedArray r8 = androidx.core.content.p022a.C0308a.m241a(r1, r4, r3, r8)
            int r12 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableItem_android_id
            int r12 = r8.getResourceId(r12, r11)
            int r13 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableItem_android_drawable
            int r10 = r8.getResourceId(r13, r10)
            if (r10 <= 0) goto L_0x00c4
            androidx.appcompat.widget.ResourceManagerInternal r6 = androidx.appcompat.widget.ResourceManagerInternal.get()
            android.graphics.drawable.Drawable r6 = r6.getDrawable(r0, r10)
        L_0x00c4:
            r8.recycle()
            int r8 = r20.getAttributeCount()
            int[] r10 = new int[r8]
            r13 = r11
            r14 = r13
        L_0x00cf:
            if (r13 >= r8) goto L_0x00f1
            int r15 = r3.getAttributeNameResource(r13)
            if (r15 == 0) goto L_0x00ee
            r9 = 16842960(0x10100d0, float:2.369414E-38)
            if (r15 == r9) goto L_0x00ee
            r9 = 16843161(0x1010199, float:2.3694704E-38)
            if (r15 == r9) goto L_0x00ee
            int r9 = r14 + 1
            boolean r16 = r3.getAttributeBooleanValue(r13, r11)
            if (r16 == 0) goto L_0x00ea
            goto L_0x00eb
        L_0x00ea:
            int r15 = -r15
        L_0x00eb:
            r10[r14] = r15
            r14 = r9
        L_0x00ee:
            int r13 = r13 + 1
            goto L_0x00cf
        L_0x00f1:
            int[] r8 = android.util.StateSet.trimStateSet(r10, r14)
            java.lang.String r9 = ": <item> tag requires a 'drawable' attribute or child tag defining a drawable"
            if (r6 != 0) goto L_0x012b
        L_0x00f9:
            int r6 = r19.next()
            r10 = 4
            if (r6 != r10) goto L_0x0101
            goto L_0x00f9
        L_0x0101:
            r10 = 2
            if (r6 != r10) goto L_0x011c
            java.lang.String r6 = r19.getName()
            java.lang.String r10 = "vector"
            boolean r6 = r6.equals(r10)
            if (r6 == 0) goto L_0x0115
            a.g.a.a.q r6 = p000a.p013g.p014a.p015a.C0064q.createFromXmlInner(r18, r19, r20, r21)
            goto L_0x012b
        L_0x0115:
            int r6 = android.os.Build.VERSION.SDK_INT
            android.graphics.drawable.Drawable r6 = android.graphics.drawable.Drawable.createFromXmlInner(r18, r19, r20, r21)
            goto L_0x012b
        L_0x011c:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r1 = p026b.p027a.p030b.p031a.C0632a.m1016a((org.xmlpull.v1.XmlPullParser) r2, (java.lang.StringBuilder) r1, (java.lang.String) r9)
            r0.<init>(r1)
            throw r0
        L_0x012b:
            if (r6 == 0) goto L_0x0142
            androidx.appcompat.b.a.c r9 = r5.mState
            int r6 = r9.addChild(r6)
            int[][] r10 = r9.mStateSets
            r10[r6] = r8
            a.b.o r8 = r9.mStateIds
            java.lang.Integer r9 = java.lang.Integer.valueOf(r12)
            r8.put(r6, r9)
            goto L_0x01ec
        L_0x0142:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r1 = p026b.p027a.p030b.p031a.C0632a.m1016a((org.xmlpull.v1.XmlPullParser) r2, (java.lang.StringBuilder) r1, (java.lang.String) r9)
            r0.<init>(r1)
            throw r0
        L_0x0151:
            java.lang.String r6 = r19.getName()
            java.lang.String r8 = "transition"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x01ec
            int[] r6 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableTransition
            android.content.res.TypedArray r6 = androidx.core.content.p022a.C0308a.m241a(r1, r4, r3, r6)
            int r8 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableTransition_android_fromId
            int r8 = r6.getResourceId(r8, r10)
            int r9 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableTransition_android_toId
            int r9 = r6.getResourceId(r9, r10)
            int r12 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableTransition_android_drawable
            int r12 = r6.getResourceId(r12, r10)
            if (r12 <= 0) goto L_0x0180
            androidx.appcompat.widget.ResourceManagerInternal r13 = androidx.appcompat.widget.ResourceManagerInternal.get()
            android.graphics.drawable.Drawable r12 = r13.getDrawable(r0, r12)
            goto L_0x0181
        L_0x0180:
            r12 = 0
        L_0x0181:
            int r13 = androidx.appcompat.resources.C0203R.styleable.AnimatedStateListDrawableTransition_android_reversible
            boolean r11 = r6.getBoolean(r13, r11)
            r6.recycle()
            java.lang.String r6 = ": <transition> tag requires a 'drawable' attribute or child tag defining a drawable"
            if (r12 != 0) goto L_0x01c0
        L_0x018e:
            int r12 = r19.next()
            r13 = 4
            if (r12 != r13) goto L_0x0196
            goto L_0x018e
        L_0x0196:
            r13 = 2
            if (r12 != r13) goto L_0x01b1
            java.lang.String r12 = r19.getName()
            java.lang.String r13 = "animated-vector"
            boolean r12 = r12.equals(r13)
            if (r12 == 0) goto L_0x01aa
            a.g.a.a.f r12 = p000a.p013g.p014a.p015a.C0053f.m60a(r17, r18, r19, r20, r21)
            goto L_0x01c0
        L_0x01aa:
            int r12 = android.os.Build.VERSION.SDK_INT
            android.graphics.drawable.Drawable r12 = android.graphics.drawable.Drawable.createFromXmlInner(r18, r19, r20, r21)
            goto L_0x01c0
        L_0x01b1:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r1 = p026b.p027a.p030b.p031a.C0632a.m1016a((org.xmlpull.v1.XmlPullParser) r2, (java.lang.StringBuilder) r1, (java.lang.String) r6)
            r0.<init>(r1)
            throw r0
        L_0x01c0:
            if (r12 == 0) goto L_0x01dd
            if (r8 == r10) goto L_0x01cc
            if (r9 == r10) goto L_0x01cc
            androidx.appcompat.b.a.c r6 = r5.mState
            r6.addTransition(r8, r9, r12, r11)
            goto L_0x01ec
        L_0x01cc:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = ": <transition> tag requires 'fromId' & 'toId' attributes"
            java.lang.String r1 = p026b.p027a.p030b.p031a.C0632a.m1016a((org.xmlpull.v1.XmlPullParser) r2, (java.lang.StringBuilder) r1, (java.lang.String) r3)
            r0.<init>(r1)
            throw r0
        L_0x01dd:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r1 = p026b.p027a.p030b.p031a.C0632a.m1016a((org.xmlpull.v1.XmlPullParser) r2, (java.lang.StringBuilder) r1, (java.lang.String) r6)
            r0.<init>(r1)
            throw r0
        L_0x01ec:
            r6 = 0
            r9 = 1
            goto L_0x0084
        L_0x01f0:
            int[] r0 = r5.getState()
            r5.onStateChange(r0)
            return r5
        L_0x01f8:
            org.xmlpull.v1.XmlPullParserException r0 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r19.getPositionDescription()
            r1.append(r2)
            java.lang.String r2 = ": invalid animated-selector tag "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.p020b.p021a.C0165h.m144a(android.content.Context, android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):androidx.appcompat.b.a.h");
    }

    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    public /* bridge */ /* synthetic */ boolean canApplyTheme() {
        return super.canApplyTheme();
    }

    /* access modifiers changed from: package-private */
    public void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    /* access modifiers changed from: package-private */
    public C0168k cloneConstantState() {
        return new C0160c(this.mState, this, (Resources) null);
    }

    public /* bridge */ /* synthetic */ void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public /* bridge */ /* synthetic */ int getAlpha() {
        return super.getAlpha();
    }

    public /* bridge */ /* synthetic */ int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public /* bridge */ /* synthetic */ void getHotspotBounds(Rect rect) {
        super.getHotspotBounds(rect);
    }

    public /* bridge */ /* synthetic */ int getIntrinsicHeight() {
        return super.getIntrinsicHeight();
    }

    public /* bridge */ /* synthetic */ int getIntrinsicWidth() {
        return super.getIntrinsicWidth();
    }

    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    public /* bridge */ /* synthetic */ void getOutline(Outline outline) {
        super.getOutline(outline);
    }

    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public /* bridge */ /* synthetic */ void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    public /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    public boolean isStateful() {
        return true;
    }

    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        C0164g gVar = this.mTransition;
        if (gVar != null) {
            gVar.stop();
            this.mTransition = null;
            selectDrawable(this.f147Sc);
            this.f147Sc = -1;
            this.f148Tc = -1;
        }
    }

    public Drawable mutate() {
        if (!this.mMutated) {
            super.mutate();
            if (this == this) {
                C0160c cVar = this.mState;
                cVar.mTransitions = cVar.mTransitions.clone();
                cVar.mStateIds = cVar.mStateIds.clone();
                this.mMutated = true;
            }
        }
        return this;
    }

    public /* bridge */ /* synthetic */ boolean onLayoutDirectionChanged(int i) {
        return super.onLayoutDirectionChanged(i);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0098, code lost:
        if (selectDrawable(r0) == false) goto L_0x009b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a2  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onStateChange(int[] r11) {
        /*
            r10 = this;
            androidx.appcompat.b.a.c r0 = r10.mState
            int r0 = r0.indexOfKeyframe(r11)
            int r1 = r10.getCurrentIndex()
            r2 = 1
            r3 = 0
            if (r0 == r1) goto L_0x009b
            androidx.appcompat.b.a.g r1 = r10.mTransition
            if (r1 == 0) goto L_0x0032
            int r4 = r10.f147Sc
            if (r0 != r4) goto L_0x0018
            goto L_0x008f
        L_0x0018:
            int r4 = r10.f148Tc
            if (r0 != r4) goto L_0x002c
            boolean r4 = r1.canReverse()
            if (r4 == 0) goto L_0x002c
            r1.reverse()
            int r1 = r10.f148Tc
            r10.f147Sc = r1
            r10.f148Tc = r0
            goto L_0x008f
        L_0x002c:
            int r4 = r10.f147Sc
            r1.stop()
            goto L_0x0036
        L_0x0032:
            int r4 = r10.getCurrentIndex()
        L_0x0036:
            r1 = 0
            r10.mTransition = r1
            r1 = -1
            r10.f148Tc = r1
            r10.f147Sc = r1
            androidx.appcompat.b.a.c r1 = r10.mState
            int r5 = r1.getKeyframeIdAt(r4)
            int r6 = r1.getKeyframeIdAt(r0)
            if (r6 == 0) goto L_0x0091
            if (r5 != 0) goto L_0x004d
            goto L_0x0091
        L_0x004d:
            int r7 = r1.indexOfTransition(r5, r6)
            if (r7 >= 0) goto L_0x0054
            goto L_0x0091
        L_0x0054:
            boolean r8 = r1.transitionHasReversibleFlag(r5, r6)
            r10.selectDrawable(r7)
            android.graphics.drawable.Drawable r7 = super.getCurrent()
            boolean r9 = r7 instanceof android.graphics.drawable.AnimationDrawable
            if (r9 == 0) goto L_0x006f
            boolean r1 = r1.isTransitionReversed(r5, r6)
            androidx.appcompat.b.a.e r5 = new androidx.appcompat.b.a.e
            android.graphics.drawable.AnimationDrawable r7 = (android.graphics.drawable.AnimationDrawable) r7
            r5.<init>(r7, r1, r8)
            goto L_0x0086
        L_0x006f:
            boolean r1 = r7 instanceof p000a.p013g.p014a.p015a.C0053f
            if (r1 == 0) goto L_0x007b
            androidx.appcompat.b.a.d r5 = new androidx.appcompat.b.a.d
            a.g.a.a.f r7 = (p000a.p013g.p014a.p015a.C0053f) r7
            r5.<init>(r7)
            goto L_0x0086
        L_0x007b:
            boolean r1 = r7 instanceof android.graphics.drawable.Animatable
            if (r1 == 0) goto L_0x0091
            androidx.appcompat.b.a.b r5 = new androidx.appcompat.b.a.b
            android.graphics.drawable.Animatable r7 = (android.graphics.drawable.Animatable) r7
            r5.<init>(r7)
        L_0x0086:
            r5.start()
            r10.mTransition = r5
            r10.f148Tc = r4
            r10.f147Sc = r0
        L_0x008f:
            r1 = r2
            goto L_0x0092
        L_0x0091:
            r1 = r3
        L_0x0092:
            if (r1 != 0) goto L_0x009c
            boolean r0 = r10.selectDrawable(r0)
            if (r0 == 0) goto L_0x009b
            goto L_0x009c
        L_0x009b:
            r2 = r3
        L_0x009c:
            android.graphics.drawable.Drawable r10 = super.getCurrent()
            if (r10 == 0) goto L_0x00a7
            boolean r10 = r10.setState(r11)
            r2 = r2 | r10
        L_0x00a7:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.p020b.p021a.C0165h.onStateChange(int[]):boolean");
    }

    public /* bridge */ /* synthetic */ void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        super.scheduleDrawable(drawable, runnable, j);
    }

    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    public /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }

    public /* bridge */ /* synthetic */ void setDither(boolean z) {
        super.setDither(z);
    }

    public /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public /* bridge */ /* synthetic */ void setTintList(ColorStateList colorStateList) {
        super.setTintList(colorStateList);
    }

    public /* bridge */ /* synthetic */ void setTintMode(PorterDuff.Mode mode) {
        super.setTintMode(mode);
    }

    public boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (this.mTransition != null && (visible || z2)) {
            if (z) {
                this.mTransition.start();
            } else {
                jumpToCurrentState();
            }
        }
        return visible;
    }

    public /* bridge */ /* synthetic */ void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        super.unscheduleDrawable(drawable, runnable);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cloneConstantState  reason: collision with other method in class */
    public C0172o m4697cloneConstantState() {
        return new C0160c(this.mState, this, (Resources) null);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo1085a(C0168k kVar) {
        super.mo1085a(kVar);
        if (kVar instanceof C0160c) {
            this.mState = (C0160c) kVar;
        }
    }
}
