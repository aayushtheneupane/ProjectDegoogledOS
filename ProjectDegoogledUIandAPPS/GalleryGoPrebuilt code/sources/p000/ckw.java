package p000;

/* renamed from: ckw */
/* compiled from: PG */
final class ckw extends cmh {

    /* renamed from: a */
    private final String f4590a;

    /* renamed from: b */
    private final String f4591b;

    public ckw(String str, String str2) {
        if (str != null) {
            this.f4590a = str;
            if (str2 != null) {
                this.f4591b = str2;
                return;
            }
            throw new NullPointerException("Null volumePath");
        }
        throw new NullPointerException("Null volumeUuid");
    }

    /* renamed from: a */
    public final String mo3222a() {
        return this.f4590a;
    }

    /* renamed from: b */
    public final String mo3223b() {
        return this.f4591b;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof cmh) {
            cmh cmh = (cmh) obj;
            return this.f4590a.equals(cmh.mo3222a()) && this.f4591b.equals(cmh.mo3223b());
        }
    }

    public final int hashCode() {
        return ((this.f4590a.hashCode() ^ 1000003) * 1000003) ^ this.f4591b.hashCode();
    }

    public final String toString() {
        String str = this.f4590a;
        String str2 = this.f4591b;
        StringBuilder sb = new StringBuilder(str.length() + 43 + str2.length());
        sb.append("VolumeChosenEvent{volumeUuid=");
        sb.append(str);
        sb.append(", volumePath=");
        sb.append(str2);
        sb.append("}");
        return sb.toString();
    }
}
