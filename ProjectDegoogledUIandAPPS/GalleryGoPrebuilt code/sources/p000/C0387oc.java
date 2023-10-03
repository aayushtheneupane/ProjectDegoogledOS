package p000;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: oc */
/* compiled from: PG */
final class C0387oc implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    private final /* synthetic */ C0392oh f15343a;

    /* renamed from: b */
    private final /* synthetic */ C0389oe f15344b;

    public C0387oc(C0389oe oeVar, C0392oh ohVar) {
        this.f15344b = oeVar;
        this.f15343a = ohVar;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.f15344b.f15364q.onClick(this.f15343a.f15386b, i);
        if (!this.f15344b.f15370w) {
            this.f15343a.f15386b.dismiss();
        }
    }
}
