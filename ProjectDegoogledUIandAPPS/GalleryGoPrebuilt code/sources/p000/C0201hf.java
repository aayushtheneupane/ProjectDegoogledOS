package p000;

import android.view.View;
import java.util.ArrayList;
import java.util.Map;

/* renamed from: hf */
/* compiled from: PG */
final class C0201hf implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ArrayList f12631a;

    /* renamed from: b */
    private final /* synthetic */ Map f12632b;

    public C0201hf(ArrayList arrayList, Map map) {
        this.f12631a = arrayList;
        this.f12632b = map;
    }

    public final void run() {
        int size = this.f12631a.size();
        for (int i = 0; i < size; i++) {
            View view = (View) this.f12631a.get(i);
            C0340mj.m14697a(view, (String) this.f12632b.get(C0340mj.m14722m(view)));
        }
    }
}
