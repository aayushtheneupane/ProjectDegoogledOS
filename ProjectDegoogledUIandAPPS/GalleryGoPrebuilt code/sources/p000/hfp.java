package p000;

/* renamed from: hfp */
/* compiled from: PG */
final class hfp extends hfs {

    /* renamed from: a */
    public final int f12661a;

    /* renamed from: b */
    public final int f12662b;

    public hfp(int i, int i2) {
        this.f12661a = i;
        this.f12662b = i2;
    }

    /* renamed from: a */
    public final int mo7365a() {
        return this.f12661a;
    }

    /* renamed from: b */
    public final int mo7366b() {
        return this.f12662b;
    }

    public final int hashCode() {
        return ((this.f12661a ^ 1000003) * 1000003) ^ this.f12662b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof hfs) {
            hfs hfs = (hfs) obj;
            return this.f12661a == hfs.mo7365a() && this.f12662b == hfs.mo7366b();
        }
    }

    public final String toString() {
        String str = this.f12661a != 1 ? "CACHE" : "FILES";
        String str2 = this.f12662b != 1 ? "CREDENTIAL" : "DEVICE";
        StringBuilder sb = new StringBuilder(str.length() + 31 + str2.length());
        sb.append("StorageSpec{type=");
        sb.append(str);
        sb.append(", directBoot=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }
}
