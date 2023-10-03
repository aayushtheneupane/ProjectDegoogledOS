package androidx.lifecycle;

import java.util.HashMap;

/* renamed from: androidx.lifecycle.A */
public class C0442A {
    private final HashMap mMap = new HashMap();

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo4441a(String str, C0466w wVar) {
        C0466w wVar2 = (C0466w) this.mMap.put(str, wVar);
        if (wVar2 != null) {
            wVar2.mo247Dc();
        }
    }

    public final void clear() {
        for (C0466w clear : this.mMap.values()) {
            clear.clear();
        }
        this.mMap.clear();
    }

    /* access modifiers changed from: package-private */
    public final C0466w get(String str) {
        return (C0466w) this.mMap.get(str);
    }
}
