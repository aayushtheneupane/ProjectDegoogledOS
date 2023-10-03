package android.support.p002v7.view.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/* renamed from: android.support.v7.view.menu.ExpandedMenuView */
/* compiled from: PG */
public final class ExpandedMenuView extends ListView implements AdapterView.OnItemClickListener, C0471rf, C0488rw {

    /* renamed from: a */
    private static final int[] f868a = {16842964, 16843049};

    /* renamed from: b */
    private C0472rg f869b;

    public ExpandedMenuView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842868);
    }

    public ExpandedMenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        setOnItemClickListener(this);
        C0684zc a = C0684zc.m16192a(context, attributeSet, f868a, i, 0);
        if (a.mo10735f(0)) {
            setBackgroundDrawable(a.mo10723a(0));
        }
        if (a.mo10735f(1)) {
            setDivider(a.mo10723a(1));
        }
        a.mo10724a();
    }

    /* renamed from: a */
    public final void mo774a(C0472rg rgVar) {
        this.f869b = rgVar;
    }

    /* renamed from: a */
    public final boolean mo775a(C0475rj rjVar) {
        return this.f869b.mo9836a((MenuItem) rjVar, 0);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChildrenDrawingCacheEnabled(false);
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        mo775a((C0475rj) getAdapter().getItem(i));
    }
}
