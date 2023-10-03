package p000;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: aub */
/* compiled from: PG */
final class aub implements asq, arh {

    /* renamed from: a */
    private final asp f1684a;

    /* renamed from: b */
    private final asr f1685b;

    /* renamed from: c */
    private int f1686c;

    /* renamed from: d */
    private int f1687d = -1;

    /* renamed from: e */
    private aqu f1688e;

    /* renamed from: f */
    private List f1689f;

    /* renamed from: g */
    private int f1690g;

    /* renamed from: h */
    private volatile axn f1691h;

    /* renamed from: i */
    private File f1692i;

    /* renamed from: j */
    private auc f1693j;

    public aub(asr asr, asp asp) {
        this.f1685b = asr;
        this.f1684a = asp;
    }

    /* renamed from: b */
    public final void mo1551b() {
        axn axn = this.f1691h;
        if (axn != null) {
            axn.f1831c.mo1517c();
        }
    }

    /* renamed from: c */
    private final boolean m1671c() {
        return this.f1690g < this.f1689f.size();
    }

    /* renamed from: a */
    public final void mo1525a(Object obj) {
        this.f1684a.mo1554a(this.f1688e, obj, this.f1691h.f1831c, 4, this.f1693j);
    }

    /* renamed from: a */
    public final void mo1524a(Exception exc) {
        this.f1684a.mo1553a(this.f1693j, exc, this.f1691h.f1831c, 4);
    }

    /* renamed from: a */
    public final boolean mo1550a() {
        List list;
        List d = this.f1685b.mo1563d();
        boolean z = false;
        if (d.isEmpty()) {
            return false;
        }
        asr asr = this.f1685b;
        aph aph = asr.f1539c.f1316c;
        Class<?> cls = asr.f1540d.getClass();
        Class cls2 = asr.f1543g;
        Class cls3 = asr.f1547k;
        bds bds = aph.f1334g;
        bfn bfn = (bfn) bds.f2097a.getAndSet((Object) null);
        if (bfn == null) {
            bfn = new bfn(cls, cls2, cls3);
        } else {
            bfn.mo1966a(cls, cls2, cls3);
        }
        synchronized (bds.f2098b) {
            list = (List) bds.f2098b.get(bfn);
        }
        bds.f2097a.set(bfn);
        if (list == null) {
            list = new ArrayList();
            for (Class b : aph.f1328a.mo1717a(cls)) {
                List b2 = aph.f1330c.mo1847b(b, cls2);
                int size = b2.size();
                for (int i = 0; i < size; i++) {
                    Class cls4 = (Class) b2.get(i);
                    if (!aph.f1333f.mo1810b(cls4, cls3).isEmpty() && !list.contains(cls4)) {
                        list.add(cls4);
                    }
                }
            }
            bds bds2 = aph.f1334g;
            List unmodifiableList = Collections.unmodifiableList(list);
            synchronized (bds2.f2098b) {
                bds2.f2098b.put(new bfn(cls, cls2, cls3), unmodifiableList);
            }
        }
        if (!list.isEmpty()) {
            while (true) {
                if (this.f1689f != null && m1671c()) {
                    this.f1691h = null;
                    while (!z && m1671c()) {
                        List list2 = this.f1689f;
                        int i2 = this.f1690g;
                        this.f1690g = i2 + 1;
                        File file = this.f1692i;
                        asr asr2 = this.f1685b;
                        this.f1691h = ((axo) list2.get(i2)).mo1697a(file, asr2.f1541e, asr2.f1542f, asr2.f1545i);
                        if (this.f1691h != null && this.f1685b.mo1558a(this.f1691h.f1831c.mo1510a())) {
                            this.f1691h.f1831c.mo1514a(this.f1685b.f1551o, this);
                            z = true;
                        }
                    }
                    return z;
                }
                int i3 = this.f1687d + 1;
                this.f1687d = i3;
                if (i3 >= list.size()) {
                    int i4 = this.f1686c + 1;
                    this.f1686c = i4;
                    if (i4 >= d.size()) {
                        return false;
                    }
                    this.f1687d = 0;
                }
                aqu aqu = (aqu) d.get(this.f1686c);
                Class cls5 = (Class) list.get(this.f1687d);
                ard c = this.f1685b.mo1561c(cls5);
                aui b3 = this.f1685b.mo1560b();
                asr asr3 = this.f1685b;
                this.f1693j = new auc(b3, aqu, asr3.f1550n, asr3.f1541e, asr3.f1542f, c, cls5, asr3.f1545i);
                File a = this.f1685b.mo1556a().mo1666a(this.f1693j);
                this.f1692i = a;
                if (a != null) {
                    this.f1688e = aqu;
                    this.f1689f = this.f1685b.mo1557a(a);
                    this.f1690g = 0;
                }
            }
        } else if (File.class.equals(this.f1685b.f1547k)) {
            return false;
        } else {
            String valueOf = String.valueOf(this.f1685b.f1540d.getClass());
            String valueOf2 = String.valueOf(this.f1685b.f1547k);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 38 + String.valueOf(valueOf2).length());
            sb.append("Failed to find any load path from ");
            sb.append(valueOf);
            sb.append(" to ");
            sb.append(valueOf2);
            throw new IllegalStateException(sb.toString());
        }
    }
}
