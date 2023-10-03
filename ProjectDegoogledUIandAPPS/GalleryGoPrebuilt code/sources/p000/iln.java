package p000;

import java.util.ListIterator;
import p003j$.util.Iterator$$CC;
import p003j$.util.function.Consumer;

/* renamed from: iln */
/* compiled from: PG */
final class iln implements ListIterator, p003j$.util.ListIterator {

    /* renamed from: a */
    private final ListIterator f14455a = this.f14457c.f14460a.listIterator(this.f14456b);

    /* renamed from: b */
    private final /* synthetic */ int f14456b;

    /* renamed from: c */
    private final /* synthetic */ ilp f14457c;

    public iln(ilp ilp, int i) {
        this.f14457c = ilp;
        this.f14456b = i;
    }

    public final void forEachRemaining(Consumer consumer) {
        Iterator$$CC.forEachRemaining$$dflt$$(this, consumer);
    }

    public final /* bridge */ /* synthetic */ void add(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.f14455a.hasNext();
    }

    public final boolean hasPrevious() {
        return this.f14455a.hasPrevious();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        return (String) this.f14455a.next();
    }

    public final int nextIndex() {
        return this.f14455a.nextIndex();
    }

    public final /* bridge */ /* synthetic */ Object previous() {
        return (String) this.f14455a.previous();
    }

    public final int previousIndex() {
        return this.f14455a.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* bridge */ /* synthetic */ void set(Object obj) {
        String str = (String) obj;
        throw new UnsupportedOperationException();
    }
}
