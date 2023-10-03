package com.google.common.collect;

import android.support.p016v4.media.session.C0107q;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: com.google.common.collect.Sa */
abstract class C1615Sa extends C1668jb {
    C1615Sa() {
    }

    public void clear() {
        map().clear();
    }

    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object c = C1633Xa.m4542c(map(), key);
        if (!C0107q.m131b(c, entry.getValue())) {
            return false;
        }
        if (c != null || map().containsKey(key)) {
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return map().isEmpty();
    }

    /* access modifiers changed from: package-private */
    public abstract Map map();

    public boolean remove(Object obj) {
        if (contains(obj)) {
            return map().keySet().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    public boolean removeAll(Collection collection) {
        if (collection != null) {
            try {
                return C1630W.m4533a((Set) this, collection);
            } catch (UnsupportedOperationException unused) {
                return C1630W.m4534a((Set) this, collection.iterator());
            }
        } else {
            throw new NullPointerException();
        }
    }

    public boolean retainAll(Collection collection) {
        if (collection != null) {
            try {
                return super.retainAll(collection);
            } catch (UnsupportedOperationException unused) {
                HashSet hashSet = new HashSet(C1633Xa.m4545db(collection.size()));
                for (Object next : collection) {
                    if (contains(next)) {
                        hashSet.add(((Map.Entry) next).getKey());
                    }
                }
                return map().keySet().retainAll(hashSet);
            }
        } else {
            throw new NullPointerException();
        }
    }

    public int size() {
        return map().size();
    }
}
