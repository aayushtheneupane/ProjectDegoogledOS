package p000;

import android.view.View;
import android.view.ViewTreeObserver;

/* renamed from: rz */
/* compiled from: PG */
final class C0491rz implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    private final /* synthetic */ C0494sb f15830a;

    public C0491rz(C0494sb sbVar) {
        this.f15830a = sbVar;
    }

    public final void onGlobalLayout() {
        if (this.f15830a.mo9811e()) {
            C0494sb sbVar = this.f15830a;
            if (!sbVar.f15834a.f16258p) {
                View view = sbVar.f15836c;
                if (view != null && view.isShown()) {
                    this.f15830a.f15834a.mo9805ab();
                } else {
                    this.f15830a.mo9810d();
                }
            }
        }
    }
}
