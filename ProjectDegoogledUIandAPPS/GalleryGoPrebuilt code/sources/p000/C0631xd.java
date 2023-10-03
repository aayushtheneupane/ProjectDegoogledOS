package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;

/* renamed from: xd */
/* compiled from: PG */
public final class C0631xd implements C0701zt {

    /* renamed from: a */
    private final /* synthetic */ RecyclerView f16284a;

    public C0631xd(RecyclerView recyclerView) {
        this.f16284a = recyclerView;
    }

    /* renamed from: b */
    public final void mo10531b(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2) {
        this.f16284a.animateAppearance(ymVar, xmVar, xmVar2);
    }

    /* renamed from: a */
    public final void mo10530a(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2) {
        this.f16284a.mRecycler.mo10612b(ymVar);
        this.f16284a.animateDisappearance(ymVar, xmVar, xmVar2);
    }

    /* renamed from: c */
    public final void mo10532c(C0667ym ymVar, C0640xm xmVar, C0640xm xmVar2) {
        ymVar.mo10639a(false);
        RecyclerView recyclerView = this.f16284a;
        if (recyclerView.mDataSetHasChangedAfterLayout) {
            if (recyclerView.mItemAnimator.mo10554a(ymVar, ymVar, xmVar, xmVar2)) {
                this.f16284a.postAnimationRunner();
            }
        } else if (recyclerView.mItemAnimator.mo4644c(ymVar, xmVar, xmVar2)) {
            this.f16284a.postAnimationRunner();
        }
    }

    /* renamed from: a */
    public final void mo10529a(C0667ym ymVar) {
        RecyclerView recyclerView = this.f16284a;
        C0647xt xtVar = recyclerView.mLayout;
        View view = ymVar.f16382a;
        C0656yb ybVar = recyclerView.mRecycler;
        C0563uq uqVar = xtVar.f16298i;
        int a = uqVar.f16027a.mo10305a(view);
        if (a >= 0) {
            if (uqVar.f16028b.mo10301d(a)) {
                uqVar.mo10320d(view);
            }
            uqVar.f16027a.mo10306a(a);
        }
        ybVar.mo10606a(view);
    }
}
