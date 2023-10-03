package com.google.android.material.floatingactionbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.List;

/* compiled from: PG */
public class FloatingActionButton$BaseBehavior extends abj {

    /* renamed from: a */
    private boolean f5213a;

    public FloatingActionButton$BaseBehavior() {
        this.f5213a = true;
    }

    public FloatingActionButton$BaseBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, gfq.f11177b);
        this.f5213a = obtainStyledAttributes.getBoolean(0, true);
        obtainStyledAttributes.recycle();
    }

    /* renamed from: d */
    public final /* bridge */ /* synthetic */ void mo97d(View view) {
        ((gfo) view).getLeft();
        throw null;
    }

    /* renamed from: e */
    private static boolean m5097e(View view) {
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
        gfo gfo = (gfo) view;
        if (view2 instanceof AppBarLayout) {
            m5096a((AppBarLayout) view2, gfo);
            return false;
        } else if (!m5097e(view2)) {
            return false;
        } else {
            m5096a(view2, gfo);
            return false;
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        gfo gfo = (gfo) view;
        List a = coordinatorLayout.mo1119a((View) gfo);
        int size = a.size();
        for (int i2 = 0; i2 < size; i2++) {
            View view2 = (View) a.get(i2);
            if (view2 instanceof AppBarLayout) {
                m5096a((AppBarLayout) view2, gfo);
            } else if (m5097e(view2)) {
                m5096a(view2, gfo);
            }
        }
        coordinatorLayout.mo1123b(gfo, i);
        return true;
    }

    /* renamed from: a */
    private final void m5096a(View view, gfo gfo) {
        abm abm = (abm) gfo.getLayoutParams();
        if (this.f5213a && abm.f85f == view.getId()) {
            throw null;
        }
    }
}
