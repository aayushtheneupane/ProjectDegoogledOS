package p000;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: asr */
/* compiled from: PG */
public final class asr {

    /* renamed from: a */
    public final List f1537a = new ArrayList();

    /* renamed from: b */
    public final List f1538b = new ArrayList();

    /* renamed from: c */
    public apa f1539c;

    /* renamed from: d */
    public Object f1540d;

    /* renamed from: e */
    public int f1541e;

    /* renamed from: f */
    public int f1542f;

    /* renamed from: g */
    public Class f1543g;

    /* renamed from: h */
    public asv f1544h;

    /* renamed from: i */
    public aqz f1545i;

    /* renamed from: j */
    public Map f1546j;

    /* renamed from: k */
    public Class f1547k;

    /* renamed from: l */
    public boolean f1548l;

    /* renamed from: m */
    public boolean f1549m;

    /* renamed from: n */
    public aqu f1550n;

    /* renamed from: o */
    public apb f1551o;

    /* renamed from: p */
    public atc f1552p;

    /* renamed from: q */
    public boolean f1553q;

    /* renamed from: r */
    public boolean f1554r;

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final aui mo1560b() {
        return this.f1539c.f1315b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public final List mo1563d() {
        if (!this.f1549m) {
            this.f1549m = true;
            this.f1538b.clear();
            List c = mo1562c();
            int size = c.size();
            for (int i = 0; i < size; i++) {
                axn axn = (axn) c.get(i);
                if (!this.f1538b.contains(axn.f1829a)) {
                    this.f1538b.add(axn.f1829a);
                }
                for (int i2 = 0; i2 < axn.f1830b.size(); i2++) {
                    if (!this.f1538b.contains(axn.f1830b.get(i2))) {
                        this.f1538b.add((aqu) axn.f1830b.get(i2));
                    }
                }
            }
        }
        return this.f1538b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final avc mo1556a() {
        return this.f1544h.mo1566a();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final List mo1562c() {
        if (!this.f1548l) {
            this.f1548l = true;
            this.f1537a.clear();
            List a = this.f1539c.f1316c.mo1404a(this.f1540d);
            int size = a.size();
            for (int i = 0; i < size; i++) {
                axn a2 = ((axo) a.get(i)).mo1697a(this.f1540d, this.f1541e, this.f1542f, this.f1545i);
                if (a2 != null) {
                    this.f1537a.add(a2);
                }
            }
        }
        return this.f1537a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public final atx mo1559b(Class cls) {
        atx atx;
        atx atx2;
        int i;
        Class cls2 = cls;
        aph aph = this.f1539c.f1316c;
        Class cls3 = this.f1543g;
        Class cls4 = this.f1547k;
        bdr bdr = aph.f1335h;
        bfn bfn = (bfn) bdr.f2096c.getAndSet((Object) null);
        if (bfn == null) {
            bfn = new bfn();
        }
        bfn.mo1966a(cls2, cls3, cls4);
        synchronized (bdr.f2095b) {
            atx = (atx) bdr.f2095b.get(bfn);
        }
        bdr.f2096c.set(bfn);
        if (bdr.f2094a.equals(atx)) {
            return null;
        }
        if (atx != null) {
            return atx;
        }
        ArrayList arrayList = new ArrayList();
        List b = aph.f1330c.mo1847b(cls2, cls3);
        int size = b.size();
        int i2 = 0;
        while (i2 < size) {
            Class cls5 = (Class) b.get(i2);
            List b2 = aph.f1333f.mo1810b(cls5, cls4);
            int size2 = b2.size();
            int i3 = 0;
            while (true) {
                i = i2 + 1;
                if (i3 >= size2) {
                    break;
                }
                Class cls6 = (Class) b2.get(i3);
                asy asy = r2;
                int i4 = i3;
                int i5 = size2;
                List list = b2;
                List a = aph.f1330c.mo1844a(cls2, cls5);
                Class cls7 = cls5;
                asy asy2 = new asy(cls, cls5, cls6, a, aph.f1333f.mo1808a(cls5, cls6), aph.f1336i);
                arrayList.add(asy2);
                i3 = i4 + 1;
                cls5 = cls7;
                i2 = i2;
                b2 = list;
                size2 = i5;
            }
            i2 = i;
        }
        if (!arrayList.isEmpty()) {
            atx2 = new atx(cls, cls3, cls4, arrayList, aph.f1336i);
        } else {
            atx2 = null;
        }
        bdr bdr2 = aph.f1335h;
        synchronized (bdr2.f2095b) {
            bdr2.f2095b.put(new bfn(cls2, cls3, cls4), atx2 == null ? bdr.f2094a : atx2);
        }
        return atx2;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final List mo1557a(File file) {
        return this.f1539c.f1316c.mo1404a((Object) file);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public final ard mo1561c(Class cls) {
        ard ard = (ard) this.f1546j.get(cls);
        if (ard == null) {
            Iterator it = this.f1546j.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry entry = (Map.Entry) it.next();
                if (((Class) entry.getKey()).isAssignableFrom(cls)) {
                    ard = (ard) entry.getValue();
                    break;
                }
            }
        }
        if (ard != null) {
            return ard;
        }
        if (!this.f1546j.isEmpty() || !this.f1553q) {
            return azg.f1899b;
        }
        String valueOf = String.valueOf(cls);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 115);
        sb.append("Missing transformation for ");
        sb.append(valueOf);
        sb.append(". If you wish to ignore unknown resource types, use the optional transformation methods.");
        throw new IllegalArgumentException(sb.toString());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo1558a(Class cls) {
        return mo1559b(cls) != null;
    }
}
