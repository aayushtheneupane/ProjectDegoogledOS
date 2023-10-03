package p000;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: hqu */
/* compiled from: PG */
final class hqu extends hul {

    /* renamed from: a */
    public final transient Map f13275a;

    /* renamed from: b */
    public final /* synthetic */ hrc f13276b;

    public hqu(hrc hrc, Map map) {
        this.f13276b = hrc;
        this.f13275a = map;
    }

    public final int size() {
        return ((hru) this.f13275a).f13328g;
    }

    public final void clear() {
        Map map = this.f13275a;
        hrc hrc = this.f13276b;
        Map map2 = hrc.f13294a;
        if (map == map2) {
            for (Collection clear : map2.values()) {
                clear.clear();
            }
            hrc.f13294a.clear();
            hrc.f13295b = 0;
            return;
        }
        ife.m12889c((Iterator) new hqt(this));
    }

    public final boolean containsKey(Object obj) {
        Map map = this.f13275a;
        ife.m12898e((Object) map);
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    /* renamed from: a */
    public final Set mo7701a() {
        return new hqs(this);
    }

    public final boolean equals(Object obj) {
        return this == obj || this.f13275a.equals(obj);
    }

    public final /* bridge */ /* synthetic */ Object get(Object obj) {
        Collection collection = (Collection) ife.m12831a(this.f13275a, obj);
        if (collection != null) {
            return this.f13276b.mo7692a(obj, collection);
        }
        return null;
    }

    public final int hashCode() {
        return this.f13275a.hashCode();
    }

    public final Set keySet() {
        return this.f13276b.mo7786d();
    }

    public final /* bridge */ /* synthetic */ Object remove(Object obj) {
        Collection collection = (Collection) this.f13275a.remove(obj);
        if (collection == null) {
            return null;
        }
        Collection a = this.f13276b.mo7691a();
        a.addAll(collection);
        hrc.m11945b(this.f13276b, collection.size());
        collection.clear();
        return a;
    }

    public final String toString() {
        return this.f13275a.toString();
    }
}
