package com.google.common.collect;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/* renamed from: com.google.common.collect.k */
class C1669k implements Iterator {

    /* renamed from: fN */
    final Iterator f2533fN;

    /* renamed from: hN */
    final Collection f2534hN = this.this$1.delegate;
    final /* synthetic */ C1672l this$1;

    C1669k(C1672l lVar) {
        this.this$1 = lVar;
        this.f2533fN = lVar.this$0.m4062e(lVar.delegate);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Zk */
    public void mo9192Zk() {
        this.this$1.mo9197Ml();
        if (this.this$1.delegate != this.f2534hN) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean hasNext() {
        mo9192Zk();
        return this.f2533fN.hasNext();
    }

    public Object next() {
        mo9192Zk();
        return this.f2533fN.next();
    }

    public void remove() {
        this.f2533fN.remove();
        AbstractMapBasedMultimap.m4061c(this.this$1.this$0);
        this.this$1.mo9198Nl();
    }

    C1669k(C1672l lVar, Iterator it) {
        this.this$1 = lVar;
        this.f2533fN = it;
    }
}
