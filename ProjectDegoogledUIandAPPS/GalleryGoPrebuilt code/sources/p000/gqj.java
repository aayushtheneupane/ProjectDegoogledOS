package p000;

/* renamed from: gqj */
/* compiled from: PG */
final /* synthetic */ class gqj implements eyq {

    /* renamed from: a */
    private final iev f11839a;

    public gqj(iev iev) {
        this.f11839a = iev;
    }

    /* renamed from: a */
    public final void mo5407a(eyp eyp) {
        iev iev = this.f11839a;
        eyr a = eyp.mo5406a();
        if (!a.mo5409c()) {
            if (!a.mo5408b()) {
                iev.mo6952a((Throwable) new gqq(a));
            } else if (iev.mo8346b((Object) eyp)) {
                return;
            }
            gqr.m10644a((Object) eyp);
            return;
        }
        String valueOf = String.valueOf(eyp);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
        sb.append("We never use the blocking API for these calls: ");
        sb.append(valueOf);
        throw new AssertionError(sb.toString());
    }
}
