package android.support.p000v4.util;

import android.support.p000v4.util.MapCollections;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* renamed from: android.support.v4.util.ArrayMap */
public class ArrayMap<K, V> extends SimpleArrayMap<K, V> implements Map<K, V> {
    MapCollections<K, V> mCollections;

    public ArrayMap() {
    }

    private MapCollections<K, V> getCollection() {
        if (this.mCollections == null) {
            this.mCollections = new MapCollections<K, V>() {
                /* access modifiers changed from: protected */
                public void colClear() {
                    ArrayMap.this.clear();
                }

                /* access modifiers changed from: protected */
                public Object colGetEntry(int i, int i2) {
                    return ArrayMap.this.mArray[(i << 1) + i2];
                }

                /* access modifiers changed from: protected */
                public Map<K, V> colGetMap() {
                    return ArrayMap.this;
                }

                /* access modifiers changed from: protected */
                public int colGetSize() {
                    return ArrayMap.this.mSize;
                }

                /* access modifiers changed from: protected */
                public int colIndexOfKey(Object obj) {
                    return ArrayMap.this.indexOfKey(obj);
                }

                /* access modifiers changed from: protected */
                public int colIndexOfValue(Object obj) {
                    return ArrayMap.this.indexOfValue(obj);
                }

                /* access modifiers changed from: protected */
                public void colPut(K k, V v) {
                    ArrayMap.this.put(k, v);
                }

                /* access modifiers changed from: protected */
                public void colRemoveAt(int i) {
                    ArrayMap.this.removeAt(i);
                }

                /* access modifiers changed from: protected */
                public V colSetValue(int i, V v) {
                    int i2 = (i << 1) + 1;
                    V[] vArr = ArrayMap.this.mArray;
                    V v2 = vArr[i2];
                    vArr[i2] = v;
                    return v2;
                }
            };
        }
        return this.mCollections;
    }

    public Set<Map.Entry<K, V>> entrySet() {
        MapCollections collection = getCollection();
        if (collection.mEntrySet == null) {
            collection.mEntrySet = new MapCollections.EntrySet();
        }
        return collection.mEntrySet;
    }

    public Set<K> keySet() {
        MapCollections collection = getCollection();
        if (collection.mKeySet == null) {
            collection.mKeySet = new MapCollections.KeySet();
        }
        return collection.mKeySet;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(map.size() + this.mSize);
        for (Map.Entry next : map.entrySet()) {
            put(next.getKey(), next.getValue());
        }
    }

    public boolean retainAll(Collection<?> collection) {
        return MapCollections.retainAllHelper(this, collection);
    }

    public Collection<V> values() {
        MapCollections collection = getCollection();
        if (collection.mValues == null) {
            collection.mValues = new MapCollections.ValuesCollection();
        }
        return collection.mValues;
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        if (simpleArrayMap != null) {
            putAll(simpleArrayMap);
        }
    }
}
