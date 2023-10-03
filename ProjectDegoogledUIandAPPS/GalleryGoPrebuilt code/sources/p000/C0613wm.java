package p000;

import android.widget.AbsListView;

/* renamed from: wm */
/* compiled from: PG */
final class C0613wm implements AbsListView.OnScrollListener {

    /* renamed from: a */
    private final /* synthetic */ C0616wp f16239a;

    public C0613wm(C0616wp wpVar) {
        this.f16239a = wpVar;
    }

    public final void onScroll(AbsListView absListView, int i, int i2, int i3) {
    }

    public final void onScrollStateChanged(AbsListView absListView, int i) {
        if (i == 1 && !this.f16239a.mo10507i() && this.f16239a.f16259q.getContentView() != null) {
            C0616wp wpVar = this.f16239a;
            wpVar.f16257o.removeCallbacks(wpVar.f16256n);
            this.f16239a.f16256n.run();
        }
    }
}
