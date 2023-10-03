package p000;

import java.util.HashSet;

/* renamed from: fx */
/* compiled from: PG */
final class C0163fx implements C0189gu {

    /* renamed from: a */
    private final /* synthetic */ C0171gd f10653a;

    public C0163fx(C0171gd gdVar) {
        this.f10653a = gdVar;
    }

    /* renamed from: b */
    public final void mo6297b(C0147fh fhVar, C0259jj jjVar) {
        boolean z;
        synchronized (jjVar) {
            z = jjVar.f15079a;
        }
        if (!z) {
            C0171gd gdVar = this.f10653a;
            HashSet hashSet = (HashSet) gdVar.f10988g.get(fhVar);
            if (hashSet != null && hashSet.remove(jjVar) && hashSet.isEmpty()) {
                gdVar.f10988g.remove(fhVar);
                if (fhVar.f9587f < 3) {
                    gdVar.mo6435b(fhVar);
                    gdVar.mo6423a(fhVar, fhVar.mo5625F());
                }
            }
        }
    }

    /* renamed from: a */
    public final void mo6296a(C0147fh fhVar, C0259jj jjVar) {
        C0171gd gdVar = this.f10653a;
        if (gdVar.f10988g.get(fhVar) == null) {
            gdVar.f10988g.put(fhVar, new HashSet());
        }
        ((HashSet) gdVar.f10988g.get(fhVar)).add(jjVar);
    }
}
