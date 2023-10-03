package p000;

import android.view.Menu;
import android.view.View;

/* renamed from: pj */
/* compiled from: PG */
final class C0421pj implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ C0426po f15513a;

    public C0421pj(C0426po poVar) {
        this.f15513a = poVar;
    }

    public final void run() {
        C0426po poVar = this.f15513a;
        Menu k = poVar.mo9639k();
        C0472rg rgVar = k instanceof C0472rg ? (C0472rg) k : null;
        if (rgVar != null) {
            rgVar.mo9859e();
        }
        try {
            k.clear();
            if (!poVar.f15521c.onCreatePanelMenu(0, k) || !poVar.f15521c.onPreparePanel(0, (View) null, k)) {
                k.clear();
            }
            if (rgVar != null) {
                rgVar.mo9860f();
            }
        } catch (Throwable th) {
            if (rgVar != null) {
                rgVar.mo9860f();
            }
            throw th;
        }
    }
}
