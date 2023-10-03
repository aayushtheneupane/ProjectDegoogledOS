package androidx.lifecycle;

class FullLifecycleObserverAdapter implements C0451h {

    /* renamed from: Hp */
    private final C0447d f429Hp;

    /* renamed from: Ip */
    private final C0451h f430Ip;

    FullLifecycleObserverAdapter(C0447d dVar, C0451h hVar) {
        this.f429Hp = dVar;
        this.f430Ip = hVar;
    }

    /* renamed from: a */
    public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        switch (lifecycle$Event.ordinal()) {
            case 0:
                this.f429Hp.mo4457e(jVar);
                break;
            case 1:
                this.f429Hp.mo4456d(jVar);
                break;
            case 2:
                this.f429Hp.mo4453a(jVar);
                break;
            case 3:
                this.f429Hp.mo4454b(jVar);
                break;
            case 4:
                this.f429Hp.mo4458f(jVar);
                break;
            case 5:
                this.f429Hp.mo4455c(jVar);
                break;
            case 6:
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
        }
        C0451h hVar = this.f430Ip;
        if (hVar != null) {
            hVar.mo611a(jVar, lifecycle$Event);
        }
    }
}
