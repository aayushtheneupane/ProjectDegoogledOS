package com.google.common.collect;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableSet;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

public abstract class ImmutableSortedSet<E> extends ImmutableSortedSetFauxverideShim<E> implements NavigableSet<E>, SortedIterable<E> {
    final transient Comparator<? super E> comparator;
    transient ImmutableSortedSet<E> descendingSet;

    public static final class Builder<E> extends ImmutableSet.Builder<E> {
        private final Comparator<? super E> comparator;

        public Builder(Comparator<? super E> comparator2) {
            if (comparator2 != null) {
                this.comparator = comparator2;
                return;
            }
            throw new NullPointerException();
        }

        public ImmutableCollection.Builder add(Object obj) {
            super.add(obj);
            return this;
        }

        /* renamed from: add  reason: collision with other method in class */
        public ImmutableSet.Builder m130add(Object obj) {
            super.add(obj);
            return this;
        }

        public ImmutableSortedSet<E> build() {
            ImmutableSortedSet<E> construct = ImmutableSortedSet.construct(this.comparator, this.size, this.contents);
            this.size = construct.size();
            return construct;
        }

        public Builder<E> add(E... eArr) {
            super.add(eArr);
            return this;
        }
    }

    private static class SerializedForm<E> implements Serializable {
        private static final long serialVersionUID = 0;
        final Comparator<? super E> comparator;
        final Object[] elements;

        public SerializedForm(Comparator<? super E> comparator2, Object[] objArr) {
            this.comparator = comparator2;
            this.elements = objArr;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            Builder builder = new Builder(this.comparator);
            builder.add((E[]) this.elements);
            return builder.build();
        }
    }

    ImmutableSortedSet(Comparator<? super E> comparator2) {
        this.comparator = comparator2;
    }

    static <E> ImmutableSortedSet<E> construct(Comparator<? super E> comparator2, int i, E... eArr) {
        if (i == 0) {
            return emptySet(comparator2);
        }
        for (int i2 = 0; i2 < i; i2++) {
            Collections2.checkElementNotNull(eArr[i2], i2);
        }
        Arrays.sort(eArr, 0, i, comparator2);
        int i3 = 1;
        for (int i4 = 1; i4 < i; i4++) {
            E e = eArr[i4];
            if (comparator2.compare(e, eArr[i3 - 1]) != 0) {
                eArr[i3] = e;
                i3++;
            }
        }
        Arrays.fill(eArr, i3, i, (Object) null);
        return new RegularImmutableSortedSet(ImmutableList.asImmutableList(eArr, i3), comparator2);
    }

    static <E> RegularImmutableSortedSet<E> emptySet(Comparator<? super E> comparator2) {
        if (Ordering.natural().equals(comparator2)) {
            return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
        }
        return new RegularImmutableSortedSet<>(ImmutableList.m74of(), comparator2);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public E ceiling(E e) {
        return Collections2.getNext(tailSet(e, true).iterator(), null);
    }

    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> createDescendingSet();

    public abstract UnmodifiableIterator<E> descendingIterator();

    public E first() {
        return iterator().next();
    }

    public E floor(E e) {
        return Collections2.getNext(headSet(e, true).descendingIterator(), null);
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> headSetImpl(E e, boolean z);

    public E higher(E e) {
        return Collections2.getNext(tailSet(e, false).iterator(), null);
    }

    /* access modifiers changed from: package-private */
    public abstract int indexOf(Object obj);

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return iterator();
    }

    public E last() {
        return descendingIterator().next();
    }

    public E lower(E e) {
        return Collections2.getNext(headSet(e, false).descendingIterator(), null);
    }

    @Deprecated
    public final E pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final E pollLast() {
        throw new UnsupportedOperationException();
    }

    public Spliterator<E> spliterator() {
        return new Spliterators.AbstractSpliterator<E>((long) size(), 1365) {
            final UnmodifiableIterator<E> iterator = ImmutableSortedSet.this.iterator();

            public Comparator<? super E> getComparator() {
                return ImmutableSortedSet.this.comparator;
            }

            public boolean tryAdvance(Consumer<? super E> consumer) {
                if (!this.iterator.hasNext()) {
                    return false;
                }
                consumer.accept(this.iterator.next());
                return true;
            }
        };
    }

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> subSetImpl(E e, boolean z, E e2, boolean z2);

    /* access modifiers changed from: package-private */
    public abstract ImmutableSortedSet<E> tailSetImpl(E e, boolean z);

    /* access modifiers changed from: package-private */
    public int unsafeCompare(Object obj, Object obj2) {
        return this.comparator.compare(obj, obj2);
    }

    /* access modifiers changed from: package-private */
    public Object writeReplace() {
        return new SerializedForm(this.comparator, toArray());
    }

    public ImmutableSortedSet<E> descendingSet() {
        ImmutableSortedSet<E> immutableSortedSet = this.descendingSet;
        if (immutableSortedSet != null) {
            return immutableSortedSet;
        }
        ImmutableSortedSet<E> createDescendingSet = createDescendingSet();
        this.descendingSet = createDescendingSet;
        createDescendingSet.descendingSet = this;
        return createDescendingSet;
    }

    public ImmutableSortedSet<E> headSet(E e) {
        return headSet(e, false);
    }

    public ImmutableSortedSet<E> subSet(E e, E e2) {
        return subSet(e, true, e2, false);
    }

    public ImmutableSortedSet<E> tailSet(E e) {
        return tailSet(e, true);
    }

    public ImmutableSortedSet<E> headSet(E e, boolean z) {
        if (e != null) {
            return headSetImpl(e, z);
        }
        throw new NullPointerException();
    }

    public ImmutableSortedSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        if (e == null) {
            throw new NullPointerException();
        } else if (e2 != null) {
            MoreObjects.checkArgument(this.comparator.compare(e, e2) <= 0);
            return subSetImpl(e, z, e2, z2);
        } else {
            throw new NullPointerException();
        }
    }

    public ImmutableSortedSet<E> tailSet(E e, boolean z) {
        if (e != null) {
            return tailSetImpl(e, z);
        }
        throw new NullPointerException();
    }
}
