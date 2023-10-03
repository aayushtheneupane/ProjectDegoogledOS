package p000;

/* renamed from: bou */
/* compiled from: PG */
final /* synthetic */ class bou implements hga {

    /* renamed from: a */
    private final long f3278a;

    public bou(long j) {
        this.f3278a = j;
    }

    /* renamed from: a */
    public final void mo2584a(hfz hfz) {
        long j = this.f3278a;
        hgn hgn = new hgn();
        hgn.mo7409a("UPDATE mt");
        hgn.mo7409a(" SET ap");
        hgn.mo7409a(" = ap + 1 ");
        hgn.mo7409a(" WHERE a = ?");
        hgn.mo7411b(String.valueOf(j));
        hfz.mo7388a(hgn.mo7407a());
    }
}
