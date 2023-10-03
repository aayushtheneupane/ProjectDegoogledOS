package p000;

/* renamed from: gef */
/* compiled from: PG */
final class gef implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ int f11104a;

    /* renamed from: b */
    private final /* synthetic */ geq f11105b;

    public gef(geq geq, int i) {
        this.f11105b = geq;
        this.f11104a = i;
    }

    public final void run() {
        this.f11105b.f11121aa.smoothScrollToPosition(this.f11104a);
    }
}
