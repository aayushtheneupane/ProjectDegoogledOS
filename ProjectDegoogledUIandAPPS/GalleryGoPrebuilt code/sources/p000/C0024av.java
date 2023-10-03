package p000;

/* renamed from: av */
/* compiled from: PG */
public final class C0024av {

    /* renamed from: a */
    private final C0021as f1751a;

    /* renamed from: b */
    private final C0025aw f1752b;

    public C0024av(C0025aw awVar, C0021as asVar) {
        this.f1751a = asVar;
        this.f1752b = awVar;
    }

    /* renamed from: a */
    public final C0020ar mo1665a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            String str = "android.arch.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName;
            C0020ar arVar = (C0020ar) this.f1752b.f1790a.get(str);
            if (cls.isInstance(arVar)) {
                C0021as asVar = this.f1751a;
                if (asVar instanceof C0023au) {
                    C0023au auVar = (C0023au) asVar;
                    return arVar;
                }
            } else {
                C0021as asVar2 = this.f1751a;
                if (asVar2 instanceof C0022at) {
                    arVar = ((C0022at) asVar2).mo1582b();
                } else {
                    arVar = asVar2.mo1541a();
                }
                C0020ar arVar2 = (C0020ar) this.f1752b.f1790a.put(str, arVar);
                if (arVar2 != null) {
                    arVar2.mo1506a();
                    return arVar;
                }
            }
            return arVar;
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }
}
