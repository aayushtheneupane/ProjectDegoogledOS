package com.bumptech.glide.load.engine.bitmap_recycle;

import android.support.p002v7.appcompat.R$style;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public final class LruArrayPool implements ArrayPool {
    static final int MAX_OVER_SIZE_MULTIPLE = 8;
    private final Map<Class<?>, ArrayAdapterInterface<?>> adapters;
    private int currentSize;
    private final GroupedLinkedMap<Key, Object> groupedMap;
    private final KeyPool keyPool;
    private final int maxSize;
    private final Map<Class<?>, NavigableMap<Integer, Integer>> sortedSizes;

    private static final class Key implements Poolable {
        private Class<?> arrayClass;
        private final KeyPool pool;
        int size;

        Key(KeyPool keyPool) {
            this.pool = keyPool;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Key)) {
                return false;
            }
            Key key = (Key) obj;
            if (this.size == key.size && this.arrayClass == key.arrayClass) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i = this.size * 31;
            Class<?> cls = this.arrayClass;
            return i + (cls != null ? cls.hashCode() : 0);
        }

        /* access modifiers changed from: package-private */
        public void init(int i, Class<?> cls) {
            this.size = i;
            this.arrayClass = cls;
        }

        public void offer() {
            this.pool.offer(this);
        }

        public String toString() {
            int i = this.size;
            String valueOf = String.valueOf(this.arrayClass);
            StringBuilder sb = new StringBuilder(valueOf.length() + 27);
            sb.append("Key{size=");
            sb.append(i);
            sb.append("array=");
            sb.append(valueOf);
            sb.append('}');
            return sb.toString();
        }
    }

    private static final class KeyPool extends BaseKeyPool<Key> {
        KeyPool() {
        }

        /* access modifiers changed from: protected */
        public Poolable create() {
            return new Key(this);
        }

        /* access modifiers changed from: package-private */
        public Key get(int i, Class<?> cls) {
            Key key = (Key) get();
            key.init(i, cls);
            return key;
        }
    }

    public LruArrayPool() {
        this.groupedMap = new GroupedLinkedMap<>();
        this.keyPool = new KeyPool();
        this.sortedSizes = new HashMap();
        this.adapters = new HashMap();
        this.maxSize = 4194304;
    }

    private void decrementArrayOfSize(int i, Class<?> cls) {
        NavigableMap<Integer, Integer> sizesForAdapter = getSizesForAdapter(cls);
        Integer num = (Integer) sizesForAdapter.get(Integer.valueOf(i));
        if (num == null) {
            String valueOf = String.valueOf(this);
            StringBuilder sb = new StringBuilder(valueOf.length() + 56);
            sb.append("Tried to decrement empty size, size: ");
            sb.append(i);
            sb.append(", this: ");
            sb.append(valueOf);
            throw new NullPointerException(sb.toString());
        } else if (num.intValue() == 1) {
            sizesForAdapter.remove(Integer.valueOf(i));
        } else {
            sizesForAdapter.put(Integer.valueOf(i), Integer.valueOf(num.intValue() - 1));
        }
    }

    private void evictToSize(int i) {
        while (this.currentSize > i) {
            Object removeLast = this.groupedMap.removeLast();
            R$style.checkNotNull(removeLast, "Argument must not be null");
            ArrayAdapterInterface<?> adapterFromType = getAdapterFromType(removeLast.getClass());
            this.currentSize -= adapterFromType.getArrayLength(removeLast) * adapterFromType.getElementSizeInBytes();
            decrementArrayOfSize(adapterFromType.getArrayLength(removeLast), removeLast.getClass());
            if (Log.isLoggable(adapterFromType.getTag(), 2)) {
                String tag = adapterFromType.getTag();
                int arrayLength = adapterFromType.getArrayLength(removeLast);
                StringBuilder sb = new StringBuilder(20);
                sb.append("evicted: ");
                sb.append(arrayLength);
                Log.v(tag, sb.toString());
            }
        }
    }

    private <T> ArrayAdapterInterface<T> getAdapterFromType(Class<T> cls) {
        ArrayAdapterInterface<T> arrayAdapterInterface = this.adapters.get(cls);
        if (arrayAdapterInterface == null) {
            if (cls.equals(int[].class)) {
                arrayAdapterInterface = new IntegerArrayAdapter();
            } else if (cls.equals(byte[].class)) {
                arrayAdapterInterface = new ByteArrayAdapter();
            } else {
                String simpleName = cls.getSimpleName();
                throw new IllegalArgumentException(simpleName.length() != 0 ? "No array pool found for: ".concat(simpleName) : new String("No array pool found for: "));
            }
            this.adapters.put(cls, arrayAdapterInterface);
        }
        return arrayAdapterInterface;
    }

    private <T> T getForKey(Key key, Class<T> cls) {
        ArrayAdapterInterface<T> adapterFromType = getAdapterFromType(cls);
        T t = this.groupedMap.get(key);
        if (t != null) {
            this.currentSize -= adapterFromType.getArrayLength(t) * adapterFromType.getElementSizeInBytes();
            decrementArrayOfSize(adapterFromType.getArrayLength(t), cls);
        }
        if (t != null) {
            return t;
        }
        if (Log.isLoggable(adapterFromType.getTag(), 2)) {
            String tag = adapterFromType.getTag();
            int i = key.size;
            StringBuilder sb = new StringBuilder(27);
            sb.append("Allocated ");
            sb.append(i);
            sb.append(" bytes");
            Log.v(tag, sb.toString());
        }
        return adapterFromType.newArray(key.size);
    }

    private NavigableMap<Integer, Integer> getSizesForAdapter(Class<?> cls) {
        NavigableMap<Integer, Integer> navigableMap = this.sortedSizes.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.sortedSizes.put(cls, treeMap);
        return treeMap;
    }

    public synchronized void clearMemory() {
        evictToSize(0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0023  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <T> T get(int r6, java.lang.Class<T> r7) {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.NavigableMap r0 = r5.getSizesForAdapter(r7)     // Catch:{ all -> 0x004c }
            java.lang.Integer r1 = java.lang.Integer.valueOf(r6)     // Catch:{ all -> 0x004c }
            java.lang.Object r0 = r0.ceilingKey(r1)     // Catch:{ all -> 0x004c }
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch:{ all -> 0x004c }
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002c
            int r3 = r5.currentSize     // Catch:{ all -> 0x004c }
            if (r3 == 0) goto L_0x0020
            int r4 = r5.maxSize     // Catch:{ all -> 0x004c }
            int r4 = r4 / r3
            r3 = 2
            if (r4 < r3) goto L_0x001e
            goto L_0x0020
        L_0x001e:
            r3 = r2
            goto L_0x0021
        L_0x0020:
            r3 = r1
        L_0x0021:
            if (r3 != 0) goto L_0x002d
            int r3 = r0.intValue()     // Catch:{ all -> 0x004c }
            int r4 = r6 * 8
            if (r3 > r4) goto L_0x002c
            goto L_0x002d
        L_0x002c:
            r1 = r2
        L_0x002d:
            if (r1 == 0) goto L_0x003a
            com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool$KeyPool r6 = r5.keyPool     // Catch:{ all -> 0x004c }
            int r0 = r0.intValue()     // Catch:{ all -> 0x004c }
            com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool$Key r6 = r6.get(r0, r7)     // Catch:{ all -> 0x004c }
            goto L_0x0046
        L_0x003a:
            com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool$KeyPool r0 = r5.keyPool     // Catch:{ all -> 0x004c }
            com.bumptech.glide.load.engine.bitmap_recycle.Poolable r0 = r0.get()     // Catch:{ all -> 0x004c }
            com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool$Key r0 = (com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool.Key) r0     // Catch:{ all -> 0x004c }
            r0.init(r6, r7)     // Catch:{ all -> 0x004c }
            r6 = r0
        L_0x0046:
            java.lang.Object r6 = r5.getForKey(r6, r7)     // Catch:{ all -> 0x004c }
            monitor-exit(r5)
            return r6
        L_0x004c:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.load.engine.bitmap_recycle.LruArrayPool.get(int, java.lang.Class):java.lang.Object");
    }

    public synchronized <T> T getExact(int i, Class<T> cls) {
        Key key;
        key = (Key) this.keyPool.get();
        key.init(i, cls);
        return getForKey(key, cls);
    }

    public synchronized <T> void put(T t) {
        Class<?> cls = t.getClass();
        ArrayAdapterInterface<?> adapterFromType = getAdapterFromType(cls);
        int arrayLength = adapterFromType.getArrayLength(t);
        int elementSizeInBytes = adapterFromType.getElementSizeInBytes() * arrayLength;
        int i = 1;
        if (elementSizeInBytes <= this.maxSize / 2) {
            Key key = this.keyPool.get(arrayLength, cls);
            this.groupedMap.put(key, t);
            NavigableMap<Integer, Integer> sizesForAdapter = getSizesForAdapter(cls);
            Integer num = (Integer) sizesForAdapter.get(Integer.valueOf(key.size));
            Integer valueOf = Integer.valueOf(key.size);
            if (num != null) {
                i = 1 + num.intValue();
            }
            sizesForAdapter.put(valueOf, Integer.valueOf(i));
            this.currentSize += elementSizeInBytes;
            evictToSize(this.maxSize);
        }
    }

    public synchronized void trimMemory(int i) {
        if (i >= 40) {
            try {
                clearMemory();
            } catch (Throwable th) {
                throw th;
            }
        } else if (i >= 20) {
            evictToSize(this.maxSize / 2);
        }
    }

    public LruArrayPool(int i) {
        this.groupedMap = new GroupedLinkedMap<>();
        this.keyPool = new KeyPool();
        this.sortedSizes = new HashMap();
        this.adapters = new HashMap();
        this.maxSize = i;
    }
}
