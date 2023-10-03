package p000;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

/* renamed from: uw */
/* compiled from: PG */
final class C0569uw implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ArrayList f16034a;

    /* renamed from: b */
    private final /* synthetic */ C0578ve f16035b;

    public C0569uw(C0578ve veVar, ArrayList arrayList) {
        this.f16035b = veVar;
        this.f16034a = arrayList;
    }

    public final void run() {
        ArrayList arrayList = this.f16034a;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            C0667ym ymVar = (C0667ym) arrayList.get(i);
            C0578ve veVar = this.f16035b;
            View view = ymVar.f16382a;
            ViewPropertyAnimator animate = view.animate();
            veVar.f16079d.add(ymVar);
            animate.alpha(1.0f).setDuration(veVar.mo4538e()).setListener(new C0571uy(veVar, ymVar, view, animate)).start();
        }
        this.f16034a.clear();
        this.f16035b.f16076a.remove(this.f16034a);
    }
}
