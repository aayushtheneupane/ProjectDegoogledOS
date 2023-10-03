package p000;

/* renamed from: hcl */
/* compiled from: PG */
public final class hcl extends ftr {
    public hcl(C0147fh fhVar, boolean z) {
        super(fhVar, z);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final gkn mo6180a(gkn gkn) {
        return gkn;
    }

    /* renamed from: d */
    public static hcl m11211d(C0147fh fhVar) {
        return new hcl(fhVar, true);
    }

    /* renamed from: a */
    public static final void m11210a(C0147fh fhVar, ikf ikf) {
        m9611b(fhVar);
        imi.m14107a(fhVar.f9592k, "TIKTOK_FRAGMENT_ARGUMENT", (ikf) ife.m12898e((Object) ikf));
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public final void mo6181c(C0147fh fhVar) {
        super.mo6181c(fhVar);
        ife.m12879b(fhVar.mo5653m() instanceof hbs, "TikTok Fragment, %s cannot be attached to a non-TikTok Activity, %s", fhVar.getClass().getSimpleName(), fhVar.mo5653m().getClass().getSimpleName());
    }
}
