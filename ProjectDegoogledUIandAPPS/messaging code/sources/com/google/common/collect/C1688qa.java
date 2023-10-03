package com.google.common.collect;

import java.util.AbstractSet;
import java.util.Iterator;

/* renamed from: com.google.common.collect.qa */
final class C1688qa extends AbstractSet {
    final /* synthetic */ MapMakerInternalMap this$0;

    C1688qa(MapMakerInternalMap mapMakerInternalMap) {
        this.this$0 = mapMakerInternalMap;
    }

    public void clear() {
        this.this$0.clear();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r4 = (java.util.Map.Entry) r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean contains(java.lang.Object r4) {
        /*
            r3 = this;
            boolean r0 = r4 instanceof java.util.Map.Entry
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r0 = r4.getKey()
            if (r0 != 0) goto L_0x000f
            return r1
        L_0x000f:
            com.google.common.collect.MapMakerInternalMap r2 = r3.this$0
            java.lang.Object r0 = r2.get(r0)
            if (r0 == 0) goto L_0x0026
            com.google.common.collect.MapMakerInternalMap r3 = r3.this$0
            com.google.common.base.u r3 = r3.valueEquivalence
            java.lang.Object r4 = r4.getValue()
            boolean r3 = r3.mo8558d(r4, r0)
            if (r3 == 0) goto L_0x0026
            r1 = 1
        L_0x0026:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.C1688qa.contains(java.lang.Object):boolean");
    }

    public boolean isEmpty() {
        return this.this$0.isEmpty();
    }

    public Iterator iterator() {
        return new C1685pa(this.this$0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0006, code lost:
        r3 = (java.util.Map.Entry) r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean remove(java.lang.Object r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof java.util.Map.Entry
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r0 = r3.getKey()
            if (r0 == 0) goto L_0x001b
            com.google.common.collect.MapMakerInternalMap r2 = r2.this$0
            java.lang.Object r3 = r3.getValue()
            boolean r2 = r2.remove(r0, r3)
            if (r2 == 0) goto L_0x001b
            r1 = 1
        L_0x001b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.C1688qa.remove(java.lang.Object):boolean");
    }

    public int size() {
        return this.this$0.size();
    }
}
