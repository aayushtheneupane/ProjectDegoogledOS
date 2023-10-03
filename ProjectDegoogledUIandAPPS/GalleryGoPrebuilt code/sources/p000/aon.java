package p000;

/* renamed from: aon */
/* compiled from: PG */
public abstract class aon {

    /* renamed from: a */
    public int f1282a = 0;

    public aon() {
    }

    /* renamed from: a */
    public final boolean mo1359a(int i) {
        return (i & this.f1282a) != 0;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo1361c(int i) {
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract int mo1355d();

    public final int hashCode() {
        return this.f1282a;
    }

    public aon(int i) {
        m1300d(i);
        mo1360b(i);
    }

    /* renamed from: d */
    private final void m1300d(int i) {
        int d = (mo1355d() ^ -1) & i;
        if (d == 0) {
            mo1361c(i);
            return;
        }
        String hexString = Integer.toHexString(d);
        StringBuilder sb = new StringBuilder(String.valueOf(hexString).length() + 33);
        sb.append("The option bit(s) 0x");
        sb.append(hexString);
        sb.append(" are invalid!");
        throw new ang(sb.toString(), 103);
    }

    public final boolean equals(Object obj) {
        return this.f1282a == ((aon) obj).f1282a;
    }

    /* renamed from: a */
    public final void mo1358a(int i, boolean z) {
        int i2;
        if (!z) {
            i2 = (i ^ -1) & this.f1282a;
        } else {
            i2 = i | this.f1282a;
        }
        this.f1282a = i2;
    }

    /* renamed from: b */
    public final void mo1360b(int i) {
        m1300d(i);
        this.f1282a = i;
    }

    public final String toString() {
        String valueOf = String.valueOf(Integer.toHexString(this.f1282a));
        return valueOf.length() == 0 ? new String("0x") : "0x".concat(valueOf);
    }
}
