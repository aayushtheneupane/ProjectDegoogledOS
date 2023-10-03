package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.primitives.Ints;

final class RegularImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    private final transient int[] counts;
    private final transient long[] cumulativeCounts;
    private final transient RegularImmutableSortedSet<E> elementSet;
    private final transient int length;
    private final transient int offset;

    RegularImmutableSortedMultiset(RegularImmutableSortedSet<E> regularImmutableSortedSet, int[] iArr, long[] jArr, int i, int i2) {
        this.elementSet = regularImmutableSortedSet;
        this.counts = iArr;
        this.cumulativeCounts = jArr;
        this.offset = i;
        this.length = i2;
    }

    /* access modifiers changed from: package-private */
    public Multiset.Entry<E> getEntry(int i) {
        return Multisets.immutableEntry(this.elementSet.asList().get(i), this.counts[this.offset + i]);
    }

    public Multiset.Entry<E> firstEntry() {
        return getEntry(0);
    }

    public Multiset.Entry<E> lastEntry() {
        return getEntry(this.length - 1);
    }

    public int count(Object obj) {
        int indexOf = this.elementSet.indexOf(obj);
        if (indexOf == -1) {
            return 0;
        }
        return this.counts[indexOf + this.offset];
    }

    public int size() {
        long[] jArr = this.cumulativeCounts;
        int i = this.offset;
        return Ints.saturatedCast(jArr[this.length + i] - jArr[i]);
    }

    public ImmutableSortedSet<E> elementSet() {
        return this.elementSet;
    }

    public ImmutableSortedMultiset<E> headMultiset(E e, BoundType boundType) {
        RegularImmutableSortedSet<E> regularImmutableSortedSet = this.elementSet;
        Preconditions.checkNotNull(boundType);
        return getSubMultiset(0, regularImmutableSortedSet.headIndex(e, boundType == BoundType.CLOSED));
    }

    public ImmutableSortedMultiset<E> tailMultiset(E e, BoundType boundType) {
        RegularImmutableSortedSet<E> regularImmutableSortedSet = this.elementSet;
        Preconditions.checkNotNull(boundType);
        return getSubMultiset(regularImmutableSortedSet.tailIndex(e, boundType == BoundType.CLOSED), this.length);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMultiset<E> getSubMultiset(int i, int i2) {
        Preconditions.checkPositionIndexes(i, i2, this.length);
        if (i == i2) {
            return ImmutableSortedMultiset.emptyMultiset(comparator());
        }
        if (i == 0 && i2 == this.length) {
            return this;
        }
        return new RegularImmutableSortedMultiset((RegularImmutableSortedSet) this.elementSet.getSubSet(i, i2), this.counts, this.cumulativeCounts, this.offset + i, i2 - i);
    }

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return this.offset > 0 || this.length < this.counts.length;
    }
}
