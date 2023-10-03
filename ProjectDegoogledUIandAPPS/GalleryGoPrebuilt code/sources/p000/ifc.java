package p000;

import java.util.concurrent.Callable;

/* renamed from: ifc */
/* compiled from: PG */
final class ifc extends ieg {

    /* renamed from: a */
    private final Callable f13992a;

    /* renamed from: b */
    private final /* synthetic */ ifd f13993b;

    public ifc(ifd ifd, Callable callable) {
        this.f13993b = ifd;
        this.f13992a = (Callable) ife.m12898e((Object) callable);
    }

    /* renamed from: a */
    public final void mo8409a(Object obj, Throwable th) {
        if (th == null) {
            this.f13993b.mo8346b(obj);
        } else {
            this.f13993b.mo6952a(th);
        }
    }

    /* renamed from: c */
    public final boolean mo8410c() {
        return this.f13993b.isDone();
    }

    /* renamed from: b */
    public final Object mo8408b() {
        return this.f13992a.call();
    }

    /* renamed from: a */
    public final String mo8406a() {
        return this.f13992a.toString();
    }
}
