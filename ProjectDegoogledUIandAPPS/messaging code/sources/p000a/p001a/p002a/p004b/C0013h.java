package p000a.p001a.p002a.p004b;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import p026b.p027a.p030b.p031a.C0632a;

/* renamed from: a.a.a.b.h */
public class C0013h implements Iterable {

    /* renamed from: Un */
    private WeakHashMap f8Un = new WeakHashMap();
    private C0009d mEnd;
    private int mSize = 0;
    C0009d mStart;

    public Iterator descendingIterator() {
        C0008c cVar = new C0008c(this.mEnd, this.mStart);
        this.f8Un.put(cVar, false);
        return cVar;
    }

    public Map.Entry eldest() {
        return this.mStart;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C0013h)) {
            return false;
        }
        C0013h hVar = (C0013h) obj;
        if (this.mSize != hVar.mSize) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = hVar.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            Object next = it2.next();
            if ((entry == null && next != null) || (entry != null && !entry.equals(next))) {
                return false;
            }
        }
        if (it.hasNext() || it2.hasNext()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public C0009d get(Object obj) {
        C0009d dVar = this.mStart;
        while (dVar != null && !dVar.mKey.equals(obj)) {
            dVar = dVar.mNext;
        }
        return dVar;
    }

    public int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((Map.Entry) it.next()).hashCode();
        }
        return i;
    }

    public Iterator iterator() {
        C0007b bVar = new C0007b(this.mStart, this.mEnd);
        this.f8Un.put(bVar, false);
        return bVar;
    }

    /* renamed from: pc */
    public C0010e mo30pc() {
        C0010e eVar = new C0010e(this);
        this.f8Un.put(eVar, false);
        return eVar;
    }

    /* access modifiers changed from: protected */
    public C0009d put(Object obj, Object obj2) {
        C0009d dVar = new C0009d(obj, obj2);
        this.mSize++;
        C0009d dVar2 = this.mEnd;
        if (dVar2 == null) {
            this.mStart = dVar;
            this.mEnd = this.mStart;
            return dVar;
        }
        dVar2.mNext = dVar;
        dVar.f4Qn = dVar2;
        this.mEnd = dVar;
        return dVar;
    }

    public Object putIfAbsent(Object obj, Object obj2) {
        C0009d dVar = get(obj);
        if (dVar != null) {
            return dVar.mValue;
        }
        put(obj, obj2);
        return null;
    }

    /* renamed from: qc */
    public Map.Entry mo32qc() {
        return this.mEnd;
    }

    public Object remove(Object obj) {
        C0009d dVar = get(obj);
        if (dVar == null) {
            return null;
        }
        this.mSize--;
        if (!this.f8Un.isEmpty()) {
            for (C0012g a : this.f8Un.keySet()) {
                a.mo20a(dVar);
            }
        }
        C0009d dVar2 = dVar.f4Qn;
        if (dVar2 != null) {
            dVar2.mNext = dVar.mNext;
        } else {
            this.mStart = dVar.mNext;
        }
        C0009d dVar3 = dVar.mNext;
        if (dVar3 != null) {
            dVar3.f4Qn = dVar.f4Qn;
        } else {
            this.mEnd = dVar.f4Qn;
        }
        dVar.mNext = null;
        dVar.f4Qn = null;
        return dVar.mValue;
    }

    public int size() {
        return this.mSize;
    }

    public String toString() {
        StringBuilder Pa = C0632a.m1011Pa("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            Pa.append(((Map.Entry) it.next()).toString());
            if (it.hasNext()) {
                Pa.append(", ");
            }
        }
        Pa.append("]");
        return Pa.toString();
    }
}
