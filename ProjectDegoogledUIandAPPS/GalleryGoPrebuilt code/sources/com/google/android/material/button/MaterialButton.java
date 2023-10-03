package com.google.android.material.button;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.google.android.apps.photosgo.R;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: PG */
public class MaterialButton extends C0524te implements Checkable, ghm {

    /* renamed from: c */
    private static final int[] f5192c = {16842911};

    /* renamed from: d */
    private static final int[] f5193d = {16842912};

    /* renamed from: e */
    private final gdr f5194e;

    /* renamed from: f */
    private final LinkedHashSet f5195f;

    /* renamed from: g */
    private PorterDuff.Mode f5196g;

    /* renamed from: h */
    private ColorStateList f5197h;

    /* renamed from: i */
    private Drawable f5198i;

    /* renamed from: j */
    private int f5199j;

    /* renamed from: k */
    private int f5200k;

    /* renamed from: l */
    private int f5201l;

    /* renamed from: m */
    private boolean f5202m;

    /* renamed from: n */
    private boolean f5203n;

    /* renamed from: o */
    private int f5204o;

    /* renamed from: c */
    private final boolean m5079c() {
        gdr gdr = this.f5194e;
        return gdr != null && gdr.f11051o;
    }

    /* renamed from: d */
    private final boolean m5080d() {
        gdr gdr = this.f5194e;
        return gdr != null && !gdr.f11050n;
    }

    public final boolean isChecked() {
        return this.f5202m;
    }

