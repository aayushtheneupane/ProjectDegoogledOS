package p000;

/* renamed from: geh */
/* compiled from: PG */
final class geh extends gfd {

    /* renamed from: a */
    private final /* synthetic */ int f11106a;

    /* renamed from: b */
    private final /* synthetic */ geq f11107b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public geh(geq geq, int i, int i2) {
        super(i);
        this.f11107b = geq;
        this.f11106a = i2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo6513a(C0664yj yjVar, int[] iArr) {
        if (this.f11106a == 0) {
            iArr[0] = this.f11107b.f11121aa.getWidth();
            iArr[1] = this.f11107b.f11121aa.getWidth();
            return;
        }
        iArr[0] = this.f11107b.f11121aa.getHeight();
        iArr[1] = this.f11107b.f11121aa.getHeight();
    }
}
