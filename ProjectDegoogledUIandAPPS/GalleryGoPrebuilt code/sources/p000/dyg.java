package p000;

/* renamed from: dyg */
/* compiled from: PG */
final class dyg extends dyj {

    /* renamed from: a */
    private final cxd f7641a;

    /* renamed from: b */
    private final dxy f7642b;

    /* renamed from: c */
    private final int f7643c;

    public /* synthetic */ dyg(cxd cxd, int i, dxy dxy) {
        this.f7641a = cxd;
        this.f7643c = i;
        this.f7642b = dxy;
    }

    /* renamed from: a */
    public final cxd mo4566a() {
        return this.f7641a;
    }

    /* renamed from: b */
    public final dxy mo4567b() {
        return this.f7642b;
    }

    /* renamed from: c */
    public final int mo4568c() {
        return this.f7643c;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof dyj) {
            dyj dyj = (dyj) obj;
            if (this.f7641a.equals(dyj.mo4566a())) {
                int i = this.f7643c;
                int c = dyj.mo4568c();
                if (i != 0) {
                    return i == c && this.f7642b.equals(dyj.mo4567b());
                }
                throw null;
            }
        }
    }

    public final int hashCode() {
        cxd cxd = this.f7641a;
        int i = cxd.f14173y;
        if (i == 0) {
            i = ikp.f14397a.mo8876a((Object) cxd).mo8862a(cxd);
            cxd.f14173y = i;
        }
        int i2 = (i ^ 1000003) * 1000003;
        int i3 = this.f7643c;
        if (i3 != 0) {
            return this.f7642b.hashCode() ^ ((i2 ^ i3) * 1000003);
        }
        throw null;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f7641a);
        String b = C0643xp.m15945b(this.f7643c);
        String valueOf2 = String.valueOf(this.f7642b);
        int length = String.valueOf(valueOf).length();
        StringBuilder sb = new StringBuilder(length + 59 + b.length() + String.valueOf(valueOf2).length());
        sb.append("GridItemDataOptions{filter=");
        sb.append(valueOf);
        sb.append(", timeGranularity=");
        sb.append(b);
        sb.append(", sortMethod=");
        sb.append(valueOf2);
        sb.append("}");
        return sb.toString();
    }
}
