package com.google.common.collect;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;

final class TreeRangeSet$RangesByUpperBound extends C1704x {

    /* renamed from: bQ */
    private final NavigableMap f2504bQ;
    /* access modifiers changed from: private */

    /* renamed from: cQ */
    public final Range f2505cQ;

    private TreeRangeSet$RangesByUpperBound(NavigableMap navigableMap, Range range) {
        this.f2504bQ = navigableMap;
        this.f2505cQ = range;
    }

    /* renamed from: c */
    private NavigableMap m4522c(Range range) {
        if (range.mo8948b(this.f2505cQ)) {
            return new TreeRangeSet$RangesByUpperBound(this.f2504bQ, range.mo8947a(this.f2505cQ));
        }
        return ImmutableSortedMap.m4242ql();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Vl */
    public Iterator mo9079Vl() {
        Collection collection;
        if (this.f2505cQ.mo8944Dl()) {
            collection = this.f2504bQ.headMap(this.f2505cQ.mo8946Fl(), false).descendingMap().values();
        } else {
            collection = this.f2504bQ.descendingMap().values();
        }
        C1647cb c = C1652ea.m4578c(collection.iterator());
        if (c.hasNext()) {
            C1649da daVar = (C1649da) c;
            if (this.f2505cQ.upperBound.mo8614c(((Range) daVar.peek()).upperBound)) {
                daVar.next();
            }
        }
        return new C1689qb(this, c);
    }

    /* renamed from: b */
    public NavigableMap tailMap(Cut cut, boolean z) {
        return m4522c(Range.m4425a((Comparable) cut, BoundType.m4074pa(z)));
    }

    public Comparator comparator() {
        return C1644bb.m4563zl();
    }

    public boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: gl */
    public Iterator mo9086gl() {
        Iterator it;
        if (!this.f2505cQ.mo8943Cl()) {
            it = this.f2504bQ.values().iterator();
        } else {
            Map.Entry lowerEntry = this.f2504bQ.lowerEntry(this.f2505cQ.mo8945El());
            if (lowerEntry == null) {
                it = this.f2504bQ.values().iterator();
            } else if (this.f2505cQ.lowerBound.mo8614c(((Range) lowerEntry.getValue()).upperBound)) {
                it = this.f2504bQ.tailMap(lowerEntry.getKey(), true).values().iterator();
            } else {
                it = this.f2504bQ.tailMap(this.f2505cQ.mo8945El(), true).values().iterator();
            }
        }
        return new C1686pb(this, it);
    }

    public boolean isEmpty() {
        if (this.f2505cQ.equals(Range.all())) {
            return this.f2504bQ.isEmpty();
        }
        return !mo9086gl().hasNext();
    }

    public int size() {
        if (this.f2505cQ.equals(Range.all())) {
            return this.f2504bQ.size();
        }
        return C1652ea.m4580e(mo9086gl());
    }

    /* renamed from: a */
    public NavigableMap subMap(Cut cut, boolean z, Cut cut2, boolean z2) {
        return m4522c(Range.m4426a(cut, BoundType.m4074pa(z), cut2, BoundType.m4074pa(z2)));
    }

    public Range get(Object obj) {
        Map.Entry lowerEntry;
        if (obj instanceof Cut) {
            try {
                Cut cut = (Cut) obj;
                if (this.f2505cQ.contains(cut) && (lowerEntry = this.f2504bQ.lowerEntry(cut)) != null && ((Range) lowerEntry.getValue()).upperBound.equals(cut)) {
                    return (Range) lowerEntry.getValue();
                }
            } catch (ClassCastException unused) {
            }
        }
        return null;
    }

    /* renamed from: a */
    public NavigableMap headMap(Cut cut, boolean z) {
        return m4522c(Range.m4427b(cut, BoundType.m4074pa(z)));
    }
}
