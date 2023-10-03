package p000;

/* renamed from: djn */
/* compiled from: PG */
public final class djn implements dji {

    /* renamed from: a */
    public final djh f6669a;

    /* renamed from: b */
    public final dil f6670b;

    /* renamed from: c */
    public final iel f6671c;

    /* renamed from: d */
    private final Object f6672d = new Object();

    /* renamed from: e */
    private grf f6673e;

    /* renamed from: f */
    private final gus f6674f;

    /* renamed from: g */
    private final iel f6675g;

    /* renamed from: h */
    private final inw f6676h;

    public djn(djh djh, dil dil, gus gus, iel iel, iel iel2, inw inw) {
        this.f6669a = djh;
        this.f6670b = dil;
        this.f6674f = gus;
        this.f6675g = iel;
        this.f6671c = iel2;
        this.f6676h = inw;
    }

    /* renamed from: a */
    public final ieh mo4162a() {
        ieh a;
        synchronized (this.f6672d) {
            if (this.f6673e == null) {
                this.f6673e = new grf(new djj(this), this.f6675g);
            }
            a = this.f6673e.mo6948a();
        }
        return a;
    }

    /* renamed from: b */
    public final void mo4163b() {
        synchronized (this.f6672d) {
            this.f6673e = null;
        }
        this.f6674f.mo7099b(ife.m12820a((Object) null), (Object) "SPECIAL_TYPE_DATA_SERVICE_KEY");
        ((ble) this.f6676h.mo9034a()).mo2554a();
    }
}
