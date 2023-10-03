package p000a.p005b;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: a.b.b */
public class C0015b extends C0027n implements Map {
    C0026m mCollections;

    public C0015b() {
    }

    private C0026m getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new C0014a(this);
        }
        return this.mCollections;
    }

    public Set entrySet() {
        C0026m collection = getCollection();
        if (collection.mEntrySet == null) {
            collection.mEntrySet = new C0022i(collection);
        }
        return collection.mEntrySet;
    }

    public Set keySet() {
        C0026m collection = getCollection();
        if (collection.mKeySet == null) {
            collection.mKeySet = new C0023j(collection);
        }
        return collection.mKeySet;
    }

    public void putAll(Map map) {
        ensureCapacity(map.size() + this.mSize);
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public boolean retainAll(Collection collection) {
        return C0026m.retainAllHelper(this, collection);
    }

    public Collection values() {
        C0026m collection = getCollection();
        if (collection.mValues == null) {
            collection.mValues = new C0025l(collection);
        }
        return collection.mValues;
    }

    public C0015b(int i) {
        super(i);
    }
}
