package p000;

import android.view.View;

/* renamed from: gen */
/* compiled from: PG */
final class gen implements View.OnClickListener {

    /* renamed from: a */
    private final /* synthetic */ gey f11115a;

    /* renamed from: b */
    private final /* synthetic */ geq f11116b;

    public gen(geq geq, gey gey) {
        this.f11116b = geq;
        this.f11115a = gey;
    }

    public final void onClick(View view) {
        int n = this.f11116b.mo6518P().mo10481n() + 1;
        if (n < this.f11116b.f11121aa.getAdapter().mo220a()) {
            this.f11116b.mo6519a(this.f11115a.mo6543f(n));
        }
    }
}
