package androidx.recyclerview.widget;

import android.view.View;
import java.util.ArrayList;

/* renamed from: androidx.recyclerview.widget.Ba */
class C0524Ba {

    /* renamed from: At */
    ArrayList f503At = new ArrayList();

    /* renamed from: Bt */
    int f504Bt = RtlSpacingHelper.UNDEFINED;

    /* renamed from: Ct */
    int f505Ct = RtlSpacingHelper.UNDEFINED;

    /* renamed from: Dt */
    int f506Dt = 0;
    final int mIndex;
    final /* synthetic */ StaggeredGridLayoutManager this$0;

    C0524Ba(StaggeredGridLayoutManager staggeredGridLayoutManager, int i) {
        this.this$0 = staggeredGridLayoutManager;
        this.mIndex = i;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: X */
    public int mo4619X(int i) {
        int i2 = this.f505Ct;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.f503At.size() == 0) {
            return i;
        }
        mo4621ad();
        return this.f505Ct;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Y */
    public int mo4620Y(int i) {
        int i2 = this.f504Bt;
        if (i2 != Integer.MIN_VALUE) {
            return i2;
        }
        if (this.f503At.size() == 0) {
            return i;
        }
        mo4623bd();
        return this.f504Bt;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ad */
    public void mo4621ad() {
        StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem V;
        ArrayList arrayList = this.f503At;
        View view = (View) arrayList.get(arrayList.size() - 1);
        C0600xa layoutParams = getLayoutParams(view);
        this.f505Ct = this.this$0.f601ks.getDecoratedEnd(view);
        if (layoutParams.f682Yk && (V = this.this$0.f605os.mo5273V(layoutParams.getViewLayoutPosition())) != null && V.f621rt == 1) {
            this.f505Ct += V.mo4977S(this.mIndex);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public int mo4622b(int i, int i2, boolean z) {
        int startAfterPadding = this.this$0.f601ks.getStartAfterPadding();
        int endAfterPadding = this.this$0.f601ks.getEndAfterPadding();
        int i3 = i2 > i ? 1 : -1;
        while (i != i2) {
            View view = (View) this.f503At.get(i);
            int decoratedStart = this.this$0.f601ks.getDecoratedStart(view);
            int decoratedEnd = this.this$0.f601ks.getDecoratedEnd(view);
            boolean z2 = false;
            boolean z3 = !z ? decoratedStart < endAfterPadding : decoratedStart <= endAfterPadding;
            if (!z ? decoratedEnd > startAfterPadding : decoratedEnd >= startAfterPadding) {
                z2 = true;
            }
            if (z3 && z2 && (decoratedStart < startAfterPadding || decoratedEnd > endAfterPadding)) {
                return this.this$0.getPosition(view);
            }
            i += i3;
        }
        return -1;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: bd */
    public void mo4623bd() {
        StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem V;
        View view = (View) this.f503At.get(0);
        C0600xa layoutParams = getLayoutParams(view);
        this.f504Bt = this.this$0.f601ks.getDecoratedStart(view);
        if (layoutParams.f682Yk && (V = this.this$0.f605os.mo5273V(layoutParams.getViewLayoutPosition())) != null && V.f621rt == -1) {
            this.f504Bt -= V.mo4977S(this.mIndex);
        }
    }

    /* renamed from: cd */
    public int mo4624cd() {
        if (this.this$0.f597Wr) {
            return mo4622b(this.f503At.size() - 1, -1, true);
        }
        return mo4622b(0, this.f503At.size(), true);
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.f503At.clear();
        this.f504Bt = RtlSpacingHelper.UNDEFINED;
        this.f505Ct = RtlSpacingHelper.UNDEFINED;
        this.f506Dt = 0;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo4626d(View view) {
        C0600xa layoutParams = getLayoutParams(view);
        layoutParams.f681Xk = this;
        this.f503At.add(view);
        this.f505Ct = RtlSpacingHelper.UNDEFINED;
        if (this.f503At.size() == 1) {
            this.f504Bt = RtlSpacingHelper.UNDEFINED;
        }
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            this.f506Dt = this.this$0.f601ks.getDecoratedMeasurement(view) + this.f506Dt;
        }
    }

    /* renamed from: dd */
    public int mo4627dd() {
        if (this.this$0.f597Wr) {
            return mo4622b(0, this.f503At.size(), true);
        }
        return mo4622b(this.f503At.size() - 1, -1, true);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo4628e(View view) {
        C0600xa layoutParams = getLayoutParams(view);
        layoutParams.f681Xk = this;
        this.f503At.add(0, view);
        this.f504Bt = RtlSpacingHelper.UNDEFINED;
        if (this.f503At.size() == 1) {
            this.f505Ct = RtlSpacingHelper.UNDEFINED;
        }
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            this.f506Dt = this.this$0.f601ks.getDecoratedMeasurement(view) + this.f506Dt;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: ed */
    public int mo4629ed() {
        int i = this.f505Ct;
        if (i != Integer.MIN_VALUE) {
            return i;
        }
        mo4621ad();
        return this.f505Ct;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: fd */
    public int mo4630fd() {
        int i = this.f504Bt;
        if (i != Integer.MIN_VALUE) {
            return i;
        }
        mo4623bd();
        return this.f504Bt;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: gd */
    public void mo4631gd() {
        int size = this.f503At.size();
        View view = (View) this.f503At.remove(size - 1);
        C0600xa layoutParams = getLayoutParams(view);
        layoutParams.f681Xk = null;
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            this.f506Dt -= this.this$0.f601ks.getDecoratedMeasurement(view);
        }
        if (size == 1) {
            this.f504Bt = RtlSpacingHelper.UNDEFINED;
        }
        this.f505Ct = RtlSpacingHelper.UNDEFINED;
    }

    /* access modifiers changed from: package-private */
    public C0600xa getLayoutParams(View view) {
        return (C0600xa) view.getLayoutParams();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: hd */
    public void mo4633hd() {
        View view = (View) this.f503At.remove(0);
        C0600xa layoutParams = getLayoutParams(view);
        layoutParams.f681Xk = null;
        if (this.f503At.size() == 0) {
            this.f505Ct = RtlSpacingHelper.UNDEFINED;
        }
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            this.f506Dt -= this.this$0.f601ks.getDecoratedMeasurement(view);
        }
        this.f504Bt = RtlSpacingHelper.UNDEFINED;
    }

    /* renamed from: p */
    public View mo4634p(int i, int i2) {
        View view = null;
        if (i2 != -1) {
            int size = this.f503At.size() - 1;
            while (size >= 0) {
                View view2 = (View) this.f503At.get(size);
                StaggeredGridLayoutManager staggeredGridLayoutManager = this.this$0;
                if (staggeredGridLayoutManager.f597Wr && staggeredGridLayoutManager.getPosition(view2) >= i) {
                    break;
                }
                StaggeredGridLayoutManager staggeredGridLayoutManager2 = this.this$0;
                if ((!staggeredGridLayoutManager2.f597Wr && staggeredGridLayoutManager2.getPosition(view2) <= i) || !view2.hasFocusable()) {
                    break;
                }
                size--;
                view = view2;
            }
        } else {
            int size2 = this.f503At.size();
            int i3 = 0;
            while (i3 < size2) {
                View view3 = (View) this.f503At.get(i3);
                StaggeredGridLayoutManager staggeredGridLayoutManager3 = this.this$0;
                if (staggeredGridLayoutManager3.f597Wr && staggeredGridLayoutManager3.getPosition(view3) <= i) {
                    break;
                }
                StaggeredGridLayoutManager staggeredGridLayoutManager4 = this.this$0;
                if ((!staggeredGridLayoutManager4.f597Wr && staggeredGridLayoutManager4.getPosition(view3) >= i) || !view3.hasFocusable()) {
                    break;
                }
                i3++;
                view = view3;
            }
        }
        return view;
    }
}
