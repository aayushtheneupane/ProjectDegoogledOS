package androidx.fragment.app;

/* renamed from: androidx.fragment.app.G */
class C0388G implements C0421h {

    /* renamed from: Vo */
    final boolean f343Vo;

    /* renamed from: Wo */
    private int f344Wo;

    /* renamed from: go */
    final C0407a f345go;

    C0388G(C0407a aVar, boolean z) {
        this.f343Vo = z;
        this.f345go = aVar;
    }

    public void completeTransaction() {
        boolean z = this.f344Wo > 0;
        C0389H h = this.f345go.mManager;
        int size = h.mAdded.size();
        for (int i = 0; i < size; i++) {
            C0424j jVar = (C0424j) h.mAdded.get(i);
            jVar.setOnStartEnterTransitionListener((C0421h) null);
            if (z && jVar.isPostponed()) {
                jVar.startPostponedEnterTransition();
            }
        }
        C0407a aVar = this.f345go;
        aVar.mManager.mo4078a(aVar, this.f343Vo, !z, true);
    }

    public boolean isReady() {
        return this.f344Wo == 0;
    }

    public void onStartEnterTransition() {
        this.f344Wo--;
        if (this.f344Wo == 0) {
            this.f345go.mManager.mo4153wc();
        }
    }

    public void startListening() {
        this.f344Wo++;
    }
}
