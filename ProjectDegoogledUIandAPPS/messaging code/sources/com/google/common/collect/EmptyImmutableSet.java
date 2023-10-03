package com.google.common.collect;

import java.util.Collection;
import java.util.Set;

final class EmptyImmutableSet extends ImmutableSet {
    static final EmptyImmutableSet INSTANCE = new EmptyImmutableSet();
    private static final long serialVersionUID = 0;

    private EmptyImmutableSet() {
    }

    /* renamed from: Ol */
    public ImmutableList mo8648Ol() {
        return ImmutableList.m4204ql();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Rl */
    public boolean mo8649Rl() {
        return true;
    }

    public boolean contains(Object obj) {
        return false;
    }

    public boolean containsAll(Collection collection) {
        return collection.isEmpty();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo8652d(Object[] objArr, int i) {
        return i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Set) {
            return ((Set) obj).isEmpty();
        }
        return false;
    }

    public final int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: pl */
    public boolean mo8636pl() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public Object readResolve() {
        return INSTANCE;
    }

    public int size() {
        return 0;
    }

    public String toString() {
        return "[]";
    }

    public C1692rb iterator() {
        return C1652ea.emptyListIterator();
    }
}
