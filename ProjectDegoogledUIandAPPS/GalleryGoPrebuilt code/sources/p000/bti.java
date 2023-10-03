package p000;

import java.util.List;

/* renamed from: bti */
/* compiled from: PG */
final class bti extends btl {

    /* renamed from: a */
    private final cxe f3536a;

    /* renamed from: b */
    private final List f3537b;

    /* renamed from: c */
    private final long f3538c;

    /* renamed from: d */
    private final String f3539d;

    /* renamed from: e */
    private final int f3540e;

    public /* synthetic */ bti(cxe cxe, int i, List list, long j, String str) {
        this.f3536a = cxe;
        this.f3540e = i;
        this.f3537b = list;
        this.f3538c = j;
        this.f3539d = str;
    }

    /* renamed from: a */
    public final cxe mo2737a() {
        return this.f3536a;
    }

    /* renamed from: b */
    public final List mo2738b() {
        return this.f3537b;
    }

    /* renamed from: c */
    public final long mo2739c() {
        return this.f3538c;
    }

    /* renamed from: d */
    public final String mo2740d() {
        return this.f3539d;
    }

    /* renamed from: e */
    public final int mo2741e() {
        return this.f3540e;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof btl) {
            btl btl = (btl) obj;
            if (this.f3536a.equals(btl.mo2737a())) {
                int i = this.f3540e;
                int e = btl.mo2741e();
                if (i != 0) {
                    return i == e && this.f3537b.equals(btl.mo2738b()) && this.f3538c == btl.mo2739c() && this.f3539d.equals(btl.mo2740d());
                }
                throw null;
            }
        }
    }

    public final int hashCode() {
        cxe cxe = this.f3536a;
        int i = cxe.f14173y;
        if (i == 0) {
            i = ikp.f14397a.mo8876a((Object) cxe).mo8862a(cxe);
            cxe.f14173y = i;
        }
        int d = ggf.m10258d(this.f3540e);
        int hashCode = this.f3537b.hashCode();
        long j = this.f3538c;
        return ((((((d ^ ((i ^ 1000003) * 1000003)) * 1000003) ^ hashCode) * 1000003) ^ ((int) ((j >>> 32) ^ j))) * 1000003) ^ this.f3539d.hashCode();
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f3536a);
        String c = ggf.m10256c(this.f3540e);
        String valueOf2 = String.valueOf(this.f3537b);
        long j = this.f3538c;
        String str = this.f3539d;
        int length = String.valueOf(valueOf).length();
        int length2 = c.length();
        StringBuilder sb = new StringBuilder(length + 94 + length2 + String.valueOf(valueOf2).length() + String.valueOf(str).length());
        sb.append("DeviceFolder{folder=");
        sb.append(valueOf);
        sb.append(", storageType=");
        sb.append(c);
        sb.append(", mediaList=");
        sb.append(valueOf2);
        sb.append(", size=");
        sb.append(j);
        sb.append(", humanReadableSize=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
