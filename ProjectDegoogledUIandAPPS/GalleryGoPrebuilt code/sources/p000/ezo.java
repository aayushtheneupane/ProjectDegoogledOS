package p000;

/* renamed from: ezo */
/* compiled from: PG */
public final /* synthetic */ class ezo implements eyq {

    /* renamed from: a */
    private final iev f9220a;

    public ezo(iev iev) {
        this.f9220a = iev;
    }

    /* renamed from: a */
    public final void mo5407a(eyp eyp) {
        iev iev = this.f9220a;
        eyr a = eyp.mo5406a();
        if (!a.mo5409c()) {
            if (!a.mo5408b()) {
                iev.mo6952a((Throwable) new ezs(a));
            } else if (iev.mo8346b((Object) eyp)) {
                return;
            }
            ezt.m8427a(eyp);
            return;
        }
        String valueOf = String.valueOf(eyp);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47);
        sb.append("We never use the blocking API for these calls: ");
        sb.append(valueOf);
        throw new AssertionError(sb.toString());
    }
}
