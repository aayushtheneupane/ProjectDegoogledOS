package p000a.p001a.p002a.p004b;

import java.util.Iterator;

/* renamed from: a.a.a.b.e */
class C0010e implements Iterator, C0012g {

    /* renamed from: Rn */
    private C0009d f5Rn;

    /* renamed from: Sn */
    private boolean f6Sn = true;
    final /* synthetic */ C0013h this$0;

    C0010e(C0013h hVar) {
        this.this$0 = hVar;
    }

    /* renamed from: a */
    public void mo20a(C0009d dVar) {
        C0009d dVar2 = this.f5Rn;
        if (dVar == dVar2) {
            this.f5Rn = dVar2.f4Qn;
            this.f6Sn = this.f5Rn == null;
        }
    }

    public boolean hasNext() {
        if (!this.f6Sn) {
            C0009d dVar = this.f5Rn;
            if (dVar == null || dVar.mNext == null) {
                return false;
            }
            return true;
        } else if (this.this$0.mStart != null) {
            return true;
        } else {
            return false;
        }
    }

    public Object next() {
        if (this.f6Sn) {
            this.f6Sn = false;
            this.f5Rn = this.this$0.mStart;
        } else {
            C0009d dVar = this.f5Rn;
            this.f5Rn = dVar != null ? dVar.mNext : null;
        }
        return this.f5Rn;
    }
}
