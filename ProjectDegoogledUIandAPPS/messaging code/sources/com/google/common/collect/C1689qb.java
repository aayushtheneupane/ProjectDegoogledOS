package com.google.common.collect;

/* renamed from: com.google.common.collect.qb */
class C1689qb extends C1642b {

    /* renamed from: gO */
    final /* synthetic */ C1647cb f2541gO;
    final /* synthetic */ TreeRangeSet$RangesByUpperBound this$0;

    C1689qb(TreeRangeSet$RangesByUpperBound treeRangeSet$RangesByUpperBound, C1647cb cbVar) {
        this.this$0 = treeRangeSet$RangesByUpperBound;
        this.f2541gO = cbVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: Gl */
    public Object mo9135Gl() {
        if (!this.f2541gO.hasNext()) {
            mo9136Hl();
            return null;
        }
        Range range = (Range) ((C1649da) this.f2541gO).next();
        if (this.this$0.f2505cQ.lowerBound.mo8614c(range.upperBound)) {
            return C1633Xa.m4547i(range.upperBound, range);
        }
        mo9136Hl();
        return null;
    }
}
