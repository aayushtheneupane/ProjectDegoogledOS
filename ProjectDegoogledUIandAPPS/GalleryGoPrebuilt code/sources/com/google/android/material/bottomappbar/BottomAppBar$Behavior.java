package com.google.android.material.bottomappbar;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior;
import java.lang.ref.WeakReference;

/* compiled from: PG */
public class BottomAppBar$Behavior extends HideBottomViewOnScrollBehavior {

    /* renamed from: b */
    public WeakReference f5156b;

    public BottomAppBar$Behavior() {
        new gdb(this);
        new Rect();
    }

    public BottomAppBar$Behavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        new gdb(this);
        new Rect();
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo88a(CoordinatorLayout coordinatorLayout, View view, int i) {
        this.f5156b = new WeakReference((gdc) view);
        throw null;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ boolean mo93a(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2) {
        gdc gdc = (gdc) view;
        throw null;
    }
}