    public MaterialButton(Context context) {
        this(context, (AttributeSet) null);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialButtonStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MaterialButton(android.content.Context r22, android.util.AttributeSet r23, int r24) {
        /*
            r21 = this;
            r0 = r21
            r7 = r23
            r8 = r24
            r9 = 2131952550(0x7f1303a6, float:1.9541546E38)
            r1 = r22
            android.content.Context r1 = p000.gkl.m10444a(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            java.util.LinkedHashSet r1 = new java.util.LinkedHashSet
            r1.<init>()
            r0.f5195f = r1
            r10 = 0
            r0.f5202m = r10
            r0.f5203n = r10
            android.content.Context r11 = r21.getContext()
            int[] r3 = p000.gds.f11053a
            int[] r6 = new int[r10]
            r5 = 2131952550(0x7f1303a6, float:1.9541546E38)
            r1 = r11
            r2 = r23
            r4 = r24
            android.content.res.TypedArray r1 = p000.gga.m10238a(r1, r2, r3, r4, r5, r6)
            r2 = 11
            int r2 = r1.getDimensionPixelSize(r2, r10)
            r0.f5201l = r2
            r2 = 14
            r3 = -1
            int r2 = r1.getInt(r2, r3)
            android.graphics.PorterDuff$Mode r4 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r2 = p000.ggf.m10248a((int) r2, (android.graphics.PorterDuff.Mode) r4)
            r0.f5196g = r2
            android.content.Context r2 = r21.getContext()
            r4 = 13
            android.content.res.ColorStateList r2 = p000.gqb.m10615a((android.content.Context) r2, (android.content.res.TypedArray) r1, (int) r4)
            r0.f5197h = r2
            android.content.Context r2 = r21.getContext()
            r4 = 9
            android.graphics.drawable.Drawable r2 = p000.gqb.m10622b(r2, r1, r4)
            r0.f5198i = r2
            r2 = 10
            r4 = 1
            int r2 = r1.getInteger(r2, r4)
            r0.f5204o = r2
            r2 = 12
            int r2 = r1.getDimensionPixelSize(r2, r10)
            r0.f5199j = r2
            ggy r2 = p000.gha.m10331a((android.content.Context) r11, (android.util.AttributeSet) r7, (int) r8, (int) r9)
            gha r2 = r2.mo6660a()
            gdr r5 = new gdr
            r5.<init>(r0, r2)
            r0.f5194e = r5
            int r2 = r1.getDimensionPixelOffset(r10, r10)
            r5.f11039c = r2
            int r2 = r1.getDimensionPixelOffset(r4, r10)
            r5.f11040d = r2
            r2 = 2
            int r6 = r1.getDimensionPixelOffset(r2, r10)
            r5.f11041e = r6
            r6 = 3
            int r6 = r1.getDimensionPixelOffset(r6, r10)
            r5.f11042f = r6
            r6 = 7
            boolean r7 = r1.hasValue(r6)
            if (r7 == 0) goto L_0x00b2
            int r6 = r1.getDimensionPixelSize(r6, r3)
            r5.f11043g = r6
            gha r7 = r5.f11038b
            float r6 = (float) r6
            gha r6 = r7.mo6670a((float) r6)
            r5.mo6482a((p000.gha) r6)
        L_0x00b2:
            r6 = 19
            int r6 = r1.getDimensionPixelSize(r6, r10)
            r5.f11044h = r6
            r6 = 6
            int r6 = r1.getInt(r6, r3)
            android.graphics.PorterDuff$Mode r7 = android.graphics.PorterDuff.Mode.SRC_IN
            android.graphics.PorterDuff$Mode r6 = p000.ggf.m10248a((int) r6, (android.graphics.PorterDuff.Mode) r7)
            r5.f11045i = r6
            com.google.android.material.button.MaterialButton r6 = r5.f11037a
            android.content.Context r6 = r6.getContext()
            r7 = 5
            android.content.res.ColorStateList r6 = p000.gqb.m10615a((android.content.Context) r6, (android.content.res.TypedArray) r1, (int) r7)
            r5.f11046j = r6
            com.google.android.material.button.MaterialButton r6 = r5.f11037a
            android.content.Context r6 = r6.getContext()
            r7 = 18
            android.content.res.ColorStateList r6 = p000.gqb.m10615a((android.content.Context) r6, (android.content.res.TypedArray) r1, (int) r7)
            r5.f11047k = r6
            com.google.android.material.button.MaterialButton r6 = r5.f11037a
            android.content.Context r6 = r6.getContext()
            r7 = 15
            android.content.res.ColorStateList r6 = p000.gqb.m10615a((android.content.Context) r6, (android.content.res.TypedArray) r1, (int) r7)
            r5.f11048l = r6
            r6 = 4
            boolean r6 = r1.getBoolean(r6, r10)
            r5.f11051o = r6
            r6 = 8
            int r6 = r1.getDimensionPixelSize(r6, r10)
            com.google.android.material.button.MaterialButton r7 = r5.f11037a
            int r7 = p000.C0340mj.m14716g(r7)
            com.google.android.material.button.MaterialButton r8 = r5.f11037a
            int r8 = r8.getPaddingTop()
            com.google.android.material.button.MaterialButton r9 = r5.f11037a
            int r9 = p000.C0340mj.m14717h(r9)
            com.google.android.material.button.MaterialButton r11 = r5.f11037a
            int r11 = r11.getPaddingBottom()
            com.google.android.material.button.MaterialButton r12 = r5.f11037a
            ggu r13 = new ggu
            gha r14 = r5.f11038b
            r13.<init>((p000.gha) r14)
            com.google.android.material.button.MaterialButton r14 = r5.f11037a
            android.content.Context r14 = r14.getContext()
            r13.mo6634a((android.content.Context) r14)
            android.content.res.ColorStateList r14 = r5.f11046j
            p000.C0257jh.m14475a((android.graphics.drawable.Drawable) r13, (android.content.res.ColorStateList) r14)
            android.graphics.PorterDuff$Mode r14 = r5.f11045i
            if (r14 != 0) goto L_0x0131
            goto L_0x0134
        L_0x0131:
            p000.C0257jh.m14476a((android.graphics.drawable.Drawable) r13, (android.graphics.PorterDuff.Mode) r14)
        L_0x0134:
            int r14 = r5.f11044h
            float r14 = (float) r14
            android.content.res.ColorStateList r15 = r5.f11047k
            r13.mo6633a((float) r14, (android.content.res.ColorStateList) r15)
            ggu r14 = new ggu
            gha r15 = r5.f11038b
            r14.<init>((p000.gha) r15)
            r14.setTint(r10)
            int r15 = r5.f11044h
            float r15 = (float) r15
            r14.mo6632a((float) r15, (int) r10)
            ggu r15 = new ggu
            gha r4 = r5.f11038b
            r15.<init>((p000.gha) r4)
            r5.f11049m = r15
            android.graphics.drawable.Drawable r4 = r5.f11049m
            p000.C0257jh.m14473a((android.graphics.drawable.Drawable) r4, (int) r3)
            android.graphics.drawable.RippleDrawable r3 = new android.graphics.drawable.RippleDrawable
            android.content.res.ColorStateList r4 = r5.f11048l
            android.content.res.ColorStateList r4 = p000.ggk.m10271a(r4)
            android.graphics.drawable.LayerDrawable r15 = new android.graphics.drawable.LayerDrawable
            android.graphics.drawable.Drawable[] r2 = new android.graphics.drawable.Drawable[r2]
            r2[r10] = r14
            r14 = 1
            r2[r14] = r13
            r15.<init>(r2)
            android.graphics.drawable.InsetDrawable r2 = new android.graphics.drawable.InsetDrawable
            int r13 = r5.f11039c
            int r10 = r5.f11041e
            int r14 = r5.f11040d
            int r0 = r5.f11042f
            r16 = r15
            r15 = r2
            r17 = r13
            r18 = r10
            r19 = r14
            r20 = r0
            r15.<init>(r16, r17, r18, r19, r20)
            android.graphics.drawable.Drawable r0 = r5.f11049m
            r3.<init>(r4, r2, r0)
            r5.f11052p = r3
            android.graphics.drawable.LayerDrawable r0 = r5.f11052p
            super.setBackgroundDrawable(r0)
            ggu r0 = r5.mo6481a()
            if (r0 != 0) goto L_0x0199
            goto L_0x019d
        L_0x0199:
            float r2 = (float) r6
            r0.mo6637b((float) r2)
        L_0x019d:
            com.google.android.material.button.MaterialButton r0 = r5.f11037a
            int r2 = r5.f11039c
            int r7 = r7 + r2
            int r2 = r5.f11041e
            int r8 = r8 + r2
            int r2 = r5.f11040d
            int r9 = r9 + r2
            int r2 = r5.f11042f
            int r11 = r11 + r2
            p000.C0340mj.m14690a(r0, r7, r8, r9, r11)
            r1.recycle()
            r0 = r21
            int r1 = r0.f5201l
            r0.setCompoundDrawablePadding(r1)
            android.graphics.drawable.Drawable r1 = r0.f5198i
            if (r1 == 0) goto L_0x01be
            r10 = 1
            goto L_0x01c0
        L_0x01be:
            r10 = 0
        L_0x01c0:
            r0.m5076a((boolean) r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.button.MaterialButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    /* renamed from: a */
    private final String mo80a() {
        return (m5079c() ? CompoundButton.class : Button.class).getName();
    }

    public final ColorStateList getBackgroundTintList() {
        C0682za zaVar;
        if (m5080d()) {
            return this.f5194e.f11046j;
        }
        C0523td tdVar = this.f15919b;
        if (tdVar == null || (zaVar = tdVar.f15913a) == null) {
            return null;
        }
        return zaVar.f16431a;
    }

    public final PorterDuff.Mode getBackgroundTintMode() {
        C0682za zaVar;
        if (m5080d()) {
            return this.f5194e.f11045i;
        }
        C0523td tdVar = this.f15919b;
        if (tdVar == null || (zaVar = tdVar.f15913a) == null) {
            return null;
        }
        return zaVar.f16432b;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ggq.m10281a(this, this.f5194e.mo6481a());
    }

    /* access modifiers changed from: protected */
    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (m5079c()) {
            mergeDrawableStates(onCreateDrawableState, f5192c);
        }
        if (this.f5202m) {
            mergeDrawableStates(onCreateDrawableState, f5193d);
        }
        return onCreateDrawableState;
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(mo80a());
        accessibilityEvent.setChecked(this.f5202m);
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(mo80a());
        accessibilityNodeInfo.setCheckable(m5079c());
        accessibilityNodeInfo.setChecked(this.f5202m);
        accessibilityNodeInfo.setClickable(isClickable());
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = Build.VERSION.SDK_INT;
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        m5077b();
    }

    /* access modifiers changed from: protected */
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        m5077b();
    }

