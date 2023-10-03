package p000;

/* renamed from: des */
/* compiled from: PG */
final /* synthetic */ class des implements hpr {

    /* renamed from: a */
    private final det f6407a;

    public des(det det) {
        this.f6407a = det;
    }

    /* renamed from: a */
    public final Object mo1484a(Object obj) {
        det det = this.f6407a;
        Boolean bool = (Boolean) obj;
        if (bool.booleanValue()) {
            det.f6410c.mo2554a();
            if (det.f6412e.isPresent()) {
                ((ble) det.f6412e.get()).mo2554a();
            }
            if (det.f6411d.isPresent()) {
                ((ble) det.f6411d.get()).mo2554a();
            }
        }
        det.f6413f.f6405a.set(true);
        det.f6414g.mo7096a(ife.m12820a((Object) null), (Object) "MEDIA_STORE_SYNC_STATE_KEY");
        return bool;
    }
}
