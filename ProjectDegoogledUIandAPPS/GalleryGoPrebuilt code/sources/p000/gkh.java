package p000;

import android.view.View;
import android.widget.AdapterView;

/* renamed from: gkh */
/* compiled from: PG */
final class gkh implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    private final /* synthetic */ gki f11540a;

    public gkh(gki gki) {
        this.f11540a = gki;
    }

    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        Object obj;
        View view2 = null;
        if (i < 0) {
            C0616wp wpVar = this.f11540a.f11541a;
            obj = wpVar.mo9811e() ? wpVar.f16247e.getSelectedItem() : null;
        } else {
            obj = this.f11540a.getAdapter().getItem(i);
        }
        this.f11540a.mo6800a(obj);
        AdapterView.OnItemClickListener onItemClickListener = this.f11540a.getOnItemClickListener();
        if (onItemClickListener != null) {
            if (view == null || i < 0) {
                C0616wp wpVar2 = this.f11540a.f11541a;
                if (wpVar2.mo9811e()) {
                    view2 = wpVar2.f16247e.getSelectedView();
                }
                view = view2;
                C0616wp wpVar3 = this.f11540a.f11541a;
                i = wpVar3.mo9811e() ? wpVar3.f16247e.getSelectedItemPosition() : -1;
                C0616wp wpVar4 = this.f11540a.f11541a;
                if (wpVar4.mo9811e()) {
                    j = wpVar4.f16247e.getSelectedItemId();
                } else {
                    j = Long.MIN_VALUE;
                }
            }
            onItemClickListener.onItemClick(this.f11540a.f11541a.f16247e, view, i, j);
        }
        this.f11540a.f11541a.mo9810d();
    }
}
