package p000;

/* renamed from: gsp */
/* compiled from: PG */
final class gsp extends gsu {

    /* renamed from: a */
    private final String f11979a;

    public gsp(String str) {
        this.f11979a = str;
    }

    /* renamed from: a */
    public final String mo7022a() {
        return this.f11979a;
    }

    /* renamed from: b */
    public final void mo7023b() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof gsu) {
            gsu gsu = (gsu) obj;
            if (this.f11979a.equals(gsu.mo7022a())) {
                gsu.mo7023b();
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((this.f11979a.hashCode() ^ 1000003) * 1000003) ^ 1;
    }

    public final String toString() {
        String str = this.f11979a;
        StringBuilder sb = new StringBuilder(str.length() + 58);
        sb.append("UniqueWorkSpec{uniquenessKey=");
        sb.append(str);
        sb.append(", existingWorkPolicy=REPLACE}");
        return sb.toString();
    }
}
