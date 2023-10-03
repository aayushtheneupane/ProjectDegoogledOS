package com.google.common.collect;

import com.google.common.base.C1509F;
import com.google.common.collect.Cut;
import java.io.Serializable;
import p026b.p027a.p030b.p031a.C0632a;

public final class Range implements C1509F, Serializable {
    private static final Range ALL = new Range(Cut.m4096kl(), Cut.AboveAll.INSTANCE);
    private static final long serialVersionUID = 0;
    final Cut lowerBound;
    final Cut upperBound;

    static {
        new C1650db();
    }

    private Range(Cut cut, Cut cut2) {
        if (cut.compareTo(cut2) > 0 || cut == Cut.AboveAll.INSTANCE || cut2 == Cut.BelowAll.INSTANCE) {
            StringBuilder Pa = C0632a.m1011Pa("Invalid range: ");
            StringBuilder sb = new StringBuilder(16);
            cut.mo8612a(sb);
            sb.append(8229);
            cut2.mo8613b(sb);
            Pa.append(sb.toString());
            throw new IllegalArgumentException(Pa.toString());
        }
        this.lowerBound = cut;
        if (cut2 != null) {
            this.upperBound = cut2;
            return;
        }
        throw new NullPointerException();
    }

    /* renamed from: a */
    public static Range m4426a(Comparable comparable, BoundType boundType, Comparable comparable2, BoundType boundType2) {
        Cut cut;
        Cut cut2;
        if (boundType == null) {
            throw new NullPointerException();
        } else if (boundType2 != null) {
            if (boundType == BoundType.OPEN) {
                cut = Cut.m4093a(comparable);
            } else {
                cut = Cut.m4094b(comparable);
            }
            if (boundType2 == BoundType.OPEN) {
                cut2 = Cut.m4094b(comparable2);
            } else {
                cut2 = Cut.m4093a(comparable2);
            }
            return new Range(cut, cut2);
        } else {
            throw new NullPointerException();
        }
    }

    public static Range all() {
        return ALL;
    }

    /* renamed from: b */
    public static Range m4427b(Comparable comparable, BoundType boundType) {
        int ordinal = boundType.ordinal();
        if (ordinal == 0) {
            return new Range(Cut.m4096kl(), new Cut.BelowValue(comparable));
        }
        if (ordinal == 1) {
            return new Range(Cut.m4096kl(), new Cut.AboveValue(comparable));
        }
        throw new AssertionError();
    }

    /* renamed from: Cl */
    public boolean mo8943Cl() {
        return this.lowerBound != Cut.m4096kl();
    }

    /* renamed from: Dl */
    public boolean mo8944Dl() {
        return this.upperBound != Cut.m4095jl();
    }

    /* renamed from: El */
    public Comparable mo8945El() {
        return this.lowerBound.mo8617ll();
    }

    /* renamed from: Fl */
    public Comparable mo8946Fl() {
        return this.upperBound.mo8617ll();
    }

    public boolean contains(Comparable comparable) {
        if (comparable != null) {
            return this.lowerBound.mo8614c(comparable) && !this.upperBound.mo8614c(comparable);
        }
        throw new NullPointerException();
    }

    @Deprecated
    /* renamed from: d */
    public boolean apply(Comparable comparable) {
        return contains(comparable);
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

    public int hashCode() {
        return this.upperBound.hashCode() + (this.lowerBound.hashCode() * 31);
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return equals(ALL) ? ALL : this;
    }

    public String toString() {
        Cut cut = this.lowerBound;
        Cut cut2 = this.upperBound;
        StringBuilder sb = new StringBuilder(16);
        cut.mo8612a(sb);
        sb.append(8229);
        cut2.mo8613b(sb);
        return sb.toString();
    }

    /* renamed from: b */
    public boolean mo8948b(Range range) {
        return this.lowerBound.compareTo(range.upperBound) <= 0 && range.lowerBound.compareTo(this.upperBound) <= 0;
    }

    /* renamed from: a */
    public static Range m4425a(Comparable comparable, BoundType boundType) {
        int ordinal = boundType.ordinal();
        if (ordinal == 0) {
            return new Range(Cut.m4093a(comparable), Cut.AboveAll.INSTANCE);
        }
        if (ordinal == 1) {
            return new Range(Cut.m4094b(comparable), Cut.AboveAll.INSTANCE);
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public Range mo8947a(Range range) {
        int a = this.lowerBound.compareTo(range.lowerBound);
        int a2 = this.upperBound.compareTo(range.upperBound);
        if (a >= 0 && a2 <= 0) {
            return this;
        }
        if (a <= 0 && a2 >= 0) {
            return range;
        }
        return new Range(a >= 0 ? this.lowerBound : range.lowerBound, a2 <= 0 ? this.upperBound : range.upperBound);
    }

    /* renamed from: a */
    static int m4424a(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }
}
