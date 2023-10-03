package p000;

/* renamed from: gug */
/* compiled from: PG */
final /* synthetic */ class gug implements gvv {

    /* renamed from: a */
    private final guy f12071a;

    public gug(guy guy) {
        this.f12071a = guy;
    }

    /* renamed from: a */
    public final int mo7084a(long j, guc guc, boolean z) {
        guy guy = this.f12071a;
        if (guc.mo7081b() && guc.mo7082c()) {
            gtf gtf = gtf.f12011a;
            ife.m12876b(guc.mo7081b(), (Object) "Cannot get timestamp for a CacheResult that does not have content");
            ife.m12876b(guc.mo7082c(), (Object) "Cannot get timestamp for an invalid CacheResult");
            long j2 = guc.f12068b.f12064a;
            ife.m12898e((Object) gtf);
            if (!(j2 < j - guy.f12113b)) {
                return 2;
            }
        }
        if (z || !guc.mo7081b()) {
            return !guc.mo7081b() ? 1 : 3;
        }
        return 2;
    }
}
