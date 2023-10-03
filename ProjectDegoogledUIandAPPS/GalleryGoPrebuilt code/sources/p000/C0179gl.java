package p000;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* renamed from: gl */
/* compiled from: PG */
final class C0179gl {

    /* renamed from: a */
    public final ArrayList f11561a = new ArrayList();

    /* renamed from: b */
    public final HashMap f11562b = new HashMap();

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6822a(C0147fh fhVar) {
        if (!this.f11561a.contains(fhVar)) {
            synchronized (this.f11561a) {
                this.f11561a.add(fhVar);
            }
            fhVar.f9597p = true;
            return;
        }
        throw new IllegalStateException("Fragment already added: " + fhVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6821a() {
        this.f11562b.values().removeAll(Collections.singleton((Object) null));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo6824a(String str) {
        return this.f11562b.containsKey(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final C0147fh mo6825b(String str) {
        C0178gk gkVar = (C0178gk) this.f11562b.get(str);
        if (gkVar != null) {
            return gkVar.f11534b;
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final List mo6828c() {
        ArrayList arrayList = new ArrayList();
        for (C0178gk gkVar : this.f11562b.values()) {
            if (gkVar != null) {
                arrayList.add(gkVar.f11534b);
            } else {
                arrayList.add((Object) null);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final List mo6826b() {
        ArrayList arrayList;
        if (this.f11561a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f11561a) {
            arrayList = new ArrayList(this.f11561a);
        }
        return arrayList;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final void mo6823a(C0178gk gkVar) {
        this.f11562b.put(gkVar.f11534b.f9591j, gkVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final void mo6827b(C0147fh fhVar) {
        synchronized (this.f11561a) {
            this.f11561a.remove(fhVar);
        }
        fhVar.f9597p = false;
    }
}
