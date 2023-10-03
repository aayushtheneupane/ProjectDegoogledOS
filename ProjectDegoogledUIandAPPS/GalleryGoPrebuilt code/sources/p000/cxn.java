package p000;

/* renamed from: cxn */
/* compiled from: PG */
public final class cxn extends C0297ku {

    /* renamed from: a */
    public final /* synthetic */ cxo f5938a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public cxn(cxo cxo) {
        super(2);
        this.f5938a = cxo;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final /* bridge */ /* synthetic */ Object mo3941b(Object obj) {
        cxd cxd = (cxd) obj;
        if (this.f5938a.f5943e.mo3175a()) {
            return new grf(new cxj(this, cxd), this.f5938a.f5940b);
        }
        return new grf(new cxk(this, cxd), this.f5938a.f5940b);
    }
}
