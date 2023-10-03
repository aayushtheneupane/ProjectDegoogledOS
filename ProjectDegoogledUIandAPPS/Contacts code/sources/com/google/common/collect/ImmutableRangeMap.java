package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.SortedLists;
import java.lang.Comparable;
import java.util.Map;
import java.util.NoSuchElementException;

public class ImmutableRangeMap<K extends Comparable<?>, V> implements RangeMap<K, V> {
    private static final ImmutableRangeMap<Comparable<?>, Object> EMPTY = new ImmutableRangeMap<>(ImmutableList.m19of(), ImmutableList.m19of());
    /* access modifiers changed from: private */
    public final ImmutableList<Range<K>> ranges;
    private final ImmutableList<V> values;

    /* renamed from: of */
    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> m57of() {
        return EMPTY;
    }

    /* renamed from: of */
    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> m58of(Range<K> range, V v) {
        return new ImmutableRangeMap<>(ImmutableList.m20of(range), ImmutableList.m20of(v));
    }

    public static <K extends Comparable<?>, V> ImmutableRangeMap<K, V> copyOf(RangeMap<K, ? extends V> rangeMap) {
        if (rangeMap instanceof ImmutableRangeMap) {
            return (ImmutableRangeMap) rangeMap;
        }
        Map<Range<K>, ? extends V> asMapOfRanges = rangeMap.asMapOfRanges();
        ImmutableList.Builder builder = new ImmutableList.Builder(asMapOfRanges.size());
        ImmutableList.Builder builder2 = new ImmutableList.Builder(asMapOfRanges.size());
        for (Map.Entry next : asMapOfRanges.entrySet()) {
            builder.add(next.getKey());
            builder2.add(next.getValue());
        }
        return new ImmutableRangeMap<>(builder.build(), builder2.build());
    }

    public static <K extends Comparable<?>, V> Builder<K, V> builder() {
        return new Builder<>();
    }

    public static final class Builder<K extends Comparable<?>, V> {
        private final RangeSet<K> keyRanges = TreeRangeSet.create();
        private final RangeMap<K, V> rangeMap = TreeRangeMap.create();

        public Builder<K, V> put(Range<K> range, V v) {
            Preconditions.checkNotNull(range);
            Preconditions.checkNotNull(v);
            Preconditions.checkArgument(!range.isEmpty(), "Range must not be empty, but was %s", range);
            if (!this.keyRanges.complement().encloses(range)) {
                for (Map.Entry next : this.rangeMap.asMapOfRanges().entrySet()) {
                    Range range2 = (Range) next.getKey();
                    if (range2.isConnected(range) && !range2.intersection(range).isEmpty()) {
                        throw new IllegalArgumentException("Overlapping ranges: range " + range + " overlaps with entry " + next);
                    }
                }
            }
            this.keyRanges.add(range);
            this.rangeMap.put(range, v);
            return this;
        }

        public Builder<K, V> putAll(RangeMap<K, ? extends V> rangeMap2) {
            for (Map.Entry next : rangeMap2.asMapOfRanges().entrySet()) {
                put((Range) next.getKey(), next.getValue());
            }
            return this;
        }

        public ImmutableRangeMap<K, V> build() {
            Map<Range<K>, V> asMapOfRanges = this.rangeMap.asMapOfRanges();
            ImmutableList.Builder builder = new ImmutableList.Builder(asMapOfRanges.size());
            ImmutableList.Builder builder2 = new ImmutableList.Builder(asMapOfRanges.size());
            for (Map.Entry next : asMapOfRanges.entrySet()) {
                builder.add(next.getKey());
                builder2.add(next.getValue());
            }
            return new ImmutableRangeMap<>(builder.build(), builder2.build());
        }
    }

    ImmutableRangeMap(ImmutableList<Range<K>> immutableList, ImmutableList<V> immutableList2) {
        this.ranges = immutableList;
        this.values = immutableList2;
    }

    public V get(K k) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(k), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (binarySearch != -1 && this.ranges.get(binarySearch).contains(k)) {
            return this.values.get(binarySearch);
        }
        return null;
    }

    public Map.Entry<Range<K>, V> getEntry(K k) {
        int binarySearch = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), Cut.belowValue(k), SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_LOWER);
        if (binarySearch == -1) {
            return null;
        }
        Range range = this.ranges.get(binarySearch);
        if (range.contains(k)) {
            return Maps.immutableEntry(range, this.values.get(binarySearch));
        }
        return null;
    }

    public Range<K> span() {
        if (!this.ranges.isEmpty()) {
            ImmutableList<Range<K>> immutableList = this.ranges;
            return Range.create(this.ranges.get(0).lowerBound, immutableList.get(immutableList.size() - 1).upperBound);
        }
        throw new NoSuchElementException();
    }

    public void put(Range<K> range, V v) {
        throw new UnsupportedOperationException();
    }

    public void putAll(RangeMap<K, V> rangeMap) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        throw new UnsupportedOperationException();
    }

    public void remove(Range<K> range) {
        throw new UnsupportedOperationException();
    }

    public ImmutableMap<Range<K>, V> asMapOfRanges() {
        if (this.ranges.isEmpty()) {
            return ImmutableMap.m38of();
        }
        return new RegularImmutableSortedMap(new RegularImmutableSortedSet(this.ranges, Range.RANGE_LEX_ORDERING), this.values);
    }

    public ImmutableRangeMap<K, V> subRangeMap(final Range<K> range) {
        Preconditions.checkNotNull(range);
        if (range.isEmpty()) {
            return m57of();
        }
        if (this.ranges.isEmpty() || range.encloses(span())) {
            return this;
        }
        final int binarySearch = SortedLists.binarySearch(this.ranges, Range.upperBoundFn(), range.lowerBound, SortedLists.KeyPresentBehavior.FIRST_AFTER, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        int binarySearch2 = SortedLists.binarySearch(this.ranges, Range.lowerBoundFn(), range.upperBound, SortedLists.KeyPresentBehavior.ANY_PRESENT, SortedLists.KeyAbsentBehavior.NEXT_HIGHER);
        if (binarySearch >= binarySearch2) {
            return m57of();
        }
        final int i = binarySearch2 - binarySearch;
        final Range<K> range2 = range;
        return new ImmutableRangeMap<K, V>(new ImmutableList<Range<K>>() {
            /* access modifiers changed from: package-private */
            public boolean isPartialView() {
                return true;
            }

            public int size() {
                return i;
            }

            public Range<K> get(int i) {
                Preconditions.checkElementIndex(i, i);
                if (i == 0 || i == i - 1) {
                    return ((Range) ImmutableRangeMap.this.ranges.get(i + binarySearch)).intersection(range);
                }
                return (Range) ImmutableRangeMap.this.ranges.get(i + binarySearch);
            }
        }, this.values.subList(binarySearch, binarySearch2)) {
            public ImmutableRangeMap<K, V> subRangeMap(Range<K> range) {
                if (range2.isConnected(range)) {
                    return this.subRangeMap(range.intersection(range2));
                }
                return ImmutableRangeMap.m57of();
            }
        };
    }

    public int hashCode() {
        return asMapOfRanges().hashCode();
    }

    public boolean equals(Object obj) {
        if (obj instanceof RangeMap) {
            return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
        }
        return false;
    }

    public String toString() {
        return asMapOfRanges().toString();
    }
}
