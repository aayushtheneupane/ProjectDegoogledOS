package p000;

import java.util.Collection;
import java.util.Iterator;

/* renamed from: huq */
/* compiled from: PG */
final class huq extends hvm {

    /* renamed from: a */
    private final /* synthetic */ hrj f13421a;

    public huq(hrj hrj) {
        this.f13421a = hrj;
    }

    public final Iterator iterator() {
        return this.f13421a.mo7771a();
    }

    public huq() {
    }

    public final void clear() {
        this.f13421a.clear();
    }

    public final boolean contains(Object obj) {
        return this.f13421a.contains(obj);
    }

    public final boolean containsAll(Collection collection) {
        return this.f13421a.containsAll(collection);
    }

    public final boolean isEmpty() {
        return this.f13421a.isEmpty();
    }

    public final boolean remove(Object obj) {
        return this.f13421a.mo7772b(obj, Integer.MAX_VALUE) > 0;
    }

    public final int size() {
        return this.f13421a.mo7796f().size();
    }
}
