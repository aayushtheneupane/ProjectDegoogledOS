package p000;

import java.util.Iterator;

/* renamed from: htu */
/* compiled from: PG */
final class htu extends hqq {

    /* renamed from: a */
    private final /* synthetic */ Iterator f13394a;

    /* renamed from: b */
    private final /* synthetic */ hqa f13395b;

    public htu(Iterator it, hqa hqa) {
        this.f13394a = it;
        this.f13395b = hqa;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo7687a() {
        while (this.f13394a.hasNext()) {
            Object next = this.f13394a.next();
            if (this.f13395b.mo4768a(next)) {
                return next;
            }
        }
        return mo7688b();
    }
}
