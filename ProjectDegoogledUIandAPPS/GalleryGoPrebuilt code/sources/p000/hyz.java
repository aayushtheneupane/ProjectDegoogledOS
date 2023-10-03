package p000;

import java.util.Iterator;

/* renamed from: hyz */
/* compiled from: PG */
public final class hyz implements Iterable {

    /* renamed from: a */
    private final /* synthetic */ Iterable f13668a;

    /* renamed from: b */
    private final /* synthetic */ hzc f13669b;

    public hyz(hzc hzc, Iterable iterable) {
        this.f13669b = hzc;
        this.f13668a = iterable;
    }

    public final Iterator iterator() {
        return new hzb(this.f13669b, this.f13668a);
    }
}