    public final boolean performClick() {
        toggle();
        return super.performClick();
    }

    /* renamed from: b */
    private final void m5078b(boolean z) {
        if (z) {
            dcm.m5902a(this, this.f5198i, (Drawable) null, (Drawable) null, (Drawable) null);
        } else {
            dcm.m5902a(this, (Drawable) null, (Drawable) null, this.f5198i, (Drawable) null);
        }
    }

    public final void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public final void setBackgroundColor(int i) {
        if (m5080d()) {
            gdr gdr = this.f5194e;
            if (gdr.mo6481a() != null) {
                gdr.mo6481a().setTint(i);
                return;
            }
            return;
        }
        super.setBackgroundColor(i);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        if (!m5080d()) {
            super.setBackgroundDrawable(drawable);
        } else if (drawable != getBackground()) {
            Log.w("MaterialButton", "Do not set the background; MaterialButton manages its own background drawable.");
            gdr gdr = this.f5194e;
            gdr.f11050n = true;
            gdr.f11037a.m5074a(gdr.f11046j);
            gdr.f11037a.m5075a(gdr.f11045i);
            super.setBackgroundDrawable(drawable);
        } else {
            getBackground().setState(drawable.getState());
        }
    }

    public final void setBackgroundResource(int i) {
        Drawable drawable;
        if (i != 0) {
            drawable = C0436py.m15105b(getContext(), i);
        } else {
            drawable = null;
        }
        setBackgroundDrawable(drawable);
    }

