package com.google.common.collect;

import java.util.List;
import java.util.ListIterator;

/* renamed from: com.google.common.collect.m */
class C1675m extends C1669k implements ListIterator {
    final /* synthetic */ C1678n this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1675m(C1678n nVar) {
        super(nVar);
        this.this$1 = nVar;
    }

    /* renamed from: To */
    private ListIterator m4599To() {
        mo9192Zk();
        return (ListIterator) this.f2533fN;
    }

    public void add(Object obj) {
        boolean isEmpty = this.this$1.isEmpty();
        m4599To().add(obj);
        AbstractMapBasedMultimap.m4059b(this.this$1.this$0);
        if (isEmpty) {
            this.this$1.mo9196Ll();
        }
    }

    public boolean hasPrevious() {
        return m4599To().hasPrevious();
    }

    public int nextIndex() {
        return m4599To().nextIndex();
    }

    public Object previous() {
        return m4599To().previous();
    }

    public int previousIndex() {
        return m4599To().previousIndex();
    }

    public void set(Object obj) {
        m4599To().set(obj);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1675m(C1678n nVar, int i) {
        super(nVar, ((List) nVar.delegate).listIterator(i));
        this.this$1 = nVar;
    }
}
