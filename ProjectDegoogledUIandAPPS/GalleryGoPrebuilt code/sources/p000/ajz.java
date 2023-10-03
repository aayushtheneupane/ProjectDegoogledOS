package p000;

import java.util.List;

/* renamed from: ajz */
/* compiled from: PG */
final class ajz implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ List f656a;

    /* renamed from: b */
    private final /* synthetic */ aka f657b;

    public ajz(aka aka, List list) {
        this.f657b = aka;
        this.f656a = list;
    }

    public final void run() {
        List list = this.f656a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((aji) list.get(i)).mo547a(this.f657b.f661d);
        }
    }
}
