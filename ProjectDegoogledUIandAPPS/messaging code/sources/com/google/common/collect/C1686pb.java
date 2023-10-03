package com.google.common.collect;

import java.util.Iterator;

/* renamed from: com.google.common.collect.pb */
class C1686pb extends C1642b {

    /* renamed from: gO */
    final /* synthetic */ Iterator f2540gO;
    final /* synthetic */ TreeRangeSet$RangesByUpperBound this$0;

    C1686pb(TreeRangeSet$RangesByUpperBound treeRangeSet$RangesByUpperBound, Iterator it) {
        this.this$0 = treeRangeSet$RangesByUpperBound;
        this.f2540gO = it;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Gl */
    public Object mo9135Gl() {
        if (!this.f2540gO.hasNext()) {
            mo9136Hl();
            return null;
        }
        Range range = (Range) this.f2540gO.next();
        if (!this.this$0.f2505cQ.upperBound.mo8614c(range.upperBound)) {
            return C1633Xa.m4547i(range.upperBound, range);
        }
        mo9136Hl();
        return null;
    }
}
