package com.google.common.collect;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public final class Collections2 {

    static class FilteredCollection<E> extends AbstractCollection<E> {
        final Predicate<? super E> predicate;
        final Collection<E> unfiltered;

        FilteredCollection(Collection<E> collection, Predicate<? super E> predicate2) {
            this.unfiltered = collection;
            this.predicate = predicate2;
        }

        static /* synthetic */ boolean lambda$retainAll$1(Collection collection, Object obj) {
            return !collection.contains(obj);
        }

        public boolean add(E e) {
            MoreObjects.checkArgument(this.predicate.apply(e));
            return this.unfiltered.add(e);
        }

        public boolean addAll(Collection<? extends E> collection) {
            for (Object apply : collection) {
                MoreObjects.checkArgument(this.predicate.apply(apply));
            }
            return this.unfiltered.addAll(collection);
        }

        public void clear() {
            Collection<E> collection = this.unfiltered;
            Predicate<? super E> predicate2 = this.predicate;
            if (collection instanceof Collection) {
                collection.removeIf(predicate2);
                return;
            }
            Iterator<T> it = collection.iterator();
            if (predicate2 != null) {
                while (it.hasNext()) {
                    if (predicate2.apply(it.next())) {
                        it.remove();
                    }
                }
                return;
            }
            throw new NullPointerException();
        }

        public boolean contains(Object obj) {
            if (Collections2.safeContains(this.unfiltered, obj)) {
                return this.predicate.apply(obj);
            }
            return false;
        }

        public boolean containsAll(Collection<?> collection) {
            for (Object contains : collection) {
                if (!contains(contains)) {
                    return false;
                }
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public FilteredCollection<E> createCombined(Predicate<? super E> predicate2) {
            return new FilteredCollection<>(this.unfiltered, MoreObjects.and(this.predicate, predicate2));
        }

        public void forEach(Consumer<? super E> consumer) {
            if (consumer != null) {
                this.unfiltered.forEach(new Consumer(consumer) {
                    private final /* synthetic */ Consumer f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void accept(Object obj) {
                        Collections2.FilteredCollection.this.lambda$forEach$0$Collections2$FilteredCollection(this.f$1, obj);
                    }
                });
                return;
            }
            throw new NullPointerException();
        }

        public boolean isEmpty() {
            Collection<E> collection = this.unfiltered;
            Predicate<? super E> predicate2 = this.predicate;
            Iterator<T> it = collection.iterator();
            MoreObjects.checkNotNull(predicate2, "predicate");
            boolean z = false;
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (predicate2.apply(it.next())) {
                    break;
                } else {
                    i++;
                }
            }
            if (i != -1) {
                z = true;
            }
            return true ^ z;
        }

        public Iterator<E> iterator() {
            Iterator<E> it = this.unfiltered.iterator();
            Predicate<? super E> predicate2 = this.predicate;
            if (it == null) {
                throw new NullPointerException();
            } else if (predicate2 != null) {
                return new Iterators$5(it, predicate2);
            } else {
                throw new NullPointerException();
            }
        }

        public /* synthetic */ void lambda$forEach$0$Collections2$FilteredCollection(Consumer consumer, Object obj) {
            if (this.predicate.test(obj)) {
                consumer.accept(obj);
            }
        }

        public /* synthetic */ boolean lambda$removeIf$2$Collections2$FilteredCollection(java.util.function.Predicate predicate2, Object obj) {
            return this.predicate.apply(obj) && predicate2.test(obj);
        }

        public boolean remove(Object obj) {
            if (!(Collections2.safeContains(this.unfiltered, obj) ? this.predicate.apply(obj) : false) || !this.unfiltered.remove(obj)) {
                return false;
            }
            return true;
        }

        public boolean removeAll(Collection<?> collection) {
            collection.getClass();
            return this.unfiltered.removeIf(new java.util.function.Predicate(new java.util.function.Predicate(collection) {
                private final /* synthetic */ Collection f$0;

                {
                    this.f$0 = r1;
                }

                public final boolean test(Object obj) {
                    return this.f$0.contains(obj);
                }
            }) {
                private final /* synthetic */ java.util.function.Predicate f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean test(Object obj) {
                    return Collections2.FilteredCollection.this.lambda$removeIf$2$Collections2$FilteredCollection(this.f$1, obj);
                }
            });
        }

        public boolean removeIf(java.util.function.Predicate<? super E> predicate2) {
            if (predicate2 != null) {
                return this.unfiltered.removeIf(new java.util.function.Predicate(predicate2) {
                    private final /* synthetic */ java.util.function.Predicate f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final boolean test(Object obj) {
                        return Collections2.FilteredCollection.this.lambda$removeIf$2$Collections2$FilteredCollection(this.f$1, obj);
                    }
                });
            }
            throw new NullPointerException();
        }

        public boolean retainAll(Collection<?> collection) {
            return this.unfiltered.removeIf(new java.util.function.Predicate(new java.util.function.Predicate(collection) {
                private final /* synthetic */ Collection f$0;

                {
                    this.f$0 = r1;
                }

                public final boolean test(Object obj) {
                    return Collections2.FilteredCollection.lambda$retainAll$1(this.f$0, obj);
                }
            }) {
                private final /* synthetic */ java.util.function.Predicate f$1;

                {
                    this.f$1 = r2;
                }

                public final boolean test(Object obj) {
                    return Collections2.FilteredCollection.this.lambda$removeIf$2$Collections2$FilteredCollection(this.f$1, obj);
                }
            });
        }

        public int size() {
            int i = 0;
            for (E apply : this.unfiltered) {
                if (this.predicate.apply(apply)) {
                    i++;
                }
            }
            return i;
        }

        public Spliterator<E> spliterator() {
            return Collections2.filter(this.unfiltered.spliterator(), this.predicate);
        }

        public Object[] toArray() {
            return Lists.newArrayList(iterator()).toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            return Lists.newArrayList(iterator()).toArray(tArr);
        }
    }

    static class TransformedCollection<F, T> extends AbstractCollection<T> {
        final Collection<F> fromCollection;
        final Function<? super F, ? extends T> function;

        TransformedCollection(Collection<F> collection, Function<? super F, ? extends T> function2) {
            if (collection != null) {
                this.fromCollection = collection;
                if (function2 != null) {
                    this.function = function2;
                    return;
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }

        public void clear() {
            this.fromCollection.clear();
        }

        public void forEach(Consumer<? super T> consumer) {
            if (consumer != null) {
                this.fromCollection.forEach(new Consumer(consumer) {
                    private final /* synthetic */ Consumer f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void accept(Object obj) {
                        Collections2.TransformedCollection.this.lambda$forEach$0$Collections2$TransformedCollection(this.f$1, obj);
                    }
                });
                return;
            }
            throw new NullPointerException();
        }

        public boolean isEmpty() {
            return this.fromCollection.isEmpty();
        }

        public Iterator<T> iterator() {
            return Collections2.transform(this.fromCollection.iterator(), this.function);
        }

        public /* synthetic */ void lambda$forEach$0$Collections2$TransformedCollection(Consumer consumer, Object obj) {
            consumer.accept(this.function.apply(obj));
        }

        public /* synthetic */ boolean lambda$removeIf$1$Collections2$TransformedCollection(java.util.function.Predicate predicate, Object obj) {
            return predicate.test(this.function.apply(obj));
        }

        public boolean removeIf(java.util.function.Predicate<? super T> predicate) {
            if (predicate != null) {
                return this.fromCollection.removeIf(new java.util.function.Predicate(predicate) {
                    private final /* synthetic */ java.util.function.Predicate f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final boolean test(Object obj) {
                        return Collections2.TransformedCollection.this.lambda$removeIf$1$Collections2$TransformedCollection(this.f$1, obj);
                    }
                });
            }
            throw new NullPointerException();
        }

        public int size() {
            return this.fromCollection.size();
        }

        public Spliterator<T> spliterator() {
            return Collections2.map(this.fromCollection.spliterator(), this.function);
        }
    }

    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> it) {
        if (collection == null) {
            throw new NullPointerException();
        } else if (it != null) {
            boolean z = false;
            while (it.hasNext()) {
                z |= collection.add(it.next());
            }
            return z;
        } else {
            throw new NullPointerException();
        }
    }

    public static <K, V> Map<K, V> asMap(Set<K> set, Function<? super K, V> function) {
        return new Maps$AsMapView(set, function);
    }

    static int capacity(int i) {
        if (i < 3) {
            checkNonnegative(i, "expectedSize");
            return i + 1;
        } else if (i < 1073741824) {
            return (int) ((((float) i) / 0.75f) + 1.0f);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    static Object checkElementNotNull(Object obj, int i) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(GeneratedOutlineSupport.outline5("at index ", i));
    }

    static Object[] checkElementsNotNull(Object... objArr) {
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            checkElementNotNull(objArr[i], i);
        }
        return objArr;
    }

    static void checkEntryNotNull(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException(GeneratedOutlineSupport.outline6("null key in entry: null=", obj2));
        } else if (obj2 == null) {
            throw new NullPointerException(GeneratedOutlineSupport.outline7("null value in entry: ", obj, "=null"));
        }
    }

    static int checkNonnegative(int i, String str) {
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException(str + " cannot be negative but was: " + i);
    }

    static void clear(Iterator<?> it) {
        if (it != null) {
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            return;
        }
        throw new NullPointerException();
    }

    static int closedTableSize(int i, double d) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max <= ((int) (d * ((double) highestOneBit)))) {
            return highestOneBit;
        }
        int i2 = highestOneBit << 1;
        if (i2 > 0) {
            return i2;
        }
        return 1073741824;
    }

    static boolean equalsImpl(Set<?> set, Object obj) {
        if (set == obj) {
            return true;
        }
        if (obj instanceof Set) {
            Set set2 = (Set) obj;
            try {
                if (set.size() != set2.size() || !set.containsAll(set2)) {
                    return false;
                }
                return true;
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    public static <E> Collection<E> filter(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof FilteredCollection) {
            return ((FilteredCollection) collection).createCombined(predicate);
        }
        if (collection == null) {
            throw new NullPointerException();
        } else if (predicate != null) {
            return new FilteredCollection(collection, predicate);
        } else {
            throw new NullPointerException();
        }
    }

    public static <K, V> Map<K, V> filterKeys(Map<K, V> map, Predicate<? super K> predicate) {
        if (predicate != null) {
            Predicate<A> compose = MoreObjects.compose(predicate, keyFunction());
            if (map instanceof Maps$AbstractFilteredMap) {
                Maps$AbstractFilteredMap maps$AbstractFilteredMap = (Maps$AbstractFilteredMap) map;
                return new Maps$FilteredEntryMap(maps$AbstractFilteredMap.unfiltered, MoreObjects.and(maps$AbstractFilteredMap.predicate, compose));
            } else if (map != null) {
                return new Maps$FilteredKeyMap(map, predicate, compose);
            } else {
                throw new NullPointerException();
            }
        } else {
            throw new NullPointerException();
        }
    }

    static <F, T> Spliterator<T> flatMap(Spliterator<F> spliterator, java.util.function.Function<? super F, Spliterator<T>> function, int i, long j) {
        boolean z = true;
        MoreObjects.checkArgument((i & 16384) == 0, "flatMap does not support SUBSIZED characteristic");
        if ((i & 4) != 0) {
            z = false;
        }
        MoreObjects.checkArgument(z, "flatMap does not support SORTED characteristic");
        if (spliterator == null) {
            throw new NullPointerException();
        } else if (function != null) {
            return new CollectSpliterators$1FlatMapSpliterator((Spliterator) null, spliterator, i, j, function);
        } else {
            throw new NullPointerException();
        }
    }

    @SafeVarargs
    public static <T> UnmodifiableIterator<T> forArray(T... tArr) {
        return forArray(tArr, 0, tArr.length, 0);
    }

    public static <T> T getNext(Iterator<? extends T> it, T t) {
        return it.hasNext() ? it.next() : t;
    }

    public static <T> T getOnlyElement(Iterable<T> iterable) {
        Iterator<T> it = iterable.iterator();
        T next = it.next();
        if (!it.hasNext()) {
            return next;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("expected one element but was: <");
        sb.append(next);
        for (int i = 0; i < 4 && it.hasNext(); i++) {
            sb.append(", ");
            sb.append(it.next());
        }
        if (it.hasNext()) {
            sb.append(", ...");
        }
        sb.append('>');
        throw new IllegalArgumentException(sb.toString());
    }

    public static boolean hasSameComparator(Comparator<?> comparator, Iterable<?> iterable) {
        Object obj;
        if (comparator == null) {
            throw new NullPointerException();
        } else if (iterable != null) {
            if (iterable instanceof SortedSet) {
                obj = ((SortedSet) iterable).comparator();
                if (obj == null) {
                    obj = Ordering.natural();
                }
            } else if (!(iterable instanceof SortedIterable)) {
                return false;
            } else {
                obj = ((SortedIterable) iterable).comparator();
            }
            return comparator.equals(obj);
        } else {
            throw new NullPointerException();
        }
    }

    static int hashCodeImpl(Set<?> set) {
        Iterator<?> it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    static <T> Spliterator<T> indexed(int i, int i2, IntFunction<T> intFunction) {
        return indexed(i, i2, intFunction, (Comparator) null);
    }

    static <K> Function<Map.Entry<K, ?>, K> keyFunction() {
        return Maps$EntryFunction.KEY;
    }

    static <K> K keyOrNull(Map.Entry<K, ?> entry) {
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    /* access modifiers changed from: package-private */
    public static <F, T> Spliterator<T> map(Spliterator<F> spliterator, java.util.function.Function<? super F, ? extends T> function) {
        if (spliterator == null) {
            throw new NullPointerException();
        } else if (function != null) {
            return new CollectSpliterators$1(spliterator, function);
        } else {
            throw new NullPointerException();
        }
    }

    public static <T> Iterable<List<T>> partition(Iterable<T> iterable, int i) {
        if (iterable != null) {
            MoreObjects.checkArgument(i > 0);
            return new Iterables$2(iterable, i);
        }
        throw new NullPointerException();
    }

    public static <T> PeekingIterator<T> peekingIterator(Iterator<? extends T> it) {
        if (it instanceof Iterators$PeekingImpl) {
            return (Iterators$PeekingImpl) it;
        }
        return new Iterators$PeekingImpl(it);
    }

    static <T> T pollNext(Iterator<T> it) {
        if (!it.hasNext()) {
            return null;
        }
        T next = it.next();
        it.remove();
        return next;
    }

    static boolean removeAllImpl(Set<?> set, Iterator<?> it) {
        boolean z = false;
        while (it.hasNext()) {
            z |= set.remove(it.next());
        }
        return z;
    }

    static boolean safeContains(Collection<?> collection, Object obj) {
        if (collection != null) {
            try {
                return collection.contains(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        } else {
            throw new NullPointerException();
        }
    }

    static <V> V safeGet(Map<?, V> map, Object obj) {
        if (map != null) {
            try {
                return map.get(obj);
            } catch (ClassCastException | NullPointerException unused) {
                return null;
            }
        } else {
            throw new NullPointerException();
        }
    }

    static int smear(int i) {
        return (int) (((long) Integer.rotateLeft((int) (((long) i) * -862048943), 15)) * 461845907);
    }

    static int smearedHash(Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Iterable<?>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.Object[] toArray(java.lang.Iterable<?> r1) {
        /*
            boolean r0 = r1 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0007
            java.util.Collection r1 = (java.util.Collection) r1
            goto L_0x000f
        L_0x0007:
            java.util.Iterator r1 = r1.iterator()
            java.util.ArrayList r1 = com.google.common.collect.Lists.newArrayList(r1)
        L_0x000f:
            java.lang.Object[] r1 = r1.toArray()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Collections2.toArray(java.lang.Iterable):java.lang.Object[]");
    }

    public static String toString(Iterator<?> it) {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        boolean z = true;
        while (it.hasNext()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(it.next());
        }
        sb.append(']');
        return sb.toString();
    }

    static String toStringImpl(Map<?, ?> map) {
        int size = map.size();
        checkNonnegative(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(((long) size) * 8, 1073741824));
        sb.append('{');
        boolean z = true;
        for (Map.Entry next : map.entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            z = false;
            sb.append(next.getKey());
            sb.append('=');
            sb.append(next.getValue());
        }
        sb.append('}');
        return sb.toString();
    }

    public static <F, T> Iterator<T> transform(Iterator<F> it, Function<? super F, ? extends T> function) {
        if (function != null) {
            return new Iterators$6(it, function);
        }
        throw new NullPointerException();
    }

    public static <K, V1, V2> Map<K, V2> transformEntries(Map<K, V1> map, Maps$EntryTransformer<? super K, ? super V1, V2> maps$EntryTransformer) {
        return new Maps$TransformedEntriesMap(map, maps$EntryTransformer);
    }

    public static <T> UnmodifiableIterator<T> unmodifiableIterator(Iterator<? extends T> it) {
        if (it == null) {
            throw new NullPointerException();
        } else if (it instanceof UnmodifiableIterator) {
            return (UnmodifiableIterator) it;
        } else {
            return new Iterators$1(it);
        }
    }

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        return ((navigableSet instanceof ImmutableSortedSet) || (navigableSet instanceof Sets$UnmodifiableNavigableSet)) ? navigableSet : new Sets$UnmodifiableNavigableSet(navigableSet);
    }

    static <T> UnmodifiableListIterator<T> forArray(T[] tArr, int i, int i2, int i3) {
        MoreObjects.checkArgument(i2 >= 0);
        MoreObjects.checkPositionIndexes(i, i + i2, tArr.length);
        MoreObjects.checkPositionIndex(i3, i2);
        if (i2 == 0) {
            return Iterators$ArrayItr.EMPTY;
        }
        return new Iterators$ArrayItr(tArr, i, i2, i3);
    }

    static <T> Spliterator<T> indexed(int i, int i2, IntFunction<T> intFunction, Comparator<? super T> comparator) {
        if (comparator != null) {
            MoreObjects.checkArgument((i2 & 4) != 0);
        }
        return new CollectSpliterators$1WithCharacteristics(IntStream.range(0, i).mapToObj(intFunction).spliterator(), i2, comparator);
    }

    static boolean removeAllImpl(Set<?> set, Collection<?> collection) {
        if (collection != null) {
            if (collection instanceof Multiset) {
                collection = ((Multiset) collection).elementSet();
            }
            if (!(collection instanceof Set) || collection.size() <= set.size()) {
                return removeAllImpl(set, collection.iterator());
            }
            Iterator<?> it = set.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }
        throw new NullPointerException();
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.Iterable<? extends T>, java.lang.Iterable] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> T[] toArray(java.lang.Iterable<? extends T> r1, T[] r2) {
        /*
            boolean r0 = r1 instanceof java.util.Collection
            if (r0 == 0) goto L_0x0007
            java.util.Collection r1 = (java.util.Collection) r1
            goto L_0x000f
        L_0x0007:
            java.util.Iterator r1 = r1.iterator()
            java.util.ArrayList r1 = com.google.common.collect.Lists.newArrayList(r1)
        L_0x000f:
            java.lang.Object[] r1 = r1.toArray(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.Collections2.toArray(java.lang.Iterable, java.lang.Object[]):java.lang.Object[]");
    }

    static <T> Spliterator<T> filter(Spliterator<T> spliterator, java.util.function.Predicate<? super T> predicate) {
        if (spliterator == null) {
            throw new NullPointerException();
        } else if (predicate != null) {
            return new CollectSpliterators$1Splitr(spliterator, predicate);
        } else {
            throw new NullPointerException();
        }
    }

    public static <E> Set<E> filter(Set<E> set, Predicate<? super E> predicate) {
        if (set instanceof SortedSet) {
            SortedSet sortedSet = (SortedSet) set;
            if (sortedSet instanceof Sets$FilteredSet) {
                Sets$FilteredSet sets$FilteredSet = (Sets$FilteredSet) sortedSet;
                return new Sets$FilteredSortedSet((SortedSet) sets$FilteredSet.unfiltered, MoreObjects.and(sets$FilteredSet.predicate, predicate));
            } else if (sortedSet == null) {
                throw new NullPointerException();
            } else if (predicate != null) {
                return new Sets$FilteredSortedSet(sortedSet, predicate);
            } else {
                throw new NullPointerException();
            }
        } else if (set instanceof Sets$FilteredSet) {
            Sets$FilteredSet sets$FilteredSet2 = (Sets$FilteredSet) set;
            return new Sets$FilteredSet((Set) sets$FilteredSet2.unfiltered, MoreObjects.and(sets$FilteredSet2.predicate, predicate));
        } else if (set == null) {
            throw new NullPointerException();
        } else if (predicate != null) {
            return new Sets$FilteredSet(set, predicate);
        } else {
            throw new NullPointerException();
        }
    }
}
