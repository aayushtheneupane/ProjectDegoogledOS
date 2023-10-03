package p000;

import android.view.View;
import android.view.ViewPropertyAnimator;
import java.util.ArrayList;

/* renamed from: uu */
/* compiled from: PG */
final class C0567uu implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ArrayList f16030a;

    /* renamed from: b */
    private final /* synthetic */ C0578ve f16031b;

    public C0567uu(C0578ve veVar, ArrayList arrayList) {
        this.f16031b = veVar;
        this.f16030a = arrayList;
    }

    public final void run() {
        ArrayList arrayList = this.f16030a;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            C0577vd vdVar = (C0577vd) arrayList.get(i);
            C0578ve veVar = this.f16031b;
            C0667ym ymVar = vdVar.f16070a;
            int i2 = vdVar.f16071b;
            int i3 = vdVar.f16072c;
            int i4 = vdVar.f16073d;
            int i5 = vdVar.f16074e;
            View view = ymVar.f16382a;
            int i6 = i4 - i2;
            int i7 = i5 - i3;
            if (i6 != 0) {
                view.animate().translationX(0.0f);
            }
            if (i7 != 0) {
                view.animate().translationY(0.0f);
            }
            ViewPropertyAnimator animate = view.animate();
            veVar.f16080e.add(ymVar);
            animate.setDuration(250).setListener(new C0572uz(veVar, ymVar, i6, view, i7, animate)).start();
        }
        this.f16030a.clear();
        this.f16031b.f16077b.remove(this.f16030a);
    }
}
