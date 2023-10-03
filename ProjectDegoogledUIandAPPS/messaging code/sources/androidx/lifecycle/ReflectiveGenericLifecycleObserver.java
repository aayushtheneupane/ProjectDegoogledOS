package androidx.lifecycle;

class ReflectiveGenericLifecycleObserver implements C0451h {
    private final C0444a mInfo = C0446c.sInstance.mo4451b(this.mWrapped.getClass());
    private final Object mWrapped;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.mWrapped = obj;
    }

    /* renamed from: a */
    public void mo611a(C0453j jVar, Lifecycle$Event lifecycle$Event) {
        this.mInfo.mo4447a(jVar, lifecycle$Event, this.mWrapped);
    }
}
