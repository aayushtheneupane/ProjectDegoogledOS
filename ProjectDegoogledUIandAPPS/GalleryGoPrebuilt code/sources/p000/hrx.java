package p000;

import java.util.Iterator;

/* renamed from: hrx */
/* compiled from: PG */
final class hrx extends hsa {

    /* renamed from: a */
    private final /* synthetic */ Iterable f13334a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public hrx(Iterable iterable, Iterable iterable2) {
        super(iterable);
        this.f13334a = iterable2;
    }

    public final Iterator iterator() {
        return this.f13334a.iterator();
    }
}
