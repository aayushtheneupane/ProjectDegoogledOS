package p000;

/* renamed from: aoq */
/* compiled from: PG */
public final class aoq extends aon {

    /* renamed from: b */
    public int f1283b = 2048;

    /* renamed from: c */
    public String f1284c = "\n";

    /* renamed from: d */
    public String f1285d = "  ";

    public aoq() {
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public final int mo1355d() {
        return 4976;
    }

    /* renamed from: f */
    public final boolean mo1390f() {
        return (this.f1282a & 3) == 2;
    }

    /* renamed from: g */
    public final boolean mo1391g() {
        return (this.f1282a & 3) == 3;
    }

    private aoq(int i) {
        super(i);
    }

    public final Object clone() {
        try {
            aoq aoq = new aoq(this.f1282a);
            aoq.f1285d = this.f1285d;
            aoq.f1284c = this.f1284c;
            aoq.f1283b = this.f1283b;
            return aoq;
        } catch (ang e) {
            return null;
        }
    }

    /* renamed from: h */
    public final String mo1392h() {
        if (!mo1390f()) {
            return mo1391g() ? "UTF-16LE" : "UTF-8";
        }
        return "UTF-16BE";
    }

    /* renamed from: e */
    public final boolean mo1389e() {
        return mo1359a(512);
    }

    /* renamed from: c */
    public final boolean mo1387c() {
        return mo1359a(256);
    }

    /* renamed from: a */
    public final boolean mo1385a() {
        return mo1359a(16);
    }

    /* renamed from: b */
    public final boolean mo1386b() {
        return mo1359a(32);
    }
}
