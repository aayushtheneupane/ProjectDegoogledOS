package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.List;

/* renamed from: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior */
/* compiled from: PG */
public class C0073xe6e79f6 extends abj {

    /* renamed from: a */
    private Rect f5210a;

    /* renamed from: b */
    private boolean f5211b;

    /* renamed from: c */
    private boolean f5212c;

    public C0073xe6e79f6() {
        this.f5211b = false;
        this.f5212c = true;
    }

    public C0073xe6e79f6(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, gfq.f11176a);
        this.f5211b = obtainStyledAttributes.getBoolean(0, false);
        this.f5212c = obtainStyledAttributes.getBoolean(1, true);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: b */
    private final void m5089b(gfn gfn) {
        if (this.f5212c) {
            int i = gfn.f11171g;
            gfp gfp = gfn.f11173d;
        } else {
            int i2 = gfn.f11171g;
            gfp gfp2 = gfn.f11174e;
        }
        throw null;
    }

    /* renamed from: d */
    public final /* bridge */ /* synthetic */ void mo97d(View view) {
        gfn gfn = (gfn) view;
    }

    /* renamed from: e */
    private static boolean m5091e(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof abm) {
            return ((abm) layoutParams).f80a instanceof BottomSheetBehavior;
        }
        return false;
    }

    /* renamed from: a */
    public final void mo82a(abm abm) {
        if (abm.f87h == 0) {
            abm.f87h = 80;
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo92a(CoordinatorLayout coordinatorLayout, View view, View view2) {
        gfn gfn = (gfn) view;
        if (view2 instanceof AppBarLayout) {
            m5088a(coordinatorLayout, (AppBarLayout) view2, gfn);
            return false;
        } else if (!m5091e(view2)) {
            return false;
        } else {
            m5090b(view2, gfn);
            return false;
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        gfn gfn = (gfn) view;
        List a = coordinatorLayout.mo1119a((View) gfn);
        int size = a.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = (View) a.get(i2);
            if (!(view2 instanceof AppBarLayout)) {
                if (m5091e(view2) && m5090b(view2, gfn)) {
                    break;
                }
            } else if (m5088a(coordinatorLayout, (AppBarLayout) view2, gfn)) {
                break;
            }
        }
        coordinatorLayout.mo1123b(gfn, i);
        return true;
    }

    /* renamed from: a */
    private final boolean m5087a(View view, gfn gfn) {
        abm abm = (abm) gfn.getLayoutParams();
        if ((!this.f5211b && !this.f5212c) || abm.f85f != view.getId()) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private final void m5086a(gfn gfn) {
        if (this.f5212c) {
            int i = gfn.f11171g;
            gfp gfp = gfn.f11172c;
        } else {
            int i2 = gfn.f11171g;
            gfp gfp2 = gfn.f11175f;
        }
        throw null;
    }

    /* renamed from: a */
    private final boolean m5088a(CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, gfn gfn) {
        int i;
        int i2 = 0;
        if (!m5087a(appBarLayout, gfn)) {
            return false;
        }
        if (this.f5210a == null) {
            this.f5210a = new Rect();
        }
        Rect rect = this.f5210a;
        gfw.m10234a((ViewGroup) coordinatorLayout, (View) appBarLayout, rect);
        int i3 = rect.bottom;
        int e = appBarLayout.mo3585e();
        int j = C0340mj.m14719j(appBarLayout);
        if (j != 0) {
            i = j + j + e;
        } else {
            int childCount = appBarLayout.getChildCount();
            if (childCount > 0) {
                i2 = C0340mj.m14719j(appBarLayout.getChildAt(childCount - 1));
            }
            i = i2 == 0 ? appBarLayout.getHeight() / 3 : i2 + i2 + e;
        }
        if (i3 <= i) {
            m5086a(gfn);
            return true;
        }
        m5089b(gfn);
        return true;
    }

    /* renamed from: b */
    private final boolean m5090b(View view, gfn gfn) {
        if (!m5087a(view, gfn)) {
            return false;
        }
        if (view.getTop() < (gfn.getHeight() / 2) + ((abm) gfn.getLayoutParams()).topMargin) {
            m5086a(gfn);
            return true;
        }
        m5089b(gfn);
        return true;
    }
}
