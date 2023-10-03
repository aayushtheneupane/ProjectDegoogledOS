package p000;

/* renamed from: gwu */
/* compiled from: PG */
final class gwu extends gwy {

    /* renamed from: a */
    private final String f12212a;

    /* renamed from: b */
    private final String f12213b;

    /* renamed from: c */
    private final ihw f12214c;

    /* renamed from: d */
    private final long f12215d;

    public gwu(String str, String str2, ihw ihw, long j) {
        this.f12212a = str;
        this.f12213b = str2;
        this.f12214c = ihw;
        this.f12215d = j;
    }

    /* renamed from: a */
    public final String mo7157a() {
        return this.f12212a;
    }

    /* renamed from: b */
    public final String mo7158b() {
        return this.f12213b;
    }

    /* renamed from: c */
    public final ihw mo7159c() {
        return this.f12214c;
    }

    /* renamed from: d */
    public final long mo7160d() {
        return this.f12215d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof gwy)) {
            return false;
        }
        gwy gwy = (gwy) obj;
        String str = this.f12212a;
        if (str == null ? gwy.mo7157a() == null : str.equals(gwy.mo7157a())) {
            String str2 = this.f12213b;
            if (str2 == null ? gwy.mo7158b() == null : str2.equals(gwy.mo7158b())) {
                ihw ihw = this.f12214c;
                if (ihw == null ? gwy.mo7159c() == null : ihw.equals(gwy.mo7159c())) {
                    if (this.f12215d == gwy.mo7160d()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        String str = this.f12212a;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) ^ 1000003) * 1000003;
        String str2 = this.f12213b;
        int hashCode2 = (hashCode ^ (str2 != null ? str2.hashCode() : 0)) * 1000003;
        ihw ihw = this.f12214c;
        if (ihw != null) {
            i = ihw.hashCode();
        }
        long j = this.f12215d;
        return ((hashCode2 ^ i) * 1000003) ^ ((int) ((j >>> 32) ^ j));
    }

    public final String toString() {
        String str = this.f12212a;
        String str2 = this.f12213b;
        String valueOf = String.valueOf(this.f12214c);
        long j = this.f12215d;
        int length = String.valueOf(str).length();
        StringBuilder sb = new StringBuilder(length + 100 + String.valueOf(str2).length() + String.valueOf(valueOf).length());
        sb.append("Metadata{serverToken=");
        sb.append(str);
        sb.append(", snapshotToken=");
        sb.append(str2);
        sb.append(", experimentToken=");
        sb.append(valueOf);
        sb.append(", lastUpdateEpochMillis=");
        sb.append(j);
        sb.append("}");
        return sb.toString();
    }
}
