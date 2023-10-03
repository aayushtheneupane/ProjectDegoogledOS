package com.google.android.material.behavior;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* compiled from: PG */
public class HideBottomViewOnScrollBehavior extends abj {

    /* renamed from: a */
    public ViewPropertyAnimator f5145a;

    /* renamed from: b */
    private int f5146b = 0;

    /* renamed from: c */
    private int f5147c = 2;

    public HideBottomViewOnScrollBehavior() {
    }

    /* renamed from: a */
    public boolean mo93a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2) {
        return i == 2;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: a */
    private final void m5031a(View view, int i, long j, TimeInterpolator timeInterpolator) {
        this.f5145a = view.animate().translationY((float) i).setInterpolator(timeInterpolator).setDuration(j).setListener(new gcw(this));
    }

    /* renamed from: a */
    public boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        this.f5146b = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        return false;
    }

    /* renamed from: a */
    public final void mo84a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3, int[] iArr) {
        if (i > 0) {
            if (this.f5147c != 1) {
                ViewPropertyAnimator viewPropertyAnimator = this.f5145a;
                if (viewPropertyAnimator != null) {
                    viewPropertyAnimator.cancel();
                    view.clearAnimation();
                }
                this.f5147c = 1;
                m5031a(view, this.f5146b, 175, gci.f10938c);
            }
        } else if (i < 0 && this.f5147c != 2) {
            ViewPropertyAnimator viewPropertyAnimator2 = this.f5145a;
            if (viewPropertyAnimator2 != null) {
                viewPropertyAnimator2.cancel();
                view.clearAnimation();
            }
            this.f5147c = 2;
            m5031a(view, 0, 225, gci.f10939d);
        }
    }
}
