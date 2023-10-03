package p000;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: huc */
/* compiled from: PG */
final class huc implements ListIterator, p003j$.util.ListIterator {

    /* renamed from: a */
    private boolean f13407a;

    /* renamed from: b */
    private final /* synthetic */ ListIterator f13408b;

    /* renamed from: c */
    private final /* synthetic */ hud f13409c;

    public huc(hud hud, ListIterator listIterator) {
        this.f13409c = hud;
        this.f13408b = listIterator;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final void add(Object obj) {
        this.f13408b.add(obj);
        this.f13408b.previous();
        this.f13407a = false;
    }

    public final boolean hasNext() {
        return this.f13408b.hasPrevious();
    }

    public final boolean hasPrevious() {
        return this.f13408b.hasNext();
    }

    public final Object next() {
        if (hasNext()) {
            this.f13407a = true;
            return this.f13408b.previous();
        }
        throw new NoSuchElementException();
    }

    public final int nextIndex() {
        return this.f13409c.mo8030a(this.f13408b.nextIndex());
    }

    public final Object previous() {
        if (hasPrevious()) {
            this.f13407a = true;
            return this.f13408b.next();
        }
        throw new NoSuchElementException();
    }

    public final int previousIndex() {
        return nextIndex() - 1;
    }

    public final void remove() {
        ife.m12875b(this.f13407a);
        this.f13408b.remove();
        this.f13407a = false;
    }

    public final void set(Object obj) {
        ife.m12896d(this.f13407a);
        this.f13408b.set(obj);
    }
}
