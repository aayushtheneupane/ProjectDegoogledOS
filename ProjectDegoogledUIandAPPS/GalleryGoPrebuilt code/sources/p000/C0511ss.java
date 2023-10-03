package p000;

/* renamed from: ss */
/* compiled from: PG */
final class C0511ss implements C0485rt {

    /* renamed from: a */
    private final /* synthetic */ C0512st f15878a;

    public C0511ss(C0512st stVar) {
        this.f15878a = stVar;
    }

    /* renamed from: a */
    public final void mo9574a(C0472rg rgVar, boolean z) {
        if (rgVar instanceof C0495sc) {
            rgVar.mo9868j().mo9835a(false);
        }
        C0485rt rtVar = this.f15878a.f15694e;
        if (rtVar != null) {
            rtVar.mo9574a(rgVar, z);
        }
    }

    /* renamed from: a */
    public final boolean mo9575a(C0472rg rgVar) {
        if (rgVar != null) {
            C0512st stVar = this.f15878a;
            stVar.f15885m = ((C0495sc) rgVar).f15854k.f15780a;
            C0485rt rtVar = stVar.f15694e;
            if (rtVar != null) {
                return rtVar.mo9575a(rgVar);
            }
        }
        return false;
    }
}
