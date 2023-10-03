package p000;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

/* renamed from: xw */
/* compiled from: PG */
public abstract class C0650xw {

    /* renamed from: a */
    public RecyclerView f16316a;

    /* renamed from: b */
    public Scroller f16317b;

    /* renamed from: c */
    private final C0652xy f16318c = new C0675yu(this);

    /* renamed from: a */
    public abstract int mo10483a(C0647xt xtVar, int i, int i2);

    /* renamed from: a */
    public abstract View mo10484a(C0647xt xtVar);

    /* renamed from: a */
    public abstract int[] mo10485a(C0647xt xtVar, View view);

    /* renamed from: a */
    public void mo4650a(RecyclerView recyclerView) {
        RecyclerView recyclerView2 = this.f16316a;
        if (recyclerView2 != recyclerView) {
            if (recyclerView2 != null) {
                recyclerView2.removeOnScrollListener(this.f16318c);
                this.f16316a.setOnFlingListener((C0650xw) null);
            }
            this.f16316a = recyclerView;
            if (recyclerView == null) {
                return;
            }
            if (recyclerView.getOnFlingListener() == null) {
                this.f16316a.addOnScrollListener(this.f16318c);
                this.f16316a.setOnFlingListener(this);
                this.f16317b = new Scroller(this.f16316a.getContext(), new DecelerateInterpolator());
                mo10599a();
                return;
            }
            throw new IllegalStateException("An instance of OnFlingListener already set.");
        }
    }

    @Deprecated
    /* renamed from: b */
    public C0663yi mo4651b(C0647xt xtVar) {
        if (xtVar instanceof C0662yh) {
            return new C0676yv(this, this.f16316a.getContext());
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo10599a() {
        C0647xt layoutManager;
        View a;
        RecyclerView recyclerView = this.f16316a;
        if (recyclerView != null && (layoutManager = recyclerView.getLayoutManager()) != null && (a = mo10484a(layoutManager)) != null) {
            int[] a2 = mo10485a(layoutManager, a);
            int i = a2[0];
            if (i != 0 || a2[1] != 0) {
                this.f16316a.smoothScrollBy(i, a2[1]);
            }
        }
    }
}
