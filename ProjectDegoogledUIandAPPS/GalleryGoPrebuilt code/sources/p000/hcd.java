package p000;

import android.view.LayoutInflater;

/* renamed from: hcd */
/* compiled from: PG */
final /* synthetic */ class hcd implements hqk {

    /* renamed from: a */
    private final hcf f12468a;

    public hcd(hcf hcf) {
        this.f12468a = hcf;
    }

    /* renamed from: a */
    public final Object mo2652a() {
        hcf hcf = this.f12468a;
        return ((LayoutInflater) hcf.getBaseContext().getSystemService("layout_inflater")).cloneInContext(hcf);
    }
}
