package p000;

import java.util.Iterator;

/* renamed from: hts */
/* compiled from: PG */
public final class hts extends hsa {

    /* renamed from: a */
    private final /* synthetic */ Iterable f13391a;

    /* renamed from: b */
    private final /* synthetic */ hpr f13392b;

    public hts(Iterable iterable, hpr hpr) {
        this.f13391a = iterable;
        this.f13392b = hpr;
    }

    public final Iterator iterator() {
        Iterator it = this.f13391a.iterator();
        hpr hpr = this.f13392b;
        ife.m12898e((Object) hpr);
        return new htv(it, hpr);
    }
}
