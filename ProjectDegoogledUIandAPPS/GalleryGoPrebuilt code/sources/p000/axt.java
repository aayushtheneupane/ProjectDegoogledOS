package p000;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* renamed from: axt */
/* compiled from: PG */
final class axt implements ari, arh {

    /* renamed from: a */
    private final List f1836a;

    /* renamed from: b */
    private final C0306lc f1837b;

    /* renamed from: c */
    private int f1838c = 0;

    /* renamed from: d */
    private apb f1839d;

    /* renamed from: e */
    private arh f1840e;

    /* renamed from: f */
    private List f1841f;

    /* renamed from: g */
    private boolean f1842g;

    public axt(List list, C0306lc lcVar) {
        this.f1837b = lcVar;
        cns.m4635a((Collection) list);
        this.f1836a = list;
    }

    /* renamed from: c */
    public final void mo1517c() {
        this.f1842g = true;
        List list = this.f1836a;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((ari) list.get(i)).mo1517c();
        }
    }

    /* renamed from: b */
    public final void mo1516b() {
        List list = this.f1841f;
        if (list != null) {
            this.f1837b.mo1972a(list);
        }
        this.f1841f = null;
        List list2 = this.f1836a;
        int size = list2.size();
        for (int i = 0; i < size; i++) {
            ((ari) list2.get(i)).mo1516b();
        }
    }

    /* renamed from: a */
    public final Class mo1510a() {
        return ((ari) this.f1836a.get(0)).mo1510a();
    }

    /* renamed from: d */
    public final int mo1518d() {
        return ((ari) this.f1836a.get(0)).mo1518d();
    }

    /* renamed from: a */
    public final void mo1514a(apb apb, arh arh) {
        this.f1839d = apb;
        this.f1840e = arh;
        this.f1841f = (List) this.f1837b.mo1971a();
        ((ari) this.f1836a.get(this.f1838c)).mo1514a(apb, this);
        if (this.f1842g) {
            mo1517c();
        }
    }

    /* renamed from: a */
    public final void mo1525a(Object obj) {
        if (obj != null) {
            this.f1840e.mo1525a(obj);
        } else {
            m1861e();
        }
    }

    /* renamed from: a */
    public final void mo1524a(Exception exc) {
        ((List) cns.m4632a((Object) this.f1841f)).add(exc);
        m1861e();
    }

    /* renamed from: e */
    private final void m1861e() {
        if (this.f1842g) {
            return;
        }
        if (this.f1838c < this.f1836a.size() - 1) {
            this.f1838c++;
            mo1514a(this.f1839d, this.f1840e);
            return;
        }
        cns.m4632a((Object) this.f1841f);
        this.f1840e.mo1524a((Exception) new atu("Fetch failed", (List) new ArrayList(this.f1841f)));
    }
}
