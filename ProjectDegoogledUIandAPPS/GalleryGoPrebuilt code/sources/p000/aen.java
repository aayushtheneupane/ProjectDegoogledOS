package p000;

import android.os.Bundle;

/* renamed from: aen */
/* compiled from: PG */
public final class aen {

    /* renamed from: a */
    public final aem f277a = new aem();

    /* renamed from: b */
    private final aeo f278b;

    private aen(aeo aeo) {
        this.f278b = aeo;
    }

    /* renamed from: a */
    public static aen m284a(aeo aeo) {
        return new aen(aeo);
    }

    /* renamed from: a */
    public final void mo250a(Bundle bundle) {
        C0600w ad = this.f278b.mo5ad();
        if (ad.mo61a() == C0573v.INITIALIZED) {
            ad.mo64a(new aei(this.f278b));
            aem aem = this.f277a;
            if (!aem.f276c) {
                if (bundle != null) {
                    aem.f275b = bundle.getBundle("android.arch.lifecycle.BundlableSavedStateRegistry.key");
                }
                ad.mo64a(new aej());
                aem.f276c = true;
                return;
            }
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
    }

    /* renamed from: b */
    public final void mo251b(Bundle bundle) {
        aem aem = this.f277a;
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = aem.f275b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        C0222i a = aem.f274a.mo9306a();
        while (a.hasNext()) {
            C0195h hVar = (C0195h) a.next();
            bundle2.putBundle((String) hVar.f12388a, ((ael) hVar.f12389b).mo249a());
        }
        bundle.putBundle("android.arch.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}
