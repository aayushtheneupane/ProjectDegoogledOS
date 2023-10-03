package p000;

/* renamed from: fcm */
/* compiled from: PG */
public final class fcm {

    /* renamed from: a */
    public final String f9268a;

    /* renamed from: b */
    public final int f9269b;

    public fcm(int i, String str) {
        this.f9269b = i;
        this.f9268a = str;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof fcm) {
            fcm fcm = (fcm) obj;
            if (fcm.f9269b - 1 != this.f9269b - 1 || !ife.m12891c((Object) fcm.f9268a, (Object) this.f9268a)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i = this.f9269b - 1;
        String str = this.f9268a;
        return i + ((str != null ? str.hashCode() : 0) * 31);
    }
}
