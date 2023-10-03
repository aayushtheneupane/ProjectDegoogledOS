package p000;

import java.util.HashMap;
import java.util.Iterator;

/* renamed from: gh */
/* compiled from: PG */
final class C0175gh extends C0020ar {

    /* renamed from: h */
    private static final C0021as f11330h = new C0174gg();

    /* renamed from: c */
    public final HashMap f11331c = new HashMap();

    /* renamed from: d */
    public final HashMap f11332d = new HashMap();

    /* renamed from: e */
    public final HashMap f11333e = new HashMap();

    /* renamed from: f */
    public final boolean f11334f;

    /* renamed from: g */
    public boolean f11335g = false;

    public C0175gh(boolean z) {
        this.f11334f = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C0175gh ghVar = (C0175gh) obj;
        return this.f11331c.equals(ghVar.f11331c) && this.f11332d.equals(ghVar.f11332d) && this.f11333e.equals(ghVar.f11333e);
    }

    /* renamed from: a */
    static C0175gh m10324a(C0025aw awVar) {
        return (C0175gh) new C0024av(awVar, f11330h).mo1665a(C0175gh.class);
    }

    public final int hashCode() {
        return (((this.f11331c.hashCode() * 31) + this.f11332d.hashCode()) * 31) + this.f11333e.hashCode();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final void mo1506a() {
        if (C0171gd.m10054a(3)) {
            "onCleared called for " + this;
        }
        this.f11335g = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo6666a(C0147fh fhVar) {
        if (!this.f11331c.containsKey(fhVar.f9591j) || !this.f11334f) {
            return true;
        }
        return this.f11335g;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.f11331c.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.f11332d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.f11333e.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}
