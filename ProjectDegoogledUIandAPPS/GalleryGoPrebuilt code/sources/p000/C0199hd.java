package p000;

import android.view.View;
import java.util.ArrayList;

/* renamed from: hd */
/* compiled from: PG */
final class C0199hd implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ int f12508a;

    /* renamed from: b */
    private final /* synthetic */ ArrayList f12509b;

    /* renamed from: c */
    private final /* synthetic */ ArrayList f12510c;

    /* renamed from: d */
    private final /* synthetic */ ArrayList f12511d;

    /* renamed from: e */
    private final /* synthetic */ ArrayList f12512e;

    public C0199hd(int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4) {
        this.f12508a = i;
        this.f12509b = arrayList;
        this.f12510c = arrayList2;
        this.f12511d = arrayList3;
        this.f12512e = arrayList4;
    }

    public final void run() {
        for (int i = 0; i < this.f12508a; i++) {
            C0340mj.m14697a((View) this.f12509b.get(i), (String) this.f12510c.get(i));
            C0340mj.m14697a((View) this.f12511d.get(i), (String) this.f12512e.get(i));
        }
    }
}
