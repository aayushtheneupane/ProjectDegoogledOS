package androidx.lifecycle;

class CompositeGeneratedAdaptersObserver implements C0451h {

    /* renamed from: Gp */
    private final C0448e[] f428Gp;

    CompositeGeneratedAdaptersObserver(C0448e[] eVarArr) {
        this.f428Gp = eVarArr;
    }

    /* renamed from: a */
    public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        C0461r rVar = new C0461r();
        for (C0448e a : this.f428Gp) {
            a.mo4459a(jVar, lifecycle$Event, false, rVar);
        }
        for (C0448e a2 : this.f428Gp) {
            a2.mo4459a(jVar, lifecycle$Event, true, rVar);
        }
    }
}
