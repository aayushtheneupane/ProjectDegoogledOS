package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: yd */
/* compiled from: PG */
public final class C0658yd extends dcm {

    /* renamed from: a */
    private final /* synthetic */ RecyclerView f16333a;

    public C0658yd(RecyclerView recyclerView) {
        this.f16333a = recyclerView;
    }

    /* renamed from: a */
    public final void mo4054a() {
        this.f16333a.assertNotInLayoutOrScroll((String) null);
        RecyclerView recyclerView = this.f16333a;
        recyclerView.mState.f16363f = true;
        recyclerView.processDataSetCompletelyChanged(true);
        if (!this.f16333a.mAdapterHelper.mo10093d()) {
            this.f16333a.requestLayout();
        }
    }

    /* renamed from: a */
    public final void mo4056a(int i, int i2, Object obj) {
        this.f16333a.assertNotInLayoutOrScroll((String) null);
        C0521tb tbVar = this.f16333a.mAdapterHelper;
        if (i2 > 0) {
            tbVar.f15904a.add(tbVar.mo10086a(4, i, i2, obj));
            tbVar.f15906c |= 4;
            if (tbVar.f15904a.size() == 1) {
                m16091b();
            }
        }
    }

    /* renamed from: a */
    public final void mo4055a(int i, int i2) {
        this.f16333a.assertNotInLayoutOrScroll((String) null);
        C0521tb tbVar = this.f16333a.mAdapterHelper;
        if (i2 > 0) {
            tbVar.f15904a.add(tbVar.mo10086a(1, i, i2, (Object) null));
            tbVar.f15906c |= 1;
            if (tbVar.f15904a.size() == 1) {
                m16091b();
            }
        }
    }

    /* renamed from: c */
    public final void mo4058c(int i, int i2) {
        this.f16333a.assertNotInLayoutOrScroll((String) null);
        C0521tb tbVar = this.f16333a.mAdapterHelper;
        if (i != i2) {
            tbVar.f15904a.add(tbVar.mo10086a(8, i, i2, (Object) null));
            tbVar.f15906c |= 8;
            if (tbVar.f15904a.size() == 1) {
                m16091b();
            }
        }
    }

    /* renamed from: b */
    public final void mo4057b(int i, int i2) {
        this.f16333a.assertNotInLayoutOrScroll((String) null);
        C0521tb tbVar = this.f16333a.mAdapterHelper;
        if (i2 > 0) {
            tbVar.f15904a.add(tbVar.mo10086a(2, i, i2, (Object) null));
            tbVar.f15906c |= 2;
            if (tbVar.f15904a.size() == 1) {
                m16091b();
            }
        }
    }

    /* renamed from: b */
    private final void m16091b() {
        if (RecyclerView.POST_UPDATES_ON_ANIMATION) {
            RecyclerView recyclerView = this.f16333a;
            if (recyclerView.mHasFixedSize && recyclerView.mIsAttached) {
                C0340mj.m14695a((View) recyclerView, recyclerView.mUpdateChildViewsRunnable);
                return;
            }
        }
        RecyclerView recyclerView2 = this.f16333a;
        recyclerView2.mAdapterUpdateDuringMeasure = true;
        recyclerView2.requestLayout();
    }
}
