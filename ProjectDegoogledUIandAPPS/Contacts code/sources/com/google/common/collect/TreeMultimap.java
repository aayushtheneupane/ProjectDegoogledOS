package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMapBasedMultimap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class TreeMultimap<K, V> extends AbstractSortedKeySortedSetMultimap<K, V> {
    private static final long serialVersionUID = 0;
    private transient Comparator<? super K> keyComparator;
    private transient Comparator<? super V> valueComparator;

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ boolean containsEntry(Object obj, Object obj2) {
        return super.containsEntry(obj, obj2);
    }

    public /* bridge */ /* synthetic */ boolean containsKey(Object obj) {
        return super.containsKey(obj);
    }

    public /* bridge */ /* synthetic */ boolean containsValue(Object obj) {
        return super.containsValue(obj);
    }

    public /* bridge */ /* synthetic */ Set entries() {
        return super.entries();
    }

    public /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public /* bridge */ /* synthetic */ boolean isEmpty() {
        return super.isEmpty();
    }

    public /* bridge */ /* synthetic */ Multiset keys() {
        return super.keys();
    }

    public /* bridge */ /* synthetic */ boolean put(Object obj, Object obj2) {
        return super.put(obj, obj2);
    }

    public /* bridge */ /* synthetic */ boolean putAll(Multimap multimap) {
        return super.putAll(multimap);
    }

    public /* bridge */ /* synthetic */ boolean putAll(Object obj, Iterable iterable) {
        return super.putAll(obj, iterable);
    }

    public /* bridge */ /* synthetic */ boolean remove(Object obj, Object obj2) {
        return super.remove(obj, obj2);
    }

    public /* bridge */ /* synthetic */ SortedSet removeAll(Object obj) {
        return super.removeAll(obj);
    }

    public /* bridge */ /* synthetic */ SortedSet replaceValues(Object obj, Iterable iterable) {
        return super.replaceValues(obj, iterable);
    }

    public /* bridge */ /* synthetic */ int size() {
        return super.size();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public /* bridge */ /* synthetic */ Collection values() {
        return super.values();
    }

    public static <K extends Comparable, V extends Comparable> TreeMultimap<K, V> create() {
        return new TreeMultimap<>(Ordering.natural(), Ordering.natural());
    }

    public static <K, V> TreeMultimap<K, V> create(Comparator<? super K> comparator, Comparator<? super V> comparator2) {
        Preconditions.checkNotNull(comparator);
        Preconditions.checkNotNull(comparator2);
        return new TreeMultimap<>(comparator, comparator2);
    }

    public static <K extends Comparable, V extends Comparable> TreeMultimap<K, V> create(Multimap<? extends K, ? extends V> multimap) {
        return new TreeMultimap<>(Ordering.natural(), Ordering.natural(), multimap);
    }

    TreeMultimap(Comparator<? super K> comparator, Comparator<? super V> comparator2) {
        super(new TreeMap(comparator));
        this.keyComparator = comparator;
        this.valueComparator = comparator2;
    }

    private TreeMultimap(Comparator<? super K> comparator, Comparator<? super V> comparator2, Multimap<? extends K, ? extends V> multimap) {
        this(comparator, comparator2);
        putAll(multimap);
    }

    /* access modifiers changed from: package-private */
    public SortedSet<V> createCollection() {
        return new TreeSet(this.valueComparator);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> createCollection(K k) {
        if (k == null) {
            keyComparator().compare(k, k);
        }
        return super.createCollection(k);
    }

    public Comparator<? super K> keyComparator() {
        return this.keyComparator;
    }

    public Comparator<? super V> valueComparator() {
        return this.valueComparator;
    }

    /* access modifiers changed from: package-private */
    public NavigableMap<K, Collection<V>> backingMap() {
        return (NavigableMap) super.backingMap();
    }

    public NavigableSet<V> get(K k) {
        return (NavigableSet) super.get((Object) k);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> unmodifiableCollectionSubclass(Collection<V> collection) {
        return Sets.unmodifiableNavigableSet((NavigableSet) collection);
    }

    /* access modifiers changed from: package-private */
    public Collection<V> wrapCollection(K k, Collection<V> collection) {
        return new AbstractMapBasedMultimap.WrappedNavigableSet(k, (NavigableSet) collection, (AbstractMapBasedMultimap<K, V>.WrappedCollection) null);
    }

    public NavigableSet<K> keySet() {
        return (NavigableSet) super.keySet();
    }

    /* access modifiers changed from: package-private */
    public NavigableSet<K> createKeySet() {
        return new AbstractMapBasedMultimap.NavigableKeySet(backingMap());
    }

    public NavigableMap<K, Collection<V>> asMap() {
        return (NavigableMap) super.asMap();
    }

    /* access modifiers changed from: package-private */
    public NavigableMap<K, Collection<V>> createAsMap() {
        return new AbstractMapBasedMultimap.NavigableAsMap(backingMap());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(keyComparator());
        objectOutputStream.writeObject(valueComparator());
        Serialization.writeMultimap(this, objectOutputStream);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        Comparator<? super K> comparator = (Comparator) objectInputStream.readObject();
        Preconditions.checkNotNull(comparator);
        this.keyComparator = comparator;
        Comparator<? super V> comparator2 = (Comparator) objectInputStream.readObject();
        Preconditions.checkNotNull(comparator2);
        this.valueComparator = comparator2;
        setMap(new TreeMap(this.keyComparator));
        Serialization.populateMultimap(this, objectInputStream);
    }
}
