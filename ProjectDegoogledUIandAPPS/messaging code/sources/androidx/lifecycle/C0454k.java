package androidx.lifecycle;

/* renamed from: androidx.lifecycle.k */
class C0454k {

    /* renamed from: Pp */
    C0451h f438Pp;
    Lifecycle$State mState;

    C0454k(C0452i iVar, Lifecycle$State lifecycle$State) {
        this.f438Pp = C0457n.m472k(iVar);
        this.mState = lifecycle$State;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo4463b(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        Lifecycle$State b = C0455l.m461b(lifecycle$Event);
        this.mState = C0455l.m460a(this.mState, b);
        this.f438Pp.mo611a(jVar, lifecycle$Event);
        this.mState = b;
    }
}
