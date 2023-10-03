package p000;

import android.view.View;
import android.view.ViewTreeObserver;
import java.util.List;

/* renamed from: qv */
/* compiled from: PG */
final class C0460qv implements ViewTreeObserver.OnGlobalLayoutListener {

    /* renamed from: a */
    private final /* synthetic */ C0466ra f15699a;

    public C0460qv(C0466ra raVar) {
        this.f15699a = raVar;
    }

    public final void onGlobalLayout() {
        if (this.f15699a.mo9811e() && this.f15699a.f15710b.size() > 0) {
            if (!((C0464qz) this.f15699a.f15710b.get(0)).f15706a.f16258p) {
                View view = this.f15699a.f15712d;
                if (view != null && view.isShown()) {
                    List list = this.f15699a.f15710b;
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        ((C0464qz) list.get(i)).f15706a.mo9805ab();
                    }
                    return;
                }
                this.f15699a.mo9810d();
            }
        }
    }
}
