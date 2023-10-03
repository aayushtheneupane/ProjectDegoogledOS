package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: xe */
/* compiled from: PG */
public final class C0632xe implements C0562up {

    /* renamed from: a */
    public final /* synthetic */ RecyclerView f16285a;

    public C0632xe(RecyclerView recyclerView) {
        this.f16285a = recyclerView;
    }

    /* renamed from: b */
    public final View mo10307b(int i) {
        return this.f16285a.getChildAt(i);
    }

    /* renamed from: a */
    public final int mo10304a() {
        return this.f16285a.getChildCount();
    }

    /* renamed from: a */
    public final int mo10305a(View view) {
        return this.f16285a.indexOfChild(view);
    }

    /* renamed from: b */
    public final void mo10308b(View view) {
        C0667ym childViewHolderInt = RecyclerView.getChildViewHolderInt(view);
        if (childViewHolderInt != null) {
            this.f16285a.setChildImportantForAccessibilityInternal(childViewHolderInt, childViewHolderInt.f16394m);
            childViewHolderInt.f16394m = 0;
        }
    }

    /* renamed from: a */
    public final void mo10306a(int i) {
        View childAt = this.f16285a.getChildAt(i);
        if (childAt != null) {
            this.f16285a.dispatchChildDetached(childAt);
            childAt.clearAnimation();
        }
        this.f16285a.removeViewAt(i);
    }
}
