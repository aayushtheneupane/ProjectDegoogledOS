package com.google.common.collect;

import com.google.common.collect.ImmutableList;
import java.util.Comparator;

final class ImmutableSortedAsList extends RegularImmutableAsList implements C1674lb {
    ImmutableSortedAsList(ImmutableSortedSet immutableSortedSet, ImmutableList immutableList) {
        super((ImmutableCollection) immutableSortedSet, immutableList);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: M */
    public ImmutableList mo8720M(int i, int i2) {
        return new RegularImmutableSortedSet(new ImmutableList.SubList(i, i2 - i), comparator()).mo8648Ol();
    }

    public Comparator comparator() {
        return mo8695Ql().comparator();
    }

    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    public int indexOf(Object obj) {
        int indexOf = mo8695Ql().indexOf(obj);
        if (indexOf < 0 || !get(indexOf).equals(obj)) {
            return -1;
        }
        return indexOf;
    }

    public int lastIndexOf(Object obj) {
        return indexOf(obj);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ql */
    public ImmutableSortedSet m4239Ql() {
        return (ImmutableSortedSet) super.mo8695Ql();
    }
}
