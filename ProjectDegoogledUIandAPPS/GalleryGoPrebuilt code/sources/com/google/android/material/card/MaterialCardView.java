package com.google.android.material.card;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import com.google.android.apps.photosgo.R;

/* compiled from: PG */
public class MaterialCardView extends aak implements Checkable, ghm {

    /* renamed from: g */
    private static final int[] f5205g = {16842911};

    /* renamed from: h */
    private static final int[] f5206h = {16842912};

    /* renamed from: i */
    private final gdu f5207i;

    /* renamed from: j */
    private boolean f5208j;

    /* renamed from: k */
    private boolean f5209k;

    /* renamed from: b */
    private final boolean m5083b() {
        gdu gdu = this.f5207i;
        return gdu != null && gdu.f11072r;
    }

    public final boolean isChecked() {
        return this.f5209k;
    }

    public MaterialCardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(gkl.m10444a(context, attributeSet, i, 2131952562), attributeSet, i);
        Drawable drawable;
        this.f5209k = false;
        this.f5208j = true;
        TypedArray a = gga.m10238a(getContext(), attributeSet, gdv.f11076b, i, 2131952562, new int[0]);
        gdu gdu = new gdu(this, attributeSet, i);
        this.f5207i = gdu;
        gdu.f11058d.mo6635a(((aao) ((aaj) this.f28f).f20a).f33e);
        gdu gdu2 = this.f5207i;
        gdu2.f11057c.set(this.f26d.left, this.f26d.top, this.f26d.right, this.f26d.bottom);
        float f = 0.0f;
        float c = ((!gdu2.f11056b.f25c || gdu2.mo6488a()) && !gdu2.mo6489b()) ? 0.0f : gdu2.mo6490c();
        if (gdu2.f11056b.f25c) {
            int i2 = Build.VERSION.SDK_INT;
            if (gdu2.f11056b.f24b) {
                double d = 1.0d - gdu.f11054a;
                double b = (double) aak.f22a.mo23b(gdu2.f11056b.f28f);
                Double.isNaN(b);
                f = (float) (d * b);
            }
        }
        int i3 = (int) (c - f);
        MaterialCardView materialCardView = gdu2.f11056b;
        materialCardView.f26d.set(gdu2.f11057c.left + i3, gdu2.f11057c.top + i3, gdu2.f11057c.right + i3, gdu2.f11057c.bottom + i3);
        aak.f22a.mo24c(materialCardView.f28f);
        gdu gdu3 = this.f5207i;
        gdu3.f11067m = gqb.m10615a(gdu3.f11056b.getContext(), a, 8);
        if (gdu3.f11067m == null) {
            gdu3.f11067m = ColorStateList.valueOf(-1);
        }
        gdu3.f11062h = a.getDimensionPixelSize(9, 0);
        boolean z = a.getBoolean(0, false);
        gdu3.f11072r = z;
        gdu3.f11056b.setLongClickable(z);
        gdu3.f11066l = gqb.m10615a(gdu3.f11056b.getContext(), a, 3);
        Drawable b2 = gqb.m10622b(gdu3.f11056b.getContext(), a, 2);
        gdu3.f11064j = b2;
        if (b2 != null) {
            Drawable mutate = b2.mutate();
            int i4 = Build.VERSION.SDK_INT;
            gdu3.f11064j = mutate;
            C0257jh.m14475a(gdu3.f11064j, gdu3.f11066l);
        }
        if (gdu3.f11069o != null) {
            gdu3.f11069o.setDrawableByLayerId(R.id.mtrl_card_checked_layer_id, gdu3.mo6492e());
        }
        gdu3.f11065k = gqb.m10615a(gdu3.f11056b.getContext(), a, 4);
        if (gdu3.f11065k == null) {
            gdu3.f11065k = ColorStateList.valueOf(ggf.m10246a((View) gdu3.f11056b, (int) R.attr.colorControlHighlight));
        }
        ColorStateList a2 = gqb.m10615a(gdu3.f11056b.getContext(), a, 1);
        gdu3.f11059e.mo6635a(a2 == null ? ColorStateList.valueOf(0) : a2);
        if (!ggk.f11254a || (drawable = gdu3.f11068n) == null) {
            ggu ggu = gdu3.f11070p;
            if (ggu != null) {
                ggu.mo6635a(gdu3.f11065k);
            }
        } else {
            ((RippleDrawable) drawable).setColor(gdu3.f11065k);
        }
        gdu3.f11058d.mo6637b(((aaj) gdu3.f11056b.f28f).f21b.getElevation());
        gdu3.f11059e.mo6633a((float) gdu3.f11062h, gdu3.f11067m);
        super.setBackgroundDrawable(gdu3.mo6486a((Drawable) gdu3.f11058d));
        gdu3.f11063i = gdu3.f11056b.isClickable() ? gdu3.mo6491d() : gdu3.f11059e;
        gdu3.f11056b.setForeground(gdu3.mo6486a(gdu3.f11063i));
        a.recycle();
    }

    /* access modifiers changed from: protected */
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ggq.m10281a(this, this.f5207i.f11058d);
    }

    /* access modifiers changed from: protected */
    public final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (m5083b()) {
            mergeDrawableStates(onCreateDrawableState, f5205g);
        }
        if (this.f5209k) {
            mergeDrawableStates(onCreateDrawableState, f5206h);
        }
        return onCreateDrawableState;
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(this.f5209k);
    }

    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        accessibilityNodeInfo.setCheckable(m5083b());
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(this.f5209k);
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        gdu gdu = this.f5207i;
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        if (gdu.f11069o != null) {
            int i3 = gdu.f11060f;
            int i4 = gdu.f11061g;
            int i5 = (measuredWidth - i3) - i4;
            int i6 = (measuredHeight - i3) - i4;
            int f = C0340mj.m14714f(gdu.f11056b);
            gdu.f11069o.setLayerInset(2, f == 1 ? i3 : i5, gdu.f11060f, f != 1 ? i3 : i5, i6);
        }
    }

    public final void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    public final void setBackgroundDrawable(Drawable drawable) {
        if (this.f5208j) {
            gdu gdu = this.f5207i;
            if (!gdu.f11071q) {
                gdu.f11071q = true;
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public final void setChecked(boolean z) {
        if (this.f5209k != z) {
            toggle();
        }
    }

    public final void setClickable(boolean z) {
        super.setClickable(z);
        gdu gdu = this.f5207i;
        Drawable drawable = gdu.f11063i;
        gdu.f11063i = gdu.f11056b.isClickable() ? gdu.mo6491d() : gdu.f11059e;
        Drawable drawable2 = gdu.f11063i;
        if (drawable != drawable2) {
            int i = Build.VERSION.SDK_INT;
            if (gdu.f11056b.getForeground() instanceof InsetDrawable) {
                ((InsetDrawable) gdu.f11056b.getForeground()).setDrawable(drawable2);
            } else {
                gdu.f11056b.setForeground(gdu.mo6486a(drawable2));
            }
        }
    }

    /* renamed from: a */
    public final void mo3619a(gha gha) {
        this.f5207i.mo6487a(gha);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001b, code lost:
        r0 = r8.f5207i;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void toggle() {
        /*
            r8 = this;
            boolean r0 = r8.m5083b()
            if (r0 == 0) goto L_0x0040
            boolean r0 = r8.isEnabled()
            if (r0 == 0) goto L_0x0040
            boolean r0 = r8.f5209k
            r0 = r0 ^ 1
            r8.f5209k = r0
            r8.refreshDrawableState()
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 26
            if (r0 <= r1) goto L_0x0040
            gdu r0 = r8.f5207i
            android.graphics.drawable.Drawable r1 = r0.f11068n
            if (r1 != 0) goto L_0x0022
            goto L_0x0040
        L_0x0022:
            android.graphics.Rect r1 = r1.getBounds()
            int r2 = r1.bottom
            android.graphics.drawable.Drawable r3 = r0.f11068n
            int r4 = r1.left
            int r5 = r1.top
            int r6 = r1.right
            int r7 = r2 + -1
            r3.setBounds(r4, r5, r6, r7)
            android.graphics.drawable.Drawable r0 = r0.f11068n
            int r3 = r1.left
            int r4 = r1.top
            int r1 = r1.right
            r0.setBounds(r3, r4, r1, r2)
        L_0x0040:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.card.MaterialCardView.toggle():void");
    }
}
