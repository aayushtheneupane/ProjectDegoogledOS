package p000;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

/* renamed from: uv */
/* compiled from: PG */
final class C0568uv implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ArrayList f16032a;

    /* renamed from: b */
    private final /* synthetic */ C0578ve f16033b;

    public C0568uv(C0578ve veVar, ArrayList arrayList) {
        this.f16033b = veVar;
        this.f16032a = arrayList;
    }

    public final void run() {
        ArrayList arrayList = this.f16032a;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            C0576vc vcVar = (C0576vc) arrayList.get(i);
            C0578ve veVar = this.f16033b;
            C0667ym ymVar = vcVar.f16064a;
            View view = null;
            View view2 = ymVar != null ? ymVar.f16382a : null;
            C0667ym ymVar2 = vcVar.f16065b;
            if (ymVar2 != null) {
                view = ymVar2.f16382a;
            }
            if (view2 != null) {
                ViewPropertyAnimator duration = view2.animate().setDuration(250);
                veVar.f16082g.add(vcVar.f16064a);
                duration.translationX((float) (vcVar.f16068e - vcVar.f16066c));
                duration.translationY((float) (vcVar.f16069f - vcVar.f16067d));
                duration.alpha(0.0f).setListener(new C0574va(veVar, vcVar, duration, view2)).start();
            }
            if (view != null) {
                ViewPropertyAnimator animate = view.animate();
                veVar.f16082g.add(vcVar.f16065b);
                animate.translationX(0.0f).translationY(0.0f).setDuration(250).alpha(1.0f).setListener(new C0575vb(veVar, vcVar, animate, view)).start();
            }
        }
        this.f16032a.clear();
        this.f16033b.f16078c.remove(this.f16032a);
    }
}
