package p000;

/* renamed from: iot */
/* compiled from: PG */
final class iot implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ ioz f14607a;

    /* renamed from: b */
    private final /* synthetic */ iou f14608b;

    public iot(iou iou, ioz ioz) {
        this.f14608b = iou;
        this.f14607a = ioz;
    }

    public final void run() {
        if (this.f14608b.f14609a.isCancelled()) {
            ieh ieh = this.f14608b.f14609a;
            boolean z = false;
            if ((ieh instanceof ios) && ((ios) ieh).mo9048f()) {
                z = true;
            }
            this.f14607a.mo2099a(z);
        }
    }
}
