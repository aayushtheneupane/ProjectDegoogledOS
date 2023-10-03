package com.google.android.material.internal;

import android.content.Context;
import android.support.p002v7.widget.RecyclerView;
import android.util.AttributeSet;

/* compiled from: PG */
public class NavigationMenuView extends RecyclerView implements C0488rw {
    public NavigationMenuView(Context context) {
        this(context, (AttributeSet) null);
    }

    /* renamed from: a */
    public final void mo774a(C0472rg rgVar) {
    }

    public NavigationMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutManager(new C0607wg(1));
    }
}
