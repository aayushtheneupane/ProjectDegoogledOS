package p000;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* renamed from: hqs */
/* compiled from: PG */
final class hqs extends hui {

    /* renamed from: a */
    private final /* synthetic */ hqu f13271a;

    public hqs(hqu hqu) {
        this.f13271a = hqu;
    }

    /* renamed from: a */
    public final Map mo7694a() {
        return this.f13271a;
    }

    public final boolean contains(Object obj) {
        Set entrySet = this.f13271a.f13275a.entrySet();
        ife.m12898e((Object) entrySet);
        try {
            return entrySet.contains(obj);
        } catch (ClassCastException | NullPointerException e) {
            return false;
        }
    }

    public final Iterator iterator() {
        return new hqt(this.f13271a);
    }

    public final boolean remove(Object obj) {
        Object obj2;
        if (!contains(obj)) {
            return false;
        }
        hrc hrc = this.f13271a.f13276b;
        Object key = ((Map.Entry) obj).getKey();
        Map map = hrc.f13294a;
        ife.m12898e((Object) map);
        try {
            obj2 = map.remove(key);
        } catch (ClassCastException | NullPointerException e) {
            obj2 = null;
        }
        Collection collection = (Collection) obj2;
        if (collection == null) {
            return true;
        }
        int size = collection.size();
        collection.clear();
        hrc.f13295b -= size;
        return true;
    }
}
