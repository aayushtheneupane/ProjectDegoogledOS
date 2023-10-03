package p000;

/* renamed from: ats */
/* compiled from: PG */
public final class ats implements aua {

    /* renamed from: a */
    public final boolean f1660a;

    /* renamed from: b */
    private final aua f1661b;

    /* renamed from: c */
    private final atr f1662c;

    /* renamed from: d */
    private final aqu f1663d;

    /* renamed from: e */
    private int f1664e;

    /* renamed from: f */
    private boolean f1665f;

    public ats(aua aua, boolean z, aqu aqu, atr atr) {
        this.f1661b = (aua) cns.m4632a((Object) aua);
        this.f1660a = z;
        this.f1663d = aqu;
        this.f1662c = (atr) cns.m4632a((Object) atr);
    }

    /* renamed from: e */
    public final synchronized void mo1608e() {
        if (!this.f1665f) {
            this.f1664e++;
        } else {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
    }

    /* renamed from: b */
    public final Object mo1605b() {
        return this.f1661b.mo1605b();
    }

    /* renamed from: a */
    public final Class mo1604a() {
        return this.f1661b.mo1604a();
    }

    /* renamed from: c */
    public final int mo1606c() {
        return this.f1661b.mo1606c();
    }

    /* renamed from: d */
    public final synchronized void mo1607d() {
        if (this.f1664e > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        } else if (!this.f1665f) {
            this.f1665f = true;
            this.f1661b.mo1607d();
        } else {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public final void mo1609f() {
        int i;
        synchronized (this) {
            int i2 = this.f1664e;
            if (i2 > 0) {
                i = i2 - 1;
                this.f1664e = i;
            } else {
                throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
            }
        }
        if (i == 0) {
            this.f1662c.mo1584a(this.f1663d, this);
        }
    }

    public final synchronized String toString() {
        StringBuilder sb;
        boolean z = this.f1660a;
        String valueOf = String.valueOf(this.f1662c);
        String valueOf2 = String.valueOf(this.f1663d);
        int i = this.f1664e;
        boolean z2 = this.f1665f;
        String valueOf3 = String.valueOf(this.f1661b);
        int length = String.valueOf(valueOf).length();
        sb = new StringBuilder(length + 107 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("EngineResource{isMemoryCacheable=");
        sb.append(z);
        sb.append(", listener=");
        sb.append(valueOf);
        sb.append(", key=");
        sb.append(valueOf2);
        sb.append(", acquired=");
        sb.append(i);
        sb.append(", isRecycled=");
        sb.append(z2);
        sb.append(", resource=");
        sb.append(valueOf3);
        sb.append('}');
        return sb.toString();
    }
}
