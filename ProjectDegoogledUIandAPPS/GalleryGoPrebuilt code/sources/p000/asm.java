package p000;

import java.io.File;
import java.util.List;

/* renamed from: asm */
/* compiled from: PG */
final class asm implements asq, arh {

    /* renamed from: a */
    private final List f1523a;

    /* renamed from: b */
    private final asr f1524b;

    /* renamed from: c */
    private final asp f1525c;

    /* renamed from: d */
    private int f1526d;

    /* renamed from: e */
    private aqu f1527e;

    /* renamed from: f */
    private List f1528f;

    /* renamed from: g */
    private int f1529g;

    /* renamed from: h */
    private volatile axn f1530h;

    /* renamed from: i */
    private File f1531i;

    public asm(asr asr, asp asp) {
        this(asr.mo1563d(), asr, asp);
    }

    public asm(List list, asr asr, asp asp) {
        this.f1526d = -1;
        this.f1523a = list;
        this.f1524b = asr;
        this.f1525c = asp;
    }

    /* renamed from: b */
    public final void mo1551b() {
        axn axn = this.f1530h;
        if (axn != null) {
            axn.f1831c.mo1517c();
        }
    }

    /* renamed from: c */
    private final boolean m1560c() {
        return this.f1529g < this.f1528f.size();
    }

    /* renamed from: a */
    public final void mo1525a(Object obj) {
        this.f1525c.mo1554a(this.f1527e, obj, this.f1530h.f1831c, 3, this.f1527e);
    }

    /* renamed from: a */
    public final void mo1524a(Exception exc) {
        this.f1525c.mo1553a(this.f1527e, exc, this.f1530h.f1831c, 3);
    }

    /* renamed from: a */
    public final boolean mo1550a() {
        while (true) {
            boolean z = false;
            if (this.f1528f == null || !m1560c()) {
                int i = this.f1526d + 1;
                this.f1526d = i;
                if (i >= this.f1523a.size()) {
                    return false;
                }
                aqu aqu = (aqu) this.f1523a.get(this.f1526d);
                File a = this.f1524b.mo1556a().mo1666a(new asn(aqu, this.f1524b.f1550n));
                this.f1531i = a;
                if (a != null) {
                    this.f1527e = aqu;
                    this.f1528f = this.f1524b.mo1557a(a);
                    this.f1529g = 0;
                }
            } else {
                this.f1530h = null;
                while (!z && m1560c()) {
                    List list = this.f1528f;
                    int i2 = this.f1529g;
                    this.f1529g = i2 + 1;
                    File file = this.f1531i;
                    asr asr = this.f1524b;
                    this.f1530h = ((axo) list.get(i2)).mo1697a(file, asr.f1541e, asr.f1542f, asr.f1545i);
                    if (this.f1530h != null && this.f1524b.mo1558a(this.f1530h.f1831c.mo1510a())) {
                        this.f1530h.f1831c.mo1514a(this.f1524b.f1551o, this);
                        z = true;
                    }
                }
                return z;
            }
        }
    }
}
