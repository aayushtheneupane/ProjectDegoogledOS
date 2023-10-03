package p000;

import java.util.Iterator;

/* renamed from: hvi */
/* compiled from: PG */
final class hvi extends hqq {

    /* renamed from: a */
    private final Iterator f13473a = this.f13475c.f13476a.iterator();

    /* renamed from: b */
    private final Iterator f13474b = ((hvo) this.f13475c.f13477b).iterator();

    /* renamed from: c */
    private final /* synthetic */ hvj f13475c;

    public hvi(hvj hvj) {
        this.f13475c = hvj;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo7687a() {
        if (this.f13473a.hasNext()) {
            return this.f13473a.next();
        }
        while (this.f13474b.hasNext()) {
            Object next = this.f13474b.next();
            if (!this.f13475c.f13476a.contains(next)) {
                return next;
            }
        }
        return mo7688b();
    }
}
