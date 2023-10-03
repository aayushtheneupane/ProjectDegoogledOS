package p000;

import java.util.Iterator;

/* renamed from: hvk */
/* compiled from: PG */
final class hvk extends hqq {

    /* renamed from: a */
    private final Iterator f13478a = this.f13479b.f13480a.iterator();

    /* renamed from: b */
    private final /* synthetic */ hvl f13479b;

    public hvk(hvl hvl) {
        this.f13479b = hvl;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final Object mo7687a() {
        while (this.f13478a.hasNext()) {
            Object next = this.f13478a.next();
            if (!this.f13479b.f13481b.contains(next)) {
                return next;
            }
        }
        return mo7688b();
    }
}
