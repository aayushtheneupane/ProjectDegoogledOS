package p000;

import android.support.p002v7.app.AlertController$RecycleListView;
import android.view.View;
import android.widget.AdapterView;

/* renamed from: od */
/* compiled from: PG */
final class C0388od implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    private final /* synthetic */ AlertController$RecycleListView f15345a;

    /* renamed from: b */
    private final /* synthetic */ C0392oh f15346b;

    /* renamed from: c */
    private final /* synthetic */ C0389oe f15347c;

    public C0388od(C0389oe oeVar, AlertController$RecycleListView alertController$RecycleListView, C0392oh ohVar) {
        this.f15347c = oeVar;
        this.f15345a = alertController$RecycleListView;
        this.f15346b = ohVar;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        boolean[] zArr = this.f15347c.f15368u;
        if (zArr != null) {
            zArr[i] = this.f15345a.isItemChecked(i);
        }
        this.f15347c.f15372y.onClick(this.f15346b.f15386b, i, this.f15345a.isItemChecked(i));
    }
}