    public final void setBackgroundTintList(ColorStateList colorStateList) {
        m5074a(colorStateList);
    }

    public final void setBackgroundTintMode(PorterDuff.Mode mode) {
        m5075a(mode);
    }

    public final void setChecked(boolean z) {
        if (m5079c() && isEnabled() && this.f5202m != z) {
            this.f5202m = z;
            refreshDrawableState();
            if (!this.f5203n) {
                this.f5203n = true;
                Iterator it = this.f5195f.iterator();
                while (it.hasNext()) {
                    ((gdq) it.next()).mo6480a();
                }
                this.f5203n = false;
            }
        }
    }

    public final void setElevation(float f) {
        super.setElevation(f);
        if (m5080d()) {
            this.f5194e.mo6481a().mo6637b(f);
        }
    }

    /* renamed from: a */
    public final void mo3618a(Drawable drawable) {
        if (this.f5198i != drawable) {
            this.f5198i = drawable;
            m5076a(true);
        }
    }

    /* renamed from: a */
    public final void mo3619a(gha gha) {
        if (m5080d()) {
            this.f5194e.mo6482a(gha);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    /* renamed from: a */
    private final void m5074a(ColorStateList colorStateList) {
        if (m5080d()) {
            gdr gdr = this.f5194e;
            if (gdr.f11046j != colorStateList) {
                gdr.f11046j = colorStateList;
                if (gdr.mo6481a() != null) {
                    C0257jh.m14475a((Drawable) gdr.mo6481a(), gdr.f11046j);
                    return;
                }
                return;
            }
            return;
        }
        C0523td tdVar = this.f15919b;
        if (tdVar != null) {
            if (tdVar.f15913a == null) {
                tdVar.f15913a = new C0682za();
            }
            C0682za zaVar = tdVar.f15913a;
            zaVar.f16431a = colorStateList;
            zaVar.f16434d = true;
            tdVar.mo10102a();
        }
    }

    /* renamed from: a */
    private final void m5075a(PorterDuff.Mode mode) {
        if (m5080d()) {
            gdr gdr = this.f5194e;
            if (gdr.f11045i != mode) {
                gdr.f11045i = mode;
                if (gdr.mo6481a() != null && gdr.f11045i != null) {
                    C0257jh.m14476a((Drawable) gdr.mo6481a(), gdr.f11045i);
                    return;
                }
                return;
            }
            return;
        }
        C0523td tdVar = this.f15919b;
        if (tdVar != null) {
            if (tdVar.f15913a == null) {
                tdVar.f15913a = new C0682za();
            }
            C0682za zaVar = tdVar.f15913a;
            zaVar.f16432b = mode;
            zaVar.f16433c = true;
            tdVar.mo10102a();
        }
    }

    public final void toggle() {
        setChecked(!this.f5202m);
    }

    /* renamed from: a */
    private final void m5076a(boolean z) {
        Drawable drawable = this.f5198i;
        if (drawable != null) {
            int i = Build.VERSION.SDK_INT;
            Drawable mutate = drawable.mutate();
            this.f5198i = mutate;
            C0257jh.m14475a(mutate, this.f5197h);
            PorterDuff.Mode mode = this.f5196g;
            if (mode != null) {
                C0257jh.m14476a(this.f5198i, mode);
            }
            int i2 = this.f5199j;
            if (i2 == 0) {
                i2 = this.f5198i.getIntrinsicWidth();
            }
            int i3 = this.f5199j;
            if (i3 == 0) {
                i3 = this.f5198i.getIntrinsicHeight();
            }
            Drawable drawable2 = this.f5198i;
            int i4 = this.f5200k;
            drawable2.setBounds(i4, 0, i2 + i4, i3);
        }
        int i5 = this.f5204o;
        boolean z2 = true;
        if (!(i5 == 1 || i5 == 2)) {
            z2 = false;
        }
        if (!z) {
            Drawable[] a = dcm.m5903a((TextView) this);
            Drawable drawable3 = a[0];
            Drawable drawable4 = a[2];
            if ((z2 && drawable3 != this.f5198i) || (!z2 && drawable4 != this.f5198i)) {
                m5078b(z2);
                return;
            }
            return;
        }
        m5078b(z2);
    }

    /* renamed from: b */
    private final void m5077b() {
        if (this.f5198i != null && getLayout() != null) {
            int i = this.f5204o;
            boolean z = true;
            if (i == 1 || i == 3) {
                this.f5200k = 0;
                m5076a(false);
                return;
            }
            TextPaint paint = getPaint();
            String charSequence = getText().toString();
            if (getTransformationMethod() != null) {
                charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
            }
            int min = Math.min((int) paint.measureText(charSequence), getLayout().getEllipsizedWidth());
            int i2 = this.f5199j;
            if (i2 == 0) {
                i2 = this.f5198i.getIntrinsicWidth();
            }
            int measuredWidth = (((((getMeasuredWidth() - min) - C0340mj.m14717h(this)) - i2) - this.f5201l) - C0340mj.m14716g(this)) / 2;
            boolean z2 = C0340mj.m14714f(this) == 1;
            if (this.f5204o != 4) {
                z = false;
            }
            if (z2 != z) {
                measuredWidth = -measuredWidth;
            }
            if (this.f5200k != measuredWidth) {
                this.f5200k = measuredWidth;
                m5076a(false);
            }
        }
    }
}
