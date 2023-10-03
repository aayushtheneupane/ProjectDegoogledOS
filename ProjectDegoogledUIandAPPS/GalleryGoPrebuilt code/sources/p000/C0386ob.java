package p000;

import android.content.Context;
import android.support.p002v7.app.AlertController$RecycleListView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/* renamed from: ob */
/* compiled from: PG */
final class C0386ob extends ArrayAdapter {

    /* renamed from: a */
    private final /* synthetic */ AlertController$RecycleListView f15341a;

    /* renamed from: b */
    private final /* synthetic */ C0389oe f15342b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0386ob(C0389oe oeVar, Context context, int i, CharSequence[] charSequenceArr, AlertController$RecycleListView alertController$RecycleListView) {
        super(context, i, 16908308, charSequenceArr);
        this.f15342b = oeVar;
        this.f15341a = alertController$RecycleListView;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        boolean[] zArr = this.f15342b.f15368u;
        if (zArr != null && zArr[i]) {
            this.f15341a.setItemChecked(i, true);
        }
        return view2;
    }
}
