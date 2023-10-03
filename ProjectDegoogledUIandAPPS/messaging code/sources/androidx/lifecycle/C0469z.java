package androidx.lifecycle;

import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: androidx.lifecycle.z */
public class C0469z {
    private final C0467x mFactory;
    private final C0442A mViewModelStore;

    public C0469z(C0442A a, C0467x xVar) {
        this.mFactory = xVar;
        this.mViewModelStore = a;
    }

    /* renamed from: d */
    public C0466w mo4482d(Class cls) {
        C0466w wVar;
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String k = C0632a.m1025k("androidx.lifecycle.ViewModelProvider.DefaultKey:", canonicalName);
            C0466w wVar2 = this.mViewModelStore.get(k);
            if (cls.isInstance(wVar2)) {
                return wVar2;
            }
            C0467x xVar = this.mFactory;
            if (xVar instanceof C0468y) {
                wVar = ((C0468y) xVar).mo4481a(k, cls);
            } else {
                wVar = xVar.mo245a(cls);
            }
            C0466w wVar3 = wVar;
            this.mViewModelStore.mo4441a(k, wVar3);
            return wVar3;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
