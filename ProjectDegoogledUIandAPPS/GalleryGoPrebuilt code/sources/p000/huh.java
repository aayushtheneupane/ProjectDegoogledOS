package p000;

import java.util.Iterator;
import java.util.Map;

/* renamed from: huh */
/* compiled from: PG */
final class huh extends hvr {

    /* renamed from: a */
    private final /* synthetic */ Iterator f13412a;

    public huh(Iterator it) {
        this.f13412a = it;
    }

    public final boolean hasNext() {
        return this.f13412a.hasNext();
    }

    public final /* bridge */ /* synthetic */ Object next() {
        Map.Entry entry = (Map.Entry) this.f13412a.next();
        ife.m12898e((Object) entry);
        return new hug(entry);
    }
}
