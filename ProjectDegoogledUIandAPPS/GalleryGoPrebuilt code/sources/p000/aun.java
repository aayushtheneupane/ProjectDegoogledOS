package p000;

import java.util.List;

/* renamed from: aun */
/* compiled from: PG */
final class aun {

    /* renamed from: a */
    public final Object f1715a;

    /* renamed from: b */
    public List f1716b;

    /* renamed from: c */
    public aun f1717c;

    /* renamed from: d */
    public aun f1718d;

    public aun() {
        this((Object) null);
    }

    public aun(Object obj) {
        this.f1718d = this;
        this.f1717c = this;
        this.f1715a = obj;
    }

    /* renamed from: a */
    public final Object mo1647a() {
        int b = mo1648b();
        if (b > 0) {
            return this.f1716b.remove(b - 1);
        }
        return null;
    }

    /* renamed from: b */
    public final int mo1648b() {
        List list = this.f1716b;
        if (list == null) {
            return 0;
        }
        return list.size();
    }
}
