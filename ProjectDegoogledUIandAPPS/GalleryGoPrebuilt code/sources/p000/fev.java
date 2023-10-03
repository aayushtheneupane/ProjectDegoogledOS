package p000;

import java.util.List;

/* renamed from: fev */
/* compiled from: PG */
public final /* synthetic */ class fev implements fct {

    /* renamed from: a */
    private final fdr f9410a;

    /* renamed from: b */
    private final fdu f9411b;

    public fev(fdr fdr, fdu fdu) {
        this.f9410a = fdr;
        this.f9411b = fdu;
    }

    /* renamed from: a */
    public final List mo5488a(fcy fcy) {
        fdr fdr = this.f9410a;
        fdu fdu = this.f9411b;
        boolean z = false;
        ife.m12876b(fdr != null, (Object) "View is not instrumented.");
        if (fdr.mo5541c() == 1) {
            z = true;
        }
        ife.m12878b(z, "VE is not visible: %s", (Object) fdr);
        ife.m12898e((Object) ffa.f9433a);
        fdo fdo = fdr.f9325b;
        if (fdo != null) {
            fdo.mo5520d(fdr);
        }
        ife.m12879b(fdr.mo5542c(ffa.f9433a), "VE is not impressed: %s {attached=%s}", fdr, Boolean.valueOf(fdr.mo5544e(ffa.f9433a).mo5530f()));
        fcl.m8575a();
        return hso.m12033a((Object) new fff(fej.m8699a(fdr), fdu));
    }
}
