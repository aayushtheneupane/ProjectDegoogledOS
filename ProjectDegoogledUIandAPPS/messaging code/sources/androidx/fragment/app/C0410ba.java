package androidx.fragment.app;

import android.os.Build;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;

/* renamed from: androidx.fragment.app.ba */
class C0410ba implements Runnable {

    /* renamed from: Ap */
    final /* synthetic */ int f399Ap;

    /* renamed from: Bp */
    final /* synthetic */ ArrayList f400Bp;

    /* renamed from: Cp */
    final /* synthetic */ ArrayList f401Cp;

    /* renamed from: gp */
    final /* synthetic */ ArrayList f402gp;

    /* renamed from: sp */
    final /* synthetic */ ArrayList f403sp;

    C0410ba(C0416ea eaVar, int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.f399Ap = i;
        this.f402gp = arrayList;
        this.f400Bp = arrayList2;
        this.f403sp = arrayList3;
        this.f401Cp = arrayList4;
    }

    public void run() {
        for (int i = 0; i < this.f399Ap; i++) {
            ViewCompat.setTransitionName((View) this.f402gp.get(i), (String) this.f400Bp.get(i));
            int i2 = Build.VERSION.SDK_INT;
            ((View) this.f403sp.get(i)).setTransitionName((String) this.f401Cp.get(i));
        }
    }
}
