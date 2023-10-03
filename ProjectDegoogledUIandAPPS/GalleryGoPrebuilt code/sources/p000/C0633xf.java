package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: xf */
/* compiled from: PG */
public final class C0633xf implements C0518sz {

    /* renamed from: a */
    public final /* synthetic */ RecyclerView f16286a;

    public C0633xf(RecyclerView recyclerView) {
        this.f16286a = recyclerView;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10533a(C0520ta taVar) {
        int i = taVar.f15900a;
        if (i == 1) {
            this.f16286a.mLayout.mo10435c();
        } else if (i == 2) {
            this.f16286a.mLayout.mo10438f();
        } else if (i == 4) {
            this.f16286a.mLayout.mo10439g();
        } else if (i == 8) {
            this.f16286a.mLayout.mo10437e();
        }
    }

    /* renamed from: a */
    public final C0667ym mo10077a(int i) {
        C0667ym findViewHolderForPosition = this.f16286a.findViewHolderForPosition(i, true);
        if (findViewHolderForPosition == null || this.f16286a.mChildHelper.mo10318c(findViewHolderForPosition.f16382a)) {
            return null;
        }
        return findViewHolderForPosition;
    }

    /* renamed from: a */
    public final void mo10079a(int i, int i2, Object obj) {
        this.f16286a.viewRangeUpdate(i, i2, obj);
        this.f16286a.mItemsChanged = true;
    }

    /* renamed from: b */
    public final void mo10080b(int i, int i2) {
        this.f16286a.offsetPositionRecordsForInsert(i, i2);
        this.f16286a.mItemsAddedOrRemoved = true;
    }

    /* renamed from: c */
    public final void mo10081c(int i, int i2) {
        this.f16286a.offsetPositionRecordsForMove(i, i2);
        this.f16286a.mItemsAddedOrRemoved = true;
    }

    /* renamed from: a */
    public final void mo10078a(int i, int i2) {
        this.f16286a.offsetPositionRecordsForRemove(i, i2, true);
        RecyclerView recyclerView = this.f16286a;
        recyclerView.mItemsAddedOrRemoved = true;
        recyclerView.mState.f16360c += i2;
    }
}
