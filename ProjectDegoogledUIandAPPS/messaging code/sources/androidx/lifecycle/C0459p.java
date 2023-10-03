package androidx.lifecycle;

/* renamed from: androidx.lifecycle.p */
abstract class C0459p {

    /* renamed from: Tp */
    int f447Tp = -1;
    boolean mActive;
    final C0463t mObserver;
    final /* synthetic */ C0460q this$0;

    C0459p(C0460q qVar, C0463t tVar) {
        this.this$0 = qVar;
        this.mObserver = tVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: A */
    public void mo4467A(boolean z) {
        if (z != this.mActive) {
            this.mActive = z;
            int i = 1;
            boolean z2 = this.this$0.mActiveCount == 0;
            C0460q qVar = this.this$0;
            int i2 = qVar.mActiveCount;
            if (!this.mActive) {
                i = -1;
            }
            qVar.mActiveCount = i2 + i;
            if (z2 && this.mActive) {
                this.this$0.onActive();
            }
            C0460q qVar2 = this.this$0;
            if (qVar2.mActiveCount == 0 && !this.mActive) {
                qVar2.mo229Ac();
            }
            if (this.mActive) {
                this.this$0.mo4469a(this);
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public boolean mo4444g(C0453j jVar) {
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: xc */
    public void mo4445xc() {
    }

    /* access modifiers changed from: package-private */
    /* renamed from: yc */
    public abstract boolean mo4446yc();
}
