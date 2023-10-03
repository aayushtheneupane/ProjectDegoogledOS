package com.google.android.apps.photosgo.editor;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

/* compiled from: PG */
public final class ImageContainerBehavior extends abj {

    /* renamed from: a */
    public hso f4824a = hso.m12047f();

    /* renamed from: b */
    public hso f4825b = hso.m12047f();

    /* renamed from: c */
    public boolean f4826c = false;

    /* renamed from: d */
    public bwo f4827d;

    /* renamed from: e */
    private final Rect f4828e = new Rect();

    /* renamed from: f */
    private int f4829f;

    /* renamed from: g */
    private int f4830g;

    /* renamed from: h */
    private boolean f4831h = false;

    public ImageContainerBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* renamed from: c */
    private final int m4763c() {
        return this.f4826c ? 16 : 0;
    }

    /* renamed from: a */
    private static int m4762a(CoordinatorLayout coordinatorLayout, hso hso) {
        hvs i = hso.listIterator();
        int i2 = 0;
        while (i.hasNext()) {
            View findViewById = coordinatorLayout.findViewById(((Integer) i.next()).intValue());
            if (findViewById != null) {
                i2 += findViewById.getHeight();
            } else {
                throw null;
            }
        }
        return i2;
    }

    /* renamed from: a */
    public final boolean mo87a(View view) {
        int id = view.getId();
        hso hso = this.f4824a;
        Integer valueOf = Integer.valueOf(id);
        return hso.contains(valueOf) || this.f4825b.contains(valueOf);
    }

    /* renamed from: b */
    public final void mo3344b() {
        bwo bwo = this.f4827d;
        if (bwo != null) {
            int i = 16;
            int i2 = this.f4828e.left + (!this.f4826c ? 0 : 16);
            int c = this.f4828e.top + m4763c() + (!this.f4831h ? this.f4830g : 0);
            int i3 = this.f4828e.right;
            if (!this.f4826c) {
                i = 0;
            }
            int i4 = this.f4828e.bottom;
            int c2 = m4763c();
            int i5 = this.f4829f;
            bvv bvv = ((buy) bwo).f3651a;
            Rect rect = new Rect(i2, c, i3 + i, i4 + c2 + i5);
            if (!rect.equals(bvv.f3694G)) {
                bvv.f3694G = rect;
                bvv.mo2811d();
            }
        }
    }

    /* renamed from: a */
    public final boolean mo92a(CoordinatorLayout coordinatorLayout, View view, View view2) {
        this.f4829f = m4762a(coordinatorLayout, this.f4824a);
        this.f4830g = m4762a(coordinatorLayout, this.f4825b);
        mo3344b();
        return false;
    }

    /* renamed from: a */
    public final boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        view.layout(this.f4828e.left, this.f4828e.top, coordinatorLayout.getWidth() - this.f4828e.right, coordinatorLayout.getHeight() - this.f4828e.bottom);
        Rect rect = this.f4828e;
        int i2 = 0;
        int i3 = rect.left + (!this.f4826c ? 0 : 16);
        int i4 = this.f4828e.top;
        int i5 = this.f4828e.right;
        if (this.f4826c) {
            i2 = 16;
        }
        rect.set(i3, i4, i5 + i2, this.f4828e.bottom);
        this.f4829f = m4762a(coordinatorLayout, this.f4824a);
        this.f4830g = m4762a(coordinatorLayout, this.f4825b);
        mo3344b();
        return true;
    }

    /* renamed from: a */
    public final boolean mo89a(CoordinatorLayout coordinatorLayout, View view, int i, int i2, int i3) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        this.f4828e.set(marginLayoutParams.leftMargin, marginLayoutParams.topMargin, marginLayoutParams.rightMargin, marginLayoutParams.bottomMargin);
        view.measure(View.MeasureSpec.makeMeasureSpec(coordinatorLayout.getWidth() - this.f4828e.width(), 1073741824), View.MeasureSpec.makeMeasureSpec(coordinatorLayout.getHeight() - this.f4828e.height(), 1073741824));
        return true;
    }

    /* renamed from: c */
    public final void mo3345c(boolean z) {
        this.f4831h = z;
        mo3344b();
    }
}
