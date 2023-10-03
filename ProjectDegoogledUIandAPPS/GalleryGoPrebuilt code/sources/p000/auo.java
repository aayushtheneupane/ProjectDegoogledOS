package p000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: auo */
/* compiled from: PG */
final class auo {

    /* renamed from: a */
    private final aun f1719a = new aun();

    /* renamed from: b */
    private final Map f1720b = new HashMap();

    /* renamed from: a */
    public final Object mo1650a(auv auv) {
        aun aun = (aun) this.f1720b.get(auv);
        if (aun == null) {
            aun = new aun(auv);
            this.f1720b.put(auv, aun);
        } else {
            auv.mo1653a();
        }
        m1715b(aun);
        aun aun2 = this.f1719a;
        aun.f1718d = aun2;
        aun.f1717c = aun2.f1717c;
        m1714a(aun);
        return aun.mo1647a();
    }

    /* renamed from: a */
    public final void mo1651a(auv auv, Object obj) {
        aun aun = (aun) this.f1720b.get(auv);
        if (aun == null) {
            aun = new aun(auv);
            m1715b(aun);
            aun aun2 = this.f1719a;
            aun.f1718d = aun2.f1718d;
            aun.f1717c = aun2;
            m1714a(aun);
            this.f1720b.put(auv, aun);
        } else {
            auv.mo1653a();
        }
        if (aun.f1716b == null) {
            aun.f1716b = new ArrayList();
        }
        aun.f1716b.add(obj);
    }

    /* renamed from: b */
    private static void m1715b(aun aun) {
        aun aun2 = aun.f1718d;
        aun2.f1717c = aun.f1717c;
        aun.f1717c.f1718d = aun2;
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [auv, java.lang.Object] */
    /* renamed from: a */
    public final Object mo1649a() {
        for (aun aun = this.f1719a.f1718d; !aun.equals(this.f1719a); aun = aun.f1718d) {
            Object a = aun.mo1647a();
            if (a != null) {
                return a;
            }
            m1715b(aun);
            this.f1720b.remove(aun.f1715a);
            aun.f1715a.mo1653a();
        }
        return null;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GroupedLinkedMap( ");
        aun aun = this.f1719a.f1717c;
        boolean z = false;
        while (!aun.equals(this.f1719a)) {
            sb.append('{');
            sb.append(aun.f1715a);
            sb.append(':');
            sb.append(aun.mo1648b());
            sb.append("}, ");
            aun = aun.f1717c;
            z = true;
        }
        if (z) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(" )");
        return sb.toString();
    }

    /* renamed from: a */
    private static void m1714a(aun aun) {
        aun.f1717c.f1718d = aun;
        aun.f1718d.f1717c = aun;
    }
}
