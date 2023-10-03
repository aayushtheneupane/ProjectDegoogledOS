package com.google.common.collect;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Set;

/* renamed from: com.google.common.collect.jb */
abstract class C1668jb extends AbstractSet {
    C1668jb() {
    }

    public boolean removeAll(Collection collection) {
        return C1630W.m4533a((Set) this, collection);
    }

    public boolean retainAll(Collection collection) {
        if (collection != null) {
            return super.retainAll(collection);
        }
        throw new NullPointerException();
    }
}
