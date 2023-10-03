package p000;

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/* renamed from: aus */
/* compiled from: PG */
public final class aus implements aui {

    /* renamed from: a */
    private final auo f1724a;

    /* renamed from: b */
    private final aur f1725b;

    /* renamed from: c */
    private final Map f1726c;

    /* renamed from: d */
    private final Map f1727d;

    /* renamed from: e */
    private final int f1728e;

    /* renamed from: f */
    private int f1729f;

    public aus() {
        this.f1724a = new auo();
        this.f1725b = new aur();
        this.f1726c = new HashMap();
        this.f1727d = new HashMap();
        this.f1728e = 4194304;
    }

    public aus(int i) {
        this.f1724a = new auo();
        this.f1725b = new aur();
        this.f1726c = new HashMap();
        this.f1727d = new HashMap();
        this.f1728e = i;
    }

    /* renamed from: a */
    public final synchronized void mo1636a() {
        m1728b(0);
    }

    /* renamed from: b */
    private final void m1729b(int i, Class cls) {
        NavigableMap b = m1727b(cls);
        Integer valueOf = Integer.valueOf(i);
        Integer num = (Integer) b.get(valueOf);
        if (num == null) {
            String valueOf2 = String.valueOf(this);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf2).length() + 56);
            sb.append("Tried to decrement empty size, size: ");
            sb.append(i);
            sb.append(", this: ");
            sb.append(valueOf2);
            throw new NullPointerException(sb.toString());
        } else if (num.intValue() == 1) {
            b.remove(valueOf);
        } else {
            b.put(valueOf, Integer.valueOf(num.intValue() - 1));
        }
    }

    /* renamed from: b */
    private final void m1728b(int i) {
        while (this.f1729f > i) {
            Object a = this.f1724a.mo1649a();
            cns.m4632a(a);
            auh c = m1730c(a.getClass());
            this.f1729f -= c.mo1631a(a) * c.mo1630a();
            m1729b(c.mo1631a(a), a.getClass());
            c.mo1633b();
        }
    }

    /* renamed from: a */
    public final synchronized Object mo1634a(int i, Class cls) {
        auq auq;
        Integer num = (Integer) m1727b(cls).ceilingKey(Integer.valueOf(i));
        if (num != null) {
            int i2 = this.f1729f;
            if (i2 != 0) {
                if (this.f1728e / i2 < 2) {
                    if (num.intValue() <= (i << 3)) {
                    }
                }
            }
            auq = this.f1725b.mo1657a(num.intValue(), cls);
        }
        auq = this.f1725b.mo1657a(i, cls);
        return m1726a(auq, cls);
    }

    /* renamed from: c */
    private final auh m1730c(Class cls) {
        auh auh = (auh) this.f1727d.get(cls);
        if (auh == null) {
            if (cls.equals(int[].class)) {
                auh = new aup();
            } else if (cls.equals(byte[].class)) {
                auh = new aum();
            } else {
                String valueOf = String.valueOf(cls.getSimpleName());
                throw new IllegalArgumentException(valueOf.length() == 0 ? new String("No array pool found for: ") : "No array pool found for: ".concat(valueOf));
            }
            this.f1727d.put(cls, auh);
        }
        return auh;
    }

    /* renamed from: a */
    public final synchronized Object mo1635a(Class cls) {
        return m1726a(this.f1725b.mo1657a(8, cls), cls);
    }

    /* renamed from: a */
    private final Object m1726a(auq auq, Class cls) {
        auh c = m1730c(cls);
        Object a = this.f1724a.mo1650a((auv) auq);
        if (a != null) {
            this.f1729f -= c.mo1631a(a) * c.mo1630a();
            m1729b(c.mo1631a(a), cls);
        }
        if (a != null) {
            return a;
        }
        c.mo1633b();
        return c.mo1632a(auq.f1721a);
    }

    /* renamed from: b */
    private final NavigableMap m1727b(Class cls) {
        NavigableMap navigableMap = (NavigableMap) this.f1726c.get(cls);
        if (navigableMap != null) {
            return navigableMap;
        }
        TreeMap treeMap = new TreeMap();
        this.f1726c.put(cls, treeMap);
        return treeMap;
    }

    /* renamed from: a */
    public final synchronized void mo1638a(Object obj) {
        Class<?> cls = obj.getClass();
        auh c = m1730c(cls);
        int a = c.mo1631a(obj);
        int a2 = c.mo1630a() * a;
        int i = 1;
        if (a2 <= (this.f1728e >> 1)) {
            auq a3 = this.f1725b.mo1657a(a, cls);
            this.f1724a.mo1651a(a3, obj);
            NavigableMap b = m1727b((Class) cls);
            Integer num = (Integer) b.get(Integer.valueOf(a3.f1721a));
            Integer valueOf = Integer.valueOf(a3.f1721a);
            if (num != null) {
                i = 1 + num.intValue();
            }
            b.put(valueOf, Integer.valueOf(i));
            this.f1729f += a2;
            m1728b(this.f1728e);
        }
    }

    /* renamed from: a */
    public final synchronized void mo1637a(int i) {
        if (i >= 40) {
            mo1636a();
        } else if (i >= 20 || i == 15) {
            m1728b(this.f1728e >> 1);
        }
    }
}
