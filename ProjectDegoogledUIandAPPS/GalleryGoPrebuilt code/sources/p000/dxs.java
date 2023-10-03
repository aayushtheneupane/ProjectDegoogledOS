package p000;

import android.view.View;

/* renamed from: dxs */
/* compiled from: PG */
final /* synthetic */ class dxs implements View.OnClickListener {

    /* renamed from: a */
    private final dxu f7581a;

    public dxs(dxu dxu) {
        this.f7581a = dxu;
    }

    public final void onClick(View view) {
        dxu dxu = this.f7581a;
        cxi cxi = dxu.f7597l;
        if (cxi == null) {
            return;
        }
        if (!dxu.f7587b.mo3764d()) {
            ihg.m13039a((hoi) new dxp(cxi, cxd.f5884h), view);
        } else {
            dxu.mo4552f();
        }
    }
}
