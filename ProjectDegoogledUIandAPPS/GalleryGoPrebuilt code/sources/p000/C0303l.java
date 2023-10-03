package p000;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* renamed from: l */
/* compiled from: PG */
public class C0303l implements Iterable {

    /* renamed from: b */
    public C0195h f15178b;

    /* renamed from: c */
    public C0195h f15179c;

    /* renamed from: d */
    public final WeakHashMap f15180d = new WeakHashMap();

    /* renamed from: e */
    public int f15181e = 0;

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0303l) {
            C0303l lVar = (C0303l) obj;
            if (this.f15181e == lVar.f15181e) {
                Iterator it = iterator();
                Iterator it2 = lVar.iterator();
                while (it.hasNext() && it2.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    Object next = it2.next();
                    if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                        return false;
                    }
                }
                return !it.hasNext() && !it2.hasNext();
            }
        }
    }

    /* renamed from: a */
    public C0195h mo4622a(Object obj) {
        C0195h hVar = this.f15178b;
        while (hVar != null && !hVar.f12388a.equals(obj)) {
            hVar = hVar.f12390c;
        }
        return hVar;
    }

    public final int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((Map.Entry) it.next()).hashCode();
        }
        return i;
    }

    public final Iterator iterator() {
        C0139f fVar = new C0139f(this.f15178b, this.f15179c);
        this.f15180d.put(fVar, false);
        return fVar;
    }

    /* renamed from: a */
    public final C0222i mo9306a() {
        C0222i iVar = new C0222i(this);
        this.f15180d.put(iVar, false);
        return iVar;
    }

    /* renamed from: a */
    public final C0195h mo9305a(Object obj, Object obj2) {
        C0195h hVar = new C0195h(obj, obj2);
        this.f15181e++;
        C0195h hVar2 = this.f15179c;
        if (hVar2 == null) {
            this.f15178b = hVar;
            this.f15179c = hVar;
            return hVar;
        }
        hVar2.f12390c = hVar;
        hVar.f12391d = hVar2;
        this.f15179c = hVar;
        return hVar;
    }

    /* renamed from: b */
    public Object mo4623b(Object obj) {
        C0195h a = mo4622a(obj);
        if (a == null) {
            return null;
        }
        this.f15181e--;
        if (!this.f15180d.isEmpty()) {
            for (C0276k c : this.f15180d.keySet()) {
                c.mo8313c(a);
            }
        }
        C0195h hVar = a.f12391d;
        if (hVar == null) {
            this.f15178b = a.f12390c;
        } else {
            hVar.f12390c = a.f12390c;
        }
        C0195h hVar2 = a.f12390c;
        if (hVar2 != null) {
            hVar2.f12391d = hVar;
        } else {
            this.f15179c = hVar;
        }
        a.f12390c = null;
        a.f12391d = null;
        return a.f12389b;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((Map.Entry) it.next()).toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
