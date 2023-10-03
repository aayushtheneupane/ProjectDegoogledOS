package p000;

/* renamed from: ifb */
/* compiled from: PG */
final class ifb extends ieg {

    /* renamed from: a */
    private final ice f13990a;

    /* renamed from: b */
    private final /* synthetic */ ifd f13991b;

    public ifb(ifd ifd, ice ice) {
        this.f13991b = ifd;
        this.f13990a = (ice) ife.m12898e((Object) ice);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo8409a(Object obj, Throwable th) {
        ieh ieh = (ieh) obj;
        if (th == null) {
            this.f13991b.mo6946a(ieh);
        } else {
            this.f13991b.mo6952a(th);
        }
    }

    /* renamed from: c */
    public final boolean mo8410c() {
        return this.f13991b.isDone();
    }

    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo8408b() {
        return (ieh) ife.m12829a((Object) this.f13990a.mo2539a(), "AsyncCallable.call returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object) this.f13990a);
    }

    /* renamed from: a */
    public final String mo8406a() {
        return this.f13990a.toString();
    }
}
