package p000;

import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* renamed from: he */
/* compiled from: PG */
final class C0200he implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ArrayList f12563a;

    /* renamed from: b */
    private final /* synthetic */ Map f12564b;

    public C0200he(ArrayList arrayList, Map map) {
        this.f12563a = arrayList;
        this.f12564b = map;
    }

    public final void run() {
        String str;
        int size = this.f12563a.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.f12563a.get(i);
            String m = C0340mj.m14722m(view);
            if (m != null) {
                Iterator it = this.f12564b.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        str = null;
                        break;
                    }
                    Map.Entry entry = (Map.Entry) it.next();
                    if (m.equals(entry.getValue())) {
                        str = (String) entry.getKey();
                        break;
                    }
                }
                C0340mj.m14697a(view, str);
            }
        }
    }
}
