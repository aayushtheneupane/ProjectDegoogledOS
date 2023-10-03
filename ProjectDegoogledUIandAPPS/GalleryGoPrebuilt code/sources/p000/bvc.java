package p000;

import android.view.View;

/* renamed from: bvc */
/* compiled from: PG */
final /* synthetic */ class bvc implements View.OnClickListener {

    /* renamed from: a */
    private final bvv f3668a;

    public bvc(bvv bvv) {
        this.f3668a = bvv;
    }

    public final void onClick(View view) {
        bvv bvv = this.f3668a;
        if (bvv.f3690C) {
            boolean z = true;
            if (bvv.f3691D && !bvv.f3720b.f3637h) {
                z = false;
            }
            bvv.mo2809b(z);
            bvv.f3726h.mo2884a();
        }
    }
}
