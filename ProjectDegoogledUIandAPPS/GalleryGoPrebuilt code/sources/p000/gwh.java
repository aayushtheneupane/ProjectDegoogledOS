package p000;

import java.util.List;

/* renamed from: gwh */
/* compiled from: PG */
public final class gwh extends gws {

    /* renamed from: a */
    public final hso f12187a;

    /* renamed from: b */
    public final boolean f12188b;

    /* renamed from: c */
    public final long f12189c;

    /* renamed from: d */
    private final int f12190d;

    public /* synthetic */ gwh(hso hso, boolean z, long j, int i) {
        this.f12187a = hso;
        this.f12188b = z;
        this.f12189c = j;
        this.f12190d = i;
    }

    /* renamed from: a */
    public final hso mo7137a() {
        return this.f12187a;
    }

    /* renamed from: b */
    public final boolean mo7138b() {
        return this.f12188b;
    }

    /* renamed from: c */
    public final long mo7139c() {
        return this.f12189c;
    }

    /* renamed from: d */
    public final int mo7140d() {
        return this.f12190d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gws) {
            gws gws = (gws) obj;
            if (ife.m12856a((List) this.f12187a, (Object) gws.mo7137a()) && this.f12188b == gws.mo7138b() && this.f12189c == gws.mo7139c()) {
                int i = this.f12190d;
                int d = gws.mo7140d();
                if (i == 0) {
                    throw null;
                } else if (i == d) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        int hashCode = (this.f12187a.hashCode() ^ 1000003) * 1000003;
        int i = !this.f12188b ? 1237 : 1231;
        long j = this.f12189c;
        int i2 = (((hashCode ^ i) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003;
        int i3 = this.f12190d;
        if (i3 != 0) {
            return i2 ^ i3;
        }
        throw null;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.f12187a);
        boolean z = this.f12188b;
        long j = this.f12189c;
        int i = this.f12190d;
        String str = i != 1 ? i != 2 ? "null" : "NORMAL" : "MINI";
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 87 + str.length());
        sb.append("DebugDataRequest{acceptTypes=");
        sb.append(valueOf);
        sb.append(", allowPii=");
        sb.append(z);
        sb.append(", timeLimitMs=");
        sb.append(j);
        sb.append(", size=");
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
