package p000;

import android.database.DataSetObserver;

/* renamed from: wl */
/* compiled from: PG */
final class C0612wl extends DataSetObserver {

    /* renamed from: a */
    private final /* synthetic */ C0616wp f16238a;

    public C0612wl(C0616wp wpVar) {
        this.f16238a = wpVar;
    }

    public final void onChanged() {
        if (this.f16238a.mo9811e()) {
            this.f16238a.mo9805ab();
        }
    }

    public final void onInvalidated() {
        this.f16238a.mo9810d();
    }
}
