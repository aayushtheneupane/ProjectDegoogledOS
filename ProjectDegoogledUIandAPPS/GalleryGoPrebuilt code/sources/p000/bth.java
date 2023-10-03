package p000;

/* renamed from: bth */
/* compiled from: PG */
public final /* synthetic */ class bth implements Runnable {

    /* renamed from: a */
    private final bsz f3535a;

    public bth(bsz bsz) {
        this.f3535a = bsz;
    }

    public final void run() {
        bsz bsz = this.f3535a;
        synchronized (bsz.f3508a) {
            bsz.f3509b = cxd.f5884h;
            bsz.f3510c = null;
        }
        bsz.f3513f.mo7099b(ife.m12820a((Object) null), (Object) "DEVICE_FOLDERS_DATA_SERVICE_KEY");
        ((ble) bsz.f3515h.mo9034a()).mo2554a();
    }
}
