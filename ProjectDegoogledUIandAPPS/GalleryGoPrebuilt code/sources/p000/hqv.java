package p000;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hqv */
/* compiled from: PG */
final class hqv implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private Map.Entry f13277a;

    /* renamed from: b */
    private final /* synthetic */ Iterator f13278b;

    /* renamed from: c */
    private final /* synthetic */ hqw f13279c;

    public hqv(hqw hqw, Iterator it) {
        this.f13279c = hqw;
        this.f13278b = it;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        return this.f13278b.hasNext();
    }

    public final Object next() {
        Map.Entry entry = (Map.Entry) this.f13278b.next();
        this.f13277a = entry;
        return entry.getKey();
    }

    public final void remove() {
        ife.m12875b(this.f13277a != null);
        Collection collection = (Collection) this.f13277a.getValue();
        this.f13278b.remove();
        hrc.m11945b(this.f13279c.f13280a, collection.size());
        collection.clear();
        this.f13277a = null;
    }
}
