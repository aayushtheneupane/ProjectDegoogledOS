package p000;

/* renamed from: afr */
/* compiled from: PG */
final class afr extends afm {

    /* renamed from: a */
    private final afs f348a;

    public afr(afs afs) {
        this.f348a = afs;
    }

    /* renamed from: a */
    public final void mo265a(afl afl) {
        afs afs = this.f348a;
        int i = afs.f349n - 1;
        afs.f349n = i;
        if (i == 0) {
            afs.f350o = false;
            afs.mo320e();
        }
        afl.mo312b((afk) this);
    }

    /* renamed from: b */
    public final void mo278b(afl afl) {
        afs afs = this.f348a;
        if (!afs.f350o) {
            afs.mo318d();
            this.f348a.f350o = true;
        }
    }
}
