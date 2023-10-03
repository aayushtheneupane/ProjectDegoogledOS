package p000;

/* renamed from: aae */
/* compiled from: PG */
public final class aae implements C0627x, C0705zx {

    /* renamed from: a */
    private final C0600w f11a;

    /* renamed from: b */
    private final aad f12b;

    /* renamed from: c */
    private C0705zx f13c;

    /* renamed from: d */
    private final /* synthetic */ aag f14d;

    public aae(aag aag, C0600w wVar, aad aad) {
        this.f14d = aag;
        this.f11a = wVar;
        this.f12b = aad;
        wVar.mo64a(this);
    }

    /* renamed from: a */
    public final void mo14a() {
        this.f11a.mo65b(this);
        this.f12b.mo13b(this);
        C0705zx zxVar = this.f13c;
        if (zxVar != null) {
            zxVar.mo14a();
            this.f13c = null;
        }
    }

    /* renamed from: a */
    public final void mo3a(C0681z zVar, C0546u uVar) {
        if (uVar == C0546u.ON_START) {
            aag aag = this.f14d;
            aad aad = this.f12b;
            aag.f17a.add(aad);
            aaf aaf = new aaf(aag, aad);
            aad.mo12a(aaf);
            this.f13c = aaf;
        } else if (uVar == C0546u.ON_STOP) {
            C0705zx zxVar = this.f13c;
            if (zxVar != null) {
                zxVar.mo14a();
            }
        } else if (uVar == C0546u.ON_DESTROY) {
            mo14a();
        }
    }
}
