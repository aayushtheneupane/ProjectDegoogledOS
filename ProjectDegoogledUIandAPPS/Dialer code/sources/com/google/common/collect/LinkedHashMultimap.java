package com.google.common.collect;

import android.support.p002v7.appcompat.R$style;
import com.google.common.base.MoreObjects;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public final class LinkedHashMultimap<K, V> extends LinkedHashMultimapGwtSerializationDependencies<K, V> {
    static final double VALUE_SET_LOAD_FACTOR = 1.0d;
    private static final long serialVersionUID = 1;
    /* access modifiers changed from: private */
    public transient ValueEntry<K, V> multimapHeaderEntry;
    transient int valueSetCapacity;

    static final class ValueEntry<K, V> extends ImmutableEntry<K, V> implements ValueSetLink<K, V> {
        ValueEntry<K, V> nextInValueBucket;
        ValueEntry<K, V> predecessorInMultimap;
        ValueSetLink<K, V> predecessorInValueSet;
        final int smearedValueHash;
        ValueEntry<K, V> successorInMultimap;
        ValueSetLink<K, V> successorInValueSet;

        ValueEntry(K k, V v, int i, ValueEntry<K, V> valueEntry) {
            super(k, v);
            this.smearedValueHash = i;
            this.nextInValueBucket = valueEntry;
        }

        public ValueEntry<K, V> getPredecessorInMultimap() {
            return this.predecessorInMultimap;
        }

        public ValueSetLink<K, V> getPredecessorInValueSet() {
            return this.predecessorInValueSet;
        }

        public ValueEntry<K, V> getSuccessorInMultimap() {
            return this.successorInMultimap;
        }

        public ValueSetLink<K, V> getSuccessorInValueSet() {
            return this.successorInValueSet;
        }

        /* access modifiers changed from: package-private */
        public boolean matchesValue(Object obj, int i) {
            return this.smearedValueHash == i && R$style.equal(getValue(), obj);
        }

        public void setPredecessorInMultimap(ValueEntry<K, V> valueEntry) {
            this.predecessorInMultimap = valueEntry;
        }

        public void setPredecessorInValueSet(ValueSetLink<K, V> valueSetLink) {
            this.predecessorInValueSet = valueSetLink;
        }

        public void setSuccessorInMultimap(ValueEntry<K, V> valueEntry) {
            this.successorInMultimap = valueEntry;
        }

        public void setSuccessorInValueSet(ValueSetLink<K, V> valueSetLink) {
            this.successorInValueSet = valueSetLink;
        }
    }

    final class ValueSet extends Sets$ImprovedAbstractSet<V> implements ValueSetLink<K, V> {
        /* access modifiers changed from: private */
        public ValueSetLink<K, V> firstEntry;
        ValueEntry<K, V>[] hashTable;
        private final K key;
        private ValueSetLink<K, V> lastEntry;
        /* access modifiers changed from: private */
        public int modCount = 0;
        private int size = 0;

        ValueSet(K k, int i) {
            this.key = k;
            this.firstEntry = this;
            this.lastEntry = this;
            this.hashTable = new ValueEntry[Collections2.closedTableSize(i, LinkedHashMultimap.VALUE_SET_LOAD_FACTOR)];
        }

        private int mask() {
            return this.hashTable.length - 1;
        }

        public boolean add(V v) {
            int smearedHash = Collections2.smearedHash(v);
            int mask = mask() & smearedHash;
            ValueEntry<K, V> valueEntry = this.hashTable[mask];
            ValueEntry<K, V> valueEntry2 = valueEntry;
            while (true) {
                boolean z = false;
                if (valueEntry2 == null) {
                    ValueEntry<K, V> valueEntry3 = new ValueEntry<>(this.key, v, smearedHash, valueEntry);
                    LinkedHashMultimap.access$200(this.lastEntry, valueEntry3);
                    LinkedHashMultimap.access$200(valueEntry3, this);
                    ValueEntry predecessorInMultimap = LinkedHashMultimap.this.multimapHeaderEntry.getPredecessorInMultimap();
                    predecessorInMultimap.setSuccessorInMultimap(valueEntry3);
                    valueEntry3.setPredecessorInMultimap(predecessorInMultimap);
                    ValueEntry access$300 = LinkedHashMultimap.this.multimapHeaderEntry;
                    valueEntry3.setSuccessorInMultimap(access$300);
                    access$300.setPredecessorInMultimap(valueEntry3);
                    ValueEntry<K, V>[] valueEntryArr = this.hashTable;
                    valueEntryArr[mask] = valueEntry3;
                    this.size++;
                    this.modCount++;
                    int i = this.size;
                    int length = valueEntryArr.length;
                    if (((double) i) > ((double) length) * LinkedHashMultimap.VALUE_SET_LOAD_FACTOR && length < 1073741824) {
                        z = true;
                    }
                    if (z) {
                        ValueEntry<K, V>[] valueEntryArr2 = new ValueEntry[(this.hashTable.length * 2)];
                        this.hashTable = valueEntryArr2;
                        int length2 = valueEntryArr2.length - 1;
                        for (ValueSetLink valueSetLink = this.firstEntry; valueSetLink != this; valueSetLink = valueSetLink.getSuccessorInValueSet()) {
                            ValueEntry<K, V> valueEntry4 = (ValueEntry) valueSetLink;
                            int i2 = valueEntry4.smearedValueHash & length2;
                            valueEntry4.nextInValueBucket = valueEntryArr2[i2];
                            valueEntryArr2[i2] = valueEntry4;
                        }
                    }
                    return true;
                } else if (valueEntry2.matchesValue(v, smearedHash)) {
                    return false;
                } else {
                    valueEntry2 = valueEntry2.nextInValueBucket;
                }
            }
        }

        public void clear() {
            Arrays.fill(this.hashTable, (Object) null);
            this.size = 0;
            for (ValueSetLink<K, V> valueSetLink = this.firstEntry; valueSetLink != this; valueSetLink = valueSetLink.getSuccessorInValueSet()) {
                ValueEntry valueEntry = (ValueEntry) valueSetLink;
                ValueEntry predecessorInMultimap = valueEntry.getPredecessorInMultimap();
                ValueEntry successorInMultimap = valueEntry.getSuccessorInMultimap();
                predecessorInMultimap.setSuccessorInMultimap(successorInMultimap);
                successorInMultimap.setPredecessorInMultimap(predecessorInMultimap);
            }
            LinkedHashMultimap.access$200(this, this);
            this.modCount++;
        }

        public boolean contains(Object obj) {
            int smearedHash = Collections2.smearedHash(obj);
            for (ValueEntry<K, V> valueEntry = this.hashTable[mask() & smearedHash]; valueEntry != null; valueEntry = valueEntry.nextInValueBucket) {
                if (valueEntry.matchesValue(obj, smearedHash)) {
                    return true;
                }
            }
            return false;
        }

        public void forEach(Consumer<? super V> consumer) {
            if (consumer != null) {
                for (ValueSetLink<K, V> valueSetLink = this.firstEntry; valueSetLink != this; valueSetLink = valueSetLink.getSuccessorInValueSet()) {
                    consumer.accept(((ValueEntry) valueSetLink).getValue());
                }
                return;
            }
            throw new NullPointerException();
        }

        public ValueSetLink<K, V> getPredecessorInValueSet() {
            return this.lastEntry;
        }

        public ValueSetLink<K, V> getSuccessorInValueSet() {
            return this.firstEntry;
        }

        public Iterator<V> iterator() {
            return new Iterator<V>() {
                int expectedModCount = ValueSet.this.modCount;
                ValueSetLink<K, V> nextEntry = ValueSet.this.firstEntry;
                ValueEntry<K, V> toRemove;

                private void checkForComodification() {
                    if (ValueSet.this.modCount != this.expectedModCount) {
                        throw new ConcurrentModificationException();
                    }
                }

                public boolean hasNext() {
                    if (ValueSet.this.modCount == this.expectedModCount) {
                        return this.nextEntry != ValueSet.this;
                    }
                    throw new ConcurrentModificationException();
                }

                public V next() {
                    checkForComodification();
                    if (this.nextEntry != ValueSet.this) {
                        ValueEntry<K, V> valueEntry = (ValueEntry) this.nextEntry;
                        V value = valueEntry.getValue();
                        this.toRemove = valueEntry;
                        this.nextEntry = valueEntry.getSuccessorInValueSet();
                        return value;
                    }
                    throw new NoSuchElementException();
                }

                public void remove() {
                    if (ValueSet.this.modCount == this.expectedModCount) {
                        MoreObjects.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
                        ValueSet.this.remove(this.toRemove.getValue());
                        this.expectedModCount = ValueSet.this.modCount;
                        this.toRemove = null;
                        return;
                    }
                    throw new ConcurrentModificationException();
                }
            };
        }

        public boolean remove(Object obj) {
            int smearedHash = Collections2.smearedHash(obj);
            int mask = mask() & smearedHash;
            ValueEntry<K, V> valueEntry = null;
            for (ValueEntry<K, V> valueEntry2 = this.hashTable[mask]; valueEntry2 != null; valueEntry2 = valueEntry2.nextInValueBucket) {
                if (valueEntry2.matchesValue(obj, smearedHash)) {
                    if (valueEntry == null) {
                        this.hashTable[mask] = valueEntry2.nextInValueBucket;
                    } else {
                        valueEntry.nextInValueBucket = valueEntry2.nextInValueBucket;
                    }
                    LinkedHashMultimap.succeedsInValueSet(valueEntry2.getPredecessorInValueSet(), valueEntry2.getSuccessorInValueSet());
                    ValueEntry<K, V> predecessorInMultimap = valueEntry2.getPredecessorInMultimap();
                    ValueEntry<K, V> successorInMultimap = valueEntry2.getSuccessorInMultimap();
                    predecessorInMultimap.setSuccessorInMultimap(successorInMultimap);
                    successorInMultimap.setPredecessorInMultimap(predecessorInMultimap);
                    this.size--;
                    this.modCount++;
                    return true;
                }
                valueEntry = valueEntry2;
            }
            return false;
        }

        public void setPredecessorInValueSet(ValueSetLink<K, V> valueSetLink) {
            this.lastEntry = valueSetLink;
        }

        public void setSuccessorInValueSet(ValueSetLink<K, V> valueSetLink) {
            this.firstEntry = valueSetLink;
        }

        public int size() {
            return this.size;
        }
    }

    private interface ValueSetLink<K, V> {
        ValueSetLink<K, V> getPredecessorInValueSet();

        ValueSetLink<K, V> getSuccessorInValueSet();

        void setPredecessorInValueSet(ValueSetLink<K, V> valueSetLink);

        void setSuccessorInValueSet(ValueSetLink<K, V> valueSetLink);
    }

    static /* synthetic */ void access$200(ValueSetLink valueSetLink, ValueSetLink valueSetLink2) {
        valueSetLink.setSuccessorInValueSet(valueSetLink2);
        valueSetLink2.setPredecessorInValueSet(valueSetLink);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.multimapHeaderEntry = new ValueEntry<>(null, null, 0, (ValueEntry) null);
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        valueEntry.setSuccessorInMultimap(valueEntry);
        valueEntry.setPredecessorInMultimap(valueEntry);
        this.valueSetCapacity = 2;
        int readInt = objectInputStream.readInt();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            linkedHashMap.put(readObject, createCollection(readObject));
        }
        int readInt2 = objectInputStream.readInt();
        for (int i2 = 0; i2 < readInt2; i2++) {
            ((Collection) linkedHashMap.get(objectInputStream.readObject())).add(objectInputStream.readObject());
        }
        setMap(linkedHashMap);
    }

    /* access modifiers changed from: private */
    public static <K, V> void succeedsInValueSet(ValueSetLink<K, V> valueSetLink, ValueSetLink<K, V> valueSetLink2) {
        valueSetLink.setSuccessorInValueSet(valueSetLink2);
        valueSetLink2.setPredecessorInValueSet(valueSetLink);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(keySet().size());
        for (Object writeObject : keySet()) {
            objectOutputStream.writeObject(writeObject);
        }
        objectOutputStream.writeInt(size());
        for (Map.Entry entry : entries()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    public /* bridge */ /* synthetic */ Map asMap() {
        return super.asMap();
    }

    public void clear() {
        super.clear();
        ValueEntry<K, V> valueEntry = this.multimapHeaderEntry;
        valueEntry.setSuccessorInMultimap(valueEntry);
        valueEntry.setPredecessorInMultimap(valueEntry);
    }

    public boolean containsEntry(Object obj, Object obj2) {
        Collection collection = (Collection) asMap().get(obj);
        return collection != null && collection.contains(obj2);
    }

    public Set<Map.Entry<K, V>> entries() {
        return super.entries();
    }

    /* access modifiers changed from: package-private */
    public Iterator<Map.Entry<K, V>> entryIterator() {
        return new Iterator<Map.Entry<K, V>>() {
            ValueEntry<K, V> nextEntry = LinkedHashMultimap.this.multimapHeaderEntry.successorInMultimap;
            ValueEntry<K, V> toRemove;

            public boolean hasNext() {
                return this.nextEntry != LinkedHashMultimap.this.multimapHeaderEntry;
            }

            public Object next() {
                if (this.nextEntry != LinkedHashMultimap.this.multimapHeaderEntry) {
                    ValueEntry<K, V> valueEntry = this.nextEntry;
                    this.toRemove = valueEntry;
                    this.nextEntry = valueEntry.successorInMultimap;
                    return valueEntry;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                MoreObjects.checkState(this.toRemove != null, "no calls to next() since the last call to remove()");
                LinkedHashMultimap.this.remove(this.toRemove.getKey(), this.toRemove.getValue());
                this.toRemove = null;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public Spliterator<Map.Entry<K, V>> entrySpliterator() {
        return Spliterators.spliterator(entries(), 17);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Multimap) {
            return asMap().equals(((Multimap) obj).asMap());
        }
        return false;
    }

    public /* bridge */ /* synthetic */ Set get(Object obj) {
        return super.get(obj);
    }

    public int hashCode() {
        return asMap().hashCode();
    }

    public Set<K> keySet() {
        return super.keySet();
    }

    public boolean remove(Object obj, Object obj2) {
        Collection collection = (Collection) asMap().get(obj);
        return collection != null && collection.remove(obj2);
    }

    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public String toString() {
        return asMap().toString();
    }

    /* access modifiers changed from: package-private */
    public Set<V> createCollection() {
        return new LinkedHashSet(this.valueSetCapacity);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createCollection(K k) {
        return new ValueSet(k, this.valueSetCapacity);
    }
}
