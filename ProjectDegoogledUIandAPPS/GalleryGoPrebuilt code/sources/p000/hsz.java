package p000;

import java.util.Map;

/* renamed from: hsz */
/* compiled from: PG */
final class hsz extends hvr {

    /* renamed from: a */
    private final hvr f13364a = this.f13365b.f13369a.entrySet().iterator();

    /* renamed from: b */
    private final /* synthetic */ htc f13365b;

    public hsz(htc htc) {
        this.f13365b = htc;
    }

    public final boolean hasNext() {
        return this.f13364a.hasNext();
    }

    public final Object next() {
        return ((Map.Entry) this.f13364a.next()).getValue();
    }
}
