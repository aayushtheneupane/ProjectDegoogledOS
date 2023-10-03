package p000;

import android.support.p002v7.widget.RecyclerView;

/* renamed from: yu */
/* compiled from: PG */
final class C0675yu extends C0652xy {

    /* renamed from: a */
    private boolean f16421a = false;

    /* renamed from: b */
    private final /* synthetic */ C0650xw f16422b;

    public C0675yu(C0650xw xwVar) {
        this.f16422b = xwVar;
    }

    /* renamed from: a */
    public final void mo4639a(RecyclerView recyclerView, int i) {
        if (i == 0 && this.f16421a) {
            this.f16421a = false;
            this.f16422b.mo10599a();
        }
    }

    /* renamed from: a */
    public final void mo4654a(RecyclerView recyclerView, int i, int i2) {
        if (i != 0 || i2 != 0) {
            this.f16421a = true;
        }
    }
}
