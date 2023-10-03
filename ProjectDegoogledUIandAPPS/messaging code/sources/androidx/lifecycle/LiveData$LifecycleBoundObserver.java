package androidx.lifecycle;

class LiveData$LifecycleBoundObserver extends C0459p implements C0451h {
    final C0453j mOwner;
    final /* synthetic */ C0460q this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LiveData$LifecycleBoundObserver(C0460q qVar, C0453j jVar, C0463t tVar) {
        super(qVar, tVar);
        this.this$0 = qVar;
        this.mOwner = jVar;
    }

    /* renamed from: a */
    public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        if (this.mOwner.getLifecycle().getCurrentState() == Lifecycle$State.DESTROYED) {
            this.this$0.mo234a(this.mObserver);
        } else {
            mo4467A(mo4446yc());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean mo4444g(C0453j jVar) {
        return this.mOwner == jVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: xc */
    public void mo4445xc() {
        this.mOwner.getLifecycle().mo4461b(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: yc */
    public boolean mo4446yc() {
        return this.mOwner.getLifecycle().getCurrentState().compareTo(Lifecycle$State.STARTED) >= 0;
    }
}
