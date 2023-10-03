package com.google.common.collect;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/* renamed from: com.google.common.collect.n */
class C1678n extends C1672l implements List {
    final /* synthetic */ AbstractMapBasedMultimap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1678n(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, List list, C1672l lVar) {
        super(abstractMapBasedMultimap, obj, list, lVar);
        this.this$0 = abstractMapBasedMultimap;
    }

    public void add(int i, Object obj) {
        mo9197Ml();
        boolean isEmpty = this.delegate.isEmpty();
        ((List) this.delegate).add(i, obj);
        AbstractMapBasedMultimap.m4059b(this.this$0);
        if (isEmpty) {
            mo9196Ll();
        }
    }

    public boolean addAll(int i, Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean addAll = ((List) this.delegate).addAll(i, collection);
        if (addAll) {
            AbstractMapBasedMultimap.m4053a(this.this$0, this.delegate.size() - size);
            if (size == 0) {
                mo9196Ll();
            }
        }
        return addAll;
    }

    public Object get(int i) {
        mo9197Ml();
        return ((List) this.delegate).get(i);
    }

    public int indexOf(Object obj) {
        mo9197Ml();
        return ((List) this.delegate).indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        mo9197Ml();
        return ((List) this.delegate).lastIndexOf(obj);
    }

    public ListIterator listIterator() {
        mo9197Ml();
        return new C1675m(this);
    }

    public Object remove(int i) {
        mo9197Ml();
        Object remove = ((List) this.delegate).remove(i);
        AbstractMapBasedMultimap.m4061c(this.this$0);
        mo9198Nl();
        return remove;
    }

    public Object set(int i, Object obj) {
        mo9197Ml();
        return ((List) this.delegate).set(i, obj);
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [com.google.common.collect.l] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List subList(int r4, int r5) {
        /*
            r3 = this;
            r3.mo9197Ml()
            com.google.common.collect.AbstractMapBasedMultimap r0 = r3.this$0
            java.lang.Object r1 = r3.key
            java.util.Collection r2 = r3.delegate
            java.util.List r2 = (java.util.List) r2
            java.util.List r4 = r2.subList(r4, r5)
            com.google.common.collect.l r5 = r3.f2535FP
            if (r5 != 0) goto L_0x0014
            goto L_0x0015
        L_0x0014:
            r3 = r5
        L_0x0015:
            java.util.List r3 = r0.m4057a(r1, r4, r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.C1678n.subList(int, int):java.util.List");
    }

    public ListIterator listIterator(int i) {
        mo9197Ml();
        return new C1675m(this, i);
    }
}
