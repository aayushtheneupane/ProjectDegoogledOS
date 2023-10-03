package com.google.common.collect;

import com.android.tools.p006r8.GeneratedOutlineSupport;
import com.google.common.base.Predicate;
import com.google.common.collect.Cut;
import java.io.Serializable;
import java.lang.Comparable;

public final class Range<C extends Comparable> extends RangeGwtSerializationDependencies implements Predicate<C>, Serializable {
    private static final Range<Comparable> ALL = new Range<>(Cut.belowAll(), Cut.AboveAll.INSTANCE);
    private static final long serialVersionUID = 0;
    final Cut<C> lowerBound;
    final Cut<C> upperBound;

    private Range(Cut<C> cut, Cut<C> cut2) {
        if (cut != null) {
            this.lowerBound = cut;
            if (cut2 != null) {
                this.upperBound = cut2;
                if (cut.compareTo(cut2) > 0 || cut == Cut.AboveAll.INSTANCE || cut2 == Cut.BelowAll.INSTANCE) {
                    StringBuilder outline13 = GeneratedOutlineSupport.outline13("Invalid range: ");
                    StringBuilder sb = new StringBuilder(16);
                    cut.describeAsLowerBound(sb);
                    sb.append("..");
                    cut2.describeAsUpperBound(sb);
                    outline13.append(sb.toString());
                    throw new IllegalArgumentException(outline13.toString());
                }
                return;
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public static <C extends Comparable<?>> Range<C> all() {
        return ALL;
    }

    static int compareOrThrow(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static <C extends Comparable<?>> Range<C> downTo(C c, BoundType boundType) {
        int ordinal = boundType.ordinal();
        if (ordinal == 0) {
            return new Range<>(Cut.aboveValue(c), Cut.AboveAll.INSTANCE);
        }
        if (ordinal == 1) {
            return new Range<>(Cut.belowValue(c), Cut.AboveAll.INSTANCE);
        }
        throw new AssertionError();
    }

    public static <C extends Comparable<?>> Range<C> range(C c, BoundType boundType, C c2, BoundType boundType2) {
        if (boundType == null) {
            throw new NullPointerException();
        } else if (boundType2 != null) {
            return new Range<>(boundType == BoundType.OPEN ? Cut.aboveValue(c) : Cut.belowValue(c), boundType2 == BoundType.OPEN ? Cut.belowValue(c2) : Cut.aboveValue(c2));
        } else {
            throw new NullPointerException();
        }
    }

    public static <C extends Comparable<?>> Range<C> upTo(C c, BoundType boundType) {
        int ordinal = boundType.ordinal();
        if (ordinal == 0) {
            return new Range<>(Cut.belowAll(), new Cut.BelowValue(c));
        }
        if (ordinal == 1) {
            return new Range<>(Cut.belowAll(), new Cut.AboveValue(c));
        }
        throw new AssertionError();
    }

    public boolean contains(C c) {
        if (c != null) {
            return this.lowerBound.isLessThan(c) && !this.upperBound.isLessThan(c);
        }
        throw new NullPointerException();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Range)) {
            return false;
        }
        Range range = (Range) obj;
        if (!this.lowerBound.equals(range.lowerBound) || !this.upperBound.equals(range.upperBound)) {
            return false;
        }
        return true;
    }

    public boolean hasLowerBound() {
        return this.lowerBound != Cut.belowAll();
    }

    public boolean hasUpperBound() {
        return this.upperBound != Cut.aboveAll();
    }

    public int hashCode() {
        return (this.lowerBound.hashCode() * 31) + this.upperBound.hashCode();
    }

    public Range<C> intersection(Range<C> range) {
        int compareTo = this.lowerBound.compareTo(range.lowerBound);
        int compareTo2 = this.upperBound.compareTo(range.upperBound);
        if (compareTo >= 0 && compareTo2 <= 0) {
            return this;
        }
        if (compareTo <= 0 && compareTo2 >= 0) {
            return range;
        }
        return new Range<>(compareTo >= 0 ? this.lowerBound : range.lowerBound, compareTo2 <= 0 ? this.upperBound : range.upperBound);
    }

    public boolean isConnected(Range<C> range) {
        return this.lowerBound.compareTo(range.upperBound) <= 0 && range.lowerBound.compareTo(this.upperBound) <= 0;
    }

    public C lowerEndpoint() {
        return this.lowerBound.endpoint();
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return equals(ALL) ? ALL : this;
    }

    public String toString() {
        Cut<C> cut = this.lowerBound;
        Cut<C> cut2 = this.upperBound;
        StringBuilder sb = new StringBuilder(16);
        cut.describeAsLowerBound(sb);
        sb.append("..");
        cut2.describeAsUpperBound(sb);
        return sb.toString();
    }

    public C upperEndpoint() {
        return this.upperBound.endpoint();
    }

    @Deprecated
    public boolean apply(C c) {
        return contains(c);
    }
}
