package p000;

import p003j$.util.Optional;

/* renamed from: dbp */
/* compiled from: PG */
final /* synthetic */ class dbp implements icf {

    /* renamed from: a */
    private final dbs f6205a;

    /* renamed from: b */
    private final cyd f6206b;

    /* renamed from: c */
    private final dbo f6207c;

    public dbp(dbs dbs, cyd cyd, dbo dbo) {
        this.f6205a = dbs;
        this.f6206b = cyd;
        this.f6207c = dbo;
    }

    /* renamed from: a */
    public final ieh mo2538a(Object obj) {
        dbs dbs = this.f6205a;
        cyd cyd = this.f6206b;
        dbo dbo = this.f6207c;
        return dbs.mo4040a(Optional.m16285of(cyd), (String) ((Optional) obj).orElse(dbo.mo3208e()), dbo, true);
    }
}
