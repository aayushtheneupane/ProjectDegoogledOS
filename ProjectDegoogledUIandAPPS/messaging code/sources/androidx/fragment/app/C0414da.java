package androidx.fragment.app;

import android.os.Build;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: androidx.fragment.app.da */
class C0414da implements Runnable {

    /* renamed from: gp */
    final /* synthetic */ ArrayList f406gp;

    /* renamed from: np */
    final /* synthetic */ Map f407np;

    C0414da(C0416ea eaVar, ArrayList arrayList, Map map) {
        this.f406gp = arrayList;
        this.f407np = map;
    }

    public void run() {
        int size = this.f406gp.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.f406gp.get(i);
            String transitionName = ViewCompat.getTransitionName(view);
            int i2 = Build.VERSION.SDK_INT;
            view.setTransitionName((String) this.f407np.get(transitionName));
        }
    }
}
