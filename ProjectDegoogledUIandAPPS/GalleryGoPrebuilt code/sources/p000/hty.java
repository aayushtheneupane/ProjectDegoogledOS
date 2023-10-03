package p000;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: hty */
/* compiled from: PG */
final class hty implements Iterator, p003j$.util.Iterator {

    /* renamed from: a */
    private Iterator f13401a;

    /* renamed from: b */
    private Iterator f13402b = htx.f13399a;

    /* renamed from: c */
    private Iterator f13403c;

    /* renamed from: d */
    private Deque f13404d;

    public hty(Iterator it) {
        this.f13403c = (Iterator) ife.m12898e((Object) it);
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final boolean hasNext() {
        Iterator it;
        while (!((Iterator) ife.m12898e((Object) this.f13402b)).hasNext()) {
            while (true) {
                Iterator it2 = this.f13403c;
                it = null;
                if (it2 == null || !it2.hasNext()) {
                    Deque deque = this.f13404d;
                    if (deque == null || deque.isEmpty()) {
                        break;
                    }
                    this.f13403c = (Iterator) this.f13404d.removeFirst();
                } else {
                    it = this.f13403c;
                    break;
                }
            }
            this.f13403c = it;
            if (it == null) {
                return false;
            }
            Iterator it3 = (Iterator) it.next();
            this.f13402b = it3;
            if (it3 instanceof hty) {
                hty hty = (hty) it3;
                this.f13402b = hty.f13402b;
                if (this.f13404d == null) {
                    this.f13404d = new ArrayDeque();
                }
                this.f13404d.addFirst(this.f13403c);
                if (hty.f13404d != null) {
                    while (!hty.f13404d.isEmpty()) {
                        this.f13404d.addFirst((Iterator) hty.f13404d.removeLast());
                    }
                }
                this.f13403c = hty.f13403c;
            }
        }
        return true;
    }

    public final Object next() {
        if (hasNext()) {
            Iterator it = this.f13402b;
            this.f13401a = it;
            return it.next();
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        ife.m12875b(this.f13401a != null);
        this.f13401a.remove();
        this.f13401a = null;
    }
}
