package androidx.recyclerview.widget;

import java.util.Arrays;

/* renamed from: androidx.recyclerview.widget.wa */
class C0598wa {
    boolean mLayoutFromEnd;
    int mOffset;
    int mPosition;
    boolean mValid;

    /* renamed from: pt */
    boolean f675pt;

    /* renamed from: qt */
    int[] f676qt;
    final /* synthetic */ StaggeredGridLayoutManager this$0;

    C0598wa(StaggeredGridLayoutManager staggeredGridLayoutManager) {
        this.this$0 = staggeredGridLayoutManager;
        reset();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo5260a(C0524Ba[] baArr) {
        int length = baArr.length;
        int[] iArr = this.f676qt;
        if (iArr == null || iArr.length < length) {
            this.f676qt = new int[this.this$0.f600js.length];
        }
        for (int i = 0; i < length; i++) {
            this.f676qt[i] = baArr[i].mo4620Y(RtlSpacingHelper.UNDEFINED);
        }
    }

    /* access modifiers changed from: package-private */
    public void assignCoordinateFromPadding() {
        int i;
        if (this.mLayoutFromEnd) {
            i = this.this$0.f601ks.getEndAfterPadding();
        } else {
            i = this.this$0.f601ks.getStartAfterPadding();
        }
        this.mOffset = i;
    }

    /* access modifiers changed from: package-private */
    public void reset() {
        this.mPosition = -1;
        this.mOffset = RtlSpacingHelper.UNDEFINED;
        this.mLayoutFromEnd = false;
        this.f675pt = false;
        this.mValid = false;
        int[] iArr = this.f676qt;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
    }
}
