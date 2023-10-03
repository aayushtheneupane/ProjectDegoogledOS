package p000;

/* renamed from: ibt */
/* compiled from: PG */
final class ibt extends ibv {
    public ibt(ieh ieh, icf icf) {
        super(ieh, icf);
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ Object mo8359a(Object obj, Object obj2) {
        icf icf = (icf) obj;
        ieh a = icf.mo2538a(obj2);
        ife.m12829a((Object) a, "AsyncFunction.apply returned null instead of a Future. Did you mean to return immediateFuture(null)? %s", (Object) icf);
        return a;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo8334a(Object obj) {
        mo6946a((ieh) obj);
    }
}
