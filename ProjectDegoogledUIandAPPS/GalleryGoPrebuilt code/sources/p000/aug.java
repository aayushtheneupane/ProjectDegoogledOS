package p000;

import java.util.Collections;
import java.util.List;

/* renamed from: aug */
/* compiled from: PG */
final class aug implements asq, asp {

    /* renamed from: a */
    public final asr f1707a;

    /* renamed from: b */
    public final asp f1708b;

    /* renamed from: c */
    public Object f1709c;

    /* renamed from: d */
    public asn f1710d;

    /* renamed from: e */
    private int f1711e;

    /* renamed from: f */
    private asm f1712f;

    /* renamed from: g */
    private volatile axn f1713g;

    public aug(asr asr, asp asp) {
        this.f1707a = asr;
        this.f1708b = asp;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean mo1629a(axn axn) {
        axn axn2 = this.f1713g;
        return axn2 != null && axn2 == axn;
    }

    /* renamed from: c */
    public final void mo1555c() {
        throw null;
    }

    /* renamed from: b */
    public final void mo1551b() {
        axn axn = this.f1713g;
        if (axn != null) {
            axn.f1831c.mo1517c();
        }
    }

    /* renamed from: a */
    public final void mo1553a(aqu aqu, Exception exc, ari ari, int i) {
        this.f1708b.mo1553a(aqu, exc, ari, this.f1713g.f1831c.mo1518d());
    }

    /* renamed from: a */
    public final void mo1554a(aqu aqu, Object obj, ari ari, int i, aqu aqu2) {
        this.f1708b.mo1554a(aqu, obj, ari, this.f1713g.f1831c.mo1518d(), aqu);
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public final boolean mo1550a() {
        Object obj = this.f1709c;
        if (obj != null) {
            this.f1709c = null;
            bfk.m2412a();
            try {
                aqk a = this.f1707a.f1539c.f1316c.f1329b.mo1839a(obj.getClass());
                if (a != null) {
                    aso aso = new aso(a, obj, this.f1707a.f1545i);
                    this.f1710d = new asn(this.f1713g.f1829a, this.f1707a.f1550n);
                    this.f1707a.mo1556a().mo1668a(this.f1710d, aso);
                    this.f1713g.f1831c.mo1516b();
                    this.f1712f = new asm(Collections.singletonList(this.f1713g.f1829a), this.f1707a, this);
                } else {
                    throw new apg(obj.getClass());
                }
            } catch (Throwable th) {
                this.f1713g.f1831c.mo1516b();
                throw th;
            }
        }
        asm asm = this.f1712f;
        if (asm != null && asm.mo1550a()) {
            return true;
        }
        this.f1712f = null;
        this.f1713g = null;
        boolean z = false;
        while (!z && this.f1711e < this.f1707a.mo1562c().size()) {
            List c = this.f1707a.mo1562c();
            int i = this.f1711e;
            this.f1711e = i + 1;
            this.f1713g = (axn) c.get(i);
            if (this.f1713g != null && (this.f1707a.f1552p.mo1579a(this.f1713g.f1831c.mo1518d()) || this.f1707a.mo1558a(this.f1713g.f1831c.mo1510a()))) {
                this.f1713g.f1831c.mo1514a(this.f1707a.f1551o, new auf(this, this.f1713g));
                z = true;
            }
        }
        return z;
    }
}
