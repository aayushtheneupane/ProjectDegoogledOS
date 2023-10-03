package p000;

/* renamed from: icm */
/* compiled from: PG */
public final class icm implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ida f13884a;

    /* renamed from: b */
    private final /* synthetic */ idb f13885b;

    public icm(idb idb, ida ida) {
        this.f13885b = idb;
        this.f13884a = ida;
    }

    public final void run() {
        ida ida = this.f13884a;
        icz icz = new icz(this.f13885b);
        guo guo = ((gun) ida).f12078a;
        synchronized (guo.f12079a) {
            guo.f12080b = icz;
        }
    }
}
