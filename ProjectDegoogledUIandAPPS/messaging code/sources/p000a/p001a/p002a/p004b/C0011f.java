package p000a.p001a.p002a.p004b;

import java.util.Iterator;

/* renamed from: a.a.a.b.f */
abstract class C0011f implements Iterator, C0012g {

    /* renamed from: Tn */
    C0009d f7Tn;
    C0009d mNext;

    C0011f(C0009d dVar, C0009d dVar2) {
        this.f7Tn = dVar2;
        this.mNext = dVar;
    }

    private C0009d nextNode() {
        C0009d dVar = this.mNext;
        C0009d dVar2 = this.f7Tn;
        if (dVar == dVar2 || dVar2 == null) {
            return null;
        }
        return mo13c(dVar);
    }

    /* renamed from: a */
    public void mo20a(C0009d dVar) {
        C0009d dVar2 = null;
        if (this.f7Tn == dVar && dVar == this.mNext) {
            this.mNext = null;
            this.f7Tn = null;
        }
        C0009d dVar3 = this.f7Tn;
        if (dVar3 == dVar) {
            this.f7Tn = mo12b(dVar3);
        }
        C0009d dVar4 = this.mNext;
        if (dVar4 == dVar) {
            C0009d dVar5 = this.f7Tn;
            if (!(dVar4 == dVar5 || dVar5 == null)) {
                dVar2 = mo13c(dVar4);
            }
            this.mNext = dVar2;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public abstract C0009d mo12b(C0009d dVar);

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public abstract C0009d mo13c(C0009d dVar);

    public boolean hasNext() {
        return this.mNext != null;
    }

    public Object next() {
        C0009d dVar = this.mNext;
        this.mNext = nextNode();
        return dVar;
    }
}
