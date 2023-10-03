package com.google.common.collect;

import com.google.common.collect.ImmutableList;
import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.IntFunction;

final class ImmutableSortedAsList<E> extends RegularImmutableAsList<E> implements SortedIterable<E> {
    ImmutableSortedAsList(ImmutableSortedSet<E> immutableSortedSet, ImmutableList<E> immutableList) {
        super(immutableSortedSet, immutableList);
    }

    public Comparator<? super E> comparator() {
        return delegateCollection().comparator();
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public int indexOf(Object obj) {
        int indexOf = delegateCollection().indexOf(obj);
        if (indexOf < 0 || !get(indexOf).equals(obj)) {
            return -1;
        }
        return indexOf;
    }

    public int lastIndexOf(Object obj) {
        return indexOf(obj);
    }

    public Spliterator<E> spliterator() {
        int size = size();
        ImmutableList delegateList = delegateList();
        delegateList.getClass();
        return Collections2.indexed(size, 1301, new IntFunction() {
            public final Object apply(int i) {
                return ImmutableList.this.get(i);
            }
        }, comparator());
    }

    /* access modifiers changed from: package-private */
    public ImmutableList<E> subListUnchecked(int i, int i2) {
        return new RegularImmutableSortedSet(new ImmutableList.SubList(i, i2 - i), comparator()).asList();
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedSet<E> delegateCollection() {
        return (ImmutableSortedSet) super.delegateCollection();
    }
}
