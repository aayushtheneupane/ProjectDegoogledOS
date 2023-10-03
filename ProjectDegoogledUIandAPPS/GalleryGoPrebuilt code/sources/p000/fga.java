package p000;

import java.util.ArrayList;
import java.util.List;

/* renamed from: fga */
/* compiled from: PG */
final class fga implements fdq {

    /* renamed from: a */
    private final fdr f9496a;

    /* renamed from: b */
    private fdr f9497b;

    /* renamed from: c */
    private List f9498c;

    /* renamed from: d */
    private boolean f9499d = false;

    public fga(fdr fdr) {
        this.f9496a = fdr;
    }

    /* renamed from: a */
    public final boolean mo5523a() {
        return this.f9497b == null;
    }

    /* renamed from: b */
    public final fdr mo5524b() {
        return this.f9497b;
    }

    /* renamed from: d */
    public final void mo5528d() {
    }

    /* renamed from: f */
    public final boolean mo5530f() {
        return this.f9499d;
    }

    /* renamed from: i */
    public final int mo5533i() {
        return 1;
    }

    /* renamed from: b */
    public final void mo5525b(fdr fdr) {
        if (this.f9498c == null) {
            this.f9498c = new ArrayList();
        }
        fdq e = fdr.mo5544e(ffa.f9433a);
        e.mo5522a(this.f9496a);
        ife.m12890c(this.f9498c.add(fdr));
        if (this.f9499d) {
            e.mo5529e();
        }
    }

    /* renamed from: c */
    public final void mo5526c() {
        ife.m12876b(this.f9497b != null, (Object) "No parent override to unset");
        this.f9497b = null;
    }

    /* renamed from: h */
    public final void mo5532h() {
        List list = this.f9498c;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                m8761d((fdr) list.get(i));
            }
            this.f9498c.clear();
            this.f9498c = null;
        }
        fdr fdr = this.f9497b;
        if (fdr != null) {
            fdr.mo5544e(ffa.f9433a).mo5527c(this.f9496a);
        }
    }

    /* renamed from: e */
    public final void mo5529e() {
        ife.m12896d(!this.f9499d);
        this.f9499d = true;
        this.f9496a.mo5545f(ffa.f9433a);
        List list = this.f9498c;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((fdr) list.get(i)).mo5544e(ffa.f9433a).mo5529e();
            }
        }
    }

    /* renamed from: g */
    public final void mo5531g() {
        ife.m12896d(this.f9499d);
        this.f9499d = false;
        List list = this.f9498c;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                ((fdr) list.get(i)).mo5544e(ffa.f9433a).mo5531g();
            }
        }
        this.f9496a.mo5546g(ffa.f9433a);
    }

    /* renamed from: c */
    public final void mo5527c(fdr fdr) {
        ife.m12890c(this.f9498c.remove(fdr));
        m8761d(fdr);
    }

    /* renamed from: d */
    private final void m8761d(fdr fdr) {
        fdq e = fdr.mo5544e(ffa.f9433a);
        if (this.f9499d) {
            ife.m12890c(e.mo5530f());
            e.mo5531g();
        }
        e.mo5526c();
    }

    /* renamed from: a */
    public final void mo5522a(fdr fdr) {
        boolean z;
        ife.m12898e((Object) fdr);
        if (this.f9497b == null) {
            z = true;
        } else {
            z = false;
        }
        ife.m12876b(z, (Object) "Already has a parent override");
        this.f9497b = fdr;
    }

    /* renamed from: a */
    public final void mo5521a(fdl fdl) {
        List list = this.f9498c;
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                fdl.mo5515a((fdr) this.f9498c.get(size));
            }
        }
    }
}
