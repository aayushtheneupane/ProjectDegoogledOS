package p000;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: hui */
/* compiled from: PG */
abstract class hui extends hvm {
    /* renamed from: a */
    public abstract Map mo7694a();

    public final void clear() {
        mo7694a().clear();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object a = ife.m12831a(mo7694a(), key);
        if (!ife.m12891c(a, entry.getValue())) {
            return false;
        }
        if (a != null || mo7694a().containsKey(key)) {
            return true;
        }
        return false;
    }

    public final boolean isEmpty() {
        return mo7694a().isEmpty();
    }

    public boolean remove(Object obj) {
        if (contains(obj)) {
            return mo7694a().keySet().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    public final boolean removeAll(Collection collection) {
        try {
            return ife.m12857a((Set) this, (Collection) ife.m12898e((Object) collection));
        } catch (UnsupportedOperationException e) {
            return ife.m12858a((Set) this, collection.iterator());
        }
    }

    public final boolean retainAll(Collection collection) {
        int i;
        try {
            return super.retainAll((Collection) ife.m12898e((Object) collection));
        } catch (UnsupportedOperationException e) {
            int size = collection.size();
            if (size < 3) {
                ife.m12839a(size, "expectedSize");
                i = size + 1;
            } else {
                i = size < 1073741824 ? (int) ((((float) size) / 0.75f) + 1.0f) : Integer.MAX_VALUE;
            }
            HashSet hashSet = new HashSet(i);
            for (Object next : collection) {
                if (contains(next)) {
                    hashSet.add(((Map.Entry) next).getKey());
                }
            }
            return mo7694a().keySet().retainAll(hashSet);
        }
    }

    public final int size() {
        return mo7694a().size();
    }
}
