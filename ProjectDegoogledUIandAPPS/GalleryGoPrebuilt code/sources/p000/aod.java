package p000;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* renamed from: aod */
/* compiled from: PG */
public final class aod implements Comparable {

    /* renamed from: a */
    public String f1246a;

    /* renamed from: b */
    public String f1247b;

    /* renamed from: c */
    public aod f1248c;

    /* renamed from: d */
    public List f1249d;

    /* renamed from: e */
    public aop f1250e;

    /* renamed from: f */
    public boolean f1251f;

    /* renamed from: g */
    public boolean f1252g;

    /* renamed from: h */
    public boolean f1253h;

    /* renamed from: i */
    public boolean f1254i;

    /* renamed from: j */
    private List f1255j;

    public aod(String str, aop aop) {
        this(str, (String) null, aop);
    }

    public aod(String str, String str2, aop aop) {
        this.f1255j = null;
        this.f1249d = null;
        this.f1246a = str;
        this.f1247b = str2;
        this.f1250e = aop;
    }

    /* renamed from: a */
    public final void mo1312a(int i, aod aod) {
        m1238c(aod.f1246a);
        aod.f1248c = this;
        mo1330k().add(i - 1, aod);
    }

    /* renamed from: a */
    public final void mo1313a(aod aod) {
        m1238c(aod.f1246a);
        aod.f1248c = this;
        mo1330k().add(aod);
    }

    /* renamed from: c */
    public final void mo1319c(aod aod) {
        String str = aod.f1246a;
        if ("[]".equals(str) || mo1315b(str) == null) {
            aod.f1248c = this;
            aod.mo1328i().mo1358a(32, true);
            mo1328i().mo1369b(true);
            if (aod.m1239l()) {
                this.f1250e.mo1367a(true);
                m1241n().add(0, aod);
            } else if (aod.m1240m()) {
                this.f1250e.mo1371c(true);
                m1241n().add(this.f1250e.mo1372c() ? 1 : 0, aod);
            } else {
                m1241n().add(aod);
            }
        } else {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
            sb.append("Duplicate '");
            sb.append(str);
            sb.append("' qualifier");
            throw new ang(sb.toString(), 203);
        }
    }

    /* renamed from: c */
    private final void m1238c(String str) {
        if (!"[]".equals(str) && mo1310a(str) != null) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 35);
            sb.append("Duplicate property or field node '");
            sb.append(str);
            sb.append("'");
            throw new ang(sb.toString(), 203);
        }
    }

    /* renamed from: a */
    public final void mo1311a() {
        if (this.f1255j.isEmpty()) {
            this.f1255j = null;
        }
    }

    public final Object clone() {
        aop aop;
        try {
            aop = new aop(mo1328i().f1282a);
        } catch (ang e) {
            aop = new aop();
        }
        aod aod = new aod(this.f1246a, this.f1247b, aop);
        try {
            Iterator f = mo1325f();
            while (f.hasNext()) {
                aod.mo1313a((aod) ((aod) f.next()).clone());
            }
            Iterator h = mo1327h();
            while (h.hasNext()) {
                aod.mo1319c((aod) ((aod) h.next()).clone());
            }
        } catch (ang e2) {
        }
        return aod;
    }

    public final int compareTo(Object obj) {
        if (mo1328i().mo1379j()) {
            return this.f1247b.compareTo(((aod) obj).f1247b);
        }
        return this.f1246a.compareTo(((aod) obj).f1246a);
    }

    /* renamed from: a */
    private static final aod m1237a(List list, String str) {
        if (list == null) {
            return null;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            aod aod = (aod) it.next();
            if (aod.f1246a.equals(str)) {
                return aod;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final aod mo1310a(String str) {
        return m1237a(mo1330k(), str);
    }

    /* renamed from: b */
    public final aod mo1315b(String str) {
        return m1237a(this.f1249d, str);
    }

    /* renamed from: a */
    public final aod mo1309a(int i) {
        return (aod) mo1330k().get(i - 1);
    }

    /* renamed from: k */
    public final List mo1330k() {
        if (this.f1255j == null) {
            this.f1255j = new ArrayList(0);
        }
        return this.f1255j;
    }

    /* renamed from: c */
    public final int mo1318c() {
        List list = this.f1255j;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: i */
    public final aop mo1328i() {
        if (this.f1250e == null) {
            this.f1250e = new aop();
        }
        return this.f1250e;
    }

    /* renamed from: n */
    private final List m1241n() {
        if (this.f1249d == null) {
            this.f1249d = new ArrayList(0);
        }
        return this.f1249d;
    }

    /* renamed from: b */
    public final aod mo1314b(int i) {
        return (aod) m1241n().get(i - 1);
    }

    /* renamed from: d */
    public final int mo1322d() {
        List list = this.f1249d;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    /* renamed from: e */
    public final boolean mo1324e() {
        List list = this.f1255j;
        return list != null && list.size() > 0;
    }

    /* renamed from: g */
    public final boolean mo1326g() {
        List list = this.f1249d;
        return list != null && list.size() > 0;
    }

    /* renamed from: l */
    private final boolean m1239l() {
        return "xml:lang".equals(this.f1246a);
    }

    /* renamed from: m */
    private final boolean m1240m() {
        return "rdf:type".equals(this.f1246a);
    }

    /* renamed from: f */
    public final Iterator mo1325f() {
        if (this.f1255j != null) {
            return mo1330k().iterator();
        }
        return Collections.EMPTY_LIST.listIterator();
    }

    /* renamed from: h */
    public final Iterator mo1327h() {
        if (this.f1249d != null) {
            return new aoc(m1241n().iterator());
        }
        return Collections.EMPTY_LIST.iterator();
    }

    /* renamed from: b */
    public final void mo1317b(aod aod) {
        mo1330k().remove(aod);
        mo1311a();
    }

    /* renamed from: b */
    public final void mo1316b() {
        this.f1255j = null;
    }

    /* renamed from: d */
    public final void mo1323d(aod aod) {
        aop i = mo1328i();
        if (aod.m1239l()) {
            i.mo1367a(false);
        } else if (aod.m1240m()) {
            i.mo1371c(false);
        }
        m1241n().remove(aod);
        if (this.f1249d.isEmpty()) {
            i.mo1369b(false);
            this.f1249d = null;
        }
    }

    /* renamed from: j */
    public final void mo1329j() {
        int length;
        if (mo1326g()) {
            aod[] aodArr = (aod[]) m1241n().toArray(new aod[mo1322d()]);
            int i = 0;
            while (true) {
                length = aodArr.length;
                if (length <= i || (!"xml:lang".equals(aodArr[i].f1246a) && !"rdf:type".equals(aodArr[i].f1246a))) {
                    Arrays.sort(aodArr, i, length);
                    ListIterator listIterator = this.f1249d.listIterator();
                } else {
                    aodArr[i].mo1329j();
                    i++;
                }
            }
            Arrays.sort(aodArr, i, length);
            ListIterator listIterator2 = this.f1249d.listIterator();
            for (int i2 = 0; i2 < aodArr.length; i2++) {
                listIterator2.next();
                listIterator2.set(aodArr[i2]);
                aodArr[i2].mo1329j();
            }
        }
        if (mo1324e()) {
            if (!mo1328i().mo1375f()) {
                Collections.sort(this.f1255j);
            }
            Iterator f = mo1325f();
            while (f.hasNext()) {
                ((aod) f.next()).mo1329j();
            }
        }
    }
}
