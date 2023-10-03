package com.google.common.collect;

import java.util.Comparator;
import java.util.SortedSet;

/* renamed from: com.google.common.collect.p */
class C1684p extends C1672l implements SortedSet {
    final /* synthetic */ AbstractMapBasedMultimap this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    C1684p(AbstractMapBasedMultimap abstractMapBasedMultimap, Object obj, SortedSet sortedSet, C1672l lVar) {
        super(abstractMapBasedMultimap, obj, sortedSet, lVar);
        this.this$0 = abstractMapBasedMultimap;
    }

    public Comparator comparator() {
        return ((SortedSet) this.delegate).comparator();
    }

    public Object first() {
        mo9197Ml();
        return ((SortedSet) this.delegate).first();
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [com.google.common.collect.l] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.SortedSet headSet(java.lang.Object r5) {
        /*
            r4 = this;
            r4.mo9197Ml()
            com.google.common.collect.p r0 = new com.google.common.collect.p
            com.google.common.collect.AbstractMapBasedMultimap r1 = r4.this$0
            java.lang.Object r2 = r4.key
            java.util.Collection r3 = r4.delegate
            java.util.SortedSet r3 = (java.util.SortedSet) r3
            java.util.SortedSet r5 = r3.headSet(r5)
            com.google.common.collect.l r3 = r4.f2535FP
            if (r3 != 0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r4 = r3
        L_0x0017:
            r0.<init>(r1, r2, r5, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.C1684p.headSet(java.lang.Object):java.util.SortedSet");
    }

    public Object last() {
        mo9197Ml();
        return ((SortedSet) this.delegate).last();
    }

    /* JADX WARNING: type inference failed for: r6v1, types: [com.google.common.collect.l] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.SortedSet subSet(java.lang.Object r5, java.lang.Object r6) {
        /*
            r4 = this;
            r4.mo9197Ml()
            com.google.common.collect.p r0 = new com.google.common.collect.p
            com.google.common.collect.AbstractMapBasedMultimap r1 = r4.this$0
            java.lang.Object r2 = r4.key
            java.util.Collection r3 = r4.delegate
            java.util.SortedSet r3 = (java.util.SortedSet) r3
            java.util.SortedSet r5 = r3.subSet(r5, r6)
            com.google.common.collect.l r6 = r4.f2535FP
            if (r6 != 0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r4 = r6
        L_0x0017:
            r0.<init>(r1, r2, r5, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.C1684p.subSet(java.lang.Object, java.lang.Object):java.util.SortedSet");
    }

    /* JADX WARNING: type inference failed for: r3v2, types: [com.google.common.collect.l] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.SortedSet tailSet(java.lang.Object r5) {
        /*
            r4 = this;
            r4.mo9197Ml()
            com.google.common.collect.p r0 = new com.google.common.collect.p
            com.google.common.collect.AbstractMapBasedMultimap r1 = r4.this$0
            java.lang.Object r2 = r4.key
            java.util.Collection r3 = r4.delegate
            java.util.SortedSet r3 = (java.util.SortedSet) r3
            java.util.SortedSet r5 = r3.tailSet(r5)
            com.google.common.collect.l r3 = r4.f2535FP
            if (r3 != 0) goto L_0x0016
            goto L_0x0017
        L_0x0016:
            r4 = r3
        L_0x0017:
            r0.<init>(r1, r2, r5, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.C1684p.tailSet(java.lang.Object):java.util.SortedSet");
    }
}
