package com.google.common.collect;

import java.util.Collection;
import java.util.Set;

final class Maps$2 extends ForwardingSet<E> {
    final /* synthetic */ Set val$set;

    Maps$2(Set set) {
        this.val$set = set;
    }

    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: protected */
    public Object delegate() {
        return this.val$set;
    }

    /* access modifiers changed from: protected */
    /* renamed from: delegate  reason: collision with other method in class */
    public Collection m131delegate() {
        return this.val$set;
    }

    /* access modifiers changed from: protected */
    /* renamed from: delegate  reason: collision with other method in class */
    public Set<E> m132delegate() {
        return this.val$set;
    }
}
