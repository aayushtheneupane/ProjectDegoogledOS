package p000;

import android.view.View;

/* renamed from: fec */
/* compiled from: PG */
final /* synthetic */ class fec implements hpr {

    /* renamed from: a */
    private final fee f9359a;

    /* renamed from: b */
    private final View f9360b;

    public fec(fee fee, View view) {
        this.f9359a = fee;
        this.f9360b = view;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        fee fee = this.f9359a;
        View view = this.f9360b;
        fdr fdr = (fdr) obj;
        fdr a = fdz.m8660a(view, ffa.f9433a);
        if (a != null) {
            if (!a.mo5538a()) {
                a.mo5537a(fdr);
            } else if (a.mo5542c(ffa.f9433a)) {
                fee.f9363b.mo5549a(new IllegalStateException("CVE is already impressed and cannot be replaced."));
            } else {
                fee.f9363b.mo5549a(new IllegalStateException("CVE is already attached and cannot be replaced."));
            }
            return a;
        }
        fdz fdz = new fdz(view, fdr);
        fdr.f9326c = fdz;
        fdz.mo5528d();
        return fdr;
    }
}
