package p000;

import android.view.View;

/* renamed from: geo */
/* compiled from: PG */
final class geo implements View.OnClickListener {

    /* renamed from: a */
    private final /* synthetic */ gey f11117a;

    /* renamed from: b */
    private final /* synthetic */ geq f11118b;

    public geo(geq geq, gey gey) {
        this.f11118b = geq;
        this.f11117a = gey;
    }

    public final void onClick(View view) {
        int o = this.f11118b.mo6518P().mo10482o() - 1;
        if (o >= 0) {
            this.f11118b.mo6519a(this.f11117a.mo6543f(o));
        }
    }
}
