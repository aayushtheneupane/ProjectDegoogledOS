package p000;

import android.view.View;

/* renamed from: fe */
/* compiled from: PG */
final class C0144fe extends C0156fq {

    /* renamed from: a */
    private final /* synthetic */ C0147fh f9355a;

    public C0144fe(C0147fh fhVar) {
        this.f9355a = fhVar;
    }

    /* renamed from: Z */
    public final boolean mo5558Z() {
        return this.f9355a.f9573L != null;
    }

    /* renamed from: a */
    public final View mo5559a(int i) {
        View view = this.f9355a.f9573L;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Fragment " + this + " does not have a view");
    }
}
