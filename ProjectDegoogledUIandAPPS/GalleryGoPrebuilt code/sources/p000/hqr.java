package p000;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/* renamed from: hqr */
/* compiled from: PG */
class hqr extends hrc implements hum {
    public static final long serialVersionUID = 6588350623831699109L;

    /* renamed from: a */
    public /* bridge */ /* synthetic */ Collection mo7691a() {
        throw null;
    }

    /* renamed from: a */
    public final List mo7693a(Object obj) {
        Collection collection = (Collection) this.f13294a.get(obj);
        if (collection == null) {
            collection = mo7691a();
        }
        return (List) mo7692a(obj, collection);
    }

    /* renamed from: a */
    public final Collection mo7692a(Object obj, Collection collection) {
        return mo7762a(obj, (List) collection, (hqz) null);
    }

    public hqr(Map map) {
        super(map);
    }
}
