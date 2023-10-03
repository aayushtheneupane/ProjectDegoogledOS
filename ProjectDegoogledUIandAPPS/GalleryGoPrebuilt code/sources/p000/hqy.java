package p000;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hqy */
/* compiled from: PG */
class hqy implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    public final Iterator f13281a;

    /* renamed from: b */
    private final Collection f13282b = this.f13283c.f13285b;

    /* renamed from: c */
    private final /* synthetic */ hqz f13283c;

    public hqy(hqz hqz) {
        Iterator it;
        this.f13283c = hqz;
        Collection collection = hqz.f13285b;
        if (collection instanceof List) {
            it = ((List) collection).listIterator();
        } else {
            it = collection.iterator();
        }
        this.f13281a = it;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public hqy(hqz hqz, Iterator it) {
        this.f13283c = hqz;
        this.f13281a = it;
    }

    public final boolean hasNext() {
        mo7720a();
        return this.f13281a.hasNext();
    }

    public final Object next() {
        mo7720a();
        return this.f13281a.next();
    }

    public final void remove() {
        this.f13281a.remove();
        hrc.m11944b(this.f13283c.f13287d);
        this.f13283c.mo7727b();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo7720a() {
        this.f13283c.mo7724a();
        if (this.f13283c.f13285b != this.f13282b) {
            throw new ConcurrentModificationException();
        }
    }
}
