package androidx.fragment.app;

import androidx.lifecycle.C0442A;
import androidx.lifecycle.C0466w;
import androidx.lifecycle.C0467x;
import androidx.lifecycle.C0469z;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* renamed from: androidx.fragment.app.K */
class C0392K extends C0466w {

    /* renamed from: hq */
    private static final C0467x f350hq = new C0391J();

    /* renamed from: bq */
    private final HashSet f351bq = new HashSet();

    /* renamed from: cq */
    private final HashMap f352cq = new HashMap();

    /* renamed from: dq */
    private final HashMap f353dq = new HashMap();

    /* renamed from: eq */
    private final boolean f354eq;

    /* renamed from: fq */
    private boolean f355fq = false;

    /* renamed from: gq */
    private boolean f356gq = false;

    C0392K(boolean z) {
        this.f354eq = z;
    }

    /* renamed from: a */
    static C0392K m344a(C0442A a) {
        return (C0392K) new C0469z(a, f350hq).mo4482d(C0392K.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: Dc */
    public void mo247Dc() {
        this.f355fq = true;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Ec */
    public Collection mo4156Ec() {
        return this.f351bq;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: Fc */
    public boolean mo4157Fc() {
        return this.f355fq;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo4158b(C0424j jVar) {
        return this.f351bq.add(jVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public C0442A mo4159e(C0424j jVar) {
        C0442A a = (C0442A) this.f353dq.get(jVar.mWho);
        if (a != null) {
            return a;
        }
        C0442A a2 = new C0442A();
        this.f353dq.put(jVar.mWho, a2);
        return a2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C0392K.class != obj.getClass()) {
            return false;
        }
        C0392K k = (C0392K) obj;
        if (!this.f351bq.equals(k.f351bq) || !this.f352cq.equals(k.f352cq) || !this.f353dq.equals(k.f353dq)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hashCode = this.f352cq.hashCode();
        return this.f353dq.hashCode() + ((hashCode + (this.f351bq.hashCode() * 31)) * 31);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: k */
    public boolean mo4162k(C0424j jVar) {
        return this.f351bq.remove(jVar);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: r */
    public void mo4163r(C0424j jVar) {
        C0392K k = (C0392K) this.f352cq.get(jVar.mWho);
        if (k != null) {
            k.mo247Dc();
            this.f352cq.remove(jVar.mWho);
        }
        C0442A a = (C0442A) this.f353dq.get(jVar.mWho);
        if (a != null) {
            a.clear();
            this.f353dq.remove(jVar.mWho);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: s */
    public C0392K mo4164s(C0424j jVar) {
        C0392K k = (C0392K) this.f352cq.get(jVar.mWho);
        if (k != null) {
            return k;
        }
        C0392K k2 = new C0392K(this.f354eq);
        this.f352cq.put(jVar.mWho, k2);
        return k2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: t */
    public boolean mo4165t(C0424j jVar) {
        if (!this.f351bq.contains(jVar)) {
            return true;
        }
        if (this.f354eq) {
            return this.f355fq;
        }
        return !this.f356gq;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.f351bq.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.f352cq.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.f353dq.keySet().iterator();
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
