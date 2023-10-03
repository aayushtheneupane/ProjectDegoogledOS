package com.google.common.collect;

import java.util.Collection;
import java.util.Set;

/* renamed from: com.google.common.collect.o */
class C1681o extends C1672l implements Set {
    final /* synthetic */ AbstractMapBasedMultimap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1681o(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, Set set) {
        super(abstractMapBasedMultimap, obj, set, (C1672l) null);
        this.this$0 = abstractMapBasedMultimap;
    }

    public boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        int size = size();
        boolean a = C1630W.m4533a((Set) this.delegate, collection);
        if (a) {
            AbstractMapBasedMultimap.m4053a(this.this$0, this.delegate.size() - size);
            mo9198Nl();
        }
        return a;
    }
}
