package p000;

/* renamed from: ibb */
/* compiled from: PG */
final class ibb extends ibd {
    public ibb(ieh ieh, Class cls, icf icf) {
        super(ieh, cls, icf);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo8333a(Object obj, Throwable th) {
        icf icf = (icf) obj;
        ieh a = icf.mo2538a(th);
        ife.m12829a((Object) a, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object) icf);
        return a;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo8334a(Object obj) {
        mo6946a((ieh) obj);
    }
}
