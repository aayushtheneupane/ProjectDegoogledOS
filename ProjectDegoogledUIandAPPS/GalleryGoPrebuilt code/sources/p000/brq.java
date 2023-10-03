package p000;

/* renamed from: brq */
/* compiled from: PG */
final class brq implements hol {

    /* renamed from: a */
    private final /* synthetic */ brp f3453a;

    public brq(brp brp) {
        this.f3453a = brp;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ hom mo2639a(hoi hoi) {
        bsk bsk = (bsk) hoi;
        btr btr = this.f3453a.f3446f;
        ieh a = btr.f3560b.mo6360a(new btp(), btr.f3559a);
        btr.f3562d.mo7099b(a, (Object) "DEVICE_FOLDERS_PROMO_DATA_SERVICE_KEY");
        cwn.m5509a(a, "PromoDataService[DeviceFolders]: Failed to update promo dismissed state", new Object[0]);
        return hom.f13155a;
    }
}
