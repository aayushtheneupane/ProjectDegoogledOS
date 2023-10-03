package p000;

import android.app.Activity;
import java.util.Set;

/* renamed from: hcq */
/* compiled from: PG */
public final class hcq {

    /* renamed from: a */
    private final fwc f12480a;

    /* renamed from: b */
    private final C0149fj f12481b;

    /* renamed from: c */
    private final Set f12482c;

    /* renamed from: d */
    private final Set f12483d;

    /* renamed from: e */
    private boolean f12484e = false;

    public hcq(Activity activity, Set set, Set set2) {
        this.f12481b = (C0149fj) activity;
        this.f12480a = ((fvw) activity).mo6228g();
        this.f12482c = set;
        this.f12483d = set2;
    }

    /* renamed from: a */
    public final void mo7280a() {
        if (!this.f12484e) {
            for (C0654y a : this.f12483d) {
                this.f12481b.mo5ad().mo64a(a);
            }
            hvr a2 = ((hvf) this.f12482c).iterator();
            while (a2.hasNext()) {
                this.f12480a.mo6246a((fwt) (hcp) a2.next());
            }
            this.f12484e = true;
        }
    }
}
