package androidx.fragment.app;

import android.os.Build;
import android.view.View;
import androidx.core.view.ViewCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* renamed from: androidx.fragment.app.ca */
class C0412ca implements Runnable {

    /* renamed from: gp */
    final /* synthetic */ ArrayList f404gp;

    /* renamed from: np */
    final /* synthetic */ Map f405np;

    C0412ca(C0416ea eaVar, ArrayList arrayList, Map map) {
        this.f404gp = arrayList;
        this.f405np = map;
    }

    public void run() {
        String str;
        int size = this.f404gp.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.f404gp.get(i);
            String transitionName = ViewCompat.getTransitionName(view);
            if (transitionName != null) {
                Iterator it = this.f405np.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = null;
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (transitionName.equals(entry.getValue())) {
                        str = (String) entry.getKey();
                        break;
                    }
                }
                int i2 = Build.VERSION.SDK_INT;
                view.setTransitionName(str);
            }
        }
    }
}
