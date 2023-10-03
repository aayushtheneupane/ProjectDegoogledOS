package p000;

/* renamed from: gvt */
/* compiled from: PG */
final class gvt implements gux {

    /* renamed from: a */
    public final /* synthetic */ gvu f12154a;

    public gvt(gvu gvu) {
        this.f12154a = gvu;
    }

    /* renamed from: a */
    public final void mo7104a(guw guw) {
        boolean z;
        guu guu = guu.LOCAL_STATE_CHANGE;
        int ordinal = guw.f12101c.ordinal();
        if (ordinal == 0) {
            z = this.f12154a.f12159e.compareAndSet((Object) null, guu.LOCAL_STATE_CHANGE);
        } else if (ordinal != 1) {
            String valueOf = String.valueOf(guw);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 25);
            sb.append("Unrecognized CallReason: ");
            sb.append(valueOf);
            throw new IllegalStateException(sb.toString());
        } else if (!this.f12154a.f12159e.compareAndSet(guu.LOCAL_STATE_CHANGE, guu.REMOTE_STATE_CHANGE)) {
            z = this.f12154a.f12159e.compareAndSet((Object) null, guu.REMOTE_STATE_CHANGE);
        } else {
            return;
        }
        if (z) {
            this.f12154a.f12156b.execute(new gvs(this));
        }
    }
}
