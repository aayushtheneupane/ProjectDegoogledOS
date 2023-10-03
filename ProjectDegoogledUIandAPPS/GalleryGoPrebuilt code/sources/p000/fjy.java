package p000;

/* renamed from: fjy */
/* compiled from: PG */
public final class fjy {

    /* renamed from: a */
    public final String f9865a;

    public fjy(String str) {
        this.f9865a = str;
    }

    /* renamed from: a */
    public static String m9052a(fjy fjy) {
        if (fjy != null) {
            return fjy.f9865a;
        }
        return null;
    }

    public final String toString() {
        return this.f9865a;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof fjy) {
            return this.f9865a.equals(((fjy) obj).f9865a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f9865a.hashCode();
    }
}
