package com.google.common.collect;

import java.lang.Comparable;

abstract class AbstractRangeSet<C extends Comparable> implements RangeSet<C> {
    public abstract boolean encloses(Range<C> range);

    public abstract Range<C> rangeContaining(C c);

    AbstractRangeSet() {
    }

    public boolean contains(C c) {
        return rangeContaining(c) != null;
    }

    public boolean isEmpty() {
        return asRanges().isEmpty();
    }

    public void add(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    public void remove(Range<C> range) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        remove(Range.all());
    }

    public boolean enclosesAll(RangeSet<C> rangeSet) {
        for (Range<C> encloses : rangeSet.asRanges()) {
            if (!encloses(encloses)) {
                return false;
            }
        }
        return true;
    }

    public void addAll(RangeSet<C> rangeSet) {
        for (Range<C> add : rangeSet.asRanges()) {
            add(add);
        }
    }

    public void removeAll(RangeSet<C> rangeSet) {
        for (Range<C> remove : rangeSet.asRanges()) {
            remove(remove);
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RangeSet) {
            return asRanges().equals(((RangeSet) obj).asRanges());
        }
        return false;
    }

    public final int hashCode() {
        return asRanges().hashCode();
    }

    public final String toString() {
        return asRanges().toString();
    }
}
