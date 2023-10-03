package p000;

/* renamed from: ag */
/* compiled from: PG */
final class C0007ag extends C0008ah implements C0627x {

    /* renamed from: d */
    private final C0681z f364d;

    /* renamed from: e */
    private final /* synthetic */ C0009ai f365e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0007ag(C0009ai aiVar, C0681z zVar, C0011ak akVar) {
        super(aiVar, akVar);
        this.f365e = aiVar;
        this.f364d = zVar;
    }

    /* renamed from: a */
    public final boolean mo341a(C0681z zVar) {
        return this.f364d == zVar;
    }

    /* renamed from: b */
    public final void mo342b() {
        this.f364d.mo5ad().mo65b(this);
    }

    /* renamed from: a */
    public final void mo3a(C0681z zVar, C0546u uVar) {
        if (this.f364d.mo5ad().mo61a() == C0573v.DESTROYED) {
            this.f365e.mo511a(this.f466a);
        } else {
            mo456a(mo340a());
        }
    }

    /* renamed from: a */
    public final boolean mo340a() {
        return this.f364d.mo5ad().mo61a().mo10359a(C0573v.STARTED);
    }
}
