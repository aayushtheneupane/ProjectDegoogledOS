package p000;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hqt */
/* compiled from: PG */
final class hqt implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private final Iterator f13272a = this.f13274c.f13275a.entrySet().iterator();

    /* renamed from: b */
    private Collection f13273b;

    /* renamed from: c */
    private final /* synthetic */ hqu f13274c;

    public hqt(hqu hqu) {
        this.f13274c = hqu;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f13272a.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.f13272a.next();
        this.f13273b = (Collection) entry.getValue();
        hqu hqu = this.f13274c;
        Object key = entry.getKey();
        return new hsg(key, hqu.f13276b.mo7692a(key, (Collection) entry.getValue()));
    }

    public final void remove() {
        ife.m12875b(this.f13273b != null);
        this.f13272a.remove();
        hrc.m11945b(this.f13274c.f13276b, this.f13273b.size());
        this.f13273b.clear();
        this.f13273b = null;
    }
}
