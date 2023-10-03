package p000;

import java.util.Iterator;

/* renamed from: htr */
/* compiled from: PG */
public final class htr extends hsa {

    /* renamed from: a */
    private final /* synthetic */ Iterable f13389a;

    /* renamed from: b */
    private final /* synthetic */ hqa f13390b;

    public htr(Iterable iterable, hqa hqa) {
        this.f13389a = iterable;
        this.f13390b = hqa;
    }

    public final Iterator iterator() {
        Iterator it = this.f13389a.iterator();
        hqa hqa = this.f13390b;
        ife.m12898e((Object) it);
        ife.m12898e((Object) hqa);
        return new htu(it, hqa);
    }
}
