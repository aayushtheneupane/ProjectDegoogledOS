package p000;

import android.view.View;

/* renamed from: gem */
/* compiled from: PG */
final class gem implements View.OnClickListener {

    /* renamed from: a */
    private final /* synthetic */ geq f11114a;

    public gem(geq geq) {
        this.f11114a = geq;
    }

    public final void onClick(View view) {
        geq geq = this.f11114a;
        int i = geq.f11123ac;
        if (i == 2) {
            geq.mo6520e(1);
        } else if (i == 1) {
            geq.mo6520e(2);
        }
    }
}
